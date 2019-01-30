package db;

import wjc920.util.ReadFile;

import java.io.IOException;
import java.util.List;

public class TypeTransform {
    public static void main(String[] args) throws IOException {
        List<String> lines = ReadFile.readInFileByLine();
        for (String line:lines) {
            System.out.println(transform(line));
        }

    }

    public static String transform(String str){
        if(str.startsWith("bigint")||str.startsWith("int")){
            return "INT64";
        }
        if(str.startsWith("decimal")){
            return "DOUBLE";
        }
        if(str.startsWith("datetime")){
            return "UNIXTIME_MICROS";
        }
        if(str.startsWith("varchar")){
            return "STRING";
        }
        return str+"======";
    }
}
