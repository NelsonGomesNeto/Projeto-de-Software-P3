package MainPackage;

import java.awt.Graphics2D;
import java.util.ArrayList;

import gameSupport.BombermanComponent;
import gameSupport.BombermanWorld;
import gameSupport.GridToPoint;
import gameSupport.SupportFunctions;

/**
 * The explosion which happens when a bomb explode. It kills hero, monsters, and
 * the destroyable walls.
 * 
 * @author Yvette Weng, Jiaqi Fang, David Everhart.
 */
public class Explosion extends DrawableObject {

	private Bomb bomb;
	private boolean inProgress = false;

	public Explosion(BombermanWorld world, Bomb bomb) {
		super(world, bomb.getGrid().getX(), bomb.getGrid().getY());
		this.bomb = bomb;
	}

	/**
	 * Draws the image of explosion in all possible directions on the given
	 * Graphics2D.
	 * 
	 * @para g2 Graphics2D
	 */
	@Override
	public void draw(Graphics2D g2) {
		ArrayList<GridToPoint> grids = this.bomb.getGridsToExplode();

		for (int i = 0; i < grids.size(); i++) {
			drawHelper(g2, grids.get(i));
		}
	}

	/**
	 * Draws a single image of explosion on the given grid point.
	 * 
	 * @para g2 Graphics2D grid the GridToPoint which needs a image to be drawn
	 *       on
	 */
	private void drawHelper(Graphics2D g2, GridToPoint grid) {
		int x = (int) (GridToPoint.getTopLeftCoordinate(grid).getX());
		int y = (int) (GridToPoint.getTopLeftCoordinate(grid).getY());
		g2.drawImage(SupportFunctions.getImageWithText(getFileName()), x, y, BombermanComponent.SQUARE_SIZE,
				BombermanComponent.SQUARE_SIZE, null);
	}

	@Override
	public String getFileName() {
		return "explosion.png";
	}

	@Override
	boolean collide(DrawableObject object) {
		return true;
	}

	@Override
	boolean collideWithHero(Hero object) {
		return true;
	}

	@Override
	boolean collideWithMonster(Monster object) {
		return true;
	}

	@Override
	boolean collideWithPowerup(PowerUp object) {
		return true;
	}

	@Override
	public void timePassed() {
		if (!inProgress) {
			this.inProgress = true;
			Runnable explosion = new Runnable() {
				@Override
				public void run() {

					try {
						destroy();
						Thread.sleep(500);
						die();
						if (getWorld().getHero().getBombNumber() < getWorld().getHero().getMaxBombNumber())
							getWorld().getHero().addToBombNumber(1);

					} catch (InterruptedException exception) {
						// Stop when interrupted
					}
				}
			};
			new Thread(explosion).start();
		}
	}

	/**
	 * If hero, monsters, or destroyable bricks collide with the explosion. The
	 * object will be removed from the drawable list.
	 */
	private void destroy() {
		ArrayList<GridToPoint> grids = this.bomb.getGridsToExplode();
		for (int i = 0; i < grids.size(); i++) {
			ArrayList<DrawableObject> objects = getWorld().getObjects(grids.get(i), this);
			for (DrawableObject object : objects) {
				if (object.isDestroyable()) {
					object.die();
				}
			}
			Hero hero = this.getWorld().getHero();
			if (hero.getGrid().equals(grids.get(i))) {
				hero.die();
			}
		}

	}

}
