package game;

import java.io.*;
import java.util.*;

public abstract class AbstractGameobject {

    public static final String TEXT_PATH = "text/";
    final String CLASS_NAME = this.getClass().getName().substring((this.getClass().getPackage().getName().length() + 1));

    protected String name;
    protected int health;
    protected Item item;

    public AbstractGameobject(String name, int health, Item item) {
        this.name = name;
        this.health = health;
        this.item = item;
    }

    public String helloMessage() {
        return AbstractGameobject.randomLine(CLASS_NAME + "HelloMessage" + ".txt");
    }

    public String interactMessage() {
        return AbstractGameobject.randomLine(CLASS_NAME + "InteractMessage" + ".txt");
    }

    public String dieMessage() {
        return AbstractGameobject.randomLine(CLASS_NAME + "DieMessage" + ".txt");
    }

    public String attackMessage() {
        return AbstractGameobject.randomLine(CLASS_NAME + "AttackMessage" + ".txt");
    }

    public abstract AbstractGameobject randomObject();

    @Override
    public String toString(){
        return name;
    }

    public static String randomLine(String fileName){
        String randomLine = "";
        List<String> allLines = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(TEXT_PATH + fileName))){

            String current = "";
            while((current = br.readLine()) != null){
                allLines.add(current);
            }
            randomLine = allLines.get((int)(Math.random() * allLines.size()));

        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            System.out.println("FileNotFound, make sure it is in the text folder and only provide the name to the method);");
            return "Ich bin ein Zufälliger String";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return randomLine;
    }

    public static String lineAtIndex(String fileName, int index){
        String randomLine = "";
        List<String> allLines = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(TEXT_PATH + fileName))){

            String currentLine = "";
            int i = 0;
            while((currentLine = br.readLine()) != null && i <= index){
                allLines.add(currentLine);
                i++;
            }
            randomLine = allLines.get(index);

        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            System.out.println("FileNotFound, make sure it is in the text folder and only provide the name to the method);");
            return "Ich bin ein Zufälliger String";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return randomLine;
    }

    public static void writeLine(Scanner scanner, String fileName){

        String fullFileName = TEXT_PATH + fileName + ".txt";
        File file = new File(fullFileName);

        List<String> allLines = new ArrayList<>();
        try(BufferedWriter br = new BufferedWriter(new FileWriter(file, true)))
        {
            System.out.println("Write a line into: " + fullFileName + "\n \"end\" to exit current file");
            String eingabe = scanner.nextLine();;
            while(!eingabe.equals("end")){
                br.write(eingabe);
                br.newLine();
                br.flush();
                eingabe = scanner.nextLine();
            }

        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            System.out.println("FileNotFound, make sure it is in the text folder and only provide the name to the method);");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
        String eingabe = "";
        /* while(true){
            System.out.println("which File you want to write into?");
            eingabe = scanner.nextLine();
            if(eingabe.equals("end")){
                break;
            }
            writeLine(scanner,eingabe);
        }*/
        
        Map<Integer, String> allTextFiles = new HashMap<>(){
            @Override
            public String toString(){
                Iterator<Entry<Integer,String>> i = entrySet().iterator();
                if (! i.hasNext())
                    return "{}";

                StringBuilder sb = new StringBuilder();
                sb.append('{');
                int index = 1;
                for (;;) {
                    Entry<Integer,String> e = i.next();
                    Integer key = e.getKey();
                    String value = e.getValue();
                    sb.append(key);
                    sb.append('=');
                    sb.append(value);
                    if (! i.hasNext())
                        return sb.append('}').toString();
                    sb.append(',').append(index%4 == 0? "\n" : " ");
                    index++;
                }
            }
        };
        MethodNames[] names = MethodNames.values();
        for (int i = 0; i < Game.allObjects.length; i++) {
            for (int j = 0; j < names.length; j++) {
                allTextFiles.put(names.length * i + j,Game.allObjects[i].CLASS_NAME + names[j]);
            }
        }
        int nextFile = 0;
        while(true) {
            System.out.println("which File you want to write into?");
            System.out.println(allTextFiles);
            eingabe = scanner.nextLine();
            if(eingabe.equals("end")){
                break;
            }
            try {
                nextFile = Integer.valueOf(eingabe);
                writeLine(scanner, allTextFiles.get(Integer.valueOf(eingabe)));
            }
            catch(NumberFormatException e){
                writeLine(scanner, eingabe);
            }
        }

    }


}
