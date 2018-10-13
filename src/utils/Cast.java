package utils;

public class Cast {

    public static String toString(boolean[] in){
        String result = "";
        for(boolean b: in){
            result += (b ? "" + 1 : "" + 0);
        }
        return result;
    }

    public static int toInteger(boolean[] in){
        int res = 0;
        for(int i = 0; i < in.length; i++){
            if(!in[i])
                continue;

            int val = (int) Math.pow(2, in.length-1-i);
            res += val;

        }
        return res;
    }
}
