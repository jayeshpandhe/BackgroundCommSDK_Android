package com.bgcomm;

import com.bgcomm.models.Response;

public interface BackgroundCommCallback {
    void onPreExecute(Object requestType);
    Response executeRequest(Object requestType) throws Exception;
    void onSuccess(Object requestType, Response response);
    void onError(Object requestType, Response response);
    void onPostExecute(Object requestType);
}
