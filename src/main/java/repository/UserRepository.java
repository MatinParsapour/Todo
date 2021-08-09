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
}
