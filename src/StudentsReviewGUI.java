import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class StudentsReviewGUI {

	public JFrame frame;
	private JLabel sidField;
	private JLabel studentIDField;
	private JLabel firstNameField;
	private JLabel secondNameField;
	private JDBC jdbc;
	private JTextField searchField;
	private JLabel userCount; 
	private int studentno = 0;
	private int currentCount = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public StudentsReviewGUI(JDBC jdbc) {
		this.jdbc = jdbc;
		initialize();
		sidField.setText(String.valueOf(jdbc.students.get(studentno).getSid()));
		studentIDField.setText(String.valueOf(jdbc.students.get(studentno).getStudent_id()));
		firstNameField.setText(jdbc.students.get(studentno).getFirstName());
		secondNameField.setText(jdbc.students.get(studentno).getSecondName());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Student Database");
		
		sidField = new JLabel();
		
		studentIDField = new JLabel();

		firstNameField = new JLabel();

		
		secondNameField = new JLabel();
		
		userCount = new JLabel("Student 1 out of "+jdbc.studentCount);

		
		JLabel lblSid = new JLabel("SID");
		
		JLabel lblStudentId = new JLabel("Student ID");
		
		JLabel lblFirstName = new JLabel("First Name");
		
		JLabel lblSecondName = new JLabel("Second Name");
		
		searchField = new JTextField();
		searchField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(searchField.getText());
				if (searchField.getText()!= "") {
					jdbc.searchSurname(searchField.getText());
					sidField.setText(String.valueOf(jdbc.students.get(0).getSid()));
					studentIDField.setText(String.valueOf(jdbc.students.get(0).getStudent_id()));
					firstNameField.setText(jdbc.students.get(0).getFirstName());
					secondNameField.setText(jdbc.students.get(0).getSecondName());
					
					userCount.setText("Student "+ 1 +" out of " + jdbc.studentCount);
				}
			}
		});
		
		JButton btnNextStudent = new JButton("Next Student");
		btnNextStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (studentno < jdbc.studentCount-1) {
					studentno++;
				} else if (jdbc.studentCount > 1){
					studentno = 0;
				}
				
				sidField.setText(String.valueOf(jdbc.students.get(studentno).getSid()));
				studentIDField.setText(String.valueOf(jdbc.students.get(studentno).getStudent_id()));
				firstNameField.setText(jdbc.students.get(studentno).getFirstName());
				secondNameField.setText(jdbc.students.get(studentno).getSecondName());
				currentCount = studentno + 1;
				userCount.setText("Student "+ currentCount +" out of " + jdbc.studentCount);
			}
		});
		
		JButton btnPreviousStudent = new JButton("Previous Student");
		btnPreviousStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (studentno != 0) {
					studentno--;
					} else {
						studentno = jdbc.studentCount-1;
					}
				
				sidField.setText(String.valueOf(jdbc.students.get(studentno).getSid()));
				studentIDField.setText(String.valueOf(jdbc.students.get(studentno).getStudent_id()));
				firstNameField.setText(jdbc.students.get(studentno).getFirstName());
				secondNameField.setText(jdbc.students.get(studentno).getSecondName());
				currentCount = studentno + 1;
				userCount.setText("Student "+ currentCount +" out of " + jdbc.studentCount);
			}
		});
		
		JButton btnClearFields = new JButton("Clear Fields");
		btnClearFields.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sidField.setText(String.valueOf(""));
				studentIDField.setText(String.valueOf(""));
				firstNameField.setText("");
				secondNameField.setText("");
				searchField.setText("");
			}
		});
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jdbc.run();
	
				sidField.setText(String.valueOf(jdbc.students.get(0).getSid()));
				studentIDField.setText(String.valueOf(jdbc.students.get(0).getStudent_id()));
				firstNameField.setText(jdbc.students.get(0).getFirstName());
				secondNameField.setText(jdbc.students.get(0).getSecondName());
				userCount.setText("User "+ 1 +" out of " + jdbc.studentCount);
				searchField.setText("");
			
			}
		});
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(123, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblFirstName)
								.addComponent(lblStudentId)
								.addComponent(lblSid)
								.addComponent(lblSecondName))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(userCount, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(searchField, Alignment.TRAILING)
								.addComponent(btnSearch, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnRefresh, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(sidField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(studentIDField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(firstNameField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(secondNameField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnClearFields, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnPreviousStudent, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNextStudent, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(41)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(sidField)
								.addComponent(lblSid))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(studentIDField)
								.addComponent(lblStudentId)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(52)
							.addComponent(btnNextStudent)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(firstNameField)
						.addComponent(lblFirstName)
						.addComponent(btnPreviousStudent))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(secondNameField)
						.addComponent(lblSecondName)
						.addComponent(btnClearFields))
					.addGap(46)
					.addComponent(searchField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSearch)
					.addGap(18)
					.addComponent(btnRefresh)
					.addGap(18)
					.addComponent(userCount, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
					.addGap(41)
					.addComponent(btnExit)
					.addGap(103))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
