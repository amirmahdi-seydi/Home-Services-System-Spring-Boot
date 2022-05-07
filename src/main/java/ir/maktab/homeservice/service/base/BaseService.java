package ir.maktab.homeservice.service.base;
/*
 * created by Amir mahdi seydi 5/7/2022 11:28 AM
 */

import ir.maktab.homeservice.model.base.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseService<E extends BaseEntity<ID>, ID extends Serializable> {

    E save(E e);

    void deleteById(ID id);

    Optional<E> findById(ID id);

    List<E> findAll();
}
