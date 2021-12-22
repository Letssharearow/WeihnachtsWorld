package game.sehnes;

import game.ItemKey;
import game.Player;

public class JuliGameObject extends SehnesGameObject{

    public JuliGameObject() {
        super("Juli", new ItemKey(SehnesGameObject.values[1].toLowerCase()) {
            @Override
            public void useItem(Player player) {
                player.changeHealth(amount);
                amount = 0;
            }
        }, 1, 80,"vielleicht heißts auch AugustProst, schmecken tuts auf jeden Fall",
                "so gut, dass man sich den Namen nicht merken kann, wa");
    }
}
