package game;

public class TalkGameObject extends AbstractGameobject{

    protected int line = -1;
    protected String endMessage;

    public TalkGameObject(String name, int health, Item item) {
        super(name, health, item);
    }

    @Override
    public AbstractGameobject randomObject() {
        return new TalkGameObject("Labermaul", 500, null);
    }

    @Override
    public String interactMessage(){
        if(endMessage != null){
            return endMessage;
        }
        line ++;
        String returnValue;
        try{
            returnValue = AbstractGameobject.lineAtIndex(CLASS_NAME + "interactMessage" + ".txt", line);
        }
        catch (IndexOutOfBoundsException e){
            line--;
            endMessage = AbstractGameobject.lineAtIndex(CLASS_NAME + "interactMessage" + ".txt", line);
            returnValue = endMessage;
        }
        return returnValue;
    }
}
