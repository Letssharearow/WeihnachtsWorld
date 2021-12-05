package game;

public class ItemPotion extends Item{
    Colors color;
    int amount;

    public ItemPotion(String name, int amount, Colors color){
        super(name);
        this.amount = amount;
        setAmountNeutral();
        this.color = color;
        setAmountColor();

    }

    @Override
    public void useItem(Player player) {
        player.changeHealth(amount);
        player.removeItem(this);
    }

    private void setAmountNeutral(){
        if(amount < 0){
            amount *= -1;
        }
    }

    private void setAmountColor(){
        switch (color){
            case red, blue -> {}
            case green, yellow -> amount *= -1;
        }
    }
}
