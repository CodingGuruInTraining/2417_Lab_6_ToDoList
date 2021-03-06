package com.example.hl4350hb.todolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddToDoItemFragment.NewItemCreatedListener, ToDoItemDetailFragment.MarkItemAsDoneListener,ToDoListFragment.ListItemSelectedListener {

    // Static key and tag values.
    private static final String TODO_ITEMS_KEY = "TODO ITEMS ARRAY LIST";
    private static final String ADD_NEW_FRAG_TAG = "ADD NEW FRAGMENT";
    private static final String LIST_FRAG_TAG = "LIST FRAGMENT";
    private static final String DETAIL_FRAG_TAG = "DETAIL FRAGMENT";
    private static final String TAG = "MAIN ACTIVITY";

    // Global variable to hold arraylist.
    private ArrayList<ToDoItem> mTodoItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            // no saved instance state
            Log.d(TAG, "onCreate has no instance state. ...");
            // Create new arraylist
            mTodoItems = new ArrayList<>();

            // Creates new instance of each fragment container.
            AddToDoItemFragment addNewFragment = AddToDoItemFragment.newInstance();
            ToDoListFragment listFragment = ToDoListFragment.newInstance(mTodoItems);
            ToDoItemDetailFragment detailFragment = ToDoItemDetailFragment.newInstance(new ToDoItem("", false));

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            // Adds each fragment container to activity.
            ft.add(R.id.add_todo_view_container, addNewFragment, ADD_NEW_FRAG_TAG);
            ft.add(R.id.todo_list_view_container, listFragment, LIST_FRAG_TAG);
            ft.add(R.id.todo_detail_view_container, detailFragment);

            ft.commit();
        } else {
            // Loads saved values.
            mTodoItems = savedInstanceState.getParcelableArrayList(TODO_ITEMS_KEY);
            Log.d(TAG, "onCreate has saved instance state ArrayList = " + mTodoItems);
        }
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

        // Does some fragment magic.
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        ToDoListFragment listFragment = (ToDoListFragment) fm.findFragmentByTag(LIST_FRAG_TAG);
        listFragment.notifyItemsChanged();
    }

    @Override
    public void itemSelected(ToDoItem selected) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Replaces current fragment item.
        ft.replace(R.id.todo_detail_view_container, ToDoItemDetailFragment.newInstance(selected));

        // Create a new Detail fragment and add to the activity.
        ToDoItemDetailFragment detailFragment = ToDoItemDetailFragment.newInstance(selected);
        ft.add(android.R.id.content, detailFragment);
        // Add to the back stack so pressing the Back button will revert to this transaction.
        ft.addToBackStack(DETAIL_FRAG_TAG);

        ft.commit();
    }

    @Override
    public void todoItemDone(ToDoItem doneItem) {
        // Remove item from list
        mTodoItems.remove(doneItem);
        Log.d(TAG, "newItemRemoved list is now = " + mTodoItems);

        // Find list fragment and tell it that the data has changed.
        FragmentManager fm = getSupportFragmentManager();
        ToDoListFragment listFragment = (ToDoListFragment) fm.findFragmentByTag(LIST_FRAG_TAG);
        listFragment.notifyItemsChanged();

        // Revert the last fragment transaction on the back stack.
        // This removes the detail fragment from the activity, which leaves the addlist fragments.

        FragmentTransaction ft = fm.beginTransaction();
        fm.popBackStack();
        ft.commit();
    }
}
