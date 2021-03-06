/**
 *  Project			:  A09 Team Project
 *  File			:  SimonApp.java
 *  Name			:  Marcellus Barrus and Mike Lawrence
 *  Date Created	:  2013-11-16
 *	Date Due		:  2013-11-24
 * 
 *  Description: Simon Memory Game
 *  
 *  You guess in sequence what the computer chooses. 
 *  
 */

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
import java.util.Collections;
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

import javax.swing.SwingConstants;
import javax.xml.ws.handler.MessageContext.Scope;

import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.FlowLayout;

public class SimonApp extends JFrame {

	private JPanel contentPane;
	private JPanel pnlGame;
	private JPanel pnlHighScore;
	private JPanel pnlHighScoreList;
	private JPanel pnlGameOver;
	private JPanel pnlWelcome;
	
	private int score = 0;

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
	private JLabel lblGameOver;
	private JLabel lblYourScore;
	private JLabel lblScore = new JLabel("0");
	private JButton btnPlayNewGame;
	private JButton btnExit;
	private JButton btnShowHighScores;
	private JButton btnPlayGameFromScores;
	private JLabel lblNewLabel;
	private JLabel lblBlank;
	private JLabel lblGameScore;
	private JButton btnLetsPlay;
	private JTextField tfName;
	private JLabel lblName;
	private JLabel lblNameError;
	private JLabel jlblHighScores;
	private JTextPane jtpScores;
	

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
		setBounds(100, 100, 440, 355);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		txtrDebug = new JTextArea();
		txtrDebug.setText("Debug");
		//contentPane.add(txtrDebug, BorderLayout.EAST);
		
		pnlWelcome = createWelcomePanel();
		contentPane.add(pnlWelcome, BorderLayout.CENTER);
		
		pnlGame = createGamePanel();
		//contentPane.add(pnlGame, BorderLayout.CENTER);
		
		pnlHighScore = createHighScorePanel();
		//contentPane.add(pnlHighScore, BorderLayout.EAST);
		
		pnlHighScoreList = createHighScoreListPanel();
		//contentPane.add(pnlHighScoreList, BorderLayout.CENTER);
		
		pnlGameOver = createGameOverPanel();
		//contentPane.add(pnlGameOver, BorderLayout.CENTER);
		
		
		
	}

	private JPanel createHighScoreListPanel() {
		JPanel highScoreList = new JPanel();
		highScoreList.setLayout(new BorderLayout(0, 0));
		
		jtpScores = new JTextPane();
		highScoreList.add(jtpScores, BorderLayout.CENTER);
		
		jlblHighScores = new JLabel();
		jlblHighScores.setText("High Scores");
		highScoreList.add(jlblHighScores, BorderLayout.NORTH);
		
		
		
		return highScoreList;
	}

	private JPanel createWelcomePanel() {
		JPanel welcomePanel = new JPanel();
		welcomePanel.setLayout(new GridLayout(10, 1, 0, 0));
		
		lblName = new JLabel("Enter Name");
		welcomePanel.add(lblName);
		
		tfName = new JTextField();
		tfName.setHorizontalAlignment(SwingConstants.CENTER);
		welcomePanel.add(tfName);
	
		
		
		btnLetsPlay = new JButton("Play");
		btnLetsPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( !tfName.getText().isEmpty() )
				{
					contentPane.removeAll();
		            contentPane.add(pnlHighScore, BorderLayout.EAST);
		            contentPane.add(pnlGame, BorderLayout.CENTER);
		            revalidate();
		            repaint();
					
		            clearAndPlay();
				} else
				{
					lblNameError.setText( "You must enter in your name" );
				}
			}
		});
		welcomePanel.add(btnLetsPlay);
		
		lblNameError = new JLabel("");
		lblNameError.setForeground(Color.RED);
		welcomePanel.add(lblNameError);
		
		return welcomePanel;
	}

	private JPanel createGameOverPanel() {
		JPanel gameOver = new JPanel();
		gameOver.setLayout(new GridLayout(10, 1, 0, 0));
		
		lblGameOver = new JLabel("GAME OVER!");
		lblGameOver.setFont(new Font("Lucida Grande", Font.BOLD, 40));
		lblGameOver.setHorizontalAlignment(SwingConstants.CENTER);
		gameOver.add(lblGameOver);
		
		lblYourScore = new JLabel("Your Score:");
		lblYourScore.setFont(new Font("Lucida Grande", Font.BOLD, 26));
		lblYourScore.setHorizontalAlignment(SwingConstants.CENTER);
		gameOver.add(lblYourScore);
		
	
		lblScore.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		
		gameOver.add(lblScore);
		
		btnPlayNewGame = new JButton("Play New Game");
		btnPlayNewGame.setSize(new Dimension(150, 50));
		btnPlayNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 contentPane.removeAll();
	             contentPane.add(pnlHighScore, BorderLayout.EAST);
	             contentPane.add(pnlGame, BorderLayout.CENTER);
	             revalidate();
	             repaint();
				
	             clearAndPlay();
			}	
		});
		gameOver.add(btnPlayNewGame);
		
		
		return gameOver;
	}

	private JPanel createHighScorePanel() {
		JPanel scores = new JPanel();
		scores.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblNewLabel = new JLabel("Your Score");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scores.add(lblNewLabel);
		
		lblGameScore = new JLabel("");
		lblGameScore.setFont(new Font("Lucida Grande", Font.BOLD, 40));
		lblGameScore.setHorizontalAlignment(SwingConstants.CENTER);
		scores.add(lblGameScore);
		
		lblBlank = new JLabel("");
		scores.add(lblBlank);
		
		
		btnShowHighScores = new JButton("High Scores");
		btnShowHighScores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
	            contentPane.add(pnlHighScore, BorderLayout.EAST);
	            contentPane.add(pnlHighScoreList, BorderLayout.CENTER);
	            revalidate();
	            repaint();
				
	            readInHighScores();
				
	            btnShowHighScores.setVisible(false);
	            btnPlayGameFromScores.setVisible(true);
			}
		});
		scores.add(btnShowHighScores);
		
		btnPlayGameFromScores = new JButton("Play Game");
		btnPlayGameFromScores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
	            contentPane.add(pnlHighScore, BorderLayout.EAST);
	            contentPane.add(pnlGame, BorderLayout.CENTER);
	            
	            clearAndPlay();
	            
	            revalidate();
	            repaint();
			
				
	            btnPlayGameFromScores.setVisible(false);
	            btnShowHighScores.setVisible(true);
			}
		});
		btnPlayGameFromScores.setVisible(false);
		scores.add(btnPlayGameFromScores);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		scores.add(btnExit);

		return scores;
	}
	
	private void clearAndPlay()
	{
		score = 0;
		lblGameScore.setText(Integer.toString(score));
		computer.clear();
		player.clear();
		continueLoop = true;
		new Thread(){
			public void run(){
				playGame();
			}
		}.start(); 
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
				
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				Icon clickGreenImage = SimonEnum.GREEN.getImage();
				btnGreen.setIcon(clickGreenImage);
				addTurns(3);
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
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				Icon clickRedImage = SimonEnum.RED.getImage();
				btnRed.setIcon(clickRedImage);
				addTurns(1);
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
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				Icon clickYellowImage = SimonEnum.YELLOW.getImage();
				btnYellow.setIcon(clickYellowImage);
				addTurns(2);
			}
		});
	
		gamePanel.add(btnYellow);
		
		final Icon blue = SimonEnum.BLUE.getImage();
		btnBlue = new JButton(blue);
		btnBlue.setBorder(null);
		btnBlue.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Icon clickBlueImage = SimonEnum.BLUE.getImagePressed();
				btnBlue.setIcon(clickBlueImage);
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				Icon clickBlueImage = SimonEnum.BLUE.getImage();
				btnBlue.setIcon(clickBlueImage);
				addTurns(0);
			}
		});
		
		gamePanel.add(btnBlue);
		
		
		
		 
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
				btnBlue.doClick( 700 );
				
			
				//Pause the execution for one second (if the arraylist has more
				//than one item it needs to pause in between each color lighting up)

			} else if ( computer.get( i ) == 1 ) //Red Button
			{ 
			
				myDebug( "Got Red" );
				playTone( 1 );
				btnRed.doClick( 700 );
				
			} else if ( computer.get( i ) == 2 ) //Yellow Button
			{  
				myDebug( "Got Yellow" );
				playTone( 2 );
				btnYellow.doClick( 700 ); 
				
			} else if (computer.get(i) == 3)  //Green Button
			{ 
				myDebug( "Got Green" );
				playTone( 3 );
				btnGreen.doClick(700);
			}
			try {
				Thread.sleep(200);
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
		
		if( player.size() > computer.size() )
		{
			gameOver();
		}
		
		if( continueLoop )
		{
			for( int i = 0; i < player.size(); i++ )
			{
				
				if( !player.get(i).equals( computer.get(i) ) )
				{
					myDebug( "Player: " + player.get(i).toString() );
					myDebug( "Computer: " + computer.get(i).toString() );
					myDebug("Game Over");
					
					gameOver();
				}
				
			}
		
		
		
			if( player.size() == computer.size() )
			{	
				score++;
				lblGameScore.setText( Integer.toString(score));
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
	
	private void gameOver()
	{
		contentPane.removeAll();
        
		lblScore.setText( Integer.toString( score ) );
        contentPane.add(pnlGameOver, BorderLayout.CENTER);
        revalidate();
        repaint();
		
        //System.out.println( score );
        
        saveScore( score, tfName.getText() );
		continueLoop = false;
	}

	/*
	 * This should satisfy IO requirement
	 */
	public static void saveScore(int score, String name) 
	{
		ArrayList<String> list = new ArrayList<>();
		String line;
		try(BufferedReader input = new BufferedReader(new FileReader("src/simonMemoryGame/HighScores.txt")))
		{
			while( ( line = input.readLine() ) != null)
			{
				//System.out.println( line );
				list.add(line);
			}
		
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		list.add(Integer.toString(score) + ", " + name);
		Collections.sort(list);
		
		try( PrintWriter writer = new PrintWriter( "src/simonMemoryGame/HighScores.txt" ) )
		{
			//String[] words = allWords.split( " " );
			for(String el : list) 
			{
				writer.println(el);
			}
			
		} catch( Exception e )
		{
			e.printStackTrace();
		}
		
		
	}
	
	/*
	 * This should satisfy IO requirement
	 */
	private void readInHighScores()
	{
		StringBuilder sb = new StringBuilder();
		String line;
		try(BufferedReader input = new BufferedReader(new FileReader("src/simonMemoryGame/HighScores.txt")))
		{
			while( ( line = input.readLine() ) != null)
			{
				String[] myLine = line.split(",");
				sb.append( String.format("%s %20s%n",  myLine[0], myLine[1] ) );
			}
		
			jtpScores.setText(sb.toString());
			
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
}
