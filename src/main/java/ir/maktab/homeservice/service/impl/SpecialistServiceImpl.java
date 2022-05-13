package ir.maktab.homeservice.service.impl;
/*
 * created by Amir mahdi seydi 5/7/2022 12:13 PM
 */

import ir.maktab.homeservice.config.security.CustomUserDetails;
import ir.maktab.homeservice.exception.AlreadyExistException;
import ir.maktab.homeservice.exception.NotFoundException;
import ir.maktab.homeservice.exception.UnacceptableException;
import ir.maktab.homeservice.model.FinancialCredit;
import ir.maktab.homeservice.model.Specialist;
import ir.maktab.homeservice.model.User;
import ir.maktab.homeservice.model.UserFactory;
import ir.maktab.homeservice.model.enumeration.UserState;
import ir.maktab.homeservice.repository.SpecialistRepository;
import ir.maktab.homeservice.service.FinancialCreditService;
import ir.maktab.homeservice.service.SpecialistService;
import ir.maktab.homeservice.service.UserService;
import ir.maktab.homeservice.service.base.BaseServiceImpl;
import ir.maktab.homeservice.service.dto.SpecialistDTO;
import ir.maktab.homeservice.service.dto.extra.SecureSpecialistDTO;
import ir.maktab.homeservice.util.CustomPasswordEncoder;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.List;


@Service
public class SpecialistServiceImpl extends BaseServiceImpl<Specialist, Long, SpecialistRepository>
        implements SpecialistService {

    private final UserService userService;

    private final FinancialCreditService financialCreditService;

    public SpecialistServiceImpl(SpecialistRepository repository, UserService userService, FinancialCreditService financialCreditService) {
        super(repository);
        this.userService = userService;
        this.financialCreditService = financialCreditService;
    }

    @Override
    public SecureSpecialistDTO save(SpecialistDTO specialistDTO) {

        if (repository.existsByUserName(specialistDTO.getUserName())) {
            throw new AlreadyExistException("This user name already exist!");
        } else if (repository.existsByEmailAddress(specialistDTO.getEmailAddress())) {
            throw new AlreadyExistException("This email already exist! ");
        } else if (repository.existsByMobileNumber(specialistDTO.getMobileNumber())) {
            throw new AlreadyExistException("This mobile number already exist");
        }
        if (specialistDTO.getId() == null) {
            UserFactory userFactory = new UserFactory();
            User specialistUser = userFactory.getUser("specialist");

            if (specialistUser instanceof Specialist) {
                FinancialCredit financialCredit = new FinancialCredit();
                financialCredit.setBalance(new BigDecimal("0.0"));
                financialCredit = financialCreditService.save(financialCredit);

                Specialist specialist = (Specialist) specialistUser;
                specialist.setFirstName(specialistDTO.getFirstName());
                specialist.setLastName(specialistDTO.getLastName());
                specialist.setEmailAddress(specialistDTO.getEmailAddress());
                specialist.setHashedPassword(CustomPasswordEncoder.hashPassword(specialistDTO.getPassword()));
                specialist.setDateOfRegistration(Date.from(Instant.now()));
                specialist.setScore(null);
                specialist.setProfileImage(getProfileImage(specialistDTO.getProfileImage()));
                specialist.setUserState(UserState.PENDING_CONFORMATION);
                specialist.setMobileNumber(specialistDTO.getMobileNumber());
                specialist.setUserName(specialistDTO.getUserName());
                specialist.setFinancialCredit(financialCredit);
                specialist = repository.save(specialist);

                return new SecureSpecialistDTO(
                        specialist.getId(),
                        specialist.getFirstName(),
                        specialist.getLastName(),
                        specialist.getEmailAddress(),
                        specialist.getIsActive(),
                        specialist.getDateOfRegistration(),
                        specialist.getUserName(),
                        specialist.getMobileNumber(),
                        specialist.getUserState(),
                        specialist.getScore(),
                        specialist.getProfileImage()
                );
            } else {
                return null;
            }
        } else {
            if (repository.findById(specialistDTO.getId()).isEmpty()) {
                throw new NotFoundException("There is no specialist with this id!");
            }

            CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal();
                User user = userService.findByUserName(userDetails.getUsername());
            if (!user.getId().equals(specialistDTO.getId()) && user.getUserType().equals("specialist")) {
                throw new AccessDeniedException("Access denied!");
            }

            Specialist specialist = repository.getById(specialistDTO.getId());
            specialist.setFirstName(specialistDTO.getFirstName());
            specialist.setLastName(specialistDTO.getLastName());
            specialist.setEmailAddress(specialistDTO.getEmailAddress());
            specialist.setProfileImage(getProfileImage(specialistDTO.getProfileImage()));
            specialist.setUserState(UserState.PENDING_CONFORMATION);
            specialist.setMobileNumber(specialistDTO.getMobileNumber());
            specialist.setUserName(specialistDTO.getUserName());
            specialist = repository.save(specialist);

            return new SecureSpecialistDTO(
                    specialist.getId(),
                    specialist.getFirstName(),
                    specialist.getLastName(),
                    specialist.getEmailAddress(),
                    specialist.getIsActive(),
                    specialist.getDateOfRegistration(),
                    specialist.getUserName(),
                    specialist.getMobileNumber(),
                    specialist.getUserState(),
                    specialist.getScore(),
                    specialist.getProfileImage()
            );
        }
    }

    @Override
    public List<SecureSpecialistDTO> fetchAllSpecialist() {
        return repository.fetchAllSpecialistDTOS();
    }

    @Override
    public User findByUserName(String userName) {
        return repository.findByUserName(userName);
    }

    @Override
    public Boolean existsByUserName(String userName) {
        return repository.existsByUserName(userName);
    }

    @Override
    public List<Specialist> findSpecialistBy(List<Long> ids) {
        return repository.findSpecialistBy(ids);
    }

    @Override
    public void deleteById(Long id) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        User user = userService.findByUserName(userDetails.getUsername());
        if (!user.getId().equals(id) && user.getUserType().equals("specialist")) {
            throw new AccessDeniedException("Access denied!");
        }
        super.deleteById(id);
    }

    private byte[] getProfileImage(File file) {
        byte[] profileImage = new byte[(int) file.length()];

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(profileImage);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (profileImage.length > 300000)
            throw new UnacceptableException("Image Size Should Not Exceed 300 KB!");

        return profileImage;
    }
}
