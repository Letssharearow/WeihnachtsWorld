package game.sehnes;

import game.ItemKey;
import game.Player;

public class VeronikaGameObject extends SehnesGameObject{

    public VeronikaGameObject() {
        super("Veronika", new ItemKey(SehnesGameObject.values[4].toLowerCase()){
            @Override
            public void useItem(Player player){
                player.changeHealth(amount);
                amount = 0;
            }
        }, 4, 100, "Kuchen hab ich auch noch", "ersnthaft!?");
    }
}
