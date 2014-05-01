package Libs;

import java.util.ArrayList;
import java.util.List;

import GUI.gui;
import GUI.main;
import Models.MainInterfaceModel;
import Utils.Utils;

public class Network extends Thread {
	private int timeout = 3000;

	private List<MainInterfaceModel> listView = new ArrayList<MainInterfaceModel>();
	private gui main;
	private boolean isRun = false;

	public Network(gui main) {
		this.main = main;
	}

	public List<MainInterfaceModel> getListView() {
		return listView;
	}

	public void setEmptyList(List<MainInterfaceModel> listView) {
		this.listView = listView;
	}

	public void setEmptyList() {
		this.listView = new ArrayList<MainInterfaceModel>();
	}

	public void addObserver(MainInterfaceModel obj) {
		this.listView.add(obj);
	}

	public void removeObserver(MainInterfaceModel obj) {
		this.listView.remove(obj);
	}

	public boolean isRun() {
		return isRun;
	}

	public void setRun(boolean isRun) {
		this.isRun = isRun;
	}

	@Override
	public void run() {
		while (this.isRun) {
			try {
				Thread.sleep(this.timeout);
				boolean rs = Utils.doPing("www.google.com");
				this.main.updateNetwork(rs);
				for (MainInterfaceModel model : this.listView) {
					model.updateNetwork(rs);
				}
				// if (this.listView.size() == 2) {
				// }
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
