package MainPackage;

import gameSupport.BombermanWorld;

/**
 * Increases the size of the bombs that the player makes.
 * 
 * @author Yvette Weng, Jiaqi Fang, David Everhart.
 */
public class BiggerBombPowerUp extends PowerUp {

	/**
	 * Creates a BiggerBombPowerUp given a BombermanWorld, x position,
	 * and y position.
	 * 
	 * @param world, x, y
	 * 			The BombermanWorld the PowerUp is to be created in
	 * 			The x and y components of the gridToPoint locatoin of the PowerUp
	 */
	public BiggerBombPowerUp(BombermanWorld world, int x, int y) {
		super(world,x,y);
	}

	/**
	 * Increases the size of the bombs that the player makes.
	 * 
	 */
	@Override
	void activate() {
		this.getWorld().getHero().setBombSize(2);
	}

	/**
	 * Reduces the size of the bombs that the player makes.
	 * 
	 */
	@Override
	void deactivate() {
		this.getWorld().getHero().setBombSize(1);
	}
	
	/**
	 * Returns the file name of the image for a BiggerBombPowerUp
	 * 
	 * @return "longerExplosion.png"
	 * 			The file name for the image
	 */
	@Override
	public String getFileName() {
		return "longerExplosion.png";
	}
}
