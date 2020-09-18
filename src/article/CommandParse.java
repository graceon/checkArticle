package article;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class CommandParse {
    /**
     * 处理命令行参数
      * @param args 命令行参数
     */
    public static void main(String[] args) {
        //参数不是3个,输出错误信息并推出
        if(args.length!=3){
            System.out.println("请输入3个参数");
            return;
        }
        System.out.println("源文件："+args[0]+"  比对文件："+args[1]);
        Article origin;
        Article target;
        try {
            origin = new Article(args[0]);
            target = new Article(args[1]);
        }catch (IOException e){
            return;
        }
        //计算重复特征数
        double countSameWord=ArticleCompare.compare(origin,target);
        //取特征数最小
        double countMinWord=Math.min(origin.getCountTwoCharWord(),target.getCountTwoCharWord());
        //System.out.println(countSameWord+"/"+countMinWord);
        //重复特征数/最小特征数
        double res=(countSameWord/countMinWord);
        try {
            System.out.println("查重率:" + output(args[2], res));
        }catch (IOException e){
            return;
        }
        System.out.println("输出文件:"+args[2]);
    }

    /**
     *
     * @param outputPath 输出路径
     * @param res 查重率
     * @return 写入文件的字符串
     */
    public static String output(String outputPath,double res) throws IOException {
        DecimalFormat format=new DecimalFormat("0.00");
        String data=null;
        File outputFile = new File(outputPath);
        try (FileWriter output = new FileWriter(outputFile)) {
            data = format.format(res);
            char[] chars = data.toCharArray();
            output.write(chars);
        } catch (IOException e) {
            ErrorMessages.writeFileError(outputPath);
            throw e;
        }
        return data;
    }
}
