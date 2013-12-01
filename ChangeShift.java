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

public class ChangeShift extends JFrame {

	//This Class updates the Hospital.shift table based on the name of the employee
	protected static final Exception ValueException = null;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangeShift frame = new ChangeShift();
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
	public ChangeShift() {
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
		gbc_lblName.gridy = 1;
		getContentPane().add(lblName, gbc_lblName);
		
		final JTextField textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);
		textField.setText("%");
		
		//text field to take new value for shift
		JLabel lblShift = new JLabel("Shift");
		GridBagConstraints gbc_lblShift = new GridBagConstraints();
		gbc_lblShift.insets = new Insets(0, 0, 5, 5);
		gbc_lblShift.anchor = GridBagConstraints.EAST;
		gbc_lblShift.gridx = 2;
		gbc_lblShift.gridy = 1;
		getContentPane().add(lblShift, gbc_lblShift);
		
		final JTextField shiftField = new JTextField();
		GridBagConstraints gbc_shiftField = new GridBagConstraints();
		gbc_shiftField.anchor = GridBagConstraints.WEST;
		gbc_shiftField.insets = new Insets(0, 0, 5, 5);
		gbc_shiftField.gridx = 3;
		gbc_shiftField.gridy = 1;
		getContentPane().add(shiftField, gbc_shiftField);
		shiftField.setColumns(10);
		shiftField.setText("%");
		
		final JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;
		getContentPane().add(scrollPane, gbc_scrollPane);
		//Checking for null values of name while searching
		if(textField.getText().equals(null) || textField.getText().length() == 0){
			JOptionPane.showMessageDialog(null, "You need to have a non-empty value for the name");
		}
		else{
			table_1 = new JTable(getValuesFromShiftWithName(textField.getText()));
			scrollPane.setViewportView(table_1);
		}
		
		java.awt.Button button = new java.awt.Button("Search");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_1 = new JTable(getValuesFromShiftWithName(textField.getText()));
				scrollPane.setViewportView(table_1);
			}
		});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 0, 5);
		gbc_button.gridx = 3;
		gbc_button.gridy = 4;
		getContentPane().add(button, gbc_button);
		
		//Searching name and updating the shifts as given in text field
		//The name is checked for empty value and the shift values are check against the acceptable values
		java.awt.Button btnUpdate = new java.awt.Button("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().equals(null) || textField.getText().length() == 0){
					JOptionPane.showMessageDialog(null, "You need to have a non-empty value for the name");
				}
				else if(shiftField.getText().equals("evening") || shiftField.getText().equals("night") || shiftField.getText().equals("afternoon") || shiftField.getText().equals("morning")){
					updateShiftWhereName(textField.getText(),shiftField.getText());
					table_1 = new JTable(SelectValuesFromTreatmentBean.getValuesFromTreatmentWithName(textField.getText()));
				}
				else{
					JOptionPane.showMessageDialog(null, "Please Enter Valid value for shift (evening/night/afternoon/morning)");
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
	
	//Display function
	public static DefaultTableModel getValuesFromShiftWithName(String empName){
		String query = "select name, shift_name from hospital.employees natural join hospital.takes where name like '"+empName+"'";
		DefaultTableModel dtm = ConnectDB.getSelectResult(query);
		return dtm;
	}
	
	//Update Function
	public static void updateShiftWhereName(String empName, String shift){
		String query = "Update Hospital.takes set shift_name = '"+shift+"' where employee_number in "+
				"(select distinct(employee_number) from hospital.employees where name like '"+empName+"')";
		System.out.println(query);
		ConnectDB.updateTable(query);
	}
}
