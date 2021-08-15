package repository;

import base.repository.BaseEntityRepository;
import domain.Task;
import domain.User;

import java.util.List;

public interface TaskRepository extends BaseEntityRepository<Task, Long> {

    List<Task> findUserActivities(User user);

    boolean checkTitle(String title, User user);

    Task findActivity(String title, User user);
}
