package com.cleanup.todoc.databse;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.cleanup.todoc.model.Project;

import java.util.List;

// NE RENVOIE PAS DE LIVE DATA, données directement manipulés car les projets ne vont pas évolués
@Dao
public interface ProjectDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Project project);
   // @Delete
    //void delete(Project project);

    @Query("SELECT * from table_project")
    LiveData<List<Project>> getAllProjects();

}
