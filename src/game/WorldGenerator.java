package game;

import java.util.Arrays;
import java.util.List;

public class WorldGenerator {

    AbstractGameobject[][] world;
    int x;
    int y;

    public WorldGenerator(int size, List<AbstractGameobject> elements){
        this.world = new AbstractGameobject[size][size];
        x = size/2;
        y = size/2;
        generateWorld(elements);
    }

    public WorldGenerator(int size){
        this(size, Arrays.asList(new EnemyGameObject("", 0, null, 0)));
    }

    public WorldGenerator(){
        this(50);
    }

    private void generateWorld(List<AbstractGameobject> elements){
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[i].length; j++) {
                world[i][j] = elements.get((int) (Math.random() * elements.size())).randomObject();
            }
        }
    }

    public AbstractGameobject getCurrent(){
        return world[x][y];
    }

    public void setX(int x){
        this.x = x;
    }

    public void incX(){
        this.x++;
    }

    public void incY(){
        this.y++;
    }

    public void decX(){
        this.x--;
    }

    public void decY(){
        this.y--;
    }

    public void setY(int y){
        this.y = y;
    }
}
