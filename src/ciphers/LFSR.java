package ciphers;

public class LFSR {

    private int length;
    private boolean[] settings;
    private boolean[] state;

    public LFSR(boolean[] settings){
        this(settings, new boolean[settings.length]);
    }

    public LFSR(boolean[] settings, boolean[] initialState){
        this.length = settings.length;
        this.settings = settings;
        this.state = initialState;
    }

    public boolean[] getState(){
        return this.state;
    }

    /**
     * Shifts the register and calculates the input bit.
     * @return The rightmost/least significant bit.
     */
    public boolean nextValue(){
        // The most significant bit, which will be pushed out
        boolean out = this.state[this.length-1];
        // The value which will be inserted at the end
        boolean in = (this.settings[this.length-1] ? out : false);

        // Shift all bits to the right and possibly XOR the input bit
        for(int i = this.length-2; i >= 0; i--){
            // Check if this should be XORed
            if(this.settings[i])
                in = in^this.state[i];

            // Shift right
            this.state[i+1] = this.state[i];
        }

        // Set the input bit
        this.state[0] = in;

        return out;
    }
}
