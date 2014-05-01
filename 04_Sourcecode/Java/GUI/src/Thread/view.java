package Thread;

import java.awt.EventQueue;

import Controllers.MainController;
import Controllers.MainInterfaceController;
import Libs.LoadChilkat;
import Models.MainInterfaceModel;
import Models.MainModel;

public class view extends Thread {

	public view() {

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoadChilkat.load();
					MainInterfaceModel model = new MainModel();
					MainInterfaceController controller = new MainController(
							model);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
