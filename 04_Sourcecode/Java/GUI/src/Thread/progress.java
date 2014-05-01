package Thread;

import javax.swing.JProgressBar;


public class progress extends Thread {
	private int total = 1;
	private int current = 0;
	private JProgressBar pb;
	private String type = "";

	public progress(int total, JProgressBar pb, String type) {
		this.total = total;
		this.pb = pb;
		this.type = type;
		this.setFirst();
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public void setFirst() {
		this.pb.setValue((this.current * 100) / this.total);
		this.pb.setString(((this.current * 100) / this.total) + "%  ("
				+ this.current + "/" + this.total + ")");
	}

	@Override
	public void run() {
		int max = 0;
		// TODO Auto-generated method stub
		while (true) {
			if (max < current) {
				max = current;
				this.pb.setValue((this.current * 100) / this.total);
				this.pb.setString(((this.current * 100) / this.total) + "%  ("
						+ this.current + "/" + this.total + ")");

			}
			if (this.current == this.total) {
				break;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
