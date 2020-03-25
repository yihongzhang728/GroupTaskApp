package com.example.grouptaskapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class TaskItemAdapter extends ArrayAdapter<TaskItem> {

    private int resource;

    public TaskItemAdapter(Context ctx, int res, List<TaskItem> items)
    {
        super(ctx, res, items);
        resource = res;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout taskView;
        TaskItem jb = getItem(position);

        if (convertView == null) {
            taskView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(inflater);
            vi.inflate(resource, taskView, true);
        } else {
            taskView = (LinearLayout) convertView;
        }

        TextView nameView = (TextView) taskView.findViewById(R.id.name_text);
        TextView deadlineView = (TextView) taskView.findViewById(R.id.deadline_text);
        TextView assigneeView = (TextView) taskView.findViewById(R.id.assignee_text);

        nameView.setText(jb.getName());
        deadlineView.setText(jb.getDeadline());
        assigneeView.setText(jb.getAssignee());

        return taskView;
    }


}

