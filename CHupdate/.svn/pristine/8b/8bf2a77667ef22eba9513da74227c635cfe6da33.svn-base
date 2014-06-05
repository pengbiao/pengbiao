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

public class UpgradeFileAddressSaxXml {

	private SAXParser saxParser;
	private Context context;
	private SAXParserFactory saxParserFactory;
	private XmlHandler xmlHandler;
	private UpgradeFileItem mUpgradeFileItem;

	public UpgradeFileAddressSaxXml(Context context) {
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
			mUpgradeFileItem = new UpgradeFileItem();
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

			Log.v("test", "Start pengb" + localName);
			// if (localName.equals("servertime")) {
			// } else if (localName.equals("callid")) {
			// } else if (localName.equals("state")) {
			// } else if (localName.equals("note")) {
			// } else if (localName.equals("language")) {
			// } else if (localName.equals("timezone")) {
			// } else if (localName.equals("region")) {
			// } else if (localName.equals("upgrade")) {
			// } else if (localName.equals("type")) {
			// } else if (localName.equals("appid")) {
			// } else if (localName.equals("apptype")) {
			// } else if (localName.equals("title")) {
			// } else if (localName.equals("version")) {
			// } else if (localName.equals("verid")) {
			// } else if (localName.equals("description")) {
			// } else if (localName.equals("minicon")) {
			// } else if (localName.equals("midicon")) {
			// } else if (localName.equals("note")) {
			// } else if (localName.equals("apiversion")) {
			// } else {
			// }

			super.startElement(uri, localName, qName, attributes);
		}

		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			Log.v("test", "Start pengb end  " + localName);
			if (localName.equals("servertime")) {
				mUpgradeFileItem.setServertime(info);
			} else if (localName.equals("callid")) {
				mUpgradeFileItem.setCallid(info);
			} else if (localName.equals("state")) {
				mUpgradeFileItem.setState(info);
			} else if (localName.equals("note")) {
				mUpgradeFileItem.setNote(info);
			} else if (localName.equals("appid")) {
				mUpgradeFileItem.setAppid(info);
			} else if (localName.equals("title")) {
				mUpgradeFileItem.setTitle(info);
			} else if (localName.equals("fileurl")) {
				mUpgradeFileItem.setFileurl(info);
			} else if (localName.equals("size")) {
				mUpgradeFileItem.setSize(info);
				;
			} else if (localName.equals("md5")) {
				mUpgradeFileItem.setMd5(info);
			} else if (localName.equals("apiversion")) {
				mUpgradeFileItem.setApiversion(info);
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

	public UpgradeFileItem getmUpgradeFileItem() {
		return mUpgradeFileItem;
	}

	public void setmUpgradeFileItem(UpgradeFileItem mUpgradeFileItem) {
		this.mUpgradeFileItem = mUpgradeFileItem;
	}

}
