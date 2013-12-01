package src;


import java.lang.Object;
import java.sql.ResultSet;

import javax.swing.plaf.TableUI;
import javax.swing.table.DefaultTableModel;

import src.ConnectDB;

public class SelectValuesFromTreatmentBean {
	public static DefaultTableModel getValuesFromTreatment(){
		String query = "select p.name, p.contact_no, v.date_of_visit from hospital.patient p, hospital.visitation v where p.patient_registration_no = v.patient_registration_no";
		DefaultTableModel dtm = ConnectDB.getSelectResult(query);
		return dtm;
	}
	
	public static DefaultTableModel getValuesFromTreatmentWithName(String patientName){
		String query = "select p.name, p.contact_no, v.date_of_visit from hospital.patient p, hospital.visitation v where p.patient_registration_no = v.patient_registration_no"
				+" and p.name like '"+patientName+"'";
		DefaultTableModel dtm = ConnectDB.getSelectResult(query);
		return dtm;
	}
}
