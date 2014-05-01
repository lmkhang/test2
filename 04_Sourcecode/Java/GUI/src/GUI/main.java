/*
 * Created by JFormDesigner on Thu Jan 23 22:09:12 ICT 2014
 */

package GUI;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import Configs.Setting;
import Models.Likeviet;
import Thread.Register;
import Thread.autofb;
import Thread.autolv;
import Thread.autogg;
import Thread.login;
import Thread.miniThread;
import Utils.Utils;

import chrriis.dj.nativeswing.NativeSwing;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.DefaultWebBrowserDecorator.WebBrowserLocationBar;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowserWindow;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserCommandEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserListener;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserNavigationEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserWindowOpeningEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserWindowWillOpenEvent;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

import javax.swing.event.ChangeListener;

/**
 * @author Kunkka Lee
 */
public class main extends JFrame {
	Container contentPane;

	private gui gui;
	private JWebBrowser webAds;

	public main(gui gui) {
		initComponents();

		this.setLocationByPlatform(true);
		JFrame.setDefaultLookAndFeelDecorated(true);
		this.setResizable(false);
		this.setLocation(50, 50);
		this.setSize(550, 550);
		this.gui = gui;
		this.readSetting();
		this.loadAdsense(Setting.urlAdsense);
		// this.checkKeyShow();
	}

	private void loadAdsense(String url) {
		webAds = new JWebBrowser();
		webAds.setJavascriptEnabled(true);
		webAds.setMenuBarVisible(false);
		webAds.setBarsVisible(false);
		webAds.setFocusable(false);
		webAds.navigate(url);
		pn_webview.setViewportView(webAds);
//		String html = Setting.htmlAdsense;
//		webAds.setHTMLContent(html);
		// if load position not in 100% then marker not added
		// cause load js after load page finished
		webAds.addWebBrowserListener(new WebBrowserListener() {

			@Override
			public void windowWillOpen(WebBrowserWindowWillOpenEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowOpening(WebBrowserWindowOpeningEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowClosing(WebBrowserEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void titleChanged(WebBrowserEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void statusChanged(WebBrowserEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void locationChanging(WebBrowserNavigationEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void locationChanged(WebBrowserNavigationEvent arg0) {
				// TODO Auto-generated method stub
				Setting.flagAdsense++;
			}

			@Override
			public void locationChangeCanceled(WebBrowserNavigationEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void loadingProgressChanged(WebBrowserEvent wbe) {
				// TODO Auto-generated method stub
			}

			@Override
			public void commandReceived(WebBrowserCommandEvent arg0) {
				// TODO Auto-generated method stub
			}
		});
	}

	private void checkKeyShow() {
		if (Utils.checkKey(Setting.keyActionPlus) == true) {
			this.btn_gg_plus.setVisible(true);
		} else {
			this.btn_gg_plus.setVisible(false);
		}
	}

	public void setLoading(JButton btn) {
		URL image = this.gui.getMain().getClass()
				.getResource("/outsrc/images/loading.gif");
		ImageIcon icon = new ImageIcon(image);
		icon.getImage().flush();
		btn.setIcon(icon);

	}

	public void setImageCaptchaRegister(String image) {
		ImageIcon icon = new ImageIcon(image);
		icon.getImage().flush();
		img_res_captcha.setIcon(icon);

	}

	public void setImageCaptchaRegister(URL image) {
		ImageIcon icon = new ImageIcon(image);
		icon.getImage().flush();
		img_res_captcha.setIcon(icon);

	}

	private void readSetting() {
		// menu autoskip
		String value = Utils.readXML(Setting.settingFileName, "tableLog",
				"skip", "value");
		if (value.equals("0") || value.equals("")) {
			this.mn_autoSkip.setSelected(false);
		} else {
			this.mn_autoSkip.setSelected(true);
		}

		// load account
		// likeviet
		value = Utils.readXML(Setting.settingFileName, "saveAccount",
				"likeviet", "value");
		if (value.equals("0") || value.equals("")) {
			this.mn_lv.setSelected(false);
		} else {
			this.mn_lv.setSelected(true);
		}
		String u = Utils.readXML(Setting.settingFileName, "saveAccount",
				"lv_user", "value");
		String p = Utils.readXML(Setting.settingFileName, "saveAccount",
				"lv_pass", "value");
		this.txt_usernamelv.setText(u);
		this.txt_passwordlv.setText(p);
		// facebook
		value = Utils.readXML(Setting.settingFileName, "saveAccount",
				"facebook", "value");
		if (value.equals("0") || value.equals("")) {
			this.mn_fb.setSelected(false);
		} else {
			this.mn_fb.setSelected(true);
		}
		u = Utils.readXML(Setting.settingFileName, "saveAccount", "fb_user",
				"value");
		p = Utils.readXML(Setting.settingFileName, "saveAccount", "fb_pass",
				"value");
		this.txt_usernamefb.setText(u);
		this.txt_passwordfb.setText(p);
		// google
		value = Utils.readXML(Setting.settingFileName, "saveAccount", "google",
				"value");
		if (value.equals("0") || value.equals("")) {
			this.mn_gg.setSelected(false);
		} else {
			this.mn_gg.setSelected(true);
		}
		u = Utils.readXML(Setting.settingFileName, "saveAccount", "gg_user",
				"value");
		p = Utils.readXML(Setting.settingFileName, "saveAccount", "gg_pass",
				"value");
		this.txt_usernamegg.setText(u);
		this.txt_passwordgg.setText(p);
	}

	private void btn_loginlv(MouseEvent e) {
		// TODO add your code here
		boolean isSelected = this.mn_lv.isSelected();
		if (isSelected) {
			this.saveSetting("saveAccount", "lv_user", "value",
					this.txt_usernamelv.getText().trim());
			this.saveSetting("saveAccount", "lv_pass", "value",
					this.txt_passwordlv.getText().trim());
		} else {
			this.saveSetting("saveAccount", "lv_user", "value", "");
			this.saveSetting("saveAccount", "lv_pass", "value", "");
		}
		login lg = new login(gui, "lv");
		lg.start();
	}

	private void viewlog(ActionEvent e) {
		// TODO add your code here
		boolean isShow = this.gui.getDialog().showlog.isVisible();
		isShow = !isShow;
		this.gui.showLog(isShow);
	}

	private void btn_loginfb(ActionEvent e) {
		// TODO add your code here
		boolean isSelected = this.mn_fb.isSelected();
		if (isSelected) {
			this.saveSetting("saveAccount", "fb_user", "value",
					this.txt_usernamefb.getText().trim());
			this.saveSetting("saveAccount", "fb_pass", "value",
					this.txt_passwordfb.getText().trim());
		} else {
			this.saveSetting("saveAccount", "fb_user", "value", "");
			this.saveSetting("saveAccount", "fb_pass", "value", "");
		}
		login lg = new login(gui, "fb");
		lg.start();
	}

	private void btn_fb_fanpage(ActionEvent e) {
		// TODO add your code here
		boolean isEnable = this.btn_fb_fanpage.isEnabled();
		if (isEnable) {
			autofb auto = new autofb(this.gui, "fanpage", 1);
			auto.start();
		}
	}

	private void btn_fb_photo(ActionEvent e) {
		// TODO add your code here
		boolean isEnable = this.btn_fb_photo.isEnabled();
		if (isEnable) {
			autofb auto = new autofb(this.gui, "photo", 2);
			auto.start();
		}
	}

	private void btn_fb_follow(ActionEvent e) {
		// TODO add your code here
		boolean isEnable = this.btn_fb_follow.isEnabled();
		if (isEnable) {
			autofb auto = new autofb(this.gui, "follow", 3);
			auto.start();
		}
	}

	private void btn_fb_share(ActionEvent e) {
		// TODO add your code here
		boolean isEnable = this.btn_fb_share.isEnabled();
		if (isEnable) {
			autofb auto = new autofb(this.gui, "share", 4);
			auto.start();
		}
	}

	public void popupSendmail(String subject, String content) {
		this.gui.getSm().txt_subject.setText(subject);
		this.gui.getSm().txt_content.setText(content);
		this.gui.getSm().setVisible(true);
	}

	private void mn_sendmailActionPerformed(ActionEvent e) {
		// TODO add your code here
		boolean isEnable = this.mn_sendmail.isEnabled();
		if (isEnable) {
			this.popupSendmail("", "");
		}
	}

	private void save_AutoSkip(ActionEvent e) {
		// TODO add your code here
		this.saveSetting("tableLog", "skip", "value", "-1");
	}

	private void mn_lvSave(ActionEvent e) {
		// TODO add your code here
		boolean isSelected = this.mn_lv.isSelected();
		if (isSelected) {
			this.saveSetting("saveAccount", "likeviet", "value", "1");
			this.saveSetting("saveAccount", "lv_user", "value",
					this.txt_usernamelv.getText().trim());
			this.saveSetting("saveAccount", "lv_pass", "value",
					this.txt_passwordlv.getText().trim());
		} else {
			this.saveSetting("saveAccount", "likeviet", "value", "0");
			this.saveSetting("saveAccount", "lv_user", "value", "");
			this.saveSetting("saveAccount", "lv_pass", "value", "");
		}
	}

	private void mn_fbSave(ActionEvent e) {
		// TODO add your code here
		boolean isSelected = this.mn_fb.isSelected();
		if (isSelected) {
			this.saveSetting("saveAccount", "facebook", "value", "1");
			this.saveSetting("saveAccount", "fb_user", "value",
					this.txt_usernamefb.getText().trim());
			this.saveSetting("saveAccount", "fb_pass", "value",
					this.txt_passwordfb.getText().trim());
		} else {
			this.saveSetting("saveAccount", "facebook", "value", "0");
			this.saveSetting("saveAccount", "fb_user", "value", "");
			this.saveSetting("saveAccount", "fb_pass", "value", "");
		}
	}

	private void mn_ggSave(ActionEvent e) {
		// TODO add your code here
		boolean isSelected = this.mn_gg.isSelected();
		if (isSelected) {
			this.saveSetting("saveAccount", "google", "value", "1");
			this.saveSetting("saveAccount", "gg_user", "value",
					this.txt_usernamegg.getText().trim());
			this.saveSetting("saveAccount", "gg_pass", "value",
					this.txt_passwordgg.getText().trim());
		} else {
			this.saveSetting("saveAccount", "google", "value", "0");
			this.saveSetting("saveAccount", "gg_user", "value", "");
			this.saveSetting("saveAccount", "gg_pass", "value", "");
		}
	}

	private void saveSetting(String el, String row, String column, String value) {
		if (value.equals("-1")) {
			value = Utils.readXML(Setting.settingFileName, el, row, column);
			if (value.equals("")) {
				value = "1";
			} else {
				int v = Integer.parseInt(value) + 1;
				v = v % 2;
				value = String.valueOf(v);
			}
		}
		Utils.modifyXML(Setting.settingFileName, Setting.parent, el, row,
				column, value);
	}

	private void btn_logingg(ActionEvent e) {
		// TODO add your code here
		boolean isSelected = this.mn_gg.isSelected();
		if (isSelected) {
			this.saveSetting("saveAccount", "gg_user", "value",
					this.txt_usernamegg.getText().trim());
			this.saveSetting("saveAccount", "gg_pass", "value",
					this.txt_passwordgg.getText().trim());
		} else {
			this.saveSetting("saveAccount", "gg_user", "value", "");
			this.saveSetting("saveAccount", "gg_pass", "value", "");
		}
		login lg = new login(gui, "gg");
		lg.start();
	}

	private void showSaveLog(ActionEvent e) {
		// TODO add your code here
		this.gui.showSaveLog();
	}

	private void likevideoyou(ActionEvent e) {
		// TODO add your code here
		boolean isEnable = this.btn_likevideo.isEnabled();
		if (isEnable) {
			autogg auto = new autogg(this.gui, "likevideo", 1);
			auto.start();
		}
	}

	private void btn_subscribeyou(ActionEvent e) {
		// TODO add your code here
		boolean isEnable = this.btn_subscribeyou.isEnabled();
		if (isEnable) {
			autogg auto = new autogg(this.gui, "subcribeyou", 2);
			auto.start();
		}
	}

	private void choose_register() {
		// TODO add your code here
		// System.out.println("register");
		boolean isEnable = this.tab_register.isEnabled();
		if (isEnable) {
			Setting.lv = null;
			this.getCaptcha();
		}
	}

	private void captcha_reload(MouseEvent e) {
		// TODO add your code here
		this.getCaptcha();
	}

	private void getCaptcha() {
		Register reg = new Register(this.gui, "lv", "captcha");
		reg.start();
	}

	private void tabsStateChanged(ChangeEvent e) {
		// TODO add your code here
		int index = this.tabs.getSelectedIndex();
		if (index == 3) {
			this.choose_register();
		}
	}

	private void btn_registerActionPerformed(ActionEvent e) {
		// TODO add your code here
		boolean isEnable = this.btn_register.isEnabled();
		if (isEnable) {
			Register reg = new Register(this.gui, "lv", "register");
			reg.start();
		}
	}

	private void btn_showreferrals(MouseEvent e) {
		// TODO add your code here
		boolean isEnable = this.btn_showreferrals.isEnabled();
		if (isEnable) {
			miniThread mini = new miniThread(this.gui, "showreferrals");
			mini.start();
		}
	}

	private void btn_fb_website(MouseEvent e) {
		// TODO add your code here
		boolean isEnable = this.btn_fb_website.isEnabled();
		if (isEnable) {
			autofb auto = new autofb(this.gui, "likeWebsite", 1);
			auto.start();
		}
	}

	private void btn_lv_surf(MouseEvent e) {
		// TODO add your code here
		boolean isEnable = this.btn_lv_surf.isEnabled();
		if (isEnable) {
			autolv auto = new autolv(this.gui, "surf");
			auto.start();
		}
	}

	private void btn_gg_plus(MouseEvent e) {
		// TODO add your code here
		boolean isEnable = this.btn_gg_plus.isEnabled();
		if (isEnable) {
			autogg auto = new autogg(this.gui, "plus", 1);
			auto.start();
		}
	}

	private void btn_gg_view(MouseEvent e) {
		// TODO add your code here
		boolean isEnable = this.btn_gg_view.isEnabled();
		if (isEnable) {
			autogg auto = new autogg(this.gui, "view", 1);
			auto.start();
		}
	}

	private void btn_main_register(MouseEvent e) {
		// TODO add your code here
	}

	private void thisWindowClosing(WindowEvent e) {
		this.dispose();
		System.exit(0);
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		menuBar1 = new JMenuBar();
		menu1 = new JMenu();
		menu4 = new JMenu();
		mn_lv = new JCheckBoxMenuItem();
		mn_fb = new JCheckBoxMenuItem();
		mn_gg = new JCheckBoxMenuItem();
		mn_autoSkip = new JCheckBoxMenuItem();
		menu2 = new JMenu();
		mn_sendmail = new JMenuItem();
		mn_vlog = new JMenuItem();
		mn_slog = new JMenuItem();
		menu3 = new JMenu();
		menuItem1 = new JMenuItem();
		menuItem4 = new JMenuItem();
		menuItem3 = new JMenuItem();
		menuItem2 = new JMenuItem();
		btn_showreferrals = new JButton();
		scrollPane1 = new JScrollPane();
		panel2 = new JPanel();
		label18 = new JLabel();
		txt_referlink = new JTextField();
		label14 = new JLabel();
		lb_bonusremain = new JLabel();
		scrollPane2 = new JScrollPane();
		panel1 = new JPanel();
		labcoin = new JLabel();
		label15 = new JLabel();
		label16 = new JLabel();
		label17 = new JLabel();
		lb_coin = new JLabel();
		lb_currentclick = new JLabel();
		lb_totalrefer = new JLabel();
		lb_remainclick = new JLabel();
		tabs = new JTabbedPane();
		tab_lv = new JPanel();
		txt_usernamelv = new JFormattedTextField();
		txt_passwordlv = new JPasswordField();
		btn_loginlv = new JToggleButton();
		lb_statuslv = new JLabel();
		pn_lv_action = new JPanel();
		btn_lv_surf = new JButton();
		tab_fb = new JPanel();
		txt_usernamefb = new JFormattedTextField();
		txt_passwordfb = new JPasswordField();
		btn_loginfb = new JToggleButton();
		lb_statusfb = new JLabel();
		panel3 = new JPanel();
		pn_fb_action = new JPanel();
		btn_fb_fanpage = new JButton();
		btn_fb_website = new JButton();
		btn_fb_photo = new JButton();
		btn_fb_follow = new JButton();
		btn_fb_share = new JButton();
		tab_gg = new JPanel();
		txt_usernamegg = new JFormattedTextField();
		txt_passwordgg = new JPasswordField();
		btn_logingg = new JToggleButton();
		lb_statusgg = new JLabel();
		pn_gg_action = new JPanel();
		btn_likevideo = new JButton();
		btn_subscribeyou = new JToggleButton();
		btn_gg_plus = new JButton();
		btn_gg_view = new JButton();
		tab_register = new JPanel();
		txt_res_username = new JFormattedTextField();
		txt_res_password = new JFormattedTextField();
		img_res_captcha = new JLabel();
		txt_res_captcha = new JFormattedTextField();
		txt_res_email = new JFormattedTextField();
		btn_register = new JButton();
		pn_webview = new JScrollPane();
		panel8 = new JPanel();
		label7 = new JLabel();
		lb_status = new JLabel();

		// ======== this ========
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Auto Like for Facebook - Google site");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				thisWindowClosing(e);
			}
		});
		Container contentPane = getContentPane();
		contentPane
				.setLayout(new FormLayout("default:grow",
						"top:43dlu, $lgap, 31dlu, $lgap, 138dlu, $lgap, default:grow, $lgap, default"));

		// ======== menuBar1 ========
		{

			// ======== menu1 ========
			{
				menu1.setText("Setting");

				// ======== menu4 ========
				{
					menu4.setText("Save Accounts");

					// ---- mn_lv ----
					mn_lv.setIcon(new ImageIcon(getClass().getResource(
							"/outsrc/images/lv.png")));
					mn_lv.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
							KeyEvent.CTRL_MASK | KeyEvent.ALT_MASK));
					mn_lv.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							mn_lvSave(e);
						}
					});
					menu4.add(mn_lv);

					// ---- mn_fb ----
					mn_fb.setIcon(new ImageIcon(getClass().getResource(
							"/outsrc/images/fb.png")));
					mn_fb.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
							KeyEvent.CTRL_MASK | KeyEvent.ALT_MASK));
					mn_fb.setSelectedIcon(new ImageIcon(getClass().getResource(
							"/outsrc/images/fb.png")));
					mn_fb.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							mn_fbSave(e);
						}
					});
					menu4.add(mn_fb);

					// ---- mn_gg ----
					mn_gg.setIcon(new ImageIcon(getClass().getResource(
							"/outsrc/images/gg.png")));
					mn_gg.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
							KeyEvent.CTRL_MASK | KeyEvent.ALT_MASK));
					mn_gg.setSelectedIcon(new ImageIcon(getClass().getResource(
							"/outsrc/images/gg.png")));
					mn_gg.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							mn_ggSave(e);
						}
					});
					menu4.add(mn_gg);
				}
				menu1.add(menu4);

				// ---- mn_autoSkip ----
				mn_autoSkip.setText("Auto Skip");
				mn_autoSkip
						.setToolTipText("[Table Log] Skip if the record appears error");
				mn_autoSkip.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						save_AutoSkip(e);
					}
				});
				menu1.add(mn_autoSkip);
			}
			menuBar1.add(menu1);

			// ======== menu2 ========
			{
				menu2.setText("Tool");

				// ---- mn_sendmail ----
				mn_sendmail.setText("Send Mail");
				mn_sendmail.setAccelerator(KeyStroke.getKeyStroke(
						KeyEvent.VK_M, KeyEvent.CTRL_MASK));
				mn_sendmail.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						mn_sendmailActionPerformed(e);
					}
				});
				menu2.add(mn_sendmail);

				// ---- mn_vlog ----
				mn_vlog.setText("View Log");
				mn_vlog.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,
						KeyEvent.CTRL_MASK));
				mn_vlog.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						viewlog(e);
					}
				});
				menu2.add(mn_vlog);

				// ---- mn_slog ----
				mn_slog.setText("Save Log");
				mn_slog.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
						KeyEvent.CTRL_MASK));
				mn_slog.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						showSaveLog(e);
					}
				});
				menu2.add(mn_slog);
			}
			menuBar1.add(menu2);

			// ======== menu3 ========
			{
				menu3.setText("Help");

				// ---- menuItem1 ----
				menuItem1.setText("Welcome");
				menu3.add(menuItem1);

				// ---- menuItem4 ----
				menuItem4.setText("Help Contents");
				menuItem4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,
						KeyEvent.CTRL_MASK));
				menu3.add(menuItem4);

				// ---- menuItem3 ----
				menuItem3.setText("Check Updates");
				menuItem3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
						KeyEvent.CTRL_MASK));
				menu3.add(menuItem3);

				// ---- menuItem2 ----
				menuItem2.setText("About");
				menuItem2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
						KeyEvent.CTRL_MASK));
				menu3.add(menuItem2);
			}
			menuBar1.add(menu3);

			// ---- btn_showreferrals ----
			btn_showreferrals.setText("Show Refferals");
			btn_showreferrals.setContentAreaFilled(false);
			btn_showreferrals.setFocusPainted(false);
			btn_showreferrals.setBorder(Borders.DIALOG_BORDER);
			btn_showreferrals.setEnabled(false);
			btn_showreferrals.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					btn_showreferrals(e);
				}
			});
			menuBar1.add(btn_showreferrals);
		}
		setJMenuBar(menuBar1);

		// ======== scrollPane1 ========
		{
			scrollPane1.setBorder(new EtchedBorder());

			// ======== panel2 ========
			{
				panel2.setLayout(new FormLayout("default, $lcgap, 98dlu:grow",
						"18dlu:grow, $lgap, 15dlu:grow"));

				// ---- label18 ----
				label18.setText("Refer link");
				panel2.add(label18, CC.xy(1, 1, CC.DEFAULT, CC.TOP));

				// ---- txt_referlink ----
				txt_referlink.setEditable(false);
				panel2.add(txt_referlink, CC.xy(3, 1, CC.FILL, CC.TOP));

				// ---- label14 ----
				label14.setText("Bonus Remain");
				panel2.add(label14, CC.xy(1, 3, CC.DEFAULT, CC.TOP));
				panel2.add(lb_bonusremain, CC.xy(3, 3, CC.DEFAULT, CC.TOP));
			}
			scrollPane1.setViewportView(panel2);
		}
		contentPane.add(scrollPane1, CC.xy(1, 1, CC.DEFAULT, CC.FILL));

		// ======== scrollPane2 ========
		{
			scrollPane2.setBorder(new EtchedBorder());

			// ======== panel1 ========
			{
				panel1.setLayout(new GridBagLayout());
				((GridBagLayout) panel1.getLayout()).columnWidths = new int[] {
						77, 107, 100, 88, 0 };
				((GridBagLayout) panel1.getLayout()).rowHeights = new int[] {
						25, 0, 0 };
				((GridBagLayout) panel1.getLayout()).columnWeights = new double[] {
						0.0, 0.0, 0.0, 1.0, 1.0E-4 };
				((GridBagLayout) panel1.getLayout()).rowWeights = new double[] {
						0.0, 0.0, 1.0E-4 };

				// ---- labcoin ----
				labcoin.setText("Coin");
				panel1.add(labcoin, new GridBagConstraints(0, 0, 1, 1, 0.0,
						0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

				// ---- label15 ----
				label15.setText("Current Click");
				panel1.add(label15, new GridBagConstraints(1, 0, 1, 1, 0.0,
						0.0, GridBagConstraints.WEST,
						GridBagConstraints.VERTICAL, new Insets(0, 0, 5, 5), 0,
						0));

				// ---- label16 ----
				label16.setText("Total Refer");
				panel1.add(label16, new GridBagConstraints(2, 0, 1, 1, 0.0,
						0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

				// ---- label17 ----
				label17.setText("Remain Click");
				panel1.add(label17, new GridBagConstraints(3, 0, 1, 1, 0.0,
						0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));
				panel1.add(lb_coin, new GridBagConstraints(0, 1, 1, 1, 0.0,
						0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));
				panel1.add(lb_currentclick, new GridBagConstraints(1, 1, 1, 1,
						0.0, 0.0, GridBagConstraints.WEST,
						GridBagConstraints.VERTICAL, new Insets(0, 0, 0, 5), 0,
						0));
				panel1.add(lb_totalrefer, new GridBagConstraints(2, 1, 1, 1,
						0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));
				panel1.add(lb_remainclick, new GridBagConstraints(3, 1, 1, 1,
						0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			}
			scrollPane2.setViewportView(panel1);
		}
		contentPane.add(scrollPane2, CC.xy(1, 3, CC.DEFAULT, CC.FILL));

		// ======== tabs ========
		{
			tabs.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
			tabs.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					tabsStateChanged(e);
				}
			});

			// ======== tab_lv ========
			{
				tab_lv.setLayout(new GridBagLayout());
				((GridBagLayout) tab_lv.getLayout()).columnWidths = new int[] {
						143, 0, 0 };
				((GridBagLayout) tab_lv.getLayout()).rowHeights = new int[] {
						36, 36, 0, 0 };
				((GridBagLayout) tab_lv.getLayout()).columnWeights = new double[] {
						0.0, 1.0, 1.0E-4 };
				((GridBagLayout) tab_lv.getLayout()).rowWeights = new double[] {
						0.0, 0.0, 1.0, 1.0E-4 };

				// ---- txt_usernamelv ----
				txt_usernamelv
						.setToolTipText("T\u00ean \u0111\u0103ng nh\u1eadp");
				txt_usernamelv.setBorder(new TitledBorder("Username"));
				tab_lv.add(txt_usernamelv, new GridBagConstraints(0, 0, 1, 1,
						0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

				// ---- txt_passwordlv ----
				txt_passwordlv.setToolTipText("M\u1eadt kh\u1ea9u");
				txt_passwordlv.setBorder(new TitledBorder("Password"));
				tab_lv.add(txt_passwordlv, new GridBagConstraints(1, 0, 1, 1,
						0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

				// ---- btn_loginlv ----
				btn_loginlv.setSelectedIcon(new ImageIcon(getClass()
						.getResource("/outsrc/images/login.png")));
				btn_loginlv.setIcon(new ImageIcon(getClass().getResource(
						"/outsrc/images/login.png")));
				btn_loginlv.setBorder(null);
				btn_loginlv.setContentAreaFilled(false);
				btn_loginlv.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						btn_loginlv(e);
					}
				});
				tab_lv.add(btn_loginlv, new GridBagConstraints(0, 1, 1, 1, 0.0,
						0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
				tab_lv.add(lb_statuslv, new GridBagConstraints(1, 1, 1, 1, 0.0,
						0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

				// ======== pn_lv_action ========
				{
					pn_lv_action.setVisible(false);
					pn_lv_action.setLayout(new GridBagLayout());
					((GridBagLayout) pn_lv_action.getLayout()).columnWidths = new int[] {
							0, 0 };
					((GridBagLayout) pn_lv_action.getLayout()).rowHeights = new int[] {
							0, 0 };
					((GridBagLayout) pn_lv_action.getLayout()).columnWeights = new double[] {
							1.0, 1.0E-4 };
					((GridBagLayout) pn_lv_action.getLayout()).rowWeights = new double[] {
							1.0, 1.0E-4 };

					// ---- btn_lv_surf ----
					btn_lv_surf.setText("Surf");
					btn_lv_surf.setHorizontalAlignment(SwingConstants.LEFT);
					btn_lv_surf.setBorder(null);
					btn_lv_surf.setContentAreaFilled(false);
					btn_lv_surf.setCursor(Cursor
							.getPredefinedCursor(Cursor.HAND_CURSOR));
					btn_lv_surf.setForeground(new Color(0, 153, 255));
					btn_lv_surf.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							btn_lv_surf(e);
						}
					});
					pn_lv_action.add(btn_lv_surf, new GridBagConstraints(0, 0,
							1, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.VERTICAL,
							new Insets(0, 0, 0, 0), 0, 0));
				}
				tab_lv.add(pn_lv_action, new GridBagConstraints(1, 2, 1, 1,
						0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			}
			tabs.addTab(
					"",
					new ImageIcon(getClass().getResource(
							"/outsrc/images/lv_tab.png")), tab_lv);
			tabs.setDisabledIconAt(
					0,
					new ImageIcon(getClass().getResource(
							"/outsrc/images/lv_tab.png")));

			// ======== tab_fb ========
			{
				tab_fb.setLayout(new GridBagLayout());
				((GridBagLayout) tab_fb.getLayout()).columnWidths = new int[] {
						143, 0, 0 };
				((GridBagLayout) tab_fb.getLayout()).rowHeights = new int[] {
						36, 36, 34, 0 };
				((GridBagLayout) tab_fb.getLayout()).columnWeights = new double[] {
						0.0, 1.0, 1.0E-4 };
				((GridBagLayout) tab_fb.getLayout()).rowWeights = new double[] {
						0.0, 0.0, 0.0, 1.0E-4 };

				// ---- txt_usernamefb ----
				txt_usernamefb.setBorder(new TitledBorder("Username"));
				tab_fb.add(txt_usernamefb, new GridBagConstraints(0, 0, 1, 1,
						0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

				// ---- txt_passwordfb ----
				txt_passwordfb.setBorder(new TitledBorder("Password"));
				tab_fb.add(txt_passwordfb, new GridBagConstraints(1, 0, 1, 1,
						0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

				// ---- btn_loginfb ----
				btn_loginfb.setIcon(new ImageIcon(getClass().getResource(
						"/outsrc/images/login.png")));
				btn_loginfb.setSelectedIcon(new ImageIcon(getClass()
						.getResource("/outsrc/images/login.png")));
				btn_loginfb.setBorder(null);
				btn_loginfb.setContentAreaFilled(false);
				btn_loginfb.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						btn_loginfb(e);
					}
				});
				tab_fb.add(btn_loginfb, new GridBagConstraints(0, 1, 1, 1, 0.0,
						0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
				tab_fb.add(lb_statusfb, new GridBagConstraints(1, 1, 1, 1, 0.0,
						0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

				// ======== panel3 ========
				{
					panel3.setLayout(new FormLayout("default:grow",
							"default:grow"));
				}
				tab_fb.add(panel3, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 0, 5), 0, 0));

				// ======== pn_fb_action ========
				{
					pn_fb_action.setAutoscrolls(true);
					pn_fb_action.setVisible(false);
					pn_fb_action.setLayout(new FormLayout(
							"4*(default, $lcgap), default", "default:grow"));

					// ---- btn_fb_fanpage ----
					btn_fb_fanpage.setIcon(null);
					btn_fb_fanpage.setContentAreaFilled(false);
					btn_fb_fanpage.setToolTipText("Like Fanpage");
					btn_fb_fanpage.setBorder(null);
					btn_fb_fanpage.setText("Fanpage");
					btn_fb_fanpage.setForeground(new Color(0, 102, 255));
					btn_fb_fanpage.setCursor(Cursor
							.getPredefinedCursor(Cursor.HAND_CURSOR));
					btn_fb_fanpage.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							btn_fb_fanpage(e);
						}
					});
					pn_fb_action.add(btn_fb_fanpage, CC.xy(1, 1));

					// ---- btn_fb_website ----
					btn_fb_website.setText("Website");
					btn_fb_website.setForeground(new Color(0, 102, 255));
					btn_fb_website.setToolTipText("Website");
					btn_fb_website.setBorder(null);
					btn_fb_website.setContentAreaFilled(false);
					btn_fb_website.setCursor(Cursor
							.getPredefinedCursor(Cursor.HAND_CURSOR));
					btn_fb_website.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							btn_fb_website(e);
						}
					});
					pn_fb_action.add(btn_fb_website, CC.xy(3, 1));

					// ---- btn_fb_photo ----
					btn_fb_photo.setIcon(null);
					btn_fb_photo.setContentAreaFilled(false);
					btn_fb_photo.setToolTipText("Like Photo");
					btn_fb_photo.setBorder(null);
					btn_fb_photo.setForeground(new Color(0, 102, 255));
					btn_fb_photo.setText("Photo");
					btn_fb_photo.setCursor(Cursor
							.getPredefinedCursor(Cursor.HAND_CURSOR));
					btn_fb_photo.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							btn_fb_photo(e);
						}
					});
					pn_fb_action.add(btn_fb_photo,
							CC.xy(5, 1, CC.DEFAULT, CC.FILL));

					// ---- btn_fb_follow ----
					btn_fb_follow.setIcon(null);
					btn_fb_follow.setContentAreaFilled(false);
					btn_fb_follow.setToolTipText("Follow");
					btn_fb_follow.setBorder(null);
					btn_fb_follow.setForeground(new Color(0, 102, 255));
					btn_fb_follow.setText("Follow");
					btn_fb_follow.setCursor(Cursor
							.getPredefinedCursor(Cursor.HAND_CURSOR));
					btn_fb_follow.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							btn_fb_follow(e);
						}
					});
					pn_fb_action.add(btn_fb_follow,
							CC.xy(7, 1, CC.FILL, CC.FILL));

					// ---- btn_fb_share ----
					btn_fb_share.setIcon(null);
					btn_fb_share.setContentAreaFilled(false);
					btn_fb_share.setToolTipText("Share on Facebook");
					btn_fb_share.setBorder(null);
					btn_fb_share.setForeground(new Color(0, 102, 255));
					btn_fb_share.setText("Share on Facebook");
					btn_fb_share.setCursor(Cursor
							.getPredefinedCursor(Cursor.HAND_CURSOR));
					btn_fb_share.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							btn_fb_share(e);
						}
					});
					pn_fb_action.add(btn_fb_share,
							CC.xy(9, 1, CC.DEFAULT, CC.FILL));
				}
				tab_fb.add(pn_fb_action, new GridBagConstraints(1, 2, 1, 1,
						0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			}
			tabs.addTab(
					"",
					new ImageIcon(getClass().getResource(
							"/outsrc/images/fb_tab.png")), tab_fb);
			tabs.setDisabledIconAt(
					1,
					new ImageIcon(getClass().getResource(
							"/outsrc/images/fb_tab.png")));
			tabs.setEnabledAt(1, false);

			// ======== tab_gg ========
			{
				tab_gg.setLayout(new GridBagLayout());
				((GridBagLayout) tab_gg.getLayout()).columnWidths = new int[] {
						143, 0, 0 };
				((GridBagLayout) tab_gg.getLayout()).rowHeights = new int[] {
						36, 36, 0, 0 };
				((GridBagLayout) tab_gg.getLayout()).columnWeights = new double[] {
						0.0, 1.0, 1.0E-4 };
				((GridBagLayout) tab_gg.getLayout()).rowWeights = new double[] {
						0.0, 0.0, 0.0, 1.0E-4 };

				// ---- txt_usernamegg ----
				txt_usernamegg.setBorder(new TitledBorder("Username"));
				tab_gg.add(txt_usernamegg, new GridBagConstraints(0, 0, 1, 1,
						0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

				// ---- txt_passwordgg ----
				txt_passwordgg.setBorder(new TitledBorder("Password"));
				tab_gg.add(txt_passwordgg, new GridBagConstraints(1, 0, 1, 1,
						0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

				// ---- btn_logingg ----
				btn_logingg.setIcon(new ImageIcon(getClass().getResource(
						"/outsrc/images/login.png")));
				btn_logingg.setSelectedIcon(new ImageIcon(getClass()
						.getResource("/outsrc/images/login.png")));
				btn_logingg.setBorder(null);
				btn_logingg.setContentAreaFilled(false);
				btn_logingg.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						btn_logingg(e);
					}
				});
				tab_gg.add(btn_logingg, new GridBagConstraints(0, 1, 1, 1, 0.0,
						0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
				tab_gg.add(lb_statusgg, new GridBagConstraints(1, 1, 1, 1, 0.0,
						0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

				// ======== pn_gg_action ========
				{
					pn_gg_action.setVisible(false);
					pn_gg_action.setLayout(new GridBagLayout());
					((GridBagLayout) pn_gg_action.getLayout()).columnWidths = new int[] {
							0, 0, 0, 0, 0 };
					((GridBagLayout) pn_gg_action.getLayout()).rowHeights = new int[] {
							0, 0 };
					((GridBagLayout) pn_gg_action.getLayout()).columnWeights = new double[] {
							0.0, 0.0, 0.0, 0.0, 1.0E-4 };
					((GridBagLayout) pn_gg_action.getLayout()).rowWeights = new double[] {
							0.0, 1.0E-4 };

					// ---- btn_likevideo ----
					btn_likevideo.setIcon(new ImageIcon(getClass().getResource(
							"/outsrc/images/likevideo.png")));
					btn_likevideo.setSelectedIcon(new ImageIcon(getClass()
							.getResource("/outsrc/images/likevideo.png")));
					btn_likevideo.setBorder(null);
					btn_likevideo.setContentAreaFilled(false);
					btn_likevideo.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							likevideoyou(e);
						}
					});
					pn_gg_action.add(btn_likevideo, new GridBagConstraints(0,
							0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0,
							0));

					// ---- btn_subscribeyou ----
					btn_subscribeyou.setIcon(new ImageIcon(getClass()
							.getResource("/outsrc/images/subcribe.png")));
					btn_subscribeyou.setSelectedIcon(new ImageIcon(getClass()
							.getResource("/outsrc/images/subcribe.png")));
					btn_subscribeyou.setBorder(null);
					btn_subscribeyou.setContentAreaFilled(false);
					btn_subscribeyou.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							btn_subscribeyou(e);
						}
					});
					pn_gg_action.add(btn_subscribeyou, new GridBagConstraints(
							1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0,
							0));

					// ---- btn_gg_plus ----
					btn_gg_plus.setContentAreaFilled(false);
					btn_gg_plus.setBorder(null);
					btn_gg_plus.setIcon(new ImageIcon(getClass().getResource(
							"/outsrc/images/gg_plus.png")));
					btn_gg_plus.setSelectedIcon(new ImageIcon(getClass()
							.getResource("/outsrc/images/gg_plus.png")));
					btn_gg_plus.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							btn_gg_plus(e);
						}
					});
					pn_gg_action.add(btn_gg_plus, new GridBagConstraints(2, 0,
							1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0,
							0));

					// ---- btn_gg_view ----
					btn_gg_view.setBorder(null);
					btn_gg_view.setContentAreaFilled(false);
					btn_gg_view.setIcon(new ImageIcon(getClass().getResource(
							"/outsrc/images/movie.png")));
					btn_gg_view.setSelectedIcon(new ImageIcon(getClass()
							.getResource("/outsrc/images/movie.png")));
					btn_gg_view.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							btn_gg_view(e);
						}
					});
					pn_gg_action.add(btn_gg_view, new GridBagConstraints(3, 0,
							1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0,
							0));
				}
				tab_gg.add(pn_gg_action, new GridBagConstraints(1, 2, 1, 1,
						0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			}
			tabs.addTab(
					"",
					new ImageIcon(getClass().getResource(
							"/outsrc/images/gg_tab.png")), tab_gg);
			tabs.setDisabledIconAt(
					2,
					new ImageIcon(getClass().getResource(
							"/outsrc/images/gg_tab.png")));
			tabs.setEnabledAt(2, false);

			// ======== tab_register ========
			{
				tab_register.setLayout(new GridBagLayout());
				((GridBagLayout) tab_register.getLayout()).columnWidths = new int[] {
						314, 0, 0 };
				((GridBagLayout) tab_register.getLayout()).rowHeights = new int[] {
						0, 0, 0, 0 };
				((GridBagLayout) tab_register.getLayout()).columnWeights = new double[] {
						0.0, 1.0, 1.0E-4 };
				((GridBagLayout) tab_register.getLayout()).rowWeights = new double[] {
						1.0, 1.0, 1.0, 1.0E-4 };

				// ---- txt_res_username ----
				txt_res_username
						.setFocusLostBehavior(JFormattedTextField.COMMIT);
				txt_res_username.setBorder(new TitledBorder("Username"));
				tab_register.add(txt_res_username, new GridBagConstraints(0, 0,
						1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 1, 1), 0, 0));

				// ---- txt_res_password ----
				txt_res_password
						.setFocusLostBehavior(JFormattedTextField.COMMIT);
				txt_res_password.setBorder(new TitledBorder("Password"));
				tab_register.add(txt_res_password, new GridBagConstraints(1, 0,
						1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 1, 0), 0, 0));

				// ---- img_res_captcha ----
				img_res_captcha.setIcon(null);
				img_res_captcha.setBorder(new TitledBorder("Captcha"));
				img_res_captcha.setAutoscrolls(true);
				img_res_captcha.setOpaque(true);
				img_res_captcha.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						captcha_reload(e);
					}
				});
				tab_register.add(img_res_captcha, new GridBagConstraints(0, 1,
						1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 1, 1), 0, 0));

				// ---- txt_res_captcha ----
				txt_res_captcha
						.setFocusLostBehavior(JFormattedTextField.COMMIT);
				txt_res_captcha.setBorder(new TitledBorder("Fill captcha"));
				tab_register.add(txt_res_captcha, new GridBagConstraints(1, 1,
						1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 1, 0), 0, 0));

				// ---- txt_res_email ----
				txt_res_email.setFocusLostBehavior(JFormattedTextField.COMMIT);
				txt_res_email.setBorder(new TitledBorder("Email"));
				tab_register.add(txt_res_email, new GridBagConstraints(0, 2, 1,
						1, 0.0, 0.0, GridBagConstraints.NORTH,
						GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 1),
						0, 0));

				// ---- btn_register ----
				btn_register.setText("Register");
				btn_register.setBorder(new EmptyBorder(5, 5, 5, 5));
				btn_register.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						btn_registerActionPerformed(e);
					}
				});
				tab_register.add(btn_register, new GridBagConstraints(1, 2, 1,
						1, 0.0, 0.0, GridBagConstraints.NORTH,
						GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			}
			tabs.addTab("\u0110\u0103ng k\u00fd", tab_register);
		}
		contentPane.add(tabs, CC.xy(1, 5, CC.FILL, CC.FILL));

		// ======== pn_webview ========
		{
			pn_webview.setBorder(new EtchedBorder());
			pn_webview.setAutoscrolls(true);
			pn_webview.setDoubleBuffered(true);
			pn_webview.setFocusCycleRoot(true);
			pn_webview.setFocusTraversalPolicyProvider(true);
			pn_webview.setInheritsPopupMenu(true);
		}
		contentPane.add(pn_webview, CC.xy(1, 7, CC.FILL, CC.FILL));

		// ======== panel8 ========
		{
			panel8.setLayout(new GridBagLayout());
			((GridBagLayout) panel8.getLayout()).columnWidths = new int[] { 68,
					0, 0 };
			((GridBagLayout) panel8.getLayout()).rowHeights = new int[] { 0, 0 };
			((GridBagLayout) panel8.getLayout()).columnWeights = new double[] {
					0.0, 1.0, 1.0E-4 };
			((GridBagLayout) panel8.getLayout()).rowWeights = new double[] {
					1.0, 1.0E-4 };

			// ---- label7 ----
			label7.setText("Status");
			panel8.add(label7, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 5), 0, 0));
			panel8.add(lb_status, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 0), 0, 0));
		}
		contentPane.add(panel8, CC.xy(1, 9));
		pack();
		setLocationRelativeTo(getOwner());
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	public JMenuBar menuBar1;
	public JMenu menu1;
	public JMenu menu4;
	public JCheckBoxMenuItem mn_lv;
	public JCheckBoxMenuItem mn_fb;
	public JCheckBoxMenuItem mn_gg;
	public JCheckBoxMenuItem mn_autoSkip;
	public JMenu menu2;
	public JMenuItem mn_sendmail;
	public JMenuItem mn_vlog;
	public JMenuItem mn_slog;
	public JMenu menu3;
	public JMenuItem menuItem1;
	public JMenuItem menuItem4;
	public JMenuItem menuItem3;
	public JMenuItem menuItem2;
	public JButton btn_showreferrals;
	public JScrollPane scrollPane1;
	public JPanel panel2;
	public JLabel label18;
	public JTextField txt_referlink;
	public JLabel label14;
	public JLabel lb_bonusremain;
	public JScrollPane scrollPane2;
	public JPanel panel1;
	public JLabel labcoin;
	public JLabel label15;
	public JLabel label16;
	public JLabel label17;
	public JLabel lb_coin;
	public JLabel lb_currentclick;
	public JLabel lb_totalrefer;
	public JLabel lb_remainclick;
	public JTabbedPane tabs;
	public JPanel tab_lv;
	public JFormattedTextField txt_usernamelv;
	public JPasswordField txt_passwordlv;
	public JToggleButton btn_loginlv;
	public JLabel lb_statuslv;
	public JPanel pn_lv_action;
	public JButton btn_lv_surf;
	public JPanel tab_fb;
	public JFormattedTextField txt_usernamefb;
	public JPasswordField txt_passwordfb;
	public JToggleButton btn_loginfb;
	public JLabel lb_statusfb;
	public JPanel panel3;
	public JPanel pn_fb_action;
	public JButton btn_fb_fanpage;
	public JButton btn_fb_website;
	public JButton btn_fb_photo;
	public JButton btn_fb_follow;
	public JButton btn_fb_share;
	public JPanel tab_gg;
	public JFormattedTextField txt_usernamegg;
	public JPasswordField txt_passwordgg;
	public JToggleButton btn_logingg;
	public JLabel lb_statusgg;
	public JPanel pn_gg_action;
	public JButton btn_likevideo;
	public JToggleButton btn_subscribeyou;
	public JButton btn_gg_plus;
	public JButton btn_gg_view;
	public JPanel tab_register;
	public JFormattedTextField txt_res_username;
	public JFormattedTextField txt_res_password;
	public JLabel img_res_captcha;
	public JFormattedTextField txt_res_captcha;
	public JFormattedTextField txt_res_email;
	public JButton btn_register;
	public JScrollPane pn_webview;
	public JPanel panel8;
	public JLabel label7;
	public JLabel lb_status;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
