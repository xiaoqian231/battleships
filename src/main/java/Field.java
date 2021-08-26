import java.util.ArrayList;
import java.util.Scanner;

public class Field {
    int[][] field = new int[10][10];

    // 0 empty, 1 somebody has shoot there, 2 ship, 3 drown ship
    public Field() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                field[i][j] = 0;
            }
        }
    }

    public void draw() {
        System.out.print(" ");
        for (int i = 0; i < 10; i++) {
            System.out.print(i);
        }
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.print(i);
            for (int j = 0; j < 10; j++) {
                System.out.print(field[i][j]);
            }
            System.out.println();
        }
    }

    private boolean moveRight(int x, int y, int shipLenght) {
        if ((x + shipLenght) <= 10) {
            for (int i = 0; i < shipLenght; i++) {
                if (field[i + x][y] != 0) {
                    return false;
                }
            }
            for (int i = 0; i < shipLenght; i++) {
                field[i + x][y] = 2;
            }
            return true;
        }
        return false;
    }
//moveDown

    private boolean moveDown(int x, int y, int shipLenght) {
        if ((y + shipLenght) <= 10) {
            for (int i = 0; i < shipLenght; i++) {
                if (field[x][i + y] != 0) {
                    return false;
                }
            }
            for (int i = 0; i < shipLenght; i++) {
                field[x][i + y] = 2;
            }
            return true;
        }
        return false;
    }

    public boolean placeShip(int x, int y, int shipLenght, int direction) {// true go down
        if (direction == 1) {
            return moveDown(x, y, shipLenght);
        }
        return moveRight(x, y, shipLenght);
    }


    public void guessWhereIsShip() {
        Scanner sc = new Scanner(System.in);
        System.out.println("guess where are my ship  x=?   ");
        int x = sc.nextInt();
        System.out.println("guess where are my ship  y=?   ");
        int y = sc.nextInt();
        if (field[x][y] == 2) {
            field[x][y] = 3;
            System.out.println("correct!");
        } else {
            System.out.println("guessed wrong!");
        }
    }

    public int checkWin() {
        ArrayList <Integer> leftShips=new ArrayList<>() ;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (field[i][j]==2){
                    leftShips.add(i);
                }
            }
        }
        return leftShips.size();
    }


    public  void askUser(int length){
        Scanner sc = new Scanner(System.in);
        System.out.println("Which direction do you want to place ship? (Right:1 or Down:0)");
        int direction=sc.nextInt();
        System.out.println("Where do you want to place x=?   " +  length + " lenght ship?");
        int x=sc.nextInt();
        System.out.println("Where do you want to place y=？   " +  length + " lenght ship?");
        int y=sc.nextInt();
       placeShip(x,y,length,direction);
    }
}
