package utils;

public class Display {
    public static void array(boolean[] in){
        for(int i = 0; i < in.length; i++){
            System.out.println("[" + i + "]\t" + in[i]);
        }
    }

    public static void bitString(boolean[] in){
        System.out.println(Cast.toString(in));
    }
}