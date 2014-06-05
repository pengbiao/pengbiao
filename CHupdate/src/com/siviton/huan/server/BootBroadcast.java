package com.siviton.huan.server;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Downloads;

public class BootBroadcast extends BroadcastReceiver {
//com.siviton.updatelater
	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		System.out.println("=huanwang===onReceive===getAction========="
				+ arg1.getAction());
		if (arg1.getAction() == null) {
			return;
		}
		String action = arg1.getAction();

		if (action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
			arg0.startService(new Intent(arg0, HuanServer.class).putExtra(
					"style", HuanServer.NET_CHANGE));
		} else if (action.equals("com.boot.huanfirst")) {
//			arg0.startService(new Intent(arg0, HuanServer.class).putExtra(
//					"style", HuanServer.BOOT));
		} else if (action.equals("android.intent.action.BOOT_COMPLETED")) {
			arg0.startService(new Intent(arg0, HuanServer.class).putExtra(
					"style", HuanServer.BOOT));
		} else if (action.equals(Downloads.Impl.ACTION_DOWNLOAD_COMPLETED)) {
			arg0.startService(new Intent(arg0, HuanServer.class).putExtra(
					"style", HuanServer.DOWNLOAD_COM));
		} else if (action.equals("com.siviton.updatelater")) {
			arg0.startService(new Intent(arg0, HuanServer.class).putExtra(
					"style", HuanServer.UPDATE_LATER_NOTIFY));
		}

	}
}
