package game.crusader;

import game.AbstractGameobject;

public class SultanGameObject extends CrusaderGameObject {
    public SultanGameObject() {
        super("sultan", 1, 1);
    }
    @Override
    public AbstractGameobject randomObject() {
        return new SultanGameObject();
    }
}
