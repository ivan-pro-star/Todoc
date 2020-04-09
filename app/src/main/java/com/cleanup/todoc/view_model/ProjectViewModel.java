package com.cleanup.todoc.view_model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.repository.ProjectRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class ProjectViewModel extends ViewModel {

    //  REPOSITORY - EXECUTOR --
    private final ProjectRepository projectRepository;
    private final Executor executor;

    //  CONSTRUCTOR --
    public ProjectViewModel(ProjectRepository projectRepository, Executor executor) {
        this.projectRepository = projectRepository;
        this.executor = executor;
    }

    //  METHODES --
    public LiveData<List<Project>> getAllProjects(){
        return projectRepository.getAllProjects();
    }

    public void insertProject(Project project){
        executor.execute(()->{ projectRepository.insertProject(project); });
    }

    //public void deleteTask(Task task){
      //  executor.execute(()->{ projectRepository.deleteProject(task); });
    //}
}
