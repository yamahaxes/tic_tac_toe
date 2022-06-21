package org.tic_tac_toe.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class Game extends JPanel {

    private static final int SIZE_FIELD = 3;
    private int[][] field;
    private Image background;

    public Game(){
        // двумерный массив будет хранить значения
        field = new int[SIZE_FIELD][SIZE_FIELD];

        // загрузка ресурсов
        loadResources();
        // установка цвета фона
        setBackground(Color.lightGray);
        // инициализация
        init();
    }

    private void init(){
        // Определим в анонимном классе действие на событие нажатия на мышку и добавим обработчик события
        MouseListener mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                doSomethong();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };

        addMouseListener(mouseListener);
    }

    public void doSomethong(){
        int s = 0;
    }

    public void loadResources(){
        ClassLoader classLoader = this.getClass().getClassLoader();

        URL url = classLoader.getResource("image/background.png");
        if (url != null){
            ImageIcon imageIcon = new ImageIcon(url);
            background = imageIcon.getImage();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(background, 0, 0, null); // фон
    }

}
