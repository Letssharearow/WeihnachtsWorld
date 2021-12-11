package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class WorldGenerator {

    final int RANGE_OF_IMPORTANT_OBJECTS = 5;
    final int RANGE_OF_MAP = 15;
    AbstractGameobject[][] world;
    int x;
    int y;
    List<AbstractGameobject> importantObjects = new LinkedList<AbstractGameobject>(Arrays.asList(Game.allObjects));

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
                int random = (int) (Math.random() * elements.size());
                world[i][j] = elements.get(random).randomObject();
                if(world[i][j] == null){
                    System.out.println(elements.get(random).getClass());
                };
            }
        }
        setImportantObjects();
    }

    public void setImportantObjects() {
        world[x][y] = importantObjects.remove(0);

        int xTemp = x - RANGE_OF_MAP / 2;
        int yTemp = y - RANGE_OF_MAP / 2;
        if(xTemp < 0 || yTemp < 0){
            System.out.println("map to smol WorldGenerator.SetIMportantObjects");
        }
        ArrayList<Integer> xTemps = new ArrayList<>();
        ArrayList<Integer> yTemps = new ArrayList<>();
        for (int j = 0; j < RANGE_OF_IMPORTANT_OBJECTS; j++) {
            for (int i = 0; i < RANGE_OF_IMPORTANT_OBJECTS; i++) {
                if (i == RANGE_OF_IMPORTANT_OBJECTS / 2) {
                    continue;
                }
                yTemps.add(i);
                xTemps.add(i);
            }
        }
        for (int i = 0; i < importantObjects.size() - 1; i++) {
            AbstractGameobject current = importantObjects.get(i);
            world[x - RANGE_OF_IMPORTANT_OBJECTS/2 + xTemps.remove((int) (Math.random() * xTemps.size()))]
                    [y - RANGE_OF_IMPORTANT_OBJECTS/2 + yTemps.remove((int) (Math.random() * xTemps.size()))] = current;
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

    @Override
    public String toString(){
        String st = "";
        for (int i = 0; i < RANGE_OF_MAP; i++) {
            for (int j = 0; j < RANGE_OF_MAP; j++) {
                int xTemp = x - RANGE_OF_MAP / 2 + i;
                int yTemp = y - RANGE_OF_MAP / 2 + j;
                AbstractGameobject current = world[xTemp][yTemp];
                if(current == null){
                    System.out.println(x + " " + RANGE_OF_MAP + " "+ i);
                }
                else if(current.visited){
                    st += current;
                }
                else if(importantObjects.contains(current)){
                        st += "?";
                }
                else {
                    st += "X";
                }
                st += //xTemp + " " + yTemp +
                        "\t";
            }
            st += "\n";
        }
        return st;
    }

    public static void main(String[] args) {
        List<AbstractGameobject> enemies = Arrays.asList(Game.allObjects);
        WorldGenerator world = new WorldGenerator(50, enemies);
        System.out.println(world);
    }
}
