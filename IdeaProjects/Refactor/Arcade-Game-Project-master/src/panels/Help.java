package panels;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import gameSupport.BombermanFrame;
import gameSupport.SupportFunctions;

/**
 * The panal for the help page with how to play the game and description for powerups.  
 * 
 * @author Yvette Weng, Jiaqi Fang, David Everhart.
 */
public class Help extends JPanel {

	public Help() {
		setPreferredSize(BombermanFrame.GAME_DIMENSION);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		JPanel imagePanel = new ImagePanel(); 
		
		Dimension preferred = new Dimension(BombermanFrame.GAME_DIMENSION.width, BombermanFrame.GAME_DIMENSION.height - 100);
		imagePanel.setPreferredSize(preferred);
		add(imagePanel);
		JPanel buttonPanel = new JPanel();
		add(buttonPanel);
		add(Box.createVerticalGlue());
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		
		SupportFunctions.constructButtons(buttonPanel, "StartNewGame");
		buttonPanel.add(Box.createHorizontalStrut(100));
		SupportFunctions.constructButtons(buttonPanel, "MainPage");
	}

	private class ImagePanel extends JPanel {

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;

			BufferedImage image = SupportFunctions.getImageWithText("help.png");
			g2.drawImage(image, 0, 0, BombermanFrame.GAME_DIMENSION.width, BombermanFrame.GAME_DIMENSION.height - 100,
					this);
		}
	}
}
