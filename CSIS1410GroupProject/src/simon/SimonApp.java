package simon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JTextPane;

import toneTest.Note;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

@SuppressWarnings("serial")
public class SimonApp extends JFrame {

	private JPanel contentPane;
	private JPanel panelControls;
	private JPanel playground;
	private JPanel player;
	private JPanel controlPlayer;
	private JPanel highScores;
	
	JButton btnVisualRed;
	JButton btnVisualGreen;
	JButton btnVisualBlue;
	JButton btnVisualYellow;
	ArrayList<String> scores = new ArrayList<>();
	
	private final ButtonGroup controlButtonDifficultyGroup = new ButtonGroup();

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelControls = createControlPanel();
		contentPane.add(panelControls, BorderLayout.EAST);
		
		
		JPanel playgroundControls = new JPanel();
		playgroundControls.setLayout(new BorderLayout(0, 0));
		contentPane.add(playgroundControls, BorderLayout.WEST);
		
		playground = new JPanel();
		playgroundControls.add(playground, BorderLayout.NORTH);
		
		player = createVisualPlayer();
		player.setVisible(false);
		playground.add( player );
		
		controlPlayer = createControlPlayer();
		controlPlayer.setVisible(false);
		playground.add(controlPlayer);
		
		highScores = createHighScore();
		highScores.setVisible(false);
		playground.add( highScores );
		
		JButton btnGo = new JButton("GO!");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(){
					   public void run(){
					         playSequence();
					    }
					  }.start(); 
			}
		});
		playgroundControls.add(btnGo, BorderLayout.WEST);
		
		JLabel lblNewLabel = new JLabel("New label");
		playgroundControls.add(lblNewLabel, BorderLayout.EAST);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		playgroundControls.add(lblNewLabel_1, BorderLayout.CENTER);
		
	}
	
	private JPanel createHighScore() {
		JPanel scores = new JPanel();
		scores.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblHighScore = new JLabel("High Scores:");
		scores.add(lblHighScore);
		
		JTextPane txtpnMarcellusBarrus = new JTextPane();
		txtpnMarcellusBarrus.setText("1\tMarcellus Barrus\t245\n2\tMarcellus Barrus\t234\n3\tMarcellus Barrus\t198\n4\tMarcellus Barrus\t189");
		scores.add(txtpnMarcellusBarrus);
		
		return scores;
		
	}

	private JPanel createControlPlayer()
	{
		JPanel controlPlayer = new JPanel();
		
		return controlPlayer;
	}
	
	private JPanel createVisualPlayer() 
	{
		JPanel player = new JPanel();
		
		player.setLayout(new GridLayout(0, 2, 0, 0));
	
		final Icon green = SimonEnum.GREEN.getImage();
		btnVisualGreen = new JButton(green);
		player.add(btnVisualGreen);
		
		final Icon red = SimonEnum.RED.getImage();
		btnVisualRed = new JButton(red);
		player.add(btnVisualRed);
		
		final Icon yellow = SimonEnum.YELLOW.getImage();	
		btnVisualYellow = new JButton(yellow);
		player.add(btnVisualYellow);
		
		final Icon blue = SimonEnum.BLUE.getImage();
		btnVisualBlue = new JButton(blue);
		player.add(btnVisualBlue);
		
		return player;
	}

	private JPanel createControlPanel() {
		

		JPanel controls = new JPanel();
		controls.setLayout(new GridLayout(10, 0, 0, 0));
		
		JButton btnPlayGame = new JButton("Play Game");
		btnPlayGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				highScores.setVisible(false);
				controlPlayer.setVisible(false);
				
				player.setVisible(true);
			}
		});

		controls.add(btnPlayGame);
		
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		controls.add(btnNewButton, BorderLayout.CENTER);
		
		JButton btnHighScores = new JButton("High Scores");
		btnHighScores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				highScores.setVisible(true);
				controlPlayer.setVisible(false);
				player.setVisible(false);
			}
		});
		controls.add(btnHighScores);
		
		return controls;
	}
	
	private void playSequence()
	{
		List<Integer> myList = new ArrayList<>();
		myList.add(0);
		myList.add(1);
		myList.add(2);
		myList.add(3);
		myList.add(2);
		myList.add(1);
		myList.add(0);
		
		List<JButton> myButtonList = new ArrayList<>();
		myButtonList.add( btnVisualGreen );
		myButtonList.add( btnVisualRed );
		myButtonList.add( btnVisualYellow );
		myButtonList.add( btnVisualBlue );
		
		List<Note> myNotes = new ArrayList<>();
		myNotes.add( Note.C4);
		myNotes.add( Note.A4);
		myNotes.add( Note.B4);
		myNotes.add( Note.D4);
		
		Border blackline = BorderFactory.createLineBorder(Color.black);
		Border empty = BorderFactory.createEmptyBorder();
		
		
		List<Icon> myButtonPressed = new ArrayList<>();
		
		myButtonPressed.add( SimonEnum.GREEN.getImagePressed() );
		myButtonPressed.add( SimonEnum.RED.getImagePressed() );
		myButtonPressed.add( SimonEnum.YELLOW.getImagePressed() );
		myButtonPressed.add( SimonEnum.BLUE.getImagePressed() );
		
		Icon defaultImage;
		
		//final AudioFormat af = new AudioFormat(Note.SAMPLE_RATE, 8, 1, true, true);
        //SourceDataLine line;
        
        for( int i : myList )
		{
			
			//System.out.println( i );
			
			defaultImage = myButtonList.get(i).getIcon();
			
			myButtonList.get(i).setIcon(myButtonPressed.get(i));
			
			//play(line, myNotes.get(i), 400);
			myButtonList.get(i).setIcon(defaultImage);
			
		}
		//line.drain();
		//line.close();
	}
	
	private void play(SourceDataLine line, Note note, int ms) 
	{
	        ms = Math.min(ms, Note.SECONDS * 1000);
	        //int count = 0;
	        int length = Note.SAMPLE_RATE * ms / 1000;
	        line.write(note.data(), 0, length);
	}
	
	private void playNote( Note note, int ms ) 
	{
		final AudioFormat af = new AudioFormat(Note.SAMPLE_RATE, 8, 1, true, true);
        SourceDataLine line = null;
     
	        ms = Math.min(ms, Note.SECONDS * 1000);
	        //int count = 0;
	        int length = Note.SAMPLE_RATE * ms / 1000;
	        line.write(note.data(), 0, length);
	        
	    line.drain();
	    line.close();
	}
	
	public static void saveScore(int score, String name, ArrayList<String> list) 
	{
		try(BufferedReader reader = new BufferedReader(new FileReader("src/simon/HighScores.txt"));
				PrintWriter writer = new PrintWriter("src/simon/HighScores.txt")) 
		{
			String line;
			
			while((line = reader.readLine()) != null)
			{
				list.add(line);
			}
			list.add(Integer.toString(score) + ", " + name);
			Collections.sort(list);
			for(String el : list) 
			{
				writer.println(el);
			}
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
