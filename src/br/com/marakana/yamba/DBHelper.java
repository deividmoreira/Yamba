/**
 * 
 */
package br.com.marakana.yamba;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * @author Deivid
 *
 */
public class DBHelper extends SQLiteOpenHelper {
	
	static final String TAG = "DBHelper";
	static final String DB_NAME = "timeline.db";
	static final int DB_VERSION = 1;
	static final String TABLE = "timeline";
	static final String C_ID = BaseColumns._ID;
	static final String C_CREATED_AT = "created_id";
	static final String C_SOURCE = "source";
	static final String C_TEXT = "txt";
	static final String C_USER = "user";
	
	Context context;
	
	public DBHelper(Context context){
		super(context, DB_NAME, null, DB_VERSION);
	}
	
	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */
	// Called only once, first time the DB is created
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql = "create table "+TABLE+ " (" +C_ID+ " int primary key, "
				+ C_CREATED_AT + " int, " +C_SOURCE+ " text, " +C_USER+ " text, " +C_TEXT+ " text)";
		db.execSQL(sql);
		
		Log.d(TAG, "onCreated sql: "+sql);
	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
	// Called whenever newVersion != oldVersion
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int version) {
		// TODO Auto-generated method stub
		db.execSQL("drop table if exists "+TABLE);//drop the old database
		Log.d(TAG, "onUpdated");
		onCreate(db);
	}

}
