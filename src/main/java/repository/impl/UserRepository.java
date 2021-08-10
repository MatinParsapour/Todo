package repository.impl;

import entity.User;
import repository.BaseRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class UserRepository implements BaseRepository<User> {

    private final EntityManagerFactory entityManagerFactory;

    public UserRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public List<User> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    public entity.User findUser(String username) {
        List<User> users = findAll();
        User user = new User();
        for (User value : users) {
            if (value.getUsername().equals(username)) {
                user = value;
            }
        }
        return user;
    }

    public void updatePassword(String password, User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        User user1 = entityManager.find(User.class, user.getId());
        user1.setPassword(password);
        entityManager.merge(user1);
        entityManager.getTransaction().commit();
        System.out.println("Your password successfully changed");
    }

    public void updateUsername(String username, User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        User user1 = entityManager.find(User.class, user.getId());
        user1.setUsername(username);
        entityManager.merge(user1);
        entityManager.getTransaction().commit();
        System.out.println("Your username successfully changed");
    }
}
