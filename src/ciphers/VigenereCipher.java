package ciphers;

import utils.FrequencyAnalysis;

public class VigenereCipher {
	
	private int keyLength;
	private ShiftCipher[] ciphers;
	
	public VigenereCipher(String key) {
		this.keyLength = key.length();
		this.ciphers = new ShiftCipher[this.keyLength];
		for(int i = 0; i < this.keyLength; i++) {
			this.ciphers[i] = new ShiftCipher(key.charAt(i));
		}
	}
	
	public String encrypt(String plaintext) {
		String result = "";
		
		int j = 0;
		for(int i = 0; i < plaintext.length(); i++) {
			char c = plaintext.charAt(i);
			if(FrequencyAnalysis.punctuations.contains(c)) {
				result = result += c;
				continue;
			}
			else {
				result = result + this.ciphers[j%this.keyLength].encrypt(c);
				j++;
			}
		}
		
		return result;
	}
	
	public String decrypt(String ciphertext) {
		String result = "";
		
		int j = 0;
		for(int i = 0; i < ciphertext.length(); i++) {
			char c = ciphertext.charAt(i);
			if(FrequencyAnalysis.punctuations.contains(c)) {
				result = result += c;
				continue;
			}
			else {
				result = result + this.ciphers[j%this.keyLength].decrypt(c);
				j++;
			}
		}
		
		return result;
	}
}
