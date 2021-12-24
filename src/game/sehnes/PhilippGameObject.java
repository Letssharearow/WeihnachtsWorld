package game.sehnes;

import game.ItemKey;
import game.Player;

public class PhilippGameObject extends SehnesGameObject{
    public PhilippGameObject() {
        super("Philipp", new ItemKey(SehnesGameObject.values[0].toLowerCase()){
            @Override
            public void useItem(Player player){
                player.setName("Philipp");
            }
        },
                0, 100, "Könnte sein, dass man den noch Stempeln muss", "Du dummi");
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
