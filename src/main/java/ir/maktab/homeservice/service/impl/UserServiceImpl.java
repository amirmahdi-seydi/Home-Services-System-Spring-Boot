package ir.maktab.homeservice.service.impl;
/*
 * created by Amir mahdi seydi 5/7/2022 11:59 AM
 */

import com.google.common.base.Joiner;
import ir.maktab.homeservice.config.CustomAuthenticationProvider;
import ir.maktab.homeservice.config.JwtUtil;
import ir.maktab.homeservice.config.security.CustomUserDetails;
import ir.maktab.homeservice.config.security.MyUserDetailsService;
import ir.maktab.homeservice.exception.AccountNotActiveException;
import ir.maktab.homeservice.exception.NotFoundException;
import ir.maktab.homeservice.exception.UnacceptableException;
import ir.maktab.homeservice.model.User;
import ir.maktab.homeservice.model.enumeration.UserState;
import ir.maktab.homeservice.model.enumeration.UserType;
import ir.maktab.homeservice.model.specification.GenericSpecificationsBuilder;
import ir.maktab.homeservice.model.specification.UserSpecification;
import ir.maktab.homeservice.repository.UserRepository;
import ir.maktab.homeservice.service.UserActivationService;
import ir.maktab.homeservice.service.UserService;
import ir.maktab.homeservice.service.base.BaseServiceImpl;
import ir.maktab.homeservice.service.dto.ResetPasswordDTO;
import ir.maktab.homeservice.service.dto.UserDTO;
import ir.maktab.homeservice.service.dto.extra.SecureUserDTO;
import ir.maktab.homeservice.service.dto.extra.request.AuthenticationRequestDTO;
import ir.maktab.homeservice.service.dto.extra.request.AuthenticationResponseDTO;
import ir.maktab.homeservice.service.dto.extra.request.ChangePasswordDTO;
import ir.maktab.homeservice.util.ConformationToken;
import ir.maktab.homeservice.util.CustomPasswordEncoder;
import ir.maktab.homeservice.util.SearchOperation;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long, UserRepository>
        implements UserService {

    private final UserActivationService userActivationService;

    private final MyUserDetailsService myUserDetailsService;

    private final CustomAuthenticationProvider customAuthenticationProvider;

    private final JwtUtil jwtUtil;

    public UserServiceImpl(UserRepository repository,
                           @Lazy UserActivationService userActivationService,
                           @Lazy MyUserDetailsService myUserDetailsService,
                           @Lazy CustomAuthenticationProvider customAuthenticationProvider) {
        super(repository);
        this.userActivationService = userActivationService;
        this.myUserDetailsService = myUserDetailsService;
        this.customAuthenticationProvider = customAuthenticationProvider;
        this.jwtUtil = new JwtUtil();
    }

    @Override
    public SecureUserDTO save(UserDTO userDTO) {
        User user = repository.getById(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmailAddress());
        user.setUserName(userDTO.getUserName());
        user.setUserState(UserState.PENDING_CONFORMATION);
        user.setDateOfRegistration(userDTO.getDateOfRegistration());
        user.setPassword(CustomPasswordEncoder.hashPassword(userDTO.getPassword()));
        user.setMobileNumber(userDTO.getMobileNumber());
        User savedUser = repository.save(user);

        return new SecureUserDTO(
                savedUser.getId(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getEmail(),
                savedUser.getIsActive(),
                savedUser.getDateOfRegistration(),
                savedUser.getUserType(),
                savedUser.getUserName(),
                savedUser.getMobileNumber(),
                savedUser.getUserState()
        );
    }

    @Override
    public String changePassword(ChangePasswordDTO changePasswordDTO) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        String userName = userDetails.getUsername();
        User user = findByUserName(userName);
        String password = userDetails.getPassword();

        if (CustomPasswordEncoder.matches(changePasswordDTO.getOldPassword(), password)) {
            user.setPassword(CustomPasswordEncoder.hashPassword(changePasswordDTO.getNewPassword()));
            repository.save(user);
        } else {
            throw new UnacceptableException("The entered password is incorrect!");
        }

        return "Password changed successfully!";
    }

    @Override
    public String resetPassword(ResetPasswordDTO resetPasswordDTO) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        String userName = userDetails.getUsername();
        User admin = findByUserName(userName);
        if (!admin.getUserType().equals("admin")) {
            throw new AccessDeniedException("Access denied!");
        }
        if (findByUserName(resetPasswordDTO.getUserName()) == null) {
            throw new NotFoundException("Wrong user name!");
        }

        User user = findByUserName(resetPasswordDTO.getUserName());
        user.setPassword(CustomPasswordEncoder.hashPassword(resetPasswordDTO.getNewPassword()));
        repository.save(user);

        return "Password reset successfully!";
    }

    @Override
    public AuthenticationResponseDTO loginRequest(AuthenticationRequestDTO authenticationRequestDTO) {
        customAuthenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequestDTO.getUsername(),
                        authenticationRequestDTO.getPassword()
                )
        );
        UserDetails userDetails = myUserDetailsService.
                loadUserByUsername(authenticationRequestDTO.getUsername());
        CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;
        String generatedJWT = jwtUtil.generateToken(customUserDetails);

        if (!customUserDetails.getUser().getIsActive()){
            UserDTO userDTO = new UserDTO();
            userDTO.setUserName(customUserDetails.getUser().getUserName());
            userDTO.setEmailAddress(customUserDetails.getUser().getEmail());
            userDTO.setId(customUserDetails.getUser().getId());
            sendConfirmationLink(userDTO);
            throw new AccountNotActiveException("Account not active yet!");
        }

        return new AuthenticationResponseDTO(generatedJWT, new SecureUserDTO(
                customUserDetails.getUser().getId(),
                customUserDetails.getUser().getFirstName(),
                customUserDetails.getUser().getLastName(),
                customUserDetails.getUser().getEmail(),
                customUserDetails.getUser().getIsActive(),
                customUserDetails.getUser().getDateOfRegistration(),
                customUserDetails.getUser().getUserType(),
                customUserDetails.getUser().getUserName(),
                customUserDetails.getUser().getMobileNumber(),
                customUserDetails.getUser().getUserState()
        ));
    }

    @Override
    public String giveAccessByAdmin(Long id) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        User admin = findByUserName(userDetails.getUsername());
        if (findById(id).isEmpty()) {
           throw new NotFoundException("There is no user with this id");
        }
        if (!admin.getUserType().equals("admin")) {
            throw new AccessDeniedException("Access denied!");
        }
        User user = findById(id).get();
        user.setIsActive(Boolean.TRUE);
        user.setUserState(UserState.ACCEPTED);
        repository.save(user);
        return null;
    }

    @Override
    public Boolean existsByUserName(String userName) {
        return repository.existsByUserName(userName);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public Boolean existsByPhoneNumber(String phoneNumber) {
        return repository.existsByMobileNumber(phoneNumber);
    }

    @Override
    public User findByUserName(String userName) {
        return repository.findByUserName(userName);
    }

    @Override
    public UserType findUserTypeByUserName(String userName) {
        return repository.findUserTypeByUserName(userName);
    }

    @Override
    public byte[] findPassWord(String userName) {
        return repository.findPassword(userName);
    }

    @Override
    public List<User> findAll(String search) {
        GenericSpecificationsBuilder<User> builder = new GenericSpecificationsBuilder<>();
        String operationSetExpr = Joiner.on("|")
                .join(SearchOperation.SIMPLE_OPERATION_SET);
        Pattern pattern = Pattern.compile("(\\w+?)(" + operationSetExpr + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(4), matcher.group(3), matcher.group(5));
        }

        Specification<User> spec = builder.build(UserSpecification::new);
        return repository.findAll(spec);
    }


    @Override
    public void sendConfirmationLink(UserDTO user) {
        ConformationToken conformationTokenDTO = new ConformationToken();
        conformationTokenDTO.setConfirmationToken(UUID.randomUUID().toString());
        conformationTokenDTO.setDate(Date.from(Instant.now()));
        conformationTokenDTO.setUserDTO(user);

        userActivationService.sendActivationCode(conformationTokenDTO);
    }



}
