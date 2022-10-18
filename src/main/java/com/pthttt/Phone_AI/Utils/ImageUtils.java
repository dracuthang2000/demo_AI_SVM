package com.pthttt.Phone_AI.Utils;
import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.util.Objects;

public class ImageUtils {
    public static String downloadImage(String imageName,String binaryImage,String URL_IMG) {
        if (!Objects.isNull(imageName)) {
            try {
                String[] strings = binaryImage.split(",");
                byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
                File file = new File(URL_IMG + imageName);
                OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
                outputStream.write(data);
                return "SUCESS";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "FAIL";
    }
}
