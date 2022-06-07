package ir.maktab.homeservice.service;
/*
 * created by Amir mahdi seydi 5/7/2022 11:33 AM
 */

import ir.maktab.homeservice.model.Customer;
import ir.maktab.homeservice.model.User;
import ir.maktab.homeservice.model.enumeration.UserType;
import ir.maktab.homeservice.service.base.BaseService;
import ir.maktab.homeservice.service.dto.ResetPasswordDTO;
import ir.maktab.homeservice.service.dto.UserDTO;
import ir.maktab.homeservice.service.dto.extra.SecureUserDTO;
import ir.maktab.homeservice.service.dto.extra.request.AuthenticationRequestDTO;
import ir.maktab.homeservice.service.dto.extra.request.AuthenticationResponseDTO;
import ir.maktab.homeservice.service.dto.extra.request.ChangePasswordDTO;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserService extends BaseService<User, Long>{

    SecureUserDTO save(UserDTO userDTO);

    String changePassword(ChangePasswordDTO changePasswordDTO);

    String resetPassword(ResetPasswordDTO resetPasswordDTO);

    AuthenticationResponseDTO loginRequest(AuthenticationRequestDTO authenticationRequestDTO);

    String giveAccessByAdmin(Long id);

    Boolean existsByUserName(String userName);

    Boolean existsByEmail(String email);

    Boolean existsByPhoneNumber(String phoneNumber);

    User findByUserName(String userName);

    UserType findUserTypeByUserName(String userName);

    byte[] findPassWord(String userName);

    void sendConfirmationLink(UserDTO user);

    List<User> findAll(String search);
}
