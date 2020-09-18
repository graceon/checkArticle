package article;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.SortedSet;
import java.util.TreeSet;

public class Article{
    /**
     *
     * @return 文章特征
     */
    public SortedSet<Integer> getFeature() {
        return sortedHashFeature;
    }

    /**
     *
     * @return 获取文章中双字词个数
     */
    public int getCountTwoCharWord() {
        return countTwoCharWord;
    }
    //储存相邻汉字之间两两结合的Hash作为论文特征,并排序。
    private SortedSet<Integer> sortedHashFeature = new TreeSet<Integer>();

    //文章中双字词的数量
    private int countTwoCharWord;

    //正则表达式：匹配标点符号
    private final String splitSymbol="[.。,，、《》”“：？]";

    /**
     *
     * @param pathname 文章路径
     */
    public Article(String pathname) throws IOException {
        BufferedReader reader = null;
        try {
            //读取文件并且使用BufferedReader逐行分析
            File file = new File(pathname);
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                //使用正则表达式分割这一行，将这一行断句
                String[] subSentence=line.split(splitSymbol);
                for(String word:subSentence){
                    //读取2个汉字作为双字词，并计算这个子串的Hash，添加sortedHashFeature中
                    for (int i = 0; i < word.length()-1; i++) {
                        //获取双字词的Hash值作为，添加到特征集合
                        int hash = word.substring(i, i + 2).hashCode();
                        sortedHashFeature.add(hash);
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            //输出错误信息
            ErrorMessages.openFileError(pathname);
            throw e;
        } finally {
            //回收reader
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e2){
                }
            }
        }
        //文章中双字词的数量
        countTwoCharWord= sortedHashFeature.size();//双字词数
    }
}