package com.kumar.interview.prep.dsa.hash_map;

import java.util.*;

/**
 * Implement a Word Search Engine, given a list of documents with text, return the document ids that the given word
 * belongs in. Followup: Search a phrase
 */

class Document {
    String id;
    String text;
    public Document(String id, String text) {
        this.id = id;
        this.text = text;
    }

    @Override
    public String toString() {
        return "Document{id='" + id + "', text='" + text + "'}";
    }
}
class SearchEngine {
    private final List<Document> documents;
    private final Map<String, Map<String, Set<Integer>>> index;

    public SearchEngine() {
        documents = new ArrayList<>();
        index = new HashMap<>();
    }

    public void init(List<Document> documents) {
        this.documents.addAll(documents);

        this.createIndex(documents);
    }

    public void addDocument(Integer id, String text) {
        Document document = new Document(id.toString(), text);
        documents.add(document);
        create(document);

    }

    private void create(Document document) {
        String[] words = split(document.text);
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (!word.isEmpty()) {
                index.computeIfAbsent(word, k -> new HashMap<>()).computeIfAbsent(document.id, k -> new HashSet<>())
                        .add(i);
            }
        }
    }

    private void createIndex(List<Document> documents) {
        for (Document document : documents) {
            create(document);
        }
        System.out.println("Index= " + index);
    }

    // fetch document Ids where entire phrase exists
    public List<String> searchWord(String phrase) {
        String[] words = split(phrase);
        if (words.length == 0) {
            return Collections.emptyList();
        }
        Map<String, Set<Integer>> firstWordDocs = index.get(words[0]);
        if (firstWordDocs == null) {
            return Collections.emptyList();
        }
        Set<String> result = new HashSet<>(firstWordDocs.keySet());

        for (int i = 1; i < words.length; i++) {
            Map<String, Set<Integer>> nextWordDocs = index.get(words[i]);
            if (nextWordDocs == null) {
                return Collections.emptyList();
            }
            result.retainAll(nextWordDocs.keySet());
        }
        return new ArrayList<>(result);
    }

    private String[] split(String str) {
        if (str == null || str.trim().isEmpty()) {
            return new String[0];
        }
        return str.toLowerCase().replaceAll("[^a-zA-Z0-9 ]", "").split("\\s+");
    }

    private void intersection(Set<String> result, Set<String> documentIds) {
        result.retainAll(documentIds);
    }

    public List<String> searchPhrase(String phrase) {
        List<String> result = new ArrayList<>();
        for (Document doc : documents) {
            if (doc.text.contains(phrase)) {
                result.add(doc.id);
            }
        }
        return result;
    }
}
public class WordSearchEngineDemo {
    static void main(String[] args) {
        List<Document> documents = new ArrayList<>();

        documents.add(new Document("1", "The    quick brown fox jumps over   the lazy dog"));
        documents.add(new Document("2", "The brown fox is very quick and smart"));
        documents.add(new Document("3", "Quick brown foxes are    fast, but lazy dogs are   slow"));

        SearchEngine searchEngine = new SearchEngine();

        searchEngine.init(documents);

        String word = "fox";
        List<String> docutmentIdList = searchEngine.searchWord(word);
        System.out.println("document ids " + docutmentIdList + " for word " + word);

        String phrase = "brown fox";
        List<String> documentIds = searchEngine.searchPhrase(phrase);
        System.out.println("document ids " + documentIds + " for phrase " + phrase);

        searchEngine.addDocument(4, "The quick brown the fox is very fast");
        System.out.println("document ids after adding new document" + searchEngine.searchWord(phrase));

    }
}
