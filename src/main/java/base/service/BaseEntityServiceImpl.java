package base.service;

import base.entity.BaseEntity;
import base.repository.BaseEntityRepository;

import java.io.Serializable;
import java.util.List;

public class BaseEntityServiceImpl<E extends BaseEntity<ID>,ID extends Serializable, R extends BaseEntityRepository<E,ID>>
        implements BaseEntityService<E,ID> {

    protected final R repository;

    public BaseEntityServiceImpl(R repository){
        this.repository = repository;
    }


    @Override
    public E saveOrUpdate(E e) {
        repository.getEntityManager().getTransaction().begin();
        e  = repository.saveOrUpdate(e);
        repository.getEntityManager().getTransaction().commit();
        return e;
    }

    @Override
    public E findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public List<E> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(E e) {
        repository.getEntityManager().getTransaction().begin();
        repository.delete(e);
        repository.getEntityManager().getTransaction().commit();
    }

    @Override
    public boolean exist(ID id) {
        return repository.exists(id);
    }
}
