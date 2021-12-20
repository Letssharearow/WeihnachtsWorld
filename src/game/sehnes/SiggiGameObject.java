package game.sehnes;

import game.Item;
import game.ItemKey;
import game.TalkRightInputGameObject;

public class SiggiGameObject extends TalkRightInputGameObject implements IsSehne{

    public SiggiGameObject() {
        super("Siggi", 500, new ItemKey(SehnesGameObject.values[3].toLowerCase()));
    }

    @Override
    public Item getItemByKeySentence(String key) {
        if(SehnesGameObject.equalsXPercent(80, key.toLowerCase(), SehnesGameObject.keys[3].toLowerCase())){
            return item;
        }
        return null;
    }

    @Override
    public String rightInputMessage() {
        return "und hättest du auch das Kennzeichen gewusst :P";
    }

    @Override
    public String wrongInputMessage() {
        return "404";
    }
}