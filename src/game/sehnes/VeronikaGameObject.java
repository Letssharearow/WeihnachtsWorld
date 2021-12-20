package game.sehnes;

import game.Item;
import game.ItemKey;
import game.Player;
import game.TalkRightInputGameObject;

public class VeronikaGameObject extends TalkRightInputGameObject implements IsSehne{

    public VeronikaGameObject() {
        super("Veronika", 500, new ItemKey(SehnesGameObject.values[4].toLowerCase()){
            @Override
            public void useItem(Player player){
                player.changeHealth(amount);
                amount = 0;
            }
        });
    }

    @Override
    public Item getItemByKeySentence(String key) {
        if(SehnesGameObject.equalsXPercent(100, key.toLowerCase(), SehnesGameObject.keys[4].toLowerCase())){
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
