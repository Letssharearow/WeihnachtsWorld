package game.sehnes;

import game.Item;
import game.ItemKey;
import game.Player;
import game.TalkRightInputGameObject;

public class JuliGameObject extends TalkRightInputGameObject implements IsSehne{

    public JuliGameObject() {
        super("Juli", 500, new ItemKey(SehnesGameObject.values[1].toLowerCase()) {
            @Override
            public void useItem(Player player) {
                player.changeHealth(amount);
                amount = 0;
            }
        });
    }

    @Override
    public Item getItemByKeySentence(String key) {
        if(SehnesGameObject.equalsXPercent(80, key.toLowerCase(), SehnesGameObject.keys[1].toLowerCase())){
            return item;
        }
        return null;
    }

    @Override
    public String rightInputMessage() {
        return "vielleicht heißts auch AugustProst, schmecken tuts auf jeden Fall";
    }

    @Override
    public String wrongInputMessage() {
        return "so gut, dass man sich den Namen nicht merken kann, wa";
    }
}
