/**
 *  Project			:  A09 Team Project
 *  File			:  Note.java
 *  Name			:  Marcellus Barrus and Mike Lawrence
 *  Date Created	:  2013-11-16
 *	Date Due		:  2013-11-24
 * 
 *  Description: Simon Memory Game
 *  
 *  The notes that the simon memory game uses.  This was actually taken from code posted stackoverflow.com
 *  
 */

package simonMemoryGame;

public enum Note {
	REST, A4, A4$, B4, C4, C4$, D4, D4$, E4, F4, F4$, G4, G4$, A5;
    public static final int SAMPLE_RATE = 16 * 1024; // ~16KHz
    public static final int SECONDS = 2;
    private byte[] sin = new byte[SECONDS * SAMPLE_RATE];

    Note() {
        int n = this.ordinal();
        if (n > 0) {
            double exp = ((double) n - 1) / 12d;
            double f = 440d * Math.pow(2d, exp);
            for (int i = 0; i < sin.length; i++) {
                double period = (double)SAMPLE_RATE / f;
                double angle = 2.0 * Math.PI * i / period;
                sin[i] = (byte)(Math.sin(angle) * 127f);
            }
        }
    }

    public byte[] data() {
        return sin;
    }
}
