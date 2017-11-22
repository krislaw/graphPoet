package assignment3;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.jsoup.Jsoup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Bonus {
    URL url;
    String s;

    public Bonus(URL u){
        url = u;
        s = u.toString();
    }

    public String getString() throws IOException{
        try{
            String str = "";
            URLConnection con = url.openConnection();

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line;
            int flag = 0;
            while ((line = br.readLine()) != null) {
                if(line.contains("div")){
                    flag = flag ^ 1;
                }
                if(flag == 1){
                    //line = line.toLowerCase();
                    line = Jsoup.parse(line).text();
                    line = line.trim().replaceAll(" + ", " ");
                    str = str.concat(line + " ");
                }
            }
            br.close();
            return str;
        }
        catch (IOException ex){
            System.out.println("We couldn't get a string from this url.");
        }
        return "";
    }

    public void printPage() throws IOException{
        try {
            URLConnection con = url.openConnection();

            // open the stream and put it into BufferedReader
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));

            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                System.out.println(inputLine);
            }
            br.close();

            System.out.println("Done");

        }
        catch(Exception ex){
            System.out.println("We couldn't print this url.");
            System.exit(-1);
        }
    }
}
