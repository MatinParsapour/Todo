package service.impl;

import base.service.BaseEntityServiceImpl;
import domain.Task;
import repository.TaskRepository;
import service.TaskService;

public class TaskServiceImpl extends BaseEntityServiceImpl<Task,Long, TaskRepository> implements TaskService {
    public TaskServiceImpl(TaskRepository repository) {
        super(repository);
    }
}
