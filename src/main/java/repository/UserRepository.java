package repository;

import entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class UserRepository {

    private EntityManagerFactory entityManagerFactory;

    public UserRepository(EntityManagerFactory entityManagerFactory){
        this.entityManagerFactory = entityManagerFactory;
    }

    public List<User> findAll(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<User> users = entityManager.createQuery("FROM User",User.class).getResultList();
        return users;
    }
    public entity.User findUser(String username){
        List<User>  users = findAll();
        User user = new User();
        for(int counter = 0 ; counter< users.size() ; counter++){
            if(users.get(counter).getUsername().equals(username)){
                user = users.get(counter);
            }
        }
        return user;
    }
    public void updatePassword(String password, User user){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        User user1 = entityManager.find(User.class,user.getId());
        user1.setPassword(password);
        entityManager.merge(user1);
        entityManager.getTransaction().commit();
        System.out.println("Your password successfully changed");
    }
    public void updateUsername(String username, User user){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        User user1 = entityManager.find(User.class,user.getId());
        user1.setUsername(username);
        entityManager.merge(user1);
        entityManager.getTransaction().commit();
        System.out.println("Your username successfully changed");
    }
}
