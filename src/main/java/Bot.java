import java.util.ArrayList;
import java.util.Random;

public class Bot {

    int lastShootX;
    int lastShootY;

    Integer direction = null;

    Field field;


    public Bot(Field field) {
        this.field = field;
    }

    //TODO: add placing ships, it might be random
    public int Random() {           //get random number 0~9
        Random num = new Random();
        return num.nextInt(10);
    }

    public void placeShips(ArrayList listOfShips) {
        for (int i = 0; i < listOfShips.size(); i++) {
            Random num = new Random();
            int directionOfShip = num.nextInt(2);//random number from 0~1; Right:1 , Down:0
            int ships = (int) listOfShips.get(i);
            if (field.placeShip(Random(), Random(), ships, directionOfShip) == false) {
                field.placeShip(Random(), Random(), ships, directionOfShip);//if can't place ship , random number x,y again.
            }
        }
    }
//
    public boolean replaceShips(int x,int y){
        if (field.field[x][y] == 2) {
            field.field[x][y] = 3;
            this.lastShootX=x;//if guess correct remomber as last shoot
            this.lastShootY=y;
        } else if( field.field[x][y]==0) {
            field.field[x][y] = 1;
            return false;
        }else if(field.field[x][y]==3||field.field[x][y]==1){
            return false;
        }
        return true;
    }
    //TODO: add logic

    // logic guess ship
    public boolean logicGuessShips() {
        if (checkLastGuss() == true) {
            GuessNextShip(this.lastShootX, this.lastShootY);
        } else if (checkLastGuss() == false) {
            Random num = new Random();
            int x = num.nextInt(10);
            int y = num.nextInt(10);
            if (field.field[x][y] == 2) {
                field.field[x][y] = 3;
                while (GuessNextShip(x, y)) {
                    GuessNextShip(x, y);
                }
                return false;

            } else {
                field.field[x][y] = 1;
                return false;
            }
        } return false;

    }


    public boolean GuessNextShip(int a,int b) {
        if (a > 0 && b > 0) {
            Random num = new Random();
            int x = num.nextInt(3) + a - 1;//generate number a-1,a,a+1;
            int y = num.nextInt(3) + b - 1;//generate number b-1,b,b+1;
            replaceShips(x, y);
        } else if (a == 0 && b == 0) {
            Random num = new Random();
            int x = num.nextInt(3) + a;//generate number ,a,a+1;
            int y = num.nextInt(3) + b;//generate number b,b+1;
            replaceShips(x, y);
        } else if (a > 0 && b == 0) {
            Random num = new Random();
            int x = num.nextInt(3) + a - 1;//generate number a-1,a,a+1;
            int y = num.nextInt(3) + b;//generate number b,b+1;
            replaceShips(x, y);
        } else if (a == 0 && b > 0) {
            Random num = new Random();
            int x = num.nextInt(3) + a;//generate number a,a+1;
            int y = num.nextInt(3) + b - 1;//generate number b-1,b,b+1;
            replaceShips(x, y);
        }return false;
    }

//  to check if all the x,x+1,x-1;y,y+1,y-1 all has been guessed ?
    public boolean checkLastGuss(){
        if (this.lastShootX>0&&this.lastShootY>0){
            for (int i = 0; i < 3; i++) {// when i =0,1,2. filed = lastShootX-1,lastShootX,lastShootX+1
                for (int j = 0; j < 3; j++) {
                    if(field.field[this.lastShootX-1+i][this.lastShootY-1+j]!=3&&field.field[this.lastShootX-1+i][this.lastShootY-1+j]!=1){
                        return true;
                    }}
            }
        }
        else if (this.lastShootX==0&&this.lastShootY==0){
            for (int i = 0; i < 2; i++) {// when i =0,1,2. filed = ,lastShootX,lastShootX+1
                for (int j = 0; j < 2; j++) {
                    if(field.field[this.lastShootX+i][this.lastShootY+j]!=3&&field.field[this.lastShootX+i][this.lastShootY+j]!=1){
                        return true;
                    }}
            }
        }
        else if (this.lastShootX>0&&this.lastShootY==0){
            for (int i = 0; i < 3; i++) {// when i =0,1,2. filed = ,lastShootX,lastShootX+1
                for (int j = 0; j < 2; j++) {
                    if(field.field[this.lastShootX-1+i][this.lastShootY+j]!=3&&field.field[this.lastShootX-1+i][this.lastShootY+j]!=1){
                        return true;
                    }}
            }
        }
        else if (this.lastShootX==0&&this.lastShootY>0){
            for (int i = 0; i < 2; i++) {// when i =0,1,2. filed = ,lastShootX,lastShootX+1
                for (int j = 0; j < 3; j++) {
                    if(field.field[this.lastShootX+i][this.lastShootY-1+j]!=3&&field.field[this.lastShootX+i][this.lastShootY-1+j]!=1){
                        return true;
                    }}
            }
        }

        return false;
    }


}
