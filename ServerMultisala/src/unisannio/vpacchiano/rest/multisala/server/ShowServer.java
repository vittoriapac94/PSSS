package unisannio.vpacchiano.rest.multisala.server;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;


public class ShowServer {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public ShowServer() {
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 300, 109);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label = new JLabel("Chiudi la finestra per spegnere il server");
		panel.add(label);
		
		JLabel lblNewLabel = new JLabel("Server in esecuzione");
		frame.getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
	}

}
