package game.sehnes;

import game.Item;
import game.ItemKey;
import game.Player;
import game.TalkRightInputGameObject;

public class MamaGameObject extends TalkRightInputGameObject implements IsSehne{

    public MamaGameObject() {
        super("Mama", 500, new ItemKey(SehnesGameObject.values[6].toLowerCase()){
            @Override
            public void useItem(Player player){
                player.changeHealth(amount);
                amount = 0;
            }
        });
    }

    @Override
    public Item getItemByKeySentence(String key) {
        if(SehnesGameObject.equalsXPercent(100, key.toLowerCase(), SehnesGameObject.keys[6].toLowerCase())){
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
