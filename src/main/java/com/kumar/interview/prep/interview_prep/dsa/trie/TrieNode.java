package com.kumar.interview.prep.interview_prep.dsa.trie;

public class TrieNode {
    TrieNode[] children;
    boolean isEndOfWord;
    int wordCount;
    int prefixCount;

    public TrieNode() {
        children = new TrieNode[26];
        isEndOfWord = false;
        wordCount = 0;
        prefixCount = 0;
    }
}
