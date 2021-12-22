package game;

import java.util.ArrayList;
import java.util.List;

public class Player {
    public String name;
    int health;
    int maxHealth = 20;
    public int damage;
    private List<Item> items;


    public Player(String name) {
        this.name = name;
        health = 10;
        damage = 2;
        items = new ArrayList<>();
    }

    public void changeHealth(int amount){
        this.health += amount;
        if(health > maxHealth){
            health = maxHealth;
        }
    }

    public void addDamage(int amount){
        this.damage += amount;
    }

    public String getName(){
        return this.name;
    }

    public void setDamage(int damage){
        this.damage = damage;
    }

    public void removeItem(Item itemToBeRemoved){
        items.remove(itemToBeRemoved);
    }

    public void addItem(Item itemToBeAdded){
        items.add(itemToBeAdded);
    }

    public boolean hasItem(String name){
        return items.stream()
                .anyMatch(item -> item.name.equalsIgnoreCase(name));
    }

    public String getItemsAsString(){
        String st = "";
        int i = 0;
        for (Item item: items
             ) {
            st += i + " = " + item.toString() + ", ";
            i++;
        }
        return st;
    }
    public Item useitem(int i){
        if(items.size() == 0 || i >= items.size()){
            return null;
        }
        return items.remove(i);
    }
    @Override
    public String toString(){
        String st = name;
        st += " damage: " + damage;
        st += ", health("+ health + ": ";
        for (int i = 0; i < maxHealth; i++) {
            if(i < health){
                st += "♥";
            }
            else{
                st += "○";
            }
        }
        return st;
    }
}
