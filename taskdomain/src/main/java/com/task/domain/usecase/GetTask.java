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

package com.task.domain.usecase;

import android.support.annotation.NonNull;

import com.clean.common.usecase.UseCase;
import com.repository.task.model.Task;
import com.repository.task.data.TasksDataSource;
import com.repository.task.data.TasksRepository;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Retrieves a {@link Task} from the {@link TasksRepository}.
 */
public class GetTask extends UseCase<GetTask.RequestValues, GetTask.ResponseValue> {

    private final TasksRepository repository;

    @Inject public GetTask(@NonNull TasksRepository tasksRepository) {
        repository = checkNotNull(tasksRepository, "tasksRepository cannot be null!");
    }

    @Override
    protected void executeUseCase(final RequestValues values) {
        repository.getTask(values.getTaskId(), new TasksDataSource.GetTaskCallback() {
            @Override
            public void onTaskLoaded(Task task) {
                if (task != null) {
                    ResponseValue responseValue = new ResponseValue(task);
                    getUseCaseCallback().onSuccess(responseValue);
                } else {
                    getUseCaseCallback().onError();
                }
            }

            @Override
            public void onDataNotAvailable() {
                getUseCaseCallback().onError();
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {

        private final String taskId;

        public RequestValues(@NonNull String taskId) {
            this.taskId = checkNotNull(taskId, "taskId cannot be null!");
        }

        public String getTaskId() {
            return taskId;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {

        private Task task;

        public ResponseValue(@NonNull Task task) {
            this.task = checkNotNull(task, "task cannot be null!");
        }

        public Task getTask() {
            return task;
        }
    }
}
