package com.example.hl4350hb.todolist;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hl4350hb on 10/4/17.
 */

public class ToDoItemDetailFragment extends Fragment {

    private static final String TODO_ITEM_ARGUMENT = "todo item argument";
    private static final String TAG = "TODO ITEM DETAIL FRAG";

    MarkItemAsDoneListener mItemDoneListener;

    //TODO

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

        //TODO
        //TODO

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
