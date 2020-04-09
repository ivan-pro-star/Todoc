package com.cleanup.todoc.DI;

import android.content.Context;

import com.cleanup.todoc.databse.TodocDataBase;
import com.cleanup.todoc.factory.ViewModelFactory;
import com.cleanup.todoc.repository.ProjectRepository;
import com.cleanup.todoc.repository.TaskRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Injection {
    public static TaskRepository getTaskRepository(Context context){
        TodocDataBase db = TodocDataBase.getInstance(context);
        return new TaskRepository(db.taskDao());
    }
    public static ProjectRepository getProjectRepository(Context context){
        TodocDataBase db = TodocDataBase.getInstance(context);
        return new ProjectRepository(db.projectDao());
    }

    public static Executor getExecutor(){return Executors.newSingleThreadExecutor();
    }

    public static ViewModelFactory getViewModelFactory(Context context){
        return new ViewModelFactory(getTaskRepository(context), getProjectRepository(context), getExecutor());
    }
}
