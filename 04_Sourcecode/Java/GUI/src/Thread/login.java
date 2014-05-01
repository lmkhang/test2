package Thread;

import javax.swing.JOptionPane;

import Configs.Message;
import Configs.Setting;
import GUI.gui;
import Models.Facebook;
import Models.Gg;
import Models.Likeviet;
import Models.Main;

public class login extends Thread {

	private gui main;
	private String key = "";

	public login(Object obj, String key) {
		this.key = key;
		this.main = (gui) obj;
	}

	@Override
	public void run() {
		if (Setting.flagAdsense == 0) {
			JOptionPane.showMessageDialog(this.main.getMain(), Message.adsense);
			return;
		}
		// TODO Auto-generated method stub
		this.setInputEnable(false);
		if (this.key.equals("fb")) {
			this.loginfb();
		} else if (this.key.equals("lv")) {
			this.loginLV();
		} else if (this.key.equals("gg")) {
			this.loginGG();
		} else if (this.key.equals("main")) {
			this.loginMain();
		}
		// destroy();
	}

	private void loginMain() {
		// TODO Auto-generated method stub

	}

	private void loginGG() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		int row = this.main.addRecord("1", "Login: " + this.key, "Google", "0",
				"");
		if (Setting.gg == null) {
			Setting.gg = (Gg) Gg.getInstance();
		}
		this.main.getMain().pn_gg_action.setVisible(false);
		this.main.getNet().addObserver(Setting.gg);
		String folder = this.key;
		if (!Setting.gg.isLogin()) {
			this.main.updateRecord(row, 5, "Logining");
			String user = this.main.getMain().txt_usernamegg.getText().trim();
			String pass = this.main.getMain().txt_passwordgg.getText().trim();
			// save
			this.main.getMain().btn_logingg.setEnabled(false);
			this.main.getMain().lb_statusgg.setText("Logining");

			Setting.gg.init(folder);
			int isGetStatus = Setting.gg.checkStatusLogin();// 0: not login,
															// 1:logged
			if (isGetStatus == 0) {
				int isLogin = Setting.gg.login(user, pass);
				if (isLogin == 1) {
					this.main.getMain().pn_gg_action.setVisible(true);
					Setting.gg.setLogin(true);
					this.main.updateRecord(row, 5, "Logged");
					this.main.getMain().lb_statusgg.setText("Logged");
					// change image
					this.main.setImage(this.main.getMain().btn_logingg,
							"logout.png");
				} else if (isLogin == 0) {
					Setting.gg.setLogin(false);
					this.main.updateRecord(row, 5, "Invalid");
					Setting.gg.setNull(folder, null);
					Setting.gg = null;
					this.main.getNet().removeObserver(Setting.gg);
					this.main.getMain().lb_statusgg.setText("Invalid");
					this.setInputEnable(true);
				}else if (isLogin == -1) {
					Setting.gg.setLogin(false);
					this.main.updateRecord(row, 5, "Xin vui long kiem tra ket noi mang");
					Setting.gg.setNull(folder, null);
					Setting.gg = null;
					this.main.getNet().removeObserver(Setting.gg);
					this.main.getMain().lb_statusgg.setText("Xin vui long kiem tra ket noi mang");
					this.setInputEnable(true);
				}else if (isLogin == 2) {
					Setting.gg.setLogin(false);
					this.main.updateRecord(row, 5, "Ban chua tao Kenh Youtube. Vui long tao kenh Youtube");
					Setting.gg.setNull(folder, null);
					Setting.gg = null;
					this.main.getNet().removeObserver(Setting.gg);
					this.main.getMain().lb_statusgg.setText("Ban chua tao Kenh Youtube. Vui long tao kenh Youtube");
					this.setInputEnable(true);
				}else if (isLogin == 3) {
					Setting.gg.setLogin(false);
					this.main.updateRecord(row, 5, "Hiện tại IP của bạn đang nằm trong BL của Google, bạn nên thử lại sau nhé");
					Setting.gg.setNull(folder, null);
					Setting.gg = null;
					this.main.getNet().removeObserver(Setting.gg);
					this.main.getMain().lb_statusgg.setText("Hiện tại IP của bạn đang nằm trong BL của Google, bạn nên thử lại sau nhé");
					this.setInputEnable(true);
				}
			} else {
				Setting.gg.setLogin(true);
				this.main.updateRecord(row, 5, "Logged");
				this.main.getMain().lb_statusgg.setText("Logged");
				// change image
				this.main.setImage(this.main.getMain().btn_logingg,
						"logout.png");
			}
			this.main.getMain().btn_logingg.setEnabled(true);
		} else {
			this.main.getMain().pn_gg_action.setVisible(false);
			Setting.gg.setLogin(false);
			this.main.updateRecord(row, 5, "Logouting");
			// Setting.fb.logout();
			Setting.prepareFolder();
			Setting.gg.setNull(folder, null);
			Setting.gg = null;
			this.main.getNet().removeObserver(Setting.gg);
			this.main.getMain().lb_statusgg.setText("Logout");
			this.main.updateRecord(row, 5, "Logout");
			this.main.setImage(this.main.getMain().btn_logingg, "login.png");
			this.setInputEnable(true);
		}
	}

	private void loginfb() {
		// TODO Auto-generated method stub
		int row = this.main.addRecord("1", "Login: " + this.key, "Facebook",
				"0", "");
		if (Setting.fb == null) {
			Setting.fb = (Facebook) Facebook.getInstance();
		}
		this.main.getMain().pn_fb_action.setVisible(false);
		this.main.getNet().addObserver(Setting.fb);
		String folder = this.key;
		if (!Setting.fb.isLogin()) {
			this.main.updateRecord(row, 5, "Logining");
			String user = this.main.getMain().txt_usernamefb.getText().trim();
			String pass = this.main.getMain().txt_passwordfb.getText().trim();
			// save
			this.main.getMain().btn_loginfb.setEnabled(false);
			this.main.getMain().lb_statusfb.setText("Logining");

			Setting.fb.init(folder);
			boolean isGetStatus = Setting.fb.getStatus();
			if (isGetStatus == true) {
				int isLogin = Setting.fb.login(user, pass);
				if (isLogin == 1) {
					this.main.getMain().pn_fb_action.setVisible(true);
					Setting.fb.setLogin(true);
					this.main.updateRecord(row, 5, "Logged");
					this.main.getMain().lb_statusfb.setText("Logged");
					// change image
					this.main.setImage(this.main.getMain().btn_loginfb,
							"logout.png");
				} else if (isLogin == 0) {
					Setting.fb.setLogin(false);
					this.main.updateRecord(row, 5, "Invalid");
					Setting.fb.setNull(folder, null);
					Setting.fb = null;
					this.main.getNet().removeObserver(Setting.fb);
					this.main.getMain().lb_statusfb.setText("Invalid");
					this.setInputEnable(true);
				} else if (isLogin == -1) {
					Setting.fb.setLogin(false);
					this.main.updateRecord(row, 5,
							"Your Account Is Temporarily Locked");
					Setting.fb.setNull(folder, null);
					Setting.fb = null;
					this.main.getNet().removeObserver(Setting.fb);
					this.main.getMain().lb_statusfb
							.setText("Please login on browser once to recognize location.");
					this.setInputEnable(true);
				}
			} else {
				Setting.fb.setLogin(true);
				this.main.getMain().pn_fb_action.setVisible(true);
				this.main.updateRecord(row, 5, "Logged");
				this.main.getMain().lb_statusfb.setText("Logged");
				// change image
				this.main.setImage(this.main.getMain().btn_loginfb,
						"logout.png");
			}
			this.main.getMain().btn_loginfb.setEnabled(true);
		} else {
			this.main.getMain().pn_fb_action.setVisible(false);
			Setting.fb.setLogin(false);
			this.main.updateRecord(row, 5, "Logouting");
			// Setting.fb.logout();
			Setting.prepareFolder();
			Setting.fb.setNull(folder, null);
			Setting.fb = null;
			this.main.getNet().removeObserver(Setting.fb);
			this.main.getMain().lb_statusfb.setText("Logout");
			this.main.updateRecord(row, 5, "Logout");
			this.main.setImage(this.main.getMain().btn_loginfb, "login.png");
			this.setInputEnable(true);
		}
	}

	private void loginLV() {
		// TODO Auto-generated method stub
		int row = this.main.addRecord("1", "Login: " + this.key, "LikeViet",
				"0", "");
		if (Setting.lv == null) {
			Setting.lv = (Likeviet) Likeviet.getInstance();
		}
		this.main.getNet().addObserver(Setting.lv);
		String folder = this.key;
		if (!Setting.lv.isLogin()) {
			this.main.updateRecord(row, 5, "Logining");
			String user = this.main.getMain().txt_usernamelv.getText().trim();
			String pass = this.main.getMain().txt_passwordlv.getText().trim();
			// save
			this.main.getMain().btn_loginlv.setEnabled(false);
			this.main.getMain().tabs.setEnabledAt(3, false);
			this.main.getMain().lb_statuslv.setText("Logining");

			Setting.lv.init(folder);

			boolean isLogin = Setting.lv.login(user, pass);
			if (isLogin == true) {
				Setting.lv.setLogin(true);
				this.main.updateRecord(row, 5, "Logged");
				this.main.getMain().lb_statuslv.setText("Logged");
				// save
				String coin = Setting.lv.getCurrentCoin();
				// change image
				this.main.setImage(this.main.getMain().btn_loginlv,
						"logout.png");
				this.main.getMain().pn_lv_action.setVisible(true);
			} else {
				Setting.lv.setLogin(false);
				this.main.updateRecord(row, 5, "Invalid");
				Setting.lv.setNull(folder, null);
				Setting.lv = null;
				this.main.getNet().removeObserver(Setting.lv);
				this.main.getMain().lb_statuslv.setText("Invalid");
				// save
				this.setInputEnable(true);
				this.main.getMain().tabs.setEnabledAt(3, true);
				this.main.getMain().pn_lv_action.setVisible(false);
			}
			this.setTabs(isLogin);
			this.main.getMain().btn_loginlv.setEnabled(true);
		} else {
			Setting.lv.setLogin(false);
			this.main.updateRecord(row, 5, "Logouting");
			// Setting.fb.logout();
			Setting.prepareFolder();
			Setting.lv.setNull(folder, null);
			Setting.lv = null;
			this.main.getNet().removeObserver(Setting.lv);
			this.main.getMain().lb_statuslv.setText("Logout");
			this.main.updateRecord(row, 5, "Logout");
			this.setTabs(false);
			this.main.setImage(this.main.getMain().btn_loginlv, "login.png");
			this.setInputEnable(true);
			this.main.getMain().tabs.setEnabledAt(3, true);
			this.main.getMain().pn_lv_action.setVisible(false);
		}

		miniThread mini = new miniThread(this.main, "getcurrentclick");
		mini.start();
	}

	private void setTabs(boolean isEnable) {
		this.main.getMain().tabs.setEnabledAt(1, isEnable);
		this.main.getMain().tabs.setEnabledAt(2, isEnable);
		this.main.getMain().btn_showreferrals.setEnabled(isEnable);
	}

	private void setInputEnable(boolean isEnable) {
		if (this.key.equals("fb")) {
			this.main.getMain().txt_usernamefb.setEnabled(isEnable);
			this.main.getMain().txt_passwordfb.setEnabled(isEnable);
		} else if (this.key.equals("lv")) {
			this.main.getMain().txt_usernamelv.setEnabled(isEnable);
			this.main.getMain().txt_passwordlv.setEnabled(isEnable);
		} else if (this.key.equals("gg")) {
			this.main.getMain().txt_usernamegg.setEnabled(isEnable);
			this.main.getMain().txt_passwordgg.setEnabled(isEnable);
		}

	}

}
