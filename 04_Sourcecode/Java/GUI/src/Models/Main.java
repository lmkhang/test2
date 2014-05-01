package Models;

public class Main extends Parent {
	public static Main main = null;

	public Main() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Likeviet clone() throws CloneNotSupportedException {

		return (Likeviet) super.clone();

	}

	public static Main getInstance() {
		// System.out.println("Process: create new fb");
		// if (fb == null) {
		main = new Main();
		// }
		return main;
	}
}
