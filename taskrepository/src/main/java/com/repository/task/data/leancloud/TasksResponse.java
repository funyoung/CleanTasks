package com.repository.task.data.leancloud;

import com.google.gson.annotations.SerializedName;
import com.repository.task.model.Task;

import java.util.List;

/**
 * Created by yangfeng on 16-8-12.
 * {"results":[{"completed":false,"description":"trewq","title":"ewq","createdAt":"2016-07-20T06:19:12.324Z","updatedAt":"2016-07-21T05:24:42.029Z","objectId":"578f17e08a51a2004149a2e9"}]}
 */
public class TasksResponse {
    @SerializedName("results")
    List<Task> results;

    public List<Task> getItems() {
        return results;
    }
}
