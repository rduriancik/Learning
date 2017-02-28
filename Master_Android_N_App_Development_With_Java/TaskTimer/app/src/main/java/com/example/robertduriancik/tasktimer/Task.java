package com.example.robertduriancik.tasktimer;

import java.io.Serializable;

/**
 * Created by robert on 28.2.2017.
 */

class Task implements Serializable {
    public static final long serialVersionUID = 20161120L;

    private long m_Id;
    private final String mName;
    private final String mDescription;
    private final int mSortOrder;

    public Task(long id, String description, String name, int sortOrder) {
        m_Id = id;
        mDescription = description;
        mName = name;
        mSortOrder = sortOrder;
    }

    public long getId() {
        return m_Id;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getName() {
        return mName;
    }

    public int getSortOrder() {
        return mSortOrder;
    }

    public void setId(long id) {
        m_Id = id;
    }

    @Override
    public String toString() {
        return "Task{" +
                "m_Id=" + m_Id +
                ", mName='" + mName + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", mSortOrder=" + mSortOrder +
                '}';
    }
}
