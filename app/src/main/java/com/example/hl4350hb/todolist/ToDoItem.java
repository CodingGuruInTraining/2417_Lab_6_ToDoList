package com.example.hl4350hb.todolist;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by hl4350hb on 10/4/17.
 */

public class ToDoItem implements Parcelable {
    // Object's attributes.
    private String text;
    private Date dateCreated;
    private boolean urgent;

    // Constructor #1.
    public ToDoItem(Parcel in) {
        text = in.readString();
        dateCreated = (Date) in.readSerializable();
        urgent = (in.readInt() == 1);
    }

    // Constructor #2.
    public ToDoItem(String text, boolean urgent) {
        this.text = text;
        this.urgent = urgent;
        dateCreated = new Date();
    }

    // Getters and Setters.

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isUrgent() {
        return urgent;
    }

    public void setUrgent(boolean urgent) {
        this.urgent = urgent;
    }

    @Override
    public String toString() {
        return text + " " + dateCreated.toString() + " is urgent? " + urgent;
    }

    // Code required by the Parcelable interface
    static final Parcelable.Creator<ToDoItem> CREATOR = new Parcelable.Creator<ToDoItem>() {
        public ToDoItem createFromParcel(Parcel in) {
            return new ToDoItem(in);
        }

        @Override
        public ToDoItem[] newArray(int size) {
            return new ToDoItem[size];
        }
    };

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(text);
        dest.writeSerializable(dateCreated);
        // Write - for false, 1 for true
        int urg = urgent ? 1 : 0;
        dest.writeInt(urg);
    }
}
