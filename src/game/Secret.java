package game;

import java.util.HashMap;
import java.util.Map;

public class Secret {
    public Map<String, String> secretSentences = new HashMap<>();
    public String currentSentence;

    public Secret(){
        String allLines = AbstractGameobject.getAllLines("SecretSentences.txt");
        String[] allLinesSplit = allLines.split("\n");
        for (int i = 0; i < allLinesSplit.length; i+= 2) {
            try{
                secretSentences.put(allLinesSplit[i], allLinesSplit[i+1]);
            }
            catch (IndexOutOfBoundsException e){
                System.out.println("no equal amount of lines");
                e.printStackTrace();
            }
        }
        System.out.println(secretSentences);
    }

    public boolean checkSentence(String key){
        if(secretSentences.containsKey(key)){
            currentSentence = secretSentences.get(key);
            return true;
        }
        return false;
    }


}
