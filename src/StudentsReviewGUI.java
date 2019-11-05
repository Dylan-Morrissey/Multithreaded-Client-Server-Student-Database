import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentsReviewGUI {

	private JFrame frame;
	private JTextField sidField;
	private JTextField studentIDField;
	private JTextField firstNameField;
	private JTextField secondNameField;
	private JTextField searchField;
	private JLabel userCount = new JLabel("Student 1 out of "+jdbc.studentCount);
	private static JDBC jdbc = new JDBC();
	private int studentno = 0;
	private int currentCount = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		jdbc.run();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentsReviewGUI window = new StudentsReviewGUI();
					window.frame.setVisible(true);
					window.frame.setBounds(500,120,400,450);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StudentsReviewGUI() {
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
		
		sidField = new JTextField();
		sidField.setColumns(10);
		
		studentIDField = new JTextField();
		studentIDField.setColumns(10);
		
		firstNameField = new JTextField();
		firstNameField.setColumns(10);
		
		secondNameField = new JTextField();
		secondNameField.setColumns(10);
		
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
					jdbc.search(searchField.getText());		
					sidField.setText(String.valueOf(jdbc.students.get(studentno).getSid()));
					studentIDField.setText(String.valueOf(jdbc.students.get(studentno).getStudent_id()));
					firstNameField.setText(jdbc.students.get(studentno).getFirstName());
					secondNameField.setText(jdbc.students.get(studentno).getSecondName());
					
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
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(130, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblFirstName)
								.addComponent(lblStudentId)
								.addComponent(lblSid)
								.addComponent(lblSecondName))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(searchField)
								.addComponent(secondNameField)
								.addComponent(firstNameField)
								.addComponent(studentIDField)
								.addComponent(sidField)
								.addComponent(btnSearch, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(btnRefresh, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
							.addComponent(userCount)))
					.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnNextStudent)
						.addComponent(btnPreviousStudent)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(btnClearFields)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(41)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(sidField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSid))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(studentIDField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblStudentId))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(firstNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblFirstName)
								.addComponent(btnPreviousStudent)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(52)
							.addComponent(btnNextStudent)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(secondNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSecondName))
							.addGap(46)
							.addComponent(searchField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSearch))
						.addComponent(btnClearFields))
					.addPreferredGap(ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
					.addComponent(userCount)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnRefresh)
					.addGap(7))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
