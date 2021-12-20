package game;

public abstract class Item {
    String name;
    protected int amount = 3;

    public Item(String name){
        this.name = name;
    }

    public abstract void useItem(Player player);

    @Override
    public String toString(){
        return name;
    }
}
