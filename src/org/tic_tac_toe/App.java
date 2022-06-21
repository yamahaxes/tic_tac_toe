package org.tic_tac_toe;

import org.tic_tac_toe.game.Game;

import javax.swing.*;

public class App extends JFrame{

    public App(){
        setTitle("Крестики-нолики"); // Заголовок
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Кнопка закрытии формы
        setSize(512, 512); // размер окна
        setResizable(false);
        setLocation(400, 400); // позиция окна
        add(new Game()); // добавляем новую JPanel на форму
        setVisible(true); // видимость
    }
    public static void main(String[] args)  {
        App app = new App();
    }

}
