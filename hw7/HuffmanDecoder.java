import java.util.LinkedList;

public class HuffmanDecoder {
    public static void main(String[] args) {
        ObjectReader readFile = new ObjectReader(args[0]);
        BinaryTrie decodingTrie = (BinaryTrie) readFile.readObject();
        int nofSymbols = (Integer) readFile.readObject();
        BitSequence bitSequence = (BitSequence) readFile.readObject();
        LinkedList<Character> symbols = new LinkedList<>();
        Match symbolAndBits;
        while (bitSequence.length() > 0) {
            symbolAndBits = decodingTrie.longestPrefixMatch(bitSequence);
            symbols.add(symbolAndBits.getSymbol());
            bitSequence = bitSequence.allButFirstNBits(symbolAndBits.getSequence().length());
        }
        char[] charSymbols = new char[symbols.size()];
        int i = 0;
        while (!symbols.isEmpty()) {
            charSymbols[i] = symbols.removeFirst();
            i += 1;
        }
        FileUtils.writeCharArray(args[1], charSymbols);
    }
}
