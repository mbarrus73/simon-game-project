/**
 * 
 */
package simon;

import javax.swing.ImageIcon;


/**
 * @author mbarrus73
 *
 */
public enum SimonEnum
{
	ROCK( new ImageIcon( SimonEnum.class.getResource("RockPaperScissors-Rock.png") ) ), 
	PAPER( new ImageIcon( SimonEnum.class.getResource("RockPaperScissors-Paper.png") ) ), 
	SCISSORS( new ImageIcon( SimonEnum.class.getResource("RockPaperScissors-Scissors.png") ) ), 
	LIZARD( new ImageIcon( SimonEnum.class.getResource("RockPaperScissors-Lizzard.png") ) ), 
	SPOCK( new ImageIcon( SimonEnum.class.getResource("RockPaperScissors-Spock.png") ) );

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
