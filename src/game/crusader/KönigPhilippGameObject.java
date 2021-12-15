package game.crusader;

import game.AbstractGameobject;

public class KönigPhilippGameObject extends CrusaderGameObject{
    public KönigPhilippGameObject() {
        super("König Philipp", 4, 2);
    }
    @Override
    public AbstractGameobject randomObject() {
        return new KönigPhilippGameObject();
    }
}
