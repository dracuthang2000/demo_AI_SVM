package com.pthttt.Phone_AI.Utils;

import com.pthttt.Phone_AI.model.dto.PhoneDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class RenderLabel {
    public static String renderLabel(PhoneDto dto){
        Double ram = 0d;
        Double rom = 0d;
        Double brand = 0d;
        Double cpu = 0d;
        Double ppi = sqrt(pow(dto.getResolution1(),2)+pow(dto.getResolution2(),2))/dto.getWidescreen();
        if (dto.getData()=="Sheet1"){
            ram = (double)dto.getRam();
            rom = (double)dto.getRom();
            brand = (double)dto.getBrand().getPoint();
            cpu = (double)dto.getCpu().getPoint();
            ppi = ppi/526;
        }else{
            ram = (double)dto.getRam()/12;
            rom = (double)dto.getRom()/1024;
            brand = (double)dto.getBrand().getPoint()/10;
            cpu = (double)dto.getCpu().getPoint()/100;
            ppi = ppi/526;
        }
        try {
            ProcessBuilder builder = new ProcessBuilder("python",
                    "D:\\PTIT_MTT\\PTUDTT\\Phone_AI\\src\\main\\resources\\python\\svm.py"
            ,dto.getData(),brand.toString(),
                    cpu.toString(),ram.toString(),
                    ppi.toString(),rom.toString());
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
