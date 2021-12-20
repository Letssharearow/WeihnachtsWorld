package game.sehnes;

import game.Item;
import game.ItemKey;
import game.Player;
import game.TalkRightInputGameObject;

public class ViviGameObject extends TalkRightInputGameObject implements IsSehne{

    public ViviGameObject() {
        super("Vivi", 500, new ItemKey(SehnesGameObject.values[2].toLowerCase()){
            @Override
            public void useItem(Player player){
                player.changeHealth((int)(Math.random() * amount * 2 - amount));
            }
        });
    }

    @Override
    public Item getItemByKeySentence(String key) {
        if(SehnesGameObject.equalsXPercent(100, key.toLowerCase(), SehnesGameObject.keys[2].toLowerCase())){
            return item;
        }
        return null;
    }

    @Override
    public String rightInputMessage() {
        return "Isopropyl Profimil Barbitur Saures Phenyl Dementhyl Amino Phyrazolon";
    }

    @Override
    public String wrongInputMessage() {
        return "muss man nicht wissen";
    }

    public static void main(String[] args) {
        for (int i = 0; i <100; i++) {
            int amount = 3;
            System.out.println((int)(Math.random() * amount * 2 - amount));
        }
    }
}