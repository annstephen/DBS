package src;

import javax.swing.JFrame;

import org.eclipse.swt.widgets.Table;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Toolkit;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Insets;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

//This function allows users to search for patient visitation details based on their names 
public class SelectValuesFromTreatment extends JFrame {
	public SelectValuesFromTreatment() {
		
		final JFrame frame = this;
		
		//Grid Initialization to arrange components
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
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 1;
		getContentPane().add(lblName, gbc_lblName);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);
		//Default value for name is the wildcard %
		textField.setText("%");
		
		final JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;
		getContentPane().add(scrollPane, gbc_scrollPane);
		if(textField.getText().equals(null) || textField.getText().length() == 0){
			JOptionPane.showMessageDialog(null, "You need to have a non-empty value for the name");
		}
		else{
			table_1 = new JTable(getValuesFromTreatmentWithName(textField.getText()));
			scrollPane.setViewportView(table_1);
		}
		//Event control after typing a name
		java.awt.Button button = new java.awt.Button("Submit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Check for Null or empty value. 
				if(textField.getText().equals(null) || textField.getText().length() == 0){
					JOptionPane.showMessageDialog(null, "You need to have a non-empty value for the name");
				}
				else{
					table_1 = new JTable(getValuesFromTreatmentWithName(textField.getText()));
					scrollPane.setViewportView(table_1);
				}
			}
		});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 0, 5);
		gbc_button.gridx = 3;
		gbc_button.gridy = 4;
		getContentPane().add(button, gbc_button);
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
		gbc_btnExit.gridx = 4;
		gbc_btnExit.gridy = 4;
		getContentPane().add(btnExit, gbc_btnExit);
		
	}
	public static void main(String[] args){
		//createControls(null);
		try{
			SelectValuesFromTreatment svft = new SelectValuesFromTreatment();
			svft.setVisible(true);
		}
		catch(Exception e){
			System.out.println(e);
			JOptionPane.showMessageDialog(null, e);
		}
		
	}
	private Table table;
	private JTable table_1;
	private JTextField textField;
	
	//Query to search for patient and visitation details based on the name
	public static DefaultTableModel getValuesFromTreatmentWithName(String patientName){
		String query = "select p.name, p.contact_no, v.date_of_visit from hospital.patient p, hospital.visitation v where p.patient_registration_no = v.patient_registration_no"
				+" and p.name like '"+patientName+"'";
		DefaultTableModel dtm = ConnectDB.getSelectResult(query);
		return dtm;
	}

}
