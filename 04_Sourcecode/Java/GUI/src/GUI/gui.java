package GUI;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;

import Configs.Setting;
import Libs.Network;

public class gui {
	private main main;
	private Network net;
	private showlog dialog;
	private sendmail sm;
	private dl_savelog saveLog = null;

	public gui() {
		Setting.prepareFolder();
		this.main = new main(this);

		// log
		this.dialog = new showlog(this);
		this.sm = new sendmail(this.main);
		// setting table log
		int y = (int) this.main.getY();
		int x = (int) (this.main.getLocation().getX() + this.main.getWidth());

		this.dialog.showlog.setResizable(false);
		this.dialog.showlog.setLocationByPlatform(true);
		this.dialog.showlog.setSize(this.main.getWidth() + 100,
				this.main.getHeight());
		this.dialog.showlog.setResizable(true);
		this.dialog.showlog.setLocation(x, y);

		this.dialog.log.setAutoscrolls(true);
		this.dialog.log.setRowHeight(25);
		this.dialog.log.setGridColor(Color.blue);
		this.dialog.log.setShowGrid(true);

		this.initLogTable();
	}

	public void showSaveLog() {
		if (saveLog == null) {
			saveLog = new dl_savelog(this);
		} else {
			saveLog.showGUI();
		}
	}

	private void initLogTable() {
		// TODO Auto-generated method stub
		Vector<String> columns = new Vector<String>();
		columns.add("Stt");
		columns.add("Id");
		columns.add("Type");
		columns.add("Link");
		columns.add("Coin");
		columns.add("Status");
		columns.add("Try");
		Vector<Vector<String>> data = new Vector<Vector<String>>();

		// add columns
		DefaultTableModel model = new DefaultTableModel(data, columns);
		this.dialog.log.setModel(model);
		this.dialog.log.getColumn("Stt").setPreferredWidth(30);
		this.dialog.log.getColumn("Id").setPreferredWidth(50);
		this.dialog.log.getColumn("Type").setPreferredWidth(100);
		this.dialog.log.getColumn("Link").setPreferredWidth(350);
		this.dialog.log.getColumn("Coin").setPreferredWidth(50);
		this.dialog.log.getColumn("Status").setPreferredWidth(100);
		this.dialog.log.getColumn("Try").setPreferredWidth(50);

		this.dialog.log.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int row = dialog.log.rowAtPoint(new Point(e.getX(), e.getY()));
				int col = dialog.log.columnAtPoint(new Point(e.getX(), e.getY()));
				if (col == 3) {
					try {
						String link = dialog.log.getModel()
								.getValueAt(row, col).toString();
						URI uri = new URI(link);

						if (Desktop.isDesktopSupported()) {
							try {
								Desktop.getDesktop().browse(uri);
							} catch (IOException ioe) { /* TODO: error handling */
							}
						} else { /* TODO: error handling */
						}
					} catch (Exception ex) {

					}
				}
			}
		});
	}

	public void setImage(JToggleButton btn, String image) {
		// btn_loginlv.setIcon(new ImageIcon(main.class
		// .getResource("/outsrc/images/" + image)));
		btn.setSelectedIcon(new ImageIcon(this.main.getClass().getResource(
				"/outsrc/images/" + image)));
		btn.setIcon(new ImageIcon(this.main.getClass().getResource(
				"/outsrc/images/" + image)));
	}

	public void show(boolean isShow) {
		this.main.setVisible(isShow);
	}

	public void showLog(boolean isShow) {
		this.dialog.showlog.setVisible(isShow);
	}

	public main getMain() {
		return this.main;
	}

	public void updateNetwork(boolean isOnline) {
		if (isOnline == false) {
			this.main.lb_status.setText("Disconnected");
		} else {
			this.main.lb_status.setText("Connected");
		}
	}

	public Network getNet() {
		return net;
	}

	public showlog getDialog() {
		return dialog;
	}

	public sendmail getSm() {
		return sm;
	}

	public void network(boolean isRun) {
		// TODO Auto-generated method stub
		if (isRun) {
			if (this.net == null) {
				this.net = new Network(this);
			}
			this.net.setRun(isRun);
			this.net.start();
		} else {
			if (this.net != null) {
				this.net.setRun(isRun);
				this.net.stop();
			}
		}

	}

	public int addRecord(String id, String type, String link, String coin,
			String status) {
		DefaultTableModel model = (DefaultTableModel) this.dialog.log
				.getModel();
		Vector<String> data = new Vector<String>();

		int max = model.getRowCount();
		max++;
		data.add(String.valueOf(max));
		data.add(id);
		data.add(type);
		data.add(link);
		data.add(coin);
		data.add(status);

		model.addRow(data);

		return max;
	}

	public void updateRecord(int row, int column, String str) {
		DefaultTableModel model = (DefaultTableModel) this.dialog.log
				.getModel();
		model.setValueAt(str, row - 1, column);
	}

}
