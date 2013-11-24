/**
 * 
 */
package simon;

import javax.swing.ImageIcon;

//import finalProject.Simon;


/**
 * @author mbarrus73
 *
 */
public enum SimonEnum
{
	RED(new ImageIcon(SimonEnum.class.getResource(""))), 
	BLUE(new ImageIcon(SimonEnum.class.getResource(""))),
	GREEN(new ImageIcon(SimonEnum.class.getResource(""))), 
	YELLOW(new ImageIcon(SimonEnum.class.getResource("")));

private ImageIcon image;

/**
 * @param image
 */
private SimonEnum( ImageIcon image )
{
	this.image = image;
}

public ImageIcon getImage()
{
	return image;
}
}
