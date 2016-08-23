package com.repository.task.data.leancloud;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yangfeng on 16-8-23.
 */
public class TaskComplete {
    @SerializedName("completed")
    private boolean completed;

    public TaskComplete(boolean complete) {
        this.completed = complete;
    }
}
