
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class BookLocalFlight extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookLocalFlight frame = new BookLocalFlight();
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
	public BookLocalFlight() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 843, 419);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(37, 150, 190));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Yerel Uçuşlar");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(28, 0, 679, 28);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		contentPane.add(lblNewLabel);
		
		String[] columns = {"Uçuş ID", "Saat", "Tarih", "Kalkış Noktası",
                "Varış Noktası" , "Economy Koltuk" , "Business Koltuk" , "Uçuş Süresi"};

		DefaultTableModel obj=new DefaultTableModel(columns,0);
        
	    for (int i=0 ; i<LocalFlightSchedule.flightList.size() ; i++)
	    {
	    	
	        Object[] update= {LocalFlightSchedule.flightList.get(i).getFlightID() , LocalFlightSchedule.flightList.get(i).getDepartTime() , LocalFlightSchedule.flightList.get(i).getLandTime()
	        			, LocalFlightSchedule.flightList.get(i).getDepartDestination() , LocalFlightSchedule.flightList.get(i).getLandDestination() , 
	        			LocalFlightSchedule.flightList.get(i).getEconomySeats() , LocalFlightSchedule.flightList.get(i).getBusinessSeats() , LocalFlightSchedule.flightList.get(i).getDistance()};
	        
	    	obj.addRow(update);
	    }
	    
	     table = new JTable(obj);
	    JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 39, 764, 200);
		contentPane.add(scrollPane);
		
		JButton btnNewButton = new JButton("Uçuşu İptal Et");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for (Passengers x: SignUP.PassengerList)
				{
					if (x.getUsername().equals(SignUP.username.getText()))
					{
						if (x.getBooked()==true)
						{
							double hour=Integer.valueOf((String) table.getModel().getValueAt(table.getSelectedRow(), 7));
							
							double cost=(hour*10000)+((hour*10000)*5/100);
							JOptionPane.showMessageDialog(null, "Uçuşunuz " + (cost*25/100) + " ceza puanıyla başarılı bir şekilde iptal edildi");
							SignUP.PassengerList.get(0).setBooked(false);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Lütfen uçuş rezervasyonunuzu yapın.");
						}
					}
				}
				
				
			}
		});
		btnNewButton.setBounds(249, 250, 112, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Uçuş Rezervasyonu Yapın");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRowCount()==1)
				{
					String selectClass = JOptionPane.showInputDialog(null, "Ekonomi için '1', Business sınıfı için '2' girin.",null);
					int slctClass = Integer.valueOf(selectClass);
					
					switch (slctClass)
					{
						case 1:
						{
							for (Passengers x: SignUP.PassengerList)
							{
								if (x.getUsername().equals(SignUP.username.getText()))
								{
									if(x.getBooked()==true)
									{
										JOptionPane.showMessageDialog(null, "Uçuşunuz zaten Pasaportunuzla rezerve edildi: "+x.getPassport());
									}
									if (Integer.valueOf((String) table.getModel().getValueAt(table.getSelectedRow(), 5)) > 0 )
									{
										x.setBooked(true);
										JOptionPane.showMessageDialog(null, "Uçuşunuz Pasaportunuzla rezerve edildi: "+x.getPassport());
									}
									else
									{
										JOptionPane.showMessageDialog(null, "Bu uçuşta Ekonomi sınıfında boş koltuk bulunmamaktadır.");
									}
									
								}
							}
							break;
						}
						case 2:
						{
							for (Passengers x: SignUP.PassengerList)
							{
								if (x.getUsername().equals(SignUP.username.getText()))
								{
									if(x.getBooked()==true)
									{
										JOptionPane.showMessageDialog(null, "Uçuşunuz zaten Pasaportunuzla rezerve edildi: "+x.getPassport());
									}
									if (Integer.valueOf((String) table.getModel().getValueAt(table.getSelectedRow(), 6)) > 0 )
									{
										x.setBooked(true);
										JOptionPane.showMessageDialog(null, "Uçuşunuz Pasaportunuz karşılığında rezerve edildi: "+x.getPassport());
									}
									else
									{
										JOptionPane.showMessageDialog(null, "Bu uçuşta Business sınıfında boş koltuk yok.");
									}
								}
							}
							
							break;
						}
						default:
						{
							JOptionPane.showMessageDialog(null, "Geçersiz bir giriş girdiniz. Tekrar deneyin");
							break;
						}
					}
				}
				else if(table.getSelectedRowCount()==0)
				{
					JOptionPane.showMessageDialog(null, "Lütfen rezervasyon yapmak için bir uçuş seçin.");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Lütfen rezervasyon yapmak için tek seferde sadece bir uçuş seçin.");
				}
			}
		});
		btnNewButton_1.setBounds(127, 250, 111, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Bilet Fiyatını Görüntüle");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double hour=Integer.valueOf((String) table.getModel().getValueAt(table.getSelectedRow(), 7));
				
				double cost=(hour*10000)+((hour*10000)*5/100);
				JOptionPane.showMessageDialog(null, "Bu uçuşun maliyeti: "+cost);
			}
		});
		btnNewButton_2.setBounds(371, 250, 136, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Geri");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PassengerMenu obj=new PassengerMenu();
				obj.setVisible(true);
				dispose();
			}
		});
		btnNewButton_3.setBounds(517, 250, 112, 23);
		contentPane.add(btnNewButton_3);
	

}
}
