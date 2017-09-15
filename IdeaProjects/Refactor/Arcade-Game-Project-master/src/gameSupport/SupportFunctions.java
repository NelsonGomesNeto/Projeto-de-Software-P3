package gameSupport;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Holds useful functions that didn't seem to have one obvious
 * location.
 * 
 * @author Yvette Weng, Jiaqi Fang, David Everhart.
 */
public class SupportFunctions {

	static HashMap<String, BufferedImage> stringBufferedImageHashMap = new HashMap<>();
	/**
	 * Returns a buffered Image given a string filename.
	 * 
	 * @param text
	 * 			The string filename of the image
	 * @return image
	 * 			The buffered image to be returned
	 */
	public static BufferedImage getImageWithText(String text) {
		if (stringBufferedImageHashMap.containsKey(text)) {
			return stringBufferedImageHashMap.get(text);
		} else {
			BufferedImage image = null;
			try {
				image = ImageIO.read(new File("Images/" + text));
			} catch (IOException exception) {
				throw new RuntimeException("no image found");
			}
			stringBufferedImageHashMap.put(text, image);
			return image;
		}
		/* BufferedImage image = null;
			try {
				image = ImageIO.read(new File("Images/" + text));
			} catch (IOException exception) {
				throw new RuntimeException("no image found");
			}
			return image; */
	}

	/**
	 * Constructs buttons on a JPanel with the given text.
	 * 
	 * @param panel, text
	 * 			The Jpanel the buttons are to be added to
	 * 			The string filename of the image
	 */
	public static void constructButtons(JPanel panel, final String text) {
		JButton button = new JButton(text);
		button.setFont(new Font("Arial", Font.BOLD, 16));
		panel.add(button);
		
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Class<? extends Component> clazz;
				try {
					clazz = Class.forName("panels." + text).asSubclass(Component.class);
				} catch (ClassNotFoundException exception) {
					throw new RuntimeException("Class not found while constructing button");
				}
				Constructor<? extends Component> constructor = null;
				try {
					constructor = clazz.getConstructor();
				} catch (NoSuchMethodException exception) {
					throw new RuntimeException("Constructor method not found while constructing buttons");
				} catch (SecurityException exception) {
					exception.printStackTrace();
				}
				Component temp = null;
				try {
					temp = constructor.newInstance();
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException exception) {
					throw new RuntimeException("Could not construct new instance");
				}
				Component component = temp;
				BombermanFrame.handleChangePanel(component);
			}

		});

	}
}
