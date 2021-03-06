package com.siviton.huanapi.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.IContentObserver;
import android.net.Uri;
import android.net.ethernet.EthernetDevInfo;
import android.net.ethernet.EthernetManager;
import android.os.Handler;
import android.os.SystemProperties;
import android.provider.Downloads;
import android.provider.Settings;

import com.mstar.android.tvapi.common.TvManager;
import com.mstar.android.tvapi.common.exception.TvCommonException;
import com.siviton.huan.server.BootBroadcast;

public class HuanApi {
	public static final String TABLE_NAME = "huanitem";
	public static final String COLUMN_DEVICEID = "deviceid";
	public static final String COLUMN_DNUM = "dnum";
	public static final String COLUMN_DEVICEMODEL = "devicemodel";
	public static final String COLUMN_ACTIVEKEY = "activekey";
	public static final String COLUMN_DIDTOKEN = "didtoken";
	public static final String COLUMN_TOKEN = "token";
	public static final String COLUMN_HUANID = "huanid";
	public static final String COLUMN_LICENSETYPE = "licensetype";
	public static final String COLUMN_LICENSEDATA = "licensedata";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_DOWNLOAD_ID = "downloadid";
	public static final String COLUMN_UPDATE_STATE = "updatestate";
	private Context mContext;
	// database name
	public static final String DATABASE_NAME = "huanwangdata.db";
	// table name
	private static final String CONTENT_LABEL = "content://";
	// authority
	public static final String AUTHORITY = "com.siviton.huandatabase.pengb";
	private static final String MARK_SLASH = "/";
	// uri
	private static final String URI = CONTENT_LABEL + AUTHORITY + MARK_SLASH
			+ TABLE_NAME;
	public static final Uri CONTENT_URI = Uri.parse(URI);

	// https post
	private static final AllowAllHostnameVerifier HOSTNAME_VERIFIER = new AllowAllHostnameVerifier();
	private static X509TrustManager xtm = new X509TrustManager() {
		public void checkClientTrusted(X509Certificate[] chain, String authType) {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	};
	private static X509TrustManager[] xtmArray = new X509TrustManager[] { xtm };
	private static HttpsURLConnection conn = null;

	private UpgradeFileItem upgradeFileItem;

	private boolean isGetUpdate = false;
	private UpgradeIncrResponse mUpgradeIncrResponse;
	private HuanItemInfo huanItemInfo = null;
	private Handler mHandler = new Handler() {

		public void handleMessage(android.os.Message msg) {
		};
	};

	private HuanLoginListen mHuanLoginListen;
	public static final int STATE_DEVICEACTIVE = 1;
	public static final int STATE_DEVICEACTIVE_ISALREADYACTIVE = 11;
	public static final int STATE_DEVICEACTIVE_ERROR_COCDE = 12;
	public static final int STATE_DEVICEACTIVE_ACTIVE_SUCC = 13;
	public static final int STATE_DEVICEACTIVE_DNUMORACTIVE_NULL = 14;
	public static final int STATE_DEVICEACTIVE_JESON_EXCEPTION = 15;
	public static final int STATE_DEVICEACTIVE_NETCODE_ISNO200 = 16;
	public static final int STATE_DEVICEACTIVE_NET_EXCEPTION = 17;
	public static final int STATE_DEVICELOGIN = 2;
	public static final int STATE_DEVICELOGIN_ERROR_COCDE = 21;
	public static final int STATE_DEVICELOGIN_LOGIN_SUCC = 22;
	public static final int STATE_DEVICELOGIN_ACTIVEKEY_NULL = 23;
	public static final int STATE_DEVICELOGIN_JESON_EXCEPTION = 24;
	public static final int STATE_DEVICELOGIN_NETCODE_ISNO200 = 25;
	public static final int STATE_DEVICELOGIN_NETCODE_EXCEPTION = 26;
	public static final int STATE_USERLOGIN = 3;
	public static final int STATE_USERLOGIN_ERROR_COCDE = 31;
	public static final int STATE_USERLOGIN_LOGIN_SUCC = 32;
	public static final int STATE_USERLOGIN_HUANIDTOKEN_NULL = 33;
	public static final int STATE_USERLOGIN_JESON_EXCEPTION = 34;
	public static final int STATE_USERLOGIN_NETCODE_ISNO200 = 35;
	public static final int STATE_USERLOGIN_NETCODE_EXCEPTION = 36;

	public static final int STATE_GETUPDATEINFO = 4;
	public static final int STATE_GETUPDATEINFO_ISGET = 41;
	public static final int STATE_GETUPDATEINFO_ISOK = 42;
	public static final int STATE_GETUPDATEINFO_NETCODE_ISNO200 = 43;
	public static final int STATE_GETUPDATEINFO_NETCODE_EXCEPTION = 44;
	public static final int STATE_GETUPDATEADDRESS = 5;
	public static final int STATE_GETUPDATEADDRESS_ISGET = 51;
	public static final int STATE_GETUPDATEADDRESS_ISOK = 52;
	public static final int STATE_GETUPDATEADDRESS_NETCODE_ISNO200 = 53;
	public static final int STATE_GETUPDATEADDRESS_NETCODE_EXCEPTION = 54;

	public static final int STATE_DOWNLOAD = 6;
	public static final int STATE_DOWNLOAD_ISGET = 61;
	public static final int STATE_DOWNLOAD_ISOK = 62;
	public static final int STATE_DOWNLOAD_NETCODE_ISNO200 = 63;
	public static final int STATE_DOWNLOAD_NETCODE_EXCEPTION = 64;
	public static final String FILE_PATH = "/cache/update.zip";

	public HuanApi(Context mContext, HuanLoginListen huanLoginListen) {
		super();
		this.mContext = mContext;
		huanItemInfo = new HuanItemInfo();
		mHuanLoginListen = huanLoginListen;
		mContext.getContentResolver().registerContentObserver(CONTENT_URI,
				true, new HuanDataChange(null));
		Cursor cursor = mContext.getContentResolver().query(CONTENT_URI, null,
				null, null, null);
		if (cursor != null && cursor.moveToFirst()) {
			huanItemInfo.setActivekey(cursor.getString(cursor
					.getColumnIndex(COLUMN_ACTIVEKEY)));
			huanItemInfo.setDeviceid(cursor.getString(cursor
					.getColumnIndex(COLUMN_DEVICEID)));
			huanItemInfo.setDevicemodel(cursor.getString(cursor
					.getColumnIndex(COLUMN_DEVICEMODEL)));
			huanItemInfo.setDidtoken(cursor.getString(cursor
					.getColumnIndex(COLUMN_DIDTOKEN)));
			huanItemInfo.setDnum(cursor.getString(cursor
					.getColumnIndex(COLUMN_DNUM)));
			huanItemInfo.setHuanid(cursor.getString(cursor
					.getColumnIndex(COLUMN_HUANID)));
			huanItemInfo.setLicensedata(cursor.getString(cursor
					.getColumnIndex(COLUMN_LICENSEDATA)));
			huanItemInfo.setLicensetype(cursor.getString(cursor
					.getColumnIndex(COLUMN_LICENSETYPE)));
			huanItemInfo.setToken(cursor.getString(cursor
					.getColumnIndex(COLUMN_TOKEN)));
			huanItemInfo.setDownloadid(cursor.getString(cursor
					.getColumnIndex(COLUMN_DOWNLOAD_ID)));
			huanItemInfo.setUpdatestate(cursor.getString(cursor
					.getColumnIndex(COLUMN_UPDATE_STATE)));

		}
		if (cursor != null) {
			cursor.close();
		}
		System.out.println("==pengbdata==HuanApi======"
				+ huanItemInfo.getDeviceid() + "==="
				+ huanItemInfo.getDevicemodel() + "==="
				+ huanItemInfo.getDidtoken() + "==="
				+ huanItemInfo.getActivekey() + "===" + huanItemInfo.getDnum());
		if (huanItemInfo.getDeviceid() == null
				|| huanItemInfo.getDevicemodel() == null) {
			huanItemInfo.setDeviceid(getdeviceid());
			huanItemInfo.setDevicemodel(getdevmodel());
		} else {
			String did = huanItemInfo.getDeviceid().trim();
			String client = huanItemInfo.getDevicemodel().trim();
			if (did == null || did.length() < 1) {
				huanItemInfo.setDeviceid(getdeviceid());
			}
			if (client == null || client.length() < 1) {
				huanItemInfo.setDevicemodel(getdevmodel());
			}
		}

	}

	class HuanDataChange extends ContentObserver {

		public HuanDataChange(Handler handler) {
			super(handler);
			// TODO Auto-generated constructor stub
		}

		@Override
		public boolean deliverSelfNotifications() {
			// TODO Auto-generated method stub
			return super.deliverSelfNotifications();
		}

		@Override
		public IContentObserver getContentObserver() {
			// TODO Auto-generated method stub
			return super.getContentObserver();
		}

		@Override
		public void onChange(boolean selfChange) {
			// TODO Auto-generated method stub
			Cursor cursor = mContext.getContentResolver().query(CONTENT_URI,
					null, null, null, null);

			if (cursor != null && cursor.moveToFirst()) {
				huanItemInfo.setActivekey(cursor.getString(cursor
						.getColumnIndex(COLUMN_ACTIVEKEY)));
				huanItemInfo.setDeviceid(cursor.getString(cursor
						.getColumnIndex(COLUMN_DEVICEID)));
				huanItemInfo.setDevicemodel(cursor.getString(cursor
						.getColumnIndex(COLUMN_DEVICEMODEL)));
				huanItemInfo.setDidtoken(cursor.getString(cursor
						.getColumnIndex(COLUMN_DIDTOKEN)));
				huanItemInfo.setDnum(cursor.getString(cursor
						.getColumnIndex(COLUMN_DNUM)));
				huanItemInfo.setHuanid(cursor.getString(cursor
						.getColumnIndex(COLUMN_HUANID)));
				huanItemInfo.setLicensedata(cursor.getString(cursor
						.getColumnIndex(COLUMN_LICENSEDATA)));
				huanItemInfo.setLicensetype(cursor.getString(cursor
						.getColumnIndex(COLUMN_LICENSETYPE)));
				huanItemInfo.setToken(cursor.getString(cursor
						.getColumnIndex(COLUMN_TOKEN)));
				huanItemInfo.setDownloadid(cursor.getString(cursor
						.getColumnIndex(COLUMN_DOWNLOAD_ID)));
				huanItemInfo.setUpdatestate(cursor.getString(cursor
						.getColumnIndex(COLUMN_UPDATE_STATE)));
			}
			if (cursor != null) {
				cursor.close();
			}
//			System.out.println("==pengbdata==HuanDataChange======"
//					+ huanItemInfo.getDeviceid() + "==="
//					+ huanItemInfo.getDevicemodel() + "==="
//					+ huanItemInfo.getDidtoken() + "==="
//					+ huanItemInfo.getActivekey() + "==="
//					+ huanItemInfo.getDnum());
			super.onChange(selfChange);
		}

		@Override
		public IContentObserver releaseContentObserver() {
			// TODO Auto-generated method stub
			return super.releaseContentObserver();
		}

	}

	private HuanItemInfo getHuanItemInfo() {
		return huanItemInfo;
	}

	public void loginTer(Handler handler) {

		if (mContext == null) {
			return;
		}

	}

	private String getWireMacAddress() {
		EthernetDevInfo devInfo = EthernetManager.getInstance()
				.getSavedConfig();
		if (devInfo == null) {
			return "00:00:00:00:00:00";
		} else {
			return devInfo.getMacAddress();
		}
	}

	public void DeviceActive() {
		if (huanItemInfo.getDnum() != null
				&& huanItemInfo.getActivekey() != null) {
			mHuanLoginListen.StateChange(STATE_DEVICEACTIVE,
					STATE_DEVICEACTIVE_ISALREADYACTIVE, "is already active",
					null, null, null);
			return;
		}
		new Thread() {
			public void run() {
				JSONObject jsonObject2 = new JSONObject();
				try {
					jsonObject2.putOpt("deviceid", getdeviceid());
					jsonObject2.putOpt("devmodel", getdevmodel());
					jsonObject2.putOpt("devserial", getdevserial());
					jsonObject2.putOpt("devmac", getdevmac());
				} catch (JSONException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				JSONObject jsonObject = new JSONObject();
				try {
					jsonObject.putOpt("action", "DeviceActive");
					jsonObject.putOpt("locale", getlocale());
					jsonObject.putOpt("timezone", gettimezone());
					jsonObject.putOpt("region", getregion());
					jsonObject.putOpt("device", jsonObject2);
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("===pengbdata==DeviceActive===="
						+ jsonObject.toString());
				try {
					byte[] entity = jsonObject.toString().getBytes();
					URL url = new URL(getDeviceUrl());
					conn = (HttpsURLConnection) url.openConnection();
					if (conn instanceof HttpsURLConnection) {
						// Trust all certificates
						SSLContext context = SSLContext.getInstance("SSL");
						context.init(new KeyManager[0], xtmArray,
								new SecureRandom());
						SSLSocketFactory socketFactory = context
								.getSocketFactory();
						((HttpsURLConnection) conn)
								.setSSLSocketFactory(socketFactory);
						((HttpsURLConnection) conn)
								.setHostnameVerifier(HOSTNAME_VERIFIER);
					}
					conn.setConnectTimeout(5 * 1000);
					conn.setRequestMethod("POST");
					conn.setDoOutput(true);// 允许输出数据
					conn.setRequestProperty("Content-Type",
							"application/x-www-form-urlencoded");
					conn.setRequestProperty("Content-Length",
							String.valueOf(entity.length));
					OutputStream outStream = conn.getOutputStream();
					outStream.write(entity);
					outStream.flush();
					outStream.close();
					if (conn.getResponseCode() == 200) {
						BufferedReader in = new BufferedReader(
								new InputStreamReader(conn.getInputStream()));
						String line = "";
						StringBuilder stringBuffer = new StringBuilder();
						while ((line = in.readLine()) != null) {
							stringBuffer.append("" + line + "\n");
							System.out
									.println("==pengbdata==DeviceActive======"
											+ line);
						}
						in.close();

						JSONObject object = new JSONObject(""
								+ stringBuffer.toString());

						JSONObject object2 = null;
						try {
							object2 = object.getJSONObject("error");
							String code = object2.getString("code");
							String info = object2.getString("info");
							if (!code.equals("0")) {
								mHuanLoginListen.StateChange(
										STATE_DEVICEACTIVE,
										STATE_DEVICEACTIVE_ERROR_COCDE, ""
												+ info, null, null, null);
							} else {
								object2 = object.getJSONObject("device");
								String dnum = object2.getString("dnum");
								String activekey = object2
										.getString("activekey");
								if (dnum != null && activekey != null) {
									huanItemInfo.setDnum(dnum);
									huanItemInfo.setActivekey(activekey);
									huanItemInfo
											.setDidtoken(getMD5(getdeviceid()
													+ getactivekey()));
									System.out
											.println("==pengbdata==DeviceActive======"
													+ dnum.getBytes().length
													+ "=="
													+ huanItemInfo
															.getDeviceid()
													+ "==="
													+ huanItemInfo
															.getDevicemodel()
													+ "==="
													+ huanItemInfo
															.getDidtoken()
													+ "==="
													+ huanItemInfo
															.getActivekey()
													+ "==="
													+ huanItemInfo.getDnum());
									updateData();
									mHuanLoginListen.StateChange(
											STATE_DEVICEACTIVE,
											STATE_DEVICEACTIVE_ACTIVE_SUCC,
											"active succ", null, null, null);
								} else {
									// 返回数据为空
									mHuanLoginListen
											.StateChange(
													STATE_DEVICEACTIVE,
													STATE_DEVICEACTIVE_DNUMORACTIVE_NULL,
													"dnum or activekey is null",
													null, null, null);
								}
							}
						} catch (Exception e) {
							// TODO: handle exception
							mHuanLoginListen.StateChange(STATE_DEVICEACTIVE,
									STATE_DEVICEACTIVE_JESON_EXCEPTION,
									e.toString(), null, null, null);
						}

					} else {
						// 激活失败
						mHuanLoginListen.StateChange(STATE_DEVICEACTIVE,
								STATE_DEVICEACTIVE_NETCODE_ISNO200,
								"active net error", null, null, null);
					}

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					mHuanLoginListen.StateChange(STATE_DEVICEACTIVE,
							STATE_DEVICEACTIVE_NET_EXCEPTION, e.toString(),
							null, null, null);
				}

			};

		}.start();
	}

	private void updateData() {
		if (huanItemInfo == null || mContext == null) {

			return;
		}
		ContentValues values = new ContentValues();
		values.put(HuanApi.COLUMN_DEVICEID, getdeviceid());
		values.put(HuanApi.COLUMN_DNUM, getdnum());
		values.put(HuanApi.COLUMN_DEVICEMODEL, getdevmodel());
		values.put(HuanApi.COLUMN_ACTIVEKEY, getactivekey());
		values.put(HuanApi.COLUMN_DIDTOKEN, getdidtoken());
		values.put(HuanApi.COLUMN_TOKEN, getToken());
		values.put(HuanApi.COLUMN_HUANID, getHuanid());
		values.put(HuanApi.COLUMN_LICENSETYPE, getLicensetype());
		values.put(HuanApi.COLUMN_LICENSEDATA, getLicensedata());
		int i = mContext.getContentResolver().update(CONTENT_URI, values, null,
				null);
		System.out.println("==pengbdata==updateData======"
				+ huanItemInfo.getDeviceid() + "==="
				+ huanItemInfo.getDevicemodel() + "==="
				+ huanItemInfo.getDidtoken() + "==="
				+ huanItemInfo.getActivekey() + "===" + huanItemInfo.getDnum()
				+ "======ii=====" + i);
	}

	public void DeviceLogin() {

		new Thread() {

			public void run() {

				JSONObject jsonObject2 = new JSONObject();
				try {
					jsonObject2.putOpt("dnum", getdnum());
					jsonObject2.putOpt("didtoken", getdidtoken());
					jsonObject2.putOpt("activekey", getactivekey());
				} catch (JSONException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				JSONObject jsonObject3 = new JSONObject();
				try {
					jsonObject3.putOpt("ostype", getostype());
					jsonObject3.putOpt("osversion", getosversion());
					jsonObject3.putOpt("kernelversion", getkernelversion());
					jsonObject3.putOpt("webinfo", getwebinfo());
					jsonObject3.putOpt("javainfo", getjavainfo());
					jsonObject3.putOpt("flashinfo", getflashinfo());
				} catch (JSONException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				JSONObject jsonObject = new JSONObject();
				try {
					jsonObject.putOpt("action", "DeviceLogin");
					jsonObject.putOpt("device", jsonObject2);
					jsonObject.putOpt("param", jsonObject3);
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					byte[] entity = jsonObject.toString().getBytes();
					URL url = new URL(getDeviceUrl());
					HttpsURLConnection connections = (HttpsURLConnection) url
							.openConnection();
					if (connections instanceof HttpsURLConnection) {
						// Trust all certificates
						SSLContext context = SSLContext.getInstance("SSL");
						context.init(new KeyManager[0], xtmArray,
								new SecureRandom());
						SSLSocketFactory socketFactory = context
								.getSocketFactory();
						((HttpsURLConnection) connections)
								.setSSLSocketFactory(socketFactory);
						((HttpsURLConnection) connections)
								.setHostnameVerifier(HOSTNAME_VERIFIER);
					}
					connections.setConnectTimeout(5 * 1000);
					connections.setRequestMethod("POST");
					connections.setDoOutput(true);// 允许输出数据
					connections.setRequestProperty("Content-Type",
							"application/x-www-form-urlencoded");
					connections.setRequestProperty("Content-Length",
							String.valueOf(entity.length));
					OutputStream outStream = connections.getOutputStream();
					outStream.write(entity);
					outStream.flush();
					outStream.close();
					if (connections.getResponseCode() == 200) {
						BufferedReader in = new BufferedReader(
								new InputStreamReader(
										connections.getInputStream()));
						String line = "";
						StringBuilder stringBuffer = new StringBuilder();
						while ((line = in.readLine()) != null) {
							stringBuffer.append("" + line + "\n");
							System.out.println("==pengbdata==DeviceLogin====="
									+ line);
						}
						in.close();

						JSONObject object = new JSONObject(""
								+ stringBuffer.toString());

						JSONObject object2 = null;
						try {
							object2 = object.getJSONObject("error");
							String code = object2.getString("code");
							String info = object2.getString("info");
							if (!code.equals("0")) {
								mHuanLoginListen.StateChange(STATE_DEVICELOGIN,
										STATE_DEVICELOGIN_ERROR_COCDE, ""
												+ info, null, null, null);
							} else {
								object2 = object.getJSONObject("device");
								String activekey = object2
										.getString("activekey");
								if (activekey != null) {
									huanItemInfo.setActivekey(activekey);
									huanItemInfo
											.setDidtoken(getMD5(getdeviceid()
													+ getactivekey()));
									System.out
											.println("==pengbdata==DeviceLogin======"
													+ huanItemInfo
															.getDeviceid()
													+ "==="
													+ huanItemInfo
															.getDevicemodel()
													+ "==="
													+ huanItemInfo
															.getDidtoken()
													+ "==="
													+ huanItemInfo
															.getActivekey()
													+ "==="
													+ huanItemInfo.getDnum());
									updateData();
									mHuanLoginListen.StateChange(
											STATE_DEVICELOGIN,
											STATE_DEVICELOGIN_LOGIN_SUCC,
											"DeviceLogin succ", null, null,
											null);
								} else {
									// 返回数据为空
									mHuanLoginListen.StateChange(
											STATE_DEVICELOGIN,
											STATE_DEVICELOGIN_ACTIVEKEY_NULL,
											"dnum or activekey is null", null,
											null, null);
								}
							}
						} catch (Exception e) {
							// TODO: handle exception
							mHuanLoginListen.StateChange(STATE_DEVICELOGIN,
									STATE_DEVICELOGIN_JESON_EXCEPTION,
									e.toString(), null, null, null);
						}

					} else {
						// 激活失败
						mHuanLoginListen
								.StateChange(STATE_DEVICELOGIN,
										STATE_DEVICELOGIN_NETCODE_ISNO200,
										"DeviceLogin   network error", null,
										null, null);
					}

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					mHuanLoginListen.StateChange(STATE_DEVICELOGIN,
							STATE_DEVICELOGIN_NETCODE_EXCEPTION,
							"DeviceLogin   network error" + e.toString(), null,
							null, null);
				}

			};

		}.start();

	}

	public void AutoLoginUser() {

		new Thread() {

			public void run() {

				JSONObject jsonObject2 = new JSONObject();
				try {
					jsonObject2.putOpt("dnum", getdnum());
					jsonObject2.putOpt("didtoken", getdidtoken());
				} catch (JSONException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				JSONObject jsonObject = new JSONObject();
				try {
					jsonObject.putOpt("action", "AutoLoginUser");
					jsonObject.putOpt("device", jsonObject2);
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					byte[] entity = jsonObject.toString().getBytes();
					URL url = new URL(getDeviceUrl());
					HttpsURLConnection connections = (HttpsURLConnection) url
							.openConnection();
					if (connections instanceof HttpsURLConnection) {
						// Trust all certificates
						SSLContext context = SSLContext.getInstance("SSL");
						context.init(new KeyManager[0], xtmArray,
								new SecureRandom());
						SSLSocketFactory socketFactory = context
								.getSocketFactory();
						((HttpsURLConnection) connections)
								.setSSLSocketFactory(socketFactory);
						((HttpsURLConnection) connections)
								.setHostnameVerifier(HOSTNAME_VERIFIER);
					}
					connections.setConnectTimeout(5 * 1000);
					connections.setRequestMethod("POST");
					connections.setDoOutput(true);// 允许输出数据
					connections.setRequestProperty("Content-Type",
							"application/x-www-form-urlencoded");
					connections.setRequestProperty("Content-Length",
							String.valueOf(entity.length));
					OutputStream outStream = connections.getOutputStream();
					outStream.write(entity);
					outStream.flush();
					outStream.close();
					if (connections.getResponseCode() == 200) {
						BufferedReader in = new BufferedReader(
								new InputStreamReader(
										connections.getInputStream()));
						String line = "";
						StringBuilder stringBuffer = new StringBuilder();
						while ((line = in.readLine()) != null) {
							stringBuffer.append("" + line + "\n");
							System.out
									.println("==pengbdata==AutoLoginUser====="
											+ line);
						}
						in.close();

						JSONObject object = new JSONObject(""
								+ stringBuffer.toString());

						JSONObject object2 = null;
						try {
							object2 = object.getJSONObject("error");
							String code = object2.getString("code");
							String info = object2.getString("info");
							if (!code.equals("0")) {
								mHuanLoginListen.StateChange(STATE_USERLOGIN,
										STATE_USERLOGIN_ERROR_COCDE, "" + info,
										null, null, null);
							} else {
								object2 = object.getJSONObject("user");
								String huanid = object2.getString("huanid");
								String token = object2.getString("token");
								if (token != null && huanid != null) {
									huanItemInfo.setToken(token);
									huanItemInfo.setHuanid(huanid);
									updateData();
									mHuanLoginListen.StateChange(
											STATE_USERLOGIN,
											STATE_USERLOGIN_LOGIN_SUCC,
											"user succ", null, null, null);
								} else {
									// 返回数据为空
									mHuanLoginListen.StateChange(
											STATE_USERLOGIN,
											STATE_USERLOGIN_HUANIDTOKEN_NULL,
											"huanid or huanid is null", null,
											null, null);
								}
							}
						} catch (Exception e) {
							// TODO: handle exception
							mHuanLoginListen.StateChange(STATE_USERLOGIN,
									STATE_USERLOGIN_JESON_EXCEPTION,
									e.toString(), null, null, null);
						}

					} else {
						// 激活失败
						mHuanLoginListen.StateChange(STATE_USERLOGIN,
								STATE_USERLOGIN_NETCODE_ISNO200,
								"user   network error", null, null, null);
					}

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					mHuanLoginListen.StateChange(STATE_USERLOGIN,
							STATE_USERLOGIN_NETCODE_EXCEPTION,
							"user   network error" + e.toString(), null, null,
							null);
				}

			};

		}.start();

	}

	private String getMD5(String string) {

		byte[] hash = null;
		try {
			hash = MessageDigest.getInstance("MD5").digest(
					string.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		StringBuilder hex = new StringBuilder(hash.length * 2);
		for (byte b : hash) {
			if ((b & 0xFF) < 0x10)
				hex.append("0");
			hex.append(Integer.toHexString(b & 0xFF));
		}

		return hex.toString();
	}

	public String getXML() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		buffer.append("<upgradeIncrRequest>");
		buffer.append("<parameter>");
		buffer.append("<callid>" + getcallid() + "</callid>");
		buffer.append("<language>" + getlanguage() + "</language>");
		buffer.append("<timezone>" + gettimezone() + "</timezone>");
		buffer.append("<region>" + getregion() + "</region>");
		buffer.append("<client>");
		buffer.append("<dnum>" + getdnum() + "</dnum>");
		buffer.append("<didtoken>" + getdidtoken() + "</didtoken>");
		buffer.append("<devmodel>" + getdevmodel() + "</devmodel>");
		buffer.append("<systemver>" + getsystemver() + "</systemver>");
		buffer.append("<packagever>" + getpackagever() + "</packagever>");
		buffer.append("</client>");
		buffer.append("</parameter>");
		buffer.append("<app>");
		buffer.append("<appid>" + getappid() + "</appid>");
		buffer.append("<ver>" + getver() + "</ver>");
		buffer.append("<verid>" + getverid() + "</verid>");
		buffer.append("</app>");
		buffer.append("<apiversion>" + getapiversion() + "</apiversion>");
		buffer.append("</upgradeIncrRequest>");

		return buffer.toString();
	}

	// pengbadd
	public String getFileAddressXML() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
		buffer.append("<fileRequest>\n");
		buffer.append("<parameter>\n");

		buffer.append("<callid>" + getFileAddresscallid() + "</callid>\n");
		buffer.append("<client>\n");
		buffer.append("<dnum>" + huanItemInfo.getDnum() + "</dnum>\n");
		buffer.append("<didtoken>" + huanItemInfo.getDidtoken()
				+ "</didtoken>\n");
		buffer.append("<devmodel>" + huanItemInfo.getDevicemodel()
				+ "</devmodel>\n");
		buffer.append("</client>\n");
		buffer.append("</parameter>\n");
		buffer.append("<app>\n");
		buffer.append("<appid>" + getFileAddressappid() + "</appid>\n");
		buffer.append("<verid>" + getFileAddressverid() + "</verid>\n");
		buffer.append("</app>\n");
		buffer.append("<apiversion>" + getFileAddressapiversion()
				+ "</apiversion>\n");
		buffer.append("</fileRequest>");

		return buffer.toString();
	}

	public void getUpdateinfo() {
		new Thread() {
			public void run() {
				try {

					byte[] entity = getXML().toString().getBytes();
					System.out.println("===pengbdata=getUpdateinfo"
							+ getXML().toString());
					URL url = new URL(getUpdateUrl());
					HttpURLConnection httpConnection = (HttpURLConnection) url
							.openConnection();
					httpConnection.setConnectTimeout(5 * 1000);
					httpConnection.setRequestMethod("POST");
					httpConnection.setDoOutput(true);// 允许输出数据
					httpConnection.setRequestProperty("Content-Type",
							"application/xml");
					httpConnection.setRequestProperty("Content-Length",
							String.valueOf(entity.length));
					OutputStream outStream = httpConnection.getOutputStream();
					outStream.write(entity);
					outStream.flush();
					outStream.close();
					if (httpConnection.getResponseCode() == 200) {
						InputStream inputStream = httpConnection
								.getInputStream();
						//
						// BufferedReader in = new BufferedReader(
						// new InputStreamReader(inputStream));
						// String line = "";
						// StringBuilder stringBuffer = new StringBuilder();
						// while ((line = in.readLine()) != null) {
						// stringBuffer.append("" + line + "\n");
						// System.out.println("==pengbdata==4r45454====="
						// + line);
						// }

						UpgradeIncrResponseSaxXml mUpgradeIncrResponseSaxXml = new UpgradeIncrResponseSaxXml(
								mContext);
						mUpgradeIncrResponseSaxXml.Par(inputStream);
						inputStream.close();
						mUpgradeIncrResponse = mUpgradeIncrResponseSaxXml
								.getmUpgradeIncrResponse();
						mHuanLoginListen.StateChange(STATE_GETUPDATEINFO,
								STATE_GETUPDATEINFO_ISOK,
								"STATE_GETUPDATEINFO_ISOK",
								mUpgradeIncrResponse, null, null);
					} else {
						mHuanLoginListen.StateChange(STATE_GETUPDATEINFO,
								STATE_GETUPDATEINFO_NETCODE_ISNO200,
								"STATE_GETUPDATEINFO_NETCODE_ISNO200", null,
								null, null);
					}

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					mHuanLoginListen.StateChange(STATE_GETUPDATEINFO,
							STATE_GETUPDATEINFO_NETCODE_EXCEPTION,
							e.toString(), null, null, null);
				}
			};

		}.start();

	}

	public void getUpdateFileAddress() {
		new Thread() {
			public void run() {
				// isGetUpdate = true;
				try {
					byte[] entity = getFileAddressXML().toString().getBytes();
					URL url = new URL(getUpdateUrl());
					HttpURLConnection httpConnection = (HttpURLConnection) url
							.openConnection();
					httpConnection.setConnectTimeout(5 * 1000);
					httpConnection.setRequestMethod("POST");
					httpConnection.setDoOutput(true);// 允许输出数据
					httpConnection.setRequestProperty("Content-Type",
							"application/xml");
					httpConnection.setRequestProperty("Content-Length",
							String.valueOf(entity.length));
					OutputStream outStream = httpConnection.getOutputStream();
					outStream.write(entity);
					outStream.flush();
					outStream.close();
					if (httpConnection.getResponseCode() == 200) {
						InputStream inputStream = httpConnection
								.getInputStream();
						UpgradeFileAddressSaxXml saxXml = new UpgradeFileAddressSaxXml(
								mContext);
						saxXml.Par(inputStream);
						inputStream.close();
						upgradeFileItem = saxXml.getmUpgradeFileItem();
						mHuanLoginListen
								.StateChange(STATE_GETUPDATEADDRESS,
										STATE_GETUPDATEADDRESS_ISOK,
										"STATE_GETUPDATEADDRESS_ISOK", null,
										null, null);

					} else {
						mHuanLoginListen.StateChange(STATE_GETUPDATEADDRESS,
								STATE_GETUPDATEADDRESS_NETCODE_ISNO200,
								"STATE_GETUPDATEADDRESS_NETCODE_ISNO200", null,
								null, null);
						System.out.println("=========network error======");
					}

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					mHuanLoginListen.StateChange(STATE_GETUPDATEADDRESS,
							STATE_GETUPDATEADDRESS_NETCODE_EXCEPTION,
							e.toString(), null, null, null);
				}
			};

		}.start();

	}

	// public String getDeviceid() {
	// return huanItemInfo.getDeviceid();
	// }
	

	public void downloadUpdateFile(final String path) {
		new Thread() {
			public void run() {
				try {
					File updatefile = new File(FILE_PATH);

					if (updatefile.exists()) {
						updatefile.delete();
					}
					String url = path;
					System.out.println("===url" + url); // modified
					HttpClient client = new DefaultHttpClient();
					HttpGet get = new HttpGet(url);
					HttpResponse responsedownload = client.execute(get);
					if (responsedownload.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
						ContentValues values = new ContentValues();
						values.put(Downloads.Impl.COLUMN_URI, url);
						values.put(Downloads.Impl.COLUMN_MIME_TYPE,
								"application/x-compressed");
						values.put(
								Downloads.Impl.COLUMN_DESTINATION,
								Downloads.Impl.DESTINATION_SYSTEMCACHE_PARTITION);
						values.put(Downloads.Impl.COLUMN_FILE_NAME_HINT,
								FILE_PATH);
						values.put(Downloads.Impl.COLUMN_TITLE, "update");

						values.put(Downloads.Impl.COLUMN_VISIBILITY,
								Downloads.Impl.VISIBILITY_VISIBLE);
						values.put(Downloads.Impl.COLUMN_NOTIFICATION_PACKAGE,
								mContext.getPackageName());
						values.put(Downloads.Impl.COLUMN_NOTIFICATION_CLASS,
								BootBroadcast.class.getName());
						Uri l = mContext.getContentResolver().insert(
								Downloads.Impl.CONTENT_URI, values);
						long id = Long.parseLong(l.getLastPathSegment());
						String string = String.valueOf(id);
						mHuanLoginListen.StateChange(STATE_DOWNLOAD,
								STATE_DOWNLOAD_ISOK, string, null, null, null);
						System.out.println("===url" + string); // modified
					} else {
						System.out.println("==========pengbhuan===="+responsedownload.getStatusLine().getStatusCode());
						mHuanLoginListen.StateChange(STATE_DOWNLOAD,
								STATE_DOWNLOAD_NETCODE_ISNO200,
								"STATE_DOWNLOAD_NETCODE_ISNO200", null, null,
								null);
					}

				} catch (Exception e) {
					mHuanLoginListen.StateChange(STATE_DOWNLOAD,
							STATE_DOWNLOAD_NETCODE_EXCEPTION, e.toString(),
							null, null, null);
				}

			};
		}.start();

	}
	private String getFileAddresscallid() {
		return "1234567";
	}

	private String getFileAddressappid() {
		return null;
	}

	private String getFileAddressverid() {
		return null;
	}

	private String getFileAddressapiversion() {
		return "1.0";
	}

	public String getDnum() {
		return huanItemInfo.getDnum();
	}

	// public String getDevicemodel() {
	// return huanItemInfo.getDevicemodel();
	// }

	public String getActivekey() {
		return huanItemInfo.getActivekey();
	}

	public String getDidtoken() {
		return huanItemInfo.getDidtoken();
	}

	public String getToken() {
		return huanItemInfo.getToken();
	}

	public String getHuanid() {
		return huanItemInfo.getHuanid();
	}

	public String getLicensetype() {
		return huanItemInfo.getLicensetype();
	}

	public String getLicensedata() {
		return huanItemInfo.getLicensedata();
	}
	private String getlocale() {

		return "zh_CN";
	}

	private String gettimezone() {

		return "+0800";
	}

	private String getregion() {

		return "cn";
	}

	// private final String HUAN_URI = "https://pcuser.cedock.com/uc/json";
	// private String deviceid = "446015881a825f2d05ab0108337e150d0fc7dc79";
	// private String devmodel = "CH-HXE4B-DTV-00-00";
	// private static final String UPDATE_URI =
	// "http://118.194.161.82:8080/service/upmp/upgradeIncrInterface";
	//
	// private static final String UPDATEFILE_URI =
	// "http://118.194.161.82:8080/service/upmp/files";

	private String getDeviceUrl() {

		return "https://pcuser.cedock.com/uc/json";

	}

	private String getUpdateUrl() {

		return "http://118.194.161.82:8080/service/upmp/upgradeIncrInterface";

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

	private String getdevserial() {

		return null;
	}

	private String getdevmac() {
		String address = getWireMacAddress();
		char[] buffer = new char[address.length()];
		address.getChars(0, address.length(), buffer, 0);
		String strMac = "" + buffer[0] + buffer[1] + buffer[3] + buffer[4]
				+ buffer[6] + buffer[7] + buffer[9] + buffer[10] + buffer[12]
				+ buffer[13] + buffer[15] + buffer[16];
		return address;
	}

	private String getdnum() {

		return huanItemInfo.getDnum();
	}

	private String getdidtoken() {

		return huanItemInfo.getDidtoken();
	}

	private String getactivekey() {

		return huanItemInfo.getActivekey();
	}

	private String getostype() {

		return null;
	}

	private String getosversion() {

		return null;
	}

	private String getkernelversion() {

		return null;
	}

	private String getwebinfo() {

		return null;
	}

	private String getjavainfo() {

		return null;
	}

	private String getflashinfo() {

		return null;
	}

	private String getcallid() {

		return "0";
	}

	private String getlanguage() {

		return "zh_CN";
	}

	private String getsystemver() {

		return "1.0";
	}

	private String getpackagever() {

		return null;
	}

	private String getappid() {
		return "CH_XE4B(SVT6369)_AU_32B4500_M320F12-D3-L（G4）";
	}

	private String getver() {
		return  "V00.0001";
	}

	private String getverid() {

		return null;
	}

	private String getapiversion() {

		return "1.0";
	}
	// SystemProperties.get("ro.product.Module"),
	// SystemProperties.get("ro.product.SWName"),
	// SystemProperties.get("ro.product.SoftwareVersion"),
	// SystemProperties.get("ro.product.HWVersion"),
}
