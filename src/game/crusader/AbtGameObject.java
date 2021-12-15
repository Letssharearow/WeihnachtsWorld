package game.crusader;

import game.AbstractGameobject;

public class AbtGameObject extends CrusaderGameObject{
    public AbtGameObject() {
        super("Abt", 3, 3);
    }
    @Override
    public AbstractGameobject randomObject() {
        return new AbtGameObject();
    }
}
