package assignments;

import ciphers.RSA;
import utils.Euclidean;

import java.math.BigInteger;

public class Assignment3 {

    public static void main(String[] args){
        // Question 1
        System.out.println("Question 1 - RSA");
        int p = 7;
        int q = 11;
        int e = 7;
        RSA r = new RSA(p, q, e);
        System.out.println("Calculated value for d: " + r.d);
        System.out.println("Decrypting c=3: " + r.decrypt(3));
        System.out.println("Encryption of the plaintext of c=3 (should be 3): " + r.encrypt(r.decrypt(3)));

        System.out.println("");

        r = new RSA(p, q, 5);
        System.out.println("gcd(60, 5): " + Euclidean.gcd(60, 5));
        System.out.println("Value for d with e=5: " + r.d);

        // Question 2
        System.out.println("");
        System.out.println("Question 2 - Example output for  part A");
        p = 983;
        q = 1031;
        e = 7;
        System.out.println("gcd(7, " + (p-1)*(q-1) + "): " + Euclidean.gcd(7, (p-1)*(q-1)));
        r = new RSA(p, q, e);
        int d100 = r.decrypt(100);
        int d75 = r.decrypt(75);
        int d50 = r.decrypt(50);
        int d50i = Euclidean.inverse(d50, p*q);
        int d300 = r.decrypt(300);

        BigInteger res = BigInteger.valueOf(d100).multiply(BigInteger.valueOf(d100)).multiply(BigInteger.valueOf(d75)).multiply(BigInteger.valueOf(d50i)).multiply(BigInteger.valueOf(d50i)).mod(BigInteger.valueOf(p*q));

        System.out.println("d100*d100*d75*d50i*d50i = " + res);
        System.out.println("d300 = " + d300);
        if(d300 == res.intValue()) {
            System.out.println("We have a match");
        }
        else{
            // This should be unreachable
            System.out.println("How did it not match?");
        }
    }
}
