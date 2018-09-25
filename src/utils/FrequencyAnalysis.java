package utils;

import java.util.ArrayList;
import java.util.TreeMap;

import ciphers.ShiftCipher;

public class FrequencyAnalysis {
	
	// {' ', ',', '.', '\''}
	public static ArrayList<Character> punctuations = new ArrayList<Character>() {{ this.add(' '); this.add(','); this.add('.'); this.add('\''); }};
	
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
	public static void displayMap(TreeMap<Character, Integer> in) {
		for(char k: in.keySet()) {
			System.out.println(k + ": " + in.get(k));
		}
	}
}
