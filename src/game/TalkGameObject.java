package game;

public class TalkGameObject extends AbstractGameobject{

    int line = -1;

    public TalkGameObject(String name, int health, Item item) {
        super(name, health, item);
    }

    @Override
    public AbstractGameobject randomObject() {
        return new TalkGameObject("Labermaul", 500, null);
    }

    @Override
    public String interactMessage(){
        line ++;
        return AbstractGameobject.lineAtIndex(CLASS_NAME + "interactMessage" + ".txt", line);
    }
}
