import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution  {
    public static String token;
    public static String countSec;
    public static String[] mess = new String[20];
    public static void main(String[] args) {


        String strLine;
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream("C:\\Users\\Vlad\\Desktop\\settings.iml"), "UTF-8"));

            int i = 1;
            while ((strLine = br.readLine()) != null){
                if (i == 2)
                    countSec = strLine.split("s")[0];
                if (i == 4) {
                    token = strLine;
                    System.out.println(token);
                }
                if (i > 4){
                    {
                        mess[i] = strLine;
                        System.out.println(mess[i] + "  " + i);
                    }
                }
                i++;
            }
        }catch (IOException e){
            System.out.println("Ошибка, файл не найден1");
        }


        FormClass.CteateForm();
    }
}