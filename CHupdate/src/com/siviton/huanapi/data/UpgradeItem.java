package com.siviton.huanapi.data;

import java.util.List;

import android.app.ListActivity;

public class UpgradeItem {

	private String servertime;
	private String callid;
	private String state;
	private String note;
	private String language;
	private String timezone;
	private String region;
	private String apiversion;
	public String getApiversion() {
		return apiversion;
	}

	public void setApiversion(String apiversion) {
		this.apiversion = apiversion;
	}

	private List<UpgradeUpdateItem> upgradeUpdateItems;

	public UpgradeItem(String servertime, String callid, String state,
			String note, String language, String timezone, String region,
			List<UpgradeUpdateItem> upgradeUpdateItems) {
		super();
		this.servertime = servertime;
		this.callid = callid;
		this.state = state;
		this.note = note;
		this.language = language;
		this.timezone = timezone;
		this.region = region;
		this.upgradeUpdateItems = upgradeUpdateItems;
	}

	public UpgradeItem() {
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

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public List<UpgradeUpdateItem> getUpgradeUpdateItems() {
		return upgradeUpdateItems;
	}

	public void setUpgradeUpdateItems(List<UpgradeUpdateItem> upgradeUpdateItems) {
		this.upgradeUpdateItems = upgradeUpdateItems;
	}

}
