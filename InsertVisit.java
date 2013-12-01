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

public class InsertVisit extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertVisit frame = new InsertVisit();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 */
	public InsertVisit() {
		//Grid Initialization to arrange components
		final JFrame frame = this;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0, 0.0, Double.MIN_VALUE, 0.0};
		gridBagLayout.rowWeights = new double[]{0, 0.0, 0.0, 0.0};
		getContentPane().setLayout(gridBagLayout);
		
		//Text Field for name
		JLabel lblName = new JLabel("Name");
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
		
		//Text Field for diagnosis
		JLabel lblDiag = new JLabel("Diagnosis");
		GridBagConstraints gbc_lblDiag = new GridBagConstraints();
		gbc_lblDiag.insets = new Insets(0, 0, 5, 5);
		gbc_lblDiag.anchor = GridBagConstraints.EAST;
		gbc_lblDiag.gridx = 4;
		gbc_lblDiag.gridy = 0;
		getContentPane().add(lblDiag, gbc_lblDiag);
		
		final JTextField diagField = new JTextField();
		GridBagConstraints gbc_diagField = new GridBagConstraints();
		gbc_diagField.anchor = GridBagConstraints.WEST;
		gbc_diagField.insets = new Insets(0, 0, 5, 0);
		gbc_diagField.gridx = 5;
		gbc_diagField.gridy = 0;
		getContentPane().add(diagField, gbc_diagField);
		diagField.setColumns(10);
		diagField.setText("%");
		
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
		table_1 = new JTable(getValuesFromVisit(textField.getText()));
		scrollPane.setViewportView(table_1);
		
		//Action control and value checks
		//Date is checked for correct format as well
		java.awt.Button btnUpdate = new java.awt.Button("Insert");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().equals(null) || textField.getText().length() == 0){
					JOptionPane.showMessageDialog(null, "You need to have a non-empty value for the name");
				}
				else if(!dateField.getText().matches("\\d{4}-[01]\\d-[0-3]\\d")){
					JOptionPane.showMessageDialog(null, "Add Correct format for date");
				}
				else if(diagField.getText().equals(null) || diagField.getText().length() == 0){
					JOptionPane.showMessageDialog(null, "You need to have a non-empty value for the diagnosis");
				}
				else{
					insertValueIntoVisit(textField.getText(),dateField.getText(),diagField.getText());
					table_1 = new JTable(getValuesFromVisit(textField.getText()));	
					scrollPane.setViewportView(table_1);
				}
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
	
	public static DefaultTableModel getValuesFromVisit(String empName){
		String query = "select p.name, p.contact_no, v.date_of_visit, v.diagnosis from hospital.patient p, hospital.visitation v where p.patient_registration_no = v.patient_registration_no ";
		DefaultTableModel dtm = ConnectDB.getSelectResult(query);
		return dtm;
	}
	
	public static void insertValueIntoVisit(String empName, String date, String diagnosis){
		String getPRegNo = "Select distinct(patient_registration_no) from Hospital.patient where name like '"+empName+"'";
		//There can only be an entry to the visitation table if the patient already exists in the patient table
		try{
			String pRegNo = ConnectDB.getSelectResultInsert(getPRegNo);
			String query = "Insert into Hospital.Visitation Values("+pRegNo+",'"+date+"','"+diagnosis+"')";
			//System.out.println(query);
			ConnectDB.updateTable(query);	
		}
		catch(NullPointerException e){
			JOptionPane.showMessageDialog(null, "Patient has not been registered with the hospital");
		}
	}

}
