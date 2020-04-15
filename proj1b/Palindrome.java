public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        char theChar;
        Deque<Character> wordDeque = new LinkedListDeque();
        for (int i = 0; i < word.length(); i++) {
            theChar = word.charAt(i);
            wordDeque.addLast(theChar);
        }
        return wordDeque;
    }
    public boolean isPalindrome(String word) {
        Palindrome p = new Palindrome();
        Deque<Character> wordDeque = new LinkedListDeque();
        wordDeque = p.wordToDeque(word);
        int i = 0;
        while (i < word.length() && wordDeque.get(i) == wordDeque.get(word.length() - 1 - i)) {
            i++;
        }
        if (i == word.length()) {
            return true;
        }
        return false;
    }
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Palindrome p = new Palindrome();
        Deque<Character> wordDeque = new LinkedListDeque();
        wordDeque = p.wordToDeque(word);
        int i = 0;
        while (i < Math.round(word.length() / 2)
                && cc.equalChars(wordDeque.get(i), wordDeque.get(word.length() - 1 - i))) {
            i++;
        }
        if (i == Math.round(word.length() / 2)) {
            return true;
        }
        return false;
    }
}
