package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			//Conexão com banco pelo método da classe DB
			conn = DB.getConnection();
			// PrepareStatement recebe comando Sql
			st = conn.prepareStatement(
					"INSERT INTO seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentID) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?)");
			
			st.setString(1, "John aryin");
			st.setString(2, "john@email.com");
			st.setDate(3, new java.sql.Date(sdf.parse("12/06/1997").getTime()));
			st.setDouble(4, 2200.00);
			st.setInt(5, 2);
			
			//executedUpdate método para executar o prepareStatement
			int rowsAffected = st.executeUpdate();
			
			System.out.println("Done!  Rows affected: " + rowsAffected);
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(ParseException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatemente(st);
			DB.closeConnection();
		}
		
	}

}
