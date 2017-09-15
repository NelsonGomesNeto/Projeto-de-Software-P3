package MainPackage;
import gameSupport.BombermanWorld;

/**
 * The abstract class for two types of bricks
 * 
 * @author Yvette Weng, Jiaqi Fang, David Everhart.
 */
public abstract class Brick extends DrawableObject {

	public Brick(BombermanWorld world) {
		super(world);
		this.setCrossable(false);
	}
	
	public Brick(BombermanWorld world, int x, int y){
		super(world, x, y);
		this.setCrossable(false);
	}


}
