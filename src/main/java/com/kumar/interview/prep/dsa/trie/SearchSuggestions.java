package com.kumar.interview.prep.dsa.trie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchSuggestions {

    private final TrieNode root = new TrieNode();

    private void insert(String word) {
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

    /**
     * Classic approach -> use startsWith +dfs
     *
     * @param products
     *            list product
     * @param searchWord
     *            word to be searched
     * @return suggestions
     */
    public List<List<String>> searchSuggestionsV1(List<String> products, String searchWord) {

        List<List<String>> result = new ArrayList<>();

        Collections.sort(products);

        for (String pr : products) {
            insert(pr);
        }

        StringBuilder prefix = new StringBuilder();
        for (char ch : searchWord.toCharArray()) {
            prefix.append(ch);
            result.add(searchProductsSByPrefix(prefix));
        }

        return result;
    }

    private List<String> searchProductsSByPrefix(StringBuilder prefix) {

        TrieNode current = root;
        for (int i = 0; i < prefix.length(); i++) {
            int idx = prefix.charAt(i) - 'a';
            if (current.children[idx] == null) {
                return new ArrayList<>();
            }
            current = current.children[idx];
        }

        List<String> suggestions = new ArrayList<>();
        dfs(current, new StringBuilder(prefix), suggestions);

        return suggestions;
    }

    private void dfs(TrieNode current, StringBuilder path, List<String> suggestions) {
        if (suggestions.size() >= 3) {
            return;
        }
        if (current != null && current.isEndOfWord) {
            suggestions.add(path.toString());
        }

        for (int i = 0; i < 26; i++) {
            if (current.children[i] != null) {
                char ch = (char) (i + 'a');
                path.append(ch);
                dfs(current.children[i], path, suggestions);
                path.deleteCharAt(path.length() - 1);
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
    private List<List<String>> searchSuggestionsV2(List<String> products, String searchWord) {

        return new ArrayList<>();
    }
}
