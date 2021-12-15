package game.crusader;

import game.AbstractGameobject;

public class RatteGameObject extends CrusaderGameObject{
    public RatteGameObject() {
        super("Ratte", 1, 1);
    }
    @Override
    public AbstractGameobject randomObject() {
        return new RatteGameObject();
    }
}
