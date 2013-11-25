/**
 * 
 */
package simonMemoryGame;

import javax.swing.ImageIcon;

//import finalProject.Simon;

/**
 * @author mbarrus73
 *
 */
public enum SimonEnum
{
	RED( new ImageIcon( SimonEnum.class.getResource("red.jpg") ) 
		, new ImageIcon( SimonEnum.class.getResource( "red_light.jpg") ) ), 
	BLUE( new ImageIcon( SimonEnum.class.getResource("blue.jpg") ) 
		, new ImageIcon( SimonEnum.class.getResource( "blue_light.jpg" ) ) ), 
	YELLOW( new ImageIcon( SimonEnum.class.getResource("yellow.jpg") ) 
		, new ImageIcon( SimonEnum.class.getResource( "yellow_light.jpg" ) ) ), 
	GREEN( new ImageIcon( SimonEnum.class.getResource("green.jpg") ) 
		, new ImageIcon( SimonEnum.class.getResource( "green_light.jpg" ) ) )
	;

	private ImageIcon image;
	private ImageIcon imagePressed;
	
	/**
	 * @param image
	 */
	private SimonEnum( ImageIcon image, ImageIcon imagePressed )
	{
		this.image = image;
		this.imagePressed = imagePressed;
	}
	
	public ImageIcon getImage()
	{
		return image;
	}
	
	public ImageIcon getImagePressed()
	{
		return imagePressed;
	}
}
