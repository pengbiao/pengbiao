package com.siviton.huanapi.data;

import java.util.List;

import android.app.ListActivity;

public class UpgradeFileItem {

	private String servertime;
	private String callid;
	private String state;
	private String note;
	private String appid;
	private String title;
	private String fileurl;
	private String size;
	private String md5;
	private String apiversion;
	public UpgradeFileItem(String servertime, String callid, String state,
			String note, String appid, String title, String fileurl,
			String size, String md5, String apiversion) {
		super();
		this.servertime = servertime;
		this.callid = callid;
		this.state = state;
		this.note = note;
		this.appid = appid;
		this.title = title;
		this.fileurl = fileurl;
		this.size = size;
		this.md5 = md5;
		this.apiversion = apiversion;
	}
	public UpgradeFileItem() {
		super();
	}
	public String getServertime() {
		return servertime;
	}
	public void setServertime(String servertime) {
		this.servertime = servertime;
	}
	public String getCallid() {
		return callid;
	}
	public void setCallid(String callid) {
		this.callid = callid;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFileurl() {
		return fileurl;
	}
	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public String getApiversion() {
		return apiversion;
	}
	public void setApiversion(String apiversion) {
		this.apiversion = apiversion;
	}

}
