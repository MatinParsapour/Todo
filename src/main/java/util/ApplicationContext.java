package util;

import repository.TaskRepository;
import repository.UserRepository;
import repository.impl.TaskRepositoryImpl;
import repository.impl.UserRepositoryImpl;
import service.impl.TaskServiceImpl;
import service.impl.UserServiceImpl;

import javax.persistence.EntityManager;

public class ApplicationContext {
    private static final UserRepository userRepository;

    private static final UserServiceImpl userServiceImpl;

    private static final TaskRepository taskRepository;

    private static final TaskServiceImpl taskServiceImpl;

    private static final Demonstration demonstration;

    private ApplicationContext(){
    }

    static{
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        userRepository = new UserRepositoryImpl(entityManager);
        userServiceImpl = new UserServiceImpl(userRepository);
        taskRepository = new TaskRepositoryImpl(entityManager);
        taskServiceImpl = new TaskServiceImpl(taskRepository);
        demonstration = new Demonstration();
    }
    public static UserServiceImpl getUserServiceImpl(){
        return userServiceImpl;
    }

    public static Demonstration getDemonstration(){return demonstration;}

    public static TaskServiceImpl getTaskServiceImpl(){return taskServiceImpl;}
}
