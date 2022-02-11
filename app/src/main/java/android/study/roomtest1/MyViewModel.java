package android.study.roomtest1;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

/**
 * @author zyy
 * @date 2022年02月09日 下午9:55
 */
public class MyViewModel extends AndroidViewModel {

    private WordRepository wordRepository;

    public MyViewModel(@NonNull Application application) {
        super(application);
        wordRepository = new WordRepository(application);
    }

    public LiveData<List<Word>> getAllWords(){
        return wordRepository.getAllLiveData();
    }

    public void insertWords(Word... words){
        wordRepository.insertWords(words);
    }

    public void deleteAllWords(){
        wordRepository.deleteAll();
    }
}
