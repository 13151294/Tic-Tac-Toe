import java.util.HashMap;
import java.util.Scanner;


public class Human_Java {
    
    public static HashMap<Integer,Character> Board = new HashMap<Integer,Character>();
    public static Scanner PlayerInput = new Scanner(System.in);
    
    static void PrintBoard() {
        System.out.println(String.format("%s|%s|%s", Board.get(7), Board.get(8), Board.get(9)));
        System.out.println("-+-+-");
        System.out.println(String.format("%s|%s|%s", Board.get(4), Board.get(5), Board.get(6)));
        System.out.println("-+-+-");
        System.out.println(String.format("%s|%s|%s", Board.get(1), Board.get(2), Board.get(3)));
    }
    public static void Player(Character turn) {
        boolean Run = true;
        while (Run) {
            PrintBoard();
            System.out.print("Enter Position : ");
            var cmd = PlayerInput.nextInt();
            System.out.println(cmd);
            if (cmd < 1 || cmd > 9) {
                System.out.println("Index Out Of Range");
            }else if (Board.get(cmd) == ' ') {
                Board.put(cmd, turn);
                break;
            } else {
                System.out.println("That Place Is Already Filled");
            }
        }
        
    }
    static boolean CheckWin(Character turn) {
        Integer[][] WinList = {{1,2,3},{4,5,6},{7,8,9},{7,4,1},{8,5,2},{9,6,3},{7,5,3},{9,5,1},};
        boolean win = false;
        for (Integer[] pattern : WinList) {
            if (Board.get(pattern[0]) == Board.get(pattern[1]) && Board.get(pattern[1]) == Board.get(pattern[2]) && Board.get(pattern[2]) != ' ') {
                win = true;
            }
        
        } return win;
    }
    public static void main(String[] args) {
        var x = "X".toCharArray()[0];
        var o = "O".toCharArray()[0];
        HashMap<Character,Character> turn_dict = new HashMap<Character,Character>();turn_dict.put(x, o);turn_dict.put(o, x);
        var turn = x;
        boolean win = false;
        for (int i = 1; i <= 9; i++) {
            Board.put(i, ' ');
        }
        while (!win) {
            turn = turn_dict.get(turn);
            Player(turn);
            win = CheckWin(turn);
        }
        PrintBoard();
        System.out.println(String.format("%s Win!!", turn));
    }
}
