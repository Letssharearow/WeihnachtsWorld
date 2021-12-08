package game;

import game.sehnes.*;

import java.util.Arrays;
import java.util.List;

public class Game {
    WorldGenerator world;
    AbstractGameobject current;
    GameState state;
    Player player;
    String output;
    String outputEnd = " " + getCommandsAsString();

    public static final AbstractGameobject[] allObjects = new AbstractGameobject[]{
            new PhilippGameObject(),
            new JuliGameObject(),
            new ViviGameObject(),
            new SiggiGameObject(),
            new VeronikaGameObject(),
            new AndyGameObject(),
            new MamaGameObject(),
            new PapaGameObject(),
            new EnemyGameObject("",0,null,0),
            new RightInputGameObject("", 0, null, "key"),
            new TalkGameObject("", 0, null)
    };

    public Game(int size, int difficulty) {
        List<AbstractGameobject> enemies = Arrays.asList(allObjects);
        for (int i = 0; i < difficulty; i++) {
           // enemies.add(new EnemyGameObject("test", 0, null, 0));
        }
        world = new WorldGenerator(size, enemies);
        current = world.getCurrent();
        state = GameState.newObject;
        player = new Player("someone");
        output = current.helloMessage();
    }

    public Game(int size){
        this(size, 1);
    }

    public void putPlayerItem(Item item){
        if(item == null){
            output += " something went wrong with your item";
        }
        else{
            output += "congratulations, you now have: " + item;
            player.addItem(item);
        }
    }

    public void manageInput(String playerInput){
        output = "";
        outputEnd = " " + getCommandsAsString();
        current = world.getCurrent();
        Commands command = toCommand(playerInput);
        if(state == GameState.ItemMenue){
            if(player.getItemsAsString().equals("")){
                output += " you dont have any items";
                state = GameState.newObject;
            }else {
                try {
                    Item currentItem = player.useitem(Integer.valueOf(playerInput));
                    if (currentItem instanceof ItemKey) {
                        putPlayerItem(currentItem);
                        output = "";
                        state = GameState.newObject;
                    }
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage() + " use Numbers only");
                }
            }
        }
        else if(current instanceof GetItemByKeySentence){
            handleStateGetItemByKeySentence(command, playerInput);
        }
        else if(command == null && state != GameState.ObjectWasAttacked) return;
        else {
            switch (state) {
                case newObject -> manageCommand(command);
                case ObjectWasAttacked -> {
                    if(current.health <= 0){
                        output += "nothing happened";
                    }
                    else if (current instanceof ICanAttack) {
                        output += ((ICanAttack) current).MessageOnAttack();
                    } else output += "nothing happened";
                    state = GameState.newObject;
                }
                case ObjectDied -> {
                    putPlayerItem(current.item);
                    output += current.dieMessage();
                    state = GameState.newObject;
                }
                case ItemMenue -> {

                }
            }
        }
        output = world.getCurrent().name + "\n" + output + "\n" + player.toString();
    }

    private void handleStateGetItemByKeySentence(Commands command, String playerInput) {
        if(command != null){
            manageCommand(command);
            return;
        }
        GetItemByKeySentence currentKeyObject = ((GetItemByKeySentence) world.getCurrent());
        Item tempItem = currentKeyObject.getItemByKeySentence(playerInput);
        if(tempItem == null){
            output += currentKeyObject.wrongInputMessage();
        }
        else {
            output += currentKeyObject.rightInputMessage() + "\ncongratulations, you received: " + tempItem;
            player.addItem(tempItem);
        }
    }

    private void manageCommand(Commands command) {

        switch (command){
            case at -> {
                world.getCurrent().health -= player.damage;
                if(world.getCurrent().health <= 0){
                    output += world.getCurrent().dieMessage();
                }
                else{
                    output += world.getCurrent().attackMessage();
                }
                outputEnd = "\n prepare for attack";
                state = GameState.ObjectWasAttacked;
            }
            case d -> {
                world.incY();
                output += world.getCurrent().helloMessage();
                state = GameState.newObject;
            }
            case u -> {
                world.decY();
                output += world.getCurrent().helloMessage();
                state = GameState.newObject;
            }
            case r -> {
                world.incX();
                output += world.getCurrent().helloMessage();
                state = GameState.newObject;
                }
            case l -> {
                world.decX();
                output += world.getCurrent().helloMessage();
                state = GameState.newObject;
            }
            case in ->{
                output += world.getCurrent().interactMessage();
                 //state = GameState.ObjectWasAttacked;
            }
            case it -> {
                output += "items: " + player.getItemsAsString();
                state = GameState.ItemMenue;
            }
        }
    }

    public Commands toCommand(String st){
        Commands[] commands = Commands.values();
        for (int i = 0; i < commands.length; i++) {
            if(st.equals(commands[i].toString())){
                return commands[i];
            }
        }
        return null;
    }

    public String getCommandsAsString(){
        String st = output + "\nactions: ";
        Commands[] commands = Commands.values();
        for (int i = 0; i < commands.length; i++) {
            st += commands[i] + " ";
        }
        return st;
    }

    @Override
    public String toString(){
        return output + outputEnd;
    }
}
