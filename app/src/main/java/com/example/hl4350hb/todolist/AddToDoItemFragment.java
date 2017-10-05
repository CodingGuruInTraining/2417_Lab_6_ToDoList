package com.example.hl4350hb.todolist;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

        //TODO
        //TODO

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
}
