package fct.unl.pt.instagramplus.Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class DefaultImage {

    private static DefaultImage instance=new DefaultImage();

   private DefaultImage() {

    }

   public static DefaultImage getInstance(){
       return instance;
   }

    public String get() {
        BufferedReader reader;
        try{
            URL url=this.getClass().getClassLoader().getResource("default.txt");
            reader=new BufferedReader(new FileReader(url.getFile()));
            return reader.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
