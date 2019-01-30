package db;

public class NameTransform {

    public static void main(String[] args) {
//        List<String> lines = ReadFile.readInFileByLine();
        System.out.println(getName("AbcDe"));

    }

    public static String getName(String name){
        String result="";
        boolean flag=false;
        for(int i=0;i<name.length();i++){
            if(name.charAt(i)<='z'&&name.charAt(i)>='a'){
                flag=true;
            }
            if(name.charAt(i)>='A'&&name.charAt(i)<='Z'){
                if(flag) {
                    result+="_"+(char)(name.charAt(i)-'A'+'a');
                }else {
                    result+=(char)(name.charAt(i)-'A'+'a');
                }
                continue;
            }
            result+=name.charAt(i);
        }
        return result;
    }
}
