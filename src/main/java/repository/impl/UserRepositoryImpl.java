package repository.impl;

import base.repository.BaseEntityRepositoryImpl;
import base.service.BaseEntityServiceImpl;
import domain.User;
import repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class UserRepositoryImpl extends BaseEntityRepositoryImpl<User,Long> implements UserRepository {
    public UserRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<User> getEntity() {
        return User.class;
    }

    @Override
    public boolean checkUser(String username, String password) {
        try{
            return (boolean) entityManagaer.createQuery("FROM " + getEntity().getSimpleName() +
                    " WHERE username = :username " +
                    "and password = :password")
                    .setParameter("username",username).setParameter("password",password).getSingleResult();
        }catch (NoResultException exception){
            return false;
        }
    }
}
