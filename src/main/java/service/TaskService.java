package service;

import base.service.BaseEntityService;
import domain.Task;
import domain.User;

public interface TaskService extends BaseEntityService<Task, Long> {

    void addActivity(User user);

    void changeTitle(User user);

    void changeContent(User user);

    void changeStatus(User user);

    void seeActivities(User user);

    void removeUserTasks(User user);

    void removeUserTask(User user);
}
