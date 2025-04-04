package com.hyeonjs.omirailway;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

/* 
DB 입출력 안함
일단 대충 비슷하게 만들긴 함
*/

public class TimeTableRepository {

    public String read(String fileName) {
        try {
            Resource resource = new ClassPathResource("./timetable/" + fileName);
            InputStream is = resource.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String str = br.readLine();
            String line = "";
            while ((line = br.readLine()) != null) {
                str += "\n" + line;
            }
            isr.close();
            br.close();
            return str;
        } catch (Exception e) {
           System.out.println(e.toString());
        }
        return "";
    }



}
