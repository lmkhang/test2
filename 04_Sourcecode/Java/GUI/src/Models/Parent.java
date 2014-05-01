package Models;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import views.MainView;
import Configs.Setting;
import GUI.gui;
import Utils.Utils;

import com.chilkatsoft.CkCharset;
import com.chilkatsoft.CkHttp;
import com.chilkatsoft.CkHttpRequest;
import com.chilkatsoft.CkHttpResponse;

public abstract class Parent implements MainInterfaceModel, Cloneable {
	public CkHttp http = null;
	protected CkCharset charset = null;
	protected CkHttpRequest req = null;
	protected CkHttpResponse response = null;
	protected String cookie = "";
	protected String username = "";
	protected String password = "";
	protected String userAgent = Setting.listUserAgent().get(0);
	protected boolean isOnline = false;
	protected boolean isLogin = false;
	protected MainView main = null;
	protected int trying = 0;
	protected int row;
	protected static Parent par = null;
	public String message = "";

	public Parent() {
		this.http = new CkHttp();
		this.http.UnlockComponent(Setting.keyChilkat);
		this.charset = new CkCharset();
		this.charset.UnlockComponent(Setting.keyChilkat);
	}

	// cloning
	@Override
	public Parent clone() throws CloneNotSupportedException {

		return (Parent) super.clone();

	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	// set cookie while cloning
	public void setCookieDir(String dir, String folder) {
		String path = Setting.getPathApp() + "/" + Setting.cookieFolder + "/"
				+ folder + "/";
		try {
			Utils.copyDirectory(new File(path + "login"), new File(path + dir));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.http.put_CookieDir(path + dir);
	}

	public void setCookieDir(String domain) {
		CkHttp newHttp = new CkHttp();
		newHttp.UnlockComponent(Setting.keyChilkat);
		newHttp.put_CookieDir("memory");
		newHttp.put_SaveCookies(true);
		newHttp.put_SendCookies(true);
		newHttp.put_HeartbeatMs(200);
		newHttp.put_FollowRedirects(true);
		// set connect
		newHttp.put_MaxConnections(100);
		//
		newHttp.put_UserAgent(this.userAgent);
		newHttp.SetRequestHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		newHttp.SetRequestHeader("Accept-Language", "en-US,en;q=0.5");
		newHttp.SetRequestHeader("Connection", "keep-alive");
		newHttp.SetRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		newHttp.SetCookieXml(domain, this.http.getCookieXml(domain));
		this.http = newHttp;
	}

	// initial
	public void init(String folder) {
		System.out.println("Process: Initial");
		// Utils.createFolder(Setting.getPathApp() + "/" + Setting.cookieFolder
		// + "/" + folder + "/login");
		// this.http.put_CookieDir(Setting.getPathApp() + "/"
		// + Setting.cookieFolder + "/" + folder + "/login");
		this.http.put_CookieDir("memory");
		this.http.put_SaveCookies(true);
		this.http.put_SendCookies(true);
		this.http.put_HeartbeatMs(200);
		this.http.put_FollowRedirects(true);
		// set connect
		this.http.put_MaxConnections(20);
		//
		this.http.put_UserAgent(this.userAgent);
		this.http
				.SetRequestHeader("Accept",
						"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		this.http.SetRequestHeader("Accept-Language", "en-US,en;q=0.5");
		this.http.SetRequestHeader("Connection", "keep-alive");
		this.http.SetRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
	}

	// get
	protected String quickGetString(String url) {
		if (this.isOnline == false) {
			int count = 0;
			while (true) {
				if (count == 10) {
					JOptionPane
							.showMessageDialog(null,
									"Tạm biệt. rớt mạng lâu quá. Vui lòng chờ có mạng lại để sử dụng");
					System.exit(0);
				}
				count++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (this.isOnline == true) {
					break;
				}
			}
		}

		try {
			String html = this.http.quickGetStr(url);

			if (html == null) {
				return "";
			}
			return html;
		} catch (Exception e) {
			return "";
		}

	}

	// post
	protected boolean post(String domain, int port, boolean ssl) {
		if (this.isOnline == false) {
			int count = 0;
			while (true) {
				if (count == 10) {
					JOptionPane
							.showMessageDialog(null,
									"Tạm biệt. rớt mạng lâu quá. Vui lòng chờ có mạng lại để sử dụng");
					System.exit(0);
				}
				count++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (this.isOnline == true) {
					break;
				}
			}
		}

		try {
			this.response = this.http.SynchronousRequest(domain, port, ssl,
					this.req);
			// System.out.println(response);
			if (this.response == null) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// add header
	protected void addHeader(String referer) {
		this.req.AddHeader("User-Agent", this.userAgent);
		this.req.AddHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		this.req.AddHeader("Accept-Language", "en-US,en;q=0.5");
		this.req.AddHeader("Connection", "keep-alive");
		this.req.AddHeader("Referer", referer);
		this.req.AddHeader("Cookie", this.cookie);

	}

	// add header
	protected void addHeader(String referer, String cookie) {
		this.req.AddHeader("User-Agent", this.userAgent);
		this.req.AddHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		this.req.AddHeader("Accept-Language", "en-US,en;q=0.5");
		this.req.AddHeader("Connection", "keep-alive");
		this.req.AddHeader("Referer", referer);
		this.req.AddHeader("Cookie", cookie);

	}

	// basic
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	public int getTrying() {
		return trying;
	}

	public void setTrying(int trying) {
		this.trying = trying;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public boolean isOnline() {
		return isOnline;
	}

	@Override
	public void addViewFB(MainView main) {
		// TODO Auto-generated method stub
		this.main = main;
	}

	@Override
	public void updateNetwork(boolean isOnline) {
		// TODO Auto-generated method stub
		this.isOnline = isOnline;
	}
}
