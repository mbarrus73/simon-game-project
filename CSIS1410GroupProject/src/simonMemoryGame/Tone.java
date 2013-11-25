/**
 *  Project			:  A09 Team Project
 *  File			:  Note.java
 *  Name			:  Marcellus Barrus and Mike Lawrence
 *  Date Created	:  2013-11-16
 *	Date Due		:  2013-11-24
 * 
 *  Description: Simon Memory Game
 *  
 *  The notes that the simon memory game uses.  This was actually taken from code posted 
 *  stackoverflow.com but modified to work as a static class to be used within the simon app
 *  
 */

package simonMemoryGame;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Tone {

	public static void playNote( Note noteToPlay ) {
		final AudioFormat af = new AudioFormat(Note.SAMPLE_RATE, 8, 1, true, true);
	        SourceDataLine line;
			try {
				line = AudioSystem.getSourceDataLine(af);
			
		        line.open(af, Note.SAMPLE_RATE);
		        line.start();
		     
		            play(line, noteToPlay, 300);
		            play(line, Note.REST, 5);
		       
		        line.drain();
		        line.close();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
	
	private static void play(SourceDataLine line, Note note, int ms) {
	        ms = Math.min(ms, Note.SECONDS * 1000);
	        //int count = 0;
	        int length = Note.SAMPLE_RATE * ms / 1000;
	        line.write(note.data(), 0, length);
	}

}