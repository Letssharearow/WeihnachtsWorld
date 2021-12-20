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
        if(SehnesGameObject.equalsXPercent(80, key.toLowerCase(), SehnesGameObject.keys[6].toLowerCase())){
            return item;
        }
        return null;
    }

    @Override
    public String rightInputMessage() {
        return "Ist aus eigenem Garten";
    }

    @Override
    public String wrongInputMessage() {
        return "Ist doch eigentlich logisch";
    }
}
