package service.impl;

import base.repository.BaseEntityRepository;
import base.service.BaseEntityServiceImpl;
import domain.User;
import repository.UserRepository;
import service.UserService;

public class UserServiceImpl extends BaseEntityServiceImpl<User,Long, UserRepository> implements UserService {

    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }
}
