package jdbc_PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {

	public static void main(String[] args) throws SQLException {
		Connection connection=null;
		// Step 1: Register JDBC driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded successfully....");
			// STEP 2: Open a connection
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "root");
			System.out.println("Connection created successfully....");

			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO emp (eId, eName, eSal) VALUES (?, ?, ?)");

			preparedStatement.setString(1, "21");
			preparedStatement.setString(2, "John Devit");
			preparedStatement.setString(3, "45000");

			int rowsInserted = preparedStatement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("A new employee was inserted successfully!");
			}
			//we will update required record
			  PreparedStatement preparedStatement2 =connection.prepareStatement("update emp set eSal=eSal+? where eSal>?");
			  preparedStatement2.setInt(1, 200); 
			  preparedStatement2.setInt(2, 18000);
			  int x=preparedStatement2.executeUpdate();
			  System.out.println("record updated successfully..!");
			  preparedStatement2=connection.prepareStatement("select * from emp");
			  ResultSet rs=preparedStatement2.executeQuery();
			  while (rs.next()) {
					// Retrieve by column name
					int id = rs.getInt("eId");
					String name = rs.getString("eName");
					int salary = rs.getInt("eSal");
					// Display values
					System.out.print("ID: " + id);
					System.out.print(", name: " + name);
					System.out.println(", salary: " + salary);

				}
			 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			// finally block used to close resources
		
				connection.close();
				System.out.println("connecton closed successfully...");
			
		}
	}

}
