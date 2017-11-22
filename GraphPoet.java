package assignment3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GraphPoet {
    static Graph g;

    /**
     *
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(String corpus){
        /* for bonus */
        g = new Graph();
        g.addString(corpus);
    }

    public GraphPoet(File corpus) throws IOException {
        /* Read in the File and place into graph here */
        try { /* Read in Corpus File */
            g = new Graph();
            FileReader infile = new FileReader(corpus);
            BufferedReader inBuff = new BufferedReader(infile);
            String allLines = "";
            String line;
            while ((line = inBuff.readLine()) != null) {
                allLines = allLines.concat(line + " ");
            }
            g.addString(allLines);
            inBuff.close();
        }

        catch(IOException ex) {
            System.out.println("Cannot read in file '" + corpus + "'");
            System.exit(-1);
        }
    }

    /**
     * Generate a poem.
     *
     * @param input File from which to create the poem
     * @return poem (as described above)
     */

    public String poem(String input){
        return g.generatePoem(input);

    }

    public String poem(File input) throws IOException {
        String poem = "";
        try {
            FileReader infile = new FileReader(input);
            BufferedReader inBuff = new BufferedReader(infile);
            String line;
            while ((line = inBuff.readLine()) != null) {
                poem = poem.concat(line + " ");
            }
            inBuff.close();
        }
        catch(IOException ex) {
            System.out.println("Cannot read in file '" + input + "'");
            System.exit(-1);
        }
        /* Read in input and use graph to complete poem */

        //System.out.println("poem: " + poem);
        //System.exit(100);
        poem = g.generatePoem(poem);
        return poem;
    }

}
