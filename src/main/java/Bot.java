import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bot {

    int lastShootX=0;
    int lastShootY=0;

    Random num = new Random();

    Integer direction = null;

    Field p2;


    public Bot(Field field) {
        this.p2 = field;
    }

    //TODO: add placing ships, it might be random
    public int Random() {           //get random number 0~9
        return num.nextInt(10);
    }

    public void placeShips(ArrayList listOfShips) {
        for (int i = 0; i < listOfShips.size(); i++) {
            int directionOfShip = num.nextInt(2);//random number from 0~1; Right:1 , Down:0
            int ships = (int) listOfShips.get(i);
            if (p2.placeShip(Random(), Random(), ships, directionOfShip) == false) {
                p2.placeShip(Random(), Random(), ships, directionOfShip);//if can't place ship , random number x,y again.
            }
        }
    }

    //
    public int replaceShips(int x, int y, Field p1) {
        if (p1.field[x][y] == 2) {
            p1.field[x][y] = 3;
            lastShootX = x;//if guess correct remomber as last shoot
            lastShootY = y;
            return 1;
        } else if (p1.field[x][y] == 0) {//guess not correct ,return 0 stop guess
            p1.field[x][y] = 1;
            return 0;
        } else {
            return 2;// else guess 3 or 1 , return 2 can guess again
        }
    }
    //TODO: add logic

    // logic guess ship
    public int logicGuessShips(Field p1) {
        if (checkLastGuss(p1) >0) {
            // while (GuessNextShip(lastShootX, lastShootY,p1)>0) {
            return GuessNextShip(lastShootX, lastShootY, p1);
        } else {
            int x = num.nextInt(9);
            int y = num.nextInt(9);
            return GuessNextShip(x, y, p1);
        }
    }

    // TODO: replace return true/false with return 0,1,2 (missed, guessed correct, field already shoot)  a > 0 shootAgain()
    public int GuessNextShip(int a, int b, Field p1) {

        if (a > 0 && b > 0) {
            int x = num.nextInt(3) + a - 1;//generate number a-1,a,a+1;
            int y = num.nextInt(3) + b - 1;//generate number b-1,b,b+1;
            return replaceShips(x, y, p1);
        } else if (a == 0 && b == 0) {
            int x = num.nextInt(2) + a;//generate number ,a,a+1;
            int y = num.nextInt(2) + b;//generate number b,b+1;
            return replaceShips(x, y, p1);
        } else if (a > 0 && b == 0) {
            int x = num.nextInt(3) + a - 1;//generate number a-1,a,a+1;
            int y = num.nextInt(2) + b;//generate number b,b+1;
            return replaceShips(x, y, p1);
        } else if (a == 0 && b >0){//a==0;b>0
            int x = num.nextInt(2) + a;//generate number a,a+1;
            int y = num.nextInt(3) + b - 1;//generate number b-1,b,b+1;
            return replaceShips(x, y, p1);
        }
        return 0;
    }

    //  to check if all the x,x+1,x-1;y,y+1,y-1 all has been guessed ?
    public int checkLastGuss(Field p1) {
        List<Integer> list = new ArrayList<>();
        if (this.lastShootX > 0 && this.lastShootY > 0) {
            for (int i = 0; i < 3; i++) {// when i =0,1,2. filed = lastShootX-1,lastShootX,lastShootX+1
                for (int j = 0; j < 3; j++) {
                    if (p1.field[this.lastShootX - 1 + i][this.lastShootY - 1 + j] != 3 && p1.field[this.lastShootX - 1 + i][this.lastShootY - 1 + j] != 1) {
                     list.add(1);
                    }
                }
            }
        } else if (this.lastShootX == 0 && this.lastShootY == 0) {
            for (int i = 0; i < 2; i++) {// when i =0,1,2. filed = ,lastShootX,lastShootX+1
                for (int j = 0; j < 2; j++) {
                    if (p1.field[this.lastShootX + i][this.lastShootY + j] != 3 && p1.field[this.lastShootX + i][this.lastShootY + j] != 1) {
                        list.add(1);
                    }
                }
            }
        } else if (this.lastShootX > 0 && this.lastShootY == 0) {
            for (int i = 0; i < 3; i++) {// when i =0,1,2. filed = ,lastShootX,lastShootX+1
                for (int j = 0; j < 2; j++) {
                    if (p1.field[this.lastShootX - 1 + i][this.lastShootY + j] != 3 && p1.field[this.lastShootX - 1 + i][this.lastShootY + j] != 1) {
                        list.add(1);
                    }
                }
            }
        } else if (this.lastShootX == 0 && this.lastShootY > 0) {
            for (int i = 0; i < 2; i++) {// when i =0,1,2. filed = ,lastShootX,lastShootX+1
                for (int j = 0; j < 3; j++) {
                    if (p1.field[this.lastShootX + i][this.lastShootY - 1 + j] != 3 && p1.field[this.lastShootX + i][this.lastShootY - 1 + j] != 1) {
                        list.add(1);
                    }
                }
            }
        }
        return list.size();
    }

}
