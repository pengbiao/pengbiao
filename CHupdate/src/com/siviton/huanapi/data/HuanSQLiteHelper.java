package com.siviton.huanapi.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class HuanSQLiteHelper extends SQLiteOpenHelper {
	private Context mContext;
	private final String TAG = "HuanSQLiteHelper";

	// table name
	// public static final String TABLE_NAME = "huanitem";
	// public static final String COLUMN_DEVICEID = "deviceid";
	// public static final String COLUMN_DNUM = "dnum";
	// public static final String COLUMN_DEVICEMODEL = "devicemodel";
	// public static final String COLUMN_ACTIVEKEY = "activekey";
	// public static final String COLUMN_DIDTOKEN = "didtoken";
	// public static final String COLUMN_TOKEN = "token";
	// public static final String COLUMN_HUANID = "huanid";
	// public static final String COLUMN_LICENSETYPE = "licensetype";
	// public static final String COLUMN_LICENSEDATA = "licensedata";
	// public static final String COLUMN_ID = "_id";
	public HuanSQLiteHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table if not exists " + HuanApi.TABLE_NAME + " ("
				+ "_id" + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ HuanApi.COLUMN_DEVICEID + " TEXT,"
				+ HuanApi.COLUMN_DNUM+ " TEXT," 
				+ HuanApi.COLUMN_DEVICEMODEL + " TEXT,"
				+ HuanApi.COLUMN_ACTIVEKEY + " TEXT,"
				+ HuanApi.COLUMN_DIDTOKEN + " TEXT,"
				+ HuanApi.COLUMN_TOKEN+ " TEXT," 
				+ HuanApi.COLUMN_HUANID + " TEXT,"
				+ HuanApi.COLUMN_LICENSETYPE + " TEXT,"
				+ HuanApi.COLUMN_LICENSEDATA + " TEXT,"
				+ HuanApi.COLUMN_DOWNLOAD_ID + " TEXT,"
				+ HuanApi.COLUMN_UPDATE_STATE + " TEXT" + ")");
		loaddefault(db);
	}

	private void loaddefault(SQLiteDatabase db) {
		// try {
		// XmlResourceParser parser = mContext.getResources().getXml(
		// R.xml.mstariteminfo);
		// int position;
		// while((position = parser.next()) != XmlResourceParser.END_DOCUMENT){
		// if(position != XmlResourceParser.START_TAG){
		// continue;
		// }
		//
		// String name = parser.getName();
		// if(XML_TAG_BEGIN.equals(name)){
		HuanItemInfo iteminfo = new HuanItemInfo();
		iteminfo.setDeviceid(getdeviceid());
		iteminfo.setDevicemodel(getdevmodel());
		iteminfo.setDnum(null);
		iteminfo.setDidtoken(null);
		iteminfo.setActivekey(null); 
		iteminfo.setHuanid(null);
		iteminfo.setLicensedata(null);
		iteminfo.setLicensetype(null);
		iteminfo.setToken(null);
		iteminfo.setDownloadid(null);
		iteminfo.setUpdatestate(null);
		insert(db, iteminfo);
		// }
		// }
		//
		// }catch(XmlPullParserException e){
		// e.printStackTrace();
		// }catch(IOException e){
		// e.printStackTrace();
		// }

	} 

	public void insert(SQLiteDatabase db, HuanItemInfo iteminfo) {
		long result = -1;
		System.out.println("==pengbdata==1232====");
		ContentValues values = new ContentValues();
		values.put(HuanApi.COLUMN_DEVICEID, iteminfo.getDeviceid());
		values.put(HuanApi.COLUMN_DNUM, iteminfo.getDnum());
		values.put(HuanApi.COLUMN_DEVICEMODEL, iteminfo.getDevicemodel());
		values.put(HuanApi.COLUMN_ACTIVEKEY, iteminfo.getActivekey());
		values.put(HuanApi.COLUMN_DIDTOKEN, iteminfo.getDidtoken());
		values.put(HuanApi.COLUMN_TOKEN, iteminfo.getToken());
		values.put(HuanApi.COLUMN_HUANID, iteminfo.getHuanid());
		values.put(HuanApi.COLUMN_LICENSETYPE, iteminfo.getLicensetype());
		values.put(HuanApi.COLUMN_LICENSEDATA, iteminfo.getLicensedata());
		values.put(HuanApi.COLUMN_DOWNLOAD_ID, iteminfo.getDownloadid());
		values.put(HuanApi.COLUMN_UPDATE_STATE, iteminfo.getUpdatestate());

		result = db.insert(HuanApi.TABLE_NAME, null, values);
		if (result == -1) { 
			Log.i(TAG, "---db insert ok");
		} else {
			Log.i(TAG, "---db insert fail");
		} 
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
	private String getdeviceid() {
//		String string = null;
//		try {
//			string = TvManager.getInstance().getEnvironment("huandid");
//		} catch (TvCommonException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		if (string == null) {
//			string = "";
//		}
		return "9e1878bf560648694ec14a27623f5f4c2423b010";
	}

	private String getdevmodel() {
//		String string = "";
//		try {
//			string = TvManager.getInstance().getEnvironment("huanclienttype");
//		} catch (TvCommonException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		if (string == null) {
//			string = "";
//		}
//		return string;
		 return "CH-HXE4B-DTV-00-00";
	}
}
