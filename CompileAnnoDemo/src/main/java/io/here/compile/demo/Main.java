package io.here.compile.demo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

/**
 * Author:  柳汝滕
 * Email:   fingthinking@qq.com
 * Created: 2017年06月22日 16时16分
 */
public class Main {
    public static void main(String[] args) {
        String prefix = "/META-INF/services/";
        String fileName = "mFile.tmp";
        URL url = ClassLoader.getSystemResource("");
        String mPrefix = url.getFile() + prefix;
        File path = new File(mPrefix);
        if(path.exists() || path.mkdirs()){

            try {
                File file = new File(path,fileName);
                System.out.println(file);

                BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
                writer.write("heheda");
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        URL url = ClassLoader.getSystemResource(fileName);
//        if(url == null){
//            File path =
//            if(!path.exists()){
//                path.mkdirs();
//            }
//            File file = new File(fileName,);
//            file.createNewFile();
//        }
//        InputStream input = ClassLoader.getSystemResourceAsStream(fileName);
//        System.out.println(input);
//        try {
//            System.out.println(input.available());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
