package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

public class DAO implements DAOIF {
	
}

/*
public class DAO implements DAOIF {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC-ajurin lataus epäonnistui");
			System.exit(-1); // lopetus heti virheen vuoksi
		}
		//ArrayList<Valuutta> valuutat = new ArrayList<>();
		//final String URL = "jdbc:mysql://localhost/game_database";
		String URL = "jdbc:mysql://localhost:2206/game_database";
		final String USERNAME = "akikoppinen";
		final String PASSWORD = "rootpassword";
		try {
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			Statement stmt1 = conn.createStatement();
			String query1 = "SELECT firstName, lastName FROM player";
			ResultSet rs = stmt1.executeQuery(query1);
			
			while (rs.next()) {
				 String etunimi = rs.getString("firstName"); // tai rs.getInt(1)
				 String sukunimi = rs.getString("lastName");
				 System.out.println(etunimi);
				 //valuutat.add(new Valuutta(etunimi, sukunimi));
			}
			
			ResultSetMetaData rsMetaData = rs.getMetaData();
			int a = rsMetaData.getColumnCount();
			String b = rsMetaData.getColumnName(1); //starts from 1, käytä (getInt(), getDouble(),getBoolean() ja getString()
			
			System.out.println(a);
			System.out.println(b);
			
			//for (Valuutta valuutta: valuutat) {
				//System.out.println(valuutta);
			//}
			
			/*
			Statement stmt2 = conn.createStatement();
			String query2 = "INSERT INTO valuutta " +
			 "VALUES ('NTD', 40.0, 'New Taiwan Dollar')";
			int count = stmt2.executeUpdate(query2); //rivimuutosten määrä
			
			
			rs.close(); // tulosjoukko
			stmt1.close(); // kysely
			//stmt2.close(); // kysely
			conn.close(); // tietokantayhteys, vapauta AINA
			
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}*/

