package article;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class CommandParse {
    public static void main(String[] args) {
        if(args.length!=3){
            return;
        }
        System.out.println("源文件："+args[0]+"  比对文件："+args[1]);
        Article origin=new Article(args[0]);
        Article target=new Article(args[1]);
        double countSameWord=ArticleCompare.compare(origin,target);
        double countMinWord=Math.min(origin.getCountTwoCharWord(),target.getCountTwoCharWord());
        //System.out.println(countSameWord+"/"+countMinWord);
        double res=(countSameWord/countMinWord);
        System.out.println("查重率:"+output(args[2],res));
        System.out.println("输出文件:"+args[2]);
    }
    public static String output(String outputPath,double res) {
        DecimalFormat format=new DecimalFormat("0.00");
        String data=null;
        File outputFile = new File(outputPath);
        try (FileWriter output = new FileWriter(outputFile)) {
            data = format.format(res);
            char[] chars = data.toCharArray();
            output.write(chars);
        } catch (IOException e) {
            ErrorMessages.writeFileError(outputPath);
            e.printStackTrace();
        }
        return data;
    }
}
