package game.sehnes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class SehnesGameObject {

    public static final String[] keys = new String[]{"10", "prostaugust", "lebensmittelchemie", "mazda 2", "31", "an ", "hokkaido", "mau mau"}; //philipp, juli, ..



    //should do:
    //"wake" and "woke": countEqualChars = 3
    //"wake" and "waken": countEqualChars = 3
    //"wise" and "wiese": countEqualChars = 4
    //"wise" and "wiese": countEqualChars = 4
    public static void equalsXPercent(int countUntilNow, List<Integer> bestCounts, int x, int maxLength, String probablyWrong, String compareTo){
        char[] word1 = probablyWrong.toCharArray();
        char[] word2 = compareTo.toCharArray();
        int max;


        int countEqualChars = countUntilNow;

        for (int i = 0; i < word1.length; i++) {
            if(word2.length <= i){
                countEqualChars--;
                max = countEqualChars + Math.min(compareTo.length(), probablyWrong.length()) - i;
                if(max <= maxLength * x / 100f){
                    return;
                }
            }
            else if(word1[i] == word2[i]){
                countEqualChars++;
            }
            else{
                int finalI = i;
                int finalCountEqualChars = countEqualChars;
                Iterator<Integer> iterator = bestCounts.iterator();
                max = countEqualChars + Math.min(compareTo.length(), probablyWrong.length()) - i;
                if(countEqualChars < -50){
                    countEqualChars = countEqualChars;
                }
                if(max <= maxLength * x / 100f){
                    return;
                }
                while(iterator.hasNext()){
                    if(iterator.next() >= max){
                        return;
                    }
                }
                if(word2.length - 1 >= i+1 && word1[i] == word2[i + 1]){
                    equalsXPercent(countEqualChars, bestCounts, x, maxLength, probablyWrong.substring(i), compareTo.substring(i + 1));
                }
                else{
                    equalsXPercent(countEqualChars - 1, bestCounts, x, maxLength, probablyWrong.substring(i + 1), compareTo.substring(i));
                }
            }
        }

        bestCounts.add(countEqualChars);
    }

    public static boolean equalsXPercent(int x, String probablyWrong, String compareTo){

        List<Integer> result = new ArrayList<Integer>() ;
        equalsXPercent(0, result, x, compareTo.length(), probablyWrong, compareTo);
        System.out.println(result + " max: " + compareTo.length());
        Optional<Integer> resultOptional = result.stream()
                .reduce((first, second) -> {
                    if(first > second){
                        return first;
                    }
                    return second;
                });
        if(resultOptional.isPresent()){
            return (float) compareTo.length() >= x / 100f;
        }
        return false;
    }


        public static void main(String[] args) {
        System.out.println(equalsXPercent(50, "woke","wake"));
        System.out.println(equalsXPercent(50, "waken","wake"));
        System.out.println(equalsXPercent(50, "waken2","wake"));
        System.out.println(equalsXPercent(50, "wihse","wiese"));
        System.out.println(equalsXPercent(50, "wise","wiese"));
        System.out.println(equalsXPercent(50, "mazda","mazda 2"));
        System.out.println(equalsXPercent(50, "mitochodrium","mitochondrien"));
        System.out.println(equalsXPercent(100, "augustprost","prostaugust"));
        System.out.println(equalsXPercent(50, "endoplassmatisches retikulum","endoplasmatisches retikulum"));
        System.out.println(equalsXPercent(50, "1endoplasmatischesdfsdfasdfschesdfsdfasdfschesdfsdfasdfschesdfsdfasdfschesdfsdfasdfschesdfsdfasdfschesdfsdfasdf","endoplasmatisches retikulum"));
    }

}
