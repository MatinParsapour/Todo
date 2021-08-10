package repository.impl;

import entity.Tasks;
import entity.User;
import repository.BaseRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository implements BaseRepository<Tasks> {

    private final EntityManagerFactory entityManagerFactory;

    public TaskRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public List<Tasks> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery("FROM Tasks", Tasks.class).getResultList();
    }

    public entity.Tasks findActivity(String activityField) {
        List<Tasks> tasks = findAll();
        Tasks tasks1 = new Tasks();
        for (Tasks task : tasks) {
            if (task.getTitle().equals(activityField) || task.getBody().equals(activityField)) {
                tasks1 = task;
            }
        }
        return tasks1;
    }

    public void addActivity(Tasks tasks) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(tasks);
        entityManager.getTransaction().commit();
    }

    public List<Tasks> findUserActivities(User user) {
        List<Tasks> allTasks = findAll();
        List<Tasks> userTasks = new ArrayList<>();
        for (Tasks allTask : allTasks) {
            if (allTask.getUser().getUsername().equals(user.getUsername())) {
                userTasks.add(allTask);
            }
        }
        return userTasks;
    }

    public boolean isIdCorrect(String title, User user) {
        List<Tasks> tasks = findAll();
        for (Tasks task : tasks) {
            if (task.getTitle().equals(title) && task.getUser().getUsername().equals(user.getUsername())) {
                return true;
            }
        }
        return false;
    }

    public void changeStatus(int id, int status) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Tasks task = entityManager.find(Tasks.class, id);
        if (status == 1) {
            task.setStatus("completed");
        } else {
            task.setStatus("in progress");
        }
        entityManager.merge(task);
        entityManager.getTransaction().commit();
    }

    public void displayActivities(int field, int how, User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Tasks> tasks = new ArrayList<>();
        if (how == 1) {
            if (field == 1) {
                Query query = entityManager.createQuery("FROM Tasks t WHERE t.user.username = :username ORDER BY t.creationDate ASC ");
                query.setParameter("username", user.getUsername());
                tasks = query.getResultList();
            } else if (field == 2) {
                Query query = entityManager.createQuery("FROM Tasks t WHERE t.user.username = :username ORDER BY t.title ASC ");
                query.setParameter("username", user.getUsername());
                tasks = query.getResultList();
            } else if (field == 3) {
                Query query = entityManager.createQuery("FROM Tasks t WHERE t.user.username = :username ORDER BY t.status ASC ");
                query.setParameter("username", user.getUsername());
                tasks = query.getResultList();
            }
        } else if (how == 2) {
            if (field == 1) {
                Query query = entityManager.createQuery("FROM Tasks t WHERE t.user.username = :username ORDER BY t.creationDate DESC ");
                query.setParameter("username", user.getUsername());
                tasks = query.getResultList();
            } else if (field == 2) {
                Query query = entityManager.createQuery("FROM Tasks t WHERE t.user.username = :username ORDER BY t.title DESC ");
                query.setParameter("username", user.getUsername());
                tasks = query.getResultList();
            } else if (field == 3) {
                Query query = entityManager.createQuery("FROM Tasks t WHERE t.user.username = :username ORDER BY t.status DESC ");
                query.setParameter("username", user.getUsername());
                tasks = query.getResultList();
            }
        }
        for (Tasks task : tasks) {
            System.out.println("title : " + task.getTitle()
                    + " content : " + task.getBody()
                    + " date : " + task.getCreationDate()
                    + " status : " + task.getStatus());
        }
    }

    public void updateTitle(Tasks tasks, String title) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Tasks tasks1 = entityManager.find(Tasks.class, tasks.getId());
        entityManager.getTransaction().begin();
        tasks1.setTitle(title);
        entityManager.merge(tasks1);
        entityManager.getTransaction().commit();
    }

    public void updateContent(Tasks tasks, String content) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Tasks tasks1 = entityManager.find(Tasks.class, tasks.getId());
        entityManager.getTransaction().begin();
        tasks1.setBody(content);
        entityManager.merge(tasks1);
        entityManager.getTransaction().commit();
    }

}
