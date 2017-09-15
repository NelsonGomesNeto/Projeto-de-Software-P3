package MainPackage;

import gameSupport.BombermanWorld;

/**
 * The type of monster which moves randomly like the basic monster but is
 * able to cross the destroyable bricks. 
 * Its speed is slow.
 * 
 * @author Yvette Weng, Jiaqi Fang, David Everhart.
 */
public class CrossWallMonster extends Monster {

	public CrossWallMonster(BombermanWorld world, int x, int y) {
		super(world, x, y);
		setSpeed(3);
		updateDirection(this.getGrid());
	}

	@Override
	public String getFileName() {
		return "monster2.png";
	}
	
	@Override
	public boolean collideWithDesBrick() {
		return false;
	}

}
