package com.siviton.huanapi.data;

public class UpdateItem {
	private String downloadID;
	private int updateState;
	private String type;
	private String appid;
	private String title;
	private String apptype;
	private String version;
	private String verid;
	private String description;
	private String minicon;
	private String midicon;
	private String fileurl;
	private String size;
	private String md5;
	private String increment;
	private String appendver;
	private String note;
	public UpdateItem(String downloadID, int updateState, String type,
			String appid, String title, String apptype, String version,
			String verid, String description, String minicon, String midicon,
			String fileurl, String size, String md5, String increment,
			String appendver, String note) {
		super();
		this.downloadID = downloadID;
		this.updateState = updateState;
		this.type = type;
		this.appid = appid;
		this.title = title;
		this.apptype = apptype;
		this.version = version;
		this.verid = verid;
		this.description = description;
		this.minicon = minicon;
		this.midicon = midicon;
		this.fileurl = fileurl;
		this.size = size;
		this.md5 = md5;
		this.increment = increment;
		this.appendver = appendver;
		this.note = note;
	}
	public UpdateItem() {
		super();
	}
	public String getDownloadID() {
		return downloadID;
	}
	public void setDownloadID(String downloadID) {
		this.downloadID = downloadID;
	}
	public int getUpdateState() {
		return updateState;
	}
	public void setUpdateState(int updateState) {
		this.updateState = updateState;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getApptype() {
		return apptype;
	}
	public void setApptype(String apptype) {
		this.apptype = apptype;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getVerid() {
		return verid;
	}
	public void setVerid(String verid) {
		this.verid = verid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMinicon() {
		return minicon;
	}
	public void setMinicon(String minicon) {
		this.minicon = minicon;
	}
	public String getMidicon() {
		return midicon;
	}
	public void setMidicon(String midicon) {
		this.midicon = midicon;
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
	public String getIncrement() {
		return increment;
	}
	public void setIncrement(String increment) {
		this.increment = increment;
	}
	public String getAppendver() {
		return appendver;
	}
	public void setAppendver(String appendver) {
		this.appendver = appendver;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

}
