package src;
import java.sql.*;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class ConnectDB {
	public static void main(String[] args){
		Connection conn = getConnection();
		SelectValuesFromTreatment svft = new SelectValuesFromTreatment();
	}
	
	//Creating connection
	public static Connection getConnection(){
		Connection conn = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e){
			System.out.println(e);
		}
		Properties connProp = new Properties();
		connProp.setProperty("user","annstephen");
		connProp.setProperty("password", "4Getmenot91908819");
		try{
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", connProp);
			System.out.println("successfully connected");
		}
		catch (SQLException e){
			System.out.println(e);
		}
		return conn;
	}
	
	//Used to execute select statements
	public static DefaultTableModel getSelectResult(String query){
		ResultSet result = null;
		Connection conn = getConnection();
		try {
			PreparedStatement p= conn.prepareStatement(query);
			result = p.executeQuery(query);
			return	buildTableModel(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
		}
		return null;
	}
	
	//Used to select the employee id for the insert statement
	public static String getSelectResultInsert(String query){
		ResultSet result = null;
		Connection conn = getConnection();
		try {
			PreparedStatement p= conn.prepareStatement(query);
			result = p.executeQuery(query);
			if(result.next()){
				return result.getString(1);
			}
			else
				return null; 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
		}
		return null;
	}
	
	//USed for statements that modify the relations
	public static void updateTable(String query){
		ResultSet result = null;
		Connection conn = getConnection();
		try {
			PreparedStatement p= conn.prepareStatement(query);
			p.executeUpdate(query);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	//Converts ResultSet to DefaultTableModel, the data type accepted by JTables
	public static DefaultTableModel buildTableModel(ResultSet rs)
	        throws SQLException {

	    ResultSetMetaData metaData = rs.getMetaData();

	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames);

	}
}
