package game;

public class ItemWeapon extends Item{
    int damage;

    public ItemWeapon(String name, int damage) {
        super(name);
        this.damage = damage;
    }

    @Override
    public void useItem(Player player) {
        player.setDamage(damage);
    }
}
