package MainPackage;

import gameSupport.BombermanWorld;

/**
 * Increases the number of lives the hero has.
 * 
 * @author Yvette Weng, Jiaqi Fang, David Everhart.
 */
public class ExtraLifePowerUp extends PowerUp {

	/**
	 * Creates an ExtraLifePowerUp given a BombermanWorld, x position,
	 * and y position.
	 * 
	 * @param world, x, y
	 * 			The BombermanWorld the PowerUp is to be created in
	 * 			The x and y components of the gridToPoint locatoin of the PowerUp
	 */
	public ExtraLifePowerUp(BombermanWorld world, int x, int y) {
		super(world, x, y);
	}

	/**
	 * Increases the number of lives the hero has.
	 * 
	 */
	@Override
	public void activate() {
		this.getWorld().getHero().addLife();
	}

	/**
	 * Does nothing, extra lives will not be removed.
	 * 
	 */
	@Override
	public void deactivate() {
		return; //We don't want to remove any lives
	}

	/**
	 * Returns the file name of the image for an ExtraLifePowerUp
	 * 
	 * @return "extraLife.png"
	 * 			The file name for the image
	 */
	@Override
	public String getFileName() {
		return "extraLife.png";
	}

}
