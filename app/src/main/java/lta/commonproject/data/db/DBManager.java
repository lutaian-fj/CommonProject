package lta.commonproject.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: LuTaiAn
 * @ClassName:
 * @Description:
 * @date: 2016/7/26
 */
public class DBManager {
    public static DBManager instance;
    public static DBHelper mDBHelper;
    private SQLiteDatabase mDB;
    private AtomicInteger mOpenCounter = new AtomicInteger();
    public static synchronized void initialize(Context context,DBHelper dbHelper) {
        if(instance == null) {
            instance = new DBManager();
            mDBHelper = dbHelper;
        }
    }

    public static synchronized DBManager getInstance() {
        if(instance == null) {
            throw new IllegalStateException(DBManager.class.getSimpleName() +
                    " is not initialized, call initialize(..) method first.");
        }
        return instance;
    }

    public synchronized SQLiteDatabase getWritableDB() {
        if(mOpenCounter.incrementAndGet() == 1) {
            // Opening new database
            mDB = mDBHelper.getWritableDatabase();
        }
        return mDB;
    }

    public synchronized SQLiteDatabase getReadableDB() {
        if(mOpenCounter.incrementAndGet() == 1) {
            // Opening new database
            mDB = mDBHelper.getReadableDatabase();
        }
        return mDB;
    }

    public synchronized void closeDatabase() {
        if(mOpenCounter.decrementAndGet() == 0) {
            // Closing database
            mDB.close();
        }
    }
}
