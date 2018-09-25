package ciphers;

public class ShiftCipher {
	
	private int shift;
	
	public ShiftCipher(int shift) {
		this.shift = shift;
	}
	
	public ShiftCipher(char key) {
		this.shift = charToInt(key);
	}
	
	public char encrypt(char p) {
		int i = (charToInt(p)+this.shift)%26;
		return intToChar(i);
	}
	
	public String encrypt(String plaintext) {
		String result = "";
		for(int i = 0; i < plaintext.length(); i++) {
			result = result + encrypt(plaintext.charAt(i));
		}
		return result;
	}
	
	public char decrypt(char c) {
		int i = ((charToInt(c)-this.shift)+26)%26;
		if(i < 0 )
			System.out.println(i);
		return intToChar(i);
	}
	
	public String decrypt(String ciphertext) {
		String result = "";
		for(int i = 0; i < ciphertext.length(); i++) {
			result = result + decrypt(ciphertext.charAt(i));
		}
		return result;
	}
	
	public static int charToInt(char c) {
		int result = (int) c;
		result -= 97;
		return result;
	}
	
	public static char intToChar(int i) {
		char result;
		i += 97;
		result = (char) i;
		
		return result;
	}
}
