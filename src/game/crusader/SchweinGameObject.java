package game.crusader;

import game.AbstractGameobject;

public class SchweinGameObject extends CrusaderGameObject {
    public SchweinGameObject() {
        super("Schwein", 5, 3);
    }
    @Override
    public AbstractGameobject randomObject() {
        return new SchweinGameObject();
    }
}
