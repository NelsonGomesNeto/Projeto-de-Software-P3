package MainPackage;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import gameSupport.BombermanWorld;
import gameSupport.GridToPoint;

/**
 * The type of monster which tracks hero is hero is within 3 steps (grids) away
 * from any direction. Its speed is fast but still slower than hero.
 * 
 * @author Yvette Weng, Jiaqi Fang, David Everhart.
 */

public class TrackingMonster extends Monster {

	public TrackingMonster(BombermanWorld world, int x, int y) {
		super(world, x, y);
		setSpeed(5);
		updateDirection(this.getGrid());
	}

	@Override
	public String getFileName() {
		return "monster1.png";
	}

	/**
	 * Checks if hero is within 3 steps (grids) away. If hero is detected, move
	 * toward hero. If hero is not detected, keep moving radomly like the basic
	 * monster.
	 * 
	 * @para current the current grid position of monster itself
	 */
	@Override
	public void updateDirection(GridToPoint current) {
		ArrayList<Point> firstStep = getPossibleDirections(current);
		ArrayList<Point> secondStep = new ArrayList<Point>();
		ArrayList<Point> thirdStep = new ArrayList<Point>();
		GridToPoint newFirstPoint, newSecondPoint, newThirdPoint;
		Point2D newFirstCoordinate, newSecondCoordinate, newThirdCoordinate;
		// Checks if hero is within 1 step away
		for (Point fp : firstStep) {
			newFirstPoint = new GridToPoint((int) fp.getX() + current.getX(), (int) fp.getY() + current.getY());
			newFirstCoordinate = GridToPoint.getCenterCoordinate(newFirstPoint);
			if (checkHero(newFirstCoordinate.getX(), newFirstCoordinate.getY())) {
				this.xDirection = (int) fp.getX();
				this.yDirection = (int) fp.getY();
				return;
			}
			secondStep = getPossibleDirections(newFirstPoint);
			// Checks if hero is within 2 steps away
			for (Point sp : secondStep) {
				newSecondPoint = new GridToPoint((int) sp.getX() + newFirstPoint.getX(),
						(int) sp.getY() + newFirstPoint.getY());
				newSecondCoordinate = GridToPoint.getCenterCoordinate(newSecondPoint);
				if (checkHero(newSecondCoordinate.getX(), newSecondCoordinate.getY())) {
					this.xDirection = (int) fp.getX();
					this.yDirection = (int) fp.getY();
					return;
				}
				thirdStep = getPossibleDirections(newSecondPoint);
				// Checks if hero is within 3 steps away
				for (Point tp : thirdStep) {
					newThirdPoint = new GridToPoint((int) tp.getX() + newSecondPoint.getX(),
							(int) tp.getY() + newSecondPoint.getY());
					newThirdCoordinate = GridToPoint.getCenterCoordinate(newThirdPoint);
					if (checkHero(newThirdCoordinate.getX(), newThirdCoordinate.getY())) {
						this.xDirection = (int) fp.getX();
						this.yDirection = (int) fp.getY();
						return;
					}
				}
			}
		}
		// If hero is detected, update monster's first step toward hero
		if (!firstStep.isEmpty()) {
			Point direction = firstStep.get((int) (Math.random() * firstStep.size()));
			this.xDirection = (int) direction.getX();
			this.yDirection = (int) direction.getY();
		}
	}

}
