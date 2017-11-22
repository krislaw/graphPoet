package assignment3;

import java.io.*;
import java.util.*;

public class Graph {
    HashMap<String, Vertex> vertexList;

    Graph(){    /* constructor */
        vertexList = new HashMap();
    }

    void addString(String s){
        String[] wordList = StringToStrListRemoveFormatting(s);
        //PrintStringList(wordList);
        for(int i = 0; i < (wordList.length - 1); i++){
            Vertex a;
            Vertex b;

            /* make vertices for a,b or find them if they already exist in Map */
            if(vertexList.containsKey(wordList[i])){
                a = vertexList.get(wordList[i]);
            }
            else{
                a = new Vertex(wordList[i]);
                vertexList.put(wordList[i], a);
            }

            if(vertexList.containsKey(wordList[i + 1])){
                b = vertexList.get(wordList[i + 1]);
            }
            else{
                b = new Vertex(wordList[i + 1]);
                vertexList.put(wordList[i + 1], b);
            }
            a.addEdge(b);
            //a.printConnections();
        }
        //PrintGraph();
    }

    public String generatePoem(String s){
        ArrayList<String> poemWords = arrayOfWords(s);

        for(int i = 0; i < (poemWords.size() - 1); i++){
            String a = poemWords.get(i).toLowerCase().replaceAll("[:;/|!*()`~+-_=<>]", "");
            String b = poemWords.get(i + 1).toLowerCase().replaceAll("[:;/|!*()`~+-_=<>]", "");

            /* if both words are in the graph, search for a bridge */
            if(vertexList.containsKey(a) && vertexList.containsKey(b)){
                String bridge = vertexList.get(a).findBridge(vertexList.get(b));
                poemWords.add(i + 1, bridge);
                i++; //we don't want to bridge from bridge
            }
        }
        String poem = "";
        for(int i = 0; i < poemWords.size(); i++){
            poem = poem.concat(poemWords.get(i) + " ");
        }
        /* band-aid because I'm a bit of an idiot */
        poem = poem.replaceAll("  ", " ");
        return poem;
    }


    /* Helper Functions */

    /* Returns Array of Words */
    private String[] StringToStrListRemoveFormatting(String s){
        /* misunderstanding: we are keeping punctuation in words */
        s = s.replaceAll("[:;/|!*()`~+-_=<>],", "");
        s = s.toLowerCase();
        return s.split(" ");
    }
    private String[] StringToStrList(String s){
        return s.split(" ");
    }

    /* Returns an ArrayList of Words */
    private ArrayList<String> arrayOfWords(String s){
        String[] wordList = StringToStrList(s);
        ArrayList wordArray = new ArrayList(wordList.length);
        for(int i = 0; i < wordList.length; i++){
            wordArray.add(i, wordList[i]);
        }
        return wordArray;
    }

    private void PrintStringList(String[] str){
        for(int i = 0; i < str.length; i++) {
            System.out.print(str[i] + ", ");
        }
        System.out.println("\n");
    }

    public void PrintGraph(){
        Object[] keyListObj = vertexList.keySet().toArray();
        String[] keyList = new String[keyListObj.length];
        for(int i = 0; i < keyListObj.length; i++){
            keyList[i] = keyListObj[i].toString();
        }
        for(int i = 0; i < keyList.length; i++){
            System.out.print(keyList[i] + " -> ");
            vertexList.get(keyList[i]).printConnections();
        }
        return;
    }

}
