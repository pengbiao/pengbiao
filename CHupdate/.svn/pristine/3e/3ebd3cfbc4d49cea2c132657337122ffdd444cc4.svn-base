package com.siviton.huanapi.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.content.Context;
import android.util.Log;

public class UpgradeIncrResponseSaxXml {

	private SAXParser saxParser;
	private Context context;
	private SAXParserFactory saxParserFactory;
	private XmlHandler xmlHandler;
	private UpgradeIncrResponse mUpgradeIncrResponse;
	private List<UpgradeIncrResponseUpgrade> mUpgradeIncrResponseUpgrades;
	private UpgradeIncrResponseUpgrade mUpgradeIncrResponseUpgrade;
	private boolean isUpgrade;

	public UpgradeIncrResponseSaxXml(Context context) {
		super();
		this.context = context;
		saxParserFactory = SAXParserFactory.newInstance();

		try {
			saxParser = saxParserFactory.newSAXParser();
			xmlHandler = new XmlHandler();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Par(InputStream inputStream) {

		try {
			mUpgradeIncrResponse = new UpgradeIncrResponse();
			mUpgradeIncrResponseUpgrades = new ArrayList<UpgradeIncrResponseUpgrade>();
			mUpgradeIncrResponse
					.setUpgradeIncrResponseUpgrades(mUpgradeIncrResponseUpgrades);
			saxParser.parse(inputStream, xmlHandler);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * ������
	 */

	class XmlHandler extends DefaultHandler {
		String info = null;

		/**
		 * Ԫ�ؿ�ʼ
		 */
		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			info = null;
			Log.v("test", "Start pengb" + localName);
			if (localName.equals("servertime")) {
			} else if (localName.equals("callid")) {
			} else if (localName.equals("state")) {
			} else if (localName.equals("note")) {
			} else if (localName.equals("language")) {
			} else if (localName.equals("timezone")) {
			} else if (localName.equals("region")) {
			} else if (localName.equals("upgrade")) {
				isUpgrade=true;
				mUpgradeIncrResponseUpgrade = new UpgradeIncrResponseUpgrade();
			} else if (localName.equals("type")) {
			} else if (localName.equals("appid")) {
			} else if (localName.equals("apptype")) {
			} else if (localName.equals("title")) {
			} else if (localName.equals("version")) {
			} else if (localName.equals("verid")) {
			} else if (localName.equals("description")) {
			} else if (localName.equals("minicon")) {
			} else if (localName.equals("midicon")) {
			} else if (localName.equals("fileurl")) {
			} else if (localName.equals("size")) {
			} else if (localName.equals("md5")) {
			} else if (localName.equals("increment")) {
			} else if (localName.equals("appendver")) {
			} else if (localName.equals("note")) {
			} else if (localName.equals("apiversion")) {
			} else {
			}

			super.startElement(uri, localName, qName, attributes);
		}

		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			Log.v("test", "Start pengb end  " + localName);
			if (localName.equals("servertime")) {
				mUpgradeIncrResponse.setServertime(info);
				Log.v("test", "Start pengb end write  setServertime  " + info);
			} else if (localName.equals("callid")) {
				mUpgradeIncrResponse.setCallid(info);
				Log.v("test", "Start pengb end write  setCallid  " + info);
			} else if (localName.equals("state")) {
				mUpgradeIncrResponse.setState(info);
				Log.v("test", "Start pengb end write  setState  " + info);
			} else if (localName.equals("note")) {
				if (isUpgrade) {
					mUpgradeIncrResponseUpgrade.setNote(info);
				}else {
					mUpgradeIncrResponse.setNote(info);
				}
				
				Log.v("test", "Start pengb end write  setNote  " + info);
			} else if (localName.equals("language")) {
				mUpgradeIncrResponse.setLanguage(info);
				Log.v("test", "Start pengb end write  setLanguage  " + info);
			} else if (localName.equals("timezone")) {
				mUpgradeIncrResponse.setTimezone(info);
				Log.v("test", "Start pengb end write  setTimezone  " + info);
			} else if (localName.equals("region")) {
				mUpgradeIncrResponse.setRegion(info);
				Log.v("test", "Start pengb end write  setRegion  " + info);
			} else if (localName.equals("upgrade")) {
				isUpgrade=false;
				mUpgradeIncrResponseUpgrades.add(mUpgradeIncrResponseUpgrade);
			} else if (localName.equals("type")) {
				mUpgradeIncrResponseUpgrade.setType(info);
				Log.v("test", "Start pengb end write  setType  " + info);
			} else if (localName.equals("appid")) {
				mUpgradeIncrResponseUpgrade.setAppid(info);
				Log.v("test", "Start pengb end write  setAppid  " + info);
			} else if (localName.equals("apptype")) {
				mUpgradeIncrResponseUpgrade.setApptype(info);
				Log.v("test", "Start pengb end write  setApptype  " + info);
			} else if (localName.equals("title")) {
				mUpgradeIncrResponseUpgrade.setTitle(info);
				Log.v("test", "Start pengb end write  setTitle  " + info);
			} else if (localName.equals("version")) {
				mUpgradeIncrResponseUpgrade.setVersion(info);
				Log.v("test", "Start pengb end write  setVersion  " + info);
			} else if (localName.equals("verid")) {
				mUpgradeIncrResponseUpgrade.setVerid(info);
				Log.v("test", "Start pengb end write  setVerid  " + info);
			} else if (localName.equals("description")) {
				mUpgradeIncrResponseUpgrade.setDescription(info);
				Log.v("test", "Start pengb end write  setDescription  " + info);
			} else if (localName.equals("minicon")) {
				mUpgradeIncrResponseUpgrade.setMinicon(info);
				Log.v("test", "Start pengb end write  setMinicon  " + info);
			} else if (localName.equals("midicon")) {
				mUpgradeIncrResponseUpgrade.setMidicon(info);
				Log.v("test", "Start pengb end write  setMidicon  " + info);
			} else if (localName.equals("fileurl")) {
				mUpgradeIncrResponseUpgrade.setFileurl(info);
				Log.v("test", "Start pengb end write  setFileurl  " + info);
			} else if (localName.equals("size")) {
				mUpgradeIncrResponseUpgrade.setSize(info);
				Log.v("test", "Start pengb end write  setSize  " + info);
			} else if (localName.equals("md5")) {
				mUpgradeIncrResponseUpgrade.setMd5(info);
				Log.v("test", "Start pengb end write  setMd5  " + info);
			} else if (localName.equals("increment")) {
				mUpgradeIncrResponseUpgrade.setIncrement(info);
				Log.v("test", "Start pengb end write  setIncrement  " + info);
			} else if (localName.equals("appendver")) {
				mUpgradeIncrResponseUpgrade.setAppendver(info);
				Log.v("test", "Start pengb end write  setAppendver  " + info);
			} else if (localName.equals("note")) {
//				mUpgradeIncrResponseUpgrade.setNote(info);
//				Log.v("test", "Start pengb end write  setNote  " + info);
			} else if (localName.equals("apiversion")) {
				mUpgradeIncrResponse.setApiversion(info);
				Log.v("test", "Start pengb end write  setApiversion  " + info);
			}

			super.endElement(uri, localName, qName);
		}

		/**
		 * ) �û������ַ�ڵ�
		 */
		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {

			info = new String(ch, start, length);
			Log.v("test", "Start pengb info  " + info);
		}

	}

	public UpgradeIncrResponse getmUpgradeIncrResponse() {
		return mUpgradeIncrResponse;
	}

}
