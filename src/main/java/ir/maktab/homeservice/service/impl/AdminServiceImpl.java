package ir.maktab.homeservice.service.impl;
/*
 * created by Amir mahdi seydi 5/7/2022 12:10 PM
 */

import ir.maktab.homeservice.exception.AlreadyExistException;
import ir.maktab.homeservice.model.Admin;
import ir.maktab.homeservice.model.Customer;
import ir.maktab.homeservice.model.User;
import ir.maktab.homeservice.model.UserFactory;
import ir.maktab.homeservice.model.enumeration.UserState;
import ir.maktab.homeservice.repository.AdminRepository;
import ir.maktab.homeservice.service.AdminService;
import ir.maktab.homeservice.service.base.BaseServiceImpl;
import ir.maktab.homeservice.service.dto.AdminDTO;
import ir.maktab.homeservice.service.dto.extra.SecureAdminDTO;
import ir.maktab.homeservice.service.dto.extra.SecureCustomerDTO;
import ir.maktab.homeservice.util.CustomPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;


@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin, Long, AdminRepository>
        implements AdminService {

    public AdminServiceImpl(AdminRepository repository) {
        super(repository);
    }

    @Override
    public SecureAdminDTO save(AdminDTO adminDTO) {
        if (adminDTO.getId() == null) {
            UserFactory userFactory = new UserFactory();
            User userAdmin = userFactory.getUser("admin");

            if (repository.existsByUserName(adminDTO.getUserName())) {
                throw new AlreadyExistException("This user name already exist!");
            } else if (repository.existsByEmail(adminDTO.getEmail())) {
                throw new AlreadyExistException("This email already exist! ");
            } else if (repository.existsByMobileNumber(adminDTO.getMobileNumber())) {
                throw new AlreadyExistException("This mobile number already exist");
            }

            if (userAdmin instanceof Admin) {
                Admin admin = (Admin) userAdmin;
                admin.setFirstName(adminDTO.getFirstName());
                admin.setLastName(adminDTO.getLastName());
                admin.setEmail(adminDTO.getEmail());
                admin.setMobileNumber(adminDTO.getMobileNumber());
                admin.setUserState(UserState.PENDING_CONFORMATION);
                admin.setPassword(CustomPasswordEncoder.hashPassword(adminDTO.getPassword()));
                admin.setDateOfRegistration(Date.from(Instant.now()));
                admin.setUserName(adminDTO.getUserName());
                admin = repository.save(admin);

                return new SecureAdminDTO(
                        admin.getId(),
                        admin.getFirstName(),
                        admin.getLastName(),
                        admin.getEmail(),
                        admin.getIsActive(),
                        admin.getDateOfRegistration(),
                        admin.getUserName(),
                        admin.getMobileNumber(),
                        admin.getUserState(),
                        admin.getIsSuperAdmin()
                );

            } else {
                return null;
            }
        } else {
            Admin admin = repository.getById(adminDTO.getId());
            admin.setFirstName(adminDTO.getFirstName());
            admin.setLastName(adminDTO.getLastName());
            admin.setEmail(adminDTO.getEmail());
            admin.setMobileNumber(adminDTO.getMobileNumber());
            admin.setUserState(UserState.PENDING_CONFORMATION);
            admin.setUserName(adminDTO.getUserName());

            return new SecureAdminDTO(
                    admin.getId(),
                    admin.getFirstName(),
                    admin.getLastName(),
                    admin.getEmail(),
                    admin.getIsActive(),
                    admin.getDateOfRegistration(),
                    admin.getUserName(),
                    admin.getMobileNumber(),
                    admin.getUserState(),
                    admin.getIsSuperAdmin()
            );
        }
    }

    @Override
    public List<SecureAdminDTO> fetchAllAdmins() {
        return repository.fetchAllAdminsDTOS();
    }
}
