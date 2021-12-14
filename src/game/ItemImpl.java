package game;

public class ItemImpl extends Item{
    public ItemImpl(String name) {
        super(name);
    }

    @Override
    public void useItem(Player player) {
        player.removeItem(this);
    }
}
