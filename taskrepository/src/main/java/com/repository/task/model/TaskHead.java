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

package com.repository.task.model;

import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;

/**
 * Immutable model class for a Task.
 */
public final class TaskHead {

    @SerializedName("objectId")
    private String objectId;

    @SerializedName("createdAt")
    private String createdAt;

    public String getObjectId() {
        return objectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskHead task = (TaskHead) o;
        return Objects.equal(objectId, task.objectId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(objectId);
    }
}
