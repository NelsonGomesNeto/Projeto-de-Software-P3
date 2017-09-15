package MainPackage;

import gameSupport.BombermanWorld;

/**
 * Gives the framework for PowerUps, which augment the hero's abilities
 * or add lives.
 * 
 * @author Yvette Weng, Jiaqi Fang, David Everhart.
 */
abstract public class PowerUp extends DrawableObject{

	/**
	 * Creates a PowerUp given a BombermanWorld, x position,
	 * and y position.
	 * 
	 * @param world, x, y
	 * 			The BombermanWorld the PowerUp is to be created in
	 * 			The x and y components of the gridToPoint locatoin of the PowerUp
	 */
	public PowerUp(BombermanWorld world,int x, int y) {
		super(world, x, y);
		setDestroyable(false);
		setCrossable(true);
	}

	/**
	 * Activates the effect of the PowerUp.
	 * 
	 */
	abstract void activate();
	
	/**
	 * Deactivates the effect of the PowerUp.
	 * 
	 */
	abstract void deactivate();

	/**
	 * Calls an Objects collideWithPowerUp method
	 * 
	 * @param object
	 * 			The DrawableObject that collides with a PowerUp
	 * @return false
	 * 			returns false because it should not stop any DrawableObject from moving
	 */
	@Override
	public boolean collide(DrawableObject object) {
		return object.collideWithPowerup(this);
	}
	
	@Override
	public void timePassed() {
		Hero hero = this.getWorld().getHero();
		if (this.grid.equals(hero.getGrid())) {
			hero.addPowerUp(this);
			this.die();
		}
	}

}
