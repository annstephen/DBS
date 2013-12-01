package src;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import javax.swing.JRadioButton;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JPopupMenu;

//Main Menu. Allows for activity selection based on a radio button. Also provides an EXIT button to quit.
public class MainMenu extends JFrame{
	public MainMenu() {
		//Grid Initialization to arrange components
		final JFrame frame = this;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0, 0.0, Double.MIN_VALUE, 0.0};
		gridBagLayout.rowWeights = new double[]{0, 0.0, 0.0, 0.0, 0.0, 0.0};
		getContentPane().setLayout(gridBagLayout);
		
		//Option to pick patients based on their names
		final JRadioButton rdbtnSelectPatients = new JRadioButton("Select Patients");
		rdbtnSelectPatients.setActionCommand("Select Patients");
		GridBagConstraints gbc_rdbtnSelectPatients = new GridBagConstraints();
		gbc_rdbtnSelectPatients.gridwidth = 1;
		gbc_rdbtnSelectPatients.anchor = GridBagConstraints.WEST;
		gbc_rdbtnSelectPatients.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnSelectPatients.gridx = 3;
		gbc_rdbtnSelectPatients.gridy = 0;
		getContentPane().add(rdbtnSelectPatients, gbc_rdbtnSelectPatients);
		
		//Option to change the shifts of employees based on their names
		final JRadioButton rdbtnChangeShift = new JRadioButton("Change Shift");
		rdbtnChangeShift.setActionCommand("Change Shift");
		GridBagConstraints gbc_rdbtnChangeShift = new GridBagConstraints();
		gbc_rdbtnChangeShift.gridwidth = 1;
		gbc_rdbtnChangeShift.anchor = GridBagConstraints.WEST;
		gbc_rdbtnChangeShift.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnChangeShift.gridx = 3;
		gbc_rdbtnChangeShift.gridy = 1;
		getContentPane().add(rdbtnChangeShift, gbc_rdbtnChangeShift);
		
		//Option to add a visitation for a patient
		final JRadioButton rdbtnInsertNewVisit = new JRadioButton("Insert new Visit");
		rdbtnInsertNewVisit.setActionCommand("Insert new Visit");
		GridBagConstraints gbc_rdbtnInsertNewVisit = new GridBagConstraints();
		gbc_rdbtnInsertNewVisit.gridwidth = 1;
		gbc_rdbtnInsertNewVisit.anchor = GridBagConstraints.WEST;
		gbc_rdbtnInsertNewVisit.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnInsertNewVisit.gridx = 3;
		gbc_rdbtnInsertNewVisit.gridy = 2;
		getContentPane().add(rdbtnInsertNewVisit, gbc_rdbtnInsertNewVisit);
		
		//Option to see doctors on the basis of their name and specialization
		final JRadioButton rdbtnSeeDoctorsOn = new JRadioButton("See Doctors on the basis of names and specilaization");
		GridBagConstraints gbc_rdbtnSeeDoctorsOn = new GridBagConstraints();
		gbc_rdbtnSeeDoctorsOn.anchor = GridBagConstraints.WEST;
		gbc_rdbtnSeeDoctorsOn.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnSeeDoctorsOn.gridx = 3;
		gbc_rdbtnSeeDoctorsOn.gridy = 3;
		getContentPane().add(rdbtnSeeDoctorsOn, gbc_rdbtnSeeDoctorsOn);
		
		//Option to delete medicines based on the basis of the name and expiry date
		final JRadioButton rdbtnDeleteOnBatch = new JRadioButton("Delete medicines from Batch based on name and expiry date");
		GridBagConstraints gbc_rdbtnDeleteOnBatch = new GridBagConstraints();
		gbc_rdbtnDeleteOnBatch.anchor = GridBagConstraints.WEST;
		gbc_rdbtnDeleteOnBatch.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnDeleteOnBatch.gridx = 3;
		gbc_rdbtnDeleteOnBatch.gridy = 4;
		getContentPane().add(rdbtnDeleteOnBatch, gbc_rdbtnDeleteOnBatch);
		
		//Adding single selection functionality
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnSelectPatients);
		bg.add(rdbtnChangeShift);
		bg.add(rdbtnInsertNewVisit);
		bg.add(rdbtnSeeDoctorsOn);
		
		//Control to launch the different windows
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnSelectPatients.isSelected()){
					JFrame svft = new SelectValuesFromTreatment();
					svft.setVisible(true);
				}
				else if(rdbtnChangeShift.isSelected()){
					JFrame cs = new ChangeShift();
					cs.setVisible(true);
				}
				else if(rdbtnInsertNewVisit.isSelected()){
					JFrame iv = new InsertVisit();
					iv.setVisible(true);
				}
				else if(rdbtnSeeDoctorsOn.isSelected()){
					JFrame sd = new SelectDoctors();
					sd.setVisible(true);
				}
				else if(rdbtnDeleteOnBatch.isSelected()){
					JFrame dbm = new DeleteBatchMedicine();
					dbm.setVisible(true);
				}
				else{
					JOptionPane.showMessageDialog(null, "Please pick one option");
				}
			}
		});
		
		//Option to exit
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
		gbc_btnExit.gridy = 5;
		getContentPane().add(btnExit, gbc_btnExit);
		
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.gridx = 4;
		gbc_btnSubmit.gridy = 5;
		getContentPane().add(btnSubmit, gbc_btnSubmit);
	}

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainMenu window = new MainMenu();
			window.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
