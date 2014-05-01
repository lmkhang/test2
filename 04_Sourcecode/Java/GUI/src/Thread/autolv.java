package Thread;

import Configs.Setting;
import GUI.gui;
import Models.Facebook;
import Models.Likeviet;

public class autolv extends Thread {
	private gui main;
	private String type;

	public autolv(gui main, String type) {
		// TODO Auto-generated constructor stub
		this.main = main;
		this.type = type;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (this.type.equals("surf")) {
			this.surf();
		}
	}

	private void surf() {
		// TODO Auto-generated method stub
		this.main.getMain().btn_lv_surf.setEnabled(false);
		Likeviet lv = null;
		try {
			lv = Setting.lv.clone();
			lv.setCookieDir(lv.domain);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int row = 0;
		while (true) {
			String[] surf = lv.getIdSurf();
			if (surf[0].equals("-1")) {
				row = this.main.addRecord(surf[0], this.type,
						"Surf on Like Viet", surf[1], "Network die");
				break;
			} else if (surf[0].equals("0")) {
				row = this.main.addRecord(surf[0], this.type,
						"Surf on Like Viet", surf[1], "No surf");
				break;
			} else {
				row = this.main.addRecord(surf[0], this.type,
						"Surf on Like Viet", surf[1], "Surfing");
				int rs = lv.surfNow(surf);
				if (rs == 1) {
					this.main.updateRecord(row, 5,
							"Success");
					this.setCurrentCoin();
				}else{
					this.main.updateRecord(row, 5,
							"Failed");
				}
			}
		}

		this.main.getMain().btn_lv_surf.setEnabled(true);
		lv.setNull("", "");
	}
	
	private void setCurrentCoin() {
		this.main.getMain().lb_coin.setText(Setting.lv.getCurrentCoin());
	}
}
