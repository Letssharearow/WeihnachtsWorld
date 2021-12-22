package game.sehnes;

import game.ItemKey;
import game.Player;

public class PapaGameObject extends SehnesGameObject{

    public PapaGameObject() {
        super("Papa", new ItemKey(SehnesGameObject.values[7].toLowerCase()){
            @Override
            public void useItem(Player player){
                player.setDamage(3);
            }
        }, 7, 90, "Hast du meinen Blindenstock gesehen?", "Alexa, spiele \"du hast verloren\"");
    }
}
