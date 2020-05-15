import edu.princeton.cs.algs4.MinPQ;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BinaryTrie implements Serializable {
    private Node root;
    private Map<Character, BitSequence> lookupTable = new HashMap<>();

    private static class Node implements Comparable<Node>, Serializable {
        private final char ch;
        private final int freq;
        private final Node left, right;

        private Node(char ch, int freq, Node left, Node right) {
            this.ch    = ch;
            this.freq  = freq;
            this.left  = left;
            this.right = right;
        }

        private boolean isLeaf() {
            return (left == null) && (right == null);
        }

        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }

    public BinaryTrie(Map<Character, Integer> frequencyTable) {
        MinPQ<Node> frequencyPQ = new MinPQ<>();
        for (Character ch : frequencyTable.keySet()) {
            frequencyPQ.insert(new Node(ch, frequencyTable.get(ch), null, null));
        }
        while (frequencyPQ.size() > 1) {
            Node left = frequencyPQ.delMin();
            Node right = frequencyPQ.delMin();
            Node parent = new Node('\0', left.freq + right.freq, left, right);
            frequencyPQ.insert(parent);
        }
        root = frequencyPQ.delMin();
        buildLookupTableHelper("", root);
    }
    public Match longestPrefixMatch(BitSequence querySequence) {
        StringBuilder bitSequence = new StringBuilder();
        Node find = root;
        int i = 0;
        while (!find.isLeaf()) {
            if (querySequence.bitAt(i) == 0) {
                bitSequence.append('0');
                find = find.left;
            } else {
                bitSequence.append('1');
                find = find.right;
            }
            i += 1;
        }
        return new Match(new BitSequence(bitSequence.toString()), find.ch);
    }
    public Map<Character, BitSequence> buildLookupTable() {
        return new HashMap<>(lookupTable);
    }
    public void buildLookupTableHelper(String preBits, Node n) {
        if (n.isLeaf()) {
            this.lookupTable.put(n.ch, new BitSequence(preBits));
        } else {
            buildLookupTableHelper(preBits + "0", n.left);
            buildLookupTableHelper(preBits + "1", n.right);
        }
    }
}
