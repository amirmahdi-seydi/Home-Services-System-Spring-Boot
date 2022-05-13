package ir.maktab.homeservice.service.impl;
/*
 * created by Amir mahdi seydi 5/7/2022 4:55 PM
 */

import ir.maktab.homeservice.config.security.CustomUserDetails;
import ir.maktab.homeservice.exception.AlreadyExistException;
import ir.maktab.homeservice.exception.NotFoundException;
import ir.maktab.homeservice.model.*;
import ir.maktab.homeservice.repository.ServiceRepository;
import ir.maktab.homeservice.service.CategoryService;
import ir.maktab.homeservice.service.ServiceService;
import ir.maktab.homeservice.service.SpecialistService;
import ir.maktab.homeservice.service.UserService;
import ir.maktab.homeservice.service.base.BaseServiceImpl;
import ir.maktab.homeservice.service.dto.extra.ServiceAbstractDTO;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.*;


@org.springframework.stereotype.Service
public class ServiceServiceImpl extends BaseServiceImpl<Service, Long, ServiceRepository>
        implements ServiceService {

    private final CategoryService categoryService;

    private final SpecialistService specialistService;

    private final UserService userService;

    public ServiceServiceImpl(ServiceRepository repository,
                              CategoryService categoryService,
                              SpecialistService specialistService,
                              UserService userService) {
        super(repository);
        this.categoryService = categoryService;
        this.specialistService = specialistService;
        this.userService = userService;
    }

    @Override
    public ServiceAbstractDTO save(ServiceAbstractDTO serviceDTO) {
        String serviceName = serviceDTO.getName().trim().replaceAll("\\s+", " ").toUpperCase();
        String categoryName = serviceDTO.getCategoryName().trim().replaceAll("\\s+", " ").toUpperCase();

        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        User user = userService.findByUserName(userDetails.getUsername());
        if (!user.getUserType().equals("admin")) {
            throw new AccessDeniedException("Access denied!");
        }

        if (!categoryService.existByCategoryName(categoryName)) {
            throw new NotFoundException("Service not found!");
        }

        if (serviceDTO.getId() == null) {
            if (repository.existsByName(serviceName)) {
                throw new AlreadyExistException("This SubService already define!");
            }

            Category category = categoryService.findCategoryByCategoryName(categoryName);

            serviceDTO.setName(serviceName);

            Service service = new Service();
            service.setName(serviceDTO.getName());
            service.setBasePrice(serviceDTO.getBasePrice());
            service.setDescription(serviceDTO.getDescription());
            service.setOptionalDescription(serviceDTO.getOptionalDescription());
            service.setCategory(category);

            List<Long> specialistId = new ArrayList<>();

            if (serviceDTO.getSpecialistId() != null) {
                List<Specialist> specialists = specialistService.findSpecialistBy(
                        Arrays.asList(serviceDTO.getSpecialistId())
                );

                Set<ServiceSpecialist> serviceSpecialists = new HashSet<>();
                for (Specialist specialist : specialists) {
                    ServiceSpecialist serviceSpecialist = new ServiceSpecialist();
                    serviceSpecialist.setService(service);
                    serviceSpecialist.setSpecialist(specialist);
                    specialistId.add(specialist.getId());
                }
                service.setServiceSpecialist(serviceSpecialists);
            }
            service = repository.save(service);

            Long[] ids = specialistId.toArray(new Long[0]);

            if (ids.length == 0) {
                return new ServiceAbstractDTO(
                        service.getId(),
                        service.getName(),
                        service.getDescription(),
                        service.getBasePrice(),
                        service.getOptionalDescription(),
                        service.getCategory().getCategoryName()
                );
            } else {
                return new ServiceAbstractDTO(
                        service.getId(), ids,
                        service.getName(),
                        service.getDescription(),
                        service.getBasePrice(),
                        service.getOptionalDescription(),
                        service.getCategory().getCategoryName()
                );
            }
        } else {
            Service service = repository.getById(serviceDTO.getId());
            Category category = categoryService.findCategoryByCategoryName(categoryName);

            serviceDTO.setName(serviceName);

            service.setId(serviceDTO.getId());
            service.setName(serviceDTO.getName());
            service.setBasePrice(serviceDTO.getBasePrice());
            service.setDescription(serviceDTO.getDescription());
            service.setOptionalDescription(serviceDTO.getOptionalDescription());
            service.setCategory(category);

            List<Long> specialistIds = new ArrayList<>();
            if (serviceDTO.getSpecialistId() != null) {
                specialistIds = Arrays.asList(serviceDTO.getSpecialistId());
            }

            if (serviceDTO.getSpecialistId() != null) {
                List<Specialist> specialists = specialistService.findSpecialistBy(
                        Arrays.asList(serviceDTO.getSpecialistId())
                );

                service.getServiceSpecialist().clear();
                for (Specialist specialist : specialists) {

                    ServiceSpecialist serviceSpecialist = new ServiceSpecialist();
                    serviceSpecialist.setSpecialist(specialist);
                    serviceSpecialist.setService(service);
                    service.getServiceSpecialist().add(serviceSpecialist);
                }
            } else {
                service.getServiceSpecialist().clear();
            }
            service = repository.save(service);

            Long[] ids = specialistIds.toArray(new Long[0]);
            if (ids.length == 0) {
                return new ServiceAbstractDTO(
                        service.getId(),
                        service.getName(),
                        service.getDescription(),
                        service.getBasePrice(),
                        service.getOptionalDescription(),
                        service.getCategory().getCategoryName()
                );
            } else {
                return new ServiceAbstractDTO(
                        service.getId(), ids,
                        service.getName(),
                        service.getDescription(),
                        service.getBasePrice(),
                        service.getOptionalDescription(),
                        service.getCategory().getCategoryName()
                );
            }
        }
    }


    @Override
    public List<Service> fetchAllServices() {
        return repository.findAll();
    }

    @Override
    public ServiceAbstractDTO saveSpecialist(ServiceAbstractDTO serviceAbstractDTO) {

        if (!repository.existsByName(serviceAbstractDTO.getName())) {
            throw new NotFoundException("No service found!");
        }

        Service service = repository.findByName(serviceAbstractDTO.getName());


        List<Long> specialistIds = new ArrayList<>();
        if (serviceAbstractDTO.getSpecialistId() != null) {
            specialistIds = Arrays.asList(serviceAbstractDTO.getSpecialistId());
        }

        if (serviceAbstractDTO.getSpecialistId() != null) {
            List<Specialist> specialists = specialistService.findSpecialistBy(
                    Arrays.asList(serviceAbstractDTO.getSpecialistId())
            );

            for (Specialist specialist : specialists) {

                ServiceSpecialist serviceSpecialist = new ServiceSpecialist();
                serviceSpecialist.setSpecialist(specialist);
                serviceSpecialist.setService(service);
                if (service.getServiceSpecialist().contains(serviceSpecialist)) {
                    continue;
                }
                service.getServiceSpecialist().add(serviceSpecialist);
            }
        }
        Long[] ids = specialistIds.toArray(new Long[0]);
        if (ids.length == 0) {
            return new ServiceAbstractDTO(
                    service.getId(),
                    service.getName(),
                    service.getDescription(),
                    service.getBasePrice(),
                    service.getOptionalDescription(),
                    service.getCategory().getCategoryName()
            );
        } else {
            return new ServiceAbstractDTO(
                    service.getId(), ids,
                    service.getName(),
                    service.getDescription(),
                    service.getBasePrice(),
                    service.getOptionalDescription(),
                    service.getCategory().getCategoryName()
            );
        }
    }

    @Override
    public ServiceAbstractDTO deleteSpecialistByID(ServiceAbstractDTO serviceAbstractDTO) {

        if (!repository.existsByName(serviceAbstractDTO.getName())) {
            throw new NotFoundException("No service found!");
        }

        Service service = repository.findByName(serviceAbstractDTO.getName());

        if (serviceAbstractDTO.getSpecialistId() != null) {
            List<Specialist> specialists = specialistService.findSpecialistBy(
                    Arrays.asList(serviceAbstractDTO.getSpecialistId())
            );

            HashSet<ServiceSpecialist> serviceSpecialists = new HashSet<>();
            for (Specialist specialist : specialists) {
                ServiceSpecialist serviceSpecialist = new ServiceSpecialist();
                serviceSpecialist.setSpecialist(specialist);
                serviceSpecialist.setService(service);
                serviceSpecialists.add(serviceSpecialist);
            }

            service.getServiceSpecialist().removeAll(serviceSpecialists);
            service = repository.save(service);
        }

        List<Long> specialistId = new ArrayList<>();
        for (ServiceSpecialist specialist : service.getServiceSpecialist()) {
            specialistId.add(specialist.getId());
        }

        Long[] ids = specialistId.toArray(new Long[0]);

        return new ServiceAbstractDTO(
                service.getId(), ids,
                service.getName(),
                service.getDescription(),
                service.getBasePrice(),
                service.getOptionalDescription(),
                service.getCategory().getCategoryName()
        );
    }


}
