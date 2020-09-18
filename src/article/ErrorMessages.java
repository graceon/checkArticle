package article;

public class ErrorMessages {
    public static final void openFileError(String path){
        System.out.println("打开文件出错，路径："+path);
    }
    public static final void writeFileError(String path){
        System.out.println("输出文件出错，路径："+path);
    }
}
