package MainPackage;

import gameSupport.BombermanWorld;

/**
 * The destroyable class. This type of brick can be destroyed by explosion.
 * 
 * @author Yvette Weng, Jiaqi Fang, David Everhart.
 */
public class BrickDestroyable extends Brick {

	private PowerUp powerUp;

	public BrickDestroyable(BombermanWorld world, int x, int y) {
		super(world, x, y);
		this.powerUp = null;
		setDestroyable(true);
		setCrossable(false);
	}

	public void setPowerUp(PowerUp pu) {
		this.powerUp = pu;
	}

	public PowerUp getPowerUp() {
		return this.powerUp;
	}

	@Override
	public String getFileName() {
		return "brickDestroyable.png";
	}

	@Override
	public void timePassed() {
		return;
	}

	/**
	 * When an explosion happens on the brick. The brick is removed. If it has a
	 * powerup, the powerup will show up.
	 * 
	 */
	@Override
	public void die() {
		super.die();
		if (this.powerUp != null) {
			PowerUp pu = this.powerUp;
			pu.setGrid(this.grid);
			this.getWorld().addDrawable(pu);
		}
	}

	@Override
	public boolean collide(DrawableObject object) {
		return object.collideWithDesBrick();
	}

}
