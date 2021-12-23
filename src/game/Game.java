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

    public Game(int size) {
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
        output = "Gib deinen Namen ein";
    }

    public Game(){
        this(50);
    }

    public void putPlayerItem(Item item){
        if(item == null){
            output += " huch, irgendwas ist kaputt gegangen";
        }
        else{
            output += "\nGlückwunsch, du erhälst das Item: " + item;
            player.addItem(item);
        }
    }

    public void manageInput(String playerInput){
        if(state == GameState.AddName){
            player.name = playerInput;
            output = "Hallo Wichtelmeister " + playerInput + "\nDrücke irgendwas um ins Abenteuer zu starten";
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
        current = world.getCurrent();
        Commands command = toCommand(playerInput);
        if(state == GameState.ItemMenue){
            if(player.getItemsAsString().equals("")){
                output += " du hast gar keine Items";
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
                    output += "\nItem benutzt .. vielleicht hat sich was geändert";
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage() + " wähle die Zahl von dem Item aus, dass du benutzen willst");
                }
            }
        }
        else if(current.health <= 0 && (command == null || command.equals(Commands.at) || command.equals(Commands.in))){
            output += "hier passiert nichts mehr";
            return;
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
                        output += ((ICanAttack) current).MessageOnAttack() + "\nGeschenke wurden geklaut";
                        if(player.health <= 0){
                            putPlayerItem(new ItemImpl(player.name+"_Dumm"));
                            player.health = 5;
                        }
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
        output = world.toString() + "\n" + output + "\n" + world.getCurrent() + "\n\n" + player.toString();
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
                            output += "\n" + AbstractGameobject.randomLine("JamborGameObjectLeaveOnSucceed.txt") + "\n";
                            doReturn = false;
                        } else {
                            output = AbstractGameobject.randomLine("JamborGameObjectLeave.txt");
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
            output += currentKeyObject.rightInputMessage() + "\nGlückwunsch, du erhälst das Item: " + tempItem;
            player.addItem(tempItem);
        }
    }
    private void manageCommand(Commands command) {

        switch (command){
            case at -> {
                world.getCurrent().health -= player.damage;
                if(world.getCurrent().health <= 0){
                    output += world.getCurrent().dieMessage();
                    putPlayerItem(current.item);
                }
                else {
                    output += world.getCurrent().attackMessage();
                    if (current instanceof ICanAttack) {
                        outputEnd = "\n pass auf, ein Schneeball";
                        state = GameState.ObjectWasAttacked;
                    } else {
                        state = GameState.newObject;
                    }
                }
            }
            case d -> {
                movePlayer(0, 1);
            }
            case u -> {
                movePlayer(0, -1);
            }
            case r -> {
                movePlayer(1, 0);
                }
            case l -> {
                movePlayer(-1, 0);
            }
            case in ->{
                output += world.getCurrent().interactMessage();
                if(current instanceof ICanAttack){
                    outputEnd = "\n pass auf, ein Schneeball";
                    state = GameState.ObjectWasAttacked;
                }
            }
            case it -> {
                output += "Items: " + player.getItemsAsString();
                state = GameState.ItemMenue;
            }
            case map -> {}
        }
    }

    public void movePlayer(int xPlus, int yPlus){
        world.incXY(xPlus, yPlus);
        if(world.getCurrent() instanceof JamborGameObject){
            if(!playerHasItmes()){
                world.incXY(xPlus * -1, yPlus * -1);
                output = "dir fehlen noch wichtige Gegenstände um es mit ihm aufzunehmen";
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
        String st = "" + "\nAktionen (left, up, down, right, attack, interact, items, map): ";
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
