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

public class ToDoListFragment extends Fragment {

    private ListItemSelectedListener mItemSelectedListener;

    private static final String TAG = "TODO LIST FRAGMENT";

    //TODO

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_to_do_list, container, false);

        //TODO
        //TODO

        return view;
    }

    //TODO

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ListItemSelectedListener) {
            mItemSelectedListener = (ListItemSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mItemSelectedListener = null;
    }

    public interface ListItemSelectedListener {
        void itemSelected(ToDoItem selected);
    }
}
