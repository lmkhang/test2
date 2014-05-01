/*
 * Created by JFormDesigner on Fri Feb 07 23:48:00 ICT 2014
 */

package GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.StringTokenizer;

import javax.swing.*;

import Configs.Setting;
import Utils.Utils;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

/**
 * @author Kunkka Lee
 */
public class dl_savelog extends JDialog {
	private gui main;
	public JFileChooser fileChooser;
	private String fileView = Setting.getPathApp();

	public dl_savelog(gui main) {
		initComponents();
		this.main = main;
		this.setSize(500, 100);
		this.showGUI();
	}

	public void showGUI() {
		// TODO add your code here
		this.loadSettingSaveLog();
		this.setVisible(true);
		this.setResizable(false);

	}

	public void loadSettingSaveLog() {
		try {
			this.txt_rowB.setValue(Integer.parseInt(Utils.readXML(
					Setting.settingFileName, "tableLog", "row_B", "value")));
			this.txt_rowE.setValue(Integer.parseInt(Utils.readXML(
					Setting.settingFileName, "tableLog", "row_E", "value")));
			this.txt_colB.setValue(Integer.parseInt(Utils.readXML(
					Setting.settingFileName, "tableLog", "col_B", "value")));
			this.txt_colE.setValue(Integer.parseInt(Utils.readXML(
					Setting.settingFileName, "tableLog", "col_E", "value")));
			String str = Utils.readXML(Setting.settingFileName, "tableLog",
					"sheetName", "value");
			this.txt_sheetname.setText(str);
			str = Utils.readXML(Setting.settingFileName, "tableLog",
					"fileToSave", "value");
			this.fileView = str;
		} catch (Exception ex) {
		}
	}

	private void btn_Savelog(MouseEvent e) {
		// TODO add your code here
//		System.out.println(this.fileView);
		this.fileChooser = new JFileChooser(this.fileView);
		this.fileChooser.setDialogTitle("Specify a file to save");

		int userSelection = this.fileChooser
				.showSaveDialog(this.main.getMain());

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave = this.fileChooser.getSelectedFile();
			int row_B = Integer.parseInt((String) this.txt_rowB.getValue()
					.toString());
			int row_E = Integer.parseInt((String) this.txt_rowE.getValue()
					.toString());
			int col_B = Integer.parseInt((String) this.txt_colB.getValue()
					.toString());
			int col_E = Integer.parseInt((String) this.txt_colE.getValue()
					.toString());
			// String str = row_B + "|" + row_E + "|" + col_B + "|" + col_E +
			// "|"
			// + this.txt_sheetname.getText().toString() + "|"
			// + fileToSave.getParent() + "\\";
			this.fileView = fileToSave.getParent();

			Utils.modifyXML(Setting.settingFileName, Setting.parent,
					"tableLog", "row_B", "value", String.valueOf(row_B));
			Utils.modifyXML(Setting.settingFileName, Setting.parent,
					"tableLog", "row_E", "value", String.valueOf(row_E));
			Utils.modifyXML(Setting.settingFileName, Setting.parent,
					"tableLog", "col_B", "value", String.valueOf(col_B));
			Utils.modifyXML(Setting.settingFileName, Setting.parent,
					"tableLog", "col_E", "value", String.valueOf(col_E));
			Utils.modifyXML(Setting.settingFileName, Setting.parent,
					"tableLog", "sheetName", "value", this.txt_sheetname
							.getText().toString());
			Utils.modifyXML(Setting.settingFileName, Setting.parent,
					"tableLog", "fileToSave", "value", fileToSave.getParent()
							+ "\\");

			Utils.writeExcel(this.txt_sheetname.getText().toString(),
					fileToSave.getAbsoluteFile().toString(),
					this.main.getDialog().log, row_B, col_B, row_E, col_E);
		}
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Kunkka Lee
		panel3 = new JPanel();
		label6 = new JLabel();
		txt_sheetname = new JTextField();
		txt_rowB = new JSpinner();
		txt_rowE = new JSpinner();
		btn_Savelog = new JButton();
		txt_colB = new JSpinner();
		txt_colE = new JSpinner();

		// ======== this ========
		setTitle("Save log");
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		((GridBagLayout) contentPane.getLayout()).columnWidths = new int[] { 0,
				0 };
		((GridBagLayout) contentPane.getLayout()).rowHeights = new int[] { 48,
				0 };
		((GridBagLayout) contentPane.getLayout()).columnWeights = new double[] {
				1.0, 1.0E-4 };
		((GridBagLayout) contentPane.getLayout()).rowWeights = new double[] {
				0.0, 1.0E-4 };

		// ======== panel3 ========
		{

			// JFormDesigner evaluation mark
//			panel3.setBorder(new javax.swing.border.CompoundBorder(
//					new javax.swing.border.TitledBorder(
//							new javax.swing.border.EmptyBorder(0, 0, 0, 0),
//							"JFormDesigner Evaluation",
//							javax.swing.border.TitledBorder.CENTER,
//							javax.swing.border.TitledBorder.BOTTOM,
//							new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
//							java.awt.Color.red), panel3.getBorder()));
			panel3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
				public void propertyChange(java.beans.PropertyChangeEvent e) {
					if ("border".equals(e.getPropertyName()))
						throw new RuntimeException();
				}
			});

			panel3.setLayout(new FormLayout(
					"48dlu:grow, $lcgap, 92dlu:grow, 2*($lcgap, default:grow)",
					"default:grow, $lgap, default"));

			// ---- label6 ----
			label6.setText("Sheet Name");
			panel3.add(label6, CC.xy(1, 1));
			panel3.add(txt_sheetname, CC.xy(3, 1));

			// ---- txt_rowB ----
			txt_rowB.setModel(new SpinnerNumberModel(1, 0, null, 1));
			txt_rowB.setToolTipText("Row Begin");
			panel3.add(txt_rowB, CC.xy(5, 1));

			// ---- txt_rowE ----
			txt_rowE.setModel(new SpinnerNumberModel(1, 0, null, 1));
			txt_rowE.setToolTipText("Row End");
			panel3.add(txt_rowE, CC.xy(7, 1));

			// ---- btn_Savelog ----
			btn_Savelog.setText("Save");
			btn_Savelog.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					btn_Savelog(e);
				}
			});
			panel3.add(btn_Savelog, CC.xy(1, 3, CC.LEFT, CC.DEFAULT));

			// ---- txt_colB ----
			txt_colB.setModel(new SpinnerNumberModel(1, 0, null, 1));
			txt_colB.setToolTipText("Column Begin");
			panel3.add(txt_colB, CC.xy(5, 3));

			// ---- txt_colE ----
			txt_colE.setModel(new SpinnerNumberModel(999, 0, null, 1));
			txt_colE.setToolTipText("Column End");
			panel3.add(txt_colE, CC.xy(7, 3));
		}
		contentPane.add(panel3, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 0, 0), 0, 0));
		pack();
		setLocationRelativeTo(getOwner());
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Kunkka Lee
	private JPanel panel3;
	private JLabel label6;
	private JTextField txt_sheetname;
	private JSpinner txt_rowB;
	private JSpinner txt_rowE;
	private JButton btn_Savelog;
	private JSpinner txt_colB;
	private JSpinner txt_colE;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
