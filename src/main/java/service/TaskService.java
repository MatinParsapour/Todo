package service;

import base.service.BaseEntityService;
import domain.Task;
import domain.User;

import java.util.List;

public interface TaskService extends BaseEntityService<Task, Long> {

    void addActivity(User user);

    void changeTitle(User user);

    void changeContent(User user);

    void changeStatus(User user);

    List seeActivities(User user);

    void removeUserTasks(User user);

    void removeUserTask(User user);

    void removeCompletedTasks(User user);

    void userActivities(User user);
}
