package game.sehnes;

import game.ItemKey;
import game.Player;

public class SiggiGameObject extends SehnesGameObject{

    public SiggiGameObject() {
        super("Siggi", new ItemKey(SehnesGameObject.values[3].toLowerCase()){
                    @Override
                    public void useItem(Player player){
                        player.setMaxHealth(player.getMaxHealth() + amount);
                        amount = 0;
                    }
                },3,
                70, "und hättest du auch das Kennzeichen gewusst :P", "404");
    }
}