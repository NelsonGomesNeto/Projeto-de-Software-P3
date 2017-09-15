package gameSupport;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import MainPackage.BasicMonster;
import MainPackage.BiggerBombPowerUp;
import MainPackage.Bomb;
import MainPackage.BrickDestroyable;
import MainPackage.BrickUndestroyable;
import MainPackage.CrossWallMonster;
import MainPackage.DetonateOnCommandPowerUp;
import MainPackage.DrawableObject;
import MainPackage.ExtraLifePowerUp;
import MainPackage.Hero;
import MainPackage.Monster;
import MainPackage.MoreBombsPowerUp;
import MainPackage.TrackingMonster;
import panels.MainPage;

/**
 * The modeling environment for the game.
 * 
 * @author Yvette Weng, Jiaqi Fang, David Everhart.
 */
public class BombermanWorld {
	private static final long UPDATE_INTERVAL_MS = 15;
	private static final int MAX_LEVEL_NUMBER = 5;
	private String[] levelFileNames = { "Level1.txt", "Level2.txt", "Level3.txt", "Level4.txt", "Level5.txt",
			"Level6.txt" };

	private int currentLevelNumber;
	private ArrayList<DrawableObject> drawables = new ArrayList<>();
	private ArrayList<DrawableObject> drawToAdd = new ArrayList<>();
	private ArrayList<DrawableObject> drawToRemove = new ArrayList<>();
	private boolean isPaused = false;

	private Hero hero;

	/**
	 * Constructs a new world using loadLevel() at level 0.
	 * 
	 */
	public BombermanWorld() {
		this.currentLevelNumber = 0;
		loadLevel(this.levelFileNames[this.currentLevelNumber]);
		this.hero = new Hero(this, 1, 1);
	}

	/**
	 * Constructs a level based off of a text file.
	 * Based off of the characters in the text file,
	 * bricks, monsters, and powerups are added to the level.
	 * 
	 * @param fileName
	 * 			The name of the file to be read.
	 */
	private void loadLevel(String fileName) {
		this.drawables.clear();
		this.drawToAdd.clear();
		this.drawToRemove.clear();
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("Levels/" + fileName));
		} catch (FileNotFoundException exception) {
			throw new RuntimeException("Level file not found");
		}
		ArrayList<CrossWallMonster> crossMonsters = new ArrayList<>();
		// Scan the level file to draw the map
		for (int r = 0; r < BombermanComponent.NUM_SQUARES_PER_SIDE; r++) {
			String row = scanner.next();
			for (int c = 0; c < BombermanComponent.NUM_SQUARES_PER_SIDE; c++) {
				char grid = row.charAt(c);
				switch (grid) {
				case ('.'): // Add undestroyable brick
					BrickUndestroyable bu = new BrickUndestroyable(this, c, r);
					addDrawable(bu);
					break;
				case ('*'): // Add destroyable brick
					BrickDestroyable bd = new BrickDestroyable(this, c, r);
					addDrawable(bd);
					break;
				case ('m'): // Add basic monster
					BasicMonster basicMonster = new BasicMonster(this, c, r);
					addDrawable(basicMonster);
					break;
				case ('t'): // Add tracking monster
					TrackingMonster trackingMonster = new TrackingMonster(this, c, r);
					addDrawable(trackingMonster);
					break;
				case ('c'): // Add cross wall monster
					CrossWallMonster crossMonster = new CrossWallMonster(this, c, r);
					crossMonsters.add(crossMonster);
					break;
					// Add different power-ups
				case ('1'): // Creates a power-up that increases explosion size
					BiggerBombPowerUp biggerBomb = new BiggerBombPowerUp(this, c, r);
					BrickDestroyable bdPower1 = new BrickDestroyable(this, c, r);
					bdPower1.setPowerUp(biggerBomb);
					addDrawable(bdPower1);
					break;
				case ('2'): // Creates a power-up that increases the allowable number of bombs
					MoreBombsPowerUp moreBombs = new MoreBombsPowerUp(this, c, r);
					BrickDestroyable bdPower2 = new BrickDestroyable(this, c, r);
					bdPower2.setPowerUp(moreBombs);
					addDrawable(bdPower2);
					break;
				case ('3'): // Creates a power-up that allows controlled detonation of bombs
					DetonateOnCommandPowerUp detBomb = new DetonateOnCommandPowerUp(this, c, r);
					BrickDestroyable bdPower3 = new BrickDestroyable(this, c, r);
					bdPower3.setPowerUp(detBomb);
					addDrawable(bdPower3);
					break;
				case ('4'): // Creates a power-up that gives the hero an extra life
					ExtraLifePowerUp oneUp = new ExtraLifePowerUp(this, c, r);
					BrickDestroyable bdPower4 = new BrickDestroyable(this, c, r);
					bdPower4.setPowerUp(oneUp);
					addDrawable(bdPower4);
					break;
				default:
					break;
				}
			}
		}
		scanner.close();
		for (int i = 0; i < crossMonsters.size(); i++) {
			addDrawable(crossMonsters.get(i));
		}
	}

	/**
	 * Returns the hero object.
	 * 
	 * @return hero
	 */
	public Hero getHero() {
		return this.hero;
	}

	/**
	 * Returns the integer number of the current level.
	 * 
	 * @return currentLevelNumber
	 */
	public int getCurrentLevel() {
		return this.currentLevelNumber;
	}

	/**
	 * Switches the current level to a given level number.
	 * 
	 * @param levelNumber
	 * 			The number of the level to switch to
	 */
	public void switchLevel(int levelNumber) {
		if (levelNumber < 0 || levelNumber > MAX_LEVEL_NUMBER) {
			return;
		}
		loadLevel(this.levelFileNames[levelNumber]);
		this.currentLevelNumber = levelNumber;
		this.hero.resetToInitialPosition();
		this.hero.setBombNumber(this.hero.getMaxBombNumber());
	}

	/**
	 * Returns the list of DrawableObjects in the world
	 * 
	 * @return drawables
	 */
	public ArrayList<DrawableObject> getDrawable() {
		return this.drawables;
	}

	/**
	 * Adds a DrawableObject to the list of DrawableObjects
	 * that need to be added to the game.
	 * 
	 * @param item
	 * 			The DrawableObject to be added
	 */
	public synchronized void addDrawable(DrawableObject item) {
		this.drawToAdd.add(item);
	}

	/**
	 * Adds a DrawableObject to the list of DrawableObjects
	 * that need to be removed from the game.
	 * 
	 * @param item
	 * 			The DrawableObject to be romoved
	 */
	public synchronized void removeDrawable(DrawableObject item) {
		this.drawToRemove.add(item);
	}

	/**
	 * Returns a list of DrawableObjects occupying one grid space of the game.
	 * 
	 * @return objects
	 * 				The list of DrawableObjects on one grid space
	 */
	public synchronized ArrayList<DrawableObject> getObjects(GridToPoint grid, DrawableObject d) {
		ArrayList<DrawableObject> objects = new ArrayList<>();
		for (DrawableObject object : this.drawables) {
			if (object.getGrid().equals(grid) && object != d) {
				objects.add(object);
			}
		}
		return objects;
	}

	/**
	 * Calls the timePassed() method of all of the DrawableObjects
	 * and the hero in the world, adds any objects that need to be added
	 * removes objects that need to be removed, and changes the level if
	 * the player won.
	 * 
	 */
	public void timePassed() {
		if (!this.isPaused) {
			for (DrawableObject object : this.drawables) {
				object.timePassed();
			}
			this.hero.timePassed();
			this.drawables.removeAll(this.drawToRemove);
			this.drawToRemove.clear();
			this.drawables.addAll(this.drawToAdd);
			this.drawToAdd.clear();
			if (checkIfWin()) {
				switchLevel(getCurrentLevel() + 1);
			}
		}
	}

	/**
	 * Sets all of the monsters to their original
	 * position at the start of a level and removes
	 * any bombs from the level.
	 * 
	 */
	public void reset() {
		for (DrawableObject d : this.drawables) {
			if (d instanceof Monster) {
				((Monster) d).resetToInitialPosition();
			}
			else if (d instanceof Bomb) {
				((Bomb) d).remove();
			}
		}
	}
	
	/**
	 * Returns whether the player won the level (if all of 
	 * the monsters have been defeated).
	 * 
	 * @return true or false
	 * 			returns true if the player won and false otherwise
	 */
	public boolean checkIfWin() {
		int count = 0;
		for (DrawableObject d : this.drawables) {
			if (d instanceof Monster) {
				count++;
			}
		}
		if (count == 0) {
			if (this.getCurrentLevel() == MAX_LEVEL_NUMBER) {
				JOptionPane.showMessageDialog(null, "You won!");
				BombermanFrame.handleChangePanel(new MainPage());
			}
			return true;
		}
		return false;
	}

	/**
	 * Sets the boolean isPaused.
	 * 
	 * @param isPaused
	 * 			The boolean that isPaused is supposed to be set to
	 */
	public void setIsPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

	/**
	 * Returns whether the game should be paused currently.
	 * 
	 * @return isPaused
	 * 			The boolean value indicating if the game should be paused currently
	 */
	public boolean getIsPaused() {
		return this.isPaused;
	}

}
