package repository.impl;

import base.repository.BaseEntityRepositoryImpl;
import domain.User;
import repository.UserRepository;

import javax.persistence.EntityManager;
import java.util.List;

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
        List<User> users = findAll();
         for (User user : users) {
            if(user.getUsername().equals(username)
                    && user.getPassword().equals(password)){
                return true;
            }
        }
         return false;
    }

    @Override
    public boolean exists(String username) {
        List<User> users = findAll();
        for(User user : users){
            if(user.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Long findId(String username) {
        List<User> users = findAll();
        Long id = 0L;
        for(User user : users){
            if(user.getUsername().equals(username)){
                id = user.getId();
            }
        }
        return id;
    }
}
