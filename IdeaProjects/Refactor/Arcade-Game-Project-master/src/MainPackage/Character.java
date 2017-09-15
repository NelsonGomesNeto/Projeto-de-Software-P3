package MainPackage;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.HashMap;

import gameSupport.BombermanComponent;
import gameSupport.BombermanWorld;
import gameSupport.GridToPoint;
import gameSupport.SupportFunctions;

/**
 * The superclass for all characters, including hero and monsters. 
 * 
 * @author Yvette Weng, Jiaqi Fang, David Everhart.
 */
public class Character extends DrawableObject implements Movable {

	private Point2D centerpoint;
	private int speed;
	private double radius;
	private int initialX;
	private int initialY;
	
	/**
	 * 
	 * Creates a Character in BombermanWorld at the center of the grid (x, y),
	 * stores the starting grid so that when the Hero dies, every Character 
	 * (Monsters and Hero) is reset to the initial position.
	 *
	 * @param world
	 * @param x
	 * @param y
	 */

	public Character(BombermanWorld world, int x, int y) {
		super(world, x, y);
		this.initialX = x;
		this.initialY = y;
		setCrossable(false);
		setDestroyable(true);
		this.centerpoint = GridToPoint.getCenterCoordinate(getGrid());
		this.radius = BombermanComponent.SQUARE_SIZE / 2;
	}
	
	/**
	 * Draws the Character's image at its centerpoint
	 */

	@Override
	public void draw(Graphics2D g2) {
		int x = (int) (centerpoint.getX() - BombermanComponent.SQUARE_SIZE / 2);
		int y = (int) (centerpoint.getY() - BombermanComponent.SQUARE_SIZE / 2);
		g2.drawImage(SupportFunctions.getImageWithText(getFileName()), x, y, BombermanComponent.SQUARE_SIZE,
				BombermanComponent.SQUARE_SIZE, null);
	}

	/**
	 * Overriden in Hero class, Monster does not have active attack skills (for now...)
	 */
	@Override
	public void attack() {
		// TODO Auto-generated method stub.

	}

	/**
	 * @return Charater's speed
	 */
	@Override
	public int getSpeed() {
		return this.speed;
	}

	/**
	 * Sets Character's speed
	 * 
	 * @param speed
	 */
	@Override
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * Updates character's location by one pixel at a time, up to its speed,
	 * depending on whether or not it can move any further. When moving diagonally,
	 * will be able to move in one direction if another is not possible.
	 * 
	 * @param xDirection
	 *            1 for right, -1 for left
	 * @param yDirection
	 *            1 for down, -1 for up
	 * @return true if location was changed, false otherwise.
	 */
	@Override
	public boolean updateLocation(int xDirection, int yDirection) {
		if (this.getWorld().getIsPaused()) {
			return false;
		}
		boolean movingInOtherDirection = false;
		if (Math.abs(xDirection + yDirection) != 1) {
			movingInOtherDirection = true;
		}
		for (int i = 0; i < this.speed; i++) {
			boolean canMoveX = false;
			boolean canMoveY = false;
			if (this.canMove("x", xDirection, movingInOtherDirection)) {
				canMoveX = true;
			}
			if (this.canMove("y", yDirection, movingInOtherDirection)) {
				canMoveY = true;
			}
			double x = this.centerpoint.getX();
			double y = this.centerpoint.getY();
			if (canMoveX) {
				x += xDirection;
			}
			if (canMoveY) {
				y += yDirection;
			}
			this.centerpoint = new Point2D.Double(x, y);
			this.setGrid(GridToPoint.getGridFromPoint(this.centerpoint));
		}

		return true;
	}

	/**
	 * Determines if a character can move.
	 * 
	 * For Monster: only checks the grid that it's moving towards.
	 * 
	 * For Hero: additionally checks the two grids (perpendicular to the axis
	 * 			in the parameter) neighboring the grid that the Hero is moving towards
	 * 
	 * @param axis
	 * 			"x" or "y"
	 * @param direction
	 * 			1 or -1
	 * @param movingInOtherDirection
	 * 			Only applicable for Hero, true if moving diagonally, false otherwise
	 * @return true if can move, false otherwise
	 */
	@Override
	public boolean canMove(String axis, int direction, boolean movingInOtherDirection) {

		if (direction == 0) {
			return true;
		}
		
		//Determines the x, y increments of grids to check in relation to this.grid
		int[] x = new int[] { direction, direction, direction };
		int[] y = new int[] { -1, 0, 1 };
		if (axis.equals("y")) {
			int[] temp = x;
			x = y;
			y = temp;
		}
		
		//Creates a HashMap with each grid and an ArrayList of the objects in that grid
		HashMap<GridToPoint, ArrayList<DrawableObject>> gridsWithObjects = new HashMap<>();
		GridToPoint[] grids = new GridToPoint[3];
		GridToPoint myGrid = this.getGrid();
		for (int i = 0; i < 3; i++) {
			grids[i] = new GridToPoint(x[i] + myGrid.getX(), y[i] + myGrid.getY());
			gridsWithObjects.put(grids[i], getWorld().getObjects(grids[i], this));
		}
		int range = BombermanComponent.SQUARE_SIZE / 2;

		//Checks for the immediate grid the character is moving towards
		ArrayList<DrawableObject> objects = gridsWithObjects.get(grids[1]);
		boolean midCrossable = true;
		if (!objects.isEmpty()) {
			for (DrawableObject object : objects) {
				//if the object is brick
				if (object.getShape().equals("rectangle")) {
					if (object.collide(this)) {
						midCrossable = false;
						int bound = 0;
						if (x[1] != 0) {
							bound = (int) GridToPoint.getCenterCoordinate(myGrid).getX() + x[1] * range;
							if (Math.abs(this.centerpoint.getX() - bound) <= range) {
								return false;
							}
						} else {
							bound = (int) GridToPoint.getCenterCoordinate(myGrid).getY() + y[1] * range;
							if (Math.abs(this.centerpoint.getY() - bound) <= range) {
								return false;
							}
						}
					}
				} else {
					if (this.collide(object)) {
						return false;
					}
				}
			}
		}

		//For Hero only, since only the Hero can move freely and diagonally
		if (this instanceof Hero) {
			
			//If the middle grid is crossable, but the other two is not, and if strictly 
			//moving in the x or y direction, will automatically adjusts the Hero's 
			//centerpoint to align with the middle grid's axis so the Hero can pass 
			//through easily
			if (midCrossable && !movingInOtherDirection) {
				ArrayList<DrawableObject> neg = gridsWithObjects.get(grids[0]);
				ArrayList<DrawableObject> pos = gridsWithObjects.get(grids[2]);
				boolean negCrossable = true;
				boolean posCrossable = true;
				for (DrawableObject object : neg) {
					if (!object.isCrossable()) {
						negCrossable = false;
					}
				}
				for (DrawableObject object : pos) {
					if (!object.isCrossable()) {
						posCrossable = false;
					}
				}
				if (!negCrossable && !posCrossable) {
					double centerX = this.centerpoint.getX();
					double centerY = this.centerpoint.getY();
					if (axis.equals("x")) {
						centerY = GridToPoint.getCenterCoordinate(this.grid).getY();
					} else {
						centerX = GridToPoint.getCenterCoordinate(this.grid).getX();
					}
					this.centerpoint = new Point2D.Double(centerX, centerY);
					this.setGrid(GridToPoint.getGridFromPoint(this.centerpoint));
					return true;
				}
			}

			//Checks if the Hero's colliding with the grids on the sides
			for (int i = 0; i < 3; i++) {
				if (i == 1) {
					continue;
				}
				objects = gridsWithObjects.get(grids[i]);
				if (!objects.isEmpty()) {
					for (DrawableObject object : objects) {
						//if the object is brick
						if (object.getShape().equals("rectangle")) {

							ArrayList<Point2D.Double> vertexPoints = new ArrayList<>();
							Point2D.Double topLeft = (Double) GridToPoint.getTopLeftCoordinate(grids[i]);
							Point2D.Double topRight = new Point2D.Double(
									topLeft.getX() + BombermanComponent.SQUARE_SIZE, topLeft.getY());
							Point2D.Double botLeft = new Point2D.Double(topLeft.getX(),
									topLeft.getY() + BombermanComponent.SQUARE_SIZE);
							Point2D.Double botRight = new Point2D.Double(topRight.getX(),
									topRight.getY() + BombermanComponent.SQUARE_SIZE);
							vertexPoints.add(topLeft);
							vertexPoints.add(topRight);
							vertexPoints.add(botLeft);
							vertexPoints.add(botRight);
							for (Point2D.Double point : vertexPoints) {
								if (point.distance(this.centerpoint) < this.radius && !object.isCrossable()) {
									return false;
								}
							}
						} else {
							if (this.collide(object)) {
								return false;
							}
						}
					}
				}
			}
		}
		return true;
	}

	@Override
	public String getFileName() {
		return null;
	}

	/**
	 * @return Character's centerpoint
	 */
	@Override
	public Point2D getCenterpoint() {
		return this.centerpoint;
	}
	
	
	/**
	 * Sets Character's centerpoint
	 * 
	 * @param point
	 */
	@Override
	public void setCenterpoint(Point2D point) {
		this.centerpoint = point;
	}

	/**
	 * @return Character's radius
	 */
	@Override
	public double getRadius() {
		return this.radius;
	}

	/**
	 * "circle" only for Character, "rectangle" otherwise.
	 * @return String saying the effective bound of the object
	 */
	@Override
	public String getShape() {
		return "circle";
	}

	@Override
	public void timePassed() {
		// to be overriden
	}

	/**
	 * 
	 * Resets to grid (initialX, initialY) when Hero dies.
	 *
	 */
	public void resetToInitialPosition() {
		this.setCenterpoint(GridToPoint.getCenterCoordinate(new GridToPoint(this.initialX, this.initialY)));
		this.setGrid(new GridToPoint(this.initialX, this.initialY));
	}

}
