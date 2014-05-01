package Models;

import views.MainView;

public class MainModel implements MainInterfaceModel {

	private MainView main = null;

	public MainModel() {

	}

	@Override
	public void addViewFB(MainView main) {
		// TODO Auto-generated method stub
		this.main = main;
	}

	@Override
	public void updateNetwork(boolean isOnline) {
		// TODO Auto-generated method stub

	}
}
