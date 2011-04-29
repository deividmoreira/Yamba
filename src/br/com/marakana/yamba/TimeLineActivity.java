/**
 * 
 */
package br.com.marakana.yamba;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import br.com.marakana.yamba.StatusData.DbHelper;

/**
 * @author Deivid
 *
 */
public class TimeLineActivity extends Activity {
	DbHelper dbHelper;
	SQLiteDatabase db;
	Cursor cursor;
	TextView textTimeline;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timeline_basic);
		// Find your views
		textTimeline = (TextView) findViewById(R.id.textTimeline);
		// Connect to database
//		dbHelper = new DbHelper(this); 
		db = dbHelper.getReadableDatabase(); //
	}
}
