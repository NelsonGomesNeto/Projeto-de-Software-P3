package MainPackage;

import gameSupport.BombermanWorld;

/**
 * The type of monster which moves randomly and does nothing
 * Its speed is moderate.
 * 
 * @author Yvette Weng, Jiaqi Fang, David Everhart.
 */
public class BasicMonster extends Monster{

	public BasicMonster(BombermanWorld world, int x, int y) {
		super(world, x, y);
		setSpeed(3);
		updateDirection(this.getGrid());
	}

	@Override
	public String getFileName() {
		return "basicMonster.png";
	}
}
