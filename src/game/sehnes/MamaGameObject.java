package game.sehnes;

import game.Item;
import game.ItemKey;
import game.TalkRightInputGameObject;

public class MamaGameObject extends TalkRightInputGameObject {

    public MamaGameObject() {
        super("Mama", 500, new ItemKey("Zaubertrank"));
    }

    @Override
    public Item getItemByKeySentence(String key) {
        if(key.toLowerCase().equals(SehnesGameObject.keys[6])){
            return item;
        }
        return null;
    }

    @Override
    public String rightInputMessage() {
        return "Aus eigenem Garten";
    }

    @Override
    public String wrongInputMessage() {
        return "Ist doch eigentlich logisch";
    }
}
