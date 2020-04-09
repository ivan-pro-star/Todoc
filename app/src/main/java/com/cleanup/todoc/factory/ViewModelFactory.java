package com.cleanup.todoc.factory;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.cleanup.todoc.repository.ProjectRepository;
import com.cleanup.todoc.repository.TaskRepository;
import com.cleanup.todoc.view_model.ProjectViewModel;
import com.cleanup.todoc.view_model.TaskViewModel;

import java.util.concurrent.Executor;

public class ViewModelFactory implements ViewModelProvider.Factory{
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final Executor executor;

    public ViewModelFactory(TaskRepository taskRepository, ProjectRepository projectRepository, Executor executor) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.executor = executor;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {

        if(modelClass.isAssignableFrom(TaskViewModel.class)){
            return (T) new TaskViewModel(taskRepository, executor);
        }
         else if(modelClass.isAssignableFrom(ProjectViewModel.class)){
            return (T) new ProjectViewModel(projectRepository, executor);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
