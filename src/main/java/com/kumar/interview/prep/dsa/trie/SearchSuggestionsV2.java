package com.kumar.interview.prep.dsa.trie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Node {
    Node[] children;
    List<String> suggestions;

    public Node() {
        this.children = new Node[26];
        this.suggestions = new ArrayList<>();
    }
}
public class SearchSuggestionsV2 {

    private final Node root = new Node();

    private void insert(String word) {
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (current.children[idx] == null) {
                current.children[idx] = new Node();

            }
            current = current.children[idx];

            if (current.suggestions.size() < 3) {
                current.suggestions.add(word);
            }
        }

    }

    /**
     * Optimize approach -> precomputed suggestions
     *
     * @param products
     *            list product
     * @param searchWord
     *            word to be searched
     * @return suggestions
     */
    public List<List<String>> searchSuggestionsV2(List<String> products, String searchWord) {

        Collections.sort(products);

        for (String product : products) {
            insert(product);
        }

        List<List<String>> result = new ArrayList<>();
        Node current = root;

        for (char ch : searchWord.toCharArray()) {
            int idx = ch - 'a';
            if (current.children[idx] != null) {
                current = current.children[idx];
            }

            if (current == null) {
                result.add(new ArrayList<>()); // no match found
            } else {
                result.add(current.suggestions);
            }

        }
        return result;
    }

}
