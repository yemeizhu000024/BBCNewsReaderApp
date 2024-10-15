package com.example.androidlabs;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class ToDoAdapter extends BaseAdapter {

    private final ArrayList<ToDoItem> toDoList;
    private final Context context;

    public ToDoAdapter(ArrayList<ToDoItem> toDoList, Context context) {
        this.toDoList = toDoList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return toDoList.size();
    }

    @Override
    public Object getItem(int position) {
        return toDoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.todo_item_layout, parent, false);
            holder = new ViewHolder();
            holder.textView = convertView.findViewById(R.id.todo_item_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ToDoItem item = toDoList.get(position);
        holder.textView.setText(item.getText());

        if (item.isUrgent()) {
            convertView.setBackgroundColor(Color.RED);
            holder.textView.setTextColor(Color.WHITE);
        } else {
            convertView.setBackgroundColor(Color.WHITE);
            holder.textView.setTextColor(Color.BLACK);
        }

        return convertView;
    }

    static class ViewHolder {
        TextView textView;
    }
}