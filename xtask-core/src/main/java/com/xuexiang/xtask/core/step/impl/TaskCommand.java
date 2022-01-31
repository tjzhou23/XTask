/*
 * Copyright (C) 2022 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.xuexiang.xtask.core.step.impl;

import androidx.annotation.NonNull;

import com.xuexiang.xtask.core.param.ITaskParam;
import com.xuexiang.xtask.core.param.ITaskResult;
import com.xuexiang.xtask.core.param.impl.TaskParam;
import com.xuexiang.xtask.core.step.ITaskStepController;

/**
 * 任务命令，执行简单任务
 *
 * @author xuexiang
 * @since 1/31/22 2:28 AM
 */
public abstract class TaskCommand implements Runnable, ITaskStepController {

    private ITaskStepController mController;

    /**
     * 设置任务步骤执行控制器
     *
     * @param controller 控制器
     * @return this
     */
    public TaskCommand setTaskStepResultController(ITaskStepController controller) {
        mController = controller;
        return this;
    }

    @Override
    public void onTaskSucceed(@NonNull ITaskResult result) {
        if (mController != null) {
            mController.onTaskSucceed(result);
        }
    }

    @Override
    public void onTaskFailed(@NonNull ITaskResult result) {
        if (mController != null) {
            mController.onTaskFailed(result);
        }
    }

    @Override
    public String getName() {
        return mController != null ? mController.getName() : "unknown";
    }

    @NonNull
    @Override
    public ITaskParam getTaskParam() {
        return mController != null ? mController.getTaskParam() : TaskParam.get();
    }

    @Override
    public void recycle() {
        if (mController != null) {
            mController.recycle();
        }
    }
}
