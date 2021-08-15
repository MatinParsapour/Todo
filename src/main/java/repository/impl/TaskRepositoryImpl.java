package repository.impl;

import base.repository.BaseEntityRepositoryImpl;
import domain.Task;
import domain.User;
import repository.TaskRepository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class TaskRepositoryImpl extends BaseEntityRepositoryImpl<Task, Long> implements TaskRepository {
    public TaskRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Task> getEntity() {
        return Task.class;
    }

    @Override
    public List<Task> findUserActivities(User user) {
        List<Task> tasks = findAll();
        List<Task> userTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getUser().equals(user)) {
                userTasks.add(task);
            }
        }
        return userTasks;
    }

    @Override
    public boolean checkTitle(String title, User user) {
        List<Task> tasks = findAll();
        for (Task task : tasks) {
            if (task.getTitle().equals(title) && task.getUser().equals(user)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Task findActivity(String title, User user) {
        List<Task> userTasks = findUserActivities(user);
        Task userTask = new Task();
        for (Task task : userTasks) {
            if (task.getTitle().equals(title)) {
                userTask = task;
            }
        }
        return userTask;
    }

    @Override
    public List<Task> sortActivities(int field, int how, User user) {
        List<Task> tasks = new ArrayList<>();
        if(how == 1){
            if(field == 1){
                tasks = entityManagaer.createQuery("FROM Task t WHERE t.user = :user " +
                        "ORDER BY t.status DESC ").setParameter("user",user).getResultList();
            }else if(field == 2){
                tasks = entityManagaer.createQuery("FROM Task t WHERE t.user = :user " +
                        "ORDER BY t.title DESC ").setParameter("user",user).getResultList();

            }else{
                tasks = entityManagaer.createQuery("FROM Task t WHERE t.user = :user " +
                        "ORDER BY t.creationDate DESC ").setParameter("user",user).getResultList();
            }
        }else if(how == 2){
            if(field == 1){
                tasks = entityManagaer.createQuery("FROM Task t WHERE t.user = :user " +
                        "ORDER BY t.status ASC ").setParameter("user",user).getResultList();
            }else if(field == 2){
                tasks = entityManagaer.createQuery("FROM Task t WHERE t.user = :user " +
                        "ORDER BY t.title ASC ").setParameter("user",user).getResultList();

            }else{
                tasks = entityManagaer.createQuery("FROM Task t WHERE t.user = :user " +
                        "ORDER BY t.creationDate ASC ").setParameter("user",user).getResultList();
            }
        }
        return tasks;
    }
}
