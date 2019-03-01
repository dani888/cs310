import java.util.*;


public class Match {
	public static HashMap<String,Integer> map = new HashMap<String,Integer>();
	public static HashMap<String,String[]> sequenceMap = new HashMap<String,String[]>();

	public Match() {

	}

	public Integer match(String a, String b) {
		if(a.length() == 0) return 0;
		//match lengthn & ensure b is the shorter one
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
		//map key
		Integer min,p1,p2,penaltyNum;
		String key = a + b;
		if(map.get(key) != null){
			min = map.get(key);
		}else{
			penaltyNum = penalty(a.charAt(0),b.charAt(0));
			p1 = penaltyNum + match(a.substring(1),b.substring(1));
			int indexOfSpace = b.indexOf("-");
			if(indexOfSpace > 0){
				//b remove 1 -
				//b add - in the beginning
				if(indexOfSpace == b.length()-1){
					b = "-" + b.substring(0,indexOfSpace);
				}else{
					b = "-" + b.substring(0,indexOfSpace) + b.substring(indexOfSpace+1);
				}
				penaltyNum = 2;
				p2 = 2 + match(a.substring(1),b.substring(1));
			}else{
				p2 = p1;
			}
			min = p1 < p2 ? p1 : p2;
			map.put(key,min);
			sequenceMap.put(key, new String[]{a,b});
		}

		return min;

	} 
	// return the optimal match between
	// the strings a and b
	//
	// return null if either string is null
	// or if either string is length 0

	public int penalty(char a, char b) {
		if(a == b){
			return 0;
		}else if(a == '-' || b == '-'){
			return 2;
		}else{
			return 1;
		}
	}



	public static void main(String[] args) {
		String a = "AACAGTTACC";
		String b = "TAAGGTCA";
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