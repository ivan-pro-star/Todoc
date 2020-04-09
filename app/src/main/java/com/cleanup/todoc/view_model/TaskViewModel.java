package com.cleanup.todoc.view_model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repository.TaskRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class TaskViewModel extends ViewModel {

    //  REPOSITORY- EXECUTOR --
    private final TaskRepository taskRepository;
    private final Executor executor;

    //  CONSTRUCTOR --
    public TaskViewModel(TaskRepository taskRepository, Executor executor) {
        this.taskRepository = taskRepository;
        this.executor = executor;
    }

    // METHODES
    public LiveData<List<Task>> getAllTasks(){
        return taskRepository.getAllTasks();
    }

    public void insertTask(Task task){
        executor.execute(()->{ taskRepository.insertTask(task); });
    }

    public void deleteTask(Task task){
        executor.execute(()->{ taskRepository.deleteTask(task); });
    }

}
