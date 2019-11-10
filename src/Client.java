import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JTextPane;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import net.miginfocom.swing.MigLayout;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JTextArea;

public class Client extends JFrame{
	public JTextField textField;
	public JTextArea textArea = new JTextArea();
	private DataOutputStream toServer;
	private DataInputStream fromServer;

	/**
	 * Launch the application.
	 */	
	public static void main(String[] args) {
		Client gui = new Client();
		gui.setVisible(true);
		gui.setTitle("User Login");
		gui.setBounds(500,120,560,340);
		gui.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public Client() {
		
		try {
		      // Create a socket to connect to the server
		      Socket socket = new Socket("localhost", 8000);

		      // Create an input stream to receive data from the server
		      fromServer = new DataInputStream(socket.getInputStream());

		      // Create an output stream to send data to the server
		      toServer = new DataOutputStream(socket.getOutputStream());
		    }
		    catch (IOException ex) {
		      textArea.append(ex.toString() + '\n');
		    }
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int uid = Integer.parseInt(textField.getText().strip());
				textArea.append("Server: Processing......");
		        try {
					toServer.writeInt(uid);
					if(fromServer.readBoolean() == true) {
			        	textArea.append("\nClient-1: Welcome " + fromServer.readUTF() + "... You are now connected to the Server.");
						StudentsReviewGUI window = new StudentsReviewGUI();
						window.frame.setVisible(true);
						window.frame.setBounds(500,120,400,450);
						window.frame.setVisible(true);
					 } else {
				        	textArea.append("\nClient-1 Sorry " + uid + ". You are not a registered student. Bye.\n");
				        }
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        
		        	
		     
		       
			}
		});
		
		JLabel lblUserLogin = new JLabel("User Login");
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(241)
							.addComponent(btnLogin))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(221)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(243)
							.addComponent(lblUserLogin))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addComponent(lblUserLogin)
					.addGap(18)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnLogin)
					.addGap(30)
					.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	}
}
