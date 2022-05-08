package ir.maktab.homeservice.service;
/*
 * created by Amir mahdi seydi 5/7/2022 11:33 AM
 */

import ir.maktab.homeservice.model.User;
import ir.maktab.homeservice.model.enumeration.UserType;
import ir.maktab.homeservice.service.base.BaseService;
import ir.maktab.homeservice.service.dto.UserDTO;
import ir.maktab.homeservice.service.dto.extra.SecureUserDTO;


public interface UserService extends BaseService<User, Long> {

    public SecureUserDTO save(UserDTO userDTO);

    Boolean existsByUserName(String userName);

    Boolean existsByEmail(String email);

    Boolean existsByPhoneNumber(String phoneNumber);

    User findByUserName(String userName);

    UserType findUserTypeByUserName(String userName);

    byte[] findPassWord(String userName);
}
