package game.sehnes;

import game.ItemKey;
import game.Player;

public class MamaGameObject extends SehnesGameObject{

    public MamaGameObject() {
        super("Mama", new ItemKey(SehnesGameObject.values[6].toLowerCase()){
            @Override
            public void useItem(Player player){
                player.changeHealth(amount);
                amount = 0;
            }
        }, 6, 100, "Ist aus eigenem Garten", "Ist doch eigentlich logisch");
    }
}
