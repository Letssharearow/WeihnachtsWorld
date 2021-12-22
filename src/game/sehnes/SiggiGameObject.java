package game.sehnes;

import game.ItemKey;

public class SiggiGameObject extends SehnesGameObject{

    public SiggiGameObject() {
        super("Siggi", new ItemKey(SehnesGameObject.values[3].toLowerCase()),3,
                80, "und hättest du auch das Kennzeichen gewusst :P", "404");
    }
}