package utils;

import java.util.ArrayList;
import java.util.TreeMap;

import ciphers.ShiftCipher;

public class FrequencyAnalysis {
	
	// {' ', ',', '.', '\''}
	public static ArrayList<Character> punctuations = new ArrayList<Character>() {{ this.add(' '); this.add(','); this.add('.'); this.add('\''); }};

	public static TreeMap<Character, Double> englischFrequencies = new TreeMap<Character, Double>(){{
		this.put('a', 8.17);
		this.put('b', 1.49);
		this.put('c', 2.78);
		this.put('d', 4.25);
		this.put('e', 12.70);
		this.put('f', 2.29);
		this.put('g', 2.02);
		this.put('h', 6.09);
		this.put('i', 6.97);
		this.put('j', 0.15);
		this.put('k', 0.77);
		this.put('l', 4.03);
		this.put('m', 2.41);
		this.put('n', 6.75);
		this.put('o', 7.52);
		this.put('p', 1.93);
		this.put('q', 0.10);
		this.put('r', 5.99);
		this.put('s', 6.33);
		this.put('t', 9.01);
		this.put('u', 2.76);
		this.put('v', 0.98);
		this.put('w', 2.36);
		this.put('x', 0.15);
		this.put('y', 1.97);
		this.put('z', 0.07);
	}};

	/**
	 * Returns the frequency of each letter.
	 * @param in The string over which the frequency will be analysed
	 * @return
	 */
	public static TreeMap<Character, Integer> letter(String in) {
		TreeMap<Character, Integer> result = new TreeMap<Character, Integer>();
		
		for(char p: punctuations){
			in = in.replace(""+p, "");
		}
		
		for(int i = 0; i < in.length(); i++) {
			char c = in.charAt(i);
			
			if(!result.containsKey(c)) {
				result.put(c, 1);
			}
			else {
				result.put(c, result.get(c)+1);
			}
		}
		
		return result;
	}
	
	/**
	 * Splits the given string in i strings, where each string contains the i'th characters.
	 * Can be used to prepare for the Vigenere cipher.
	 * @param i The number of string the input should be split in.
	 * @param in The input string
	 * @return String[] containing the splitted strings
	 */
	public static String[] splitter(int i, String in) {
		String[] result = new String[i];
		for(int j = 0; j < i; j++) {
			result[j] = "";
		}
		
		for(char p: punctuations){
			in = in.replace(""+p, "");
		}
		
		int parts = (int) Math.ceil(((double) in.length())/i);
		
		for(int j = 0; j < parts; j++) {
			for(int k = 0; k < i; k++) {
				int index = j*i+k;
				if(index >= in.length())
					break;
				result[k] = result[k] + in.charAt(index);
			}
		}
		
		return result;
	}
	
	/**
	 * Return the most occurring character in the count
	 * @param in The count of the characters
	 * @return The most occurring character
	 */
	public static char highestCount(TreeMap<Character, Integer> in) {
		char mostCommon = ' ';
		int mostCount = 0;
		for(char k: in.keySet()) {
			if(mostCount < in.get(k)) {
				mostCommon = k;
				mostCount = in.get(k);
			}
		}
		return mostCommon;
	}


	public static int lowestVal(TreeMap<Integer, Double> in){
		int lowInt = 0;
		double lowVal = Double.MAX_VALUE;
		for(int k: in.keySet()){
			if(in.get(k) < lowVal){
				lowInt = k;
				lowVal = in.get(k);
			}
		}

		return lowInt;
	}


	public static TreeMap<Integer, Double> statisticalDistance(TreeMap<Character, Integer> frequencies){
		TreeMap<Integer, Double> result = new TreeMap<Integer, Double>();

		// Convert the input, which is in a occurances, to probabilities.
		int total = 0;
		for(char k: frequencies.keySet()){
			total += frequencies.get(k);
		}

		TreeMap<Character, Double> cipherFrequencies = new TreeMap<Character, Double>();
		for(char k: frequencies.keySet()){
			cipherFrequencies.put(k, ((double) frequencies.get(k))/total);
		}

		// Calculate the statistical distance for each possible shift
		for(int i = 0; i < 26; i++){
			double r = 0;

			// Check the statical distance between te letters
			for(char k: englischFrequencies.keySet()){
				// Get index of this letter in the shifter text
				int shift = (ShiftCipher.charToInt(k)+i)%26;
				// Get the probabilty in ciphertext
				double cipherProb = cipherFrequencies.containsKey(ShiftCipher.intToChar(shift)) ? cipherFrequencies.get(ShiftCipher.intToChar(shift)) : 0;
				// Probability in English, divided by 100 since it's in a percentage form
				double englishProb = englischFrequencies.get(k)/100;

				// Calculate the statistical distance
				r += Math.abs(cipherProb-englishProb);
			}

			r = r*0.5;
			result.put(i, r);
		}

		return result;
	}
	
	/**
	 * Returns the character that represents the shift from the given char to the char 'e'
	 * @param c The Character from which the shift will be calculated
	 * @return The character representing this shift
	 */
	public static char distanceToE(char c) {
		int e = 4;
		int i = ShiftCipher.charToInt(c);
		int j = ((i-e)+26)%26;		
		return ShiftCipher.intToChar(j);
	}
	
	/**
	 * Displays treemaps
	 * @param in The treemap to be displayed
	 */
	public static void displayMap(TreeMap in) {
		for(Object k: in.keySet()) {
			System.out.println(k + ": " + in.get(k));
		}
	}
}
