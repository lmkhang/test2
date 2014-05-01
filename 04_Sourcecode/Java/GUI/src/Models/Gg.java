package Models;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.util.SystemOutLogger;

import Configs.Setting;
import Utils.Utils;

import com.chilkatsoft.CkHttp;
import com.chilkatsoft.CkHttpRequest;

public class Gg extends Parent {

	public static Gg Goo = null;
	private String cookieYoutubeSub = "";
	private String cookieYoutubeLike = "";
	private String resLogin;
	private String user = "";
	private int sleep = 6000;
	public String domain1 = "google.com";
	public String domain2 = "youtube.com";

	private Gg() {
		System.out.println("Process: Create Goo Object");
	}

	@Override
	public void setCookieDir(String domain) {
		// TODO Auto-generated method stub
		CkHttp newHttp = new CkHttp();
		newHttp.UnlockComponent(Setting.keyChilkat);
		newHttp.put_CookieDir("memory");
		newHttp.put_SaveCookies(true);
		newHttp.put_SendCookies(true);
		newHttp.put_HeartbeatMs(200);
		newHttp.put_FollowRedirects(true);
		// set connect
		newHttp.put_MaxConnections(20);
		//
		newHttp.put_UserAgent(this.userAgent);
		newHttp.SetRequestHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		newHttp.SetRequestHeader("Accept-Language", "en-US,en;q=0.5");
		newHttp.SetRequestHeader("Connection", "keep-alive");
		newHttp.SetRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		newHttp.SetCookieXml(this.domain1, this.http.getCookieXml(this.domain1));
		newHttp.SetCookieXml(this.domain2, this.http.getCookieXml(this.domain2));
		this.http = newHttp;
	}

	@Override
	public Gg clone() throws CloneNotSupportedException {

		return (Gg) super.clone();

	}

	// delete Gg
	public Gg setNull(String folder, String dir) {

		// System.out.println("Process: Delete Goo Object");
		if (folder == null) {
			if (dir == null) {
				dir = "";
			}
			String path = Setting.getPathApp() + "/" + Setting.cookieFolder
					+ "/" + folder + "/" + dir;
			if (dir.equals("")) {
				Utils.deleteSubDirectory(new File(path));
			} else {
				try {
					Utils.delete(new File(path));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		Goo = null;
		return Goo;
	}

	// setcookie
	public void setCookieDir(String folder, String dir) {
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

	// get instance
	public static Gg getInstance() {
		// System.out.println("Process: create new Goo");
		// if (fb == null) {
		Goo = new Gg();
		// }
		return Goo;
	}

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

	//Ham prepare Login
	//return 1 = thanh cong, 0 = that bai
	// return -1 = rot mang, 2 = chua tao channel
	//return 3 = High security
	public int prepareLogin() {
		// System.out.println("Process: Posting...");
		int result = 0;
		String GAPS = "1:9G6dve0ffL6kcfwIkMfln0iZ7CzGOQ:dm7KxvTF-0PLLFa5";
		this.http.AddQuickHeader("Cookie", GAPS = "1:9G6dve0ffL6kcfwIkMfln0iZ7CzGOQ:dm7KxvTF-0PLLFa5;");
		String responseText = this.quickGetString("Plus.google.com");

		String refferer = this.http.finalRedirectUrl();
		this.quickGetString(refferer);
		System.out.println("refferer " + refferer);

		String domain = this.http.getDomain(refferer);
		// System.out.println(this.http.getCookieXml("accounts.google.com"));
		this.req = new CkHttpRequest();
		// use method
		this.req.UsePost();
		// add param
		this.req.put_Path("/ServiceLoginAuth");
		HashMap<String, Integer> indexList = new HashMap<String, Integer>();
		indexList.put("key", 1);
		List<HashMap<String, String>> temp = Utils.filterList(
				this.http.getCookieXml("accounts.google.com"),
				"<GALX>(.*?)</GALX>", indexList);
		String GALX = temp.get(1).get("key");
		this.req.AddParam("GALX", GALX);

		this.req.AddParam("checkedDomains", "youtube");
		this.req.AddParam("checkConnection", "youtube");
		this.req.AddParam("pstMsg", "1");
		this.req.AddParam("Email", username);
		this.req.AddParam("Passwd", password);
		this.req.AddParam("signIn", "Sign In");
		this.req.AddParam("continue",
				"https://plus.google.com/?gpsrc=gplp0&partnerid=gplp0");
		this.req.AddParam("PersistentCookie", "yes");
		this.req.AddParam("rmShown", "1");
		this.req.AddParam("bgresponse", "!Aw8AAxEAAA");
		this.req.AddParam("pstMsg", "1");

		indexList = new HashMap<String, Integer>();
		indexList.put("key", 1);
		temp = Utils.filterList(this.http.getCookieXml("accounts.google.com"),
				"<GAPS>(.*?)</GAPS>", indexList);
		
		String googleCookie = this.http.getCookieXml("accounts.google.com");
		System.out.println(googleCookie);
		GAPS = "";
		if (googleCookie.contains("<Priority>High</Priority>")){
			result = 3;
			return result;
		}
		else{
		 GAPS = temp.get(1).get("key");
		 System.out.println("GAPS Orignial = " + GAPS);
		}
		
		
		String cookie = "GAPS=" + GAPS + "; GALX=" + GALX;
		// System.out.println(cookie);
		this.addHeader(refferer, cookie);
		// set response

		boolean response = this.post(domain, 443, true);
		
		
		// System.out.println(this.response.bodyStr());
		// System.out.println();
		if (response != false) {

			this.quickGetString("https://www.youtube.com/analytics?o=U");
			String finalRedirect = this.http.finalRedirectUrl();
			System.out.println(finalRedirect);
			//Truong hop user chua tao channel
			if (finalRedirect.contains("create_channel")){
				//Cho nay se return int
				result = 2;
				return result;
			}
			// Utils.writeFile(Setting.getPathApp() + "/debug.html", html);
			this.quickGetString("https://www.youtube.com");
			// System.out.println(this.http.getCookieXml("google.com"));
			if (this.checkStatusLogin() == 1) {
				result = 1;
			}
		}else{
			result = -1;
		}

		return result;
	}
	
	


	// 0: not login, 1: logged
	public int checkStatusLogin() {
		int rs = 0;
		String text = this.http.getCookieXml("accounts.google.com");
		try {
			if (text.contains("SAPISID")) {
				rs = 1;
			}
		} catch (Exception ex) {
		}
		return rs;
	}

	public void generate_Cookie() {
		this.cookie = "NID="
				+ Utils.filter(this.http.getCookieXml("plus.google.com"),
						"<NID>(.*?)</NID>", 1)
				+ "; HSID="
				+ Utils.filter(this.http.getCookieXml("plus.google.com"),
						"<HSID>(.*?)</HSID>", 1)
				+ "; SSID="
				+ Utils.filter(this.http.getCookieXml("plus.google.com"),
						"<SSID>(.*?)</SSID>", 1)
				+ "; APISID="
				+ Utils.filter(this.http.getCookieXml("plus.google.com"),
						"<APISID>(.*?)</APISID>", 1)
				+ "; SAPISID="
				+ Utils.filter(this.http.getCookieXml("plus.google.com"),
						"<SAPISID>(.*?)</SAPISID>", 1)
				+ "; SID="
				+ Utils.filter(this.http.getCookieXml("plus.google.com"),
						"<SID>(.*?)</SID>", 1);
	}

	// Subscribe channel youtube
	public int subscribeUnSubscribe(String youtubeChannel, boolean isSubscribe) {
		int rs = 1;
		String domain = "www.youtube.com";
		String youtubeChannel_temp = new String(youtubeChannel);
		String userChannel = youtubeChannel_temp.replace(
				"http://www.youtube.com/channel/", "");

		String responseText = this.quickGetString(youtubeChannel);
		// System.out.println("responseText: " + responseText);
		// Utils.writeFile(Setting.getPathApp() + "/dg.html", responseText);
		// System.exit(0);
		// Utils.writeFile(Setting.getPathApp() + "/debug.html", responseText);
		HashMap<String, Integer> indexList = new HashMap<String, Integer>();
		indexList.put("key", 2);
		List<HashMap<String, String>> temp = Utils.filterList(responseText,
				"subscription_ajax(.*?)\"(.*?)\"", indexList);
		String session_token = temp.get(0).get("key");
		try {

			System.out.println("session_token: " + session_token);

			String PAGE_CL = Utils.filter(responseText,
					"\\'PAGE_CL\\': (.*?),\\'PAGE_BUILD_TIMESTAMP\\'", 1);

			String PAGE_BUILD_TIMESTAMP = Utils.filter(responseText,
					"\\'PAGE_BUILD_TIMESTAMP\\': \"(.*?)\"", 1);

			this.req = new CkHttpRequest();
			// use method
			this.req.UsePost();
			this.generateCookieYoutube(responseText);
			// Neu isSubscribe = False = UnSubscribe
			if (isSubscribe == false) {
				indexList = new HashMap<String, Integer>();
				indexList.put("key", 1);
				temp = Utils.filterList(responseText,
						"data-subscription-id=\"(.*?)\"", indexList);

				String data_subscription_id = temp.get(0).get("key");
				// System.out.println("data_subscription_id = " +
				// data_subscription_id);
				// System.exit(0);

				this.req.put_Path("/subscription_ajax?action_remove_subscriptions=1");
				this.req.AddParam("session_token", session_token);
				this.req.AddParam("c", userChannel);
				this.req.AddParam("s", data_subscription_id);

			} else {
				this.req.put_Path("/subscription_ajax?action_create_subscription_to_channel=1&c="
						+ userChannel);
				this.req.AddParam("session_token", session_token);

			}

			// add param

			this.req.AddHeader("User-Agent", this.userAgent);
			this.req.AddHeader("Accept",
					"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			this.req.AddHeader("Accept-Language", "en-US,en;q=0.5");
			this.req.AddHeader("Connection", "keep-alive");
			this.req.AddHeader("DNT", "1");
			this.req.AddHeader("X-YouTube-Page-CL", PAGE_CL);
			this.req.AddHeader("X-YouTube-Page-Timestamp", PAGE_BUILD_TIMESTAMP);
			this.req.AddHeader("Host", domain);
			this.req.AddHeader("Referer", youtubeChannel);
			this.req.AddHeader("Cookie", this.cookieYoutubeSub);

			// set response

			boolean response = this.post(domain, 443, true);

			if (response == false) {
				return -1;
			}
			// try {
			// Thread.sleep(this.sleep);
			// } catch (InterruptedException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			boolean is = this.isSubcriseAlready(youtubeChannel);
			System.out.println("Kiem tra da subcribe chua: " + is);
			// String html = this.response.bodyStr();
			// Utils.writeFile(Setting.getPathApp() + "/dg.html", html);
			// System.out.println(isLikeAlready + " - " + isLike);
			if (is != isSubscribe) {
				this.setTrying(this.getTrying() + 1);
				this.main.updateRecord(this.row, 6,
						String.valueOf(this.getTrying()));
				if (this.trying > Setting.limit - 1) {
					return 0;
				}
				rs = this.subscribeUnSubscribe(youtubeChannel, isSubscribe);
			}
		} catch (Exception e) {
			System.out.println("bi loi rui: " + e);
			rs = 0;
		}

		return rs;
	}

	public void generateCookieYoutube(String html) {

		String eiTemp = "";
		String htmlTemp = new String(html);
		htmlTemp = htmlTemp.replace("&amp;", "&");
		// Utils.writeFile(Setting.getPathApp() + "/youtube.html", htmlTemp);

		String pattern = "data-sessionlink=\"(ei|ved|feature)\\=(.*?)\\&(ei|ved|feature)\\=(.*?)\\&(ei|ved|feature)=(.*?)\"(.*?)class=\"subscribe-label\"(.*?)</button>";

		eiTemp = "ei=" + Utils.specialFilter(htmlTemp, pattern, "ei") + "&ved="
				+ Utils.specialFilter(htmlTemp, pattern, "ved") + "&feature="
				+ Utils.specialFilter(htmlTemp, pattern, "feature");

		this.cookieYoutubeSub = "demographics="
				+ Utils.filter(this.http.getCookieXml("youtube.com"),
						"<demographics>(.*?)</demographics>", 1)
				+ "; __utma=27069237.1841356210.1382631367.1382631367.1382631367.1; __utmz=27069237.1382631367.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); VISITOR_INFO1_LIVE="
				+ Utils.filter(this.http.getCookieXml("youtube.com"),
						"<VISITOR_INFO1_LIVE>(.*?)</VISITOR_INFO1_LIVE>", 1)
				+ "; PREF=cvdm=grid&f1=50000000&fv=11.9.900&al=vi; SID="
				+ Utils.filter(this.http.getCookieXml("youtube.com"),
						"<SID>(.*?)</SID>", 1)
				+ "; HSID="
				+ "; PREF=cvdm=grid&f1=50000000&fv=11.9.900&al=vi; SID="
				+ Utils.filter(this.http.getCookieXml("youtube.com"),
						"<HSID>(.*?)</HSID>", 1)
				+ "; APISID="
				+ Utils.filter(this.http.getCookieXml("youtube.com"),
						"<APISID>(.*?)</APISID>", 1)
				+ "; LOGIN_INFO="
				+ Utils.filter(this.http.getCookieXml("youtube.com"),
						"<LOGIN_INFO>(.*?)</LOGIN_INFO>", 1)
				+ "; sQM6Q.resume=eoa2ktJhBSc:172; YSC="
				+ Utils.filter(this.http.getCookieXml("youtube.com"),
						"<YSC>(.*?)</YSC>", 1) + "; ACTIVITY="
				+ this.http.genTimeStamp() + "; s_tempdata-"
				+ this.http.genTimeStamp() + "=" + eiTemp;

	}

	public boolean isSubcriseAlready(String YoutubeChannel) {
		String responseText = this.quickGetString(YoutubeChannel);

		if (responseText.contains("data-is-subscribed=\"True\"")) {
			return true;

		}

		return false;
	}

	// Ham Like va Unlike Video Youtube
	// Return 1 = thanh cong; 0 = that bai
	// Return -1 = rot mang

	public int likeUnlike(String youtubeID, boolean isLike) {
		int rs = 1;
		String domain = "www.youtube.com";
		this.quickGetString("https://www.youtube.com/user/" + this.user);
		String responseText = this
				.quickGetString("https://www.youtube.com/watch?v=" + youtubeID);
		 Utils.writeFile(Setting.getPathApp()+"/db.html", responseText);
		// Get needed information to post
		try {
			String session_token = Utils.filter(responseText,
					"'watch_actions_ajax', \"(.*?)\"", 1);
			System.out.println("session_token = " + session_token);
			String PAGE_CL = Utils.filter(responseText,
					"\\'PAGE_CL\\': (.*?),\\'PAGE_BUILD_TIMESTAMP\\'", 1);
			System.out.println("PAGE_CL = " + PAGE_CL);
			String PAGE_BUILD_TIMESTAMP = Utils.filter(responseText,
					"\\'PAGE_BUILD_TIMESTAMP\\': \"(.*?)\"", 1);
			System.out.println("PAGE_BUILD_TIMESTAMP = " + PAGE_BUILD_TIMESTAMP);
			String plid = Utils.filter(responseText, "\"plid\": \"(.*?)\"", 1);
			System.out.println("plid = " + plid);
			// Generate Cookie Youtube to Like
			this.generate_Cookie_Like_youtube();

			// Starting generate chilkat request and Preparing packet to post
			this.req = new CkHttpRequest();
			// use method
			this.req.UsePost();
			this.req.RemoveAllParams();
			// If isLike = False = UnLike
			if (isLike == true) {

				this.req.put_Path("/watch_actions_ajax?action_like_video=1&video_id="
						+ youtubeID + "&plid=" + plid);

			} else {
				this.req.put_Path("/watch_actions_ajax?action_indifferent_video=1&video_id="
						+ youtubeID + "&plid=" + plid);

			}

			// add param
			this.req.AddParam("screen=h", "800");
			this.req.AddParam("w", "1280");
			this.req.AddParam("d", "24");
			this.req.AddParam("session_token", session_token);

			this.req.AddHeader("User-Agent", this.userAgent);
			this.req.AddHeader("Accept",
					"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			this.req.AddHeader("Accept-Language", "en-US,en;q=0.5");
			this.req.AddHeader("Connection", "keep-alive");
			this.req.AddHeader("X-YouTube-Page-CL", PAGE_CL);
			this.req.AddHeader("X-YouTube-Page-Timestamp", PAGE_BUILD_TIMESTAMP);
			this.req.AddHeader("Host", domain);
			this.req.AddHeader("Referer", "https://www.youtube.com/watch?v="
					+ youtubeID);
			this.req.AddHeader("Cookie", this.cookieYoutubeLike);
			// set response

			boolean response = this.post(domain, 443, true);

			if (response == false) {
				return -1;
			}
			try {
				
				System.out.println("Nghi ngoi " + this.sleep + " s nao");
				Thread.sleep(this.sleep);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("Loi cmn roi. Khong sleep duoc");
				e.printStackTrace();
			}
			boolean isLikeAlready = this.isLikeAlready(youtubeID);
			// System.out.println(isLikeAlready + " - " + isLike);
			if (isLikeAlready != isLike) {
				this.setTrying(this.getTrying() + 1);
				this.main.updateRecord(this.row, 6,
						String.valueOf(this.getTrying()));
				if (this.trying > Setting.limit - 1) {
					return 0;
				}
				rs = this.likeUnlike(youtubeID, isLike);
			}
		} catch (Exception e) {
			rs = 0;
		}

		// System.out.println(rs);
		return rs;
	}

	public boolean isLikeAlready(String youtubeID) {
		String responseText = this
				.quickGetString("https://www.youtube.com/watch?v=" + youtubeID);
		// System.out.println(responseText);
		// Utils.writeFile(Setting.getPathApp() + "/dg.html", responseText);
		if (responseText.contains("data-vote-state=\"0\"")) {

			return true;

		}

		return false;
	}

	public void generate_Cookie_Like_youtube() {
		this.cookieYoutubeLike = "demographics="
				+ Utils.filter(this.http.getCookieXml("youtube.com"),
						"<demographics>(.*?)</demographics>", 1)
				+ "; __utma=27069237.1841356210.1382631367.1382631367.1382631367.1; __utmz=27069237.1382631367.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); VISITOR_INFO1_LIVE="
				+ Utils.filter(this.http.getCookieXml("youtube.com"),
						"<VISITOR_INFO1_LIVE>(.*?)</VISITOR_INFO1_LIVE>", 1)
				+ "; PREF=f1=50000000&al=vi&fv=12.0.0; SID="
				+ Utils.filter(this.http.getCookieXml("youtube.com"),
						"<SID>(.*?)</SID>", 1)
				+ "; HSID="
				+ Utils.filter(this.http.getCookieXml("youtube.com"),
						"<HSID>(.*?)</HSID>", 1)
				+ "; APISID="
				+ Utils.filter(this.http.getCookieXml("youtube.com"),
						"<APISID>(.*?)</APISID>", 1)
				+ "; LOGIN_INFO="
				+ Utils.filter(this.http.getCookieXml("youtube.com"),
						"<LOGIN_INFO>(.*?)</LOGIN_INFO>", 1)
				+ "; sQM6Q.resume=eoa2ktJhBSc:172,6LwuA4HEKuw:1289,h0ZwOXZEwVI:179; YSC="
				+ Utils.filter(this.http.getCookieXml("youtube.com"),
						"<YSC>(.*?)</YSC>", 1)
				+ "; ACTIVITY="
				+ this.http.genTimeStamp()
				+ "; SSID="
				+ Utils.filter(this.http.getCookieXml("youtube.com"),
						"<SSID>(.*?)</SSID>", 1)
				+ "; SAPISID="
				+ Utils.filter(this.http.getCookieXml("youtube.com"),
						"<SAPISID>(.*?)</SAPISID>", 1);

	}

	public boolean logout() {

		return true;
	}

	public void setUser(String user) {
		this.user = user;
	}

	//Ham Log in google
	//return 1 = thanh cong, 0 = that bai
	// return -1 = rot mang, 2 = chua tao channel	
	public int login(String username, String password) {
		System.out.println("Process: Login");
		if (username != null && password != null) {
			this.username = username;
			this.password = password;
		}
		// System.out.println(this.username + "-" + this.password);
		return this.prepareLogin();
	}

	// Ham kiem tra da +1 hay chua

	public boolean isPlus(String websiteLink) {

		boolean rs = false;

		String html = this
				.quickGetString("https://plusone.google.com/u/0/_/+1/fastbutton?count=true&url="
						+ websiteLink);
		// Utils.writeFile(Setting.getPathApp() + "/dg1.html", html);
		if (html.contains("class=\"FP jd HP Gib Ina")) {
			rs = true;
		}

		return rs;
	}

	// Ham +1 GooglePlus
	// Return -1 = rot mang; 1 = thanh cong
	public int plusUnplus(String websiteLink, boolean isLike) {
		int rs = 1;
		String post = "";
		String domain = "";
		String localWebsite = this.http.urlEncode(websiteLink);
		this.generate_Cookie();
		String html = this
				.quickGetString("https://plusone.google.com/u/0/_/+1/fastbutton?count=true&url="
						+ localWebsite);

		String originToken = Utils.filter(html,
				"window.__PVT = \\'(.*?)\\';window.__GOOGLEAPIS =", 1);
		String currentPlus = Utils.filter(html,
				"<div id=\"aggregateCount\" class=\"Oy\">(.*?)</div>", 1);
		String abtk = Utils.filter(html,
				"a:\\'bubble\\',at:\\'(.*?)\\',ld:\\[", 1);
		abtk = abtk.replace("\\x3d", "=");
		abtk = abtk.replace("\\", "");

		if (isLike == true) {
			post = "[{\"method\":\"pos.plusones.insert\",\"id\":\"pos.plusones.insert\",\"params\":{\"cdx\":\"135b0b\",\"id\":\"";
			post = post + websiteLink
					+ "\",\"source\":\"widget\",\"track\":\"[[[null,[2,"
					+ currentPlus + ",[],1,106]]]]\",\"abtk\":\"" + abtk;
			post = post
					+ "\",\"userId\":\"@viewer\",\"groupId\":\"@self\"},\"jsonrpc\":\"2.0\",\"key\":\"pos.plusones.insert\",\"apiVersion\":\"v1\"}]";

		} else {

			post = "[{\"method\":\"pos.plusones.delete\",\"id\":\"pos.plusones.delete\",\"params\":{\"cdx\":\"16529\",\"id\":\"";
			post = post
					+ websiteLink
					+ "\",\"source\":\"widget\",\"track\":\"[[[null,[2,"
					+ currentPlus
					+ ",[],1,106]]]]\",\"userId\":\"@viewer\",\"groupId\":\"@self\"},\"jsonrpc\"";
			post = post
					+ ":\"2.0\",\"key\":\"pos.plusones.delete\",\"apiVersion\":\"v1\"}]";

		}

		domain = "clients6.google.com";

		this.http.SetRequestHeader("OriginToken", originToken);
		this.http
				.SetRequestHeader(
						"ClientDetails",
						"appVersion=5.0%20(Windows)&platform=Win32&userAgent=Mozilla%2F5.0%20(Windows%20NT%206.1%3B%20rv%3A24.0)%20Gecko%2F20100101%20Firefox%2F24.0");
		this.http.SetRequestHeader("X-JavaScript-User-Agent",
				"google-api-javascript-client/1.0.0-alpha");
		this.http.SetRequestHeader("X-Origin", "https://plusone.google.com");
		this.http.SetRequestHeader("X-Referer", "https://plusone.google.com");
		this.http.SetRequestHeader("X-Goog-Encode-Response-If-Executable",
				"base64");
		this.http.SetRequestHeader("Pragma", "no-cache");
		this.http.SetRequestHeader("Content-Type",
				"application/json; charset=UTF-8");
		this.http.SetRequestHeader("Cookie", this.cookie);

		this.response = this.http
				.PostJson(
						"https://clients6.google.com/rpc?key=AIzaSyCKSbrvQasunBoV16zDH9R33D88CeLr9gQ",
						post);

		if (this.response == null) {
			rs = -1;
			// System.out.println("Rot mang roi");
		} else {

			// html = this.response.bodyStr();
			// System.out.println(html);
			// remove nhung truong header dac biet chi dung trong Google Plus
			this.http.RemoveRequestHeader("OriginToken");
			this.http.RemoveRequestHeader("ClientDetails");
			this.http.RemoveRequestHeader("X-JavaScript-User-Agent");
			this.http.RemoveRequestHeader("X-Origin");
			this.http.RemoveRequestHeader("X-Referer");
			this.http
					.RemoveRequestHeader("X-Goog-Encode-Response-If-Executable");
			this.http.RemoveRequestHeader("Pragma");
			this.http.RemoveRequestHeader("Cookie");
			this.http
					.SetRequestHeader("Accept",
							"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			boolean isPlus = this.isPlus(websiteLink);
			// System.out.println(isLikeAlready + " - " + isLike);
			if (isPlus != isLike) {
				this.setTrying(this.getTrying() + 1);
				this.main.updateRecord(this.row, 6,
						String.valueOf(this.getTrying()));
				if (this.trying > Setting.limit - 1) {
					return 0;
				}
				rs = this.likeUnlike(websiteLink, isLike);
			}

		}

		return rs;
	}

}