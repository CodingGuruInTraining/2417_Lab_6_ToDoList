package com.example.hl4350hb.todolist;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by hl4350hb on 10/4/17.
 */

public class AddToDoItemFragment extends Fragment {

    private static final String TAG = "Add To Do Item Fragment";

    private NewItemCreatedListener mNewItemListener;

    //TODO

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof NewItemCreatedListener) {
            mNewItemListener = (NewItemCreatedListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement NewItemCreatedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_to_do_item, container, false);

        Button addItem = (Button) view.findViewById(R.id.add_todo_item_button);
        final EditText newItemText = (EditText) view.findViewById(R.id.new_todo_item_edittext);
        final CheckBox urgentCheckbox = (CheckBox) view.findViewById(R.id.urgent_checkbox);

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validate user has entered some text
                if (newItemText.getText().length() > 0) {
                    String text = newItemText.getText().toString();
                    boolean urgent = urgentCheckbox.isChecked();

                    // Clear input form
                    newItemText.getText().clear();
                    urgentCheckbox.setChecked(false);

                    // Create a new to do item
                    ToDoItem newItem = new ToDoItem(text, urgent);

                    Log.d(TAG, "New item is " + newItem);

                    // Return newItem back to calling Activity
                    mNewItemListener.newItemCreated(newItem);
                } else {
                    Toast.makeText(getActivity(), "Please enter some text", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mNewItemListener = null;
    }

    public interface NewItemCreatedListener {
        void newItemCreated(ToDoItem newItem);
    }

    public static AddToDoItemFragment newInstance() {
        return new AddToDoItemFragment();
    }
}
