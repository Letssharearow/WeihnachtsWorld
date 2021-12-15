package game.sehnes;

import game.Item;
import game.ItemKey;
import game.TalkRightInputGameObject;

public class SiggiGameObject extends TalkRightInputGameObject {

    public SiggiGameObject() {
        super("Siggi", 500, new ItemKey("misterious key"));
    }

    @Override
    public Item getItemByKeySentence(String key) {
        if(key.toLowerCase().contains(SehnesGameObject.keys[3])){
            return item;
        }
        return null;
    }

    @Override
    public String rightInputMessage() {
        return "und hättest du auch das Kennzeichen gewusst?";
    }

    @Override
    public String wrongInputMessage() {
        return "404";
    }
}