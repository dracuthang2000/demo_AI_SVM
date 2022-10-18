package com.pthttt.Phone_AI.Utils;

import com.pthttt.Phone_AI.model.dto.PhoneDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class RenderLabel {
    public static String renderLabel(PhoneDto dto){
        Double ppi = sqrt(pow(dto.getResolution1(),2)+pow(dto.getResolution2(),2))/dto.getWidescreen();
        try {
            ProcessBuilder builder = new ProcessBuilder("python",
                    "D:\\PTIT_MTT\\PTUDTT\\Phone_AI\\src\\main\\resources\\python\\svm.py"
            ,"Sheet1",dto.getBrand().getPoint().toString(),
                    dto.getCpu().getPoint().toString(),dto.getRam().toString(),
                    ppi.toString(),dto.getRom().toString());
            Process process = builder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader readers = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            String lines = "";
            String label = "";
            while ((lines=reader.readLine())!=null){
                label = lines;
            }
            return label;
        }catch (IOException e){
            e.printStackTrace();
            return "";
        }
    }
}
