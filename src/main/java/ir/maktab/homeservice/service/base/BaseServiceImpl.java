package ir.maktab.homeservice.service.base;
/*
 * created by Amir mahdi seydi 5/7/2022 11:29 AM
 */

import ir.maktab.homeservice.model.base.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseServiceImpl<E extends BaseEntity<ID>, ID extends Serializable, R extends JpaRepository<E, ID>>
        implements BaseService <E, ID> {


    protected final R repository;

    public BaseServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public E save(E e) {
        return repository.save(e);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<E> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public List<E> findAll() {
        return repository.findAll();
    }
}
