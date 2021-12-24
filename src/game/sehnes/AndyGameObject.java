package game.sehnes;

import game.ItemKey;
import game.Player;

public class AndyGameObject extends SehnesGameObject implements IsSehne {

    public AndyGameObject() {
        super("Andy",
                new ItemKey(SehnesGameObject.values[5].toLowerCase()){
                    @Override
                    public void useItem(Player player){
                        player.setMaxHealth(player.getMaxHealth() + amount);
                        amount = 0;
                    }
                }
                , 5, 100, "es ist aus Holz", "dödel");
    }
}
