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

public class SelectDoctors extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectDoctors frame = new SelectDoctors();
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
	public SelectDoctors() {
		
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
		textField.setText("%");
		
		//Text Field for specialization
		JLabel lblSpec = new JLabel("Specialization");
		GridBagConstraints gbc_lblSpec = new GridBagConstraints();
		gbc_lblSpec.insets = new Insets(0, 0, 5, 5);
		gbc_lblSpec.anchor = GridBagConstraints.EAST;
		gbc_lblSpec.gridx = 3;
		gbc_lblSpec.gridy = 1;
		getContentPane().add(lblSpec, gbc_lblSpec);
		
		specField = new JTextField();
		GridBagConstraints gbc_specField = new GridBagConstraints();
		gbc_specField.anchor = GridBagConstraints.WEST;
		gbc_specField.insets = new Insets(0, 0, 5, 5);
		gbc_specField.gridx = 4;
		gbc_specField.gridy = 1;
		getContentPane().add(specField, gbc_specField);
		specField.setColumns(10);
		specField.setText("%");
		
		final JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;
		getContentPane().add(scrollPane, gbc_scrollPane);
		table_1 = new JTable(getDoctorsFromNameAndSpec(textField.getText(),specField.getText()));
		scrollPane.setViewportView(table_1);
		
		//Check and action controller for the submit button
		//Both fields are checked for empty string
		java.awt.Button button = new java.awt.Button("Submit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().equals(null) || textField.getText().length() == 0){
					JOptionPane.showMessageDialog(null, "You need to have a non-empty value for the name");
				}
				else if(textField.getText().equals(null) || textField.getText().length() == 0){
					JOptionPane.showMessageDialog(null, "You need to have a non-empty value for the specialization");
				}
				table_1 = new JTable(getDoctorsFromNameAndSpec(textField.getText(),specField.getText()));
				scrollPane.setViewportView(table_1);
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
	private JTable table_1;
	private JTextField textField;
	private JTextField specField;
	
	//Select query formation and call
	public static DefaultTableModel getDoctorsFromNameAndSpec(String name, String spc){
		String query = "select name, certification_no, specialization from hospital.employees natural join hospital.physician "+
				"  where specialization like '"+spc+"' and name like '"+name+"'";
		DefaultTableModel dtm = ConnectDB.getSelectResult(query);
		return dtm;
	}
}
