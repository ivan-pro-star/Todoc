package com.cleanup.todoc.repository;

import android.arch.lifecycle.LiveData;

import com.cleanup.todoc.databse.ProjectDao;
import com.cleanup.todoc.model.Project;

import java.util.List;

public class ProjectRepository {
    private final ProjectDao projectDao;

    public ProjectRepository(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }
    public LiveData<List<Project>> getAllProjects(){
        return projectDao.getAllProjects();
    }
    public void insertProject(Project project){
        projectDao.insert(project);
    }
    //public void deleteProject(Project project){
      //  projectDao.delete(project);
    //}
}
