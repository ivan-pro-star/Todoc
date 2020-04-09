package com.cleanup.todoc.databse;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

@Database(entities = {Project.class, Task.class}, version = 1)
public abstract class TodocDataBase extends RoomDatabase {

    private static TodocDataBase instance;

    public abstract ProjectDao projectDao();
    public abstract TaskDao taskDao();

    public static synchronized TodocDataBase getInstance(Context context){
        if( instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TodocDataBase.class, "todoc_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static  RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private ProjectDao projectDao;

        private PopulateDbAsyncTask(TodocDataBase dataBase) {
            projectDao = dataBase.projectDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            projectDao.insert(new Project("Projet Tartampion", 0xFFEADAD1));
            projectDao.insert(new Project( "Projet Lucidia", 0xFFB4CDBA));
            projectDao.insert( new Project( "Projet Circus", 0xFFA3CED2));
            return null;
        }
    }
}
