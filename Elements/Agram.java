import java.util.ArrayList;
import java.util.Scanner;

public class Agram {
	static String suits = "CDHS";
	static ArrayList<String> possibleWinners = new ArrayList<String>();
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		
		for(int i = 1; i <= 6; i++) {
			System.out.print("input "+i+": ");
			possibleWinners.add(console.next());
		}
		String compareSuit = possibleWinners.get(0).substring(1);
		System.out.println();
		int largestIndex = 0;
		int numPossible = 0;
		int compareValue = assignValue(possibleWinners.get(0));
			numPossible = markPossible(compareSuit);
			if(numPossible > 0) {
				largestIndex = findWinner(compareValue);	
				clearMarks();
			}
			else {
				markAll();
				largestIndex = findWinner(0);
			}
		clearMarks();
		System.out.println(possibleWinners.get(largestIndex));
		console.close();
			
	}
	public static int markPossible(String compareSuit) {
		int numPossible = 0;
		for(int i = 1; i < 6; i++) {
			String temp = possibleWinners.get(i);
			if(temp.substring(1).contentEquals(compareSuit)) {
				possibleWinners.set(i, possibleWinners.get(i) +"W");
				numPossible++;
			}
		}
		return numPossible;
	}
	public static void markAll() {
		for(int i = 0 ; i < possibleWinners.size(); i++) {
			possibleWinners.set(i, possibleWinners.get(i) +"W");
		}
	}
	public static int findWinner(int compareValue) {
		int successes = 0;
		int largestValue = 14;
			int largestIndex = 0;
			for(int i = 1; i < possibleWinners.size(); i++) {
				String temp = possibleWinners.get(i);
				int tempValue = assignValue(temp);
				if(temp.length() > 2) {
					if(tempValue > compareValue) {
						if(tempValue < largestValue) {
							largestIndex = i;
							largestValue = tempValue;
							successes++;
						}
						else if(tempValue == largestValue) {
							if(suitValue(temp) > suitValue(possibleWinners.get(largestIndex))) {
								largestIndex = i;
							}
						}
					}
				}
			}
			if(successes == 0) {
				return findWinner(0);
			}
			return largestIndex;
	}
	public static int assignValue(String temp) {
		int tempValue = 0;
		if(temp.substring(0,1).equals("A")) {
			tempValue = 1;
		}
		else if(temp.substring(0,1).equals("T")) {
			tempValue =10;
		}
		else if(temp.substring(0,1).equals("J")) {
			tempValue =11;
		}
		else if(temp.substring(0,1).equals("Q")) {
			tempValue =12;
		}
		else if(temp.substring(0,1).equals("K")) {
			tempValue =13;
		}
		else {
			tempValue = Integer.parseInt(temp.substring(0,1));
		}
		return tempValue;
	}
	public static void clearMarks() {
		for(int i = 0; i < possibleWinners.size(); i++) {
			String str = possibleWinners.get(i);
			if(str.length() > 2) {
				String temp = str.substring(0,2);
				possibleWinners.set(i, temp);
			}
		}
	}
	public static int suitValue(String str) {
		String temp = str.substring(1,2);
		if(temp.equals("C"))
			return 4;
		else if(temp.equals("D"))
			return 3;
		else if(temp.equals("H"))
			return 2;
		else if(temp.equals("S"))
			return 1;
		else
			return -1;
	}

}
