package lta.commonproject.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author: LuTaiAn
 * @ClassName:
 * @Description:
 * @date: 2016/7/26
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "lta";
    public static final int VERSION = 1;
    public DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.e("lta","db is creating");
        String sql = "create table info(id integer primary key autoincrement,name text,student_num text)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
