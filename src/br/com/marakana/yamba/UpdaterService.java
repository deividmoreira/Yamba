/**
 * 
 */
package br.com.marakana.yamba;

import java.util.List;

import winterwell.jtwitter.Twitter;
import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.util.Log;

/**
 * @author Deivid
 * 
 */
public class UpdaterService extends Service {

	static final String TAG = "UpdaterService";
	static final int DELAY = 60000; // 1 minuto
	private boolean runFlag = false;
	private Updater updater;
	private YambaApplication yamba;

	DBHelper dbHelper;
	SQLiteDatabase db;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Service#onBind(android.content.Intent)
	 */
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub'
		super.onCreate();
		this.yamba = (YambaApplication) getApplication();
		this.updater = new Updater();

		dbHelper = new DBHelper(this);

		Log.d(TAG, "onCreated");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		super.onStartCommand(intent, flags, startId);

		this.runFlag = true;
		this.updater.start();
		yamba.setServiceRunning(true);

		Log.d(TAG, "onStarted");
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		this.runFlag = false;
		yamba.setServiceRunning(false);
		this.updater.interrupt();
		this.updater = null;

		Log.d(TAG, "onDestroyed");
	}

	private class Updater extends Thread {
		List<Twitter.Status> timeline;

		public Updater() {
			super("UpdaterService-Updater");
		}

		@Override
		public void run() {
			UpdaterService updaterService = UpdaterService.this;
			while (updaterService.runFlag) {
				Log.d(TAG, "Running background thread");
				try {
					YambaApplication yamba = (YambaApplication) updaterService.getApplication(); //
					int newUpdates = yamba.fetchStatusUpdates(); //
					if (newUpdates > 0) { //
						Log.d(TAG, "We have a new status");
					}
					Thread.sleep(DELAY);
				} catch (InterruptedException e) {
					updaterService.runFlag = false;
				}

			}
		}
	}
}
