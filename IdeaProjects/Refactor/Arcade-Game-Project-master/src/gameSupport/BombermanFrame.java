package gameSupport;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import panels.MainPage;

/**
 * Creates a GUI for the game, with a JPanel on a JFrame.
 * 
 * @author Yvette Weng, Jiaqi Fang, David Everhart.
 */
public class BombermanFrame extends JFrame{
	
	public static final Dimension GAME_DIMENSION = new Dimension(960, 960);
	private static JPanel PANEL = new JPanel();
	public BombermanFrame() {
		setTitle("Bomberman - David Everhart, Jiaqi Fang, Yvette Weng");
		
		PANEL.add(new MainPage());
		add(PANEL);
		setResizable(true);
		setVisible(true);
		pack();
	}
	
	/**
	 * Changes the component displayed on the JPanel.
	 * This is used anytime the game changes displays, e.g.
	 * when the user desides to start the game or loses a game.
	 * 
	 * @param component
	 * 			The Component to switch to on the GUI.
	 */
	public static void handleChangePanel(Component component) {
		PANEL.removeAll();
		PANEL.add(component);
		PANEL.revalidate();
		PANEL.repaint();
	}
	
	/**
	 * Adds a Component to the JPanel
	 * 
	 * @param component
	 * 			The Component to add to the GUI.
	 */
	public static void addComponent(Component component) {
		PANEL.add(component);
	}
	
	/**
	 * Removes a Component from the JPanel
	 * 
	 * @param component
	 * 			The Component to remove from the GUI.
	 */
	public static void removeComponent(Component component) {
		PANEL.remove(component);
	}
}
