package MainPackage;

import gameSupport.BombermanWorld;

/**
 * Increases the number of bombs that the player can make.
 * 
 * @author Yvette Weng, Jiaqi Fang, David Everhart.
 */
public class MoreBombsPowerUp extends PowerUp {

	/**
	 * Creates a MoreBombsPowerUp given a BombermanWorld, x position,
	 * and y position.
	 * 
	 * @param world, x, y
	 * 			The BombermanWorld the PowerUp is to be created in
	 * 			The x and y components of the gridToPoint locatoin of the PowerUp
	 */
	public MoreBombsPowerUp(BombermanWorld world, int x, int y) {
		super(world, x, y);
	}

	/**
	 * Increases the number of bombs that the player can make.
	 * 
	 */
	@Override
	public void activate() {
		Hero hero = this.getWorld().getHero();
		hero.setMaxBombNumber(hero.getMaxBombNumber() + 1);
	}

	/**
	 * Decreases the number of the bombs that the player can make.
	 * 
	 */
	@Override
	public void deactivate() {
		Hero hero = this.getWorld().getHero();
		hero.setMaxBombNumber(hero.getMaxBombNumber() - 1);
	}
	
	/**
	 * Returns the file name of the image for a MoreBombsPowerUp
	 * 
	 * @return "moreBomb.png"
	 * 			The file name for the image
	 */
	@Override
	public String getFileName() {
		return "moreBomb.png";
	}

}
