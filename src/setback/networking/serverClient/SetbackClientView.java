package setback.networking.serverClient;

import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This class is the GUI that the player will see
 * when they play a game of Setback.  The action
 * takes place in the SetbackClient, but the
 * SetbackClientView is where the reaction happens.
 * @author Michael
 * @version Jan 1, 2014
 */
public class SetbackClientView {

	private JFrame frame;
	private JLabel testLabel;
	private JButton testButton;

	/**
	 * Create the application.
	 */
	public SetbackClientView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		testLabel = new JLabel();
		frame.getContentPane().add(testLabel, BorderLayout.CENTER);
		
		testButton = new JButton("Player One");
		testButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		frame.getContentPane().add(testButton, BorderLayout.NORTH);
		frame.setVisible(true);
	}
	
	/**
	 * This is the function that the SetbackClient
	 * will call when it receives input from the Server.
	 * The GUI will be updated accordingly.
	 * @param input The String sent from the Server.
	 */
	public void update(String input) {
		testLabel.setText(input);
		frame.repaint();
	}

}
