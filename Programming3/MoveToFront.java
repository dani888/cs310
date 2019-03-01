package cs310;
import java.util.LinkedList;
import java.util.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.*;


public class MoveToFront {

    private static final int Max = 256;

    // Apply move-to-front encoding, reading from standard input and
    // writing to standard output
    public static void encode() {
        String s = BinaryStdIn.readString();
        char[] input = s.toCharArray();

        // Store the list of chars.
        LinkedList<Integer> Slst = new LinkedList<Integer>();
        for (int i = 0; i < Max; i++)
            Slst.add(i);
        // Check whether the char is in the list.
        for (int i = 0; i < input.length; i++) {
            int idx = Slst.indexOf((int) input[i]);
            BinaryStdOut.write((char) idx, 8);
            int obj = Slst.remove(idx);
            Slst.add(0, obj);
        }

        BinaryStdOut.close();
    }

    // apply move-to-front decoding, reading from standard input and
    // writing to standard output
    public static void decode() {
        String s = BinaryStdIn.readString();
        char[] input = s.toCharArray();

        LinkedList<Integer> Slst = new LinkedList<Integer>();
        for (int i = 0; i < Max; i++)
            Slst.add(i);

        for (int i = 0; i < input.length; i++) {
            int obj = Slst.remove((int) input[i]);
            Slst.add(0, obj);
            BinaryStdOut.write((char) obj, 8);
        }

        // Total, worst, R*N, Best, N
        BinaryStdOut.close();
    }

    // if args[0] is '-', apply move-to-front encoding
    // if args[0] is '+', apply move-to-front decoding
    public static void main(String[] args) {
        if (args[0].equals("-")) encode();
        else if (args[0].equals("+")) decode();
    }
}