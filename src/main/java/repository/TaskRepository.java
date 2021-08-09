package repository;

import entity.Tasks;
import entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    private EntityManagerFactory entityManagerFactory;

    public TaskRepository(EntityManagerFactory entityManagerFactory){
        this.entityManagerFactory = entityManagerFactory;
    }

    public List<Tasks> findAllActivities(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Tasks> tasks = entityManager.createQuery("FROM Tasks",Tasks.class).getResultList();
        return tasks;
    }
    public void addActivity(Tasks tasks){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(tasks);
        entityManager.getTransaction().commit();
    }
    public List<Tasks> findUserActivities(User user){
        List<Tasks> allTasks = findAllActivities();
        List<Tasks> userTasks = new ArrayList<>();
        for(int counter = 0 ; counter < allTasks.size(); counter++){
            if(allTasks.get(counter).getUser().getUsername().equals(user.getUsername())){
                userTasks.add(allTasks.get(counter));
            }
        }
        return userTasks;
    }
    public boolean isIdCorrect(String title, User user){
        List<Tasks> tasks = findAllActivities();
        for(int counter = 0 ; counter < tasks.size() ; counter++){
            if(tasks.get(counter).getTitle().equals(title) && tasks.get(counter).getUser().getUsername().equals(user.getUsername())){
                return true;
            }
        }
        return false;
    }
    public void changeStatus(int id, int status){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Tasks task = entityManager.find(Tasks.class,id);
        if(status == 1){
            task.setStatus("completed");
        }else{
            task.setStatus("in progress");
        }
        entityManager.merge(task);
        entityManager.getTransaction().commit();
    }
}
