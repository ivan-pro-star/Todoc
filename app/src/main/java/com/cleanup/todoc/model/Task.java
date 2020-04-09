package com.cleanup.todoc.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Comparator;
import java.util.List;

/**
 * <p>Model for the tasks of the application.</p>
 *
 * @author Gaëtan HERFRAY
 */
@Entity(tableName = "table_task")
public class Task {
    /**
     * The unique identifier of the task
     */
    @PrimaryKey(autoGenerate = true)
    private int id;

    /**
     * The unique identifier of the project associated to the task
     */
    private int projectId;

    /**
     * The name of the task
     */
    // Suppress warning because setName is called in constructor
    @SuppressWarnings("NullableProblems")
    @NonNull
    private String name;

    public long getCreationTimestamp() {
        return creationTimestamp;
    }

    /**
     * The timestamp when the task has been created
     */
    private long creationTimestamp;

    /**
     * Instantiates a new Task.
     *
     * @param //id                the unique identifier of the task to set
     * @param projectId         the unique identifier of the project associated to the task to set
     * @param name              the name of the task to set
     * @param creationTimestamp the timestamp when the task has been created to set
     */
    public Task( int projectId, @NonNull String name, long creationTimestamp) {
        //this.setId(id);
        this.setProjectId(projectId);
        this.setName(name);
        this.setCreationTimestamp(creationTimestamp);
    }

    /**
     * Returns the unique identifier of the task.
     *
     * @return the unique identifier of the task
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the task.
     *
     * @param id the unique idenifier of the task to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the unique identifier of the project associated to the task.
     *
     * @param projectId the unique identifier of the project associated to the task to set
     */
    private void setProjectId(int projectId) {
        this.projectId = projectId;
    }


    public int getProjectId() {
        return this.projectId;
    }


    /**
     * Returns the project associated to the task.
     *
     * @return the project associated to the task
     */

 @Nullable
    public Project getProject(List<Project> projects) {
     Log.d("taskProjectId", String.valueOf(projectId));
     Log.d("projectsSize", String.valueOf(projects.size()));
     for (Project project : projects) {
            if (project.id == projectId) {
                return project;
            }
            Log.d("projectId", String.valueOf(project.id));
        }
        return null;
    }

    /**
     * Returns the name of the task.
     *
     * @return the name of the task
     */
    @NonNull
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the task.
     *
     * @param name the name of the task to set
     */
    private void setName(@NonNull String name) {
        this.name = name;
    }

    /**
     * Sets the timestamp when the task has been created.
     *
     * @param creationTimestamp the timestamp when the task has been created to set
     */
    private void setCreationTimestamp(long creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    /**
     * Comparator to sort task from A to Z
     */
    public static class TaskAZComparator implements Comparator<Task> {
        @Override
        public int compare(Task left, Task right) {
            return left.name.compareTo(right.name);
        }
    }

    /**
     * Comparator to sort task from Z to A
     */
    public static class TaskZAComparator implements Comparator<Task> {
        @Override
        public int compare(Task left, Task right) {
            return right.name.compareTo(left.name);
        }
    }

    /**
     * Comparator to sort task from last created to first created
     */
    public static class TaskRecentComparator implements Comparator<Task> {
        @Override
        public int compare(Task left, Task right) {
            return (int) (right.creationTimestamp - left.creationTimestamp);
        }
    }

    /**
     * Comparator to sort task from first created to last created
     */
    public static class TaskOldComparator implements Comparator<Task> {
        @Override
        public int compare(Task left, Task right) {
            return (int) (left.creationTimestamp - right.creationTimestamp);
        }
    }
}
