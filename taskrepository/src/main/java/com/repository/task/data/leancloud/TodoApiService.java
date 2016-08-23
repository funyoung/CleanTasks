
package com.repository.task.data.leancloud;

import com.repository.task.model.Task;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

public interface TodoApiService {
//    @FormUrlEncoded
    @GET("/1.1/classes/Todo")
    Observable<TasksResponse> getTasks();

    @GET("/1.1/classes/Todo/{id}")
    Observable<Task> getTask(@Path("id") String id);

    @POST("/1.1/classes/Todo")
    Observable<Task> saveTask(@Body Task task);

    @PUT("/1.1/classes/Todo/{id}")
    Observable<Task> completeTask(@Path("id") String id, @Body TaskComplete complete);
}
