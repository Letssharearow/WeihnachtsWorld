package game;

public class EnemyGameObject extends AbstractGameobject implements ICanAttack{

    int damage;

    public EnemyGameObject(String name, int health, Item item, int damage) {
        super(name, health, item);
        this.damage = damage;
    }

    @Override
    public void attack(Player player) {
        player.changeHealth(damage);
    }

    @Override
    public String MessageOnAttack() {
        return AbstractGameobject.randomLine(CLASS_NAME + "MessageOnAttack" + ".txt");
    }

    @Override
    public AbstractGameobject randomObject() {
        return new EnemyGameObject("Böser Schneemann", 10, null, 10);
    }
}
