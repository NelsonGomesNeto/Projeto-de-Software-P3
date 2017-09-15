package MainPackage;

import gameSupport.BombermanWorld;

/**
 * Lets the player detonate bombs on command.
 * 
 * @author Yvette Weng, Jiaqi Fang, David Everhart.
 */
public class DetonateOnCommandPowerUp extends PowerUp {

	/**
	 * Creates a DetonateOnCommandPowerUp given a BombermanWorld, x position,
	 * and y position.
	 * 
	 * @param world, x, y
	 * 			The BombermanWorld the PowerUp is to be created in
	 * 			The x and y components of the gridToPoint locatoin of the PowerUp
	 */
	public DetonateOnCommandPowerUp(BombermanWorld world, int x, int y) {
		super(world, x, y);
	}

	/**
	 * Lets the player detonate bombs on command.
	 * 
	 */
	@Override
	void activate() {
		this.getWorld().getHero().setCanDetonate(true);
	}

	/**
	 * Prevents the player from detonating bombs on command.
	 * 
	 */
	@Override
	void deactivate() {
		this.getWorld().getHero().setCanDetonate(false);
		this.getWorld().getHero().resetMyDetBombs();
	}

	/**
	 * Returns the file name of the image for a DetonateOnCommandPowerUp
	 * 
	 * @return "timeExplosion.png"
	 * 			The file name for the image
	 */
	@Override
	public String getFileName() {
		return "timeExplosion.png";
	}
}
