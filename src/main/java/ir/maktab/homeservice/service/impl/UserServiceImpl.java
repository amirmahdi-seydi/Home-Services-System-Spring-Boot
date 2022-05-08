package ir.maktab.homeservice.service.impl;
/*
 * created by Amir mahdi seydi 5/7/2022 11:59 AM
 */

import ir.maktab.homeservice.model.User;
import ir.maktab.homeservice.model.enumeration.UserState;
import ir.maktab.homeservice.model.enumeration.UserType;
import ir.maktab.homeservice.repository.UserRepository;
import ir.maktab.homeservice.service.UserService;
import ir.maktab.homeservice.service.base.BaseServiceImpl;
import ir.maktab.homeservice.service.dto.UserDTO;
import ir.maktab.homeservice.service.dto.extra.SecureUserDTO;
import ir.maktab.homeservice.util.CustomPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long, UserRepository>
        implements UserService {

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

    @Override
    public SecureUserDTO save(UserDTO userDTO) {
        User user = repository.getById(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmailAddress(userDTO.getEmailAddress());
        user.setUserName(userDTO.getUserName());
        user.setUserState(UserState.PENDING_CONFORMATION);
        user.setDateOfRegistration(userDTO.getDateOfRegistration());
        user.setHashedPassword(CustomPasswordEncoder.hashPassword(userDTO.getPassword()));
        user.setMobileNumber(userDTO.getMobileNumber());
        User savedUser = repository.save(user);

        return new SecureUserDTO(
                savedUser.getId(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getEmailAddress(),
                savedUser.getIsActive(),
                savedUser.getDateOfRegistration(),
                savedUser.getUserName(),
                savedUser.getMobileNumber(),
                savedUser.getUserState()
        );
    }


    @Override
    public Boolean existsByUserName(String userName) {
        return repository.existsByUserName(userName);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return repository.existsByEmailAddress(email);
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

}
