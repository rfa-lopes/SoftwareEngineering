package fct.unl.pt.instagramplus.Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

public class DefaultImage {

    private static DefaultImage instance = new DefaultImage();

    private static BufferedReader reader;

    private DefaultImage() {
    }

    public static DefaultImage getInstance() {
        return instance;
    }

    public String get() {
        try {
            if(reader != null)
                return reader.readLine();
            Random r = new Random();
            int low = 1;
            int high = 3;
            int result = r.nextInt(high-low) + low; //1 ou 2

            URL url = this.getClass().getClassLoader().getResource("Images/default"+result+".txt");
            reader = new BufferedReader(new FileReader(url.getFile()));
            return reader.readLine();
        } catch (Exception e) {
            return null;
        }
    }
}
