package com.example.hl4350hb.todolist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hl4350hb on 10/4/17.
 */

public class ToDoListArrayAdapter extends ArrayAdapter<ToDoItem> {

    Activity mActivity;

    // Constructor.
    public ToDoListArrayAdapter(Context context, int resource, ArrayList<ToDoItem> items) {
        super(context, resource, items);
        this.mActivity = (Activity) context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        // If this view hasn't already been created, then inflate it from the layout
        if (rowView == null) {
            LayoutInflater inflater = mActivity.getLayoutInflater();
            rowView = inflater.inflate(R.layout.todo_list_item_list_element, parent, false);
        }

        // Get the ToDoItem for this position in the list, set the View components to show
        // data for this ToDoItem
        ToDoItem item = getItem(position);

        // Sets up the widgets.
        TextView todoText = (TextView) rowView.findViewById(R.id.todo_list_text_textview);
        TextView todoDate = (TextView) rowView.findViewById(R.id.todo_list_date_textview);
        CheckBox todoUrgent = (CheckBox) rowView.findViewById(R.id.todo_list_urgent_checkbox);

        // Sets up values in widgets.
        todoText.setText(item.getText());
        todoDate.setText(item.getDateCreated().toString());
        todoUrgent.setChecked(item.isUrgent());

        return rowView;
    }
}
