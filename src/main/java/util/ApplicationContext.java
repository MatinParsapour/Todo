package util;

import repository.UserRepository;
import repository.impl.UserRepositoryImpl;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.persistence.EntityManager;

public class ApplicationContext {
    private static final UserRepository userRepository;

    private static final UserServiceImpl userServiceImpl;

    private ApplicationContext(){
    }

    static{
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        userRepository = new UserRepositoryImpl(entityManager);
        userServiceImpl = new UserServiceImpl(userRepository);
    }
    public static UserServiceImpl getUserServiceImpl(){
        return userServiceImpl;
    }
}
