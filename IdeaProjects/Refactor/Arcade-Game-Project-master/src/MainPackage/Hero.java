package MainPackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import gameSupport.BombermanFrame;
import gameSupport.BombermanWorld;
import gameSupport.GridToPoint;
import panels.MainPage;

/**
 * The game's main character's class
 * 
 * @author Yvette Weng, Jiaqi Fang, David Everhart.
 */
public class Hero extends Character {

	private int lives;
	private ArrayList<PowerUp> myPowerUps = new ArrayList<>();
	private ArrayList<Bomb> myDetBombs = new ArrayList<>();
	private boolean canDetonate = false;
	private int maxBombNumber = 1;
	private int bombNumber = this.getMaxBombNumber();
	private int bombSize = 1;

	public Hero(BombermanWorld world, int x, int y) {
		super(world, x, y);
		this.lives = 3;
		setSpeed(5);
	}
	
	/**
	 * Draw the hero image and the string showing the number of live the hero has. 
	 * 
	 * @para g2 Graphics2D
	 */
	@Override
	public void draw(Graphics2D g2) {
		super.draw(g2);
		g2.setColor(Color.white);
		Font font = new Font("Arial", Font.BOLD, 20);
		g2.setFont(font);
		g2.drawString("Lives: " + this.lives, 800, 40);
	}
	
	/**
	 * Puts a bomb down in the game. The bomb can only be put on a empty space. 
	 * After each attack, the bombNumber minus 1. 
	 * 
	 */
	@Override
	public void attack() {
		if (this.getBombNumber() <= 0) {
			return;
		}
		if (getWorld().getObjects(this.getGrid(), this).isEmpty()) {

			Bomb myBomb = new Bomb(this.getWorld(), this.getGrid().getX(), this.getGrid().getY());
			myBomb.setMaxRange(this.bombSize);
			myBomb.setDetOnCommand(this.canDetonate);
			if (this.canDetonate == true)
				this.addDetBomb(myBomb);
			this.getWorld().addDrawable(myBomb);
		}
		this.bombNumber--;
	}

	/**
	 * Detonates a bomb
	 * 
	 */
	public void detonate() {
		if (this.canDetonate && this.myDetBombs.size() > 0) {
			this.myDetBombs.get(0).die();
		}
	}
	
	/**
	 * Removes a detonatable bomb
	 * 
	 * @para bomb
	 * 				the detonatable bomb to be removed
	 */
	public void removeBomb(Bomb bomb) {
		if (this.myDetBombs.contains(bomb)) {
			this.myDetBombs.remove(bomb);
		}
	}
	
	/**
	 * Adds a detonatable bomb
	 * 
	 * @para bomb
	 * 				the detonatable bomb to be added
	 */
	public void addDetBomb(Bomb bomb) {
		this.myDetBombs.add(bomb);
	}

	/**
	 * Adds a powerup
	 * 
	 * @para pow
	 * 				the powerup to be added
	 */
	public void addPowerUp(PowerUp pow) {
		this.myPowerUps.add(pow);
		pow.activate();
	}
	
	/**
	 * Removes a powerup
	 * 
	 * @para bomb
	 * 				the powerup to be removed
	 */
	public void removePowerUp(PowerUp pow) {
		this.myPowerUps.remove(pow);
		pow.deactivate();
	}

	/**
	 * Adds one extra life to the hero
	 * 
	 */
	public void addLife() {
		this.lives++;
	}


	public void setMaxBombNumber(int num) {
		int difference = num - this.maxBombNumber;
		this.bombNumber += difference;
		this.maxBombNumber = num;
	}

	public int getMaxBombNumber() {
		return this.maxBombNumber;
	}

	public int getBombNumber() {
		return this.bombNumber;
	}

	public void addToBombNumber(int add) {
		this.bombNumber += add;
	}

	public void setBombSize(int size) {
		this.bombSize = size;
	}

	public void setCanDetonate(boolean set) {
		this.canDetonate = set;
	}

	/**
	 * Delets all the detonatable bombs. 
	 * 
	 */
	public void resetMyDetBombs() {
		for (Bomb b : this.myDetBombs) {
			b.setDetOnCommand(false);
		}
		this.myDetBombs.clear();
	}

	/**
	 * Each time hero collide with exposion or monsters, hero's number of live minus one. 
	 * When hero's number of live gets to zero, the whole game ends. 
	 * 
	 */
	@Override
	public void die() {
		if (this.lives == 1) {
			this.getWorld().setIsPaused(true);
			JOptionPane.showMessageDialog(null, "You Lost.");
			BombermanFrame.handleChangePanel(new MainPage());
			return;
		}
		this.lives--;
		setCenterpoint(GridToPoint.getCenterCoordinate(new GridToPoint(1, 1)));
		setGrid(new GridToPoint(1, 1));
		this.getWorld().reset();
		while (this.myPowerUps.size() > 0) {
			this.removePowerUp(this.myPowerUps.get(0));
		}
		this.bombNumber = this.maxBombNumber;
	}


	@Override
	public boolean collide(DrawableObject object) {
		return object.collide(this);
	}


	@Override
	public String getFileName() {
		return "Hero1.png";
	}

	public void setBombNumber(int bombNumber) {
		this.bombNumber = bombNumber;
	}

}
