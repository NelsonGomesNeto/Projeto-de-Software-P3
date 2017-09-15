package MainPackage;

import gameSupport.BombermanWorld;

/**
 * The undestroyable class. This type of brick cannot be destroyed by explosion.
 * 
 * @author Yvette Weng, Jiaqi Fang, David Everhart.
 */
public class BrickUndestroyable extends Brick {

	public BrickUndestroyable(BombermanWorld world, int x, int y) {
		super(world, x, y);
		setCrossable(false);
		setDestroyable(false);
	}
	
	@Override
	public boolean collide(DrawableObject object) {
		return object.collideWithUndesBrick();
	}

	@Override
	public String getFileName() {
		return "brickUndestroyable.png";
	}

	@Override
	public void timePassed() {
		// Ignored
	}


}
