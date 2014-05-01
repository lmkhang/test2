package views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JTabbedPane;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.SwingConstants;
import javax.swing.JSeparator;

import java.awt.Font;

import javax.swing.ImageIcon;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPasswordField;

import java.awt.Panel;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JFormattedTextField;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.DropMode;
import javax.swing.JEditorPane;

import java.awt.SystemColor;
import java.awt.Label;
import java.awt.Toolkit;

import javax.swing.KeyStroke;

import org.apache.poi.hssf.record.LabelRecord;

import chrriis.dj.nativeswing.NativeSwing;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowserWindow;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserCommandEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserListener;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserNavigationEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserWindowFactory;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserWindowOpeningEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserWindowWillOpenEvent;
import Configs.Message;
import Configs.Setting;
import GUI.browser;
import Libs.LoadChilkat;
import Models.Facebook;
import Models.Likeviet;
import Thread.autofb;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainView extends JFrame {

	private JPanel contentPane;
	private JTextField usernameLikeViet;
	private JPasswordField passwordFieldLikeViet;
	private JTextField textField_1;
	private JPasswordField passwordField_1;
	private final JPanel panelLoginO4F = new JPanel();
	private JTextField usernameFacebook;
	private JPasswordField passwordFacebook;
	private JTextField textField_3;
	private JPasswordField passwordField_3;
	private JTextField textField_4;
	private JPasswordField passwordField_4;
	private JPasswordField passwordField_5;
	private JTextField textField_5;
	private JLabel lblUsername;// label likeviet Username
	private JLabel lblPassword;// label likeviet Password
	private JLabel labelLinkRefer;
	private JTabbedPane tabbedPane_1;
	private HistoryReport rp = new HistoryReport();
	private JPanel mainPanel;
	private JPanel panelAfterLoginO4F;
	private JPanel panelO4F;
	private JPanel panelLikeViet;
	private JPanel panelLoginLikeViet;
	private JPanel panelAfterLoginLikeViet;
	private JPanel panelRegisterLikeviet;
	private JPanel panelLoginFacebook;
	private JPanel panelFacebook;
	private JPanel panelAfterLoginFacebook;
	private JPanel panelLoginGoogle;
	private JPanel panelGoogle;
	private JPanel panelAfterLoginGoogle;
	private JScrollPane panel_5;
	private JScrollPane panel_6;
	private JLabel labelCoin;
	private JLabel labelCurrentClick;
	private JLabel labelTotalRefer;
	private JLabel labelRemainClick;
	private JLabel labelFanpage;
	private JLabel labelWebsite;
	private JLabel labelPhoto;
	private JLabel labelFollow;
	private JLabel labelShareOnFacebook;
	private String urlO4fForgotPassword = "http://only4free.net";
	private String urlO4fRegister = "http://only4free.net";
	private String urlO4fChangePassword = "http://only4free.net";
	private String urlLikeVietForgotPassword = "http://only4free.net";
	private String urlLikeVietRegister = "http://likeviet.com.vn/recover.php";
	private String urlLikeVietChangePassword = "http://likeviet.com.vn/recover.php";
	private String urlFacebookForgotPassword = "https://www.facebook.com/r.php";
	private String urlFacebookRegister = "https://www.facebook.com/login/identify?ctx=recover";
	private String urlFacebookChangePassword = "https://www.facebook.com/login/identify?ctx=recover";
	private String urlGoogleForgotPassword = "https://accounts.google.com/SignUp";
	private String urlGoogleRegister = "https://www.google.com/accounts/recovery";
	private String urlGoogleChangePassword = "https://www.google.com/accounts/recovery";
	private String urlDonate = "http://only4free.net/donate/";

	private String FBlinkFanpage = "/p.php?p=facebook";
	private String FBsiteCheckFanpage = "/system/modules/facebook/process.php";
	private String FBreferFanpage = FBsiteCheckFanpage;

	/* Adv */
	private JWebBrowser webAds;

	JLabel lblReferOnly4Free;
	Locale vietnamLocale = new Locale("vi", "VN");
	Locale englistLocale = new Locale("en", "US");

	ResourceBundle bundleDefault = ResourceBundle.getBundle("lang");

	// bat dau resource bundle
	JMenu mnSetting;

	// ket thuc resource bundle

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NativeSwing.initialize();
					NativeInterface.open();
					MainView frame = new MainView();
					frame.setSize(750, 700);
					frame.setLocation(0, 0);
					frame.setResizable(false);
					frame.setVisible(true);
					frame.setTabbedPanelEnable();
					// add adv
					frame.loadAdsense(frame.panel_5, Setting.urlAdsenses()
							.get("24h"));
					frame.loadAdsense(frame.panel_6, Setting.urlAdsenses()
							.get("docbao"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainView() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				dispose();
				System.exit(0);
			}
		});
		setTitle("Like App Only 4 Free");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 930);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnSetting = new JMenu(bundleDefault.getString("o4f.setting"));
		menuBar.add(mnSetting);

		JMenu mnLoadAccounts = new JMenu(
				bundleDefault.getString("o4f.loadAccountCookies"));
		mnSetting.add(mnLoadAccounts);

		JMenuItem mntmFacebook = new JMenuItem(
				bundleDefault.getString("o4f.facebook"));
		// load account cookies facebook :chuaxong
		mntmFacebook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnLoadAccounts.add(mntmFacebook);

		JMenuItem mntmGooglePlus = new JMenuItem(
				bundleDefault.getString("o4f.google"));
		// Load Account Google :chuaxong
		mntmGooglePlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnLoadAccounts.add(mntmGooglePlus);

		JMenuItem mntmLikeVit = new JMenuItem(
				bundleDefault.getString("o4f.likeviet"));
		// Load Account LikeViet
		mntmLikeVit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnLoadAccounts.add(mntmLikeVit);

		JMenu mnTool = new JMenu(bundleDefault.getString("o4f.view"));
		menuBar.add(mnTool);

		JMenuItem mntmHistoryReport = new JMenuItem(
				bundleDefault.getString("o4f.historyReport"));
		mntmHistoryReport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
				InputEvent.CTRL_MASK));
		// hien thong tin lich su :chuaxong
		mntmHistoryReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rp.setVisible(true);
			}
		});
		mnTool.add(mntmHistoryReport);

		JMenuItem mntmShowRefferals = new JMenuItem(
				bundleDefault.getString("o4f.showRefferals"));
		mntmShowRefferals.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_MASK));
		// show reffrals :chuaxong
		mntmShowRefferals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnTool.add(mntmShowRefferals);

		JMenuItem mntmProcessStats = new JMenuItem(
				bundleDefault.getString("o4f.processStats"));
		mntmProcessStats.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,
				InputEvent.CTRL_MASK));

		// hien thi status process :chuaxong
		mntmProcessStats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnTool.add(mntmProcessStats);

		JMenu mnHelp = new JMenu(bundleDefault.getString("o4f.help"));
		menuBar.add(mnHelp);

		JMenuItem mntmWelcome = new JMenuItem(
				bundleDefault.getString("o4f.welcome"));
		mntmWelcome.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
				InputEvent.CTRL_MASK));
		// Welcome :chuaxong
		mntmWelcome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnHelp.add(mntmWelcome);

		JMenuItem mntmHelpContents = new JMenuItem(
				bundleDefault.getString("o4f.helpContents"));
		mntmHelpContents.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,
				InputEvent.CTRL_MASK));
		// Help contents :chuaxong
		mntmHelpContents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnHelp.add(mntmHelpContents);

		JMenuItem mntmAgreement = new JMenuItem(
				bundleDefault.getString("o4f.termAndAgreement"));
		// term and agreement :chuaxong
		mntmAgreement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnHelp.add(mntmAgreement);

		JMenuItem mntmFeedback = new JMenuItem(
				bundleDefault.getString("o4f.feedback"));
		mntmFeedback.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
				InputEvent.CTRL_MASK));

		// feedback to site :chuaxong
		mntmFeedback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnHelp.add(mntmFeedback);

		JMenuItem mntmSuggestSite = new JMenuItem(
				bundleDefault.getString("o4f.suggestSite"));
		mntmSuggestSite.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		// suggest Site:chuaxong
		mntmSuggestSite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		mnHelp.add(mntmSuggestSite);

		JMenuItem mntmAbout = new JMenuItem(
				bundleDefault.getString("o4f.about"));
		mntmAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));

		// About :chuaxong
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnHelp.add(mntmAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		panel.setSize(800, 50);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		mainPanel = new JPanel();
		tabbedPane.addTab(bundleDefault.getString("o4f.mainApp"), null,
				mainPanel, null);
		mainPanel.setLayout(null);

		JLabel lblReferLink = new JLabel(
				bundleDefault.getString("o4f.referLink"));
		lblReferLink.setFont(new Font("Arial", Font.BOLD, 13));
		lblReferLink.setBounds(62, 106, 88, 16);
		lblReferLink.setHorizontalAlignment(SwingConstants.RIGHT);
		mainPanel.add(lblReferLink);

		labelLinkRefer = new JLabel("http://likeviet.com.vn/?ref=6161");

		// click double thi copy vao clipboard
		labelLinkRefer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 2) {
					String myString = labelLinkRefer.getText().trim();
					StringSelection stringSelection = new StringSelection(
							myString);
					Clipboard clpbrd = Toolkit.getDefaultToolkit()
							.getSystemClipboard();
					clpbrd.setContents(stringSelection, null);
				}
			}
		});
		labelLinkRefer.setBounds(162, 106, 317, 16);
		labelLinkRefer.setToolTipText(bundleDefault
				.getString("o4f.doubleClickToCopyToClipboard"));
		mainPanel.add(labelLinkRefer);

		JLabel lblBonusRemain = new JLabel(
				bundleDefault.getString("o4f.bonusRemain"));
		lblBonusRemain.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBonusRemain.setFont(new Font("Arial", Font.BOLD, 13));
		lblBonusRemain.setBounds(38, 134, 114, 16);
		mainPanel.add(lblBonusRemain);

		JLabel lblNewLabel_1 = new JLabel(
				bundleDefault.getString("o4f.notRemain"));
		lblNewLabel_1.setBounds(164, 134, 70, 16);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		mainPanel.add(lblNewLabel_1);

		JSeparator separator = new JSeparator();
		separator.setBounds(6, 150, 707, 12);
		mainPanel.add(separator);

		JLabel lblYourInfo = new JLabel(
				bundleDefault.getString("o4f.accountsInfo"));
		lblYourInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblYourInfo.setFont(new Font("Arial", Font.BOLD, 14));
		lblYourInfo.setBounds(24, 163, 126, 16);
		mainPanel.add(lblYourInfo);

		JLabel lblCoint = new JLabel(bundleDefault.getString("o4f.coin"));
		lblCoint.setBounds(131, 191, 61, 16);
		mainPanel.add(lblCoint);

		JLabel lblCurrentClick = new JLabel(
				bundleDefault.getString("o4f.currentClick"));
		lblCurrentClick.setBounds(220, 191, 83, 16);
		mainPanel.add(lblCurrentClick);

		JLabel lblNewLabel_2 = new JLabel(
				bundleDefault.getString("o4f.totalRefer"));
		lblNewLabel_2.setBounds(351, 191, 116, 16);
		mainPanel.add(lblNewLabel_2);

		JLabel lblRemainClick = new JLabel(
				bundleDefault.getString("o4f.remainClick"));
		lblRemainClick.setBounds(479, 191, 98, 16);
		mainPanel.add(lblRemainClick);

		labelCoin = new JLabel("8.000");
		labelCoin.setHorizontalAlignment(SwingConstants.RIGHT);
		labelCoin.setBounds(102, 219, 61, 16);
		mainPanel.add(labelCoin);

		labelCurrentClick = new JLabel("843");
		labelCurrentClick.setHorizontalAlignment(SwingConstants.RIGHT);
		labelCurrentClick.setBounds(242, 219, 61, 16);
		mainPanel.add(labelCurrentClick);

		labelTotalRefer = new JLabel("82");
		labelTotalRefer.setHorizontalAlignment(SwingConstants.RIGHT);
		labelTotalRefer.setBounds(361, 219, 61, 16);
		mainPanel.add(labelTotalRefer);

		labelRemainClick = new JLabel("99");
		labelRemainClick.setHorizontalAlignment(SwingConstants.RIGHT);
		labelRemainClick.setBounds(502, 219, 61, 16);
		mainPanel.add(labelRemainClick);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(6, 243, 707, 12);
		mainPanel.add(separator_1);

		tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(6, 267, 711, 196);
		mainPanel.add(tabbedPane_1);

		panelO4F = new JPanel();
		tabbedPane_1.addTab(
				"Only4Free",
				new ImageIcon(MainView.class
						.getResource("/images/onlyforfreelogo40x40.jpg")),
				panelO4F, null);
		panelO4F.setLayout(null);
		panelLoginO4F.setBounds(6, 6, 678, 138);
		panelO4F.add(panelLoginO4F);
		panelLoginO4F.setLayout(null);

		JLabel lblLogin = new JLabel(bundleDefault.getString("o4f.loginToO4f"));
		lblLogin.setBounds(6, 6, 116, 16);
		panelLoginO4F.add(lblLogin);
		lblLogin.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel lblNewLabel = new JLabel(bundleDefault.getString("o4f.username"));
		lblNewLabel.setBounds(6, 34, 116, 16);
		panelLoginO4F.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		textField_1 = new JTextField();
		textField_1.setBounds(134, 28, 172, 28);
		panelLoginO4F.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel(
				bundleDefault.getString("o4f.password"));
		lblNewLabel_3.setBounds(6, 62, 116, 16);
		panelLoginO4F.add(lblNewLabel_3);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(134, 56, 172, 28);
		panelLoginO4F.add(passwordField_1);

		JButton btnForgotPasswordO4F = new JButton(
				bundleDefault.getString("o4f.forgotPassword"));

		// forgot password O4F :chuaxong
		btnForgotPasswordO4F.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// openBrowser(urlO4fForgotPassword);
				browser.createContent("Forgot password", urlO4fForgotPassword);
			}
		});
		btnForgotPasswordO4F.setBounds(335, 96, 146, 29);
		panelLoginO4F.add(btnForgotPasswordO4F);

		JButton btnLoginO4F = new JButton(bundleDefault.getString("o4f.login"));
		// login to O4F
		btnLoginO4F.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (loginToO4F("", "", true)) {
					panelLoginO4F.setVisible(false);
					panelO4F.add(panelAfterLoginO4F);
					panelAfterLoginO4F.setVisible(true);
					panelAfterLoginO4F.setBounds(6, 6, 678, 138);

					tabbedPane_1.setEnabledAt(1, true);// enable tab likeviet
					tabbedPane_1.setEnabledAt(4, true);// enable tab register
														// likeviet
				} else {
					// thong bao loi. :chuaxong
				}
			}
		});
		btnLoginO4F.setBounds(160, 96, 146, 29);
		panelLoginO4F.add(btnLoginO4F);

		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(318, 10, 19, 115);
		panelLoginO4F.add(separator_3);

		final JEditorPane dtrpnifYouDont = new JEditorPane();
		dtrpnifYouDont.setBackground(UIManager.getColor("Button.background"));
		dtrpnifYouDont.setEditable(false);
		dtrpnifYouDont.setContentType("text/html");
		dtrpnifYouDont
				.setText("<html><span style=\"text-align:justify;\">If you don't have account. Please register O4F Account. Or you don't remember account password please click Forgot password button below. <br />Thanks you!</span></html>");
		dtrpnifYouDont.setBounds(335, 28, 337, 67);
		panelLoginO4F.add(dtrpnifYouDont);

		JButton btnRegisterO4F = new JButton("Register");
		// Dang ky O4F :chuaxong cho hien qua dang ky likeviet
		btnRegisterO4F.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// openBrowser(urlO4fRegister);
				browser.createContent("Register", urlO4fRegister);
			}
		});
		btnRegisterO4F.setBounds(493, 96, 164, 29);
		panelLoginO4F.add(btnRegisterO4F);

		panelLikeViet = new JPanel();

		tabbedPane_1.addTab(
				"Like Việt",
				new ImageIcon(MainView.class
						.getResource("/images/likeviet.jpg")), panelLikeViet,
				null);
		panelLikeViet.setLayout(null);

		panelLoginLikeViet = new JPanel();

		panelLoginLikeViet.setBounds(6, 6, 678, 138);
		panelLikeViet.add(panelLoginLikeViet);
		panelLoginLikeViet.setLayout(null);

		JLabel lblLoginToLikeviet = new JLabel(
				bundleDefault.getString("o4f.loginToLikeViet"));
		lblLoginToLikeviet.setFont(new Font("Arial", Font.BOLD, 13));
		lblLoginToLikeviet.setBounds(19, 6, 156, 16);
		panelLoginLikeViet.add(lblLoginToLikeviet);

		lblUsername = new JLabel(bundleDefault.getString("o4f.username"));
		lblUsername.setBounds(6, 34, 100, 22);
		panelLoginLikeViet.add(lblUsername);
		lblUsername.setFont(new Font("Arial", Font.PLAIN, 13));
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);

		usernameLikeViet = new JTextField();
		usernameLikeViet.setBounds(113, 30, 235, 28);
		panelLoginLikeViet.add(usernameLikeViet);
		usernameLikeViet.setColumns(10);

		lblPassword = new JLabel(bundleDefault.getString("o4f.password"));
		lblPassword.setBounds(6, 65, 100, 22);
		panelLoginLikeViet.add(lblPassword);
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 13));
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);

		passwordFieldLikeViet = new JPasswordField();
		passwordFieldLikeViet.setBounds(113, 61, 235, 28);
		panelLoginLikeViet.add(passwordFieldLikeViet);

		JButton btnLoginLikeViet = new JButton("Login");
		btnLoginLikeViet.setBounds(192, 109, 156, 29);
		panelLoginLikeViet.add(btnLoginLikeViet);

		// login vao likeviet :chuaxong
		btnLoginLikeViet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (loginToLikeViet(usernameLikeViet.getText(),
						passwordFieldLikeViet.getText(), true)) {

					labelCoin.setText(Setting.lv.getCurrentCoin());
					labelTotalRefer.setText(Setting.lv.getTotalRefferer());
					labelRemainClick.setText(Setting.lv.getRemainClick() + "");
					labelCurrentClick.setText(Setting.lv.getCurrentClick());
					// Setting.lv.getBonus();

					panelLikeViet.add(panelAfterLoginLikeViet);
					panelAfterLoginLikeViet.setBounds(6, 6, 678, 138);
					panelAfterLoginLikeViet.setVisible(true);
					panelLoginLikeViet.setVisible(false);

					tabbedPane_1.setEnabledAt(2, true);// enable tab faebook
					tabbedPane_1.setEnabledAt(3, true);// enable tab google
					tabbedPane_1.setEnabledAt(4, false);// disable tab register
														// likeviet
					labelLinkRefer.setText(Setting.lv.getRefferalLink().trim());
					setAccountInfoReload();
				} else {

					// neu login khong thanh cong thi bao loi :chuaxong
				}
			}
		});
		btnLoginLikeViet.setIcon(null);

		JCheckBox chckbxRemeberUsernameAnd = new JCheckBox(
				bundleDefault.getString("o4f.rememberUsernameAndPassword"));
		chckbxRemeberUsernameAnd.setBounds(102, 86, 254, 23);
		panelLoginLikeViet.add(chckbxRemeberUsernameAnd);

		JButton btnForgotPasswordLikeViet = new JButton(
				bundleDefault.getString("o4f.forgotPassword"));

		// quen mat khau Likeviet :chuaxong
		btnForgotPasswordLikeViet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openBrowser(urlLikeVietForgotPassword);
			}
		});
		btnForgotPasswordLikeViet.setBounds(19, 109, 162, 29);
		panelLoginLikeViet.add(btnForgotPasswordLikeViet);

		JTextPane txtpnIntroToLikeviet = new JTextPane();
		txtpnIntroToLikeviet.setEditable(false);
		txtpnIntroToLikeviet.setContentType("text/html");
		txtpnIntroToLikeviet.setBackground(UIManager
				.getColor("Button.background"));
		txtpnIntroToLikeviet
				.setText("<html><b>Intro to LikeViet</b><br />\nLikeViet là một hệ thống sẽ giúp bạn phát triển website của bạn trên các mạng xã hội như Facebook, Google+, Twitter, Youtube, Linkedin...<br/>\nBạn chưa có tài khoản LikeViet?</html>");
		txtpnIntroToLikeviet.setBounds(360, 34, 312, 75);
		panelLoginLikeViet.add(txtpnIntroToLikeviet);

		JButton btnRegisterLikeviet = new JButton(
				bundleDefault.getString("o4f.registerLikeViet"));

		// dang ky likeviet :chuaxong
		btnRegisterLikeviet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane_1.setSelectedComponent(panelRegisterLikeviet);
			}
		});
		btnRegisterLikeviet.setBounds(481, 109, 191, 29);
		panelLoginLikeViet.add(btnRegisterLikeviet);

		JSeparator separator_4 = new JSeparator();
		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setBounds(345, 28, 11, 104);
		panelLoginLikeViet.add(separator_4);

		panelFacebook = new JPanel();
		tabbedPane_1.addTab(
				bundleDefault.getString("o4f.facebook"),
				new ImageIcon(MainView.class
						.getResource("/images/facebook.png")), panelFacebook,
				null);
		panelFacebook.setLayout(null);

		panelLoginFacebook = new JPanel();
		panelLoginFacebook.setBounds(6, 6, 678, 138);
		panelFacebook.add(panelLoginFacebook);
		panelLoginFacebook.setLayout(null);

		JLabel lblLoginToFacebook = new JLabel(
				bundleDefault.getString("o4f.loginToFacebook"));
		lblLoginToFacebook.setFont(new Font("Arial", Font.BOLD, 13));
		lblLoginToFacebook.setBounds(19, 6, 156, 16);
		panelLoginFacebook.add(lblLoginToFacebook);

		JLabel label_12 = new JLabel(bundleDefault.getString("o4f.username"));
		label_12.setHorizontalAlignment(SwingConstants.RIGHT);
		label_12.setFont(new Font("Arial", Font.PLAIN, 13));
		label_12.setBounds(6, 34, 100, 22);
		panelLoginFacebook.add(label_12);

		usernameFacebook = new JTextField();
		usernameFacebook.setColumns(10);
		usernameFacebook.setBounds(113, 30, 235, 28);
		panelLoginFacebook.add(usernameFacebook);

		JLabel label_16 = new JLabel(bundleDefault.getString("o4f.password"));
		label_16.setHorizontalAlignment(SwingConstants.RIGHT);
		label_16.setFont(new Font("Arial", Font.PLAIN, 13));
		label_16.setBounds(6, 65, 100, 22);
		panelLoginFacebook.add(label_16);

		passwordFacebook = new JPasswordField();
		passwordFacebook.setBounds(113, 61, 235, 28);
		panelLoginFacebook.add(passwordFacebook);

		JButton btnLoginFacebook = new JButton(
				bundleDefault.getString("o4f.login"));
		// dang nhap vao facebook :chuaxong
		btnLoginFacebook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (loginToFacebook(usernameFacebook.getText(),
						passwordFacebook.getText(), true)) {
					panelLoginFacebook.setVisible(false);
					panelFacebook.add(panelAfterLoginFacebook);
					panelAfterLoginFacebook.setBounds(6, 6, 678, 138);
					panelAfterLoginFacebook.setVisible(true);
				}
			}
		});
		btnLoginFacebook.setBounds(192, 109, 156, 29);
		panelLoginFacebook.add(btnLoginFacebook);

		JCheckBox checkBox_1 = new JCheckBox(
				bundleDefault.getString("o4f.rememberUsernameAndPassword"));
		checkBox_1.setBounds(102, 86, 254, 23);
		panelLoginFacebook.add(checkBox_1);

		JButton btnForgotPasswordFacebook = new JButton(
				bundleDefault.getString("o4f.forgotPassword"));

		// quen mat khau facebook :chuaxong
		btnForgotPasswordFacebook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openBrowser(urlGoogleForgotPassword);
			}
		});
		btnForgotPasswordFacebook.setBounds(19, 109, 162, 29);
		panelLoginFacebook.add(btnForgotPasswordFacebook);

		JButton btnRegisterFacebook = new JButton(
				bundleDefault.getString("o4f.registerFacebook"));

		// Dang ky tai khoan facebook :chuaxong
		btnRegisterFacebook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openBrowser(urlFacebookRegister);
			}
		});
		btnRegisterFacebook.setBounds(481, 109, 191, 29);
		panelLoginFacebook.add(btnRegisterFacebook);

		JSeparator separator_6 = new JSeparator();
		separator_6.setOrientation(SwingConstants.VERTICAL);
		separator_6.setBounds(345, 28, 11, 104);
		panelLoginFacebook.add(separator_6);

		JEditorPane dtrpnFacebookLWebsite = new JEditorPane();
		dtrpnFacebookLWebsite.setBackground(SystemColor.window);
		dtrpnFacebookLWebsite.setEditable(false);
		dtrpnFacebookLWebsite.setContentType("text/html");
		dtrpnFacebookLWebsite
				.setText("<html>\n<b>Introduction Facebook</b><br />\nFacebook là website mà khi bạn đăng ký với họ xong, bạn có thể giao lưu cũng như chia sẻ những hình ảnh, tin tức, những sở thích hoặc bất cứ những gì bạn muốn cho bạn bè biết về bạn... <br />\nBạn chưa có tài khoản Facebook?\n</html> ");
		dtrpnFacebookLWebsite.setBounds(360, 19, 312, 90);
		panelLoginFacebook.add(dtrpnFacebookLWebsite);

		panelGoogle = new JPanel();
		tabbedPane_1.addTab(
				"Google",
				new ImageIcon(MainView.class
						.getResource("/images/googleicon.png")), panelGoogle,
				null);
		panelGoogle.setLayout(null);

		panelLoginGoogle = new JPanel();
		panelLoginGoogle.setLayout(null);
		panelLoginGoogle.setBounds(6, 6, 678, 138);
		panelGoogle.add(panelLoginGoogle);

		JLabel lblLoginToGoogle = new JLabel(
				bundleDefault.getString("o4f.loginToGoogle"));
		lblLoginToGoogle.setFont(new Font("Arial", Font.BOLD, 13));
		lblLoginToGoogle.setBounds(19, 6, 156, 16);
		panelLoginGoogle.add(lblLoginToGoogle);

		JLabel label_17 = new JLabel(bundleDefault.getString("o4f.username"));
		label_17.setHorizontalAlignment(SwingConstants.RIGHT);
		label_17.setFont(new Font("Arial", Font.PLAIN, 13));
		label_17.setBounds(6, 34, 100, 22);
		panelLoginGoogle.add(label_17);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(113, 30, 235, 28);
		panelLoginGoogle.add(textField_3);

		JLabel label_18 = new JLabel(bundleDefault.getString("o4f.password"));
		label_18.setHorizontalAlignment(SwingConstants.RIGHT);
		label_18.setFont(new Font("Arial", Font.PLAIN, 13));
		label_18.setBounds(6, 65, 100, 22);
		panelLoginGoogle.add(label_18);

		passwordField_3 = new JPasswordField();
		passwordField_3.setBounds(113, 61, 235, 28);
		panelLoginGoogle.add(passwordField_3);

		JButton btnLoginGoogle = new JButton(
				bundleDefault.getString("o4f.login"));
		// dang nhap vao google
		btnLoginGoogle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (loginGoogle("", "", true)) {
					panelGoogle.add(panelAfterLoginGoogle);
					panelAfterLoginGoogle.setBounds(6, 6, 678, 138);
					panelAfterLoginGoogle.setVisible(true);
					panelLoginGoogle.setVisible(false);
				} else {
					// thong bao loi dang nhap google :chuaxong
				}
			}
		});
		btnLoginGoogle.setBounds(192, 109, 156, 29);
		panelLoginGoogle.add(btnLoginGoogle);

		JCheckBox checkBox_2 = new JCheckBox(
				bundleDefault.getString("o4f.rememberUsernameAndPassword"));
		checkBox_2.setBounds(102, 86, 254, 23);
		panelLoginGoogle.add(checkBox_2);

		JButton btnForgotPasswordGoogle = new JButton(
				bundleDefault.getString("o4f.forgotPassword"));
		// quen mat khau google :chuaxong
		btnForgotPasswordGoogle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openBrowser(urlGoogleForgotPassword);
			}
		});
		btnForgotPasswordGoogle.setBounds(19, 109, 162, 29);
		panelLoginGoogle.add(btnForgotPasswordGoogle);

		JButton btnRegisterGoogle = new JButton(
				bundleDefault.getString("o4f.registerGoogleAccount"));
		// dang ky tai khoan google :chuaxong
		btnRegisterGoogle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openBrowser(urlGoogleRegister);
			}
		});
		btnRegisterGoogle.setBounds(467, 109, 205, 29);
		panelLoginGoogle.add(btnRegisterGoogle);

		JSeparator separator_7 = new JSeparator();
		separator_7.setOrientation(SwingConstants.VERTICAL);
		separator_7.setBounds(345, 28, 11, 104);
		panelLoginGoogle.add(separator_7);

		JEditorPane dtrpnintroductionGoogleServices = new JEditorPane();
		dtrpnintroductionGoogleServices.setBackground(SystemColor.window);
		dtrpnintroductionGoogleServices.setContentType("text/html");
		dtrpnintroductionGoogleServices
				.setText("<html>\n<b>Introduction Google Services</b><br />\nGoogle là một công ty Internet tầm cỡ thế giới có trụ sở tại Hoa Kỳ, được thành lập vào năm 1998. Sản phẩm chính của công ty này là công cụ tìm kiếm Google...\n<br />\nBạn chưa có tài khoản Google?\n</html>");
		dtrpnintroductionGoogleServices.setBounds(367, 20, 305, 89);
		panelLoginGoogle.add(dtrpnintroductionGoogleServices);
		tabbedPane_1.setBackgroundAt(3, Color.WHITE);
		tabbedPane_1.setForegroundAt(3, Color.BLACK);

		panelRegisterLikeviet = new JPanel();
		tabbedPane_1.addTab(
				"Register",
				new ImageIcon(MainView.class
						.getResource("/images/signup_icon.png")),
				panelRegisterLikeviet, null);
		panelRegisterLikeviet.setLayout(null);

		JPanel panelRegisterLikeVietForm = new JPanel();
		panelRegisterLikeVietForm.setLayout(null);
		panelRegisterLikeVietForm.setBounds(6, 6, 678, 138);
		panelRegisterLikeviet.add(panelRegisterLikeVietForm);

		JLabel lblRegisterLikeviet = new JLabel("o4f.registerLikeViet");
		lblRegisterLikeviet.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRegisterLikeviet.setBounds(6, 6, 116, 16);
		panelRegisterLikeVietForm.add(lblRegisterLikeviet);

		JLabel label_19 = new JLabel(bundleDefault.getString("o4f.username"));
		label_19.setHorizontalAlignment(SwingConstants.RIGHT);
		label_19.setBounds(6, 34, 116, 16);
		panelRegisterLikeVietForm.add(label_19);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(134, 28, 193, 28);
		panelRegisterLikeVietForm.add(textField_4);

		JLabel label_20 = new JLabel(bundleDefault.getString("o4f.password"));
		label_20.setHorizontalAlignment(SwingConstants.RIGHT);
		label_20.setBounds(6, 62, 116, 16);
		panelRegisterLikeVietForm.add(label_20);

		passwordField_4 = new JPasswordField();
		passwordField_4.setBounds(134, 56, 193, 28);
		panelRegisterLikeVietForm.add(passwordField_4);

		JButton btnRegisterLikeVietRefer = new JButton(
				bundleDefault.getString("o4f.register"));
		// dang ky tai khoan likeviet :chuaxong
		btnRegisterLikeVietRefer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerLikeViet("username", "password", "email", "captcha");
			}
		});
		btnRegisterLikeVietRefer.setBounds(160, 109, 167, 29);
		panelRegisterLikeVietForm.add(btnRegisterLikeVietRefer);

		JSeparator separator_8 = new JSeparator();
		separator_8.setOrientation(SwingConstants.VERTICAL);
		separator_8.setBounds(339, 7, 19, 115);
		panelRegisterLikeVietForm.add(separator_8);

		JLabel lblRetypePassword = new JLabel(
				bundleDefault.getString("o4f.email"));
		lblRetypePassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRetypePassword.setBounds(6, 90, 116, 16);
		panelRegisterLikeVietForm.add(lblRetypePassword);

		passwordField_5 = new JPasswordField();
		passwordField_5.setBounds(134, 84, 193, 28);
		panelRegisterLikeVietForm.add(passwordField_5);

		JLabel lblCaptcha = new JLabel(bundleDefault.getString("o4f.captcha"));
		lblCaptcha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCaptcha.setBounds(370, 34, 61, 16);
		panelRegisterLikeVietForm.add(lblCaptcha);

		textField_5 = new JTextField();
		textField_5.setBounds(443, 28, 231, 28);
		panelRegisterLikeVietForm.add(textField_5);
		textField_5.setColumns(10);

		final JLabel lblRefreshButton = new JLabel("");
		// reload lai captcha :chuaxong
		lblRefreshButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				reloadCaptchaRegisterLikeViet();
			}
		});
		lblRefreshButton.setIcon(new ImageIcon(MainView.class
				.getResource("/images/icon_refresh.gif")));
		lblRefreshButton.setBounds(496, 78, 32, 28);
		panelRegisterLikeVietForm.add(lblRefreshButton);
		lblRefreshButton.setVisible(false);
		JLabel labelCaptchaImage = new JLabel("");
		labelCaptchaImage.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				lblRefreshButton.setVisible(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblRefreshButton.setVisible(false);
			}
		});
		labelCaptchaImage
				.setIcon(new ImageIcon(
						MainView.class
								.getResource("/images/Screen Shot 2014-04-13 at 6.12.14 PM.png")));
		labelCaptchaImage.setBounds(370, 62, 302, 60);
		panelRegisterLikeVietForm.add(labelCaptchaImage);

		JLabel lblYourActivation = new JLabel(
				bundleDefault.getString("o4f.yourAction"));
		lblYourActivation.setFont(new Font("Arial", Font.BOLD, 13));
		lblYourActivation.setHorizontalAlignment(SwingConstants.RIGHT);
		lblYourActivation.setBounds(38, 254, 112, 16);
		mainPanel.add(lblYourActivation);

		panel_5 = new JScrollPane();
		 panel_5.setBackground(Color.ORANGE);
		 panel_5.setBounds(102, 10, 615, 85);
		 mainPanel.add(panel_5);

		 panel_6 = new JScrollPane();
		 panel_6.setBackground(Color.BLUE);
		 panel_6.setBounds(6, 465, 712, 100);
		 mainPanel.add(panel_6);

		final JComboBox comboBoxLanguage = new JComboBox();
		// thay doi ngon ngu
		comboBoxLanguage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxLanguage.getSelectedIndex() == 0) {
					changeLanguges(englistLocale);
				} else if (comboBoxLanguage.getSelectedIndex() == 1) {
					changeLanguges(vietnamLocale);
				}
			}
		});
		comboBoxLanguage.setModel(new DefaultComboBoxModel(new String[] {
				"English", "Tiếng Việt" }));
		comboBoxLanguage.setBounds(587, 97, 114, 36);
		mainPanel.add(comboBoxLanguage);

		panelAfterLoginO4F = new JPanel();
		panelAfterLoginO4F.setBounds(723, 12, 678, 138);
		mainPanel.add(panelAfterLoginO4F);
		panelAfterLoginO4F.setLayout(null);
		panelAfterLoginO4F.setVisible(false);
		JLabel lblWelcomeToOf = new JLabel(
				bundleDefault.getString("o4f.welcomeToO4f"));
		lblWelcomeToOf.setBounds(6, 6, 282, 16);
		panelAfterLoginO4F.add(lblWelcomeToOf);

		JLabel lblO4F = new JLabel("");
		// click vao vo trang chu o4f
		lblO4F.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lblO4F.setIcon(new ImageIcon(MainView.class
				.getResource("/images/onlyforfreelogo50x50.jpg")));
		lblO4F.setBounds(6, 32, 50, 51);
		panelAfterLoginO4F.add(lblO4F);

		JLabel lblHelloThanhnh = new JLabel("Hello, thanhnh271188");
		lblHelloThanhnh.setBounds(78, 24, 236, 26);
		panelAfterLoginO4F.add(lblHelloThanhnh);

		JButton btnChangePasswordO4F = new JButton(
				bundleDefault.getString("o4f.changePassword"));
		// Su kien thay doi mat khau O4F
		btnChangePasswordO4F.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openBrowser(urlO4fChangePassword);
			}
		});
		btnChangePasswordO4F.setBounds(170, 109, 160, 29);
		panelAfterLoginO4F.add(btnChangePasswordO4F);

		JButton btnLogoutO4F = new JButton(
				bundleDefault.getString("o4f.logout"));
		// su kien logout O4F
		btnLogoutO4F.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (logoutO4F()) {
					panelAfterLoginO4F.setVisible(false);
					panelLoginO4F.setVisible(true);
				} else {
					// thong bao logout that bai :chuaxong
				}
			}
		});
		btnLogoutO4F.setBounds(13, 109, 145, 29);
		panelAfterLoginO4F.add(btnLogoutO4F);

		JLabel lblLastLogin = new JLabel("Last login time: 12:37:54 25-12-2014");
		lblLastLogin.setBounds(78, 49, 264, 16);
		panelAfterLoginO4F.add(lblLastLogin);

		panelAfterLoginO4F.setVisible(false);

		JLabel lblNewLabel_5 = new JLabel("Last IP login: 192.168.102.201");
		lblNewLabel_5.setBounds(78, 67, 252, 16);
		panelAfterLoginO4F.add(lblNewLabel_5);

		JSeparator separator_5 = new JSeparator();
		separator_5.setOrientation(SwingConstants.VERTICAL);
		separator_5.setBounds(349, 24, 11, 114);
		panelAfterLoginO4F.add(separator_5);

		JLabel lblUserOnline = new JLabel(
				bundleDefault.getString("o4f.userOnline"));
		lblUserOnline.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUserOnline.setBounds(382, 67, 100, 16);
		panelAfterLoginO4F.add(lblUserOnline);

		JLabel label_7 = new JLabel("12,567,899");
		label_7.setHorizontalAlignment(SwingConstants.LEFT);
		label_7.setBounds(494, 67, 88, 16);
		panelAfterLoginO4F.add(label_7);

		JLabel lblHighstat = new JLabel(bundleDefault.getString("o4f.highstat"));
		lblHighstat.setFont(new Font("Arial", Font.BOLD, 13));
		lblHighstat.setBounds(387, 32, 160, 16);
		panelAfterLoginO4F.add(lblHighstat);

		JLabel lblTotalUser = new JLabel(
				bundleDefault.getString("o4f.totalUser"));
		lblTotalUser.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalUser.setBounds(382, 49, 100, 16);
		panelAfterLoginO4F.add(lblTotalUser);

		JLabel label_9 = new JLabel("12,567,899");
		label_9.setHorizontalAlignment(SwingConstants.LEFT);
		label_9.setBounds(494, 49, 88, 16);
		panelAfterLoginO4F.add(label_9);

		JLabel lblYourRefer = new JLabel(
				bundleDefault.getString("o4f.yourRefer"));
		lblYourRefer.setBounds(6, 87, 77, 16);
		panelAfterLoginO4F.add(lblYourRefer);

		lblReferOnly4Free = new JLabel("http://only4free.com/refferal/282");
		lblReferOnly4Free.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 2) {
					String myString = lblReferOnly4Free.getText().trim();
					StringSelection stringSelection = new StringSelection(
							myString);
					Clipboard clpbrd = Toolkit.getDefaultToolkit()
							.getSystemClipboard();
					clpbrd.setContents(stringSelection, null);
				}
			}
		});
		lblReferOnly4Free.setHorizontalAlignment(SwingConstants.LEFT);
		lblReferOnly4Free.setBounds(78, 87, 259, 16);
		panelAfterLoginO4F.add(lblReferOnly4Free);

		panelAfterLoginLikeViet = new JPanel();
		panelAfterLoginLikeViet.setBounds(723, 177, 678, 138);
		mainPanel.add(panelAfterLoginLikeViet);
		panelAfterLoginLikeViet.setLayout(null);

		JLabel lblNewLabel_6 = new JLabel(
				bundleDefault.getString("o4f.welcomeToLikeViet"));
		lblNewLabel_6.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel_6.setBounds(6, 6, 191, 16);
		panelAfterLoginLikeViet.add(lblNewLabel_6);

		JLabel lblHelloThanhnhWelcome = new JLabel(
				"Hello, thanhnh271188. Welcome back LikeViet by O4F!");
		lblHelloThanhnhWelcome.setBounds(68, 34, 410, 26);
		panelAfterLoginLikeViet.add(lblHelloThanhnhWelcome);

		JLabel lblLikeViet = new JLabel("");
		lblLikeViet.setBackground(UIManager.getColor("Button.select"));
		lblLikeViet.setIcon(new ImageIcon(MainView.class
				.getResource("/images/likeviet50x50.gif")));
		lblLikeViet.setBounds(6, 34, 50, 51);
		panelAfterLoginLikeViet.add(lblLikeViet);

		JButton buttonLogoutLikeViet = new JButton(
				bundleDefault.getString("o4f.logout"));

		// logout likeviet
		buttonLogoutLikeViet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (logoutLikeViet()) {
					panelAfterLoginLikeViet.setVisible(false);
					panelLoginLikeViet.setVisible(true);
				} else {
					// thong bao loi
				}
			}
		});
		buttonLogoutLikeViet.setBounds(68, 61, 145, 29);
		panelAfterLoginLikeViet.add(buttonLogoutLikeViet);
		// change password likeviet
		JButton btnChangePasswordLikeViet = new JButton(
				bundleDefault.getString("o4f.changePassword"));
		btnChangePasswordLikeViet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openBrowser(urlLikeVietChangePassword);
			}
		});
		btnChangePasswordLikeViet.setBounds(222, 61, 160, 29);
		panelAfterLoginLikeViet.add(btnChangePasswordLikeViet);

		JLabel lblYourFunction = new JLabel(
				bundleDefault.getString("o4f.yourFunction"));
		lblYourFunction.setBounds(6, 104, 103, 16);
		panelAfterLoginLikeViet.add(lblYourFunction);

		JLabel lblSurf = new JLabel("Surf");
		// surf :chuaxong
		lblSurf.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Vao day roi nha");
				likeVietSurf();
			}
		});
		lblSurf.setFont(new Font("Arial", Font.BOLD, 14));
		lblSurf.setForeground(UIManager
				.getColor("InternalFrame.borderHighlight"));
		lblSurf.setBounds(103, 98, 50, 28);
		panelAfterLoginLikeViet.add(lblSurf);

		panelAfterLoginFacebook = new JPanel();
		panelAfterLoginFacebook.setLayout(null);
		panelAfterLoginFacebook.setBounds(723, 330, 678, 138);
		mainPanel.add(panelAfterLoginFacebook);

		JLabel lblWelcomeToFacebook = new JLabel(
				bundleDefault.getString("o4f.welcomeToFacebook"));
		lblWelcomeToFacebook.setFont(new Font("Arial", Font.BOLD, 13));
		lblWelcomeToFacebook.setBounds(6, 6, 191, 16);
		panelAfterLoginFacebook.add(lblWelcomeToFacebook);

		JLabel lblHelloThanhnhWelcome_1 = new JLabel(
				"Hello, thanhnh271188. Welcome back Facebook by O4F!");
		lblHelloThanhnhWelcome_1.setBounds(68, 34, 410, 26);
		panelAfterLoginFacebook.add(lblHelloThanhnhWelcome_1);

		JLabel lblFacebook = new JLabel("");
		lblFacebook.setIcon(new ImageIcon(MainView.class
				.getResource("/images/imagefacebook50x50.gif")));
		lblFacebook.setBackground(UIManager.getColor("Button.select"));
		lblFacebook.setBounds(6, 34, 50, 51);
		panelAfterLoginFacebook.add(lblFacebook);

		JButton btnLogoutFacebook = new JButton(
				bundleDefault.getString("o4f.logout"));

		// logout facebook
		btnLogoutFacebook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (logoutFacebook()) {
					panelAfterLoginFacebook.setVisible(false);
					panelLoginFacebook.setVisible(true);
				} else {
					// thong bao loi khong logout duoc :chuaxong
				}
			}
		});
		btnLogoutFacebook.setBounds(68, 61, 145, 29);
		panelAfterLoginFacebook.add(btnLogoutFacebook);

		JButton btnChangePasswordFacebook = new JButton(
				bundleDefault.getString("o4f.changePassword"));

		// change password facebook
		btnChangePasswordFacebook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openBrowser(urlFacebookForgotPassword);
			}
		});
		btnChangePasswordFacebook.setBounds(222, 61, 160, 29);
		panelAfterLoginFacebook.add(btnChangePasswordFacebook);

		JLabel label_14 = new JLabel(
				bundleDefault.getString("o4f.yourFunction"));
		label_14.setBounds(6, 104, 103, 16);
		panelAfterLoginFacebook.add(label_14);

		labelFanpage = new JLabel("Fanpage");
		// fanpage :chuaxong
		labelFanpage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				facebookFanpage();
			}
		});
		labelFanpage.setForeground(Color.BLUE);
		labelFanpage.setFont(new Font("Arial", Font.BOLD, 14));
		labelFanpage.setBounds(103, 98, 70, 28);
		panelAfterLoginFacebook.add(labelFanpage);

		// Website :chuaxong
		labelWebsite = new JLabel("Website");
		labelWebsite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				facebookWebsite();
			}
		});
		labelWebsite.setForeground(Color.BLUE);
		labelWebsite.setFont(new Font("Arial", Font.BOLD, 14));
		labelWebsite.setBounds(173, 98, 57, 28);
		panelAfterLoginFacebook.add(labelWebsite);

		labelPhoto = new JLabel("Photo");
		// photo :chuaxong
		labelPhoto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				facebookPhoto();
			}
		});
		labelPhoto.setForeground(Color.BLUE);
		labelPhoto.setFont(new Font("Arial", Font.BOLD, 14));
		labelPhoto.setBounds(239, 98, 50, 28);
		panelAfterLoginFacebook.add(labelPhoto);

		labelFollow = new JLabel("Follow");
		// follow :chuaxong
		labelFollow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				facebookFollow();
			}
		});
		labelFollow.setForeground(Color.BLUE);
		labelFollow.setFont(new Font("Arial", Font.BOLD, 14));
		labelFollow.setBounds(301, 98, 57, 28);
		panelAfterLoginFacebook.add(labelFollow);

		labelShareOnFacebook = new JLabel("Share");
		// Share :chuaxong
		labelShareOnFacebook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				facebookShare();
			}
		});
		labelShareOnFacebook.setForeground(Color.BLUE);
		labelShareOnFacebook.setFont(new Font("Arial", Font.BOLD, 14));
		labelShareOnFacebook.setBounds(360, 98, 50, 28);
		panelAfterLoginFacebook.add(labelShareOnFacebook);

		panelAfterLoginGoogle = new JPanel();
		panelAfterLoginGoogle.setLayout(null);
		panelAfterLoginGoogle.setBounds(723, 482, 678, 138);
		mainPanel.add(panelAfterLoginGoogle);

		JLabel lblWelcomeToTwitter = new JLabel(
				bundleDefault.getString("o4f.welcomeToGoogle"));
		lblWelcomeToTwitter.setFont(new Font("Arial", Font.BOLD, 13));
		lblWelcomeToTwitter.setBounds(6, 6, 191, 16);
		panelAfterLoginGoogle.add(lblWelcomeToTwitter);

		JLabel lblHelloThanhnhWelcome_2 = new JLabel(
				"Hello, thanhnh271188. Welcome back Google by O4F!");
		lblHelloThanhnhWelcome_2.setBounds(68, 34, 410, 26);
		panelAfterLoginGoogle.add(lblHelloThanhnhWelcome_2);

		JLabel lblGoogleAccount = new JLabel("");
		lblGoogleAccount.setIcon(new ImageIcon(MainView.class
				.getResource("/images/google-icon50x50.gif")));
		lblGoogleAccount.setBackground(UIManager.getColor("Button.select"));
		lblGoogleAccount.setBounds(6, 34, 50, 51);
		panelAfterLoginGoogle.add(lblGoogleAccount);

		JButton btnLogoutGoogle = new JButton("Logout");
		// logout google
		btnLogoutGoogle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (logoutGoogle()) {
					panelAfterLoginGoogle.setVisible(false);
					panelLoginGoogle.setVisible(true);
				}
			}
		});
		btnLogoutGoogle.setBounds(68, 61, 145, 29);
		panelAfterLoginGoogle.add(btnLogoutGoogle);

		JButton btnChangePasswordGoogle = new JButton(
				bundleDefault.getString("o4f.changePassword"));
		// thay doi mat khau google
		btnChangePasswordGoogle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openBrowser(urlGoogleChangePassword);
			}
		});
		btnChangePasswordGoogle.setBounds(222, 61, 160, 29);
		panelAfterLoginGoogle.add(btnChangePasswordGoogle);

		JLabel label_25 = new JLabel(
				bundleDefault.getString("o4f.yourFunction"));
		label_25.setBounds(6, 104, 90, 16);
		panelAfterLoginGoogle.add(label_25);

		JLabel lblLike = new JLabel("Like");
		// likegoogle :chuaxong
		lblLike.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lblLike.setForeground(Color.BLUE);
		lblLike.setFont(new Font("Arial", Font.BOLD, 14));
		lblLike.setBounds(103, 98, 39, 28);
		panelAfterLoginGoogle.add(lblLike);

		JLabel lblSubribe = new JLabel("Subsribe");
		// subscribegoogle :chuaxong
		lblSubribe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lblSubribe.setForeground(Color.BLUE);
		lblSubribe.setFont(new Font("Arial", Font.BOLD, 14));
		lblSubribe.setBounds(147, 98, 66, 28);
		panelAfterLoginGoogle.add(lblSubribe);

		JLabel lblGoogle = new JLabel("Google +1");

		// google+1 :chuaxong
		lblGoogle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lblGoogle.setForeground(Color.BLUE);
		lblGoogle.setFont(new Font("Arial", Font.BOLD, 14));
		lblGoogle.setBounds(216, 98, 77, 28);
		panelAfterLoginGoogle.add(lblGoogle);

		JLabel lblViewYoutube = new JLabel("View Youtube");

		// viewyoutube :chuaxong
		lblViewYoutube.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lblViewYoutube.setForeground(Color.BLUE);
		lblViewYoutube.setFont(new Font("Arial", Font.BOLD, 14));
		lblViewYoutube.setBounds(305, 98, 103, 28);
		panelAfterLoginGoogle.add(lblViewYoutube);

		JPanel panelMessage = new JPanel();
		panelMessage.setBounds(6, 571, 707, 22);
		mainPanel.add(panelMessage);
		panelMessage.setLayout(null);

		JLabel lblActionMessage = new JLabel("Your action: xxx");
		lblActionMessage.setForeground(UIManager
				.getColor("InternalFrame.borderHighlight"));
		lblActionMessage.setFont(new Font("Arial", Font.PLAIN, 13));
		lblActionMessage.setBounds(6, 2, 329, 16);
		panelMessage.add(lblActionMessage);

		JLabel lblMessageXxx = new JLabel("Message: xxx");
		lblMessageXxx.setForeground(UIManager
				.getColor("InternalFrame.borderShadow"));
		lblMessageXxx.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMessageXxx.setFont(new Font("Arial", Font.PLAIN, 13));
		lblMessageXxx.setBounds(372, 2, 329, 16);
		panelMessage.add(lblMessageXxx);

		JSeparator separator_9 = new JSeparator();
		separator_9.setBounds(6, 555, 707, 12);
		mainPanel.add(separator_9);

		JLabel lblLogoO4F = new JLabel("");
		// su kien khi click chuot vao logo o4f o mainframe
		lblLogoO4F.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lblLogoO4F.setIcon(new ImageIcon(MainView.class
				.getResource("/images/logo-04f.jpg")));
		lblLogoO4F.setBounds(6, 10, 90, 90);
		mainPanel.add(lblLogoO4F);

		JPanel panel_8 = new JPanel();
		tabbedPane.addTab("Configuration", null, panel_8, null);
		panel_8.setLayout(null);

		JLabel lblNumberOfThread = new JLabel(
				bundleDefault.getString("o4f.numberOfThreads"));
		lblNumberOfThread.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumberOfThread.setBounds(36, 21, 164, 16);
		panel_8.add(lblNumberOfThread);

		JSpinner spinner = new JSpinner();
		spinner.setBounds(212, 15, 48, 28);
		panel_8.add(spinner);

		JLabel lblSockIp = new JLabel("Sock IP:");
		lblSockIp.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSockIp.setBounds(33, 49, 167, 16);
		panel_8.add(lblSockIp);

		IPTextField ipA = new IPTextField(0, 255);
		IPTextField ipB = new IPTextField(0, 255);
		IPTextField ipC = new IPTextField(0, 255);
		IPTextField ipD = new IPTextField(0, 255);
		JTextField fTFClassA = new JTextField();
		fTFClassA.setDocument(ipA);
		fTFClassA.setHorizontalAlignment(SwingConstants.RIGHT);
		fTFClassA.setText("0");
		fTFClassA.setToolTipText("Class A");
		fTFClassA.setBounds(212, 43, 48, 28);
		panel_8.add(fTFClassA);

		JLabel label = new JLabel(".");
		label.setBounds(264, 55, 11, 16);
		panel_8.add(label);

		JTextField formattedTextField = new JTextField();
		formattedTextField.setDocument(ipB);
		formattedTextField.setText("0");
		formattedTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		formattedTextField.setToolTipText("Class B");
		formattedTextField.setBounds(272, 43, 48, 28);
		panel_8.add(formattedTextField);

		JTextField formattedTextField_1 = new JTextField();
		formattedTextField_1.setDocument(ipC);
		formattedTextField_1.setHorizontalAlignment(SwingConstants.RIGHT);
		formattedTextField_1.setText("0");
		formattedTextField_1.setToolTipText("Class C");
		formattedTextField_1.setBounds(332, 43, 48, 28);
		panel_8.add(formattedTextField_1);

		JTextField formattedTextField_2 = new JTextField();
		formattedTextField_2.setDocument(ipD);
		formattedTextField_2.setHorizontalAlignment(SwingConstants.RIGHT);
		formattedTextField_2.setText("0");
		formattedTextField_2.setToolTipText("Class D");
		formattedTextField_2.setBounds(392, 43, 48, 28);
		panel_8.add(formattedTextField_2);

		JLabel label_1 = new JLabel(".");
		label_1.setBounds(323, 55, 11, 16);
		panel_8.add(label_1);

		JLabel label_2 = new JLabel(".");
		label_2.setBounds(383, 55, 11, 16);
		panel_8.add(label_2);

		JLabel label_3 = new JLabel(":");
		label_3.setBounds(440, 49, 11, 16);
		panel_8.add(label_3);

		JFormattedTextField formattedTextField_3 = new JFormattedTextField();
		formattedTextField_3.setText("0");
		formattedTextField_3.setHorizontalAlignment(SwingConstants.RIGHT);
		formattedTextField_3.setToolTipText("Port");
		formattedTextField_3.setBounds(450, 43, 58, 28);
		panel_8.add(formattedTextField_3);

		JLabel lblSockType = new JLabel("Sock type:");
		lblSockType.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSockType.setBounds(122, 77, 78, 16);
		panel_8.add(lblSockType);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Sock 4",
				"Sock 5", "Proxy" }));
		comboBox.setBounds(212, 73, 108, 27);
		panel_8.add(comboBox);

		JLabel lblYourCurrentIp = new JLabel("Your current IP:");
		lblYourCurrentIp.setHorizontalAlignment(SwingConstants.RIGHT);
		lblYourCurrentIp.setBounds(54, 108, 146, 16);
		panel_8.add(lblYourCurrentIp);

		JLabel lblNewLabel_4 = new JLabel("192.168.192.192");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_4.setBounds(212, 108, 197, 16);
		panel_8.add(lblNewLabel_4);

		JLabel lblYourCountry = new JLabel("Your country:");
		lblYourCountry.setHorizontalAlignment(SwingConstants.RIGHT);
		lblYourCountry.setBounds(36, 147, 167, 16);
		panel_8.add(lblYourCountry);

		JLabel label_4 = new JLabel("");
		label_4.setToolTipText("Vietnam");
		label_4.setIcon(new ImageIcon(MainView.class
				.getResource("/images/flag_vi.gif")));
		label_4.setBounds(212, 135, 38, 28);
		panel_8.add(label_4);

		JLabel lblAutoskip = new JLabel("Auto skip:");
		lblAutoskip.setBackground(new Color(204, 255, 0));
		lblAutoskip.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAutoskip.setBounds(46, 175, 154, 16);
		panel_8.add(lblAutoskip);

		JCheckBox checkBox = new JCheckBox("");
		checkBox.setBounds(206, 175, 128, 16);
		panel_8.add(checkBox);

		JLabel lblDonate = new JLabel("New label");
		// Click vao mo qua trang donate :chuaxong
		lblDonate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				openBrowser(urlDonate);
			}
		});
		lblDonate.setIcon(new ImageIcon(MainView.class
				.getResource("/images/donate.gif")));
		lblDonate.setBounds(452, 77, 256, 118);
		panel_8.add(lblDonate);

		JButton btnCheckSock = new JButton("Check sock");
		// check sock :chuaxong
		btnCheckSock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnCheckSock.setBounds(212, 203, 117, 29);
		panel_8.add(btnCheckSock);

		JButton btnSave = new JButton("Save");
		// luu config :chuaxong
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSave.setBounds(332, 203, 117, 29);
		panel_8.add(btnSave);

		JButton btnClearCookies = new JButton("Clear cookies");
		// xoa cookies :chuaxong
		btnClearCookies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearCookies();
			}
		});
		btnClearCookies.setBounds(450, 203, 117, 29);
		panel_8.add(btnClearCookies);

		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(102, 255, 51));
		panel_9.setBounds(15, 288, 693, 296);
		panel_8.add(panel_9);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(15, 236, 693, 12);
		panel_8.add(separator_2);

		JLabel lblPleaseWatchThis = new JLabel(
				"Please watch this video below to support us:");
		lblPleaseWatchThis.setBounds(54, 256, 639, 16);
		panel_8.add(lblPleaseWatchThis);

		JLabel lblYoutubeChannel = new JLabel("");
		// vao kenh youtube cua minh :chuaxong
		lblYoutubeChannel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lblYoutubeChannel.setIcon(new ImageIcon(MainView.class
				.getResource("/images/youtube-icon.gif")));
		lblYoutubeChannel.setBounds(15, 248, 38, 24);
		panel_8.add(lblYoutubeChannel);
	}

	/**
	 * login to O4F
	 * 
	 * @param username
	 * @param password
	 * @param rememberMe
	 * @return
	 */
	public boolean loginToO4F(String username, String password,
			boolean rememberMe) {
		return true;
	}

	public boolean loginToFacebook(String username, String password,
			boolean rememberMe) {
		// set cung
		username = "nhacso002@gmail.com";
		password = "761311kh?@";

		Setting.fb = Facebook.getInstance();
		Setting.fb.setOnline(true);
		Setting.fb.init("");
		System.out.println(username + ":" + password);
		int rsLoginFb = Setting.fb.login(username, password);
		switch (rsLoginFb) {
		case 1:
			// showMessageError()
			break;
		case -1:
			showMessageError(bundleDefault.getString("o4f.notice"),
					bundleDefault.getString("o4f.noNetwork"));
			break;

		default:
			showMessageError(bundleDefault.getString("o4f.notice"),
					bundleDefault.getString("o4f.accountinvalid"));
			break;
		}
		return (rsLoginFb == 1) ? true : false;
	}

	public boolean loginToLikeViet(String username, String password,
			boolean rememberMe) {
		// set cung
		username = "TienDolar";
		password = "Kiemtien2010";
		try {
			System.out.println(new File(".").getCanonicalPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LoadChilkat.load();
		// login likeviet
		Setting.lv = (Likeviet) Likeviet.getInstance();// khoi tao
		Setting.lv.setOnline(true);// set online
		Setting.lv.init("");// khoi tao gia tri
		return Setting.lv.login(username, password);
	}

	public boolean loginGoogle(String username, String password,
			boolean rememberMe) {
		return true;
	}

	public boolean logoutO4F() {
		return true;
	}

	public boolean logoutFacebook() {
		return Setting.fb.logout();
	}

	public boolean logoutLikeViet() {
		try {
			Setting.lv.logout("");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public boolean logoutGoogle() {
		return true;
	}

	public void openBrowser(String url) {
		String os = System.getProperty("os.name").toLowerCase();
		Runtime rt = Runtime.getRuntime();

		try {

			if (os.indexOf("win") >= 0) {

				// this doesn't support showing urls in the form of
				// "page.html#nameLink"
				rt.exec("rundll32 url.dll,FileProtocolHandler " + url);

			} else if (os.indexOf("mac") >= 0) {

				rt.exec("open " + url);

			} else if (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0) {

				// Do a best guess on unix until we get a platform independent
				// way
				// Build a list of browsers to try, in this order.
				String[] browsers = { "epiphany", "firefox", "mozilla",
						"konqueror", "netscape", "opera", "links", "lynx" };

				// Build a command string which looks like
				// "browser1 "url" || browser2 "url" ||..."
				StringBuffer cmd = new StringBuffer();
				for (int i = 0; i < browsers.length; i++)
					cmd.append((i == 0 ? "" : " || ") + browsers[i] + " \""
							+ url + "\" ");

				rt.exec(new String[] { "sh", "-c", cmd.toString() });

			} else {
				return;
			}
		} catch (Exception e) {
			return;
		}
	}

	// phuong thuc checksock
	public boolean checkSock(String ipSock, String sockType) {
		return true;
	}

	// save lai config
	public boolean saveConfig() {
		return true;
	}

	// clear cookies ben configs
	public boolean clearCookies() {
		return true;
	}

	public void setUpAfterLoginO4f() {

	}

	// thay doi thong tin coint, current click, remain click, total refer
	public void setAccountInfos(int coint, int currentClick, int remainClick,
			int totalRefer) {
		labelCoin.setText(coint + "");
		labelCurrentClick.setText(currentClick + "");
		labelRemainClick.setText(remainClick + "");
		labelTotalRefer.setText(totalRefer + "");
	}

	public boolean registerLikeViet(String username, String password,
			String email, String captcha) {
		return rootPaneCheckingEnabled;
	}

	public boolean reloadCaptchaRegisterLikeViet() {
		return true;
	}

	public void showMessageError(String title, String message) {
		JOptionPane.showMessageDialog(this, message, title,
				JOptionPane.ERROR_MESSAGE);
	}

	public int getIndexOfTab(Component comp, JTabbedPane jtabPanel) {
		System.out.println("co tat ca: " + jtabPanel.getTabCount()
				+ " components.");
		for (int i = 0; i < jtabPanel.getTabCount(); i++) {
			Component com = jtabPanel.getComponent(i);

			// if component is available
			if (com != null) {
				System.out.println("Label: " + com.toString());
			} else {
				System.out.println("null at index: " + i);
			}
		}
		return -1;
	}

	public void setTabbedPanelEnable() {
		tabbedPane_1.setEnabledAt(1, false);// enable tab likeviet
		tabbedPane_1.setEnabledAt(2, false);// enable tab facebook
		tabbedPane_1.setEnabledAt(3, false);// enable tab google
		tabbedPane_1.setEnabledAt(4, false);// enable tab register;
	}

	public void changeLanguges(Locale localLanguages) {
		bundleDefault = ResourceBundle.getBundle("lang", localLanguages);
		mnSetting.setText(bundleDefault.getString("o4f.setting"));
	}

	public void getCurrentCoin() {
		Runnable t = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				labelCoin.setText(Setting.lv.getCurrentCoin());
			}
		};
		t.run();
	}

	public void setAccountInfoReload() {
		getCurrentCoin();
		Runnable t2 = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				labelTotalRefer.setText(Setting.lv.getTotalRefferer());
			}
		};
		t2.run();
		Runnable t3 = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				labelRemainClick.setText(Setting.lv.getRemainClick() + "");
			}
		};
		t3.run();
		Runnable t4 = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				labelCurrentClick.setText(Setting.lv.getCurrentClick());
			}
		};
		t4.run();
		// labelCoin.setText(Setting.lv.getCurrentCoin());
		// labelTotalRefer.setText(Setting.lv.getTotalRefferer());
		// labelRemainClick.setText(Setting.lv.getRemainClick() + "");
		// labelCurrentClick.setText(Setting.lv.getCurrentClick());
	}

	public void likeVietSurf() {
		String[] idSurf = Setting.lv.getIdSurf();
		if (idSurf.length > 0) {
			Setting.lv.surfNow(idSurf);
			getCurrentCoin();
		}
	}

	public void facebookFanpage() {
		boolean isEnable = labelFanpage.isEnabled();
		if (isEnable) {
			autofb auto = new autofb(this, "fanpage", 1);
			// auto.start();
			Setting.executor.execute(auto);
		}
	}

	public void facebookWebsite() {
		boolean isEnable = labelWebsite.isEnabled();
		if (isEnable) {
			autofb auto = new autofb(this, "likeWebsite", 1);
			// auto.start();
			Setting.executor.execute(auto);
		}
	}

	public void facebookPhoto() {
		boolean isEnable = labelPhoto.isEnabled();
		if (isEnable) {
			if (Setting.currentThreads == Setting.maxthread) {
				System.err.println("1918 mainview");
				this.showMessageError("Thread", Message.maxThread);
				return;
			}
			autofb auto = new autofb(this, "photo", 1);
			// auto.start();
			Setting.executor.execute(auto);
		}
	}

	public void facebookFollow() {
		boolean isEnable = labelFollow.isEnabled();
		if (isEnable) {
			autofb auto = new autofb(this, "follow", 1);
			// auto.start();
			Setting.executor.execute(auto);
		}
	}

	public void facebookShare() {
		boolean isEnable = labelShareOnFacebook.isEnabled();
		if (isEnable) {
			if (Setting.currentThreads == Setting.maxthread) {
				System.err.println("1936 mainview");
				this.showMessageError("Thread", Message.maxThread);
				return;
			}
			autofb auto = new autofb(this, "share", 1);
			// auto.start();
			Setting.executor.execute(auto);
		}
	}

	public JLabel getLabelFanpage() {
		// TODO Auto-generated method stub
		return this.labelFanpage;
	}

	public JLabel getLabelWebsite() {
		// TODO Auto-generated method stub
		return this.labelWebsite;
	}

	public JLabel getLabelPhoto() {
		return labelPhoto;
	}

	public void setLabelPhoto(JLabel labelPhoto) {
		this.labelPhoto = labelPhoto;
	}

	public JLabel getLabelFollow() {
		return labelFollow;
	}

	public void setLabelFollow(JLabel labelFollow) {
		this.labelFollow = labelFollow;
	}

	public JLabel getLabelShareOnFacebook() {
		return labelShareOnFacebook;
	}

	public void setLabelShareOnFacebook(JLabel labelShareOnFacebook) {
		this.labelShareOnFacebook = labelShareOnFacebook;
	}

	public void updateRecord(int row, int i, String strError) {
		// TODO Auto-generated method stub

	}

	public void loadAdsense(JScrollPane scrollpanel,
			final String url) {
		webAds = new JWebBrowser();
		webAds.setJavascriptEnabled(true);
		webAds.setMenuBarVisible(true);
		webAds.setBarsVisible(false);
		webAds.setFocusable(true);
		webAds.navigate(url);
		// webAds.setBounds(6, 465, 707, 100);
//		webAds.setBounds(x, y, width, height);
//		this.mainPanel.add(webAds);
		scrollpanel.setViewportView(webAds);
		// String html = Setting.htmlAdsense;
		// webAds.setHTMLContent(html);
		// if load position not in 100% then marker not added
		// cause load js after load page finished
		webAds.addWebBrowserListener(new WebBrowserListener() {

			@Override
			public void windowWillOpen(WebBrowserWindowWillOpenEvent arg0) {
				// TODO Auto-generated method stub
				Setting.flagAdsense++;
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
				// Setting.flagAdsense++;

			}

			@Override
			public void locationChangeCanceled(WebBrowserNavigationEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void loadingProgressChanged(WebBrowserEvent wbe) {
				// TODO Auto-generated method stub
//				JWebBrowser cw = wbe.getWebBrowser();
//				if (cw.getLoadingProgress() == 100) {
//					String newlocation = cw.getResourceLocation();
//					System.out.println(newlocation + " - " + url);
//					if (!newlocation.startsWith(url)) {
//						JWebBrowser w = new JWebBrowser();
//						JWebBrowserWindow webBrowserWindow = WebBrowserWindowFactory
//								.create(w);
//						w.navigate(newlocation);
//						webBrowserWindow.setVisible(true);
//					}
//				}
			}

			@Override
			public void commandReceived(WebBrowserCommandEvent arg0) {
				// TODO Auto-generated method stub
			}
		});
	}
	/*
	 * String link = "/p.php?p=facebook";
	 * 
	 * while(true){ List<HashMap<String, String>> hmFacebookFP = Setting.lv
	 * .getLinkFan_Photo(link); if(hmFacebookFP.size()==0){ break; } for
	 * (HashMap<String, String> hash : hmFacebookFP) { String site =
	 * hash.get("link"); if (Setting.fb.isLikeFanPage(site)) {
	 * Setting.fb.likeUnlikeFanPage(link, false); } // verify step 1
	 * Setting.lv.verifyFirst(FBsiteCheckFanpage, FBreferFanpage, site,
	 * hash.get("id"), true); // like Setting.fb.likeUnlikeFanPage(site, true);
	 * // verify step 2 Setting.lv.verifySecond(FBsiteCheckFanpage,
	 * FBreferFanpage, hash.get("id")); //set lai gia tri coin getCurrentCoin();
	 * } }
	 */
}
