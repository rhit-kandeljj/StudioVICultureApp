package Main;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Class: ButtonListenerImplemented
 * 
 * @author Richelle Elkes <br>
 *         Purpose: Class that will handle the individual actions for the Button
 *         <br>
 *         Restrictions: None
 */
public class ButtonListenerImplemented extends ButtonListener {

	// Instantiated Variables
	private String buttonID;

	JFrame frame;
	JButton currentButton;

	public ButtonListenerImplemented(JButton button, JFrame mainFrame) {
		super(button, mainFrame);
		this.currentButton = button;
		this.frame = mainFrame;
		buttonID = currentButton.getName();
	}

	@Override
	public void handleButtonPress(ActionEvent e) {

		if (buttonID.equals("Start Counting")) {
			new StartCountingLayout(frame);
		}

		if (buttonID.equals("About")) {
			new AboutLayout(frame);
		}

		if (buttonID.equals("Help")) {
			new HelpLayout(frame);
		}

		if (buttonID.equals("Home")) {
			new HomeScreen(frame);
		}

		if (buttonID.equals("Previous Day")) {

		}

		if (buttonID.equals("Next Day")) {

		}

		// Testing DayLayout, DayLayoutList, DayContent
		for (int i = 1; i < 50; i++) {
			if (buttonID.equals(String.valueOf(i))) {
				frame.add(new DayLayoutList(frame, i));
				break;
			}
		}

	}// handleButtonPress
}// ButtonListenerImplemented
