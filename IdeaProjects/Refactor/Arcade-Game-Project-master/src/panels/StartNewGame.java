package panels;

import javax.swing.JPanel;

import gameSupport.BombermanComponent;
import gameSupport.BombermanFrame;
import gameSupport.BombermanWorld;

/**
 * The panel for the game. 
 * 
 * @author Yvette Weng, Jiaqi Fang, David Everhart.
 */
public class StartNewGame extends JPanel{
	
	public StartNewGame() {
		BombermanWorld world = new BombermanWorld();
		BombermanComponent component = new BombermanComponent(world);
		
		component.setPreferredSize(BombermanFrame.GAME_DIMENSION);
		add(component);
	}
}
