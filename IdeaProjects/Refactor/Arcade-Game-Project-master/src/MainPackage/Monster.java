package MainPackage;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import gameSupport.BombermanWorld;
import gameSupport.GridToPoint;

/**
 * The superclass for all types of monsters.
 * 
 * @author Yvette Weng, Jiaqi Fang, David Everhart.
 */
public class Monster extends Character {

	protected int xDirection;
	protected int yDirection;

	public Monster(BombermanWorld world, int x, int y) {
		super(world, x, y);
	}
	
	/**
	 * Makes a random move each time this method is called
	 * 
	 * @para current 
	 * 				the current grid position of monster itself
	 * 
	 * @return If successfully update the location, return true. Return false
	 *         otherwise.
	 */
	public void updateDirection(GridToPoint current) {
		ArrayList<Point> possibleDirections = getPossibleDirections(current);
		if (!possibleDirections.isEmpty()) {
			Point direction = possibleDirections.get((int) (Math.random() * possibleDirections.size()));
			this.xDirection = (int) direction.getX();
			this.yDirection = (int) direction.getY();
		}
	}
	
	/**
	 * Gets all the possible directions of the monster while checking 
	 * if there is a object in the way. 
	 * 
	 * @para current 
	 * 				the current grid position of monster itself
	 */
	public ArrayList<Point> getPossibleDirections(GridToPoint current) {
		ArrayList<Point> possibleDirections = new ArrayList<>();
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (Math.abs(i + j) == 1) {
					GridToPoint next = new GridToPoint(current.getX() + i, current.getY() + j);
					ArrayList<DrawableObject> objects = getWorld().getObjects(next, this);
					if (objects.isEmpty()) {
						possibleDirections.add(new Point(i, j));
					}
					else {
						boolean possible = true;
						for (DrawableObject object : objects) {
							if (object.collide(this)) {
								possible = false;
							}
						}
						if (possible) {
							possibleDirections.add(new Point(i, j));
						}
					}
				}
			}
		}
		return possibleDirections;
	}

	@Override
	public void timePassed() {

		Point2D gridCenter = GridToPoint.getCenterCoordinate(this.getGrid());
		if (this.getCenterpoint().getX() == gridCenter.getX() && this.getCenterpoint().getY() == gridCenter.getY()) {
			updateDirection(this.getGrid());
		}
		if (!updateLocation(this.xDirection, this.yDirection)) {
			this.reverseDirection();
		}

		if (checkHero(this.getCenterpoint().getX(), this.getCenterpoint().getY())) {
			this.getWorld().getHero().die();
		}
	}

	public void reverseDirection() {
		this.xDirection = -this.xDirection;
		this.yDirection = -this.yDirection;
	}

	/**
	 * Update the monster's location. The monster does not collide with
	 * destroyable bricks.
	 * 
	 * @para x the x component of the location of monster y the y component of
	 *       the location of monster
	 * 
	 * @return Return true if hero is detected. Return false otherwise.
	 */
	public boolean checkHero(double x, double y) {
		// Check whether is intersecing hero
		Hero hero = getWorld().getHero();
		Point2D.Double center = new Point2D.Double(x, y);
		if (center.distance(hero.getCenterpoint()) < (this.getRadius() + hero.getRadius())) {
			return true;
		}
		return false;
	}

	/**
	 * Check if the monster is colliding with a drawable object.
	 * 
	 * @para object The drawable object which the current is colliding with.
	 * 
	 * @return Return true if the monster is collided with a drawable object.
	 *         Return false otherwise.
	 */
	@Override
	boolean collide(DrawableObject object) {
		return object.collideWithMonster(this);
	}

	/**
	 * Check if the monster is colliding with another monster.
	 * 
	 * @para object The monster which the current is colliding with.
	 * 
	 * @return Return true if the monster is collided with another monster.
	 *         Return false otherwise.
	 */
	@Override
	boolean collideWithMonster(Monster object) {
		if (this.getCenterpoint().distance(object.getCenterpoint()) <= (this.getRadius() + object.getRadius())) {
			object.reverseDirection();
			return true;
		}
		return false;
	}

}
