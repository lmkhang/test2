package Configs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Models.Facebook;
import Models.Gg;
import Models.Likeviet;
import Models.Main;
import Utils.Utils;

public class Setting {
	public static String getPathApp() {
		try {
			return new File(".").getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public static void prepareFolder() {
		Utils.deleteSubDirectory(new File(Setting.getPathApp() + "/"
				+ Setting.cookieFolder + "/"));
		Utils.createFolder(Setting.getPathApp() + "/" + Setting.cookieFolder
				+ "/");
	}

	public static String pathChilkatDll = Setting.getPathApp()
			+ "/dll/libx64.dll";
	public static String cookieFolder = "cookies";
	public static String keyChilkat = "Q37TWX11PZ9I4U0CV2YMY2780O5CRA1";// Q37TWX11PZ9I4U0CV2YMY2780O5CRA1
	public static int limit = 10;
	public static int limitError = 10;
	public static Gg gg = null;
	public static Facebook fb = null;
	public static Likeviet lv = null;
	public static Main sitemain = null;
	public static String settingFileName = Setting.getPathApp()
			+ "/setting.xml";
	public static String parent = "Setting";
	public static String refferId = "6161";
	public static String image_cache = "image_captcha";
	public static String emailSendMail = "nhacso004@gmail.com";
	public static String passwordSendMail = "761311kh?@";
	public static String urlRegister = "http://24h.com.vn";
	public static String urlForgot = "http://24h.com.vn";
	public static String keyActionPlus = getPathApp() + "/key/action_plus.lic";
	public static String htmlAdsense = "<html><body><a href='file:///D:/Khang/Working/izmaker/06_HTML/html_ver2.0-20131024/buy_1.html'>tested  eed</a></body></html>";
	public static int flagAdsense = 0;
	public static int numberAdv = 0;
	public static HashMap<String, String> urlAdsenses() {
		HashMap<String, String> hash = new HashMap<>();
		hash.put("24h", "http://24h.com.vn");
		hash.put("docbao", "http://docbao.vn");
		return hash;
	}

	public static List<String> listUserAgent() {
		List<String> list = new ArrayList<String>();
		list.add("Mozilla/5.0 (Windows NT 6.1; rv:24.0) Gecko/20100101 Firefox/24.0 AlexaToolbar/alxf-2.19");
		list.add("Mozilla/5.0 (Windows NT 6.1; rv:24.0) Gecko/20100102 Firefox/24.0 AlexaToolbar/alxf-2.19");
		list.add("Mozilla/5.0 (Windows NT 6.1; rv:24.0) Gecko/20100103 Firefox/24.0 AlexaToolbar/alxf-2.19");
		list.add("Mozilla/5.0 (Windows NT 6.1; rv:24.0) Gecko/20100104 Firefox/24.0 AlexaToolbar/alxf-2.19");
		list.add("Mozilla/5.0 (Windows NT 6.1; rv:24.0) Gecko/20100105 Firefox/24.0 AlexaToolbar/alxf-2.19");
		list.add("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1468.0 Safari/537.36");
		list.add("Mozilla/5.0 (Windows NT 6.2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1467.0 Safari/537.36");
		list.add("Mozilla/5.0 (Windows NT 6.2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1464.0 Safari/537.36");

		list.add("Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.93 Safari/537.36");
		list.add("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.93 Safari/537.36");
		list.add("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.93 Safari/537.36");
		list.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.93 Safari/537.36");

		list.add("Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.13 (KHTML, like Gecko) Chrome/24.0.1290.1 Safari/537.13");
		list.add("Mozilla/5.0 (Windows NT 6.2) AppleWebKit/537.13 (KHTML, like Gecko) Chrome/24.0.1290.1 Safari/537.13");
		list.add("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.13 (KHTML, like Gecko) Chrome/24.0.1284.0 Safari/537.13");
		list.add("Mozilla/5.0 (Windows NT 6.2) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.26 Safari/537.11");

		return list;
	}

	// po ol
	public static int maxthread = 2;
	public static int currentThreads = 0;
	public static ExecutorService executor = Executors
			.newFixedThreadPool(maxthread);
}
