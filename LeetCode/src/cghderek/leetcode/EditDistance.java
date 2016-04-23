package cghderek.leetcode;

/* 72. Edit Distance
 * Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2.
 * (each operation is counted as 1 step.) You have the following 3 operations permitted on a word:
 * a) Insert a character
 * b) Delete a character
 * c) Replace a character
 */
public class EditDistance {
	// use dynamic programming to compute the edit distance
	public int minDistance(String word1, String word2) {
		if(word1.isEmpty()) {
			return word2.length();
		}
		if(word2.isEmpty()) {
			return word1.length();
		}
		int length1 = word1.length();
		int length2 = word2.length();
		int m = length1 + 1;
		int n = length2 + 1;
		int[][] dynamicTable = new int[m][n];
		// initialize the dynamicTable
		for(int i = 0; i < m; i++) {
			dynamicTable[i][0] = i;
		}
		for(int i = 1; i < n; i++) {
			dynamicTable[0][i] = i;
		}
		// construct the matrix dynamically
	    for(int i = 1; i < m; i++) {
	    	for(int j = 1; j < n; j++) {
	    		dynamicTable[i][j] = min(dynamicTable[i-1][j] + 1, // deletion
	    				dynamicTable[i][j-1] + 1,	// insertion
	    				dynamicTable[i-1][j-1] + (word1.charAt(i-1) == word2.charAt(j-1) ? 0 : 1)); //substitution or matches
	    	}
	    }
	    return dynamicTable[length1][length2];
	}
	
	private final int min(int a, int b, int c) {
		if(a <= b) {
			if(a <= c) {
				return a;
			} else {
				return c;
			}
		} else if(b <= c) {
			return b;
		} else {
			return c;
		}
	}
	
	public static void main(String[] args) {
		EditDistance editDistance = new EditDistance();
		System.out.println(editDistance.minDistance("abcd", "bd"));
		System.out.println(editDistance.minDistance("abcd", "bde"));
	}

}

