package game.sehnes;

import game.Item;
import game.ItemKey;

public class PhilippGameObject extends game.TalkRightInputGameObject{
    public PhilippGameObject() {
        super("Philipp", 500, new ItemKey("Schuelerausweis"));
    }

    @Override
    public Item getItemByKeySentence(String key) {
        if(key.equals(SehnesGameObject.keys[0])){
            return item;
        }
        return null;
    }

    @Override
    public String rightInputMessage() {
        return "Ich weiß nicht ob ich ihn überhaupt mal gestempelt hab";
    }

    @Override
    public String wrongInputMessage() {
        return "Du dummi";
    }


/*
    public static final AbstractGameobject[] = new AbstractGameobject[]{
        new RightInputGameObject("Philipp", 500, new ItemKey("Schuelerausweis"), "10"),
                new RightInputGameObject("Juli", 500, new ItemKey("oder doch AugustProst?"), "ProstAugust"),
                new RightInputGameObject("Vivi", 500, new ItemKey("Irgendson chemieding"), "Masterarbeitsname"),
                new RightInputGameObject("Siggi", 500, new ItemKey("misterious key"), "Mazda 2"),
                new RightInputGameObject("Veronika", 500, new ItemKey("20 Snacktüten"), "31"),
                new RightInputGameObject("Andy", 500, new ItemKey("Feuerwehrhelm"), "10"),
                new RightInputGameObject("Mama", 500, new ItemKey("Schuelerausweis"), "10"),
                new RightInputGameObject("Papa", 500, new ItemKey("Schuelerausweis"), "10"),
    }*/
}
