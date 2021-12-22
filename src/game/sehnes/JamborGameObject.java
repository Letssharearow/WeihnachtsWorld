package game.sehnes;

import game.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class JamborGameObject extends TalkRightInputGameObject {
    List<StringAndBoolean> cellProperties = Arrays.asList(
            new StringAndBoolean("endoplasmatisches retikulum"),
            new StringAndBoolean("Golgi-Apparat"),
            new StringAndBoolean("mitochondrium"),
            new StringAndBoolean("Chloroplast"),
            new StringAndBoolean("Zellkern"),
            new StringAndBoolean("Zellplasma"),
            new StringAndBoolean("Ribosomen"),
            new StringAndBoolean("Ribosom"),
            new StringAndBoolean("Zellmembran"),
            new StringAndBoolean("Vesikel"),
            new StringAndBoolean("Vakuole"),
            new StringAndBoolean("Membran"),
            new StringAndBoolean("Cytoplasma"));

    final int AMOUNT_OF_RIGHT_ANWSERS = 6;
    static public final String ITEM_NAME = "Note 1";
    boolean isPerfectlyRight;

    int countRightAnwsers = 0;

    public JamborGameObject() {
        super("Walter Jambor", 1000, new ItemKey(ITEM_NAME));
    }

    @Override
    public String rightInputMessage() {
        if(isPerfectlyRight){
            return AbstractGameobject.randomLine("JamborGameObjectRightInput.txt");
        }
        else{
            return AbstractGameobject.randomLine("JamborGameObjectAlmostRightInput.txt");
        }
    }

    @Override
    public String wrongInputMessage() {
        return AbstractGameobject.randomLine("JamborGameObjectWrongInput.txt");
    }

    @Override
    public Item getItemByKeySentence(String key) {
        Item returnValue = null;
        isPerfectlyRight = false;
        Optional<StringAndBoolean> value = cellProperties.stream().filter(property -> {
            if( property.string.equalsIgnoreCase(key)){
                isPerfectlyRight = true;
            }
            return SehnesGameObject.equalsXPercent(70, property.string, key);
        }).findAny();
        if(value.isPresent()){
            if(!value.get().isUsed){
                countRightAnwsers++;
                value.get().isUsed = true;
                returnValue = new ItemImpl("Strich: " + countRightAnwsers + " von " + AMOUNT_OF_RIGHT_ANWSERS);
            }

        }
        if(countRightAnwsers >= AMOUNT_OF_RIGHT_ANWSERS){
            return item;
        }
        return returnValue;
    }
}
