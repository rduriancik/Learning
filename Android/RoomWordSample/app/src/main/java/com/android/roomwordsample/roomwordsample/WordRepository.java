package com.android.roomwordsample.roomwordsample;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by Robert Duriancik on 12/19/18.
 */
public class WordRepository {
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        new insertAsyncTask(mWordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            mAsyncTaskDao.insert(words[0]);
            return null;
        }
    }
}
