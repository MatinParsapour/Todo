package base.service;

import base.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

public interface BaseEntityService<E extends BaseEntity<ID>, ID extends Serializable> {

    E saveOrUpdate(E e);

    E findById(ID id);

    List<E> findAll();

    void delete(E e);

    boolean exist(ID id);

}
