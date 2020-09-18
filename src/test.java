

import java.io.File;
import java.io.IOException;
import java.util.Stack;

import article.CommandParse;
import org.junit.Test;

public class test {
    /**
     * 测试某个目录下所有文件查重率
     */
    @Test
    public void testAllArticle() {
        String basePath = "tests\\";
        String resPath = "res.txt";
        String origin = "orig.txt";

        long startTime = System.currentTimeMillis();

        Stack<String> testFileName = getTestFileName(basePath);

        //测试每一个子文件查重率
        while (!testFileName.empty()) {
            String fileName = testFileName.pop();

            if (fileName.equals(origin)) {
                continue;
            }
            String[] mainArg = {
                    basePath + origin,
                    basePath + fileName,
                    resPath
            };
            CommandParse.main(mainArg);
            System.out.println();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("消耗时间：" + new Double(endTime - startTime) / 1000 + "s");
    }
    /**
     * 测试命令行输入参数错误处理
     */
    @Test
    public void testWrongArgs(){
        String[] args={
                "wrong_origin",
                "wrong_traget",
                "wrong_res",
        };
        CommandParse.main(args);
    }
    @Test
    public void testShortArgs(){
        String[] args={
                "wrong_args_length",
        };
        CommandParse.main(args);
    }
    /**
     * 获取目录下的所有文件名
     * @param basePath 目录路径
     * @return 目录下的所有文件名
     */
    public static Stack<String> getTestFileName(String basePath){
        Stack<String> res=new Stack<String>();
        File dir = new File(basePath);
        if(dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                if(file.isDirectory()){
                }else{
                    res.push(file.getName());
                }
            }
        }
        return res;
    }
}
