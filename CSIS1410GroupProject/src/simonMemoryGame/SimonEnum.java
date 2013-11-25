/**
 *  Project			:  A09 Team Project
 *  File			:  SimonEnum.java
 *  Name			:  Marcellus Barrus and Mike Lawrence
 *  Date Created	:  2013-11-16
 *	Date Due		:  2013-11-24
 * 
 *  Description: Simon Memory Game
 *  
 *  Creates images and methods to use as an enum
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
