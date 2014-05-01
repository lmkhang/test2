package Models;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JOptionPane;

import Configs.Setting;
import Libs.LoadChilkat;
import Utils.Utils;

import com.chilkatsoft.CkCharset;
import com.chilkatsoft.CkHttp;
import com.chilkatsoft.CkHttpRequest;
import com.chilkatsoft.CkHttpResponse;

public class Facebook extends Parent {

	public static Facebook fb = null;
	public String domain = "www.facebook.com";
	private String lsd = "";
	private String lgnrnd = "";
	private String lgnjs = "";
	private String cuid = "";
	private String c_user = "";
	private String resLogin = "";
	private String fb_dtsg = "";
	private String get_req = "";
	private String h = "";
	private String pluginLink = "https://www.facebook.com/plugins/like.php?layout=button_count&href=";

	private Facebook() {
		System.out.println("Process: Create Facebook Object");
	}

	@Override
	public Facebook clone() throws CloneNotSupportedException {

		return (Facebook) super.clone();

	}

	// delete fb
	public Facebook setNull(String folder, String dir) {

		// System.out.println("Process: Delete Facebook Object");
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
		fb = null;
		return fb;
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

	// cac thong so khoi tao
	// @Override
	// public void init(String folder) {
	// super.init(folder);
	// Utils.createFolder(Setting.getPathApp() + "/" + Setting.cookieFolder
	// + "/" + folder + "/login");
	// this.http.put_CookieDir(Setting.getPathApp() + "/"
	// + Setting.cookieFolder + "/" + folder + "/login");
	// }

	// login to facebook
	public int login(String username, String password) {
		// System.out.println("Process: Get Info For Login");
		if (username != null && password != null) {
			this.username = username;
			this.password = password;
		}
		String html = this.quickGetString(this.domain);

		String datr = Utils.filter(this.http.getCookieXml(this.domain),
				"<datr>(.*?)</datr>", 1);
		String reg_fb_gate = Utils.filter(this.http.getCookieXml(this.domain),
				"<reg_fb_gate>(.*?)</reg_fb_gate>", 1);
		String reg_fb_ref = Utils.filter(this.http.getCookieXml(this.domain),
				"<reg_fb_ref>(.*?)</reg_fb_ref>", 1);
		this.cookie = "datr=" + datr + ";reg_fb_gate=" + reg_fb_gate + ";";
		this.cookie += "reg_fb_ref=" + reg_fb_ref;
		// Utils.writeFile(Setting.getPathApp() + "/dg.html", html);

		this.lsd = Utils.filter(html, "name=\"lsd\" value=\"(.*?)\"", 1);
		try {
			this.lgnrnd = Utils.filter(html,
					"name=\"lgnrnd\" value=\"(.*?)\" />", 1);
			long timestamp = System.currentTimeMillis() / 1000;

			this.lgnjs = String.valueOf(timestamp);

		} catch (Exception ex) {
			return 0;
		}
		try {
			this.cuid = Utils.filter(html, "name=\"cuid\" value=\"(.*?)\" />",
					1);
		} catch (Exception e) {
			this.cuid = "";
		}
		int result = 0;
		this.req = new CkHttpRequest();
		// use method
		this.req.UsePost();
		// add param
		this.req.put_Path("/login.php");
		this.req.AddParam("lsd", this.lsd);
		this.req.AddParam("email", this.username);
		this.req.AddParam("pass", this.password);
		this.req.AddParam("default_persistent", "0");
		this.req.AddParam("timezone", "-7");
		this.req.AddParam("lgnrnd", this.lgnrnd);
		this.req.AddParam("lgnjs", this.lgnjs);
		this.req.AddParam("locale", "en_US");
		if (this.cuid != "") {
			this.req.AddParam("cuid", this.cuid);
		}
		// add header

		this.addHeader("https://" + this.domain);
		// set response
		boolean response = this.post(this.domain, 443, true);

		if (response != false) {
			this.resLogin = this.response.bodyStr();
			if (this.resLogin.contains("Temporarily Locked")) {
				result = -1;
			} else if (!this.resLogin.contains("Temporarily Locked")) {
				this.quickGetString(this.domain);
				try {
					this.c_user = Utils.filter(
							this.http.getCookieXml(this.domain),
							"<c_user>(.*?)</c_user>", 1);
				} catch (Exception e) {
					this.c_user = "";
				}

				if (this.c_user != "") {
					result = 1;
				}
			}
		}
		return result;
	}

	// tao cookie
	public void generate_Cookie() {
		// TODO Auto-generated method stub
		try {
			this.cookie = "datr="
					+ Utils.filter(this.http.getCookieXml(this.domain),
							"<datr>(.*?)</datr>", 1)
					+ "; fr="
					+ Utils.filter(this.http.getCookieXml(this.domain),
							"<fr>(.*?)</fr>", 1) + "; ";

			this.cookie += "lu="
					+ Utils.filter(this.http.getCookieXml(this.domain),
							"<lu>(.*?)</lu>", 1) + "; locale=en_US; c_user="
					+ this.c_user + "; csm=2; ";

			this.cookie += "s="
					+ Utils.filter(this.http.getCookieXml(this.domain),
							"<s>(.*?)</s>", 1)
					+ "; xs="
					+ Utils.filter(this.http.getCookieXml(this.domain),
							"<xs>(.*?)</xs>", 1) + "; ";
			this.cookie += "sub=65536; p=260;";
		} catch (Exception e) {
			this.login(null, null);
			this.generate_Cookie();
		}
	}

	public String Getfb_dtsg() {
		this.fb_dtsg = Utils.filter(this.resLogin,
				"name=\"fb_dtsg\" value=\"(.*?)\" autocomplete=\"off\"", 1);
		return this.fb_dtsg;
	}

	public String Get_req(String url) {
		String html = this.quickGetString(url);
		this.get_req = Utils.filter(html, "&star_req=(.*?)\">", 1);
		return this.get_req;
	}

	public String Geth() {
		this.h = Utils
				.filter(this.resLogin, "name=\"h\" value=\"(.*?)\" />", 1);
		return this.h;
	}

	public String Getc_user() {
		try {
			this.c_user = Utils.filter(this.http.getCookieXml(this.domain),
					"<c_user>(.*?)</c_user>", 1);
		} catch (Exception e) {
			this.login(null, null);
			this.Getc_user();
		}
		return this.c_user;
	}

	// logout facebook
	public boolean logout() {
		Utils.deleteSubDirectory(new File(Setting.getPathApp() + "/"
				+ Setting.cookieFolder + "/fb/"));
		this.generate_Cookie();
		// use method
		this.req.UsePost();
		// add param
		this.req.put_Path("/logout.php?confirm=1");
		this.req.AddParam("ref", "mb");

		// add header

		this.addHeader("https://" + this.domain);
		// set response
		boolean response = this.post(this.domain, 443, true);
		if (response == false) {
			return false;
		}
		return true;
	}

	// get status login
	public boolean getStatus() {
		// System.out.println("Process: Get Status");
		String html = this.quickGetString(this.domain);
		return html.contains("Sign Up");
	}

	// get instance
	public static Facebook getInstance() {
		// System.out.println("Process: create new fb");
		// if (fb == null) {
		fb = new Facebook();
		// }
		return fb;
	}

	// ////////////////////like
	// fan page
	public boolean isLikeFanPage(String fanpageLink) {

		String html = this.quickGetString(this.pluginLink + fanpageLink);

		try {
			Utils.filter(html, "action=\"/plugins/like/disconnect", 0);

			return true;
		} catch (Exception e) {

			return false;
		}
	}

	// Ham likeUnlikeFanpage
	// return -1 = rot mang, 1 =thanh cong, 0 = that bai, 2 = Bi block tam thoi
	// Thong bao tra ve: It looks like you were misusing this feature by going
	// too fast. You’ve been blocked from using it.
	public int likeUnlikeFanPage(String fanpageLink, boolean islike) {
		// System.out.println("Process: Like Fan " + fanpageLink);
		int rs = 1;

		this.generate_Cookie();
		// System.out.println(this.cookie);

		this.req.RemoveAllParams();
		this.req.UsePost();
		// Neu isLike = False = Unlike
		if (islike == false) {
			this.req.put_Path("/plugins/like/disconnect");
		} else {
			this.req.put_Path("/plugins/like/connect");
		}

		this.req.AddParam("__a", "1");
		this.req.AddParam("__dyn", "7wfGbw");
		this.req.AddParam("__req", "1");
		this.req.AddParam("__user", this.Getc_user());
		this.req.AddParam("action", "like");
		this.req.AddParam("fb_dtsg", this.Getfb_dtsg());
		this.req.AddParam("href", this.http.urlEncode(fanpageLink));
		this.req.AddParam("iframe_referer", fanpageLink);
		this.req.AddParam("nobootload", "");
		this.req.AddParam("ref", fanpageLink);
		this.req.AddParam("ttstamp", this.http.genTimeStamp());
		// add header

		this.addHeader(fanpageLink);
		// set response
		boolean response = this.post(this.domain, 443, true);

		if (response == false) {
//			System.out.println("rot cmn mang roi");
			return -1;
		}
		 System.out.println("kiem tra lai lan nua cho chac cu da like hay chua");
		boolean isLikeFanpage = this.isLikeFanPage(fanpageLink);
		if (isLikeFanpage != islike) {
			 System.out.println("Fanpage co van de ne " + fanpageLink);
			String responseText = this.response.bodyStr();
			// Utils.writeFile(Setting.getPathApp()+"/db.html", responseText);
			// Truong hop acc bi block chuc nang like
			if (responseText.contains("Temporarily+Blocked")) {
				// System.out.println("Account bi tam khoa chuc nang like");
				rs = 2;
				return rs;
			}
			this.setTrying(this.getTrying() + 1);
			this.main.updateRecord(this.row, 6,
					String.valueOf(this.getTrying()));
			if (this.trying > Setting.limit - 1) {
				return 0;
			}
			rs = this.likeUnlikeFanPage(fanpageLink, islike);
		}
		return rs;
	}

	// Ham confirmLikeFacebook
	// return -1 = rot mang, 1 = thanh cong, 0 = that bai
	// Truong hop bi block, confirm khong thanh cong tuc la bi block hoan toan
	// facebook và facebook bắt ngưng mấy ngày. Cái này do ăn ở.
	// Hiển thị thông báo với người sử dụng là dùng account facebook khác là vừa
	public int confirmLikeFacebook(String fanpageId) {
		int rs = -1;
		this.generate_Cookie();

		// this.quickGetString("https://" + this.domain + "/" + fanpageId);
		this.quickGetString(fanpageId);
		String redirect = this.http.finalRedirectUrl();

		this.req.RemoveAllParams();
		this.req.UsePost();
		this.req.put_Path("/ajax/pages/fan_status.php");

		this.req.AddParam("fbpage_id", fanpageId);
		this.req.AddParam("add", "true");
		this.req.AddParam("reload", "false");
		this.req.AddParam("fan_origin", "page_timeline");
		this.req.AddParam("fan_source", null);
		this.req.AddParam("cat", null);
		this.req.AddParam("nctr[_mod]", "pagelet_timeline_page_actions");
		this.req.AddParam("__user", this.c_user);
		this.req.AddParam("__a", "1");
		this.req.AddParam("__dyn", "7n8aqEAMCBynxl2u5KG8HzCq7ui78hAKGgyiGGfJ0");
		this.req.AddParam("__req", "g");
		this.req.AddParam("fb_dtsg", this.fb_dtsg);
		this.req.AddParam("ttstamp", "265816690731058167");
		this.req.AddParam("__rev", "1108054");
		this.req.AddParam("confirmed", "1");

		this.addHeader(redirect);
		// set response
		boolean response = this.post(this.domain, 443, true);

		// Utils.writeFile(Setting.getPathApp()+"/db.html", html);

		if (response == false) {

			return -1;
		} else {
			String html = this.response.bodyStr();
			if (html.contains("errorSummary")) {
				rs = 0;
			} else {
				rs = 1;
			}
		}

		return rs;

	}

	// /////////////photo
	private String Get_ft_ent_identifier(String photoID) {
		String responseText = this
				.quickGetString("https://www.facebook.com/photo.php?fbid="
						+ photoID);
		String Temp = "";
		try {
			Temp = Utils.filter(responseText, "set=gm.(.*?)&amp", 1);
		} catch (Exception e) {
			Temp = photoID;
		}

		return Temp;

	}

	// like Photo
	public boolean isLikePhoto(String photoId) {
		// System.out.println("Process: Get Status isLikePhoto");
		String html = this.http
				.quickGetStr("https://www.facebook.com/photo.php?fbid="
						+ photoId);
		try {
			String rs = Utils.filter(html, "\"hasviewerliked\":(.*?),", 1);
			if (rs.equals("false")) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
	}

	public int likePhoto(String photoId, boolean isLike) {
		// System.out.println("Process: Like Photo");
		this.generate_Cookie();
		String responseText = this.http
				.quickGetStr("https://www.facebook.com/photo.php?fbid="
						+ photoId);
		String revs = Utils.filter(responseText, "\"revision\":(.*?),\"tier\"",
				1);

		int rs = 1;
		this.req.RemoveAllParams();
		this.req.UsePost();
		this.req.put_Path("/ajax/ufi/like.php");
		if (isLike == true) {
			this.req.AddParam("like_action", "true");
		} else {
			this.req.AddParam("like_action", "false");
		}

		this.req.AddParam("ft_ent_identifier",
				this.Get_ft_ent_identifier(photoId));
		this.req.AddParam("source", "2");
		this.req.AddParam("rootid", URLEncoder.encode("u_0_8"));
		this.req.AddParam("giftoccasion", "");
		this.req.AddParam("ft[tn]=>", "");
		this.req.AddParam("ft[type]", "20");
		this.req.AddParam("__user", URLEncoder.encode(this.Getc_user()));
		this.req.AddParam("__a", "1");
		this.req.AddParam("__dyn", "7n8ahyj35CFUSt2u5KIGKaExEW9ACUqx6iWF0");
		this.req.AddParam("__req", "10");
		this.req.AddParam("fb_dtsg", URLEncoder.encode(this.Getfb_dtsg()));
		this.req.AddParam("__rev", URLEncoder.encode(revs));
		this.req.AddParam("ttstamp", this.http.genTimeStamp());
		// add header

		this.addHeader("https://www.facebook.com/photo.php?fbid=" + photoId);
		// set response
		boolean response = this.post(this.domain, 443, true);
		if (response == false) {
			return -1;
		}
		boolean isLikePhoto = this.isLikePhoto(photoId);
		if (isLikePhoto != isLike) {
			this.trying++;
			this.main.updateRecord(this.row, 6, String.valueOf(this.trying));
			if (this.trying > Setting.limit - 1) {
				return 0;
			}
			rs = this.likePhoto(photoId, isLike);
		}
		return rs;
	}

	// true: following, false: not follow
	public boolean isFollow(String facebookID) {
		System.out.println(facebookID);
		String pattern = "id=\"timeline_following_swap_button(.*?)><a (.*?)hidden_elem(.*?)>(.*?)<span class=\"uiButtonText\">Follow</span></a>";
		String html = this.quickGetString("https://www.facebook.com/"
				+ facebookID);
		// System.out.println("https://www.facebook.com/" + facebookID);
		// Utils.writeFile(Setting.getPathApp() + "/db.html", html);
		try {
			Utils.filter(html, pattern, 0);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// like Subscribe_UnSubscribe Facebook
	// return -1 = rot mang; 0 = failed; 1 = thanh cong; 2 = Facebook user da bi
	// xoa hoac Facebook update
	public int subscribeUnsubscribe(String facebookID, boolean isSubscribe) {
		int rs = 1;
		String revs;
		String responseText;
		this.generate_Cookie();
		responseText = this.quickGetString("https://" + this.domain + "/"
				+ facebookID);
		try {
			revs = Utils.filter(responseText, "\"revision\":(.*?),\"tier\"", 1);
		} catch (Exception e) {
			rs = 2;
			return rs;
		}

		this.req.RemoveAllParams();
		this.req.UsePost();
		// Neu isSubscribe = False = UnSubscribe
		if (isSubscribe == false) {
			this.req.put_Path("/ajax/follow/unfollow_profile.php");
			this.req.AddParam("feed_blacklist_action",
					"hide_followee_on_unfollow");

		} else {
			this.req.put_Path("/ajax/follow/follow_profile.php");
			this.req.AddParam("feed_blacklist_action",
					"show_followee_on_follow");
		}

		this.req.AddParam("profile_id", facebookID);
		this.req.AddParam("location", "1");
		this.req.AddParam("nctr%5B_mod%5D", "pagelet_timeline_profile_actions");
		this.req.AddParam("__user", this.Getc_user());
		this.req.AddParam("__a", "1");
		this.req.AddParam("__dyn", "7n8aqEAMNoBUSt2u5KIGKaExEW9ACxO4pbGA8AGm");
		this.req.AddParam("__rev", revs);
		this.req.AddParam("__req", revs);
		this.req.AddParam("fb_dtsg", this.Getfb_dtsg());
		this.req.AddParam("ttstamp", this.http.genTimeStamp());

		this.addHeader("https://" + this.domain + "/" + facebookID);
		boolean response = this.post(this.domain, 443, true);
		// if (response == false) {
		// return false;
		// }
		// responseText = this.response.bodyStr();
		// // System.out.println(responseText);
		// if (isSubscribe == true) {
		// try {
		// Utils.filter(responseText, "FollowUser", 0);
		// return true;
		// } catch (Exception e) {
		// // TODO: handle exception
		// return false;
		// }
		// }
		if (response == false) {
			return -1;
		}
		boolean isFollow = this.isFollow(facebookID);
		if (isFollow != isSubscribe) {
			this.trying++;
			this.main.updateRecord(this.row, 6, String.valueOf(this.trying));
			if (this.trying > Setting.limit - 1) {
				return 0;
			}
			rs = this.subscribeUnsubscribe(facebookID, isSubscribe);
		}
		return rs;
	}

	// share fb
	public int shareFacebook(String WebsiteLink) {
		int rs = 1;
		String responseText = this.quickGetString("https://" + this.domain
				+ "/sharer/sharer.php?u=" + this.http.urlEncode(WebsiteLink));
		this.generate_Cookie();

		// String pageTarget = Utils.filter(responseText,
		// "name=\"pageTarget\" value=\"(.*?)\" id=\"", 1);
		String app_id = "";
		String appid = "";
		String attach_img = "";
		String Title = "";
		String summary ="";
		String title_len = "";
		String summary_len = "";
		String revs = "";
		try {
			app_id = Utils.filter(responseText,
					"name=\"app_id\" value=\"(.*?)\" />", 1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			 appid = Utils.filter(responseText,
						"name=\"appid\" value=\"(.*?)\" />", 1);
		} catch (Exception e) {
			// TODO: handle exception
		}	 
		try {
			attach_img = Utils.filter(responseText,
					"class=\"UIShareStage_Image img\" src=\"(.*?)&amp;", 1);
		} catch (Exception e) {
			// TODO: handle exception
		}	
		try {
			 Title = Utils
						.filter(responseText,
								"class=\"UIShareStage_Title\"><span dir=\"ltr\">(.*?)</span></div>",
								1);
		} catch (Exception e) {
			// TODO: handle exception
		}		
		try {
			summary = Utils
					.filter(responseText,
							"<div class=\"UIShareStage_Summary\"><p class=\"UIShareStage_BottomMargin\">(.*?)</p></div></div>",
							1);
		} catch (Exception e) {
			// TODO: handle exception
		}		 
		try {
			title_len = Utils.filter(responseText,
					"[title_len]\" value=\"(.*?)\" />", 1);
		} catch (Exception e) {
			// TODO: handle exception
		}		
		try {
			summary_len = Utils.filter(responseText,
					"[summary_len]\" value=\"(.*?)\" />", 1);
		} catch (Exception e) {
			// TODO: handle exception
		}		 
		try {
			 revs = Utils.filter(responseText,
						"\"revision\":(.*?),\"tier\"", 1);
		} catch (Exception e) {
			// TODO: handle exception
		}	 
			 
			
			this.req.put_SendCharset(true);
			this.req.put_Charset("utf-8");
			this.req.RemoveAllParams();
			this.req.UsePost();

			this.req.put_Path("/ajax/sharer/submit_page/");
			this.req.AddParam("fb_dtsg", this.Getfb_dtsg());
			this.req.AddParam("next", null);
			this.req.AddParam("mode", "self");
			this.req.AddParam("friendTarget", null);
			this.req.AddParam("groupTarget", null);
			this.req.AddParam("post_as_page", "1");
			this.req.AddParam("message_text", null);
			this.req.AddParam("message", null);
			this.req.AddParam("app_id", app_id);
			this.req.AddParam("attachment[params][urlInfo][canonical]",
					WebsiteLink);
			this.req.AddParam("attachment[params][urlInfo][final]", WebsiteLink);
			this.req.AddParam("attachment[params][urlInfo][user]", WebsiteLink);
			this.req.AddParam("attachment[params][favicon]", "http://"
					+ this.http.getDomain(WebsiteLink) + "/favicon.ico");
			this.req.AddParam("attachment[params][title]", Title);
			this.req.AddParam("attachment[params][summary]", summary);
			this.req.AddParam("attachment[params][images][0]", attach_img);
			this.req.AddParam("w", "100");
			this.req.AddParam("h", "100");
			this.req.AddParam("url", WebsiteLink);
			this.req.AddParam("cfs", "1");
			this.req.AddParam("upscale", null);
			this.req.AddParam("attachment[params][medium]", "106");
			this.req.AddParam("attachment[params][url]", WebsiteLink);
			this.req.AddParam("attachment[type]", "100");
			this.req.AddParam("link_metrics[source]", "ShareStageExternal");
			this.req.AddParam("link_metrics[domain]",
					this.http.getDomain(WebsiteLink));
			this.req.AddParam("link_metrics[base_domain]",
					this.http.getDomain(WebsiteLink));
			this.req.AddParam("link_metrics[title_len]", title_len);
			this.req.AddParam("link_metrics[summary_len]", summary_len);
			this.req.AddParam("link_metrics[min_dimensions][0]", "70");
			this.req.AddParam("link_metrics[min_dimensions][1]", "70");
			this.req.AddParam("link_metrics[images_with_dimensions]", "3");
			this.req.AddParam("link_metrics[images_pending]", "0");
			this.req.AddParam("link_metrics[images_fetched]", "0");
			this.req.AddParam("link_metrics[image_dimensions][0]", "640");
			this.req.AddParam("link_metrics[image_dimensions][1]", "207");
			this.req.AddParam("link_metrics[images_selected]", "3");
			this.req.AddParam("link_metrics[images_considered]", "5");
			this.req.AddParam("link_metrics[images_cap]", "3");
			this.req.AddParam("link_metrics[images_type]", "ranked");
			this.req.AddParam("composer_metrics[best_image_w]", "100");
			this.req.AddParam("composer_metrics[best_image_h]", "100");
			this.req.AddParam("composer_metrics[image_selected]", "0");
			this.req.AddParam("composer_metrics[images_provided]", "3");
			this.req.AddParam("composer_metrics[images_loaded]", "3");
			this.req.AddParam("composer_metrics[images_shown]", "3");
			this.req.AddParam("composer_metrics[load_duration]", "10");
			this.req.AddParam("composer_metrics[timed_out]", "0");
			this.req.AddParam("composer_metrics[sort_order]", null);
			this.req.AddParam("share_source_type", "unknown");
			this.req.AddParam("src", null);
			this.req.AddParam("appid", appid);
			this.req.AddParam("parent_fbid", null);
			this.req.AddParam("ogid", null);
			this.req.AddParam("audience[0][value]", "80");
			this.req.AddParam("UITargetedPrivacyWidget", "80");
			this.req.AddParam("share", "Share Link");
			this.req.AddParam("__user", this.Getc_user());
			this.req.AddParam("__a", "1");
			this.req.AddParam("__dyn", "7wAzU4Oi3S8wIw");
			this.req.AddParam("__req", "3");
			this.req.AddParam("__rev", revs);
			this.req.AddParam("ttstamp", this.http.genTimeStamp());

			// add header
			this.addHeader("https://" + this.domain + "/sharer/sharer.php?u="
					+ WebsiteLink);
			boolean response = this.post(this.domain, 443, true);
			if (response == false) {
				System.out.println("Response Share = " + response);
				return -1;
			}
		
		return rs;
	}
	
	// share Youtube len facebook
	//YoutubeLink = https://www.youtube.com/watch?v=FbPOfq2K4l4
		public int shareYoutubeOnFacebook(String YoutubeLink, String messageToShare) {
			int rs = 1;
			String responseText = this.quickGetString("https://" + this.domain
					+ "/sharer/sharer.php?u=" + this.http.urlEncode(YoutubeLink));
			this.generate_Cookie();

			// String pageTarget = Utils.filter(responseText,
			// "name=\"pageTarget\" value=\"(.*?)\" id=\"", 1);
			String app_id = "";
			String appid = "";
			String revs = "";
			try {
				app_id = Utils.filter(responseText,
						"name=\"app_id\" value=\"(.*?)\" />", 1);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				 appid = Utils.filter(responseText,
							"name=\"appid\" value=\"(.*?)\" />", 1);
			} catch (Exception e) {
				// TODO: handle exception
			}	 			 
			try {
				 revs = Utils.filter(responseText,
							"\"revision\":(.*?),\"tier\"", 1);
			} catch (Exception e) {
				// TODO: handle exception
			}	 
				 
				
				this.req.put_SendCharset(true);
				this.req.put_Charset("utf-8");
				this.req.RemoveAllParams();
				this.req.UsePost();

				this.req.put_Path("/ajax/sharer/submit_page/");
				this.req.AddParam("fb_dtsg", this.Getfb_dtsg());				
				this.req.AddParam("__user", this.Getc_user());
				this.req.AddParam("__a", "1");
				this.req.AddParam("__dyn", "7wAzU4Oi3S3mbx6m5FuC");
				this.req.AddParam("__req", "h");
				this.req.AddParam("__rev", revs);
				this.req.AddParam("ttstamp", this.http.genTimeStamp());
				this.req.AddParam("app_id", app_id);
				this.req.AddParam("appid", appid);
				this.req.AddParam("audience[0][value]", "80");
				this.req.AddParam("friendTarget", null);
				this.req.AddParam("groupTarget", null);
				this.req.AddParam("link_metrics[base_domain]", "youtube.com");
				this.req.AddParam("link_metrics[domain]", "www.youtube.com");
				this.req.AddParam("link_metrics[image_dimensions][0]", "480");
				this.req.AddParam("link_metrics[image_dimensions][1]", "360");
				this.req.AddParam("link_metrics[images_cap]", "10");
				this.req.AddParam("link_metrics[images_considered]", "1");
				this.req.AddParam("link_metrics[images_fetch_duration]", "12");
				this.req.AddParam("link_metrics[images_fetched]", "1");
				this.req.AddParam("link_metrics[images_pending]", "1");
				this.req.AddParam("link_metrics[images_selected]", "1");
				this.req.AddParam("link_metrics[images_type]", "images_array");
				this.req.AddParam("link_metrics[images_with_dimensions]", "1");
				this.req.AddParam("link_metrics[min_dimensions][0]", "62");
				this.req.AddParam("link_metrics[min_dimensions][1]", "62");
				this.req.AddParam("link_metrics[source]", "ShareStageExternal");
				this.req.AddParam("link_metrics[summary_len]", "320");
				this.req.AddParam("link_metrics[title_len]", "150");
				this.req.AddParam("message", messageToShare);
				this.req.AddParam("message_text", messageToShare);
				this.req.AddParam("mode", "self");
				this.req.AddParam("next", null);
				this.req.AddParam("ogid", null);
				this.req.AddParam("parent_fbid", null);
				this.req.AddParam("share", "1");
				this.req.AddParam("share_source_type", "unknown");
				this.req.AddParam("src", null);
				this.req.AddParam("type", "100");
				this.req.AddParam("url", YoutubeLink);
				
				

				// add header
				this.addHeader("https://" + this.domain + "/sharer/sharer.php?u="
						+ YoutubeLink);
				boolean response = this.post(this.domain, 443, true);
				if (response == false) {
					System.out.println("Response Share = " + response);
					return -1;
				}
			
			return rs;
		}
}
