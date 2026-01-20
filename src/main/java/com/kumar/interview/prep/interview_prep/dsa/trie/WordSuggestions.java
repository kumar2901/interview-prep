package com.kumar.interview.prep.interview_prep.dsa.trie;

import java.util.Arrays;
import java.util.List;

public class WordSuggestions {

    /**
     * Leetcode: 1268. Search Suggestions System
     */
    static void main() {
        SearchSuggestions suggestions = new SearchSuggestions();
        List<String> products = Arrays.asList("mobile", "mouse", "moneypot", "monitor", "mousepad");
        System.out.println(suggestions.searchSuggestionsV1(products, "mouse"));

        SearchSuggestionsV2 searchSuggestionsV2 = new SearchSuggestionsV2();
        System.out.println(searchSuggestionsV2.searchSuggestionsV2(products, "mouse"));
    }
}
