package repository;

import entity.Tasks;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class TaskRepository {

    private EntityManagerFactory entityManagerFactory;

    public TaskRepository(EntityManagerFactory entityManagerFactory){
        this.entityManagerFactory = entityManagerFactory;
    }

    public void addActivity(Tasks tasks){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(tasks);
        entityManager.getTransaction().commit();
    }
}
