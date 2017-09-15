package gameSupport;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.Timer;

/**
 * Smoothly moves the hero around the game panel.
 * 
 * @author Yvette Weng, Jiaqi Fang, David Everhart.
 */
public class KeyboardAnimation implements ActionListener{

	private final static String PRESSED = "pressed ";
	private final static String RELEASED = "released ";
	
	private BombermanWorld world;
	private BombermanComponent component;
	private Timer timer;
	private Map<String, Point> pressedKeys = new HashMap<String, Point>();
	
	/**
	 * Creates a KeyBoardAnimation given a BombermanWorld, BombermanComponent, and time delay.
	 * 
	 * @param world
	 * 			The BombermanWorld occupied by the hero
	 * @param component
	 * 			The BombermanComponent that receives input from the user
	 * @param delay
	 * 			The between event delay for the Timer
	 */
	public KeyboardAnimation(BombermanWorld world, BombermanComponent component, int delay) {
		this.world = world;
		this.component = component;
		this.timer = new Timer(delay, this); //This timer will send commands to the action listener every few miliseconds
		this.timer.setInitialDelay(0);
	}
	
	/**
	 * Creates a KeyBoardAnimation given a BombermanWorld, BombermanComponent, and time delay.
	 * 
	 * @param keyStroke
	 * 			The String for the key to be pressed
	 * @param xDirection
	 * 			The value for the x direction of travel
	 * @param yDirection
	 * 			The value for the y direction of travel
	 */
	public void addAction(String keyStroke, int xDirection, int yDirection) {
	
		int offset = keyStroke.lastIndexOf(" ");
		String key = offset == -1 ? keyStroke :  keyStroke.substring( offset + 1 ); //if statement to determine the value of key
		String modifiers = keyStroke.replace(key, "");

		InputMap inputMap = component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = component.getActionMap();

		Action pressedAction = new AnimationAction(key, new Point(xDirection, yDirection));
		String pressedKey = modifiers + PRESSED + key;
		KeyStroke pressedKeyStroke = KeyStroke.getKeyStroke(pressedKey);
		inputMap.put(pressedKeyStroke, pressedKey);
		actionMap.put(pressedKey, pressedAction);

		Action releasedAction = new AnimationAction(key, null);
		String releasedKey = modifiers + RELEASED + key;
		KeyStroke releasedKeyStroke = KeyStroke.getKeyStroke(releasedKey);
		inputMap.put(releasedKeyStroke, releasedKey);
		actionMap.put(releasedKey, releasedAction);
		
	}
	
	/**
	 * Creates a KeyBoardAnimation given a BombermanWorld, BombermanComponent, and time delay.
	 * 
	 * @param key
	 * 			The string name of the key being pressed
	 * @param direction
	 * 			The Point containing the direction information
	 */
	private void handleKeyEvent(String key, Point direction) {
		if (direction == null) {
			this.pressedKeys.remove(key);
		}
		else {
			this.pressedKeys.put(key, direction);
		}
		
		if (this.pressedKeys.size() == 1) {
			this.timer.start();
		}
		
		if (this.pressedKeys.size() == 0) {
			this.timer.stop();
		}
	}
	
	/**
	 * Calls the moveComponent method when pressedKeys is not empty.
	 * 
	 * @param e
	 * 			The ActionEvent caused by a key being pressed
	 */
	public void actionPerformed(ActionEvent e) {
		moveComponent();
	}
	
	/**
	 * Moves the hero using the list of keys that have been pressed.
	 * 
	 */
	private void moveComponent() {
		int xDirection = 0;
		int yDirection = 0;
		
		for (Point direction : this.pressedKeys.values()) {
			xDirection += direction.getX();
			yDirection += direction.getY();
		}
		this.world.getHero().updateLocation(xDirection, yDirection);
	}
	
	/**
	 * Extends AbstractAction and implements ActionListener to allow for actions
	 * when keys are pressed.
	 * 
	 */
	private class AnimationAction extends AbstractAction{
		private Point direction;
		
		/**
		 * Creates a new AnimationAction given a key and direction
		 * 
		 * @param key
		 * 			The String for the key being pressed
		 * @param direction
		 * 			The direction to travel in Point form
		 */
		public AnimationAction(String key, Point direction) {
			super(key);
			this.direction = direction;
		}
		
		/**
		 * Calls handleKeyEvent
		 * 
		 * @param e
		 * 			The ActionEvent caused by a key being pressed.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			handleKeyEvent((String)getValue(NAME), direction);
		}
	}
	
}
