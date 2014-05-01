package Thread;

import java.util.Vector;

import javax.swing.JOptionPane;

import org.htmlcleaner.XPatherException;

import Configs.Setting;
import GUI.ShowMessage;
import GUI.gui;
import GUI.sendmail;
import Models.Mail;

public class miniThread extends Thread {
	private String type;
	private Object gui;

	public miniThread(Object gui, String type) {

		// TODO Auto-generated constructor stub
		this.type = type;
		this.gui = gui;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		if (this.type.equals("getcurrentclick")) {
			gui gui = (gui) this.gui;
			while (Setting.lv != null && Setting.lv.isLogin()) {
				try {
					this.getInfo(gui, Setting.lv.isLogin());
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			this.getInfo(gui, false);
			gui.getMain().lb_currentclick.setText("");
		} else if (this.type.equals("sendmail")) {
			sendmail gui = (sendmail) this.gui;
			this.sendmail(gui);
		} else if (this.type.equals("showreferrals")) {
			gui gui = (gui) this.gui;
			this.showReferrals(gui);
		}
	}

	private void showReferrals(gui gui) {
		// TODO Auto-generated method stub
		try {
			Vector<Vector<String>> list = Setting.lv.getListReferrals();
			String rs = Setting.lv.showReferrals(list);
			new ShowMessage(rs);
		} catch (XPatherException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void sendmail(sendmail gui) {
		// TODO Auto-generated method stub

		Vector<String> attachmentList = new Vector<>();
		// attachmentList.add("E:\\soft\\virtualbox\\License_en_US.rtf");
		// attachmentList.add("E:\\soft\\unikey42RC1-131228-win32\\keymap.txt");
		Mail.init(Setting.emailSendMail, Setting.passwordSendMail);
		Mail.setInfo(Setting.emailSendMail, gui.txt_youremail.getText()
				.toString().trim(),
				gui.txt_subject.getText().toString().trim(), gui.txt_content
						.getText().toString().trim());
		Mail.setAttachments(gui.txt_content.getText().toString().trim(),
				attachmentList);
		String rs = Mail.send();
		JOptionPane.showMessageDialog(gui, rs);
		gui.resestButton();
		gui.dispose();
	}

	private void getInfo(gui gui, boolean isLogin) {
		if (isLogin) {
			gui.getMain().lb_coin.setText(Setting.lv.getCurrentCoin());
			gui.getMain().lb_currentclick.setText(Setting.lv.getCurrentClick());
			gui.getMain().lb_totalrefer.setText(Setting.lv.getTotalRefferer());
			gui.getMain().lb_remainclick.setText(Setting.lv.getRemainClick()
					+ "");
			gui.getMain().lb_bonusremain.setText(this.getStatusBonus(Setting.lv
					.getBonus()));
			gui.getMain().txt_referlink.setText(Setting.lv.getRefferalLink());
		} else {
			gui.getMain().lb_coin.setText("");
			gui.getMain().lb_currentclick.setText("");
			gui.getMain().lb_totalrefer.setText("");
			gui.getMain().lb_remainclick.setText("");
			gui.getMain().lb_bonusremain.setText("");
			gui.getMain().txt_referlink.setText("");
		}
		gui.getMain().repaint();
	}

	private String getStatusBonus(int rs) {
		String str = "";
		switch (rs) {
		case -1:
			str = "Network die";
			break;
		case 0:
			str = "Chưa click đủ số lượng cần click";
			break;
		case 1:
			str = "Thành công";
			break;
		case 2:
			str = "Không thể vì đã get roài";
			break;
		default:
			break;
		}
		return str;
	}
}
