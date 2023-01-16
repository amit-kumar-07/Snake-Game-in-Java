package Snake;

import java.awt.Color;

import javax.swing.JFrame;

public class Main1 {

	public static void main(String[] args) {

		JFrame frame = new JFrame("Snake Game(Amit kumar)");

		
		frame.setBounds(10, 10, 905, 670); // location and size // 700
		frame.setResizable(false); // user can't resize frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GamePanel panel = new GamePanel(); //GamePanel object
		panel.setBackground(Color.DARK_GRAY);
		
		frame.add(panel); // add panel to the frame // Extend in game panel in new class

		frame.setVisible(true); // after applying frame will visible ans show
	}

}

//Amit Kumar
