package game.sehnes;

import game.ItemKey;
import game.Player;

public class ViviGameObject extends SehnesGameObject{

    public ViviGameObject() {
        super("Vivi", new ItemKey(SehnesGameObject.values[2].toLowerCase()){
            @Override
            public void useItem(Player player){
                player.changeHealth((int)(Math.random() * amount * 2 - amount));
            }
        }, 2, 80, "NICHT ANZÜNDEN!", "Ach krass, dabei bin ich schon im Master");
    }
    public static void main(String[] args) {
        for (int i = 0; i <100; i++) {
            int amount = 3;
            System.out.println((int)(Math.random() * amount * 2 - amount));
        }
    }
}