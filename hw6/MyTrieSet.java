import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyTrieSet implements TrieSet61B{
    private static final int R = 128;
    private Node root;
    private static class Node{
        private char ch;
        private boolean isKey;
        private HashMap<Character, Node> map;
        private Node() {
            map = new HashMap<>();
        }
        private Node(char c, boolean isKey) {
            ch = c;
            this.isKey = isKey;
            map = new HashMap<>();
        }
    }
    public MyTrieSet(){
        root = new Node();
    }
    public void clear() {
        root = new Node();
    }
    public boolean contains(String key) {
        if (key == null || key.length() < 1) {
            return false;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                return false;
            }
            curr = curr.map.get(c);
        }
        return curr.isKey;
    }
    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node(c, false));
            }
            curr = curr.map.get(c);
        }
        curr.isKey = true;
    }
    private List<String> getAllString(Node P) {
        List<String> StringList = new ArrayList<>();
        if (P.isKey) {
            StringList.add(Character.toString(P.ch));
        }
        for (Character key : P.map.keySet()) {
            for (String strings : getAllString(P.map.get(key))) {
                StringList.add(P.ch + strings);
            }
        }
        return StringList;
    }


    public List<String> keysWithPrefix(String prefix) {
        List<String> keysWithPrefix = new ArrayList<>();
        List<String> StringAfterPrefix;
        if (prefix == null || prefix.length() < 1) {
            return keysWithPrefix;
        }
        Node curr = root;
        for (int i = 0, n = prefix.length(); i < n; i++) {
            char c = prefix.charAt(i);
            if (!curr.map.containsKey(c)) {
                return keysWithPrefix;
            }
            curr = curr.map.get(c);
        }
        StringAfterPrefix = getAllString(curr);
        for (String s : StringAfterPrefix) {
            keysWithPrefix.add(prefix.substring(0, prefix.length()-1) + s);
        }
        return keysWithPrefix;
    }

    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }
}
