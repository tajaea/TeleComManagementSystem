package com.microstar.cablevision.views;

import java.awt.Color;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.microstar.cablevision.controller.RepresentativeController;

import microStarCableVision.Client;

public class ViewAllServicesRepresentative extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	JPanel scomplaintsOptionsTab;
	JLabel logoImageLabel;
	JPanel complaintsOptionsDetails;
	JPanel noServiceDetailPanel;
	JPanel billComplaintDetailPanel;
	JPanel pDestructionDetailPanel;
	JScrollBar scrollBar;
	JButton noServiceButton;
	JButton billCButton;
	JButton pDestructionButton;
	JButton exitButton;
	JLabel moralsLabel;
	JPanel moralsPanel;
	JLabel moralsLogo;
	JLabel moralsDescription;
	JLabel customerImage1;
	JLabel customerImage2;
	JLabel customerImage3;
	JLabel customerName1;
	JLabel customerName2;
	JLabel customerName3;
	JLabel customerComment1;
	JLabel customerComment2;
	JLabel customerComment3;
	JLabel moralsFooter;
	JLabel warningrImage;
	JLabel warningDescription;
	JLabel pDCheckMark1;
	JLabel pDCheckMark2;
	JLabel pDCheckMark3;
	JLabel pDService1;
	JLabel pDService2;
	JLabel pDService3;
	JLabel pDFooter;
	JLabel bCServiceImage;
	JLabel bCCheckMark1;
	JLabel bCCheckMark2;
	JLabel bCService1;
	JLabel bCService2;
	JLabel bCFooter;
	JLabel bCDescription;
	
	static RepresentativeController repController;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAllServicesRepresentative frame = new ViewAllServicesRepresentative();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
		
		new ViewAllServicesRepresentative(new Client());
	}

	/**
	 * Create the frame.
	 */
	public ViewAllServicesRepresentative(Client client) {
		setResizable(false);
		setMinimumSize(new Dimension(800, 500));
		setMaximumSize(new Dimension(800, 500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		repController = new RepresentativeController(null, null, this);
		repController.setClient(client);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 245, 238));
		contentPane.setMinimumSize(new Dimension(800, 500));
		contentPane.setMaximumSize(new Dimension(800, 500));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scomplaintsOptionsTab = new JPanel();
		scomplaintsOptionsTab.setBackground(new Color(255, 245, 238));
		scomplaintsOptionsTab.setBounds(10, 11, 253, 449);
		contentPane.add(scomplaintsOptionsTab);
		scomplaintsOptionsTab.setLayout(null);
		
		logoImageLabel = new JLabel("");
		logoImageLabel.setIcon(new ImageIcon("images\\star.png"));
		logoImageLabel.setBounds(62, 5, 110, 100);
		scomplaintsOptionsTab.add(logoImageLabel);
		
		moralsLabel = new JLabel("..");
		moralsLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				if(SwingUtilities.isLeftMouseButton(event)) {
					moralsPanel.setVisible(true);
					noServiceDetailPanel.setVisible(false);
					billComplaintDetailPanel.setVisible(false);
					pDestructionDetailPanel.setVisible(false);
				}
			}
		});
		moralsLabel.setBounds(10, 96, 233, 30);
		scomplaintsOptionsTab.add(moralsLabel);
		
		noServiceButton = new JButton("No Service");
		noServiceButton.setFont(new Font("Dubai", Font.PLAIN, 23));
		noServiceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				noServiceButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent event) {
						if(SwingUtilities.isLeftMouseButton(event)) {
							moralsPanel.setVisible(false);
							noServiceDetailPanel.setVisible(true);
							billComplaintDetailPanel.setVisible(false);
							pDestructionDetailPanel.setVisible(false);
						}
					}
				});
			}
		});
		noServiceButton.setBounds(10, 137, 233, 37);
		scomplaintsOptionsTab.add(noServiceButton);
		
		billCButton = new JButton("Bill Complaint");
		billCButton.setFont(new Font("Dubai", Font.PLAIN, 20));
		billCButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				billCButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent event) {
						if(SwingUtilities.isLeftMouseButton(event)) {
							moralsPanel.setVisible(false);
							noServiceDetailPanel.setVisible(false);
							billComplaintDetailPanel.setVisible(true);
							pDestructionDetailPanel.setVisible(false);
						}
					}
				});
			}	
		});
		billCButton.setBounds(10, 229, 233, 37);
		scomplaintsOptionsTab.add(billCButton);
		
		pDestructionButton = new JButton("Property Destruction");
		pDestructionButton.setFont(new Font("Dubai", Font.PLAIN, 20));
		pDestructionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pDestructionButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent event) {
						if(SwingUtilities.isLeftMouseButton(event)) {
							moralsPanel.setVisible(false);
							noServiceDetailPanel.setVisible(false);
							billComplaintDetailPanel.setVisible(false);
							pDestructionDetailPanel.setVisible(true);
						}
					}
				});
			}	
		});
		pDestructionButton.setBounds(10, 318, 233, 37);
		scomplaintsOptionsTab.add(pDestructionButton);
		
		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				exitButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent event) {
						if(SwingUtilities.isLeftMouseButton(event)) {
							repController.returnToRepGui();
						}
					}
				});
			}
		});
		exitButton.setBounds(10, 415, 89, 23);
		scomplaintsOptionsTab.add(exitButton);
		
		complaintsOptionsDetails = new JPanel();
		complaintsOptionsDetails.setBorder(new LineBorder(new Color(0, 0, 0)));
		complaintsOptionsDetails.setBackground(new Color(255, 245, 238));
		complaintsOptionsDetails.setBounds(273, 11, 483, 449);
		contentPane.add(complaintsOptionsDetails);
		complaintsOptionsDetails.setLayout(null);
		
		moralsPanel = new JPanel();
		moralsPanel.setBackground(new Color(255, 245, 238));
		moralsPanel.setBounds(10, 11, 463, 427);
		complaintsOptionsDetails.add(moralsPanel);
		moralsPanel.setLayout(null);
		
		moralsLogo = new JLabel("");
		moralsLogo.setIcon(new ImageIcon("images\\authorized-dealer.png"));
		moralsLogo.setBounds(218, 0, 64, 64);
		moralsPanel.add(moralsLogo);
		
		moralsDescription = new JLabel("<html>Here at MicroStarCable Vision,<br>we pride ourselves on providing excellent cable services to our customers but don't take our word for it, take theirs.");
		moralsDescription.setFont(new Font("Dubai", Font.PLAIN, 16));
		moralsDescription.setBounds(29, 65, 411, 89);
		moralsPanel.add(moralsDescription);
		
		customerImage1 = new JLabel("");
		customerImage1.setIcon(new ImageIcon("images\\man.png"));
		customerImage1.setBounds(90, 165, 32, 32);
		moralsPanel.add(customerImage1);
		
		customerImage2 = new JLabel("");
		customerImage2.setIcon(new ImageIcon("images\\girl.png"));
		customerImage2.setBounds(205, 165, 32, 32);
		moralsPanel.add(customerImage2);
		
		customerImage3 = new JLabel("");
		customerImage3.setIcon(new ImageIcon("images\\couple.png"));
		customerImage3.setBounds(335, 165, 32, 32);
		moralsPanel.add(customerImage3);
		
		customerName1 = new JLabel("Christopher Panther");
		customerName1.setFont(new Font("Dubai", Font.BOLD, 13));
		customerName1.setBounds(29, 208, 128, 28);
		moralsPanel.add(customerName1);
		
		customerName2 = new JLabel("Carla Pierre");
		customerName2.setFont(new Font("Dubai", Font.BOLD, 13));
		customerName2.setBounds(185, 215, 75, 21);
		moralsPanel.add(customerName2);
		
		customerName3 = new JLabel("Mr. & Mrs. Smith");
		customerName3.setFont(new Font("Dubai", Font.BOLD, 13));
		customerName3.setBounds(321, 215, 105, 21);
		moralsPanel.add(customerName3);
		
		customerComment1 = new JLabel("<html>I love MicroStarCable Vision they have all my favourite sports channels like ESPN, FOX and many more");
		customerComment1.setFont(new Font("Dubai", Font.PLAIN, 13));
		customerComment1.setBounds(29, 247, 128, 128);
		moralsPanel.add(customerComment1);
		
		customerComment2 = new JLabel("<html>Their Wi-Fi allows me to stay on top of my Instagram Game, Yeah!!!");
		customerComment2.setFont(new Font("Dubai", Font.PLAIN, 13));
		customerComment2.setBounds(185, 247, 114, 128);
		moralsPanel.add(customerComment2);
		
		customerComment3 = new JLabel("<html>When the Mrs. and I need some couples time, we just turn on the cartoon network and our kids are hooked for the rest of the day");
		customerComment3.setFont(new Font("Dubai", Font.PLAIN, 13));
		customerComment3.setBounds(321, 247, 134, 138);
		moralsPanel.add(customerComment3);
		
		moralsFooter = new JLabel("MicroStarCable Vision");
		moralsFooter.setFont(new Font("Dubai", Font.BOLD | Font.ITALIC, 14));
		moralsFooter.setBounds(307, 395, 148, 21);
		moralsPanel.add(moralsFooter);
		
		noServiceDetailPanel = new JPanel();
		noServiceDetailPanel.setBackground(new Color(255, 245, 238));
		noServiceDetailPanel.setBounds(10, 11, 463, 427);
		complaintsOptionsDetails.add(noServiceDetailPanel);
		noServiceDetailPanel.setLayout(null);
		
		JLabel nSServiceImage = new JLabel("");
		nSServiceImage.setIcon(new ImageIcon("images\\cell-tower.png"));
		nSServiceImage.setBounds(178, 0, 64, 64);
		noServiceDetailPanel.add(nSServiceImage);
		
		JLabel nSServiceDescription = new JLabel("<html>In the case that a customer experiences<br>a disruption in the services provided by us, we will");
		nSServiceDescription.setFont(new Font("Dubai", Font.PLAIN, 16));
		nSServiceDescription.setBounds(10, 63, 347, 75);
		noServiceDetailPanel.add(nSServiceDescription);
		
		JLabel nSCheckMark1 = new JLabel("");
		nSCheckMark1.setIcon(new ImageIcon("images\\check.png"));
		nSCheckMark1.setBounds(56, 149, 32, 32);
		noServiceDetailPanel.add(nSCheckMark1);
		
		JLabel nSCheckMark2 = new JLabel("");
		nSCheckMark2.setIcon(new ImageIcon("images\\check.png"));
		nSCheckMark2.setBounds(10, 241, 32, 32);
		noServiceDetailPanel.add(nSCheckMark2);
		
		JLabel nSCheckMark3 = new JLabel("");
		nSCheckMark3.setIcon(new ImageIcon("images\\check.png"));
		nSCheckMark3.setBounds(56, 328, 32, 32);
		noServiceDetailPanel.add(nSCheckMark3);
		
		JLabel nSService1 = new JLabel("<html>Representative will remotely check status of customer account.");
		nSService1.setFont(new Font("Dubai", Font.PLAIN, 15));
		nSService1.setBounds(106, 149, 347, 48);
		noServiceDetailPanel.add(nSService1);
		
		JLabel nSService2 = new JLabel("<html>A technician will visit the customer to asses the issue on site");
		nSService2.setFont(new Font("Dubai", Font.PLAIN, 15));
		nSService2.setBounds(56, 241, 376, 32);
		noServiceDetailPanel.add(nSService2);
		
		JLabel nSService3 = new JLabel("<html>Customers will be able to utilize our live chat for quicker responses");
		nSService3.setFont(new Font("Dubai", Font.PLAIN, 15));
		nSService3.setBounds(98, 328, 355, 48);
		noServiceDetailPanel.add(nSService3);
		
		JLabel nSFooter = new JLabel("MicroStarCable Vision");
		nSFooter.setFont(new Font("Dubai", Font.BOLD | Font.ITALIC, 14));
		nSFooter.setBounds(305, 395, 148, 21);
		noServiceDetailPanel.add(nSFooter);
		
		billComplaintDetailPanel = new JPanel();
		billComplaintDetailPanel.setBackground(new Color(255, 245, 238));
		billComplaintDetailPanel.setBounds(10, 11, 463, 427);
		complaintsOptionsDetails.add(billComplaintDetailPanel);
		billComplaintDetailPanel.setLayout(null);
		
		bCDescription = new JLabel("<html>In the case a customer was wrongly charged, MicroStarCable Vision will:");
		bCDescription.setFont(new Font("Dubai", Font.PLAIN, 16));
		bCDescription.setBounds(268, 11, 185, 106);
		billComplaintDetailPanel.add(bCDescription);
		
		bCServiceImage = new JLabel("");
		bCServiceImage.setIcon(new ImageIcon("images\\bad-review.png"));
		bCServiceImage.setBounds(194, 11, 64, 64);
		billComplaintDetailPanel.add(bCServiceImage);
		
		bCCheckMark1 = new JLabel("");
		bCCheckMark1.setIcon(new ImageIcon("images\\check.png"));
		bCCheckMark1.setBounds(207, 136, 32, 32);
		billComplaintDetailPanel.add(bCCheckMark1);
		
		bCCheckMark2 = new JLabel("");
		bCCheckMark2.setIcon(new ImageIcon("images\\check.png"));
		bCCheckMark2.setBounds(10, 265, 32, 32);
		billComplaintDetailPanel.add(bCCheckMark2);
		
		bCService1 = new JLabel("<html>Representative will remotely check status of customers account");
		bCService1.setFont(new Font("Dubai", Font.PLAIN, 15));
		bCService1.setBounds(60, 179, 349, 56);
		billComplaintDetailPanel.add(bCService1);
		
		bCService2 = new JLabel("<html>If we are at fault, the customer will be reimbursed on their next bill.");
		bCService2.setFont(new Font("Dubai", Font.PLAIN, 15));
		bCService2.setBounds(52, 288, 316, 56);
		billComplaintDetailPanel.add(bCService2);
		
		bCFooter = new JLabel("MicroStarCable Vision");
		bCFooter.setFont(new Font("Dubai", Font.BOLD | Font.ITALIC, 14));
		bCFooter.setBounds(305, 395, 148, 21);
		billComplaintDetailPanel.add(bCFooter);
		
		pDestructionDetailPanel = new JPanel();
		pDestructionDetailPanel.setBackground(new Color(255, 245, 238));
		pDestructionDetailPanel.setBounds(10, 11, 463, 427);
		complaintsOptionsDetails.add(pDestructionDetailPanel);
		pDestructionDetailPanel.setLayout(null);
		
		warningrImage = new JLabel("");
		warningrImage.setIcon(new ImageIcon("images\\warning.png"));
		warningrImage.setBounds(10, 21, 64, 64);
		pDestructionDetailPanel.add(warningrImage);
		
		warningDescription = new JLabel("<html>In the case of property damged caused by equipment belonging to MIcroStarCable Vision, we are obligated to provide these services");
		warningDescription.setFont(new Font("Dubai", Font.PLAIN, 16));
		warningDescription.setBounds(84, 21, 318, 98);
		pDestructionDetailPanel.add(warningDescription);
		
		pDCheckMark1 = new JLabel("");
		pDCheckMark1.setIcon(new ImageIcon("images\\check.png"));
		pDCheckMark1.setBounds(46, 130, 32, 32);
		pDestructionDetailPanel.add(pDCheckMark1);
		
		pDCheckMark2 = new JLabel("");
		pDCheckMark2.setIcon(new ImageIcon("images\\check.png"));
		pDCheckMark2.setBounds(88, 212, 32, 32);
		pDestructionDetailPanel.add(pDCheckMark2);
		
		pDCheckMark3 = new JLabel("");
		pDCheckMark3.setIcon(new ImageIcon("images\\check.png"));
		pDCheckMark3.setBounds(130, 306, 32, 32);
		pDestructionDetailPanel.add(pDCheckMark3);
		
		pDService1 = new JLabel("<html>A Technician will come on site, to asses the damage to the property.");
		pDService1.setFont(new Font("Dubai", Font.PLAIN, 15));
		pDService1.setBounds(88, 130, 365, 53);
		pDestructionDetailPanel.add(pDService1);
		
		pDService2 = new JLabel("<html>Customer, will be reimbursed if we are at fault by a fee deduction on next bill.");
		pDService2.setFont(new Font("Dubai", Font.PLAIN, 15));
		pDService2.setBounds(130, 212, 333, 46);
		pDestructionDetailPanel.add(pDService2);
		
		pDService3 = new JLabel("<html>10 Day free trial of our premium package.");
		pDService3.setFont(new Font("Dubai", Font.PLAIN, 15));
		pDService3.setBounds(172, 306, 281, 46);
		pDestructionDetailPanel.add(pDService3);
		
		pDFooter = new JLabel("MicroStarCable Vision");
		pDFooter.setFont(new Font("Dubai", Font.BOLD | Font.ITALIC, 14));
		pDFooter.setBounds(305, 395, 148, 21);
		pDestructionDetailPanel.add(pDFooter);
	
		scrollBar = new JScrollBar();
		scrollBar.setForeground(new Color(255, 245, 238));
		scrollBar.setBackground(new Color(255, 245, 238));
		scrollBar.setBounds(767, 11, 17, 449);
		contentPane.add(scrollBar);
		setVisible(true);
	}
}
