package game;

import game.sehnes.IsSehne;
import game.sehnes.JamborGameObject;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class WorldGenerator {

    final int RANGE_OF_IMPORTANT_OBJECTS = 5;
    final int RANGE_OF_MAP = 13;
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
                AbstractGameobject currentRandom = elements.get(random).randomObject();
                world[i][j] = currentRandom;
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
        int forsize = RANGE_OF_IMPORTANT_OBJECTS * RANGE_OF_IMPORTANT_OBJECTS - importantObjects.size() - 1;
        for (int i = 0; i < forsize; i++) {
            importantObjects.add(null);
        }
        int halfRange= RANGE_OF_IMPORTANT_OBJECTS / 2;
        for (int i = x - halfRange; i <=  x + halfRange; i++) {
            for (int j = y - halfRange; j <= y + halfRange; j++) {
                if(i == x && j == y){
                    continue;
                }
                AbstractGameobject current = importantObjects.remove((int)(Math.random() * importantObjects.size()));
                if(current != null){
                    world[i][j] = current;
                }
            }
        }

        world[x+RANGE_OF_IMPORTANT_OBJECTS-1][y+RANGE_OF_IMPORTANT_OBJECTS] = new JamborGameObject();
        world[x+RANGE_OF_IMPORTANT_OBJECTS][y+RANGE_OF_IMPORTANT_OBJECTS-1] = new JamborGameObject();
        world[x+RANGE_OF_IMPORTANT_OBJECTS][y+RANGE_OF_IMPORTANT_OBJECTS+1] = new JamborGameObject();
        world[x+RANGE_OF_IMPORTANT_OBJECTS+1][y+RANGE_OF_IMPORTANT_OBJECTS] = new JamborGameObject();
        world[x+RANGE_OF_IMPORTANT_OBJECTS][y+RANGE_OF_IMPORTANT_OBJECTS] = new EndGameObject();

    }

    public AbstractGameobject getCurrent(){
        return world[x][y];
    }

    public void incXY(int xPlus, int yPlus){
        this.x += xPlus;
        if(x >= world.length){
            x = world.length-1;
        }
        if(x < 0){
            x = 0;
        }
        this.y += yPlus;
        if(y >= world.length){
            y = world.length-1;
        }
        if(y < 0){
            y = 0;
        }
    }

    @Override
    public String toString(){
        String st = "";
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world.length; j++) {
                AbstractGameobject current = world[j][i];
                if(j == x && i == y){
                    st += "[ich]";
                }
                else if(current instanceof EndGameObject){
                    st += "?????";
                }
                else if(i == world.length / 2 && j == world.length / 2){
                    st += "  X  ";
                }
                else if(current == null){
                    System.out.println(x + " " + RANGE_OF_MAP + " "+ i);
                }
                else if(current.visited){
                    String name = current.toString();
                    if(name.length() < 5){
                        st += name + mulString(" ", 5 - name.length());
                    }
                    else{
                        st += name.substring(0,5);
                    }
                }
                else if(current instanceof IsSehne){
                        st += "  ?  ";
                }
                else {
                    st += "  .  ";
                }
                st += " ";
            }
            st += "\n";
        }
        return st;
    }

    public static String mulString(String string, int amount){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < amount; i++) {
            result.append(string);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        List<AbstractGameobject> enemies = Arrays.asList(Game.allObjects);
        WorldGenerator world = new WorldGenerator(50, enemies);
        System.out.println(world);
    }
}
