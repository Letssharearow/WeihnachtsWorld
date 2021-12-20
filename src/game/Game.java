package game;

import game.crusader.*;
import game.sehnes.*;

import java.util.ArrayList;
import java.util.List;

public class Game {
    WorldGenerator world;
    AbstractGameobject current;
    GameState state;
    Player player;
    String output;
    String outputEnd = "";
    public boolean isOver = false;

    public static final AbstractGameobject[] allObjects = new AbstractGameobject[]{
            new EndGameObject(),
            new StartObject(),
            new PhilippGameObject(),
            new JuliGameObject(),
            new ViviGameObject(),
            new SiggiGameObject(),
            new VeronikaGameObject(),
            new AndyGameObject(),
            new MamaGameObject(),
            new PapaGameObject(),
            //new EnemyGameObject("",0,null,0),
            //new RightInputGameObject("", 0, null, "key"),
            //new TalkGameObject("", 0, null),
            //new MatheAufgabeGameObject("Rentieraufgabe","matheaufgabe1RentierZeit.txt", "b")
    };

    public Game(int size, int difficulty) {
        List<AbstractGameobject> enemies = new ArrayList<>();

        enemies.add(new StaticStoryGameObject());
        enemies.add(new StaticStoryGameObject());
        enemies.add(new StaticStoryGameObject());
        enemies.add(new StaticStoryGameObject());
        enemies.add(new StaticStoryGameObject());
        enemies.add(new StaticStoryGameObject());
        enemies.add(new KönigPhilippGameObject());
        enemies.add(new RatteGameObject());
        enemies.add(new SultanGameObject());
        enemies.add(new RichardGameObject());
        enemies.add(new SchweinGameObject());
        enemies.add(new AbtGameObject());

        world = new WorldGenerator(size, enemies);
        current = world.getCurrent();
        state = GameState.AddName;
        player = new Player("Friendly Cobold");
        output = "Please Enter your name";
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
        if(state == GameState.AddName){
            player.name = playerInput;
            output = "Hello Wichtelmeister " + playerInput + "\nEnter anything to start the game";
            state = GameState.setStart;
            return;
        }
        if(state == GameState.setStart){
            output = current.helloMessage();
            state = GameState.newObject;
            return;
        }
        output = "";
        outputEnd = " " + getCommandsAsString();
        if(current.health <= 0){
            output += "nothing happened";
            return;
        }
        current = world.getCurrent();
        Commands command = toCommand(playerInput);
        if(state == GameState.ItemMenue){
            if(player.getItemsAsString().equals("")){
                output += " you dont have any items";
                state = GameState.newObject;
            }else {
                try {
                    Item currentItem = player.useitem(Integer.valueOf(playerInput));
                    currentItem.useItem(player);
                    if (currentItem instanceof ItemKey) {
                        putPlayerItem(currentItem);
                        output = "";
                    }
                    state = GameState.newObject;
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
                case newObject -> {
                    manageCommand(command);
                }
                case ObjectWasAttacked -> {
                        ((ICanAttack) current).attack(player);
                        if(player.health <= 0){
                            putPlayerItem(new ItemImpl(player.name+"_Dumm"));
                            player.health = 5;
                        }
                        output += ((ICanAttack) current).MessageOnAttack();
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
        output = world.toString() + "\n" + world.getCurrent() + "\n" + output + "\n\n" + player.toString();
    }

    private void handleStateGetItemByKeySentence(Commands command, String playerInput) {
        if(current instanceof JamborGameObject){

            boolean doReturn = true;
            if(command != null) {

                switch (command) {
                    case in :
                    case at:
                    case it:
                        doReturn = false; break;
                    case map:
                        output = "ned spicken!";
                        break;
                    case r:
                    case u:
                    case d:
                    case l: {
                        if (player.hasItem(JamborGameObject.ITEM_NAME)) {
                            doReturn = false;
                        } else {
                            output = "und tschüss ... oder auch nicht.";
                        }
                    }
                    break;
                }
                if (doReturn) {
                    return;
                }
            }
        }
        if(current instanceof EndGameObject){
            if(((EndGameObject) current).hasOne){
                rollCredits();
                return;
            }
        }
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
                if(current instanceof ICanAttack){
                    outputEnd = "\n prepare for attack";
                    state = GameState.ObjectWasAttacked;
                }
                else{
                    state = GameState.newObject;
                }
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
                if(current instanceof ICanAttack){
                    outputEnd = "\n prepare for attack";
                    state = GameState.ObjectWasAttacked;
                }
            }
            case it -> {
                output += "items: " + player.getItemsAsString();
                state = GameState.ItemMenue;
                if(current instanceof ICanAttack){
                    outputEnd = "\n prepare for attack";
                    state = GameState.ObjectWasAttacked;
                }
            }
            case map -> output += world;
        }
    }

    public void movePlayer(int xPlus, int yPlus){
        world.incXY(xPlus, yPlus);
        if(world.getCurrent() instanceof JamborGameObject){
            if(!playerHasItmes()){
                world.incXY(xPlus * -1, yPlus * -1);
                output = "dir Fehlen wichtige Items";
            }
        }
        output += world.getCurrent().helloMessage();
        state = GameState.newObject;
    }

    public boolean playerHasItmes(){
        boolean hasAllItems = true;
        for(String value : SehnesGameObject.values){
            if(!player.hasItem(value)){
                hasAllItems = false;
                break;
            }
        }
        return hasAllItems;
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
        String st = "" + "\nactions: ";
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

    private void rollCredits(){
        for (int i = 0; i < 6; i++) {
            System.out.println(AbstractGameobject.lineAtIndex( "credits" + ".txt", i));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ENDE!");
        isOver = true;
    }
}
