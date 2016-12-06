package com.example.karen.registrationproject.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.karen.registrationproject.R;
import com.example.karen.registrationproject.api.model.AttendanceItem;

import java.util.List;

/**
 * Created by Karen on 10/20/2016.
 */

public class AttendanceAdapter extends BaseAdapter {

    private Context context;
    private List<AttendanceItem> data;

    public AttendanceAdapter(Context context, List<AttendanceItem> data){
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_attendace, parent, false);
            viewHolder = new ViewHolder(convertView);
            viewHolder.dayTXT = (TextView) convertView.findViewById(R.id.attendance_date_txt);
            viewHolder.timeinTXT = (TextView) convertView.findViewById(R.id.attendance_time_in_txt);
            viewHolder.timeoutTXT = (TextView) convertView.findViewById(R.id.attendance_time_out_txt);
            viewHolder.totalhrsTXT = (TextView) convertView.findViewById(R.id.attendance_total_hours_txt);
            viewHolder.taskTXT = (TextView) convertView.findViewById(R.id.attendance_task_txt);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        AttendanceItem item = data.get(position);

        viewHolder.dayTXT.setText("Date : " + item.date);
        viewHolder.timeinTXT.setText("Time-in : " + item.timeIn);
        viewHolder.timeoutTXT.setText("Time-out : " + item.timeOut);
        viewHolder.totalhrsTXT.setText("Total Hours : " + item.totalHours);
        viewHolder.taskTXT.setText("Task : " + item.task);


        return convertView;
    }

    class ViewHolder{

        public TextView dayTXT;
        public TextView timeinTXT;
        public TextView timeoutTXT;
        public TextView totalhrsTXT;
        public TextView taskTXT;


        View view;
        ViewHolder(View view){
            this.view = view;
        }
    }
}
