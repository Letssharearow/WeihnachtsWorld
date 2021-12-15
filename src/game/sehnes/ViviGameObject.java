package game.sehnes;

import game.Item;
import game.ItemKey;
import game.TalkRightInputGameObject;

public class ViviGameObject extends TalkRightInputGameObject {

    public ViviGameObject() {
        super("Vivi", 500, new ItemKey("Irgend son Chemie Ding"));
    }

    @Override
    public Item getItemByKeySentence(String key) {
        if(key.toLowerCase().equals(key.equals(SehnesGameObject.keys[2]))){
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
}