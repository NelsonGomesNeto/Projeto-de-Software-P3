package MainPackage;

import java.util.ArrayList;

import gameSupport.BombermanComponent;
import gameSupport.BombermanWorld;
import gameSupport.GridToPoint;

public class Bomb extends DrawableObject {
	private static long TIME_TILL_BOOM = 3000;
	private ArrayList<GridToPoint> gridsToExplode;
	private int maxRange;
	private boolean inProgress = false;
	
	/**
	 * 
	 * Creaates an instance of Bomb at grid (x, y)
	 *
	 * @param world
	 * @param x
	 * @param y
	 */
	public Bomb(BombermanWorld world, int x, int y) {
		super(world, x, y);
		this.maxRange = 1;
		this.gridsToExplode = new ArrayList<>();
		this.setCrossable(false);
		this.setDestroyable(true);
	}

	/**
	 * 
	 * If det is true, Hero is able to control bomb detonation
	 * (determined by power up), and bomb will not automatically explode.
	 *
	 * @param set
	 */
	public void setDetOnCommand(boolean det){
		this.inProgress = det;
	}
	
	/**
	 * 
	 * creates a new Explosion object at the current bomb's location
	 *
	 */
	public void explode() {
		if (this.getWorld().getDrawable().contains(this)) {
			setGridsToExplode();
			getWorld().addDrawable(new Explosion(getWorld(), this));
		}
	}

	/**
	 *
	 * @return ArrayList containing grids to draw explosion over
	 */
	public ArrayList<GridToPoint> getGridsToExplode() {
		return this.gridsToExplode;
	}

	/**
	 * Explodes bomb and removes bomb from world
	 */
	@Override
	public void die() {
		explode();
		this.getWorld().getHero().removeBomb(this);
		super.die();
	}
	
	
	@Override
	public String getFileName() {
		return "Bomb.png";
	}


	/**
	 * If bomb has not started countdown, the thread is started, and
	 * inProgress is set to true.
	 */
	@Override
	public void timePassed() {
		if (!this.inProgress) {
			this.inProgress = true;
			Runnable bomb = new Runnable() {
				@Override
				public void run() {
					try {
						
						Thread.sleep(TIME_TILL_BOOM);
						die();
						
					} catch (InterruptedException exception) {
						// Stop when interrupted
					}
				}
			};
			new Thread(bomb).start();
		}

	}

	/**
	 * 
	 * Sets gridsToExplode depending on bomb range and location
	 *
	 */
	public void setGridsToExplode() {
		GridToPoint current = this.getGrid();
		this.gridsToExplode.add(this.grid);
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (Math.abs(i + j) == 1) {
					boolean next = true;
					for (int r = 0; r < this.maxRange; r++) {		
						if (next) {
							int x = current.getX() + i * (r + 1);
							int y = current.getY() + j * (r + 1);
							if ((x >= 0 && x < BombermanComponent.NUM_SQUARES_PER_SIDE)
									&& (y >= 0 && y < BombermanComponent.NUM_SQUARES_PER_SIDE)) {
								GridToPoint explodingGrid = new GridToPoint(x, y);
								ArrayList<DrawableObject> objects = getWorld().getObjects(explodingGrid, this);
								if (objects.isEmpty()) {
									this.gridsToExplode.add(explodingGrid);
								}
								else {
									for (DrawableObject object : objects) {
										//Grid is not added if object is not destroyable
										if (!object.isDestroyable()) {
											next = false;
											break;											
										} 
										//Will only destoy the first destroyable object
										//in one given direction
										if (object.isDestroyable()) {
											this.gridsToExplode.add(explodingGrid);
											next = false;
											break;
										}
									}
								}
							}	
						}
					}
				}
			}
		}

	}
	
	/**
	 * Double dispatch to handle collision between objects
	 */
	@Override
	public boolean collide(DrawableObject object) {
		return object.collideWithBomb();
	}

	/**
	 * 
	 * sets range of bomb upon explosion
	 *
	 * @param range
	 */
	public void setMaxRange(int range) {
		this.maxRange = range;
	}

	/**
	 * 
	 * Removes from world without exploding
	 *
	 */
	public void remove() {	
		this.getWorld().removeDrawable(this);
	}


}
