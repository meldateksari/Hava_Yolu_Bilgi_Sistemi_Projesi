
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setBackground(new Color(37, 150, 190));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 401);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(37, 150, 190));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Havayolu Bilgi Yönetim Sistemine Hoşgeldiniz ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(41, 11, 414, 24);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		contentPane.add(lblNewLabel);
		
		JLabel label1 = new JLabel("Kullanıcı adı");
		label1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label1.setBounds(10, 46, 83, 17);
		label1.setForeground(new Color(255, 255, 255));
		contentPane.add(label1);
		
		JLabel label2 = new JLabel("Şifre");
		label2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label2.setBounds(10, 71, 83, 17);
		label2.setForeground(new Color(255, 255, 255));
		contentPane.add(label2);
		
		username = new JTextField();
		username.setBounds(103, 43, 121, 20);
		contentPane.add(username);
		username.setColumns(10);
		
		JButton loginbtn = new JButton("Giriş Yap");
		loginbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (username.getText().equals("") || password.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Lütfen bütün alanları doldurunuz");
				}
				
				for (Passengers x: SignUP.PassengerList)
				{
					if (username.getText().equals(x.getUsername()) && password.getText().equals(x.getPassword()))
					{
						PassengerMenu obj = new PassengerMenu();
						obj.setVisible(true);
						dispose();
					}
					else if(!username.getText().equals(x.getPassword()))
					{
						JOptionPane.showMessageDialog(null, "Girilen bilgiler yanlış. Tekrar deneyin");
					}
					else if(!(username.getText().equals(x.getUsername()) && password.getText().equals(x.getPassword())))
					{
						JOptionPane.showMessageDialog(null, "Kayıtlı bir kullanıcı değilsiniz.");

					}
				}
			}
		});
		loginbtn.setBounds(103, 99, 89, 23);
		contentPane.add(loginbtn);
		
		loginbtn.setBounds(103, 99, 89, 23);
		contentPane.add(loginbtn);
		
		JButton signupbtn = new JButton("Kayıt Ol");
		signupbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				SignUP obj=new SignUP();
				obj.setVisible(true);
			}
		});
		signupbtn.setBounds(103, 133, 89, 23);
		contentPane.add(signupbtn);
		
		JLabel label3 = new JLabel("Yeni Hesap");
		label3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label3.setBounds(10, 137, 83, 19);
		label3.setForeground(new Color(255, 255, 255));
		contentPane.add(label3);
		
		password = new JPasswordField();
		password.setBounds(103, 71, 121, 20);
		contentPane.add(password);
		
		JButton btnNewButton = new JButton("Geri");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(419, 137, 89, 23);
		contentPane.add(btnNewButton);
		
		
	}
}
