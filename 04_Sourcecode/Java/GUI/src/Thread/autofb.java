package Thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import views.MainView;
import Configs.Message;
import Configs.Setting;
import GUI.gui;
import Models.Facebook;
import Models.Likeviet;
import Utils.Utils;

public class autofb implements Runnable {
	private MainView main;
	private List<HashMap<String, String>> sites;
	private String type = "";
	private int index;
	private int total = 0;
	private progress pb = null;
	private List<String> errorLinks = new ArrayList<>();
	private List<String> errorLinks2 = new ArrayList<>();
	private String subject = "[LikeViet] Has the problem.";
	private String subjectFB = "[Facebook] Has the problem.";
	private String content = "[LikeViet] Has the problem.\r\n.Error Links: \r\n";
	private String contentFB = "[Facebook] Has the problem.\r\n.Error Links: \r\n";

	public autofb(Object obj, String type, int index) {
		// TODO Auto-generated constructor stub
		this.main = (MainView) obj;
		this.type = type;
		this.index = index;

	}

	@Override
	public void run() {
		System.out.println("flag adv: " + Setting.flagAdsense);
		if (Setting.flagAdsense <= Setting.numberAdv) {
			this.main.showMessageError("Advertise", Message.adsense);
			return;
		}

		Setting.currentThreads++;
		// TODO Auto-generated method stub
		if (this.type.equalsIgnoreCase("fanpage")) {
			this.likeFanpage();
		} else if (this.type.equalsIgnoreCase("photo")) {
			this.likephoto();
		} else if (this.type.equalsIgnoreCase("follow")) {
			this.follow();
		} else if (this.type.equalsIgnoreCase("share")) {
			this.share();
		} else if (this.type.equalsIgnoreCase("likeWebsite")) {
			this.likeWebsite();
		}
	}

	private void share() {
		// TODO Auto-generated method stub
		this.main.getLabelShareOnFacebook().setEnabled(false);
		Likeviet lv = null;
		try {
			lv = Setting.lv.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Facebook fb = null;
		try {
			fb = Setting.fb.clone();
			fb.setCookieDir(fb.domain);// set other place for cookie
			fb.addViewFB(this.main);
			fb.setUserAgent(Setting.listUserAgent().get(this.index));
			// this.main.getNet().addObserver(fb);

			// init
			String link = "/p.php?p=fb_share";
			String siteCheck = "/system/modules/fb_share/process.php";
			String refer = link;

			int error = 0, error2 = 0;
			while (true) {
				// likeviet-get site
				this.sites = lv.getLinkFan_Photo(link);
				this.total = this.sites.size();
				// if (this.total == 0) {
				// if (this.pb != null) {
				// this.pb.stop();
				// this.pb = null;
				// }
				// break;
				// }
				int start = 0;
				// if (this.pb != null) {
				// this.pb.stop();
				// this.pb = null;
				// }
				// this.pb = new progress(this.total,
				// this.main.getDialog().pb_4,
				// this.type);
				// pb.start();
				this.process(sites, start, fb, error, error2, siteCheck, refer,
						lv);

			}
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// this.main.getNet().removeObserver(fb);
		// this.main.getMain().btn_fb_share.setEnabled(true);
		fb.setNull("", "");

	}

	private void follow() {
		// TODO Auto-generated method stub
		this.main.getLabelFollow().setEnabled(false);
		Likeviet lv = null;
		try {
			lv = Setting.lv.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Facebook fb = null;
		try {
			fb = Setting.fb.clone();
			fb.setCookieDir(fb.domain);// set other place for cookie
			fb.addViewFB(this.main);
			fb.setUserAgent(Setting.listUserAgent().get(this.index));
			// this.main.getNet().addObserver(fb);

			// init
			String link = "/p.php?p=fb_subscribe";
			String siteCheck = "/system/modules/fb_subscribe/process.php";
			String refer = link;

			int error = 0, error2 = 0;
			while (true) {
				// likeviet-get site
				this.sites = lv.getLinkFan_Photo(link);
				this.total = this.sites.size();
				// if (this.total == 0) {
				// if (this.pb != null) {
				// this.pb.stop();
				// this.pb = null;
				// }
				// break;
				// }
				int start = 0;
				// if (this.pb != null) {
				// this.pb.stop();
				// this.pb = null;
				// }
				// this.pb = new progress(this.total,
				// this.main.getDialog().pb_3,
				// this.type);
				// pb.start();
				this.process(sites, start, fb, error, error2, siteCheck, refer,
						lv);

			}
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// this.main.getNet().removeObserver(fb);
		// this.main.getMain().btn_fb_follow.setEnabled(true);
		fb.setNull("", "");
	}

	private void likephoto() {
		// TODO Auto-generated method stub
		this.main.getLabelPhoto().setEnabled(false);
		Likeviet lv = null;
		try {
			lv = Setting.lv.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Facebook fb = null;
		try {
			fb = Setting.fb.clone();
			fb.setCookieDir(fb.domain);// set other place for cookie
			fb.addViewFB(this.main);
			fb.setUserAgent(Setting.listUserAgent().get(this.index));
			// this.main.getNet().addObserver(fb);

			// init
			String link = "/p.php?p=fb_photo";
			String siteCheck = "/system/modules/fb_photo/process.php";
			String refer = link;

			int error = 0, error2 = 0;
			while (true) {
				// likeviet-get site
				this.sites = lv.getLinkFan_Photo(link);
				this.total = this.sites.size();
				// if (this.total == 0) {
				// if (this.pb != null) {
				// this.pb.stop();
				// this.pb = null;
				// }
				// break;
				// }
				int start = 0;
				// if (this.pb != null) {
				// this.pb.stop();
				// this.pb = null;
				// }
				// this.pb = new progress(this.total,
				// this.main.getDialog().pb_2,
				// this.type);
				// pb.start();
				this.process(sites, start, fb, error, error2, siteCheck, refer,
						lv);

			}
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// this.main.getNet().removeObserver(fb);
		// this.main.getMain().btn_fb_photo.setEnabled(true);
		fb.setNull("", "");
	}

	private void likeWebsite() {
		this.main.getLabelWebsite().setEnabled(false);
		Likeviet lv = null;
		try {
			lv = Setting.lv.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Facebook fb = null;
		try {
			fb = Setting.fb.clone();
			fb.setCookieDir(fb.domain);// set other place for cookie
			fb.addViewFB(this.main);
			fb.setUserAgent(Setting.listUserAgent().get(this.index));
			// this.main.getNet().addObserver(fb);

			// init
			String link = "/p.php?p=facebook&t=web";
			String siteCheck = "/system/modules/facebook/process.php";
			String refer = link;

			int error = 0, error2 = 0;
			while (true) {
				// likeviet-get site
				this.sites = lv.getLinkFan_Photo(link);
				// this.total = this.sites.size();
				// if (this.total == 0) {
				// if (this.pb != null) {
				// this.pb.stop();
				// this.pb = null;
				// }
				// break;
				// }
				int start = 0;
				// if (this.pb != null) {
				// this.pb.stop();
				// this.pb = null;
				// }
				// this.pb = new progress(this.total,
				// this.main.getDialog().pb_5,
				// this.type);
				// pb.start();
				this.process(sites, start, fb, error, error2, siteCheck, refer,
						lv);
			}
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// this.main.getNet().removeObserver(fb);
		// this.main.getMain().btn_fb_website.setEnabled(true);
		fb.setNull("", "");
	}

	private void likeFanpage() {
		this.main.getLabelFanpage().setEnabled(false);
		Likeviet lv = null;
		try {
			lv = Setting.lv.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Facebook fb = null;
		try {
			fb = Setting.fb.clone();
			fb.setCookieDir(fb.domain);// set other place for cookie
			fb.addViewFB(this.main);
			fb.setUserAgent(Setting.listUserAgent().get(this.index));
			// this.main.getNet().addObserver(fb);

			// init
			String link = "/p.php?p=facebook";
			String siteCheck = "/system/modules/facebook/process.php";
			String refer = link;

			int error = 0, error2 = 0;
			while (true) {
				// likeviet-get site
				this.sites = lv.getLinkFan_Photo(link);
				this.total = this.sites.size();
				if (this.total == 0) {
					if (this.pb != null) {
						this.pb.stop();
						this.pb = null;
					}
					break;
				}
				int start = 0;
				if (this.pb != null) {
					this.pb.stop();
					this.pb = null;
				}
				// this.pb = new progress(this.total,
				// this.main.getDialog().pb_1,
				// this.type);
				// pb.start();
				this.process(sites, start, fb, error, error2, siteCheck, refer,
						lv);
			}
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// this.main.getNet().removeObserver(fb);
		// this.main.getMain().btn_fb_fanpage.setEnabled(true);
		fb.setNull("", "");
	}

	private void process(List<HashMap<String, String>> sites, int start,
			Facebook fb, int error, int error2, String siteCheck, String refer,
			Likeviet lv) {
		l1: for (HashMap<String, String> site : sites) {
			start++;
			try {
				Utils.filter(site.get("link"), "facebook.com", 0);
			} catch (Exception e) {
				// int row = this.main.addRecord(site.get("id"), this.type,
				// site.get("link"), site.get("coin"), "Ignore");
				// this.skip(row, site.get("id"), "Không thuộc Facebook");
				// continue l1;
			}
			boolean is = false;
			switch (this.type) {
			case "fanpage":
				is = fb.isLikeFanPage(site.get("link"));
				break;
			case "photo":
				is = fb.isLikePhoto(site.get("link"));
				break;
			case "follow":
				is = fb.isFollow(site.get("link"));
				break;
			case "share":
				is = false;
				break;
			case "likeWebsite":
				is = fb.isLikeFanPage(site.get("link"));
				break;
			default:
				break;
			}

			// int row = this.main.addRecord(site.get("id"), this.type,
			// site.get("link"), site.get("coin"), "");
			// fb.setRow(row);
			fb.setTrying(0);
			int rsUn = 1;
			if (is == true) {
				// this.main.updateRecord(row, 5, "Starting Un");

				switch (this.type) {
				case "fanpage":
					rsUn = fb.likeUnlikeFanPage(site.get("link"), false);
					break;
				case "photo":
					rsUn = fb.likePhoto(site.get("link"), false);
					break;
				case "follow":
					rsUn = fb.subscribeUnsubscribe(site.get("link"), false);
					break;
				case "share":
					// rsUn = fb.likeUnlikeFanPage(site.get("link"), false);
					break;
				case "likeWebsite":
					rsUn = fb.likeUnlikeFanPage(site.get("link"), false);
					break;
				default:
					break;
				}

				if (rsUn == -1) {
					// this.main.updateRecord(row, 6,
					// "Skip. Network or Link problem.");
					// this.skip(row, site.get("id"),
					// "Network or Link problem.");
					// this.main.updateRecord(row, 5,
					// "Network or Link problem.");

				} else if (rsUn == 0) {
					// this.main.updateRecord(row, 6,
					// "Skip. Lỗi (Facebook Update)");
					// this.skip(row, site.get("id"), "Lỗi (Facebook Update)");

					// this.main.updateRecord(row, 6, "Skip");
					this.errorLinks2.add(site.get("link"));
					error2++;
					if (error2 == Setting.limitError) {
						// this.main.getMain().popupSendmail(subjectFB,
						// this.getErrorLinks2());
					}
				}
				// this.main.updateRecord(row, 5, "Un Success");
			}
			if (rsUn == 1) {

				lv.setCookieDir(Setting.lv.domain);
				// verify1
				lv.verifyFirst(siteCheck, refer, site.get("link"),
						site.get("id"), true);

				// this.main.updateRecord(row, 5, "Not");
				int rs = 1;
				switch (this.type) {
				case "fanpage":
					rs = fb.likeUnlikeFanPage(site.get("link"), true);
					break;
				case "photo":
					rs = fb.likePhoto(site.get("link"), true);
					break;
				case "follow":
					rs = fb.subscribeUnsubscribe(site.get("link"), true);
					break;
				case "share":
					rs = fb.shareFacebook(site.get("link"));
					break;
				case "likeWebsite":
					lv.verifyFirst(siteCheck, refer, site.get("link"),
							site.get("id"), false);
					rs = fb.likeUnlikeFanPage(site.get("link"), true);
					break;
				default:
					break;
				}
				System.out.println("ket qua: " + rs);
				if (rs == -1) {
					// this.main.updateRecord(row, 6,
					// "Skip. Network or Link problem.");
					// this.skip(row, site.get("id"),
					// "Network or Link problem.");
					// this.main.updateRecord(row, 5,
					// "Network or Link problem.");
				} else if (rs == 1) {
					// verify2
					System.out.println("bat dau verify buoc 2");
					boolean vf2 = lv.verifySecond(siteCheck, refer,
							site.get("id"));
					if (!vf2) {
						// this.main.updateRecord(row, 6, "Skip. Lỗi");
						// this.skip(row, site.get("id"), "Lỗi");

						// this.main.updateRecord(row, 6, "Skip");
						this.errorLinks.add(site.get("link"));
						error++;
						if (error == Setting.limitError) {
							// this.main.getMain().popupSendmail(subject,
							// this.getErrorLinks());
						}
					} else {
						// this.main.updateRecord(row, 5, "Success");
						this.main.getCurrentCoin();
					}
				} else if (rs == 0) {
					// this.main.updateRecord(row, 6,
					// "Skip. Lỗi (Facebook Update)");
					// this.skip(row, site.get("id"), "Lỗi (Facebook Update)");

					// this.main.updateRecord(row, 6, "Skip");
					this.errorLinks2.add(site.get("link"));
					error2++;
					if (error2 == Setting.limitError) {
						// this.main.getMain().popupSendmail(subjectFB,
						// this.getErrorLinks2());
					}

				} else if (rs == 2) {
					switch (this.type) {
					case "fanpage":
						// this.blockLike(row);
						break;
					case "follow":
						break;
					case "likeWebsite":
						// this.blockLike(row);
						break;
					default:
						break;
					}
				}
			}
			// this.pb.setCurrent(start);

		}
		Setting.currentThreads--;
	}

	private String getErrorLinks() {
		String str = "";
		for (String string : this.errorLinks) {
			str += string + "\r\n";
		}
		this.content += str;
		return this.content;
	}

	private String getErrorLinks2() {
		String str = "";
		for (String string : this.errorLinks2) {
			str += string + "\r\n";
		}
		this.contentFB += str;
		return this.contentFB;
	}

	private void skip(int row, String id, String strError) {
		// String value = Utils.readXML(Setting.settingFileName, "tableLog",
		// "skip", "value");
		// if (value.equals("1")) {
		// skip s = new skip(this.main, this.type, row, id);
		// s.start();
		// this.main.updateRecord(row, 5, strError);
		// }

	}
}
