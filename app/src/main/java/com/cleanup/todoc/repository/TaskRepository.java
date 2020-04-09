package com.cleanup.todoc.repository;

import android.arch.lifecycle.LiveData;

import com.cleanup.todoc.databse.TaskDao;
import com.cleanup.todoc.model.Task;

import java.util.List;

public class TaskRepository {
    // DAO--
    private final TaskDao taskDao;

    //  CONSTRUCTOR  --
    public TaskRepository(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    // METHODES --
    public LiveData<List<Task>> getAllTasks(){
        return taskDao.getAllTasks();
    }
    public void insertTask(Task task){
        taskDao.insert(task);
    }
    public void deleteTask(Task task){
        taskDao.delete(task);
    }
}
