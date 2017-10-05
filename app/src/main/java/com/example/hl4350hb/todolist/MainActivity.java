package com.example.hl4350hb.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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

        //TODO
        //TODO
        //TODO
        //TODO
    }

    @Override
    public void onSaveInstanceState(Bundle outBundle) {
        //TODO
    }

    @Override
    public void newItemCreated(ToDoItem newItem) {
        //TODO
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
