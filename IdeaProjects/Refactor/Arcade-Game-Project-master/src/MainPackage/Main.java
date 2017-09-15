package MainPackage;
import javax.swing.JFrame;

import gameSupport.BombermanFrame;

/**
 * The main class for your arcade game.
 * 
 * You can design your game any way you like, but make the game start
 * by running main here.
 * 
 * Also don't forget to write javadocs for your classes and functions!
 * 
 * @author Buffalo
 *
 */
public class Main {

	/**
	 * Starts the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new BombermanFrame();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
