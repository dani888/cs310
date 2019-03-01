package cs310;
import java.util.*;
import java.util.stream.*;
import java.util.stream.IntStream;

public class Coins{
	private Integer n;
	private Integer[] m;
	private HashMap<Integer,Integer[]> map = new HashMap<Integer,Integer[]>();

	public Coins() {

	}

	private Integer[] getChange(Integer[] coins,Integer remaining) {
		if(map.get(remaining) != null){
			return map.get(remaining);
		}
		Integer[][] res = new Integer[coins.length][];
		Integer[] cleanedCoins = Arrays.stream(coins).filter(a -> a <= remaining).toArray(Integer[]::new);

		if (cleanedCoins.length == 0) {
			return new Integer[]{};
		}
		for (Integer i = cleanedCoins.length -1; i>=0 ;i-- ) {
			Integer rem = remaining - cleanedCoins[i];
			Integer[] addedCoin = new Integer[]{cleanedCoins[i]};
			Integer[] restOfCoins = map.getOrDefault(new Integer(rem), getChange(cleanedCoins,rem));
			res[i] = Stream.concat(Arrays.stream(addedCoin), Arrays.stream(restOfCoins)).toArray(Integer[]::new);
		}
		Integer[] smallest = null;
		for (Integer i = 0;i < res.length ;i++ ) {
			if(res[i] != null && (smallest == null || (smallest.length > res[i].length && smallest.length > 0))){
				smallest = res[i];
			}
		}
		map.put(remaining,smallest);

		return smallest;
	}

	public void print(){
		System.out.println("Change we need : " + n);
		System.out.println("Coins we have : " + Arrays.stream(m)
        	.map(a -> a.toString())
        	.collect(Collectors.joining(" ")));
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Integer counter = 0;
		Coins coin = new Coins();

		while(input.hasNextLine() && counter < 2){
			if(counter == 0){
				coin.n = Integer.valueOf(input.nextLine());
			}else if (counter <= 1){
				coin.m = Arrays.stream(input.nextLine().split(" ")).map(a -> new Integer(a)).toArray(Integer[]::new);
			}
			counter++;
		}
		coin.print();
		Integer[] change = coin.getChange(coin.m,coin.n);
		System.out.println("Number of coins " + change.length);
		System.out.print("Coins : " + Arrays.stream(change)
        	.map(a -> a.toString())
        	.collect(Collectors.joining(" ")));
		System.out.println("");
	}
}