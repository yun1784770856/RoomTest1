package android.study.roomtest1;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * @author zyy
 * @date 2022年02月09日 下午5:06
 */
@Dao // Database access Object
public interface WordDao {
    @Insert
    void insertWord(Word... words);

    @Update
    void updateWord(Word... words);

    @Delete
    void deleteWord(Word... words);

    @Query("DELETE FROM Word")
    void deleteAllWords();

    @Query("SELECT * FROM Word ORDER BY ID DESC")
    List<Word> getAll();

    @Query("SELECT * FROM Word ORDER BY ID DESC")
    LiveData<List<Word>> getAllLiveData();
}
