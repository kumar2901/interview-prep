package com.kumar.interview.prep.dsa.unix_command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;

class UnixCommand {

    /**
     * Implement the Unix Tail -N Command
     */
    public static void tailCommand(String filePath, int n)throws IOException {
        if(n==0) return;
        File file = new File(filePath);
        if(!file.exists() || file.length()==0) {
            return;
        }
        Deque<String> queue = new LinkedList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(queue.size()==n){
                    queue.poll();
                }
                queue.offer(line);
            }

        }
        for(String line: queue) {
            System.out.println(line);
        }
    }

}
public class UnixCommandDemo {



     static void main(String[] args) throws IOException {
         UnixCommand.tailCommand("src/main/java/com/kumar/interview/prep/dsa/trie/SearchSuggestions.java",100);

    }
}
