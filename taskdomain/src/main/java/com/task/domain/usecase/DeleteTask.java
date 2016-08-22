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
import com.repository.task.data.TasksRepository;
import com.repository.task.model.Task;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Deletes a {@link Task} from the {@link TasksRepository}.
 */
public class DeleteTask extends UseCase<DeleteTask.RequestValues, UseCase.EmptyResponseValue> {

    private final TasksRepository repository;

    @Inject public DeleteTask(@NonNull TasksRepository tasksRepository) {
        repository = checkNotNull(tasksRepository, "tasksRepository cannot be null!");
    }

    @Override
    protected void executeUseCase(final RequestValues values) {
        repository.deleteTask(values.getTaskId());
        getUseCaseCallback().onSuccess(emptyResponseValue());
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
}