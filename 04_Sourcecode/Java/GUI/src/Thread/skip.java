package Thread;

import Configs.Setting;
import GUI.gui;
import Models.Likeviet;

public class skip extends Thread {
	private gui main;
	private String type = "";
	private int index;
	private String id;

	public skip(Object obj, String type, int index, String id) {
		// TODO Auto-generated constructor stub
		this.main = (gui) obj;
		this.type = type;
		this.index = index;
		this.id = id;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (this.type.equalsIgnoreCase("fanpage")) {
			this.likeFanpage();
		} else if (this.type.equalsIgnoreCase("photo")) {
			this.likephoto();
		} else if (this.type.equalsIgnoreCase("follow")) {
			this.follow();
		} else if (this.type.equalsIgnoreCase("share")) {
			this.share();
		} else if (this.type.equalsIgnoreCase("likeWebsite")) {
			this.likeWebsite();
		} else if (this.type.equalsIgnoreCase("likevideo")) {
			this.likevideo();
		} else if (this.type.equalsIgnoreCase("subcribeyou")) {
			this.subscribe();
		}
	}

	private void likeWebsite() {
		// TODO Auto-generated method stub
		String link = "/p.php?p=facebook&t=web";
		String siteCheck = "/system/modules/facebook/process.php";
		String refer = link;
		Likeviet lv = null;
		this.skipLink(lv, siteCheck, refer);
		this.main.getNet().removeObserver(lv);
	}

	private void subscribe() {
		// TODO Auto-generated method stub
		// init
		String link = "/p.php?p=ysub";
		String siteCheck = "/system/modules/ysub/process.php";
		String refer = link;
		Likeviet lv = null;
		this.skipLink(lv, siteCheck, refer);
		this.main.getNet().removeObserver(lv);
	}

	private void likevideo() {
		// TODO Auto-generated method stub
		// init
		String link = "/p.php?p=ylike";
		String siteCheck = "/system/modules/ylike/process.php";
		String refer = link;
		Likeviet lv = null;
		this.skipLink(lv, siteCheck, refer);
		this.main.getNet().removeObserver(lv);
	}

	private void share() {
		// TODO Auto-generated method stub
		// init
		String link = "/p.php?p=fb_share";
		String siteCheck = "/system/modules/fb_share/process.php";
		String refer = link;
		Likeviet lv = null;
		this.skipLink(lv, siteCheck, refer);
		this.main.getNet().removeObserver(lv);
	}

	private void follow() {
		// TODO Auto-generated method stub
		// init
		String link = "/p.php?p=fb_subscribe";
		String siteCheck = "/system/modules/fb_subscribe/process.php";
		String refer = link;
		Likeviet lv = null;
		this.skipLink(lv, siteCheck, refer);
		this.main.getNet().removeObserver(lv);
	}

	private void likephoto() {
		// TODO Auto-generated method stub
		// init
		String link = "/p.php?p=fb_photo";
		String siteCheck = "/system/modules/fb_photo/process.php";
		String refer = link;
		Likeviet lv = null;
		this.skipLink(lv, siteCheck, refer);
		this.main.getNet().removeObserver(lv);
	}

	private void likeFanpage() {
		// init
		String link = "/p.php?p=facebook";
		String siteCheck = "/system/modules/facebook/process.php";
		String refer = link;
		Likeviet lv = null;
		this.skipLink(lv, siteCheck, refer);
		this.main.getNet().removeObserver(lv);
	}

	private void skipLink(Likeviet lv, String siteCheck, String refer) {
		try {
			lv = Setting.lv.clone();
			lv.setCookieDir(Setting.lv.domain);// set other place for cookie
			lv.addViewFB(this.main);
			lv.setUserAgent(Setting.listUserAgent().get(7));
			this.main.getNet().addObserver(lv);

			boolean isSkip = lv.skipLink(siteCheck, refer, this.id);
			if (!isSkip) {
				this.main.updateRecord(this.index, 6, "Dont Skip");
			} else {
				this.main.updateRecord(this.index, 6, "Skipped");
			}
			lv.setNull("", "");
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
