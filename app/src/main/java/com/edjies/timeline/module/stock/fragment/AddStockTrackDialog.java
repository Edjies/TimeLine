package com.edjies.timeline.module.stock.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.edjies.timeline.R;
import com.edjies.timeline.module.project.dao.ProjectDaoInterface;
import com.edjies.timeline.module.stock.bean.StockTrack;
import com.edjies.timeline.module.stock.dao.StockDaoInterface;
import com.edjies.timeline.utils.UDate;
import com.edjies.timeline.utils.UNumber;
import com.edjies.timeline.utils.UToast;

import java.util.Calendar;

/**
 *
 *  Created by hubble on 2016/11/21 0021.
 */

public class AddStockTrackDialog extends DialogFragment {
    private EditText mEtCode, mEtName, mEtHigh, mEtLow, mEtStart, mEtEnd, mEtDesc;

    private ProjectDaoInterface mDao;
    private long mStockPoolId;
    private AddListener mListener;

    public static AddStockTrackDialog newInstance(long stockPoolId) {
        AddStockTrackDialog frag = new AddStockTrackDialog();
        Bundle args = new Bundle();
        frag.setArguments(args);
        frag.mStockPoolId = stockPoolId;
        return frag;
    }

    public void setOnAddListener(AddListener listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.stock_view_add_stock_track, null);
        mEtCode = (EditText) view.findViewById(R.id.et_code);
        mEtName = (EditText) view.findViewById(R.id.et_name);
        mEtHigh = (EditText) view.findViewById(R.id.et_high);
        mEtLow = (EditText) view.findViewById(R.id.et_low);
        mEtStart = (EditText) view.findViewById(R.id.et_start);
        mEtEnd = (EditText) view.findViewById(R.id.et_end);
        mEtDesc = (EditText) view.findViewById(R.id.et_desc);
        mEtStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(mEtStart);
            }
        });
        mEtEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(mEtEnd);
            }
        });
        return new AlertDialog.Builder(getActivity())
                    .setIcon(R.drawable.ic_dialog_add)
                .setTitle("添加任务")
                .setView(view)
                .setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                String code = mEtCode.getText().toString();
                                String name = mEtName.getText().toString();
                                String high = mEtHigh.getText().toString();
                                String low = mEtLow.getText().toString();
                                String start = mEtStart.getText().toString();
                                String end = mEtEnd.getText().toString();
                                String desc = mEtDesc.getText().toString();
                                if("".equals(code) || "".equals(name) || "".equals(high) || "".equals(low) || "".equals(start) || "".equals(end)) {
                                    UToast.toastError(getActivity(), "信息不完整");
                                    return;
                                }

                                StockTrack stockTrack = new StockTrack(mStockPoolId, code, name, UNumber.getFloat(high, 0.0f), UNumber.getFloat(low, 0.0f), desc, start, end);
                                StockDaoInterface.getInstance().addStockTrack(stockTrack);
                                    if(mListener != null) {
                                        mListener.onAdd(stockTrack);
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

    public void showDatePicker(final EditText et) {
        final Calendar date = Calendar.getInstance();
        new DatePickerDialog(getActivity(),R.style.BaseDateTimeDialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                date.set(year, month, dayOfMonth);
                et.setText(UDate.getFormatDate(date, "yyyy-MM-dd"));
            }
        },date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH)).show();
    }


    public interface AddListener {
        void onAdd(StockTrack item);
    }

}
