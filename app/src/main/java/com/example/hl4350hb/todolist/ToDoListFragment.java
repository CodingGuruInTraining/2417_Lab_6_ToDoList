package com.example.hl4350hb.todolist;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by hl4350hb on 10/4/17.
 */

public class ToDoListFragment extends Fragment {

    private ListItemSelectedListener mItemSelectedListener;
    // Static tag values.
    private static final String TODO_LIST_ARGS = "to do list arguments";
    private static final String TAG = "TODO LIST FRAGMENT";

    private ToDoListArrayAdapter mListAdapter;

    // Constructor-like method for creating new instance of object.
    public static ToDoListFragment newInstance(ArrayList todoItems) {
        final Bundle args = new Bundle();
        args.putParcelableArrayList(TODO_LIST_ARGS, todoItems);
        final ToDoListFragment fragment = new ToDoListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_to_do_list, container, false);

        // Sets up widget and arraylist.
        ListView mListView = (ListView) view.findViewById(R.id.to_do_listview);
        ArrayList<ToDoItem> listItems = getArguments().getParcelableArrayList(TODO_LIST_ARGS);

        Log.d(TAG, "onCreateView, ArrayList: " + listItems);

        // Creates new adapter object.
        mListAdapter = new ToDoListArrayAdapter(getActivity(), R.layout.todo_list_item_list_element, listItems);
        // Designates adapter.
        mListView.setAdapter(mListAdapter);
        mListAdapter.notifyDataSetChanged();

        // ListView click event listener.
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Notify the listener that the user has clicked on a list item
                Log.d(TAG, "List item " + position + " clicked, the todo item is " + mListAdapter.getItem(position));
                mItemSelectedListener.itemSelected(mListAdapter.getItem(position));
            }
        });

        return view;
    }

    public void notifyItemsChanged() {
        Log.d(TAG, "Notified that the list of to do items has changed, update view");
        // Tell the list to update
        mListAdapter.notifyDataSetChanged();
    }

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

    // Interface.
    public interface ListItemSelectedListener {
        void itemSelected(ToDoItem selected);
    }
}
