package simonMemoryGame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.Color;

public class SimonApp extends JFrame {

	private JPanel contentPane;
	private JPanel pnlGame;
	boolean continueLoop = true;
	
	ArrayList<Integer> computer = new ArrayList<Integer>();
	ArrayList<Integer> player = new ArrayList<Integer>();
	
	
	
	private boolean testMode = true;
	
	JButton btnRed;
	JButton btnGreen;
	JButton btnBlue;
	JButton btnYellow;

	private JTextArea txtrDebug;
	
	final List<Note> myNotes = new ArrayList<>();
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SimonApp frame = new SimonApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SimonApp() {
		myNotes.add( Note.C4);
		myNotes.add( Note.A4);
		myNotes.add( Note.B4);
		myNotes.add( Note.D4);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		txtrDebug = new JTextArea();
		txtrDebug.setText("Debug");
		contentPane.add(txtrDebug, BorderLayout.EAST);
		
		pnlGame = createGamePanel();
		contentPane.add(pnlGame, BorderLayout.WEST);
		
		
	}

	private JPanel createGamePanel() {
		JPanel gamePanel = new JPanel();
		gamePanel.setLayout(new GridLayout(2, 2, 0, 0));
		
		final Icon green = SimonEnum.GREEN.getImage();
		btnGreen = new JButton(green);
		btnGreen.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnGreen.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				Icon clickGreenImage = SimonEnum.GREEN.getImagePressed();
				btnGreen.setIcon(clickGreenImage);
				addTurns(3);
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				Icon clickGreenImage = SimonEnum.GREEN.getImage();
				btnGreen.setIcon(clickGreenImage);
			}
		});
		
		gamePanel.add(btnGreen);
		
		final Icon red = SimonEnum.RED.getImage();
		btnRed = new JButton(red);
		btnRed.setBorder(null);
		btnRed.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Icon clickRedImage = SimonEnum.RED.getImagePressed();
				btnRed.setIcon(clickRedImage);
				addTurns(1);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				Icon clickRedImage = SimonEnum.RED.getImage();
				btnRed.setIcon(clickRedImage);
			}
		});
		
		gamePanel.add(btnRed);
		
		final Icon yellow = SimonEnum.YELLOW.getImage();	
		btnYellow = new JButton(yellow);
		btnYellow.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnYellow.setBackground(Color.BLACK);
		btnYellow.setBorder(null);
		btnYellow.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Icon clickYellowImage = SimonEnum.YELLOW.getImagePressed();
				btnYellow.setIcon(clickYellowImage);
				addTurns(2);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				Icon clickYellowImage = SimonEnum.YELLOW.getImage();
				btnYellow.setIcon(clickYellowImage);
			}
		});
	
		gamePanel.add(btnYellow);
		
		final Icon blue = SimonEnum.BLUE.getImage();
		btnBlue = new JButton(blue);
		btnBlue.setBackground(Color.BLACK);
		btnBlue.setBorder(null);
		btnBlue.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Icon clickBlueImage = SimonEnum.BLUE.getImagePressed();
				btnBlue.setIcon(clickBlueImage);
				addTurns(0);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				Icon clickBlueImage = SimonEnum.BLUE.getImage();
				btnBlue.setIcon(clickBlueImage);
			}
		});
		
		gamePanel.add(btnBlue);
		
		new Thread(){
			public void run(){
				playGame();
			}
		}.start(); 
		
		 
		return gamePanel;
	}

	
	
	private void playGame() {
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int CompPick;   //The color the computer picks
		Random gen = new Random();
	
		CompPick = gen.nextInt(4);  //Pick a random number(color)
		computer.add(CompPick); //Add the color to the computer's arraylist
			
			
		playSequence();

	}

	private void myDebug(String string) {
		// TODO Auto-generated method stub
		
		if( testMode == true )
		{
			txtrDebug.append( string + "\n" );
		}
	}
	
	private void playSequence() {
		
		
		//myDebug( Integer.toString( CompPick ) + "\n" );
		
		for ( int i = 0; i < computer.size(); i++ ) 
		{
			
			if ( computer.get(i) == 0 ) //Blue Button
			{ 
			
				//Make the blue button look like it was clicked
		
				myDebug( "Got Blue" );
				playTone( 0 );
				btnBlue.doClick( 1000 );
				
			
				//Pause the execution for one second (if the arraylist has more
				//than one item it needs to pause in between each color lighting up)

			} else if ( computer.get( i ) == 1 ) //Red Button
			{ 
			
				myDebug( "Got Red" );
				playTone( 1 );
				btnRed.doClick( 1000 );
				
			} else if ( computer.get( i ) == 2 ) //Yellow Button
			{  
				myDebug( "Got Yellow" );
				playTone( 2 );
				btnYellow.doClick( 1000 ); 
				
			} else if (computer.get(i) == 3)  //Green Button
			{ 
				myDebug( "Got Green" );
				playTone( 3 );
				btnGreen.doClick(1100);
			}
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	private void playTone( final int selected )
	{
		new Thread(){
			public void run(){
				Tone.playNote(myNotes.get( selected ) );
			}
		}.start(); 
	}
	
	private void addTurns( int selected ) {
		player.add( selected );
		
		playTone( selected );
		
		for( int i = 0; i < player.size(); i++ )
		{
			
			if( !player.get(i).equals( computer.get(i) ) )
			{
				myDebug( "Player: " + player.get(i).toString() );
				myDebug( "Computer: " + computer.get(i).toString() );
				myDebug("Game Over");
				continueLoop = false;
			}
			
		}
		
		
		if( player.size() == computer.size() )
		{	
			myDebug( "\nNew Round\n");
			if( continueLoop )
			{
				player.clear();
				new Thread(){
					public void run(){
						playGame();
					}
				}.start(); 
			}
		}
	}

}
