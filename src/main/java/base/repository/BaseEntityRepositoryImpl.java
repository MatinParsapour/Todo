package base.repository;

import base.entity.BaseEntity;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public abstract class BaseEntityRepositoryImpl<E extends BaseEntity<ID>,ID extends Serializable> implements BaseEntityRepository<E,ID> {

    protected final EntityManager entityManagaer;

    public BaseEntityRepositoryImpl(EntityManager entityManager){
        this.entityManagaer = entityManager;
    }

    public abstract Class<E> getEntity();

    @Override
    public E saveOrUpdate(E e) {
        entityManagaer.getTransaction().begin();
        if(e.getId() == null){
            entityManagaer.persist(e);
            entityManagaer.getTransaction().commit();
            return e;
        }else{
            entityManagaer.persist(e);
            entityManagaer.getTransaction().commit();
            return e;
        }
    }

    @Override
    public E findById(ID id) {
        return entityManagaer.find(getEntity(),id);
    }

    @Override
    public List<E> findAll() {
        return entityManagaer.createQuery("FROM " + getEntity().getSimpleName(),getEntity()).getResultList();
    }

    @Override
    public void delete(E e) {
        entityManagaer.remove(e);
    }

    @Override
    public boolean exists(ID id) {
        return entityManagaer.createQuery("FROM " + getEntity().getSimpleName() +
                " WHERE id = :id " ,Long.class).setParameter("id",id).getSingleResult() == 1;

    }

    @Override
    public EntityManager getEntityManager() {
        return entityManagaer;
    }
}
