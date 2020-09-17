

import java.io.File;
import java.util.Stack;

import article.CommandParse;
public class test {
    public static void main(String[] args) throws InterruptedException {
        final String basePath = "D:\\test\\tests\\";
        final String resPath = "D:\\res.txt";
        String origin="orig.txt";

        Stack<String> testFileName = getTestFileName(basePath);

        while (!testFileName.empty()) {
            String fileName=testFileName.pop();

            if(fileName.equals(origin)){
                continue;
            }
            String[] mainArg = {
                    basePath + origin,
                    basePath + fileName,
                    resPath
            };
            CommandParse.main(mainArg);
        }
        Thread.sleep(100000);
    }
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
