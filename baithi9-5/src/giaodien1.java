import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class giaodien1 extends JFrame {

	private JPanel contentPane;
	private JTextField txt1;
	private JTextField txt2;
	private JTextField txtResult;
	
	private Socket socketClient;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					giaodien1 frame = new giaodien1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public giaodien1() {
		setTitle("Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 354, 170);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFirstNumber = new JLabel("First Number");
		lblFirstNumber.setBounds(10, 11, 113, 14);
		contentPane.add(lblFirstNumber);
		
		JLabel lblSecondNumber = new JLabel("Second Number");
		lblSecondNumber.setBounds(10, 44, 113, 14);
		contentPane.add(lblSecondNumber);
		
		JLabel lblResultFromServer = new JLabel("Result From Server");
		lblResultFromServer.setBounds(10, 82, 113, 14);
		contentPane.add(lblResultFromServer);
		
		txt1 = new JTextField();
		txt1.setBounds(133, 8, 86, 20);
		contentPane.add(txt1);
		txt1.setColumns(10);
		
		txt2 = new JTextField();
		txt2.setBounds(133, 41, 86, 20);
		contentPane.add(txt2);
		txt2.setColumns(10);
		
		txtResult = new JTextField();
		txtResult.setBounds(133, 79, 86, 20);
		contentPane.add(txtResult);
		txtResult.setColumns(10);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					socketClient = new Socket("localhost", 1234);
					JOptionPane.showMessageDialog(null, "Kết nối thành công");
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnConnect.setBounds(239, 7, 89, 23);
		contentPane.add(btnConnect);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dos = new DataOutputStream(socketClient.getOutputStream());
					dos.writeUTF(txt1.getText());
					dos.writeUTF(txt2.getText());
					
					dis = new DataInputStream(socketClient.getInputStream());
					txtResult.setText(dis.readUTF());
					
					dis.close();
					dos.close();
					socketClient.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnSend.setBounds(239, 40, 89, 23);
		contentPane.add(btnSend);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(239, 78, 89, 23);
		contentPane.add(btnExit);
	}
}