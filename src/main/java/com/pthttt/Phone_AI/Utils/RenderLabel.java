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
        if (dto.getData().equals("DataBigNum")){
            ram = (double)dto.getRam();
            rom = (double)dto.getRom();
            brand = (double)dto.getBrand().getPoint();
            cpu = (double)dto.getCpu().getPoint();
        }else{
            ram = (double)dto.getRam()/18;
            rom = (double)dto.getRom()/1024;
            brand = (double)dto.getBrand().getPoint()/10;
            cpu = (double)dto.getCpu().getPoint()/100;
            ppi = ppi/526;
        }
        try {
            String data = "";
            if(dto.getData().equals("DataBigNum")){
                data="DataBigNum.sav";
            }else if(dto.getData().equals("Data(0-1)")){
                data="Data(0-1).sav";
            }else {
                data = "DataTest.sav";
            }
            ProcessBuilder builder = new ProcessBuilder("python",
                    "D:\\PTIT_MTT\\PTUDTT\\Phone_AI\\src\\main\\resources\\python\\loadfile.py"
            ,data,brand.toString(),
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
//            while ((lines=readers.readLine())!=null){
//                System.out.println("err: "+lines);
//            }
            return label;
        }catch (IOException e){
            e.printStackTrace();
            return "";
        }
    }
}
