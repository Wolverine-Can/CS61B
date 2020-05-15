import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanEncoder {
    public static Map<Character, Integer> buildFrequencyTable(char[] inputSymbols) {
        Map<Character, Integer> frequencyTable = new HashMap<>();
        for (Character ch : inputSymbols) {
            if (frequencyTable.containsKey(ch)) {
                frequencyTable.put(ch, frequencyTable.get(ch) + 1);
            } else {
                frequencyTable.put(ch, 1);
            }
        }
        return frequencyTable;
    }
    public static void main(String[] args) {
        char[] inputSymbols = FileUtils.readFile(args[0]);
        Map<Character, Integer> frequencyTable = HuffmanEncoder.buildFrequencyTable(inputSymbols);
        BinaryTrie decodingTrie = new BinaryTrie(frequencyTable);
        ObjectWriter file = new ObjectWriter(args[0] + ".huf");
        file.writeObject(decodingTrie);
        file.writeObject(frequencyTable.size());
        Map<Character, BitSequence> lookupTable = decodingTrie.buildLookupTable();
        List<BitSequence> listOfBitS = new ArrayList<>();
        for (Character ch : inputSymbols) {
            listOfBitS.add(new BitSequence(lookupTable.get(ch)));
        }
        BitSequence bitSequence = BitSequence.assemble(listOfBitS);
        file.writeObject(bitSequence);
    }
}
