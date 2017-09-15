package gameSupport;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import MainPackage.DrawableObject;

/**
 * Renders the BombermanWorld on the GUI.
 * 
 * @author Yvette Weng, Jiaqi Fang, David Everhart.
 */

public class BombermanComponent extends JComponent {

	/**
	 * The number of pixels per game unit side.
	 */
	public static int SQUARE_SIZE = 64;
	public static int NUM_SQUARES_PER_SIDE = 15;
	private static final int FRAMES_PER_SECOND = 30;
	private static final long REPAINT_INTERVAL_MS = 1000 / FRAMES_PER_SECOND;

	private BombermanWorld world;

	
	/**
	 * Constructs a component for rendering the given BombermanWorld on the
	 * GUI.
	 * 
	 * @param world
	 */
	public BombermanComponent(BombermanWorld world) {
		this.world = world;
		setPreferredSize(BombermanFrame.GAME_DIMENSION);

		//The KeyboardAnimation is used to allow the hero to move diagonally
		KeyboardAnimation animation = new KeyboardAnimation(world, this, 24);
		animation.addAction("LEFT", -1, 0);
		animation.addAction("RIGHT", 1, 0);
		animation.addAction("UP", 0, -1);
		animation.addAction("DOWN", 0, 1);

		handleKeyEvent(world);
		// Creates a separate "thread of execution" to trigger periodic
		// repainting of this component.
		Runnable repainter = new Runnable() {
			@Override
			public void run() {
				// Periodically asks Java to repaint this component
				try {
					while (true) {
						Thread.sleep(REPAINT_INTERVAL_MS);
						repaint();

					}
				} catch (InterruptedException exception) {
					// Stop when interrupted
				}
			}
		};
		new Thread(repainter).start();
	}
	
	
	/**
	 * This method draws all of the DrawableObjects in the BombermanWorld
	 * on the given graphics area.
	 * 
	 * @param g
	 *            the graphics area on which to draw
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.green);
		g2.fillRect(0, 0, SQUARE_SIZE * NUM_SQUARES_PER_SIDE, SQUARE_SIZE * NUM_SQUARES_PER_SIDE);

		// Draws all of the DrawableObjects in the BombermanWorld
		for (DrawableObject object : this.world.getDrawable()) {
			object.draw(g2);
		}

		this.world.getHero().draw(g2);
		// Calls the timePassed method on all of the DrawableObjects in
		// BombermanWorld. This was placed here to prevent concurrent
		// modification exceptions.
		this.world.timePassed();
	}
	
	/**
	 * This method handles all of the keys being pressed by the user.
	 * These include keys for changing the level, dropping and detonating
	 * bombs, moving the hero, and pausing the game.
	 * 
	 * @param world
	 *            the BombermanWorld containing the game objects
	 */
	private void handleKeyEvent(final BombermanWorld world) {

		// Deals with level changing
		// U to move up in level
		// D to move down in level
		Action moveUpLevel = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				world.switchLevel(world.getCurrentLevel() + 1);
				repaint();
			}
		};

		Action moveDownLevel = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				world.switchLevel(world.getCurrentLevel() - 1);
				repaint();
			}
		};

		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('u'), "moveUpLevel");
		getActionMap().put("moveUpLevel", moveUpLevel);
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('d'), "moveDownLevel");
		getActionMap().put("moveDownLevel", moveDownLevel);

		
		// Deals with hero dropping bomb
		// The key for dropping a bomb is set to x
		Action putBomb = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				world.getHero().attack();
				repaint();
			}
		};

		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('x'), "putBomb");
		getActionMap().put("putBomb", putBomb);

		
		// Deals with hero detonating the bomb when powered up
		// The key for detonating the bomb is set to c
		Action detBomb = new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				world.getHero().detonate();
				repaint();
			}
		};

		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('c'), "detBomb");
		getActionMap().put("detBomb", detBomb);

		
		// Deals with pause/restart the game
		// Game is paused when p is pressed
		Action pause = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				world.setIsPaused(!world.getIsPaused());
			}
		};

		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('p'), "pause");
		getActionMap().put("pause", pause);
	}
}
