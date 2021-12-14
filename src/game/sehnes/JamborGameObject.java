package game.sehnes;

import game.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class JamborGameObject extends TalkRightInputGameObject {
    List<StringAndBoolean> cellProperties = Arrays.asList(
            new StringAndBoolean("endoplasmatisches retikulum"),
            new StringAndBoolean("Golgi-Apparat"),
            new StringAndBoolean("GolgiApparat"),
            new StringAndBoolean("Golgi Apparat"),
            new StringAndBoolean("mitochondrium"),
            new StringAndBoolean("mitochondrien"),
            new StringAndBoolean("ER"),
            new StringAndBoolean("Chloroplasten"),
            new StringAndBoolean("Chloroplast"),
            new StringAndBoolean("Zellkern"),
            new StringAndBoolean("Zellplasma"),
            new StringAndBoolean("Ribosomen"),
            new StringAndBoolean("Ribosom"),
            new StringAndBoolean("Zellmembran"),
            new StringAndBoolean("Vesikel"),
            new StringAndBoolean("Vakuole"));

    final int AMOUNT_OF_RIGHT_ANWSERS = 3;
    static public final String ITEM_NAME = "komisch grünes Brötchen";

    int countRightAnwsers = 0;

    public JamborGameObject() {
        super("Walter Jambor", 1000, new ItemKey(ITEM_NAME));
    }

    @Override
    public String rightInputMessage() {
        return "hob mach an Strich";
    }

    @Override
    public String wrongInputMessage() {
        return "schreibst a Seitn";
    }

    @Override
    public Item getItemByKeySentence(String key) {
        Item returnValue = null;
        Optional<StringAndBoolean> value = cellProperties.stream().filter(property -> property.string.equalsIgnoreCase(key)).findAny();
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
