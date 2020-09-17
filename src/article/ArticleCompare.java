package article;

import java.util.SortedSet;

public class ArticleCompare {
    public static int compare(Article originArticle,Article targetArticle){
        int countSameWord=0;
        SortedSet<Integer> origin=originArticle.sortedHash;
        SortedSet<Integer> target=targetArticle.sortedHash;
        int originHashNow=origin.first();
        int targetHashNow=target.first();
        while(origin.size()!=0&&target.size()!=0){
            if(originHashNow==targetHashNow) {
                //origin和target指针前移
                origin.remove(originHashNow);
                target.remove(targetHashNow);
                if(origin.size()!=0)originHashNow=origin.first();
                if(target.size()!=0)targetHashNow=target.first();
                countSameWord += 1;
            }else if(originHashNow<targetHashNow){
                //origin指针前移
                origin.remove(originHashNow);
                if(origin.size()!=0)originHashNow=origin.first();
            }else{
                //target指针前移
                target.remove(targetHashNow);
                if(target.size()!=0)targetHashNow=target.first();
            }
        }
        return countSameWord;
    }
}
