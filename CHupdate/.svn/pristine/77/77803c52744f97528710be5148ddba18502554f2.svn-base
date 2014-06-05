package com.siviton.huanapi.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class DataSave {
	private Context mContext;
	private SharedPreferences mSharedPreferences;
	private final String mShareString = "ch_sharedpreferences";
	private Editor mEditor;
	private UpdateItem mUpdateItem;
	private final String DATESAVE_downloadID = "downloadID";
	private final String DATESAVE_updateState = "updateState";
	private final String DATESAVE_type = "type";
	private final String DATESAVE_appid = "appid";
	private final String DATESAVE_title = "title";
	private final String DATESAVE_apptype = "apptype";
	private final String DATESAVE_version = "version";
	private final String DATESAVE_verid = "verid";
	private final String DATESAVE_description = "description";
	private final String DATESAVE_minicon = "minicon";
	private final String DATESAVE_midicon = "midicon";
	private final String DATESAVE_fileurl = "fileurl";
	private final String DATESAVE_md5 = "md5";
	private final String DATESAVE_increment = "increment";
	private final String DATESAVE_appendver = "appendver";
	private final String DATESAVE_note = "note";
	private final String DATESAVE_size = "size";

	public DataSave(Context mContext) {
		super();
		this.mContext = mContext;
		mSharedPreferences = mContext.getSharedPreferences(mShareString,
				Context.MODE_PRIVATE);
		mEditor = mSharedPreferences.edit();
	}

	public String getDownloadID() {
		return mSharedPreferences.getString(DATESAVE_downloadID, null);
	}

	public int getUpdateState() {
		return mSharedPreferences.getInt(DATESAVE_updateState, -1);
	}

	public String getType() {
		return mSharedPreferences.getString(DATESAVE_type, null);
	}

	public String getAppid() {
		return mSharedPreferences.getString(DATESAVE_appid, null);
	}

	public String getTitle() {
		return mSharedPreferences.getString(DATESAVE_title, null);
	}

	public String getApptype() {
		return mSharedPreferences.getString(DATESAVE_apptype, null);
	}

	public String getVersion() {
		return mSharedPreferences.getString(DATESAVE_version, null);
	}

	public String getVerid() {
		return mSharedPreferences.getString(DATESAVE_verid, null);
	}

	public String getDescription() {
		return mSharedPreferences.getString(DATESAVE_description, null);
	}

	public String getMinicon() {
		return mSharedPreferences.getString(DATESAVE_minicon, null);
	}

	public String getMidicon() {
		return mSharedPreferences.getString(DATESAVE_midicon, null);
	}

	public String getFileurl() {
		return mSharedPreferences.getString(DATESAVE_fileurl, null);
	}

	public String getSize() {
		return mSharedPreferences.getString(DATESAVE_size, null);
	}

	public String getMd5() {
		return mSharedPreferences.getString(DATESAVE_md5, null);
	}

	public String getIncrement() {
		return mSharedPreferences.getString(DATESAVE_increment, null);
	}

	public String getAppendver() {
		return mSharedPreferences.getString(DATESAVE_appendver, null);
	}

	public String getNote() {
		return mSharedPreferences.getString(DATESAVE_note, null);
	}

	public void setNote(String note) {
		mEditor.putString(DATESAVE_note, note);
		mEditor.commit();
	}

	public void setAppendver(String appendver) {
		mEditor.putString(DATESAVE_appendver, appendver);
		mEditor.commit();
	}

	public void setIncrement(String increment) {
		mEditor.putString(DATESAVE_increment, increment);
		mEditor.commit();
	}

	public void setMd5(String md5) {
		mEditor.putString(DATESAVE_md5, md5);
		mEditor.commit();
	}

	public void setSize(String size) {
		mEditor.putString(DATESAVE_size, size);
		mEditor.commit();
	}

	public void setFileurl(String fileurl) {
		mEditor.putString(DATESAVE_fileurl, fileurl);
		mEditor.commit();
	}

	public void setMidicon(String midicon) {
		mEditor.putString(DATESAVE_midicon, midicon);
		mEditor.commit();
	}

	public void setMinicon(String minicon) {
		mEditor.putString(DATESAVE_minicon, minicon);
		mEditor.commit();
	}

	public void setDescription(String description) {
		mEditor.putString(DATESAVE_description, description);
		mEditor.commit();
	}

	public void setVerid(String verid) {
		mEditor.putString(DATESAVE_verid, verid);
		mEditor.commit();
	}

	public void setVersion(String version) {
		mEditor.putString(DATESAVE_version, version);
		mEditor.commit();
	}

	public void setApptype(String apptype) {
		mEditor.putString(DATESAVE_apptype, apptype);
		mEditor.commit();
	}

	public void setTitle(String title) {
		mEditor.putString(DATESAVE_title, title);
		mEditor.commit();
	}

	public void setAppid(String appid) {
		mEditor.putString(DATESAVE_appid, appid);
		mEditor.commit();
	}

	public void setType(String type) {
		mEditor.putString(DATESAVE_type, type);
		mEditor.commit();
	}

	public void setUpdateState(int updateState) {
		mEditor.putInt(DATESAVE_updateState, updateState);
		mEditor.commit();
	}

	public void setDownloadID(String downloadID) {
		mEditor.putString(DATESAVE_downloadID, downloadID);
		mEditor.commit();
	}
}
