package GUI;

import java.awt.EventQueue;

import chrriis.dj.nativeswing.NativeSwing;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;

import Libs.LoadChilkat;
import Thread.view;

public class start {
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoadChilkat.load();
					NativeSwing.initialize();
					NativeInterface.open();
					new Login();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
