import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Field p1 = new Field("User", false);
        Field p2 = new Field("Computer", true);

        Bot bot = new Bot(p2);

        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(3);


//        list.add(3);
//        list.add(2);
//        list.add(2);
//        list.add(2);
//        list.add(1);
//        list.add(1);
//        list.add(1);
//        list.add(1);


        bot.placeShips(new ArrayList(list));//automatic place ship for computer
        //place ships for player
        while (list.size() > 0) {
            int a = list.get(0);
            if (p1.askUser(a) == false) {
                p1.askUser(a);
            }
            p1.draw();
            list.remove(0);
        }
        p1.draw();// show the palyer his ship place .
       bot.field.draw();

        while (p1.checkWin() != 0 && p2.checkWin() != 0) {
            while (p2.guessWhereIsShip( guessPlace(),  guessPlace())) {
                p2.draw();
            }
            System.out.println("is computer turn");
            bot.logicGuessShips();
        }


//check win

        Boolean winner = p1.checkWinner() == true ? p1.checkWinner() : p2.checkWinner();//?
        int win = p1.checkWin() > p2.checkWin() ? p1.checkWin() : p2.checkWin();
        System.out.println("winner is , " + winner + ",  win " + win + " ships");

    }
    public static int guessPlace(){
        Scanner sc = new Scanner(System.in);
        System.out.println("guess where are my ship  x and y   ");
        return sc.nextInt();
    }
}
