
package com.repository.task.data.leancloud;

import retrofit2.http.GET;
import rx.Observable;

public interface TodoApiService {
//    @FormUrlEncoded
    @GET("/1.1/classes/Todo")
    Observable<TaskResponse> getTasks();

}
