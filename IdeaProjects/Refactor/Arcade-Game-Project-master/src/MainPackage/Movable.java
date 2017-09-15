package MainPackage;

import java.awt.geom.Point2D;

public interface Movable{

	void attack();
	
	/**
	 * Checks if the object is able to move to the given axis of the given
	 * direction.
	 * 
	 * @para axis 
	 * 				Either x or y that we are checking 
	 * 		 direction 
	 * 				The direction we are checking 
	 * 		movingInOhterDirection 
	 * 				true if moving in other direction, false otherwise
	 * 
	 * @return If the monster is able to move in the given direction, return
	 *         true. Return false otherwise.
	 */
	boolean canMove(String axis, int direction, boolean movingInOtherDirection);
	
	/**
	 * Moves the object to the new position. 
	 * 
	 * @para xDirection 
	 * 				the x component of direction which the object is trying to move 
	 *       yDirection 
	 *       		the y component of direction which the object is trying to move
	 * 
	 * @return Return true is successfully update the location. Return false otherwise. 
	 */
	boolean updateLocation(int xDirection, int yDirection);
	
	/**
	 * Returns the center point of this object.
	 * 
	 * @return the center point
	 */
	Point2D getCenterpoint();
	
	/**
	 * Sets the center point of this object.
	 * 
	 * @para the center point
	 */
	void setCenterpoint(Point2D point);
	
	/**
	 * Returns the speed of this object.
	 * 
	 * @return the speed
	 */
	int getSpeed();
	
	/**
	 * Sets the speed of the object. 
	 * 
	 * @para the speed 
	 */
	void setSpeed(int speed);
	
	/**
	 * Returns the radius of this object.
	 * 
	 * @return the radius
	 */
	double getRadius();

}
