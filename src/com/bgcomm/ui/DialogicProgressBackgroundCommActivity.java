package com.bgcomm.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.pm.ApplicationInfo;

import com.bgcomm.models.Response;
import com.bgcomm.sdk.R;
import com.bgcomm.utils.AppUtils;

public class DialogicProgressBackgroundCommActivity extends AbstractBackgroundCommActivity {
    private ProgressDialog mProgressDialog;
    private AlertDialog mAlertDialog;

    @Override
    public void onPreExecute(Object requestType) {
        String title = AppUtils.getApplicationName(getApplicationContext());
        String msg = getString(R.string.bg_comm_generic_progress_msg);
        showProgressDialog(requestType, title, msg);
    }

    protected void showProgressDialog(Object requestType, String title, String msg) {
        if(mProgressDialog == null)
            mProgressDialog = ProgressDialog.show(this, title, msg);
        if(!mProgressDialog.isShowing())
            mProgressDialog.show();
    }

    protected void dismissProgressDialog(Object requestType) {
        if(mProgressDialog != null)
            mProgressDialog.dismiss();
    }

    @Override
    public Response executeRequest(Object requestType) throws Exception {
        return null;
    }

    @Override
    public void onSuccess(Object requestType, Response response) {

    }

    @Override
    public void onError(Object requestType, Response response) {

    }

    @Override
    public void onPostExecute(Object requestType) {
        dismissProgressDialog(requestType);
    }
}
