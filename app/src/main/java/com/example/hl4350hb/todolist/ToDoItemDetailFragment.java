package com.example.hl4350hb.todolist;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by hl4350hb on 10/4/17.
 */

public class ToDoItemDetailFragment extends Fragment {

    private static final String TODO_ITEM_ARGUMENT = "todo item argument";
    private static final String TAG = "TODO ITEM DETAIL FRAG";

    MarkItemAsDoneListener mItemDoneListener;

    //TODO


    public static ToDoItemDetailFragment newInstance(ToDoItem item) {
        final Bundle args = new Bundle();
        args.putParcelable(TODO_ITEM_ARGUMENT, item);
        final ToDoItemDetailFragment fragment = new ToDoItemDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof MarkItemAsDoneListener) {
            mItemDoneListener = (MarkItemAsDoneListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement MarkItemAsDoneListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_to_do_item_detail, container, false);

        // Get the to do item from the arguements passed in when this fragment was created.
        final ToDoItem item = getArguments().getParcelable(TODO_ITEM_ARGUMENT);
        Log.d(TAG, "onCreateView received the follwoing item: " + item);

        // Set up the view.
        final TextView detailTextText = (TextView) view.findViewById(R.id.to_do_detail_text_textview);
        final TextView detailDateText = (TextView) view.findViewById(R.id.to_do_detail_date_created_textview);
        final CheckBox detailUrgenCheckBox = (CheckBox) view.findViewById(R.id.to_do_detail_urgent_checkbox);
        Button doneButton = (Button) view.findViewById(R.id.to_do_detail_done_button);

        detailTextText.setText(item.getText());
        detailDateText.setText(item.getDateCreated().toString());
        detailUrgenCheckBox.setChecked(item.isUrgent());

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Clear the data about this to do item.
                detailTextText.setText("");
                detailDateText.setText("");
                detailUrgenCheckBox.setChecked(false);
                // Tell listener that this item is done.
                mItemDoneListener.todoItemDone(item);
            }
        });

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mItemDoneListener = null;
    }

    interface MarkItemAsDoneListener {
        void todoItemDone(ToDoItem doneItem);
    }


}
