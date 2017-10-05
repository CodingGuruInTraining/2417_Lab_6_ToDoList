package com.example.hl4350hb.todolist;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddToDoItemFragment.NewItemCreatedListener, ToDoItemDetailFragment.MarkItemAsDoneListener,ToDoListFragment.ListItemSelectedListener {

    private static final String TODO_ITEMS_KEY = "TODO ITEMS ARRAY LIST";
    private static final String ADD_NEW_FRAG_TAG = "ADD NEW FRAGMENT";
    private static final String LIST_FRAG_TAG = "LIST FRAGMENT";
    private static final String DETAIL_FRAG_TAG = "DETAIL FRAGMENT";

    private ArrayList<ToDoItem> mTodoItems;

    private static final String TAG = "MAIN ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            //
            Log.d(TAG, "onCreate has no instance state. ...");

            mTodoItems = new ArrayList<>();

            AddToDoItemFragment addNewFragment = AddToDoItemFragment.newInstance();
            ToDoListFragment listFragment = ToDoListFragment.newInstance(mTodoItems);

            android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            ft.add(R.id.add_todo_view_container, addNewFragment, ADD_NEW_FRAG_TAG);
            ft.add(R.id.todo_list_view_container, listFragment, LIST_FRAG_TAG);

            ft.commit();
        } else {
            //
            mTodoItems = savedInstanceState.getParcelableArrayList(TODO_ITEMS_KEY);
            Log.d(TAG, "onCreate has saved instance state ArrayList = " + mTodoItems);
        }

        //TODO
        //TODO
        //TODO
        //TODO
    }

    @Override
    public void onSaveInstanceState(Bundle outBundle) {
        super.onSaveInstanceState(outBundle);
        outBundle.putParcelableArrayList(TODO_ITEMS_KEY, mTodoItems);
    }

    @Override
    public void newItemCreated(ToDoItem newItem) {
        // Add item to the ArrayList
        mTodoItems.add(newItem);

        Log.d(TAG, "newItemCreated = " + mTodoItems);

        //
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        ToDoListFragment listFragment = (ToDoListFragment) fm.findFragmentByTag(LIST_FRAG_TAG);
        listFragment.notifyItemsChanged();
    }

    @Override
    public void itemSelected(ToDoItem selected) {
        //TODO
    }

    @Override
    public void todoItemDone(ToDoItem doneItem) {
        //TODO
        //TODO
    }
}
