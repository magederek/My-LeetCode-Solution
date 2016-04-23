package cghderek.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/* 290. Word Pattern
 * 
 * Given a pattern and a string str, find if str follows the same pattern.
 * Here follow means a full match, such that there is a bijection between
 * a letter in pattern and a non-empty word in str.
 * 
 * Examples:
 *   pattern =  "abba", str = "dog cat cat dog" should return true.
 *   pattern = "abba", str = "dog cat cat fish" should return false.
 *   pattern = "aaaa", str = "dog cat cat dog" should return false.
 *   pattern = "abba", str = "dog dog dog dog" should return false.
 * Notes:
 *   You may assume pattern contains only lowercase letters, and str 
 *   contains lowercase letters separated by a single space. 
 */

public class WordPattern {
	
	public boolean wordPattern(String pattern, String str) {
		String[] strArray = str.split(" ");
		if(strArray.length != pattern.length()) {
			return false;
		}
		// use two map to check if words in str and char in pattern is ONE-TO-ONE relation
		Map<String, Character> scMap = new HashMap<>();
		Map<Character, String> csMap = new HashMap<>();
		Character previousChar;
		String previousString;
		char[] strPattern = new char[strArray.length];
		// the first hashing to check whether the same word has different chars in pattern
		for(int i = 0; i < strArray.length; i++) {
			previousChar = scMap.put(strArray[i], pattern.charAt(i));
			if(previousChar != null && previousChar != pattern.charAt(i)) {
				return false;
			}
		}
		// the second hashing to check whether the different words have the same char in pattern
		for(int i = 0; i < strArray.length; i++) {
			previousString = csMap.put(pattern.charAt(i), strArray[i]);
			if(previousString != null && !previousString.equals(strArray[i])) {
				return false;
			}
		}
		return true;
    }

	public static void main(String[] args) {
		String pattern1 = "abba"; String str1 = "dog cat cat dog";
		String pattern2 = "abba"; String str2 = "dog cat cat fish";
		String pattern3 = "aaaa"; String str3 = "dog cat cat dog";
		String pattern4 = "abba"; String str4 = "dog dog dog dog";
		WordPattern wp = new WordPattern();
		System.out.println(wp.wordPattern(pattern1, str1));
		System.out.println(wp.wordPattern(pattern2, str2));
		System.out.println(wp.wordPattern(pattern3, str3));
		System.out.println(wp.wordPattern(pattern4, str4));
	}

}

// a better solution use HashSet instead of HashMap (by Blankj)
class Solution {
    public boolean wordPattern(String pattern, String str) {
        int len = pattern.length();
        String[] strs = str.split(" ");
        int size = strs.length;
        if (len != size)
            return false;
        String[] map = new String[26];
        HashSet<String> set = new HashSet<String>();
        for (int i = 0; i < len; ++i) {
            int idx = pattern.charAt(i) - 'a';
            String s = strs[i];
            if (map[idx] == null) {
                if (set.contains(s))
                    return false;
                map[idx] = s;
                set.add(s);
            } else if (!map[idx].equals(s))
                return false;
        }
        return true;
    }
}
