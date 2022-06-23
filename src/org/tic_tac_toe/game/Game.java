package org.tic_tac_toe.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.List;

public class Game extends JPanel {

    private static final int SIZE_FIELD = 3;
    private final NPC npc;
    private Sign[][] gameField;
    private Image background;
    private Image imageX;
    private Image imageO;
    private boolean gameOver;
    private boolean isPlayerTurn;
    private Line line;

    public Game(){
        // объект отвечает за игру "комьютера"
        npc = new NPC(this, Symbol.O);
        // установка цвета фона
        setBackground(Color.lightGray);
        // загрузка ресурсов
        loadResources();
        // добавление котроллера мышки
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                Game.this.mousePressed(e.getX(), e.getY());
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
        });
        // начало новой игры
        newGame();
    }

    public Sign[][] getGameField() {
        return gameField;
    }

    public Image getImageO() {
        return imageO;
    }

    /**
     * Начинает новую игру, обнуляя весь предыдущий результат
     **/
    private void newGame(){
        line = null;
        gameOver = false;
        gameField = new Sign[SIZE_FIELD][SIZE_FIELD];
        // Начало игры начинает случайно NPC или игрок
        isPlayerTurn = new Random().nextBoolean();
        update();
    }

    /**
     * Обновляет статус игры, после хода игрока или NPC
    **/
    private void update(){
        // перерисовывыет окно
        repaint();

        checkGameEnd();
        if (gameOver){
            return;
        }

        // передача хода игроку или NPC
        isPlayerTurn = !isPlayerTurn;

        if (!isPlayerTurn){
            nextNPCMove();
        }
    }

    /**
     * Проверка окончания игры.
     * Метод пробегает по всей матрице, и проверяет горизонтальные, вертикальные и диагональные линнии на победу.
     * Также учитывается "ничья".
    **/
    private void checkGameEnd(){

        int totalCount = 0;
        int count = 0;

        for (int column = 0; column < gameField[0].length; column++) {

            List<Sign> verticalSignList = new ArrayList<>();
            List<Sign> diagonalSignList1 = new ArrayList<>();
            List<Sign> diagonalSignList2 = new ArrayList<>();

            for (int row = 0; row < gameField.length; row++) {

                // проверка по горизонтали
                List<Sign> horizonSignList = Arrays.asList(gameField[row]);
                if (checkList(horizonSignList)){
                    gameOver = true;
                    drawLine(0, row, gameField[row].length - 1, row);
                    break;
                }

                // заполнение списка по диагонали слева-направо
                verticalSignList.add(gameField[row][column]);
                if (gameField[row].length > row){
                    diagonalSignList1.add(gameField[row][row]);
                }

                // заполнение списка по диагонали справа-налево
                diagonalSignList2.add(gameField[row][gameField.length - 1 - row]);

                // контроль ничьей
                if (gameField[row][column] != null){
                    count++;
                }
                totalCount++;
            }
            if (gameOver) {
                break;
            }
            // Если по вертикали
            if (checkList(verticalSignList)){
                gameOver = true;
                // отрисовка линии по результату победы
                drawLine(column, 0, column, gameField.length - 1);
                break;
            }

            // Если по диагоналям
            if (checkList(diagonalSignList1)){
                gameOver = true;
                drawLine(column, 0, gameField[gameField.length - 1].length - 1, gameField.length - 1);
                break;
            }
            if (checkList(diagonalSignList2)){
                gameOver = true;
                drawLine(gameField[0].length - 1, 0, 0, gameField.length - 1);
                break;
            }
        }

        // Проверка на "ничью". Если заполнены все поля
        if(!gameOver && totalCount == count){
            gameOver = true;
        }
    }

    /**
     * Инициализизирует новую строку
     **/
    private void drawLine(int column1, int row1, int column2, int row2){

       int widthCell = getWidth() / SIZE_FIELD;
       int heightCell = getHeight() / SIZE_FIELD;
       line = new Line(column1 * widthCell + (widthCell / 2),
               row1 * heightCell + (heightCell / 2),
               column2 * widthCell + (widthCell / 2),
               row2 * heightCell + (heightCell / 2));
    }

    /**
     * Проверяет входящий List на одинаковые объекты, null игнорируется
    **/
    private boolean checkList(List<Sign> signs){
        if (signs.size() == 0){
            return false;
        }

        boolean success = true;

        Sign firstSign = signs.get(0);
        if (firstSign != null){
            for (int i = 1; i < signs.size(); i++) {
                if (!firstSign.equals(signs.get(i))){
                    success = false;
                    break;
                }
            }
        }else {
            success = false;
        }
        return success;
    }

    // ход NPC
    private void nextNPCMove() {
        npc.nextMove();
        update();
    }

    // обработчик нажатия мышки на поле
    private void mousePressed(int x, int y){
        if (gameOver){
            newGame();
            return;
        }

        if (!isPlayerTurn) {
            return;
        }

        // расчет местоположения
        int row = y / (getHeight() / SIZE_FIELD);
        int column = x / (getWidth() / SIZE_FIELD);

        if (gameField[row][column] == null){
            gameField[row][column] = new Sign(imageX, Symbol.X);
            update();
        }
    }

    private void loadResources(){
        ClassLoader classLoader = this.getClass().getClassLoader();

        background = new ImageIcon(Objects.requireNonNull(classLoader.getResource("image/3x3.png")))
                .getImage();
        imageX = new ImageIcon(Objects.requireNonNull(classLoader.getResource("image/x.png")))
                .getImage();
        imageO = new ImageIcon(Objects.requireNonNull(classLoader.getResource("image/o.png")))
                .getImage();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        final int offsetX = 50; // смещение для отражения картинки ближе к центру по X
        final int offsetY = 30; // смещение для отражения картинки ближе к центру по Y

        // Перерисовка фона (решетка)
        g.drawImage(background, 0, 0, null);

        // отрисовка крестиков и ноликов на поле
        for (int row = 0; row < gameField.length; row++) {
            for (int column = 0; column < gameField[row].length; column++) {
                if (gameField[row][column] != null){
                    g.drawImage(gameField[row][column].getImage(),
                            column * getHeight() / SIZE_FIELD + offsetX,
                            row * getWidth() / SIZE_FIELD + offsetY,
                            null);
                }
            }
        }

        // отрисовка линии
        if (line != null){
            g.setColor(Color.BLACK);
            Graphics2D graphics2D = (Graphics2D)g;
            //создаем "кисть" для рисования
            BasicStroke pen1 = new BasicStroke(20); //толщина линии 20
            graphics2D.setStroke(pen1);
            g.drawLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());
        }

    }

}
