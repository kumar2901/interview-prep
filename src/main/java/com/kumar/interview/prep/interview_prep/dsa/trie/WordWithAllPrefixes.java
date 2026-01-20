package com.kumar.interview.prep.interview_prep.dsa.trie;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Problem: Longest Word with All Prefixes
 *
 * Given an array of strings {@code words}, find the longest word in the array such that
 * every prefix of the word is also present in the array.
 *
 * If there are multiple answers with the same maximum length, return the word that is
 * lexicographically smallest.
 * If no such word exists, return an empty string.
 *
 * Definition:
 * A prefix of a word is any leading substring that can be obtained by removing
 * zero or more characters from the end of the word.
 *
 * Example:
 * Input:
 * words = ["a", "ap", "app", "appl", "apple", "apply"]
 *
 * Valid words with all prefixes present:
 * - "a"        -> prefixes: ["a"]
 * - "ap"       -> prefixes: ["a", "ap"]
 * - "app"      -> prefixes: ["a", "ap", "app"]
 * - "appl"     -> prefixes: ["a", "ap", "app", "appl"]
 * - "apple"    -> prefixes: ["a", "ap", "app", "appl", "apple"]
 *
 * Output:
 * "apple"
 *
 * Explanation:
 * Both "apple" and "apply" have the same length, but "apple" is lexicographically smaller.
 *
 * Constraints:
 * - 1 <= words.length <= 10^5
 * - 1 <= words[i].length <= 100
 * - words[i] consists of lowercase English letters only
 */

public class WordWithAllPrefixes {

    private final TrieNode root;

    private String result="";

    public  WordWithAllPrefixes(){
        root=new TrieNode();
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


    public String longestWord(String[] words) {

        for(String word:words){
            insert(word);
        }

       dfs(root,new StringBuilder());

       return result;

    }

    private void dfs(TrieNode node, StringBuilder path) {
        if(path.length()>result.length()){
            result= path.toString();
        }

        for(int i=0;i<26;i++){
            TrieNode child=node.children[i];

            if(child!=null && child.isEndOfWord){
                char ch=(char)('a'+i);
                path.append(ch);
                dfs(child,path);
                path.deleteCharAt(path.length()-1);
            }
        }
    }


    public String longestWordWithoutTrie(String[] words) {
        // Sort words lexicographically
        Arrays.sort(words);

        Set<String> built = new HashSet<>();
        String result = "";

        for (String word : words) {
            // If word length is 1, or its prefix exists in the set
            if (word.length() == 1 || built.contains(word.substring(0, word.length() - 1))) {
                built.add(word);

                // Update result if longer word is found
                if (word.length() > result.length()) {
                    result = word;
                }
            }
        }

        return result;
    }


     void main() {
        String[] words=new String[]{"a", "ap", "app", "appl", "apple", "apply","applixy"};
         System.out.println(longestWord(words));
         System.out.println(longestWordWithoutTrie(words));
    }
}
