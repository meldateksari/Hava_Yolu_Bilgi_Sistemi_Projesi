
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;

public class BookInternationalFlight extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private String visa="0";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookInternationalFlight frame = new BookInternationalFlight();
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
	public BookInternationalFlight() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 872, 433);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(37, 150, 190));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Uluslararası Uçuşlar");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 764, 23);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		contentPane.add(lblNewLabel);
		
		String[] columns = {"Uçuş ID", "Saat", "Tarih", "Kalkış Noktası",
                "Varış Noktası" , "Economy Koltuk" , "Business Koltuk" , "Uçuş Süresi"};

		DefaultTableModel obj=new DefaultTableModel(columns,0);
        
	    for (int i=0 ; i<InternationalFlightSchedule.flightList2.size() ; i++)
	    {
	    	
	        Object[] update= {InternationalFlightSchedule.flightList2.get(i).getFlightID() , InternationalFlightSchedule.flightList2.get(i).getDepartTime() , InternationalFlightSchedule.flightList2.get(i).getLandTime()
	        			, InternationalFlightSchedule.flightList2.get(i).getDepartDestination() , InternationalFlightSchedule.flightList2.get(i).getLandDestination() , 
	        			InternationalFlightSchedule.flightList2.get(i).getEconomySeats() , InternationalFlightSchedule.flightList2.get(i).getBusinessSeats() , InternationalFlightSchedule.flightList2.get(i).getDistance()};
	        
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
					
					
					
		

					String visachoice= JOptionPane.showInputDialog(null,"Vize sahibi olmak için Y, Vize sahibi olmamak için N girin",null);
					if(visachoice.equals("N")) {
						JOptionPane.showMessageDialog(null,"Üzgünüz, uçuş rezervasyonu yapamazsınız");
					}
					else if(visachoice.equals("Y")){
						String userchoice= JOptionPane.showInputDialog(null,"Ekonomi için '1', Business sınıfı için '2' girin.",null);
						
					
					int slctClass = Integer.valueOf(userchoice);
						
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
											JOptionPane.showMessageDialog(null, "Uçuşunuz zaten Pasaportunuzla rezerve edildi "+x.getPassport());
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
					else
					{
						System.out.println(visa);
						JOptionPane.showMessageDialog(null, "Lütfen yalnızca '1' veya '2' girin.");
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
				
				double cost=(hour*20000)+((hour*20000)*10/100);
				JOptionPane.showMessageDialog(null, "Bu uçuşun maliyeti: "+cost);
			}
		});
		btnNewButton_2.setBounds(371, 250, 131, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Geri");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PassengerMenu obj=new PassengerMenu();
				obj.setVisible(true);
				dispose();
			}
		});
		btnNewButton_3.setBounds(543, 250, 112, 23);
		contentPane.add(btnNewButton_3);
	

}
}
