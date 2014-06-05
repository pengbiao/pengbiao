package com.siviton.huanapi.data;

import java.util.List;

import android.app.ListActivity;

public class UpgradeIncrResponse {

	private String servertime;
	private String callid;
	private String state;
	private String note;
	private String language;
	private String timezone;
	private String region;
	private String apiversion;
	private List<UpgradeIncrResponseUpgrade> upgradeIncrResponseUpgrades;
	private String downloadid;
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("servertime:" + servertime);
		stringBuffer.append("  callid:" + callid);
		stringBuffer.append("  state:" + state);
		stringBuffer.append("  note:" + note);
		stringBuffer.append("  language:" + language);
		stringBuffer.append("  timezone:" + timezone);
		stringBuffer.append("  region:" + region);
		stringBuffer.append("  apiversion:" + apiversion);
		if (upgradeIncrResponseUpgrades != null) {
			int i = 1;
			for (UpgradeIncrResponseUpgrade mIncrResponseUpgrade : upgradeIncrResponseUpgrades) {
				stringBuffer
						.append("   " + i + mIncrResponseUpgrade.toString());
				i++;
			}
		}
		return stringBuffer.toString();
	}

	public String getApiversion() {
		return apiversion;
	}

	public void setApiversion(String apiversion) {
		this.apiversion = apiversion;
	}

	public UpgradeIncrResponse() {
		super();
	}

	public UpgradeIncrResponse(String servertime, String callid, String state,
			String note, String language, String timezone, String region,
			String apiversion,
			List<UpgradeIncrResponseUpgrade> upgradeIncrResponseUpgrades) {
		super();
		this.servertime = servertime;
		this.callid = callid;
		this.state = state;
		this.note = note;
		this.language = language;
		this.timezone = timezone;
		this.region = region;
		this.apiversion = apiversion;
		this.upgradeIncrResponseUpgrades = upgradeIncrResponseUpgrades;
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

	public List<UpgradeIncrResponseUpgrade> getUpgradeIncrResponseUpgrades() {
		return upgradeIncrResponseUpgrades;
	}

	public void setUpgradeIncrResponseUpgrades(
			List<UpgradeIncrResponseUpgrade> upgradeIncrResponseUpgrades) {
		this.upgradeIncrResponseUpgrades = upgradeIncrResponseUpgrades;
	}

	public UpgradeIncrResponseUpgrade getUpgradeIncrResponseUpgrade(int i) {

		if (upgradeIncrResponseUpgrades != null
				&& upgradeIncrResponseUpgrades.size() > i) {
			return upgradeIncrResponseUpgrades.get(i);
		}

		return null;
	}

	public String getDownloadid() {
		return downloadid;
	}

	public void setDownloadid(String downloadid) {
		this.downloadid = downloadid;
	}
}
