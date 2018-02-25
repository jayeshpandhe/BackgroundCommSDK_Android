package com.bgcomm.ui;

import android.app.Activity;

import com.bgcomm.BackgroundCommCallback;

public abstract class AbstractBackgroundCommActivity extends Activity implements BackgroundCommCallback {
    private BackgroundCommAsyncTask mBackgroundCommAsyncTask;

    public void execute() {
        execute(null);
    }

    public void execute(Object requestType) {
        mBackgroundCommAsyncTask = new BackgroundCommAsyncTask(this, requestType);
        mBackgroundCommAsyncTask.execute();
    }

    public boolean isInProgress() {
        return mBackgroundCommAsyncTask != null && mBackgroundCommAsyncTask.isInProgress();
    }
}
