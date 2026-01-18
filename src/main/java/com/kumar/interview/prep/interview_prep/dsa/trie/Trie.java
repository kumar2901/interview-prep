package com.kumar.interview.prep.interview_prep.dsa.trie;

class Trie {
    private final TrieNode root;

    public Trie() {

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
        }
        current.isEndOfWord = true;
    }

    public boolean search(String word) {

        TrieNode current = root;

        for (int i = 0; i < word.length(); i++) {

            int idx = word.charAt(i) - 'a';
            if (current.children[idx] == null) {
                return false;
            }
            current = current.children[idx];
        }

        return current != null && current.isEndOfWord;

    }

    public boolean startsWith(String prefix) {

        TrieNode current = root;

        for (int i = 0; i < prefix.length(); i++) {
            int idx = prefix.charAt(i) - 'a';
            if (current.children[idx] == null) {
                return false;
            }
            current = current.children[idx];
        }

        return true;

    }

    static void main() {
        Trie trie = new Trie();

        trie.insert("apple");
        System.out.println(trie.search("apple")); // return True
        System.out.println(trie.search("app")); // return False
        System.out.println(trie.startsWith("app")); // return True
        trie.insert("app");
        System.out.println(trie.search("app")); // return True

    }
}
