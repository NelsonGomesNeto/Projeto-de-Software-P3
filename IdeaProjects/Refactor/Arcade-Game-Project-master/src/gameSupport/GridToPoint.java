package gameSupport;

import java.awt.geom.Point2D;

/**
 * Provides a framework for converting 2DPoints to a pair of integers denoting
 * the grid box that contains the 2DPoint and vice versa.
 * 
 * @author Yvette Weng, Jiaqi Fang, David Everhart.
 */
public class GridToPoint {
	//As used in this application xGrid and yGrid range from 0 to 14
	private int xGrid;
	private int yGrid;
	
	/**
	 * Creates a GridToPoint given an integer x and y position.
	 * 
	 * @param xpos
	 * 
	 * @pram ypos
	 * 			Integers denoting which row and collumn
	 * 			of grid boxes(the theoretical positions that can 
	 * 			be occupied by an object the size of a brick) the 
	 * 			gridToPoint refers to.
	 */
	public GridToPoint(int xpos, int ypos) {
		this.xGrid = xpos;
		this.yGrid = ypos;
	}
	
	/**
	 * Converts Point2Ds to GridToPoints. This method is used often
	 * to check if a DrawableObject occupies the same grid as other objects.
	 * 
	 * @param point
	 * 			The Point2D to be converted to a GridToPoint
	 * @return new GridToPoint
	 * 			The new GridToPoint representing the original Point2D
	 */
	public static GridToPoint getGridFromPoint(Point2D point) {
		int x = (int) (point.getX()/BombermanComponent.SQUARE_SIZE);
		int y = (int) (point.getY()/BombermanComponent.SQUARE_SIZE);
		return new GridToPoint(x, y);
	}
	
	/**
	 * Returns the Point2D of the top left coordinate of a grid based
	 * on a GridToPoint. This method is used when drawing images on the game's 
	 * JComponent.
	 * 
	 * @param grid
	 * 			The GridToPoint from which to return a top left coordinate
	 * @return new Point2D.Double
	 * 				The Point2D of the top left coordinate of the grid
	 */
	public static Point2D getTopLeftCoordinate(GridToPoint grid){
		return new Point2D.Double(grid.getX() * BombermanComponent.SQUARE_SIZE, grid.getY() * BombermanComponent.SQUARE_SIZE);
	}
	
	/**
	 * Returns the Point2D of the center coordinate of a grid based
	 * on a GridToPoint. This method is used often for setting the starting
	 * coordinates of DrawableObjects.
	 * 
	 * @param grid
	 * 			The GridToPoint from which to return a center coordinate
	 * @return new Point2D.Double
	 * 				The Point2D of the center coordinate of the grid
	 */
	public static Point2D getCenterCoordinate(GridToPoint grid){
		double x = grid.getX() * BombermanComponent.SQUARE_SIZE + BombermanComponent.SQUARE_SIZE / 2;
		double y = grid.getY() * BombermanComponent.SQUARE_SIZE + BombermanComponent.SQUARE_SIZE / 2;
		return new Point2D.Double(x, y);
	}
	
	/**
	 * Checks to see if two GridToPoints have the same field values.
	 * 
	 * @param g
	 * 			The GridToPoint to compare to this
	 * @return true or false
	 * 				Returns true if xGrid and yGrid for both GridToPoints are equal
	 */
	public boolean equals(GridToPoint g) {
		return this.xGrid == g.getX() && this.yGrid == g.getY();
	}

	/**
	 * Returns the value of the xGrid field.
	 * 
	 * @return xGrid
	 * 			the value of the xGrid field
	 */
	public int getX(){
		return this.xGrid;
	}
	
	/**
	 * Returns the value of the yGrid field.
	 * 
	 * @return yGrid
	 * 			the value of the yGrid field
	 */
	public int getY(){
		return this.yGrid;
	}
	
	

}
