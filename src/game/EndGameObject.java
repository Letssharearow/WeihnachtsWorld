package game;

public class EndGameObject extends AbstractGameobject{
    public EndGameObject() {
        super("Weihnachtsmann", 10, new ItemImpl("GESCHENKE! FROHE WEIHNACHTEN! GEWONNEN!"));
    }




    @Override
    public AbstractGameobject randomObject() {
        return null;
    }
}
