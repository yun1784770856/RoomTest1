package android.study.roomtest1;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * @author zyy
 * @date 2022年02月09日 下午5:09
 */
@Database(entities = {Word.class}, version = 3, exportSchema = false)
public abstract class WordDatabase extends RoomDatabase {
    private static volatile  WordDatabase INSTANCE;

    // 单例模式
    public static WordDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (WordDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordDatabase.class,"words_db")
                            .addMigrations(MIGRATION2_3)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract WordDao getWordDao();

    static Migration MIGRATION1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE word ADD COLUMN test INTEGER NOT NULL DEFAULT 1");
        }
    };

    static Migration MIGRATION2_3 = new Migration(2,3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE word_temp (id INTEGER PRIMARY KEY NOT NULL, english_word TEXT," +
                    "chinese_word TEXT)");
            database.execSQL("INSERT INTO word_temp (id, english_word, chinese_word)" +
                    "SELECT id, english_word, chinese_word FROM word");
            database.execSQL("DROP TABLE word");
            database.execSQL("ALTER TABLE word_temp RENAME TO word");
        }
    };

}
