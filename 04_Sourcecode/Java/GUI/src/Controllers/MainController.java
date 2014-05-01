package Controllers;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import GUI.gui;
import Libs.Network;
import Models.MainInterfaceModel;

public class MainController implements MainInterfaceController {

	private MainInterfaceModel model = null;
	private gui main = null;

	public MainController(MainInterfaceModel model) {
		// TODO Auto-generated constructor stub
		this.model = model;
//		String plaf = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
//		try {
//			UIManager.setLookAndFeel(plaf);
//		} catch (Exception e) {
//			e.printStackTrace(System.out);
//		}

		this.main = new gui();
		this.main.show(true);
		this.main.network(true);
		this.model.addViewFB(this.main);
	}

}
