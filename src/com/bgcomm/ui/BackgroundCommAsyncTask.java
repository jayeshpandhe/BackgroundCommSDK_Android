package com.bgcomm.ui;

import android.os.AsyncTask;
import android.util.Log;

import com.bgcomm.BackgroundCommCallback;
import com.bgcomm.models.Response;

public class BackgroundCommAsyncTask extends AsyncTask<Void, Void, Response> {
    private static final String TAG = BackgroundCommAsyncTask.class.getSimpleName();
    private BackgroundCommCallback mBackgroundCommCallback;
    private Object mRequestType;
    private boolean inProgress;

    public BackgroundCommAsyncTask(BackgroundCommCallback backgroundCommCallback, Object requestType) {
        mBackgroundCommCallback = backgroundCommCallback;
        mRequestType = requestType;
    }

    @Override
    protected void onPreExecute() {
        inProgress = true;
        super.onPreExecute();
        if (mBackgroundCommCallback != null) {
            mBackgroundCommCallback.onPreExecute(mRequestType);
        }
    }

    @Override
    protected Response doInBackground(Void... voids) {
        Response response = null;
        if (mBackgroundCommCallback != null) {
            try {
                response = mBackgroundCommCallback.executeRequest(mRequestType);
            } catch (Exception e) {
                Log.e(TAG, "Exception while processing request. " + e);
            }
        }
        return response;
    }

    @Override
    protected void onPostExecute(Response response) {
        super.onPostExecute(response);
        if (mBackgroundCommCallback != null) {
            if (response != null && response.isSuccess()) {
                mBackgroundCommCallback.onSuccess(mRequestType, response);
            } else {
                mBackgroundCommCallback.onError(mRequestType, response);
            }
            mBackgroundCommCallback.onPostExecute(mRequestType);
        }
        inProgress = false;
    }

    public boolean isInProgress() {
        return inProgress;
    }
}
