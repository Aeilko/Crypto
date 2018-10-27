package ciphers;

import utils.Euclidean;

import java.math.BigInteger;

public class RSA {

    // All settings are final, so if they are set in the constructor they can't be written to. In that case they can be public so we can read them.
    public final int p;
    public final int q;
    public final int N;
    public final int e;
    public final int d;

    public RSA(int p, int q){
        this.p = p;
        this.q = q;
        this.N = p*q;
        // TODO Not finished yet
        this.e = 0;
        this.d = 0;
    }

    public RSA(int p, int q, int e){
        this.p = p;
        this.q = q;
        this.N = p*q;
        this.e = e;
        this.d = Euclidean.inverse(e, (p-1)*(q-1));
    }

    public int encrypt(int p){
        BigInteger c = BigInteger.valueOf(p);
        c = c.pow(this.e).mod(BigInteger.valueOf(this.N));
        return c.intValue();
    }

    public int decrypt(int c){
        BigInteger p = BigInteger.valueOf(c);
        p = p.pow(this.d).mod(BigInteger.valueOf(this.N));
        return p.intValue();
    }
}