package android.study.roomtest1;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

/**
 * @author zyy
 * @date 2022年02月09日 下午9:45
 */
public class WordRepository {

    private WordDao wordDao;

    public WordRepository(Application application) {
        WordDatabase db = WordDatabase.getDatabase(application);
        wordDao = db.getWordDao();
    }


    public LiveData<List<Word>> getAllLiveData(){
        return wordDao.getAllLiveData();
    }

    public void insertWords(Word... words){
        new InsertWordsAsyncTask(wordDao).execute(words);
    }

    public void deleteAll(){
        new DeleteAllAsyncTask(wordDao).execute();
    }

    static class InsertWordsAsyncTask extends AsyncTask<Word, Void, Void>{
        private WordDao dao;

        public InsertWordsAsyncTask(WordDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            dao.insertWord(words);
            return null;
        }
    }

    static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void>{
        private WordDao wordDao;

        public DeleteAllAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            wordDao.deleteAllWords();
            return null;
        }
    }


}
