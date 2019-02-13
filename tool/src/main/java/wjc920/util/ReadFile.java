package wjc920.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class ReadFile {
    
    public static List<String> readInFileByLine() throws IOException {
        List<String> lineList = new LinkedList<>();
//        File inFile = new File(ReadFile.class.getResource("in.txt").toString());
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("D:/in.txt")));
        String row = "";
        while(true) {
            row = reader.readLine();
            if(row == null || "".equals(row.trim())) {
                break;
            }
            lineList.add(row);
        }
        if(reader!=null){
            reader.close();
        }
        return lineList;
    }
    
    
    public static String readInFile() throws IOException {
        String content="";
        for(String line:readInFileByLine()) {
            content += line;
        }
        return content;
    }

}
