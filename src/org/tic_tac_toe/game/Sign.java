package org.tic_tac_toe.game;

import java.awt.*;

public class Sign {
    private final Image image;

    public Sign(Image image){
        this.image = image;
    }

    public Image getImage() {
        return image;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sign sign = (Sign) o;
        return this.image.equals(sign.image);
    }
}
