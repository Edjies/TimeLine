package com.edjies.timeline.module.project.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;

import com.edjies.timeline.R;
import com.edjies.timeline.module.project.bean.Project;
import com.edjies.timeline.module.project.dao.ProjectDaoInterface;
import com.edjies.timeline.utils.UDate;
import com.edjies.timeline.utils.UToast;

import java.util.Calendar;

/**
 *
 * Created by hubble on 2016/11/21 0021.
 */

public class AddProjectDialog extends DialogFragment {
    private EditText mEtTitle;
    private AddListener mListener;
    public static AddProjectDialog newInstance() {
        AddProjectDialog frag = new AddProjectDialog();
        Bundle args = new Bundle();
        frag.setArguments(args);
        return frag;
    }

    public void setOnAddListener(AddListener listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.project_view_add_project, null);
        mEtTitle = (EditText) view.findViewById(R.id.et_title);
        return new AlertDialog.Builder(getActivity())
                    .setIcon(R.drawable.ic_dialog_add)
                .setTitle("添加项目")
                .setView(view)
                .setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // do sth
                                String title = mEtTitle.getText().toString();
                                if(!"".equals(title.trim())) {
                                    Project project = new Project(title, UDate.getFormatDate(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss"));
                                    ProjectDaoInterface.getInstance().addProject(project);
                                    if(mListener != null){
                                        mListener.onAdd(project);
                                    }
                                }else {
                                    UToast.toastError(getActivity(), "title不能为空");
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

    public interface AddListener {
        void onAdd(Project item);
    }
}
