package game;

public class StartObject extends  AbstractGameobject{

    public StartObject() {
        super("RechtKnuprecht", Integer.MAX_VALUE, null);
    }

    @Override
    public AbstractGameobject randomObject() {
        return new StartObject();
    }

    @Override
    public String helloMessage(){
        return AbstractGameobject.getAllLines("startText.txt");
    }
}
