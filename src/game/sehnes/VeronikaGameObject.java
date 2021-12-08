package game.sehnes;

import game.Item;
import game.ItemKey;
import game.TalkRightInputGameObject;

public class VeronikaGameObject extends TalkRightInputGameObject {

    public VeronikaGameObject() {
        super("Veronika", 500, new ItemKey("20 Snacktüten"));
    }

    @Override
    public Item getItemByKeySentence(String key) {
        if(key.toLowerCase().equals("31")){
            return item;
        }
        return null;
    }

    @Override
    public String rightInputMessage() {
        return "Kuchen hab ich auch noch";
    }

    @Override
    public String wrongInputMessage() {
        return "ersnthaft!?";
    }
}
