import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.SocketSecurityException;
import java.awt.event.ActionEvent;

public class giaodien extends JFrame {

	private JPanel contentPane;
	private ServerSocket server;
	private Socket socketServer;
	
	private DataInputStream dis;
	private DataOutputStream dos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					giaodien frame = new giaodien();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @return 
	 */
	public void giaovien() {
		setTitle("Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 379, 82);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					server = new ServerSocket(1234);
					socketServer = server.accept();
					
					dis = new DataInputStream(socketServer.getInputStream());
					int a = Integer.parseInt(dis.readUTF());
					int b = Integer.parseInt(dis.readUTF());
					int sum = a + b;
					
					dos = new DataOutputStream(socketServer.getOutputStream());
					dos.writeUTF(String.valueOf(sum));
					dos.close();
					dis.close();
					socketServer.close();
					server.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnStart.setBounds(10, 11, 343, 23);
		contentPane.add(btnStart);
	}
}