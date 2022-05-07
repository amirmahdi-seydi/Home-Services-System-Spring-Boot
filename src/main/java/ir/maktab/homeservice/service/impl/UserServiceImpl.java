package ir.maktab.homeservice.service.impl;
/*
 * created by Amir mahdi seydi 5/7/2022 11:59 AM
 */

import ir.maktab.homeservice.model.User;
import ir.maktab.homeservice.model.enumeration.UserType;
import ir.maktab.homeservice.repository.UserRepository;
import ir.maktab.homeservice.service.UserService;
import ir.maktab.homeservice.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long, UserRepository>
        implements UserService {

    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, @Lazy PasswordEncoder passwordEncoder) {
        super(repository);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Boolean existsByUserName(String userName) {
        return null;
    }

    @Override
    public Boolean existsByEmail(String email) {
        return null;
    }

    @Override
    public Boolean existsByPhoneNumber(String phoneNumber) {
        return null;
    }

    @Override
    public User findByUserName(String userName) {
        return null;
    }

    @Override
    public UserType findUserTypeByUserName(String userName) {
        return null;
    }

    @Override
    public byte[] findPassWord(String userName) {
        return new byte[0];
    }
}
