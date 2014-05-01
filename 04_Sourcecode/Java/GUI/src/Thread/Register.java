package Thread;

import java.net.URL;

import javax.swing.JOptionPane;

import Configs.Setting;
import Models.Likeviet;
import GUI.gui;

public class Register extends Thread {
	private String site;
	private String type;
	private gui gui;

	public Register(gui gui, String site, String type) {
		this.gui = gui;
		this.site = site;
		this.type = type;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (this.site.equals("lv")) {
			if (this.type.equals("captcha")) {
				this.getCaptcha();
			} else if (this.type.equals("register")) {
				this.register();
			}
		}
	}

	private void register() {
		// TODO Auto-generated method stub
		int row = this.gui.addRecord("1", "Register", "Like Viet", "0", "");
		int rs = Setting.lv.register(
				this.gui.getMain().txt_res_username.getText(),
				this.gui.getMain().txt_res_password.getText().trim(),
				this.gui.getMain().txt_res_email.getText().trim(),
				this.gui.getMain().txt_res_captcha.getText().trim());
		if (rs == -1) {
			this.gui.updateRecord(row, 5, "Network die!");
		} else {
			JOptionPane.showMessageDialog(this.gui.getMain(),
					Setting.lv.message);
			this.gui.updateRecord(row, 5, Setting.lv.message);
			if (rs == 2) {
				this.gui.getNet().removeObserver(Setting.lv);
				Setting.lv.setNull("", null);
				Setting.lv = null;
				//reset field
				this.gui.getMain().txt_res_username.setText("");
				this.gui.getMain().txt_res_password.setText("");
				this.gui.getMain().txt_res_email.setText("");
				this.gui.getMain().txt_res_captcha.setText("");
			}
			this.getCaptcha();
		}
	}

	private void getCaptcha() {
		URL image = this.gui.getMain().getClass()
				.getResource("/outsrc/images/loading.gif");

		this.gui.getMain().setImageCaptchaRegister(image);
		int row = this.gui.addRecord("1", "Get Captcha", "Like Viet", "0", "");
		if (Setting.lv == null) {
			Setting.lv = (Likeviet) Likeviet.getInstance();
			Setting.lv.init(this.site);
			System.out.println("vao");
		}
		this.gui.getNet().addObserver(Setting.lv);
		String rs = Setting.lv.getCaptcha();
		if (!rs.equals("")) {
			String image2 = Setting.getPathApp() + "/cache/"
					+ Setting.image_cache + "." + Setting.lv.extension;
			this.gui.getMain().setImageCaptchaRegister(image2);
			this.gui.updateRecord(row, 5, "Ok");
		} else {
			image = this.gui.getMain().getClass()
					.getResource("/outsrc/images/error.png");
			this.gui.getMain().setImageCaptchaRegister(image);
			this.gui.updateRecord(row, 5, "Failed");
		}

	}
}
