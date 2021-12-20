package game.sehnes;

import game.Item;
import game.ItemKey;
import game.TalkRightInputGameObject;

public class AndyGameObject extends TalkRightInputGameObject {

    public AndyGameObject() {
        super("Andy", 500, new ItemKey("Feuerwehrhelm"));
    }

    @Override
    public Item getItemByKeySentence(String key) {
        if(SehnesGameObject.equalsXPercent(80, key.toLowerCase(), SehnesGameObject.keys[5].toLowerCase())){
            return item;
        }
        return null;
    }

    @Override
    public String rightInputMessage() {
        return "es ist aus Holz";
    }

    @Override
    public String wrongInputMessage() {
        return "dödel";
    }
}
