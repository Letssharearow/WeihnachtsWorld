package game.sehnes;

import game.Item;
import game.ItemKey;
import game.Player;
import game.TalkRightInputGameObject;

public class PapaGameObject extends TalkRightInputGameObject implements IsSehne{

    public PapaGameObject() {
        super("Papa", 500, new ItemKey(SehnesGameObject.values[7].toLowerCase()){
            @Override
            public void useItem(Player player){
                player.setDamage(3);
            }
        });
    }

    @Override
    public Item getItemByKeySentence(String key) {
        if(SehnesGameObject.equalsXPercent(90, key.toLowerCase(), SehnesGameObject.keys[7].toLowerCase())){
            return item;
        }
        return null;
    }

    @Override
    public String rightInputMessage() {
        return "Hast du meinen Blindenstock gesehen?";
    }

    @Override
    public String wrongInputMessage() {
        return "Alexa, spiele \"du hast verloren\"";
    }
}
