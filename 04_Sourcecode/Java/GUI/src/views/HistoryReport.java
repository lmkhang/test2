package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class HistoryReport extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HistoryReport frame = new HistoryReport();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HistoryReport() {
		setTitle("History Report");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 696, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		String columnNames[] = { "No", "ID","Coint" ,"Type","Status","Link","Try"};

		// Create some data
		String dataValues[][] =
		{
			{ "1", "ID","Coint" ,"Type","Status","Link","Try"},
			{ "2", "ID","Coint" ,"Type","Status","Link","Try" },
			{ "3", "ID","Coint" ,"Type","Status","Link","Try" },
			{ "4", "ID","Coint" ,"Type","Status","Link","Try" },
			{ "5", "ID","Coint" ,"Type","Status","Link","Try" },
			{ "6", "ID","Coint" ,"Type","Status","Link","Try" },
			{ "7", "ID","Coint" ,"Type","Status","Link","Try" },
			{ "8", "ID","Coint" ,"Type","Status","Link","Try" },
			{ "9", "ID","Coint" ,"Type","Status","Link","Try" },
			{ "10", "ID","Coint" ,"Type","Status","Link","Try" }
		};

		// Create a new table instance
		table = new JTable( dataValues, columnNames );
		scrollPane.setViewportView(table);
	}

}
