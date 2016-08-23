/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.repository.task.data.leancloud;

import android.support.annotation.NonNull;

import com.clean.common.remote.CommonApiManager;
import com.repository.task.data.TasksDataSource;
import com.repository.task.model.Task;
import com.repository.task.model.TaskHead;

import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import rx.Observer;
import rx.Subscription;

/**
 * Implementation of the data source that adds a latency simulating network.
 */
@Singleton
public class TasksLeanDataSource extends CommonApiManager<TodoApiService> implements TasksDataSource {
    private static final String BASE_URL = "https://us-api.leancloud.cn";

    private Subscription getTasksSubscription;
    private Subscription getTaskDetailSubscription;
    private Subscription saveTaskSubscription;

    public TasksLeanDataSource() {
        super(BASE_URL, TodoApiService.class);
    }

    protected OkHttpClient getHttpClient() {
        // Log信息
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // 公私密匙
        TodoInterceptor signingInterceptor = new TodoInterceptor();

        // OkHttp3.0的使用方式
        return new OkHttpClient.Builder()
                .addInterceptor(signingInterceptor)
                .addInterceptor(loggingInterceptor)
                .build();
    }

    private static void addTask(String title, String description) {
        Task newTask = new Task(title, description);
//        TASKS_SERVICE_DATA.put(newTask.getObjectId(), newTask);
    }

    /**
     * Note: {@link LoadTasksCallback#onDataNotAvailable()} is never fired. In a real remote data
     * source implementation, this would be fired if the server can't be contacted or the server
     * returns an error.
     */
    @Override
    public void getTasks(final @NonNull LoadTasksCallback callback) {
        // Simulate network by delaying the execution.
//        Handler handler = new Handler(Looper.getMainLooper());
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                callback.onTasksLoaded(Lists.newArrayList(TASKS_SERVICE_DATA.values()));
//            }
//        }, SERVICE_LATENCY_IN_MILLIS);

        unsubscribe(getTasksSubscription);

        getTasksSubscription = fork(getService().getTasks()).subscribe(new Observer<TaskResponse>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                callback.onDataNotAvailable();
            }

            @Override
            public void onNext(TaskResponse taskResponse) {
                callback.onTasksLoaded(taskResponse.getItems());
            }
        });
    }

    /**
     * Note: {@link GetTaskCallback#onDataNotAvailable()} is never fired. In a real remote data
     * source implementation, this would be fired if the server can't be contacted or the server
     * returns an error.
     */
    @Override
    public void getTask(@NonNull String taskId, final @NonNull GetTaskCallback callback) {
//        final Task task = TASKS_SERVICE_DATA.get(taskId);
//
//        // Simulate network by delaying the execution.
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                callback.onTaskLoaded(task);
//            }
//        }, SERVICE_LATENCY_IN_MILLIS);

        unsubscribe(getTaskDetailSubscription);

        getTaskDetailSubscription = fork(getService().getTask(taskId)).subscribe(new Observer<Task>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                callback.onDataNotAvailable();
            }

            @Override
            public void onNext(Task task) {
                callback.onTaskLoaded(task);
            }
        });
    }

    @Override
    public void saveTask(@NonNull Task task) {
//        TASKS_SERVICE_DATA.put(task.getObjectId(), task);

        unsubscribe(saveTaskSubscription);

        saveTaskSubscription = fork(getService().saveTask(task)).subscribe(new Observer<TaskHead>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(TaskHead task) {
                String id = task.getObjectId();
                String test = id;
            }
        });
    }

    @Override
    public void completeTask(@NonNull Task task) {
        Task completedTask = new Task(task.getTitle(), task.getDescription(), task.getObjectId(), true);
//        TASKS_SERVICE_DATA.put(task.getObjectId(), completedTask);
    }

    @Override
    public void completeTask(@NonNull String taskId) {
        // Not required for the remote data source because the {@link TasksRepository} handles
        // converting from a {@code taskId} to a {@link task} using its cached data.
    }

    @Override
    public void activateTask(@NonNull Task task) {
        Task activeTask = new Task(task.getTitle(), task.getDescription(), task.getObjectId());
//        TASKS_SERVICE_DATA.put(task.getObjectId(), activeTask);
    }

    @Override
    public void activateTask(@NonNull String taskId) {
        // Not required for the remote data source because the {@link TasksRepository} handles
        // converting from a {@code taskId} to a {@link task} using its cached data.
    }

    @Override
    public void clearCompletedTasks() {
//        Iterator<Map.Entry<String, Task>> it = TASKS_SERVICE_DATA.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry<String, Task> entry = it.next();
//            if (entry.getValue().isCompleted()) {
//                it.remove();
//            }
//        }
    }

    @Override
    public void refreshTasks() {
        // Not required because the {@link TasksRepository} handles the logic of refreshing the
        // tasks from all the available data sources.
    }

    @Override
    public void deleteAllTasks() {
//        TASKS_SERVICE_DATA.clear();
    }

    @Override
    public void deleteTask(@NonNull String taskId) {
//        TASKS_SERVICE_DATA.remove(taskId);
    }
}
