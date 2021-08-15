package base.repository;

import base.entity.BaseEntity;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public interface BaseEntityRepository<E extends BaseEntity<ID>, ID extends Serializable> {

    E saveOrUpdate(E e);

    E findById(ID id);

    List<E> findAll();

    void delete(E e);

    boolean exists(ID id);

    EntityManager getEntityManager();
}
