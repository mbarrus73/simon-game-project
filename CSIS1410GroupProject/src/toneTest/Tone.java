package toneTest;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Tone {

	public static void main(String[] args) {
		final AudioFormat af = new AudioFormat(Note.SAMPLE_RATE, 8, 1, true, true);
	        SourceDataLine line;
			try {
				line = AudioSystem.getSourceDataLine(af);
			
		        line.open(af, Note.SAMPLE_RATE);
		        line.start();
		        for  (Note n : Note.values()) {
		            play(line, n, 500);
		            play(line, Note.REST, 10);
		        }
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