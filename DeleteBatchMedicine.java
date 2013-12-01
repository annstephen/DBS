package src;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class DeleteBatchMedicine extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteBatchMedicine frame = new DeleteBatchMedicine();
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
	public DeleteBatchMedicine() {
		
		//Grid Initialization to arrange components
		final JFrame frame = this;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0, 0.0, Double.MIN_VALUE, 0.0};
		gridBagLayout.rowWeights = new double[]{0, 0.0, 0.0, 0.0};
		getContentPane().setLayout(gridBagLayout);
		
		//Text Field for medicine name
		JLabel lblName = new JLabel("Medicine Name");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 0;
		getContentPane().add(lblName, gbc_lblName);
		
		final JTextField textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);
		textField.setText("%");
		
		//Text Field for date
		JLabel lblDate = new JLabel("Date");
		GridBagConstraints gbc_lblDate = new GridBagConstraints();
		gbc_lblDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblDate.anchor = GridBagConstraints.EAST;
		gbc_lblDate.gridx = 0;
		gbc_lblDate.gridy = 1;
		getContentPane().add(lblDate, gbc_lblDate);
		
		final JTextField dateField = new JTextField();
		GridBagConstraints gbc_dateField = new GridBagConstraints();
		gbc_dateField.gridwidth = 2;
		gbc_dateField.anchor = GridBagConstraints.WEST;
		gbc_dateField.insets = new Insets(0, 0, 5, 5);
		gbc_dateField.gridx = 1;
		gbc_dateField.gridy = 1;
		getContentPane().add(dateField, gbc_dateField);
		dateField.setColumns(10);
		dateField.setText("%");
		
		final JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;
		getContentPane().add(scrollPane, gbc_scrollPane);
		table_1 = new JTable(getValuesFromProduct(textField.getText()));
		scrollPane.setViewportView(table_1);
		
		java.awt.Button button = new java.awt.Button("Search");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().equals(null) || textField.getText().length() == 0){
					JOptionPane.showMessageDialog(null, "You need to have a non-empty value for the name");
				}
				else{
					table_1 = new JTable(getValuesFromProduct(textField.getText()));
					scrollPane.setViewportView(table_1);
				}
			}
		});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 0, 5);
		gbc_button.gridx = 3;
		gbc_button.gridy = 4;
		getContentPane().add(button, gbc_button);
		
		//Action Controller plus the checks for all fields
		//format check for date
		java.awt.Button btnUpdate = new java.awt.Button("Delete");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if(textField.getText().equals(null) || textField.getText().length() == 0){
						JOptionPane.showMessageDialog(null, "You need to have a non-empty value for the name");
					}
					else if(!dateField.getText().matches("\\d{4}-[01]\\d-[0-3]\\d")){
						JOptionPane.showMessageDialog(null, "Add Correct format for date");
					}
					else{
						DeleteValuesFromBatch(textField.getText(),dateField.getText());
						table_1 = new JTable(getValuesFromProduct(textField.getText()));
					}
				System.out.println(e);
				scrollPane.setViewportView(table_1);
			}
		});
		GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
		gbc_btnUpdate.insets = new Insets(0, 0, 0, 5);
		gbc_btnUpdate.gridx = 4;
		gbc_btnUpdate.gridy = 4;
		getContentPane().add(btnUpdate, gbc_btnUpdate);
		//JFrame frame = this.
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 WindowEvent wev = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING); 
				 Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev); 
				 
				 frame.setVisible(false); 
				 frame.dispose(); 
				 System.exit(0); 
			}
		});
		
		GridBagConstraints gbc_btnExit = new GridBagConstraints();
		gbc_btnExit.gridx = 5;
		gbc_btnExit.gridy = 4;
		getContentPane().add(btnExit, gbc_btnExit);
	}
	

	private static JTable table_1;
	private JTextField textField;
	
	public static DefaultTableModel getValuesFromProduct(String medName){
		String query = "select m.medicine_name, m.dosage,  b.batch_id, b.expiry_date from Hospital.batch b, Hospital.production p, Hospital.medicine m"+ 
				" where p.product_id = m.product_id and p.batch_id = b.batch_id and m.medicine_name like '"+medName+"'";
		DefaultTableModel dtm = ConnectDB.getSelectResult(query);
		return dtm;
	}
	
	public static void DeleteValuesFromBatch(String medName, String date){
		String query = "delete from hospital.batch where expiry_date < '"+date+"' and batch_id in ( select distinct(b.batch_id) "+
	                   " from Hospital.production b, Hospital.medicine m where b.product_id = m.product_id and m.medicine_name like '"+medName+"')";
		//System.out.println(query);
		ConnectDB.updateTable(query);
	}

}
