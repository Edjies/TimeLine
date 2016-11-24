package com.edjies.timeline.module.project.fragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;

import com.edjies.timeline.R;
import com.edjies.timeline.module.project.bean.ProjectItem;
import com.edjies.timeline.module.project.dao.ProjectDaoInterface;
import com.edjies.timeline.utils.UDate;
import com.edjies.timeline.utils.UNumber;
import com.edjies.timeline.utils.UToast;

import java.util.Calendar;

/**
 * Created by hubble on 2016/11/21 0021.
 */

public class AddProjectItemDialog extends DialogFragment {
    private EditText mEtTitle;
    private EditText mEtEt;
    private ProjectDaoInterface mDao;
    private long mProjectId;
    private AddListener mListener;
    public static AddProjectItemDialog newInstance(long projectId) {
        AddProjectItemDialog frag = new AddProjectItemDialog();
        Bundle args = new Bundle();
        frag.setArguments(args);
        frag.mProjectId = projectId;
        return frag;
    }

    public void setOnAddListener(AddListener listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.project_view_add_project_item, null);
        mEtTitle = (EditText) view.findViewById(R.id.et_title);
        mEtEt = (EditText) view.findViewById(R.id.et_et);
        return new AlertDialog.Builder(getActivity())
                    .setIcon(R.drawable.ic_dialog_add)
                .setTitle("添加任务")
                .setView(view)
                .setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // do sth
                                String title = mEtTitle.getText().toString();
                                int et = UNumber.getInt(mEtEt.getText().toString(), 0);
                                if(!"".equals(title.trim())) {
                                    ProjectItem project = new ProjectItem(mProjectId, title, UDate.getFormatDate(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss"), et);

                                    ProjectDaoInterface.getInstance().addProjectItem(project);
                                    if(mListener != null) {
                                        mListener.onAdd(project);
                                    }
                                }else {
                                    if("".equals(title)) UToast.toastError(getActivity(), "任务描述不能为空");
                                }
                            }
                        }
                )
                .setNegativeButton("取消",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                            }
                        }
                )
                .create();
    }

    private void selectDuration() {
        final int[] durations = {5, 10, 15, 20, };
        // 选择时长
        Calendar time = Calendar.getInstance();
        TimePickerDialog td = new TimePickerDialog(getActivity(), R.style.BaseDateTimeDialogTheme, null, time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), false);
        td.show();

    }


    public interface AddListener {
        void onAdd(ProjectItem item);
    }


}
