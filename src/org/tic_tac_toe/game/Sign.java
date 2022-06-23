package org.tic_tac_toe.game;

import java.awt.*;

public class Sign {
    private final Image image;
    private final Symbol symbol;

    public Sign(Image image, Symbol symbol){
        this.image = image;
        this.symbol = symbol;
    }

    public Image getImage() {
        return image;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sign sign = (Sign) o;
        return this.symbol.equals(sign.getSymbol());
    }
}
