//javac -classpath ../../lib/algs4.jar -d ../ ../../src/cs310/TestPerf.java 
//java -cp ..:../../lib/algs4.jar cs310.TestPerf ../../src/tale.txt 
// ::: cs310/pa1/classes/cs310$ 

package cs310;
import edu.princeton.cs.algs4.*;
import java.util.*;
import java.nio.file.*;
import java.nio.charset.*;
import java.io.*;

public class TestPerf<Key, Value> {
	public LinearProbingHashST<String, Integer> lph = new LinearProbingHashST<String, Integer>();
	public SeparateChainingHashST<String, Integer> sch = new SeparateChainingHashST<String, Integer>();
	public ST st = new ST();
	public SequentialSearchST ss = new SequentialSearchST();
	public String[] words;

	public TestPerf(String file) {
		words = new In(file).readAllStrings();

	}
	public static void main(String[] args) {
		TestPerf test = new TestPerf(args[0]);     

		StdOut.println("Bello!");

		//LinearProbingHashST
		long timerer1 = System.currentTimeMillis(); 
		for (String word : test.words) {
			if (test.lph.contains(word)) {
				Integer value = (Integer)test.lph.get(word);
				test.lph.put(word, value + 1);
			} else {
				test.lph.put(word, 1);
			}
			
			// StdOut.print(word + " ");
		}


		long time1 = System.currentTimeMillis() - timerer1;
		StdOut.print("Milisec's it took to count words in LinearProbingHashST :");
		StdOut.println(time1);


		//SeperatChainingHashST
		long timerer2 = System.currentTimeMillis(); 
		for (String word : test.words) {
			if (test.sch.contains(word)) {
				Integer value = (Integer)test.sch.get(word);
				test.sch.put(word, value + 1);
			} else {
				test.sch.put(word, 1);
			}
			
			// StdOut.print(word + " ");
		}
		long time2 = System.currentTimeMillis() - timerer2;
		StdOut.print("Milisec's it took to count words in SeperatChainingHashST :");
		StdOut.println(time2);


		List<String> lines = new ArrayList<String>();
		Iterable<String> keys = test.sch.keys();
		for (String key : keys) {
			lines.add(key);
		}
		Path file = Paths.get("the-file-name.txt");
		try{
			Files.write(file, lines, Charset.forName("UTF-8"));
		}catch(IOException e){
			StdOut.println("File could not be saved");
		}



		// Symbol Table ST
		long timerer3 = System.currentTimeMillis(); 
		for (String word : test.words) {
			if (test.st.contains(word)) {
				Integer value = (Integer)test.st.get(word);
				test.st.put(word, value + 1);
			} else {
				test.st.put(word, 1);
			}
			
			// StdOut.print(word + " ");
		}
		long time3 = System.currentTimeMillis() - timerer3;
		StdOut.print("Milisec's it took to count words in Symbol Table ST :");
		StdOut.println(time3);




		// SequentialSearchST
		long timerer4 = System.currentTimeMillis(); 
		for (String word : test.words) {
			if (test.ss.contains(word)) {
				Integer value = (Integer)test.ss.get(word);
				test.ss.put(word, value + 1);
			} else {
				test.ss.put(word, 1);
			}
			
			// StdOut.print(word + " ");
		}
		long time4 = System.currentTimeMillis() - timerer4;
		StdOut.print("Milisec's it took to count words in SequentialSearchST :");
		StdOut.println(time4);
		// StdOut.println(test.words);
	}

}