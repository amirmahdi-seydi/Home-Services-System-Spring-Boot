package ir.maktab.homeservice.service;
/*
 * created by Amir mahdi seydi 6/4/2022 2:25 AM
 */


import ir.maktab.homeservice.exception.UnacceptableException;
import ir.maktab.homeservice.model.User;
import ir.maktab.homeservice.model.enumeration.UserState;
import ir.maktab.homeservice.util.ConformationToken;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class UserActivationService {

    private final JavaMailSender javaMailSender;

    private final UserService userService;

    private final CacheService cacheService;


    public UserActivationService(JavaMailSender javaMailSender,
                                 UserService userService,
                                 CacheService cacheService) {
        this.javaMailSender = javaMailSender;
        this.userService = userService;
        this.cacheService = cacheService;
    }


    public void sendActivationCode(ConformationToken conformationToken) {

        String id = conformationToken.getUserDTO().getId().toString();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(conformationToken.getUserDTO().getEmailAddress());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("home.service.maktab69@gmail.com");
        mailMessage.setText("To confirm your account, please click here : "
                + "http://localhost:8080/api/user/confirm-account?token=" + conformationToken.getConfirmationToken()
                + "&userId=" + id);

        sendEmail(mailMessage);

        cacheService.put(id, conformationToken);
    }


    public Boolean confirm(String conformation, String userId) {

        if (conformation != null) {
            ConformationToken value = cacheService.get(userId);
            if (conformation.equals(value.getConfirmationToken())) {
                User user = userService.findByUserName(value.getUserDTO().getUserName());
                if (user.getUserType().equals("customer")) {
                    user.setIsActive(true);
                    user.setUserState(UserState.NEW);
                } else {
                    user.setUserState(UserState.PENDING_CONFORMATION);
                }
                userService.save(user);
                cacheService.evict(userId);
            }
            return true;
        } else {
            throw new UnacceptableException("The link is invalid or broken!");
        }

    }


    @Async
    public void sendEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }

}
