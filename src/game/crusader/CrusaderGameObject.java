package game.crusader;

import game.*;

public class CrusaderGameObject extends StaticStoryGameObject implements ICanAttack {
    int count = -1;
    int damage;

    public CrusaderGameObject(String name, int health, int damage){
        super(name, health, new ItemPotion("Eure Beliebtheit steigt", 5, Colors.red));
        this.damage = damage;
    }

    @Override
    public void attack(Player player) {
        player.changeHealth(damage);
    }

    @Override
    public String MessageOnAttack() {
        count ++;
        try{
            return AbstractGameobject.lineAtIndex(CLASS_NAME + "Create" + ".txt", count);
        }
        catch (IndexOutOfBoundsException e){
            count = 0;
            return AbstractGameobject.lineAtIndex(CLASS_NAME + "Create" + ".txt", count);
        }
    }

    @Override
    public AbstractGameobject randomObject() {
        return this;
    }
}
