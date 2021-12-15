package game.crusader;

import game.AbstractGameobject;

public class RichardGameObject extends CrusaderGameObject{
    public RichardGameObject() {
        super("Richard", 10, 4);
    }
    @Override
    public AbstractGameobject randomObject() {
        return new RichardGameObject();
    }
}
