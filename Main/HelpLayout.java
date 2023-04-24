package Main;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Class: HelpLayout
 * 
 * @author Richelle Elkes <br>
 *         Purpose: Creates a JText Area and chat bot for user to be able to
 *         interact with when receiving help <br>
 *         Restrictions: None
 */
public class HelpLayout {

	// Instantiated Variables & Components
	private static final int SCREEN_WIDTH = 1520;
	private static final int SCREEN_HEIGHT = 820;
	private static final int CONSOLE_WIDTH = (int) (SCREEN_WIDTH / 2.5);
	private static final int CONSOLE_HEIGHT = SCREEN_HEIGHT - 28;
	private int s = 8;
	private int lastDayGlobal = DayLayoutList.lastDayGlobal;
	private String botReply;
	private String userReply = "";
	private ArrayList<String> chatHistory = new ArrayList<>();
	private HashMap<Integer, DayContent> dayContentHashMap = DayLayoutList.dayContentHashMap;
	private static final Color BACKGROUND_COLOUR = new Color(232, 244, 253);
	private JFrame frame;
	private JPanel mainPanel;
	private JPanel rightPanel;;
	private JTextArea helpConsole;
	private JScrollPane consolePane;
	private JLabel commandLabel;
	private JButton home;

	public HelpLayout(JFrame mainFrame) {

		// Main Frame
		this.frame = mainFrame;
		frame.getContentPane().setBackground(BACKGROUND_COLOUR);
		frame.setVisible(true);

		// Home Button
		home = new JButton() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setColor(Color.BLACK);
				g2.setStroke(new BasicStroke(3));
				g2.translate(SCREEN_WIDTH / 2, 37);
				g2.translate(0, -2 * Math.pow(3, 0.5) * s);
				g2.draw(new Line2D.Double(0, 0, -3 * s, 3 * Math.pow(3, 0.5) * s));
				g2.draw(new Line2D.Double(0, 0, 3 * s, 3 * Math.pow(3, 0.5) * s));
				g2.translate(-3 * s, 3 * Math.pow(3, 0.5) * s);
				g2.drawLine(0, 0, 6 * s, 0);
				g2.translate(3 * s, Math.pow(3, 0.5) * s);
				g2.draw(new Line2D.Double(0, 0, -3 * s, -3 * Math.pow(3, 0.5) * s));
				g2.draw(new Line2D.Double(0, 0, 3 * s, -3 * Math.pow(3, 0.5) * s));
				g2.translate(-3 * s, -3 * Math.pow(3, 0.5) * s);
				g2.drawLine(0, 0, 6 * s, 0);
				g2.translate(3 * s, Math.pow(3, 0.5) * s);
				g2.translate(-SCREEN_WIDTH / 2, -37);
			}
		};

		home.setName("Home");
		home.addActionListener(new ButtonListenerImplemented(home, frame));
		home.setPreferredSize(new Dimension(SCREEN_WIDTH, 75));
		home.setOpaque(false);
		home.setContentAreaFilled(false);
		home.setBorderPainted(false);
		frame.add(home, BorderLayout.NORTH);

		// Main Panel
		mainPanel = new JPanel();
		mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		mainPanel.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT - 75));
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setVisible(true);
		mainPanel.setBackground(Color.WHITE);
		frame.add(mainPanel);

		// Console Pane
		consolePane = new JScrollPane(); // Pane will have scroll function if enough lines are used
		consolePane.setPreferredSize(new Dimension(CONSOLE_WIDTH, CONSOLE_HEIGHT));
		consolePane.setBackground(Color.white);

		// Console
		helpConsole = new JTextArea(); // New TextArea where the user can freely type
		helpConsole.setFont(new Font(helpConsole.getText(), Font.PLAIN, 16));
		helpConsole.setText("Welcome to the Help Console!\n");
		helpConsole.setWrapStyleWord(true);
		helpConsole.setLineWrap(true);
		helpConsole.setEditable(true);
		helpConsole.setHighlighter(null);
		helpConsole.setBackground(Color.WHITE);
		helpConsole.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
		consolePane.setViewportView(helpConsole); // Makes it viewable
		mainPanel.add(consolePane, BorderLayout.WEST);

		// Right Panel
		rightPanel = new JPanel();
		rightPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Add a 5 pixel border around the panel
//		rightPanel.setPreferredSize(new Dimension((int) (SCREEN_WIDTH / 1.6725), CONSOLE_HEIGHT));
		rightPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		rightPanel.setBackground(Color.white);
		mainPanel.add(rightPanel, BorderLayout.CENTER);

		commandLabel = new JLabel("<html> <b> Bot Commands: </b>" + "<br>" + "<br>" + " <b> NAVIGATE </b>" + "<br>"
				+ "Will return instructions on how to use this application" + "<br>" + "<br>" + "<b> FORGOT </b>"
				+ "<br>" + "Will return the user's previous day according to the system" + "<br>" + "<br>"
				+ " <b> SEARCH (Insert Space) TERM </b>" + "<br>"
				+ "Will return lessons that include the user's search term (ie. 'Search prayer' will return Days 29 and 40)"
				+ "<br>" + "<br>" + "<b> LOADDAY (Insert Space) NUMBER </b>" + "<br>"
				+ "Will return the associated Lesson, Activity, and Blessing for the Day specified (ie. 'LoadDay 36')"
				+ "<br>" + "<br>" + "<b> CHARACTERISTIC (Insert Space) CHARACTER </b>" + "<br>"
				+ "Will return the days that exhibit the characteristic the user input (ie. 'Characteristic Chesed')"
				+ "<br>" + "<br>"
				+ "<b> <i> **NOTE: </b> <i> To allow for full functionality of the HelpBot, specifically search features, please ensure you </i>"
				+ "<br>"
				+ " <i> have clicked into a 'Day' from the 'Start Counting' screen on the homepage. Thank you! </i> </html>");
		commandLabel.setFont(new Font(commandLabel.getText(), Font.PLAIN, 16));
		rightPanel.add(commandLabel);

		frame.revalidate();
		frame.repaint();

		// KeyListener
		helpConsole.addKeyListener(new KeyAdapter() {
			// KeyListener method
			public void keyPressed(KeyEvent e) {
				// Get the current caret position

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// Get the user's input from the new variable
					String input = userReply.trim().toUpperCase(); // Only saves the newest line input by the user

					botReply = getDefaultResponse(input);
					chatHistory.add(input);
					chatHistory.add(botReply);
					helpConsole.append("\n" + botReply + "\n"); // Appending previous
					// chat history and
					// newest
					// lines
					helpConsole.setText(helpConsole.getText().trim()); // Remove gaps in console

					// Clear after adding to chatHistory (otherwise would append)
					userReply = "";
				} else if (Character.isLetterOrDigit(e.getKeyChar())) { // Only accepts letters or numbers
					userReply += e.getKeyChar();
				} else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) { // Check if Backspace key is pressed
					e.consume();
				} else if (e.getKeyCode() == KeyEvent.VK_DELETE) { // Check if Delete key is pressed
					e.consume();
				}
			}// keyPressed
		});
	}// HelpLayout

	public String getDefaultResponse(String user) {

		switch (user.toUpperCase()) { // Switch cases
		case "FORGOT":
			botReply = "According to my system, the last day you were on was Day " + lastDayGlobal;
			break;
		case "":
			botReply = "Please enter a valid command, so that I can assist you.";
			break;
		case "HI":
			botReply = "Greetings!";

			break;
		case "HELLO":
			botReply = "Greetings!";
			break;
		case "HELP":
			botReply = "I would be happy to assist you! Please enter a valid command.";
			break;
		case "QUIT":
			botReply = "Ok. Goodbye.";
		case "BYE":
			botReply = "Ok. Goodbye.";
			break;
		default:
			botReply = "Please try again, I did not find a valid command in your message.";
			break;
		}

		if (user.contains("LOADDAY")) {

			String userSearch = user.substring(user.indexOf("loadday") + 8).trim().toLowerCase();
			botReply = "The Lesson, Activity, and Blessing for Day '" + userSearch + "' is: ";
			int day = Integer.parseInt(userSearch);
			if (day < 1) {
				botReply = "Sorry, the number entered is not valid. Please try again! '";
			} else if (day >= 50) {
				botReply = "Sorry, the number entered is not valid. Please try again! '";
			}
			DayContent dayContent = dayContentHashMap.get(day);
			String jText = dayContent.getTextArea().getText();
			jText = jText.replaceAll("<html>", "");
			jText = jText.replaceAll("</html>", "");
			jText = jText.replaceAll("<b>", "");
			jText = jText.replaceAll("</b>", "");
			jText = jText.replaceAll("<br>", "");
			jText = jText.replaceAll("</br>", "");
			jText = jText.replaceAll("Lesson", " ");
			jText = jText.replaceAll("Activity", " ");
			jText = jText.replaceAll("Blessing", "");
			botReply += jText;
		}

		if (user.contains("CHARACTERISTIC"))

		{
			String userSearch = user.substring(user.indexOf("characteristic") + 15).trim().toLowerCase();
			userSearch = userSearch.substring(0, 1).toUpperCase() + userSearch.substring(1).toLowerCase();
			boolean found = false;
			botReply = "The days that exhibit the characteristic '" + userSearch + "' are: ";
			for (int i = 1; i < 50; i++) {
				DayContent dayContent = dayContentHashMap.get(i);
				if (dayContent.getLabel().getText().contains(userSearch)) {
					botReply += i + "  ";
					found = true;
				}
			}
			if (!found) {
				botReply = "Sorry, I could not find any days that exhibited the characteristic '" + userSearch + ".'";
			}
		}

		if (user.contains("SEARCH")) {
			String userSearch = user.substring(user.indexOf("search") + 7).trim().toLowerCase();
			boolean found = false;
			botReply = "The days that include '" + userSearch + "' in the lesson are: ";
			for (int i = 1; i < 50; i++) {
				DayContent dayContent = dayContentHashMap.get(i);
				if (dayContent.getTextArea().getText().toLowerCase().contains(userSearch)) {
					botReply += i + " ";
					found = true;
				}
			}
			if (!found) {
				botReply = "Sorry, I could not find any days that include '" + userSearch + "' in the lesson.";
			}
		}
		return botReply;

	}// getDefaultResponse
}// HelpLayout