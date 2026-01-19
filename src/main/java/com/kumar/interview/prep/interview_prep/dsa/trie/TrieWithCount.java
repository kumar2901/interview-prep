package com.kumar.interview.prep.interview_prep.dsa.trie;

/**
 * LeetCode 1804 - Implement Trie II (Prefix Tree)
 *
 * Design a Trie (Prefix Tree) data structure that supports inserting words, counting exact word occurrences, counting
 * words with a given prefix, and removing words.
 *
 * Implement the Trie class with the following operations:
 *
 * <ul>
 * <li>{@code Trie()} Initializes the trie object.</li>
 * <li>{@code void insert(String word)} Inserts the string {@code word} into the trie.</li>
 * <li>{@code int countWordsEqualTo(String word)} Returns the number of times the string {@code word} was inserted into
 * the trie.</li>
 * <li>{@code int countWordsStartingWith(String prefix)} Returns the number of strings in the trie that start with the
 * string {@code prefix}.</li>
 * <li>{@code void erase(String word)} Removes one occurrence of the string {@code word} from the trie.</li>
 * </ul>
 *
 * <p>
 * Notes:
 * <ul>
 * <li>Multiple identical words may be inserted into the trie.</li>
 * <li>Calling {@code erase} will remove only one occurrence of the word.</li>
 * <li>It is guaranteed that {@code erase} is called only if the word exists in the trie.</li>
 * <li>All input strings consist of lowercase English letters.</li>
 * </ul>
 *
 * <p>
 * Example:
 *
 * <pre>
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.insert("apple");
 * trie.countWordsEqualTo("apple"); // returns 2
 * trie.countWordsStartingWith("app"); // returns 2
 * trie.erase("apple");
 * trie.countWordsEqualTo("apple"); // returns 1
 * </pre>
 *
 * Constraints:
 * <ul>
 * <li>1 ≤ word.length, prefix.length ≤ 2000</li>
 * <li>At most 3 * 10^4 calls will be made to insert, erase, countWordsEqualTo, and countWordsStartingWith.</li>
 * </ul>
 */

public class TrieWithCount {
    private final TrieNode root;
    public TrieWithCount() {
        root = new TrieNode();
    }

    public void insert(String word) {

        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (current.children[idx] == null) {
                current.children[idx] = new TrieNode();
            }
            current = current.children[idx];
            current.prefixCount = current.prefixCount + 1;
        }
        current.isEndOfWord = true;
        current.wordCount = current.wordCount + 1;

    }

    public int countWordsEqualTo(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (current.children[idx] == null) {
                return 0;
            }
            current = current.children[idx];
        }

        if (current != null && current.isEndOfWord) {
            return current.wordCount;
        }

        return 0;
    }

    public int countWordsStartingWith(String prefix) {

        TrieNode current = root;

        for (int i = 0; i < prefix.length(); i++) {
            int idx = prefix.charAt(i) - 'a';
            if (current.children[idx] == null) {
                return 0;
            }

            current = current.children[idx];
        }

        return current.prefixCount;
    }

    public void erase(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (current.children[idx] == null) {
                return;
            }
            current = current.children[idx];
            current.prefixCount = current.prefixCount - 1;
        }

        if (current.isEndOfWord) {
            current.wordCount = current.wordCount - 1;
        }

    }

    static void main() {
        TrieWithCount trie = new TrieWithCount();
        trie.insert("apple"); // trie contains: ["apple"]
        trie.insert("apple"); // trie contains: ["apple", "apple"]
        System.out.println(trie.countWordsEqualTo("apple")); // returns 2
        System.out.println(trie.countWordsStartingWith("app")); // returns 2
        trie.erase("apple"); // removes one occurrence of "apple"
        System.out.println(trie.countWordsEqualTo("apple")); // returns 1
        System.out.println(trie.countWordsStartingWith("apple")); // returns 1
        System.out.println(trie.countWordsEqualTo("cat")); // returns 1
    }
}
