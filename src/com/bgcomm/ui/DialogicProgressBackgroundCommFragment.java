package com.bgcomm.ui;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;

import com.bgcomm.BackgroundCommCallback;
import com.bgcomm.sdk.R;
import com.bgcomm.utils.AppUtils;

public abstract class DialogicProgressBackgroundCommFragment extends Fragment implements BackgroundCommCallback {
    private BackgroundCommAsyncTask mBackgroundCommAsyncTask;
    private ProgressDialog mProgressDialog;
    private AlertDialog mAlertDialog;

    public void execute() {
        execute(null);
    }

    public void execute(Object requestType) {
        mBackgroundCommAsyncTask = new BackgroundCommAsyncTask(this, requestType);
        mBackgroundCommAsyncTask.execute();
    }

    @Override
    public void onPreExecute(Object requestType) {
        String title = AppUtils.getApplicationName(getActivity());
        String msg = getString(R.string.bg_comm_generic_progress_msg);
        showProgressDialog(requestType, title, msg);
    }

    @Override
    public void onPostExecute(Object requestType) {
        dismissProgressDialog(requestType);
    }

    protected void showProgressDialog(Object requestType, String title, String msg) {
        if (mProgressDialog == null)
            mProgressDialog = ProgressDialog.show(getActivity(), title, msg);
        if (!mProgressDialog.isShowing())
            mProgressDialog.show();
    }

    protected void dismissProgressDialog(Object requestType) {
        if(mProgressDialog != null)
            mProgressDialog.dismiss();
    }

    protected void showAlertDialog(String msg, DialogInterface.OnClickListener onClickListener) {
        showAlertDialog(AppUtils.getApplicationName(getActivity()), msg, onClickListener);
    }

    protected void showAlertDialog(String title, String msg, DialogInterface.OnClickListener onClickListener) {
        if(mAlertDialog == null)
            mAlertDialog = new AlertDialog.Builder(getActivity()).create();
        if(!mAlertDialog.isShowing()) {
            mAlertDialog.setTitle(title);
            mAlertDialog.setMessage(msg);
            mAlertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, getString(R.string.alert_dialog_neutral_btn_text), onClickListener);
            mAlertDialog.show();
        }
    }
}
