import java.util.*;
import java.util.stream.*;
import java.util.stream.IntStream;
import java.util.Scanner;
import java.io.File;


public class EditDistance {

	public EditDistance() {

	}
	public static void main(String[] args) {
		Scanner finput;
		String a = "",b = "";
		int counter = 0;
		finput = new Scanner(System.in);
		while (finput.hasNextLine()) {
			String input = finput.nextLine();
			if(counter == 0 ){
				a = input.replace(" ","");
			}else if(counter == 1){
				b = input.replace(" ","");
			}
			counter++;
		}
		System.out.println("a : " + a);
		System.out.println("b : " + b);
		Match match = new Match();
		Integer min = match.match(a, b);
		System.out.println("looks like " + min.toString() + " is the optimal Edit Distance.");
		if (a.length() != b.length()){
			if(b.length() > a.length()){
				String temp = a;
				a = b;
				b = a;
			}
			while(a.length() > b.length()){
				b += "-";
			}
		}
		String key = a + b;
		String[] sequences = match.sequenceMap.get(key);
		String sequenceA = sequences[0];
		String sequenceB = sequences[1];
		Integer penaltyNum;
		for(int i = 0;i<sequenceA.length();i++){
			penaltyNum = match.penalty(sequenceA.charAt(i),sequenceB.charAt(i));
 			System.out.println(sequenceA.charAt(i) + " " + sequenceB.charAt(i) + " " + penaltyNum.toString());
		}
	}
}

