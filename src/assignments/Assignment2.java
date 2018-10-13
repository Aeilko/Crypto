package assignments;

import ciphers.LFSR;
import utils.Cast;
import utils.Display;

import java.util.TreeMap;

public class Assignment2 {

    public static void main(String[] args){
        boolean[] settings;
        boolean[] state;

        // Question 1
        // Part A
//        settings  = new boolean[]{false, true, true, true, false, false, false, true, false, true};
        state     = new boolean[]{false, true, false, true, false, true, false, true, false, true};

        // Part B
        settings  = new boolean[]{false, true, false, true, false, false, false, true, false, true};

        LFSR l = new LFSR(settings, state);
        int maxPeriod = ((int) Math.pow(2, settings.length))-1;
        System.out.println("Max period: " + maxPeriod);
        TreeMap<String, Integer> found = new TreeMap<String, Integer>();
        for(int i = 0; i < maxPeriod; i++){
            String stateS = Cast.toString(l.getState());
            boolean bit = l.nextValue();
//            System.out.println(stateS + " - " + bit);

            if(found.containsKey(stateS)){
                System.out.println("Double state found at indices (" + found.get(stateS) + ", " + i + ")");
                break;
            }
            else{
                found.put(stateS, i);
            }
        }
    }
}