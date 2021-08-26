import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
/*
 // ASK USER
    public static void askUser(int length){
        Scanner sc = new Scanner(System.in);
        System.out.println("Which direction do you want to place ship? (Right:1 or Down:0)");
        int direction=sc.nextInt();
        System.out.println("Where do you want to place x=?   " +  length + " lenght ship?");
        int x=sc.nextInt();
        System.out.println("Where do you want to place y=？   " +  length + " lenght ship?");
        int y=sc.nextInt();
        this.placeShip(x,y,length,direction);
        this.p1.draw();
    }*/

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Field p1 = new Field();
        Field p2 = new Field();

        List<Integer> list = Arrays.asList(4, 3, 3, 2, 2, 2, 1, 1, 1, 1);

     while(list.size() > 0){
            int a = list.get(0);
            p1.askUser(a);
            p2.askUser(a);
            list.remove(0);
        }
        p1.draw();// show the palyer his ship place .


//play
     while(p1.checkWin()!=0&&p2.checkWin()!=0) {
        p1.guessWhereIsShip();
        p2.guessWhereIsShip();
     }



//check win
int win= p1.checkWin()> p2.checkWin()?p1.checkWin():p2.checkWin();
        System.out.println("winner is "+"win"+ win+"ships");

    }
}
