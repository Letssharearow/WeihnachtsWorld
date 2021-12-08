package game;

public class RightInputGameObject extends AbstractGameobject implements GetItemByKeySentence{

    String keyString;

    public RightInputGameObject(String name, int health, Item item, String keyString) {
        super(name, health, item);
        this.keyString = keyString;
    }

    @Override
    public String interactMessage(){
        return "if you want " + item.toString() + " you need to ask kindly";
    }

    @Override
    public AbstractGameobject randomObject() {
        return new RightInputGameObject("I have just the thing you need", 5000, new ItemWeapon("EPIC Weapon", 10), "please");
    }

    @Override
    public Item getItemByKeySentence(String key) {
        if(keyString.contains(key)){
            Item output = item;
            item = null;
            return output;
        }
        return null;
    }

    @Override
    public String rightInputMessage() {
        return "here you go";
    }

    @Override
    public String wrongInputMessage() {
        return "sike, that's the wrong input";
    }
}
