package ir.maktab.homeservice.service.impl;
/*
 * created by Amir mahdi seydi 5/7/2022 12:14 PM
 */

import ir.maktab.homeservice.exception.AlreadyExistException;
import ir.maktab.homeservice.model.SubService;
import ir.maktab.homeservice.repository.SubServiceRepository;
import ir.maktab.homeservice.service.SubServiceService;
import ir.maktab.homeservice.service.base.BaseServiceImpl;
import ir.maktab.homeservice.service.dto.SubServiceDTO;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SubServiceServiceImpl extends BaseServiceImpl<SubService, Long, SubServiceRepository>
        implements SubServiceService {

    public SubServiceServiceImpl(SubServiceRepository repository) {
        super(repository);
    }

    @Override
    public SubService save(SubService subService) {
        String subServiceName = subService.getName().trim().replaceAll("\\s+"," ").toUpperCase();

        if (repository.existsByName(subServiceName)) {
            throw new AlreadyExistException("This sub service already define!");
        }

        subService.setName(subServiceName);

        return super.save(subService);
    }

    @Override
    public SubService findSubServiceByName(String name) {
        return repository.findSubServiceByName(name);
    }

    @Override
    public Boolean existBySubServiceName(String name) {
        return repository.existsByName(name);
    }

    @Override
    public List<SubServiceDTO> fetchAllSubServiceNames() {
        return repository.fetchAllSubServiceNames();
    }
}
