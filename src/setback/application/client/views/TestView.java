/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.client.views;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author Michael Burns
 * @version Jan 5, 2014
 */
public class TestView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestView frame = new TestView();
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
	public TestView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(60, 179, 113));
		contentPane.setLayout(null);
		contentPane.setBounds(100, 100, 900, 600);
		setContentPane(contentPane);
		
		JButton passButton = new JButton("Pass");
		passButton.setEnabled(false);
		passButton.setBounds(170, 250, 75, 75);
		contentPane.add(passButton);
		
		JButton twoButton = new JButton("Two");
		twoButton.setEnabled(false);
		twoButton.setBounds(255, 250, 75, 75);
		contentPane.add(twoButton);
		
		JButton threeButton = new JButton("Three");
		threeButton.setEnabled(false);
		threeButton.setBounds(340, 250, 75, 75);
		contentPane.add(threeButton);
		
		JButton fourButton = new JButton("Four");
		fourButton.setEnabled(false);
		fourButton.setBounds(425, 250, 75, 75);
		contentPane.add(fourButton);
		
		JButton fiveButton = new JButton("Five");
		fiveButton.setEnabled(false);
		fiveButton.setBounds(510, 250, 75, 75);
		contentPane.add(fiveButton);
		
		JButton takeButton = new JButton("Take");
		takeButton.setEnabled(false);
		takeButton.setBounds(595, 250, 75, 75);
		contentPane.add(takeButton);
	}
}
