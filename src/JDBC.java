import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class JDBC {

	private final String userName = "root";
	private final String password = "";
	private final String serverName = "localhost";
	private final int portNumber = 3306;
	private final String dbName = "assign2";
	private final String studentTable = "students";
	private final String userTable = "users";
	
	public int studentCount = 0;
	public int userCount = 0;
	
	public ArrayList<Student> students = new ArrayList<Student>();
	public ArrayList<User> users = new ArrayList<User>();
	
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);

		conn = DriverManager.getConnection("jdbc:mysql://"
				+ this.serverName + ":" + this.portNumber + "/" + this.dbName,
				connectionProps);
		return conn;
	}

	public boolean executeUpdate(Connection conn, String command) throws SQLException {
	    Statement stmt = null;
	    try {
	        stmt = conn.createStatement();
	        stmt.executeUpdate(command); // This will throw a SQLException if it fails
	        return true;
	    } finally {

	    	// This will run whether we throw an exception or not
	        if (stmt != null) { stmt.close(); }
	    }
	}
	
	public void run() {
		// Connect to MySQL
		Connection conn = null;
		studentCount = 0;
		
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return;
		}
		try {
			Statement s1 = conn.createStatement ();
			Statement s2 = conn.createStatement ();
			
			s1.executeQuery ("SELECT * FROM students");
			s2.executeQuery ("SELECT * FROM users");
			ResultSet rsStudent = s1.getResultSet ();
			ResultSet rsUser = s2.getResultSet ();
			//s.executeQuery ("SELECT * FROM `users`");
			//ResultSet rsUser = s.getResultSet ();

			while (rsStudent.next ()) {
				Student student = new Student();
				student.setSid(rsStudent.getInt("SID"));
				student.setStudent_id(rsStudent.getInt("STUD_ID"));
				student.setFirstName(rsStudent.getString("FNAME"));
				student.setSecondName(rsStudent.getString("SNAME"));
			
				students.add(student);
				studentCount ++;
			}
	
			while (rsUser.next ()) {
				User user = new User();
				user.setUid(rsUser.getInt("UID"));
				user.setUserName(rsUser.getString("UNAME"));

				users.add(user);
				userCount ++;
			}
	
			rsStudent.close ();
			rsUser.close ();
			s1.close ();
			s2.close ();
			System.out.println (studentCount + " student rows were retrieved");
			System.out.println (userCount + " user rows were retrieved");
			
		} catch (SQLException e) {
			System.out.println("ERROR: Could not create the resultSet");
			e.printStackTrace();
			return;
		}
	}
	
	public String search(String surname) {
		try {
			studentCount = 0;
			Connection conn = this.getConnection();
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT * FROM `students` WHERE = "+surname);
			ResultSet rsStudent = s.getResultSet ();
			students = new ArrayList<Student>();
			while (rsStudent.next ()) {
				Student student = new Student();
				student.setSid(rsStudent.getInt("SID"));
				student.setStudent_id(rsStudent.getInt("STUD_ID"));
				student.setFirstName(rsStudent.getString("FNAME"));
				student.setSecondName(rsStudent.getString("SNAME"));
			
				students.add(student);
				studentCount ++;
			}
			rsStudent.close ();
			s.close ();
			System.out.println (studentCount + " rows were retrieved");
			if (studentCount == 0) {
				Student student = new Student();
				student.setSid(-1);
				student.setStudent_id(-1);
				student.setFirstName("");
				student.setSecondName("");
				
				students.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return surname;
	}

	/*
	public Employee create(Employee employee) {
		Connection conn = null;
		
		try {
			conn = this.getConnection();
		    String insertTable = "INSERT INTO `employee`(`social_security_number`, `date_of_birth`, `first_name`, `surname`, `salary`, `gender`) "
		    + "VALUES ("+employee.getSocialSecurityNumber()+",'"
		    +employee.getDateOfBirth()+"','"
			+employee.getFirstName()+"','"
		    +employee.getSurname()+"',"
		    +employee.getSalary()+",'"
		    +employee.getGender()+"')";
		    
			
			this.executeUpdate(conn, insertTable);
			System.out.println("Value inserted table");
			run();
	    } catch (SQLException e) {
			System.out.println("ERROR: Could not insert into the table");
			e.printStackTrace();
		}
		return employee;
	}
	
	public Employee update(Employee employee) {
		Connection conn = null;
		try {
			conn = this.getConnection();
		    String updateEmployee = "UPDATE `employee` SET "
		    		+"`date_of_birth`='"+employee.getDateOfBirth()
		    		+"',`first_name`='"+employee.getFirstName()
		    		+"',`surname`='"+employee.getSurname()
		    		+"',`salary`="+employee.getSalary()
		    		+",`gender`='"+employee.getGender()
		    		+"' WHERE social_security_number="+employee.getSocialSecurityNumber();
		    this.executeUpdate(conn, updateEmployee);
			System.out.println("User updated table");
			run();
	    } catch (SQLException e) {
			System.out.println("ERROR: Could not insert into the table");
			e.printStackTrace();
		}
		return employee;
	}
	
	public Employee delete(Employee employee) {

		try {
			Connection conn = this.getConnection();
			String deleteEmployee = "DELETE FROM `employee` WHERE social_security_number="+employee.getSocialSecurityNumber();    					
			this.executeUpdate(conn, deleteEmployee);
			System.out.println("Value deleted from employee");
			run();
		} catch (SQLException e) {
			System.out.println("ERROR: Could not delete employee");
			e.printStackTrace();
		}
		return employee;
	}
	*/

	/**
	 * Connect to the DB and do some stuff
	 */
	public static void main(String[] args) {
		JDBC app = new JDBC();
		app.run();
	}
	
		
}