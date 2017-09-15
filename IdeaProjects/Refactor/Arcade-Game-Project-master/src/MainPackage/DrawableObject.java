package MainPackage;

import java.awt.Graphics2D;

import gameSupport.BombermanComponent;
import gameSupport.BombermanWorld;
import gameSupport.GridToPoint;
import gameSupport.SupportFunctions;

/**
 * The abstract class that all of the game objects are based on.
 * 
 * @author Yvette Weng, Jiaqi Fang, David Everhart.
 */
public abstract class DrawableObject implements Temporal {

	private boolean destroyable;
	private boolean crossable;
	private BombermanWorld myWorld;
	public GridToPoint grid;

	/**
	 * Constructor for a new Drawable Object given a BombermanWorld.
	 * 
	 * @param world
	 *            The BombermanWorld the object will inhabit
	 */
	public DrawableObject(BombermanWorld world) {
		this(world, 0, 0);
	}

	/**
	 * Constructor for a new Drawable Object given a BombermanWorld and initial
	 * x and y coordinates.
	 * 
	 * @param world
	 *            The BombermanWorld the object will inhabit
	 * @param x
	 *            The starting x coordinate of the object's gridPoint
	 * @param y
	 *            The starting y coordinate of the object's gridPoint
	 */
	public DrawableObject(BombermanWorld world, int x, int y) {
		this.myWorld = world;
		this.setGrid(new GridToPoint(x, y));
	}

	/**
	 * Returns the DrawableObjects BombermanWorld
	 * 
	 * @return myWorld The BombermanWorld the object inhabits/has.
	 */
	public BombermanWorld getWorld() {
		return this.myWorld;
	}

	/**
	 * Sets the object to be destroyable or indestructable.
	 * 
	 * @param d
	 *            The boolean value that destroyable is to be set to
	 */
	public void setDestroyable(boolean d) {
		this.destroyable = d;
	}

	/**
	 * Returns whether the object is destroyable.
	 * 
	 * @return destroyable The boolean value is true if the object is
	 *         destroyable
	 */
	public boolean isDestroyable() {
		return this.destroyable;
	}

	/**
	 * Sets whether the object is crossable.
	 * 
	 * @param c
	 *            The boolean value that crossable is to be set to
	 */
	public void setCrossable(boolean c) {
		this.crossable = c;
	}

	/**
	 * Returns whether the object is crossable.
	 * 
	 * @return crossable The boolean value is true if the object is crossable
	 */
	public boolean isCrossable() {
		return this.crossable;
	}

	/**
	 * Returns the object's GridToPoint.
	 * 
	 * @return grid The DrawableObject's GridToPoint
	 */
	public GridToPoint getGrid() {
		return this.grid;
	}

	/**
	 * Sets the object to be destroyable or indestructable.
	 * 
	 * @param d
	 *            The boolean value that destroyable is to be set to
	 */
	public void setGrid(GridToPoint grid) {
		this.grid = grid;
	}

	/**
	 * Returns a string name for the shape of the object.
	 * 
	 * @return "rectangle" The default shape is rectangle
	 */
	public String getShape() {
		return "rectangle";
	}

	/**
	 * Is called to change the state of objects over time. Defaults to no
	 * action.
	 * 
	 */
	@Override
	public void timePassed() {
		// do nothing
	}

	/**
	 * Removes the object from the BomberManWorld.
	 * 
	 */
	@Override
	public void die() {
		getWorld().removeDrawable(this);
	}

	/**
	 * Draws the object on the BombermanComponent.
	 * 
	 * @param g2
	 *            The graphics area on which to draw
	 */
	public void draw(Graphics2D g2) {
		int x = (int) (GridToPoint.getTopLeftCoordinate(getGrid()).getX());
		int y = (int) (GridToPoint.getTopLeftCoordinate(getGrid()).getY());
		g2.drawImage(SupportFunctions.getImageWithText(getFileName()), x, y, BombermanComponent.SQUARE_SIZE,
				BombermanComponent.SQUARE_SIZE, null);
	}

	/**
	 * Returns the name of the image file.
	 *
	 * @return image file name - for example, Bomb.png
	 */
	public abstract String getFileName();

	// abstract boolean canBeMovedInto();
	// abstract void onBombDamage();
	// abstract void onPlayerCollision(DrawableObject arg);

	/**
	 * Returns a boolean indicating if a param object is blocked when it
	 * collides with the object. Used for double dispatch in subclasses.
	 * 
	 * @return false Defaults to return false
	 */
	boolean collide(DrawableObject object) {
		return false;
	}

	/**
	 * Returns a boolean indicating if an object is blocked when it collides
	 * with a hero. Used for double dispatch in subclasses.
	 * 
	 * @return false Defaults to return false
	 */
	boolean collideWithHero(Hero object) {
		return false;
	}

	/**
	 * Returns a boolean indicating if an object is blocked when it collides
	 * with a monster. Used for double dispatch in subclasses.
	 * 
	 * @return true Defaults to return true
	 */
	boolean collideWithMonster(Monster object) {
		return false;
	}

	/**
	 * Returns a boolean indicating if an object is blocked when it collides
	 * with a powerup. Used for double dispatch in subclasses.
	 * 
	 * @return false Defaults to return false
	 */
	boolean collideWithPowerup(PowerUp object) {
		return false;
	}

	/**
	 * Returns a boolean indicating if an object is blocked when it collides
	 * with a destructible brick. Used for double dispatch in subclasses.
	 * 
	 * @return true Defaults to return true
	 */
	public boolean collideWithDesBrick() {
		return true;
	}

	/**
	 * Returns a boolean indicating if an object is blocked when it collides
	 * with an indestructible brick. Used for double dispatch in subclasses.
	 * 
	 * @return true Defaults to return true
	 */
	public boolean collideWithUndesBrick() {
		return true;
	}

	/**
	 * Returns a boolean indicating if an object is blocked when it collides
	 * with a bomb. Used for double dispatch in subclasses.
	 * 
	 * @return true Defaults to return true
	 */
	public boolean collideWithBomb() {
		return true;
	}
}
