package repository.impl;

import base.repository.BaseEntityRepositoryImpl;
import base.service.BaseEntityServiceImpl;
import domain.User;
import repository.UserRepository;

import javax.persistence.EntityManager;

public class UserRepositoryImpl extends BaseEntityRepositoryImpl<User,Long> implements UserRepository {
    public UserRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<User> getEntity() {
        return User.class;
    }
}
