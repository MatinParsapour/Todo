package util;

import repository.UserRepository;
import repository.impl.UserRepositoryImpl;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.persistence.EntityManager;

public class ApplicationContext {
    private static final UserRepository userRepository;

    private static final UserServiceImpl userServiceImpl;

    private static final Demonstration demonstration;

    private ApplicationContext(){
    }

    static{
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        userRepository = new UserRepositoryImpl(entityManager);
        userServiceImpl = new UserServiceImpl(userRepository);
        demonstration = new Demonstration();
    }
    public static UserServiceImpl getUserServiceImpl(){
        return userServiceImpl;
    }

    public static Demonstration getDemonstration(){return demonstration;}
}
