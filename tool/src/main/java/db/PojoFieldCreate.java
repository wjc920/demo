package db;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import wjc920.util.ReadFile;
public class PojoFieldCreate {

        private static Map<String,String> map = new HashMap<>();

        static {
            map.put("STRING", "String");
            map.put("INT64", "long");
            map.put("INT8", "int");
            map.put("DOUBLE", "Double");
            map.put("UNIXTIME_MICROS", "long");
        }

        public static void main(String[] args) throws IOException {
            List<String> content = ReadFile.readInFileByLine();
            for(int i=0;i<content.size();i++) {
                String[] strArr = content.get(i).split("\t");
//                System.out.println("@JSONField(name=\""+strArr[0]+"\")");
                System.out.printf("private %s %s;\n",map.get(strArr[1]),strArr[0]);
            }

        }

        public static String getFieldNameAsJavaBean(String str) {
            char[] charArr = str.toCharArray();
            String result="";
            for(int i=0;i< charArr.length;i++) {
                if(charArr[i]=='_') {
                    result+=((char)(charArr[i+1]-'a'+'A'));
                    i++;
                }else {
                    result+=charArr[i];
                }
            }
            return result;

        }

    public static String getFieldNameSameAsDbField(String str) {
        char[] charArr = str.toCharArray();
        String result="";
        for(int i=0;i< charArr.length;i++) {
            result+=charArr[i];
        }
        return result;

    }

        public static String getDefault(String type,String def) {
            if("UNIXTIME_MICROS".equals(type)) {
                def += "L";
            }
            if("DOUBLE".equals(type)) {
                def += ".0";
            }
            if("STRING".equals(type)) {
                def="\""+def+"\"";
            }
            return def;
        }

}
