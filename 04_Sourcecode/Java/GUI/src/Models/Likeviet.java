package Models;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.poi.hssf.util.HSSFColor.GOLD;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import Configs.Setting;
import GUI.gui;
import Libs.LoadChilkat;
import Utils.Utils;

import com.chilkatsoft.CkCharset;
import com.chilkatsoft.CkHttp;
import com.chilkatsoft.CkHttpRequest;
import com.chilkatsoft.CkHttpResponse;

public class Likeviet extends Parent {
	public static Likeviet lv = null;
	public String domain = "likeviet.com.vn";
	private String challenge = "6Lc8aO4SAAAAAHYSFVNDrwLyrx3UKuzmuNtvV9k-";
	private String recaptcha_challenge_field = "";
	private int loaiCaptchar = 0;
	public String extension = "";

	private Likeviet() {
		System.out.println("Process: Create Likeviet Object");
	}

	@Override
	public Likeviet clone() throws CloneNotSupportedException {

		return (Likeviet) super.clone();

	}

	public static Likeviet getInstance() {
		// System.out.println("Process: create new fb");
		// if (fb == null) {
		lv = new Likeviet();
		// }
		return lv;
	}

	// login
	public boolean login(String username, String password) {
		boolean result = false;
		this.quickGetString(this.domain);
		this.generate_Cookie();

		this.username = username;
		this.password = password;

		this.req = new CkHttpRequest();
		this.req.RemoveAllParams();
		this.req.UsePost();
		this.req.put_Path("/index.php");
		this.req.AddParam("login", this.username);
		this.req.AddParam("pass", this.password);
		// this.req.AddParam("remember", "on");
		this.req.AddParam("connect", "%C4%90%C4%83ng+nh%E1%BA%ADp");

		this.addHeader("http://" + this.domain + "/index.php");

		boolean response = this.post(this.domain, 80, false);

		if (response != false) {
			this.quickGetString(this.domain);
			// System.out.println(this.http.getCookieXml(this.domain));
			try {
				// Kiem tra log in thanh cong hay khong
				Utils.filter(this.http.getCookieXml(this.domain), "PHPSESSID",
						0);// PESAutoLogin
				String pt = "<div class=\"error\">(.*?)</div>";
				String rs = "";
				try {
					rs = Utils.filter(this.response.bodyStr(), pt, 1);
				} catch (Exception e) {
					rs = "";

				}
				if (rs.equals("")) {
					this.setLogin(true);
					result = true;
				}
			} catch (Exception e) {
				result = false;
			}

		}

		return result;
	}

	// Ham lay so coin hien tai cua nguoi choi

	public String getCurrentCoin() {
		String responseText = this.quickGetString("http://" + this.domain
				+ "/system/uCoins.php");
		return responseText;
	}

	// Skip

	public boolean skipLink(String siteCheck, String refer, String id) {
		boolean rs = false;
		this.quickGetString(this.domain);
		this.generate_Cookie();

		this.req.RemoveAllParams();
		this.req.UsePost();
		this.req.put_Path(siteCheck);
		this.req.AddParam("step", "skip");
		this.req.AddParam("sid", id);

		this.addHeader("http://" + this.domain + refer);
		boolean response = this.post(this.domain, 80, false);
		if (response == false) {
			return false;
		}
		if (response != false) {
			String html = this.response.bodyStr();
			if (html.contains("info")) {
				rs = true;
			} else {
				rs = false;
			}
		}
		return rs;
	}

	// Logout Kho Like
	public void logout(String folder) {
		Utils.deleteSubDirectory(new File(Setting.getPathApp() + "/"
				+ Setting.cookieFolder + "/" + folder + "/"));
		this.quickGetString("http://" + this.domain + "/logout.php");
		this.setLogin(false);
	}

	// verify 1
	public boolean verifyFirst(String siteCheck, String refer, String link,
			String id, boolean isFanpage) {
		boolean rs = false;
		this.quickGetString(this.domain);
		this.generate_Cookie();
		// System.out.println(link);

		int i = 1;
		this.req.RemoveAllParams();
		this.req.UsePost();
		this.req.put_Path(siteCheck);

		// Truong hop likewebsite
		if (isFanpage == false) {
//			System.out.println("Truong hop like website");
			i++;
		}

		this.req.AddParam("get", String.valueOf(i));
		this.req.AddParam("url", link);
		this.req.AddParam("pid", id);

		this.addHeader("http://" + this.domain + refer);

		boolean response = this.post(this.domain, 80, false);

		if (response != false) {
			String html = this.response.bodyStr();
			if (html.contains("1")) {
				rs = true;
			} else if (html.contains("0")) {
				rs = false;
			}
		}

//		System.out.println("ket qua - : " + rs);
		return rs;
	}

	// verify 2
	public boolean verifySecond(String siteCheck, String refer, String id) {
		boolean rs = false;
		int caseCheck = 0;
		// http://traodoi.likeviet.vn/system/modules/facebook/process.php?id=24077&type=1

		this.quickGetString(this.domain);
		this.generate_Cookie();

		retry: while (true) {
			System.out.println("retry lan thu:" + caseCheck);
			this.req.RemoveAllParams();
			this.req.UsePost();
			this.req.put_Path(siteCheck);

			if (caseCheck > 0) {
				this.req.AddParam("type", String.valueOf(caseCheck));
			} else if (caseCheck > 2) {

				break retry;

			}
			this.req.AddParam("id", id);

			this.addHeader("http://" + this.domain + refer);

			boolean response = this.post(this.domain, 80, false);

			if (response != false) {
				String html = this.response.bodyStr();
				// System.out.println("Verify 2nd step: " + html);

				if (html.contains("1")) {
					rs = true;
					break retry;

				} else {
					// System.out.println("CaseCheck = " + caseCheck);
					caseCheck++;
					rs = false;
				}
			}
			if (caseCheck > 2) {
				break retry;
			}
			continue retry;

		}
		// System.out.println(rs);

		return rs;
	}

	// Ham verifySecondGooglePlus
	// Truyen vao id Link tren likeviet
	public boolean verifySecondGooglePlus(String id) {
		boolean rs = false;
		this.quickGetString(this.domain);
		this.generate_Cookie();

		this.req.RemoveAllParams();
		this.req.UsePost();
		this.req.put_Path("/system/modules/google/process.php");

		this.req.AddParam("id", id);

		this.addHeader("http://" + this.domain + "/p.php?p=google");

		boolean response = this.post(this.domain, 80, false);

		if (response != false) {
			String html = this.response.bodyStr();
			if (html.contains("SUCCESS!")) {
				rs = true;
			}
		}
		
		System.out.println(rs);
		return rs;

	}

	// get fanpage list - "/p.php?p=facebook"
	public List<HashMap<String, String>> getLinkFan_Photo(String link) {
		HashMap<String, Integer> indexList = new HashMap<String, Integer>();
		indexList.put("id", 1);// id la tu dinh nghia theo y minh
		indexList.put("link", 2);// tuong tu
		indexList.put("coin", 6);// tuong tu
		String responseText = this.quickGetString("http://" + this.domain
				+ link);

		// Utils.writeFile(Setting.getPathApp()+"/db.html", responseText);
		// ModulePopup('25226','https://www.facebook.com/pages/Like-Viet/643043009093677?ref=hl','Facebook','900','500','7','1');
		String pattern = "ModulePopup\\(\\'(.*?)\\',\\'(.*?)\\',\\'(.*?)\\',\\'(.*?)\\',\\'(.*?)\\',\\'(.*?)\\',\\'(.*?)'\\)";

		List<HashMap<String, String>> list = Utils.filterList(responseText,
				pattern, indexList);
//		System.out.println("size: " + list.size());
		return list;
	}

	// Ham getPlusLink
	// Link get can truyen vao: /p.php?p=google
	public List<HashMap<String, String>> getPlusLink(String link) {
		HashMap<String, Integer> indexList = new HashMap<String, Integer>();
		indexList.put("id", 1);
		indexList.put("coin", 2);
		String responseText = this.quickGetString("http://" + this.domain
				+ link);
		// Utils.writeFile(Setting.getPathApp()+"/db.html", responseText);
		// ModulePopup('25226','https://www.facebook.com/pages/Like-Viet/643043009093677?ref=hl','Facebook','900','500','7','1');
		String pattern = "<span id=\"(.*?)coins\">(.*?)<\\/span>";

		List<HashMap<String, String>> list = Utils.filterList(responseText,
				pattern, indexList);
		return list;
	}

	// Ham khoi tao cookie cho LikeViet
	public void generate_Cookie() {

		String cookie = "PHPSESSID="
				+ Utils.filter(this.http.getCookieXml(this.domain),
						"<PHPSESSID>(.*?)</PHPSESSID>", 1)
				+ "; PESRefSource=http%3A%2F%2Flikeviet.com.vn%2F";
		this.cookie = cookie;
	}

	// delete object
	public Likeviet setNull(String folder, String dir) {

		// System.out.println("Process: Delete Likeviet Object");
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
		lv = null;
		return lv;
	}

	// Ham get loai captcha
	// Return 0 = Capchar Google ; 1 = Capchar LikeViet ; -1 = Rot mang
	private int getTypeCaptcha() {
		String html = this.quickGetString(this.domain + "/register.php");

		if (html == null) {
			return -1;
		}
		// Utils.writeFile(Setting.getPathApp()+"/db.html", html);
		if (html.contains("recaptcha/api/challenge?k=")) {
			return 0;
		}
		return 1;
	}

	// Ham Get Captcha Google
	// Return Truong recaptcha
	private String getCaptcharGoogle() {
		String html = this
				.quickGetString("http://www.google.com/recaptcha/api/challenge?k="
						+ this.challenge);
		return Utils.filter(html, "challenge : \\'(.*?)\\'", 1);
	}

	private String getCaptchaLikeViet() {
		String rs = "";
		String html = this.quickGetString(this.domain + "/register.php");
		try {
			rs = Utils.filter(html,
					"<img src=\"captcha.php?img=(.*?)\" alt=\"\"", 1);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return rs;
	}

	public String getCaptcha() {
		String rs = "";
		this.quickGetString(this.domain + "/?ref=" + Setting.refferId);
		this.loaiCaptchar = this.getTypeCaptcha();
		// this.loaiCaptchar = 1;
		if (this.loaiCaptchar == 0) {
			this.recaptcha_challenge_field = this.getCaptcharGoogle();
			rs = "http://www.google.com/recaptcha/api/image?c="
					+ this.recaptcha_challenge_field;
			this.extension = "jpg";
			// System.out.println(rs);

		} else {
			this.recaptcha_challenge_field = this.getCaptchaLikeViet();
			rs = "http://" + this.domain + "/captcha.php?img="
					+ this.recaptcha_challenge_field;
			this.extension = "png";
			// System.out.println(rs);
		}
		// down captcha

		boolean isDownload = this.http.Download(rs, Setting.getPathApp()
				+ "/cache/" + Setting.image_cache + "." + this.extension);

		if (isDownload == true) {
			return rs;
		} else {
			return "";
		}
	}

	// Ham dang ky voi captcha google
	// Ham tra ve -1 = rot mang; 1 = Loi ; 2 = thanh cong
	private int registerGoogleCaptchar(String username, String passwd,
			String email, String captchaText) {
		int result = -1;
		String error = "";
		this.quickGetString(this.domain + "/?ref=" + Setting.refferId);
		this.generate_Cookie();
		this.req = new CkHttpRequest();
		this.req.RemoveAllParams();
		this.req.UsePost();
		this.req.put_Path("/register.php");

		this.req.AddParam("user", username);
		this.req.AddParam("email", email);
		this.req.AddParam("email2", email);
		this.req.AddParam("password", passwd);
		this.req.AddParam("password2", passwd);
		this.req.AddParam("gender", "1");
		this.req.AddParam("recaptcha_challenge_field",
				this.recaptcha_challenge_field);
		this.req.AddParam("recaptcha_response_field", captchaText);
		this.req.AddParam("subscribe", "0");
		this.req.AddParam("register", "G%E1%BB%ADi");

		this.addHeader("http://" + this.domain + "/register.php");

		boolean response = this.post(this.domain, 80, false);

		if (response != false) {
			String html = this.response.bodyStr();
			// Utils.writeFile(Setting.getPathApp() + "/dg.html", html);

			// Kiem tra trang thai tra ve sau khi dang lo
			try {
				error = Utils.filter(html, "<div class=\"error\">(.*?)</div>",
						1);
				this.message = error;
				result = 1;
			} catch (Exception e) {
				this.message = "Đăng ký thành công rồi!";
				result = 2;
			}

		}
		this.message = Utils.removeTags(this.message);
		return result;

	}

	// Ham dang ky voi captcha LikeViet
	// Ham tra ve -1 = rot mang; 1 = Loi ; 2 = thanh cong
	private int registerLikeVietCaptcha(String username, String passwd,
			String email, String captchaText) {

		int result = -1;

		String error = "";
		this.quickGetString(this.domain + "/?ref=" + Setting.refferId);
		this.generate_Cookie();
		this.req = new CkHttpRequest();
		this.req.RemoveAllParams();
		this.req.UsePost();
		this.req.put_Path("/register.php");

		this.req.AddParam("user", username);
		this.req.AddParam("email", email);
		this.req.AddParam("email2", email);
		this.req.AddParam("password", passwd);
		this.req.AddParam("password2", passwd);
		this.req.AddParam("gender", "1");
		this.req.AddParam("captcha", captchaText);
		this.req.AddParam("subscribe", "0");
		this.req.AddParam("register", "G%E1%BB%ADi");

		this.addHeader("http://" + this.domain + "/register.php");

		boolean response = this.post(this.domain, 80, false);

		if (response != false) {
			String html = this.response.bodyStr();
			// Utils.writeFile(Setting.getPathApp() + "/dg.html", html);

			// Kiem tra trang thai tra ve sau khi dang lo
			try {
				error = Utils.filter(html, "<div class=\"error\">(.*?)</div>",
						1);
				this.message = error;
				result = 1;
			} catch (Exception e) {
				// System.out.println("Dang ky thanh cong roi");
				this.message = "Đăng ký thành công rồi!";
				result = 2;
			}

		}
		this.message = Utils.removeTags(this.message);
		return result;

	}

	public int register(String username, String password, String email,
			String captcha) {
		int rs = -1;
		if (this.loaiCaptchar == 0) {
			rs = this
					.registerGoogleCaptchar(username, password, email, captcha);
		} else {
			rs = this.registerLikeVietCaptcha(username, password, email,
					captcha);
		}

		return rs;
	}

	// Ham get tong so click hien tai cua tai khoan
	// Cai nay nen lam timmer sau 5s se tu dong update status
	// Lam 1 label hien thi thong so nay
	// Hien thi nhu sau: Tong so click hien tai: xxx clicks
	public String getCurrentClick() {
		String rs = "";

		String html = this.quickGetString(this.domain + "/index.php");

		try {
			rs = Utils.filter(html,
					"<b>Tổng số click:</b> <font(.*?)\">(.*?)</font>", 2);

		} catch (Exception e) {
			rs = "Không thể lấy được dữ liệu. Xin vui lòng chờ bản nâng cấp.";
		}

		return rs;
	}

	// Ham get so click can phai thuc hien de get bonus
	// Return 1 - 45 : So luong click can thiet
	// Return -1 : Rot mang
	// Return 0 : Da du so luong click
	public int getRemainClick() {
		int rs = -1;
		String tmp = "";

		String html = this.quickGetString(this.domain + "/bonus.php");

		try {
			tmp = Utils.filter(html,
					"Số click phải thực hiện còn: <b>(.*) lần</b>", 1);
			rs = Integer.valueOf(tmp);
		} catch (Exception e) {
			rs = 0;
		}

		// System.out.println(rs);
		return rs;
	}

	// Ham get thong bao thoi gian con lai de co the tiep tuc get bonus
	// Ham nay tra ve dang nhu sau
	// "You already got your bonus today! Come back in 23 hours" hoac co the la
	// tieng viet
	// Lam mot label hien thi ra ngoai form chinh
	public String timeBonusRemaining() {
		String rs = "";
		String html = this.quickGetString(this.domain + "/bonus.php");

		try {
			rs = Utils
					.filter(html,
							"<div class=\"msg\"><div class=\"error\">(.*?)</div></div>",
							1);
			rs = rs.replace("<b>", "");
			rs = rs.replace("</b>", "");
		} catch (Exception e) {
			rs = "Chua get bonus";
		}

		return rs;

	}

	// Ham get bonus
	// Return 1 = thanh cong; -1 = rot mang
	// Return 0 = Chua click du so luong click
	// Return 2 = Khong the getBonus vi da get roi
	public int getBonus() {
		int rs = -1;
		int clickRemain = this.getRemainClick();
		String tmp = "";

		if (clickRemain == 0) {
			tmp = this.timeBonusRemaining();
			if (tmp.equalsIgnoreCase("Chua get bonus")) {
				// Bat dau get Bonus
				this.generate_Cookie();

				this.req.RemoveAllParams();
				this.req.UsePost();
				this.req.put_Path("/system/bonus.php");

				this.req.AddParam("get", "1");

				this.addHeader("http://" + this.domain + "/bonus.php");

				boolean response = this.post(this.domain, 80, false);

				if (response != false) {
					rs = 1;
				}
			} else {
				rs = 2;
				// System.out.println(tmp);
			}
		} else if (clickRemain > 0) {
			// System.out.println("Chua click du so click bac oi");
			// System.out.println(clickRemain);
			rs = 0;
		}

		// System.out.println("Trang thai getBonus = " + rs);
		return rs;
	}

	// Ham get Link Reffer cua user
	public String getRefferalLink() {
		String rs = "";
		String html = this.quickGetString(this.domain + "/refer.php");
		// Utils.writeFile(Setting.getPathApp() + "/dg.html", html);
		try {
			rs = "http://likeviet.com.vn/?ref="
					+ Utils.filter(
							html,
							"http://likeviet.com.vn/\\?ref\\=(.*?)\" onclick=\"",
							1);
		} catch (Exception e) {
			rs = "Không thể lấy được dữ liệu. Xin vui lòng chờ bản nâng cấp.";
		}

		// System.out.println(rs);
		return rs;
	}

	// Ham get tong so luong reffer cua nguoi dung
	public String getTotalRefferer() {
		String rs = "";

		String html = this.quickGetString(this.domain + "/referrals.php");

		try {
			rs = Utils.filter(html, "Tổng cộng: <span(.*?)\">(.*?)</span>", 2);
		} catch (Exception e) {
			rs = "Không thể lấy được dữ liệu. Xin vui lòng chờ bản nâng cấp.";
		}

		// System.out.println("Hien tai bac dang co: " + rs + " refferer");
		return rs;

	}

	public Vector<Vector<String>> getListReferrals() throws XPatherException {
		Vector<Vector<String>> list = new Vector<Vector<String>>();
		Vector<String> column = new Vector<String>();
		column.add("Id");
		column.add("Tên đăng nhập");
		column.add("Ngày");
		column.add("Đã thanh toán hoa hồng");
		list.add(column);
		String html = this.quickGetString(this.domain + "/referrals.php");
		String pattern = "//table[@class=\"table\"]/tbody/tr";
		HtmlCleaner cleaner = new HtmlCleaner();
		CleanerProperties props = cleaner.getProperties();
		props.setAllowHtmlInsideAttributes(true);
		props.setAllowMultiWordAttributes(true);
		props.setRecognizeUnicodeChars(true);
		props.setOmitComments(true);
		TagNode node = cleaner.clean(html);
		Object[] info_nodes = node.evaluateXPath(pattern);
		if (info_nodes.length > 0) {
			for (Object object : info_nodes) {
				TagNode info_nodeTR = (TagNode) object;
				list.add(getInfoRecord(info_nodeTR));
			}
		}
		return list;
	}

	private static Vector<String> getInfoRecord(TagNode node) {
		Vector<String> list = new Vector<String>();
		for (Object object : node.getChildren()) {
			TagNode td = (TagNode) object;
			list.add(td.getText().toString().trim());
		}
		return list;
	}

	public String showReferrals(Vector<Vector<String>> list) {
		String rs = "";
		for (Vector<String> vector : list) {
			rs += vector.toString();
			rs += "\r\n";
		}
		return rs;
	}

	
	/**
	 * 
	 * @return
	 */
	public String[] getIdSurf() {
		String[] rs = new String[2];
		String id = "";
		String bonusCoin = "";

		this.generate_Cookie();

		String html = this.quickGetString(this.domain + "/surf.php");

		if (html == null) {
			id = "-1";
			bonusCoin = "";
		} else {
			try {
				Utils.filter(html, "system/modules/surf/nocoins", 0);
				id = "0";
				bonusCoin = "";
			} catch (Exception e) {
				id = Utils.filter(html,
						"var buster_red = \\'\\?skip=(.*?)\\&bd\\'", 1);
				bonusCoin = Utils.filter(html, "Bạn sẽ nhận được(.*?)Xu", 1);
			}
			// Utils.writeFile(Setting.getPathApp() + "/dg.html", html);
		}

		rs[0] = id;
		rs[1] = bonusCoin;

		return rs;

	}

	// Ham surfLink tren LikeViet
	// Return -1 = rot mang; 1 = thanh cong
	public int surfNow(String[] idSurf) {
		// String [] idSurf = this.getIdSurf();
		int rs = -1;
		try {
			Thread.sleep(10500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.generate_Cookie();

		this.req.RemoveAllParams();
		this.req.UsePost();
		this.req.put_Path("/system/modules/surf/process.php");

		this.req.AddParam("data", idSurf[0]);
		this.addHeader("http://" + this.domain + "/surf.php");

		boolean response = this.post(this.domain, 80, false);

		// neu response = false => Rot mang
		if (response != false) {
			rs = 1;
			// coin = this.getCurrentCoin();
		}

		return rs;
	}
	
	
	// Ham get likeVietVideoID
	// return id cua video va so coin tren likeviet
	public List<HashMap<Integer, String>> getLikeVietVideoId() {

		//
		HashMap<String, Integer> indexList = new HashMap<String, Integer>();
		indexList.put("id", 1);// id la tu dinh nghia theo y minh
//		indexList.put("coin", 1);// id la tu dinh nghia theo y minh
		String responseText = this.quickGetString(this.domain
				+ "/p.php?p=youtube");
//		 Utils.writeFile(Setting.getPathApp()+"/db.html", responseText);
		String pattern = "report_page\\(\\'(.*?)\\',\\'(.*?)\\',\\'(.*?)'\\)";

		List<HashMap<String, String>> list = Utils.filterList(responseText,
				pattern, indexList);
		
		String link = "";
		List<HashMap<Integer, String>> result = new ArrayList<HashMap<Integer, String>>();
		HashMap<Integer, String> hm = null;
		for (HashMap<String, String> hashMap : list) {
//			System.out.println("Test id :" + hashMap.get("id"));
			link = "http://" + this.domain + "/p.php?p=youtube&vid="
					+ hashMap.get("id");
			hm = new HashMap<Integer, String>();
			hm.put(Integer.parseInt(hashMap.get("id")),
					this.getVideoIdYoutube(link));
			result.add(hm);
		}
//		System.out.println("size: " + result.size());
		return result;
	}

	// get videoid youtube
	private String getVideoIdYoutube(String link) {
		String videoId = "";
		String html = this.quickGetString(link);
		String pattern = "youtube\\.com\\/v\\/(.*?)(\\:|\\?)";
		videoId = Utils.filter(html, pattern, 1);
		return videoId;
	}

	// Ham cheatTimeViewVideo
	// Ham nay thuc chat chi truy cap link cua like viet: http://likeviet.com.vn/p.php?p=youtube&vid=220 
	// Sau do sleep 13s
	private void cheatTimeViewVideo(String likeVietVideoId) {
		
//	System.out.println("videoID "+ videoID);
//	System.out.println("likeVietVideoId "+ likeVietVideoId);
//	String html = this.quickGetString("https://www.youtube.com/watch?v=" + videoID);
	String	link = "http://" + this.domain + "/p.php?p=youtube&vid=" + likeVietVideoId;
	this.quickGetString(link);
	try {
		Thread.sleep(13000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
//	 Utils.writeFile(Setting.getPathApp()+"/db.html", html);
	}
	
	// Ham verifyViewVideo

	public int verifyViewVideo(String likeVietVideoId) {
		
		int rs = -1;
		
		this.cheatTimeViewVideo(likeVietVideoId);
		this.generate_Cookie();

		this.req.RemoveAllParams();
		this.req.UsePost();
		this.req.put_Path("/system/modules/youtube/process.php");

		this.req.AddParam("data", likeVietVideoId);

		this.addHeader("http://" + this.domain + "/p.php?p=youtube&vid="
				+ likeVietVideoId);
		this.req.AddHeader("X-Requested-With", "XMLHttpRequest");

		boolean response = this.post(this.domain, 80, false);
		
		if (response != false) {
			String html = this.response.bodyStr();
			System.out.println(">" + html + "<");
			if (html.equals("")) {
				rs = 0;
			} else {
				rs = Integer.parseInt(html);
			}
		}
		System.out.println("ket qua la :" + rs);
		return rs;

	}

	public static void main(String[] args) {
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
//		System.out.println(Setting.lv.login("lmkhang", "761311kh?@"));
//		System.out.println(Setting.lv.login("mrcas", "KimLinh1807@"));
		// System.out.println(Setting.lv.getLikeVietVideoId().size());
		// System.out.println(Setting.lv.verifyViewVideo("2", "25A7VGlupyQ"));
//		String link = "/p.php?p=google";
		
		// /////////////-----------*************---------------////////////////
		// cach get list chua hashmap. ap dung toan bo.
//		List<HashMap<String, String>> sites = Setting.lv.getPlusLink(link);
//		for (HashMap<String, String> hashMap : sites) {
//			System.out.println("id: " + hashMap.get("id"));
//			System.out.println("coin: " + hashMap.get("coin"));
//		}
		// /////////////-----------*************---------------////////////////

		// /////////////-----------*************---------------////////////////
		// cach get list cho list videoid youtube
//		System.out.println("test");
//		List<HashMap<Integer, String>> sites2 = Setting.lv.getLikeVietVideoId();
//		for (HashMap<Integer, String> hashMap : sites2) {
//			for (Integer id : hashMap.keySet()) {
//				
//				System.out.println("id: " + id);
//				
//				System.out.println("value: " + hashMap.get(id));
//			}
//		}
		// /////////////-----------*************---------------////////////////
//		Setting.lv.verifyViewVideo ("2");
//		Setting.lv.verifySecondGooglePlus("336");
		
		// try {
		// Setting.lv.getListReferrals();
		// } catch (XPatherException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// Setting.lv.req = new CkHttpRequest();
		;
		// Setting.lv.req.UseGet();
		// Setting.lv.req.put_Path("/qg1ef");

		// Setting.lv.addHeader("https://shorte.st/statistics");

		// boolean response = Setting.lv.post("shorte.st", 80, false);

		// try {
		// Thread.sleep(5000);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// Test ham getcapcharType
		// Setting.lv.getCaptcha();
		// Setting.lv.registerGoogleCaptchar("mrcas", "tranthihongtho",
		// "hiepsitinhyeu09071985@gmail.com", "captchaText");
		// System.out.println(Setting.lv.login("mrcas", "KimLinh1807@"));//
		// Setting.lv.getBonus();
		// login
		// // lv
		// List<HashMap<String, String>> sites = Setting.lv
		// .getLinkFan_Photo("/p.php?p=ysub");// get sites
		// //
		// // // Starting to Test Facebook Function
		// String link = sites.get(0).get("link");// "100004915300490";// lay
		// // tam
		// // // site dau
		// String id = sites.get(0).get("id");
		//
		// String siteCheck = "/system/modules/ysub/process.php";
		// String refer = "/p.php?p=ysub";
		// System.out.println(link);
		// System.out.println(id);
		 String link = "https://www.facebook.com/pages/U-C-MAS-Nguy%E1%BB%85n-V%C4%83n-C%E1%BB%AB/595512587196656?fref=ts";
		 Setting.fb = Facebook.getInstance();
		 Setting.fb.setOnline(true);
		 Setting.fb.init("");
		//
		 System.out.println("Logging in Facebook");
		 System.out.println(Setting.fb
		 .login("nguyen.cas", "CasPo1807!"));
		 System.out.println("Check like");
		 boolean islike = Setting.fb.isLikeFanPage(link);
		 System.out.println(islike);
//		 Setting.fb.likeUnlikeFanPage(link, !islike);
		 Setting.fb.confirmLikeFacebook ("1416033375282361");
		// islike = Setting.fb.isLikeFanPage(link);
		// System.out.println(islike);
		//
		// System.out.println("Starting to log out");
		// Setting.fb.logout();
		// Starting to Test Google Function
		// String link = "RcA3vOE6ekU";

		// bat dau subcribe
		// Setting.gg = Gg.getInstance();
		// Setting.gg.setOnline(true);
		// Setting.gg.init("");
		// System.out.println("bat dau dang nhap Google");
		// System.out.println(Setting.gg.login("hiepsitinhyeu09071985",
		// "saoemvotinh"));
		// String link = "http://www.ytequandoi.vn";
		// boolean isPlus = Setting.gg.isPlus(link);
		// System.out.println(isPlus);
		// System.out.println("Bat dau subcribe youtube");
		// Setting.gg.subscribeUnSubscribe(link, true);
		// System.out.println(Setting.gg.isSubcriseAlready(link));
		//
		// System.out.println("Kiem tra da subcribe chua");
		// boolean isSubcribe = Setting.gg.isSubcriseAlready(link);
		// System.out.println(isSubcribe);
		// if (isSubcribe == true) {
		// Setting.gg.subscribeUnSubscribe(link, false);
		// }
		// System.out.println("So coin hien tai: " +
		// Setting.lv.getCurrentCoin());
		// // verify 1
		// System.out.println("Verify step 1");
		// System.out.println(Setting.lv.verifyFirst(siteCheck, refer, link,
		// id));
		//
		// System.out.println("Starting to subcribe channel: " + link);
		// Setting.gg.subscribeUnSubscribe(link, true);
		//
		// isSubcribe = Setting.gg.isSubcriseAlready(link);
		//
		// if (isSubcribe == true) {
		// // verify 2
		// System.out.println("Verify step 2");
		// System.out.println(Setting.lv.verifySecond(siteCheck, refer, id));
		// }
		//

		// System.out.println("Starting to unSub: "+ link);
		// Setting.gg.subscribeUnSubscribe(link, false);
		// // isSubcribe = Setting.gg.isSubcriseAlready(link);
		// // System.out.println(isSubcribe);
		// // boolean isLike = Setting.gg.isLikeAlready(link);
		// // System.out.println(isLike);
		// // Setting.gg.likeUnlike(link, !isLike);
		// // isLike = Setting.gg.isLikeAlready(link);
		// // System.out.println(isLike);
		// System.out.println("Logout Google");
		// Setting.gg.logout();
	}
}
