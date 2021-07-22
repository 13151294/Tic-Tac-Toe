import java.util.HashMap;
import java.util.Scanner;


public class Normal_Java {
    
    public static String[][] Board = {{" "," "," "},{" "," "," "},{" "," "," "}};
    public static Scanner PlayerInput = new Scanner(System.in);
    
    static void PrintBoard() {
        System.out.println(String.format("%s|%s|%s", Board[2][0], Board[2][1], Board[2][2]));
        System.out.println("-+-+-");
        System.out.println(String.format("%s|%s|%s", Board[1][0], Board[1][1], Board[1][2]));
        System.out.println("-+-+-");
        System.out.println(String.format("%s|%s|%s", Board[0][0], Board[0][1], Board[0][2]));
    }
    static boolean CheckFull() {
        boolean full = true;
        for (String[] row : Board) {
            for (String column : row) {
                if (column == " ") {
                    full = false;
                    break;
                }
            }
        }
        return full;
    }
    static boolean CheckWin(String turn) {
        Integer[][] WinList = {{0,1,2},{3,4,5},{6,7,8},{6,3,0},{7,4,1},{8,5,2},{6,4,2},{8,4,0}};
        boolean win = false;
        for (Integer[] pattern : WinList) {
            int one = pattern[0];int two = pattern[1];int three = pattern[2];
            int row1 = one / 3;int row2 = two / 3;int row3 = three / 3;
            int col1 = one % 3;int col2 = two % 3;int col3 = three % 3;
            if (Board[row1][col1] == Board[row2][col2] && Board[row2][col2] == Board[row3][col3] && Board[row3][col3] != " ") {
                win = true;
            }
        
        } return win;
    }
    public static void Player(String turn) {
        boolean Run = true;
        while (Run) {
            PrintBoard();
            System.out.print("Enter Position : ");
            var cmd = PlayerInput.nextInt();
            int row = (cmd - 1) / 3;int col = (cmd - 1) % 3;
            if (cmd < 1 || cmd > 9) {
                System.out.println("Index Out Of Range");
            }else if (Board[row][col] == " ") {
                Board[row][col] = turn;
                
                break;
            } else {
                System.out.println("That Place Is Already Filled");
            }
        }  
    }
    public static void main(String[] args) {
        HashMap<String,String> turn_dict = new HashMap<String,String>();turn_dict.put("X", "O");turn_dict.put("O", "X");
        var turn = "X";
        boolean win = false;
        boolean full = false;
        while (!(win || full)) {
            turn = turn_dict.get(turn);
            Player(turn);
            win = CheckWin(turn);
            full = CheckFull();
        }
        PrintBoard();
        if (!full && win) {
           System.out.println(String.format("\nCongratulation %s Win!!", turn)); 
        } else {
            System.out.println("\nIt's Draw, Try Next time!!");
        }
    }
}
