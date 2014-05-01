package Libs;

import Utils.Utils;
import Configs.Setting;
import GUI.main;

public class LoadChilkat {

	public static void load() {
		try {
			System.load(Setting.pathChilkatDll);
		} catch (UnsatisfiedLinkError e) {
			System.err.println("Native code library failed to load.\n" + e);
			System.exit(1);
		}
	}

}
