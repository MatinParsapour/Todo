package repository.impl;

import base.repository.BaseEntityRepositoryImpl;
import domain.Task;
import repository.TaskRepository;

import javax.persistence.EntityManager;

public class TaskRepositoryImpl extends BaseEntityRepositoryImpl<Task,Long> implements TaskRepository {
    public TaskRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Task> getEntity() {
        return Task.class;
    }
}
