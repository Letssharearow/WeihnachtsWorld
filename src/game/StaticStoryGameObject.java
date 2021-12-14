package game;

public class StaticStoryGameObject extends AbstractGameobject {

    static int[] count = new int[]{-1,-1,-1,-1,-1};

    public StaticStoryGameObject() {
        super("HausgegenStand", 1000, null);
    }
    public StaticStoryGameObject(String name, int health, Item item) {
        super(name, health, item);
    }

    @Override
    public String helloMessage() {
        count[0]++;
        visited = true;
        try {
            return AbstractGameobject.lineAtIndex(CLASS_NAME + "helloMessage" + ".txt", count[0]);
        }
        catch (IndexOutOfBoundsException e){
            count[0] = 0;
            return AbstractGameobject.lineAtIndex(CLASS_NAME + "helloMessage" + ".txt", count[0]);
        }
    }

    @Override
    public String interactMessage() {
        count[1]++;
        try {
            return AbstractGameobject.lineAtIndex(CLASS_NAME + "InteractMessage" + ".txt", count[1]);
        }
        catch (IndexOutOfBoundsException e){
            count[1] = 0;
            return AbstractGameobject.lineAtIndex(CLASS_NAME + "InteractMessage" + ".txt", count[1]);
        }
    }

    @Override
    public String dieMessage() {
        count[2]++;
        try {
            return AbstractGameobject.lineAtIndex(CLASS_NAME + "dieMessage" + ".txt", count[2]);
        }
        catch (IndexOutOfBoundsException e){
            count[2] = 0;
            return AbstractGameobject.lineAtIndex(CLASS_NAME + "dieMessage" + ".txt", count[2]);
        }
    }

    @Override
    public String attackMessage() {
        count[3]++;
        try {
            return AbstractGameobject.lineAtIndex(CLASS_NAME + "attackMessage" + ".txt", count[3]);
        }
        catch (IndexOutOfBoundsException e){
            count[3] = 0;
            return AbstractGameobject.lineAtIndex(CLASS_NAME + "attackMessage" + ".txt", count[3]);
        }
    }

    @Override
    public AbstractGameobject randomObject() {
        count[4]++;
        try{
         return new StaticStoryGameObject(AbstractGameobject.lineAtIndex(CLASS_NAME + "Create" + ".txt", count[4]), 1, new ItemImpl("BÖSESTICKER"));
        }
        catch (IndexOutOfBoundsException e){
            count[4] = 0;
            return new StaticStoryGameObject(AbstractGameobject.lineAtIndex(CLASS_NAME + "Create" + ".txt", count[4]), 1, new ItemImpl("BÖSESTICKER"));
        }
    }
}
