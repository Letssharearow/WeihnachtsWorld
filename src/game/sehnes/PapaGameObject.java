package game.sehnes;

import game.Item;
import game.ItemKey;
import game.TalkRightInputGameObject;

public class PapaGameObject extends TalkRightInputGameObject {

    public PapaGameObject() {
        super("Papa", 500, new ItemKey("Man nimmt einem Blinden doch nicht seinen Stock"));
    }

    @Override
    public Item getItemByKeySentence(String key) {
        if(SehnesGameObject.equalsXPercent(80, key.toLowerCase(), SehnesGameObject.keys[7].toLowerCase())){
            return item;
        }
        return null;
    }

    @Override
    public String rightInputMessage() {
        return "Hast du meinen Blindenstock gesehen";
    }

    @Override
    public String wrongInputMessage() {
        return "Alexa, spiele \"du hast verloren\"";
    }
}
