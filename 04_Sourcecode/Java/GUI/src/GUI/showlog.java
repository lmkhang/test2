/*
 * Created by JFormDesigner on Thu Jan 23 22:47:36 ICT 2014
 */

package GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;

import javax.swing.*;

import Thread.confirm;
import Thread.skip;

/**
 * @author Kunkka Lee
 */
public class showlog {
	private gui main;

	public showlog(gui main) {
		initComponents();
		this.main = main;
		this.addMouseListenerTable();
	}

	private void addMouseListenerTable() {
		this.log.addMouseListener(new MouseListener() {

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
				int row = log.rowAtPoint(new Point(e.getX(), e.getY()));
				int col = log.columnAtPoint(new Point(e.getX(), e.getY()));
				if (col == 6) {
					try {
						String txt = log.getModel().getValueAt(row, col)
								.toString();
						if (txt.contains("Skip")) {
							String id = log.getModel().getValueAt(row, 1)
									.toString();
							String type = log.getModel().getValueAt(row, 2)
									.toString();
							skip s = new skip(main, type, row + 1, id);
							s.start();
						} else if (txt.contains("Confirm?")) {
							String type = log.getModel().getValueAt(row, 2)
									.toString();
							String link = log.getModel().getValueAt(row, 3)
									.toString();
							confirm c = new confirm(main, type, row + 1, link);
							c.start();
						}
					} catch (Exception ex) {
					}
				}
			}
		});
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		showlog = new JFrame();
		lb_announce = new JPanel();
		label1 = new JLabel();
		label2 = new JLabel();
		scrollPane1 = new JScrollPane();
		log = new JTable();
		panel1 = new JPanel();
		pb_1 = new JProgressBar();
		pb_2 = new JProgressBar();
		pb_3 = new JProgressBar();
		pb_4 = new JProgressBar();
		pb_5 = new JProgressBar();
		pb_6 = new JProgressBar();
		pb_7 = new JProgressBar();
		pb_8 = new JProgressBar();

		// ======== showlog ========
		{
			showlog.setIconImage(((ImageIcon) UIManager
					.getIcon("FileView.hardDriveIcon")).getImage());
			showlog.setTitle("Logs");
			Container showlogContentPane = showlog.getContentPane();
			showlogContentPane.setLayout(new GridBagLayout());
			((GridBagLayout) showlogContentPane.getLayout()).columnWidths = new int[] {
					0, 0 };
			((GridBagLayout) showlogContentPane.getLayout()).rowHeights = new int[] {
					0, 220, 0, 0 };
			((GridBagLayout) showlogContentPane.getLayout()).columnWeights = new double[] {
					1.0, 1.0E-4 };
			((GridBagLayout) showlogContentPane.getLayout()).rowWeights = new double[] {
					0.0, 1.0, 0.0, 1.0E-4 };

			// ======== lb_announce ========
			{
				lb_announce.setLayout(new GridBagLayout());
				((GridBagLayout) lb_announce.getLayout()).columnWidths = new int[] {
						0, 0, 0 };
				((GridBagLayout) lb_announce.getLayout()).rowHeights = new int[] {
						0, 0 };
				((GridBagLayout) lb_announce.getLayout()).columnWeights = new double[] {
						0.0, 1.0, 1.0E-4 };
				((GridBagLayout) lb_announce.getLayout()).rowWeights = new double[] {
						1.0, 1.0E-4 };

				// ---- label1 ----
				label1.setText("Announce");
				lb_announce.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0,
						0.0, GridBagConstraints.CENTER,
						GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 5),
						0, 0));
				lb_announce.add(label2, new GridBagConstraints(1, 0, 1, 1, 0.0,
						0.0, GridBagConstraints.CENTER,
						GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0),
						0, 0));
			}
			showlogContentPane.add(lb_announce, new GridBagConstraints(0, 0, 1,
					1, 0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

			// ======== scrollPane1 ========
			{

				// ---- log ----
				log.setFillsViewportHeight(true);
				log.setSurrendersFocusOnKeystroke(true);
				scrollPane1.setViewportView(log);
			}
			showlogContentPane.add(scrollPane1, new GridBagConstraints(0, 1, 1,
					1, 0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

			// ======== panel1 ========
			{
				panel1.setLayout(new GridBagLayout());
				((GridBagLayout) panel1.getLayout()).columnWidths = new int[] {
						0, 0, 0, 0, 0, 0 };
				((GridBagLayout) panel1.getLayout()).rowHeights = new int[] {
						0, 0, 0 };
				((GridBagLayout) panel1.getLayout()).columnWeights = new double[] {
						1.0, 0.0, 1.0, 1.0, 1.0, 1.0E-4 };
				((GridBagLayout) panel1.getLayout()).rowWeights = new double[] {
						0.0, 0.0, 1.0E-4 };

				// ---- pb_1 ----
				pb_1.setForeground(new Color(255, 153, 51));
				pb_1.setStringPainted(true);
				pb_1.setBorder(null);
				panel1.add(pb_1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 5, 5), 0, 0));

				// ---- pb_2 ----
				pb_2.setForeground(new Color(51, 102, 255));
				pb_2.setStringPainted(true);
				pb_2.setBorder(null);
				panel1.add(pb_2, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 5, 5), 0, 0));

				// ---- pb_3 ----
				pb_3.setForeground(new Color(255, 102, 102));
				pb_3.setStringPainted(true);
				pb_3.setBorder(null);
				panel1.add(pb_3, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 5, 5), 0, 0));

				// ---- pb_4 ----
				pb_4.setForeground(new Color(153, 0, 153));
				pb_4.setStringPainted(true);
				pb_4.setBorder(null);
				panel1.add(pb_4, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 5, 5), 0, 0));

				// ---- pb_5 ----
				pb_5.setForeground(new Color(255, 255, 51));
				pb_5.setStringPainted(true);
				pb_5.setBorder(null);
				panel1.add(pb_5, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 0, 5), 0, 0));

				// ---- pb_6 ----
				pb_6.setForeground(new Color(102, 102, 102));
				pb_6.setStringPainted(true);
				pb_6.setBorder(null);
				panel1.add(pb_6, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 0, 5), 0, 0));

				// ---- pb_7 ----
				pb_7.setForeground(new Color(51, 0, 102));
				pb_7.setStringPainted(true);
				pb_7.setBorder(null);
				panel1.add(pb_7, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 0, 5), 0, 0));

				// ---- pb_8 ----
				pb_8.setForeground(new Color(51, 255, 51));
				pb_8.setStringPainted(true);
				pb_8.setBorder(null);
				panel1.add(pb_8, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 0, 5), 0, 0));
			}
			showlogContentPane.add(panel1, new GridBagConstraints(0, 2, 1, 1,
					0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			showlog.pack();
			showlog.setLocationRelativeTo(showlog.getOwner());
		}
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	public JFrame showlog;
	public JPanel lb_announce;
	public JLabel label1;
	public JLabel label2;
	public JScrollPane scrollPane1;
	public JTable log;
	public JPanel panel1;
	public JProgressBar pb_1;
	public JProgressBar pb_2;
	public JProgressBar pb_3;
	public JProgressBar pb_4;
	public JProgressBar pb_5;
	public JProgressBar pb_6;
	public JProgressBar pb_7;
	public JProgressBar pb_8;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
