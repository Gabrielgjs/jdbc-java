package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
					+ "(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);//retorna o campo Id do banco de dados. 
			
			st.setString(1, "arya stark");
			st.setString(2, "arya@email.com");
			st.setDate(3, new java.sql.Date(sdf.parse("12/06/1998").getTime()));
			st.setDouble(4, 3000.00);
			st.setInt(5, 4);
			
			//executedUpdate método para executar o prepareStatement
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys(); //retorna o Id como resultset em forma de tabela
				while(rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Done! Id = " + id);
				}
			} else {
				System.out.println("No rows affected");
			}
			
			
			
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
