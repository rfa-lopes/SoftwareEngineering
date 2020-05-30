package fct.unl.pt.instagramplus.Utils;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.Random;

public class DefaultImage {

    private static DefaultImage instance = new DefaultImage();

    private DefaultImage() {
    }

    public static DefaultImage getInstance() {
        return instance;
    }

    //Default Image
    public String get() {
        try {
            URL url = this.getClass().getClassLoader().getResource("Images/0.jpg");
            File imgPath = new File(url.getFile());
            byte[] fileContent = Files.readAllBytes(imgPath.toPath());
            return B64Util.encode(fileContent);
        } catch (Exception e) {
            return "";
        }
    }

    //Random image from local repository for prototipe
    public String getRandom() {
        try {
            Random r = new Random();
            int low = 1;
            int high = 31;
            int result = r.nextInt(high - low) + low; //[1, 31[
            URL url = this.getClass().getClassLoader().getResource("Images/" + result + ".jpg");
            File imgPath = new File(url.getFile());
            byte[] fileContent = Files.readAllBytes(imgPath.toPath());
            return B64Util.encode(fileContent);
        } catch (Exception e) {
            return this.get();
        }
    }
}
