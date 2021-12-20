package game;

public class EndGameObject extends AbstractGameobject implements GetItemByKeySentence{

    boolean hasOne = false;
    boolean didGenocide = false;

    public EndGameObject() {
        super("Weihnachtsmann", 10, new ItemImpl("GESCHENKE"));
    }


    public String helloMessage() {
        visited = true;
        return AbstractGameobject.randomLine(CLASS_NAME + "HelloMessage" + ".txt");
    }

    @Override
    public String interactMessage() {
        hasOne = true;
        return AbstractGameobject.randomLine(CLASS_NAME + "InteractMessage" + ".txt");
    }

    @Override
    public String dieMessage() {
        didGenocide = true;
        health = 10;
        return AbstractGameobject.randomLine(CLASS_NAME + "DieMessage" + ".txt");
    }

    @Override
    public String attackMessage() {
        return AbstractGameobject.randomLine(CLASS_NAME + "AttackMessage" + ".txt");
    }

    @Override
    public AbstractGameobject randomObject() {
        return null;
    }

    @Override
    public Item getItemByKeySentence(String key) {
        if(hasOne){
            return item;
        }
        if(didGenocide){
            new ItemImpl("secret Ending");
        }
        return null;
    }

    @Override
    public String rightInputMessage() {
        return "Ich hoffe es ist was gutes Dabei";
    }

    @Override
    public String wrongInputMessage() {
        return "Vielen Dank für die Hilfe, aber was hast du gesagt?";
    }
}
