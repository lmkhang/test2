package Thread;

import Configs.Setting;
import GUI.gui;
import Models.Facebook;
import Models.Likeviet;

public class confirm extends Thread {
	private gui main;
	private String type = "";
	private int index;
	private String link;

	public confirm(Object obj, String type, int index, String link) {
		// TODO Auto-generated constructor stub
		this.main = (gui) obj;
		this.type = type;
		this.index = index;
		this.link = link;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (this.type.equalsIgnoreCase("fanpage")) {
			this.likeFanpage();
		}
	}

	private void likeFanpage() {
		// init
		Facebook fb = null;
		this.confirmLink(fb, this.link);
		this.main.getNet().removeObserver(fb);
	}

	private void confirmLink(Facebook fb, String fanpageId) {
		try {
			fb = Setting.fb.clone();
			fb.setCookieDir(Setting.lv.domain);// set other place for cookie
			fb.addViewFB(this.main);
			fb.setUserAgent(Setting.listUserAgent().get(1));
			this.main.getNet().addObserver(fb);

			int isConfirm = fb.confirmLikeFacebook(fanpageId);
			if (isConfirm == -1) {
				this.main.updateRecord(this.index, 6, "Network die");
			} else if (isConfirm == 0) {
				this.main.updateRecord(this.index, 6,
						"Cannot Confirm, Please change facebook account");
			} else if (isConfirm == 1) {
				this.main.updateRecord(this.index, 6, "Confirm Success");
			}
			fb.setNull("", "");
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
