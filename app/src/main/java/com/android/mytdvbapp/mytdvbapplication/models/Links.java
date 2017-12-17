package com.android.mytdvbapp.mytdvbapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by antoinepelletier on 17/12/2017.
 */

public class Links {
    @SerializedName("first")
    @Expose
    private int first;
    @SerializedName("last")
    @Expose
    private int last;
    @SerializedName("next")
    @Expose
    private int next;
    @SerializedName("prev")
    @Expose
    private int prev;

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public int getPrev() {
        return prev;
    }

    public void setPrev(int prev) {
        this.prev = prev;
    }
}
