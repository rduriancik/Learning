package com.android.roomwordsample.roomwordsample;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Robert Duriancik on 12/19/18.
 */
@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;

    public Word(@NonNull String mWord) {
        this.mWord = mWord;
    }

    public String getWord() {
        return mWord;
    }
}
