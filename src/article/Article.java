package article;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.SortedSet;
import java.util.TreeSet;

public class Article{
    SortedSet<Integer> sortedHash = new TreeSet<Integer>();

    int countTwoCharWord;

    public int getCountTwoCharWord() {
        return countTwoCharWord;
    }
    final String splitSymbol="[.。,，、《》”“：？]";

    public Article(String pathname){
        File file = new File(pathname);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] subSentence=line.split(splitSymbol);
                for(String word:subSentence){
                    for (int i = 0; i < word.length()-1; i++) {
                        int hash=word.substring(i,i+2).hashCode();
                        sortedHash.add(hash);
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
        countTwoCharWord=sortedHash.size();//双字词数
    }
}