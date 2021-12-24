package game.sehnes;

import game.AbstractGameobject;
import game.Item;
import game.ItemImpl;
import game.TalkRightInputGameObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class SehnesGameObject extends TalkRightInputGameObject implements IsSehne {
    public static final String[] keys = new String[]{"10", "prostaugust", "lebensmittelchemie", "mazda 2", "31.07.21", "118", "hokkaido", "mau mau"}; //philipp, juli, ..
    public static final String[] values = new String[]{"Schülerausweis", "ProstAugust", "Isopropyl Profimil Barbitur Saures Phenyl Dementhyl Amino Phyrazolon", "Misterious key", "20 Snacktüten", "Feuerwehrhelm", "Zaubertrank", "Man nimmt einem Blinden doch nicht seinen Stock"}; //philipp, juli, ..

    String rightInput;
    String wrongInput;
    int sehneIndex;
    int percent;
    protected boolean hasGivenItem = false;

    public SehnesGameObject(String name, Item item, int sehneIndex, int percent, String rightInput, String wrongInput) {
        super(name,500, item);
        this.sehneIndex = sehneIndex;
        this.percent = percent;
        this.rightInput = rightInput;
        this.wrongInput = wrongInput;
    }

    @Override
    public Item getItemByKeySentence(String key) {
        if(SehnesGameObject.equalsXPercent(percent, key.toLowerCase(), SehnesGameObject.keys[sehneIndex].toLowerCase())){
            if(hasGivenItem){
                return new ItemImpl("nope");
            }
            hasGivenItem = true;
            return item;
        }
        return null;
    }

    @Override
    public String rightInputMessage() {
        return rightInput;
    }

    @Override
    public String interactMessage(){
        if(hasGivenItem){
            return "fertig hier";
        }
        if(endMessage != null){
            return endMessage;
        }
        line ++;
        String returnValue;
        try{
            returnValue = AbstractGameobject.lineAtIndex(CLASS_NAME + "interactMessage" + ".txt", line);
        }
        catch (IndexOutOfBoundsException e){
            line--;
            endMessage = AbstractGameobject.lineAtIndex(CLASS_NAME + "interactMessage" + ".txt", line);
            returnValue = endMessage;
        }
        return returnValue;
    }

    @Override
    public String wrongInputMessage() {
        if(hasGivenItem){
            return "fertig hier";
        }
        return wrongInput;
    }





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
        //System.out.println(result + " max: " + compareTo.length());
        Optional<Integer> resultOptional = result.stream()
                .reduce((first, second) -> {
                    if(first > second){
                        return first;
                    }
                    return second;
                });
        if(resultOptional.isPresent()){
            return (float) resultOptional.get() >= x / 100f * compareTo.length();
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
