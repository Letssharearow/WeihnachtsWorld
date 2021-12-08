package game;

public abstract class TalkRightInputGameObject extends TalkGameObject implements GetItemByKeySentence{
    public TalkRightInputGameObject(String name, int health, Item item) {
        super(name, health, item);
    }
    public abstract Item getItemByKeySentence(String key);
}
