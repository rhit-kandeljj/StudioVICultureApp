package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Class: Main
 * 
 * @author Richelle Elkes <br>
 *         Purpose: Top level class for Studio VI Project containing Main method
 *         <br>
 *         Restrictions: None
 */
public class Main {

	// Instantiated Variables
	private static final int SCREEN_WIDTH = 1520;
	private static final int SCREEN_HEIGHT = 820;
	private Color BACKGROUND_COLOUR = Color.BLACK;

	/**
	 * Ensures: The program is ran
	 *
	 * <br>
	 * Requires: The Frame and subsequent components are created and displayed
	 * 
	 * @throws IOException
	 */
	private void runApp() {

		// Ensures the program is starting
		System.out.println("Running Program");

		JFrame frame = new JFrame("Counting the Omer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel mainButtonPanel = new JPanel(new BorderLayout());
		mainButtonPanel.setBackground(BACKGROUND_COLOUR);
		frame.add(mainButtonPanel);

		new HomeScreen(frame);

		frame.setMinimumSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));

//		frame.pack();
		frame.setVisible(true);
	}// runApp

	/**
	 * Ensures: Runs the program
	 * 
	 * @param Arguments not used
	 * @throws IOException
	 * @throws InvalidLevelFormatException (eventually, will add in later)
	 */
	public static void main(String[] args) {
		Main mainApp = new Main();
		mainApp.runApp();

	}// runApp
}// Main
