/*
 * Created by JFormDesigner on Mon Mar 17 21:49:59 ICT 2014
 */

package GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import chrriis.dj.nativeswing.NSOption;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

import Configs.Setting;
import Libs.LoadChilkat;
import Thread.view;

/**
 * @author td4vn
 */
public class Login extends JFrame {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoadChilkat.load();
					new Login();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private JWebBrowser web;
	public Login() {
		initComponents();
		setSize(450, 120);
		setVisible(true);
	}

	private void btn_login_register(MouseEvent e) {
		// TODO add your code here
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
//				browser browser = new browser();
//				browser.setVisible(true);
//				browser.loadURL(Setting.urlRegister);
				browser.createContent("Register", Setting.urlRegister);
			}
		});
	}

	private void btn_login_forgot(MouseEvent e) {
		// TODO add your code here
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
//				browser browser = new browser();
//				browser.setVisible(true);
//				browser.loadURL(Setting.urlForgot);
				browser.createContent("Forgot Password", Setting.urlForgot);
			}
		});
	}

	private void btn_login_login(MouseEvent e) {
		// TODO add your code here
		String user = this.txt_login_username.getText().toString().trim();
		String pass = this.txt_login_password.getText().toString().trim();
		if (user.equals("admin") && pass.equals("admin")) {
			view start = new view();
			start.start();
			this.dispose();
		}else{
			JOptionPane.showMessageDialog(this, "Username or password is wrong");
		}

	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		txt_login_username = new JFormattedTextField();
		txt_login_password = new JPasswordField();
		btn_login_login = new JButton();
		panel1 = new JPanel();
		btn_login_register = new JButton();
		btn_login_forgot = new JButton();

		//======== this ========
		setTitle("Login");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {153, 0, 0};
		((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {38, 31, 0};
		((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {0.0, 1.0, 1.0E-4};
		((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

		//---- txt_login_username ----
		txt_login_username.setBorder(new TitledBorder("Username"));
		txt_login_username.setText("admin");
		contentPane.add(txt_login_username, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 5), 0, 0));

		//---- txt_login_password ----
		txt_login_password.setBorder(new TitledBorder("Password"));
		txt_login_password.setDragEnabled(true);
		txt_login_password.setText("admin");
		contentPane.add(txt_login_password, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 0), 0, 0));

		//---- btn_login_login ----
		btn_login_login.setText("Login");
		btn_login_login.setContentAreaFilled(false);
		btn_login_login.setBorder(null);
		btn_login_login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btn_login_login(e);
			}
		});
		contentPane.add(btn_login_login, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
			new Insets(0, 0, 0, 5), 0, 0));

		//======== panel1 ========
		{
			panel1.setLayout(new GridBagLayout());
			((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 0, 0};
			((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0};
			((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
			((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {1.0, 1.0E-4};

			//---- btn_login_register ----
			btn_login_register.setText("Register");
			btn_login_register.setContentAreaFilled(false);
			btn_login_register.setBorder(null);
			btn_login_register.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					btn_login_register(e);
				}
			});
			panel1.add(btn_login_register, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 5), 0, 0));

			//---- btn_login_forgot ----
			btn_login_forgot.setText("Forgot Password");
			btn_login_forgot.setContentAreaFilled(false);
			btn_login_forgot.setBorder(null);
			btn_login_forgot.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					btn_login_forgot(e);
				}
			});
			panel1.add(btn_login_forgot, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));
		}
		contentPane.add(panel1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 0, 0), 0, 0));
		pack();
		setLocationRelativeTo(getOwner());
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private JFormattedTextField txt_login_username;
	private JPasswordField txt_login_password;
	private JButton btn_login_login;
	private JPanel panel1;
	private JButton btn_login_register;
	private JButton btn_login_forgot;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
