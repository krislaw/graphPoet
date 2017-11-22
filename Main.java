package assignment3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class Main {
    /**
     * Example program using GraphPoet.
     */

        /**
         * Generate example poetry.
         *
         * @param args unused
         * @throws IOException if a poet corpus file cannot be found or read
         */
        public static void main(String[] args) throws IOException {
            try {
                boolean bonus_enable = true;
                String bonusInput = "Prof. Thomaz is interested in health, disease, and wearable sensors!!! Peoples activities. People's activities.";

                if(bonus_enable) {
                    //Document doc = Jsoup.connect("http://users.ece.utexas.edu/~ethomaz/");
                    URL url = new URL("http://users.ece.utexas.edu/~ethomaz/");
                    Bonus bonus = new Bonus(url);
                    String scrapedCorpus = bonus.getString();
                    final GraphPoet nimoy = new GraphPoet(scrapedCorpus);
                    System.out.println(nimoy.poem(bonusInput));
                }
                else {
                    final GraphPoet nimoy = new GraphPoet(new File("src/assignment3/corpus.txt"));
                    System.out.println(nimoy.poem(new File("src/assignment3/input.txt")));
                }
            }
            catch(IOException ex){
                System.out.println("Cannot read input file(s).");
            }
        }
}
