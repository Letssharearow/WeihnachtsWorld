package game;

public class MatheAufgabeGameObject extends  AbstractGameobject implements GetItemByKeySentence{
    String filename;
    String key;

    public MatheAufgabeGameObject(String name, int health, Item item, String filename, String key) {
        super(name, health, item);
        this.filename = filename;
        this.key = key;
    }

    public MatheAufgabeGameObject(String name, String filename, String key) {
        super(name, 1000, new ItemKey("solved"));
        this.filename = filename;
        this.key = key;
    }

    @Override
    public AbstractGameobject randomObject() {
        return new MatheAufgabeGameObject("1 + 1 = ?", 2, null, null, null);
    }

    @Override
    public Item getItemByKeySentence(String input) {
        if(input.equalsIgnoreCase(key)){
            return item;
        }
        else{
            return null;
        }
    }

    @Override
    public String interactMessage(){
        return AbstractGameobject.getAllLines(filename);
    }

    @Override
    public String rightInputMessage() {
        return "richtig";
    }

    @Override
    public String wrongInputMessage() {
        return "falsch";
    }
}
