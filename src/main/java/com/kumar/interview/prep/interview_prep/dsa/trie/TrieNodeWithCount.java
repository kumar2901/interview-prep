package com.kumar.interview.prep.interview_prep.dsa.trie;

public class TrieNodeWithCount extends TrieNode {

    int wordCount;
    int prefixCount;

    public TrieNodeWithCount() {
        super();
        this.wordCount = 0;
        this.prefixCount = 0;
    }
}
