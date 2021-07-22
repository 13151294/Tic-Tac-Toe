package Java.Game.TicTacToe;

import java.util.HashMap;
import java.util.Scanner;


public class AI {

    public static String Player = "O";
    public static String Agent_A = "X";
    public static Scanner PlayerInput = new Scanner(System.in);

    static boolean CheckFull(String[][] Board) {
        for (String[] row : Board) {
            for (String column : row) {
                if (column == " ") {
                    return false;
                }
            }
        } return true;
    }

    static boolean CheckWin(String[][] Board) {
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
    public static int evaluate(String[][] Board) {
        Integer[][] WinList = {{0,1,2},{3,4,5},{6,7,8},{6,3,0},{7,4,1},{8,5,2},{6,4,2},{8,4,0},};
        String win = " ";
        for (Integer[] pattern : WinList) {
            int one = pattern[0];int two = pattern[1];int three = pattern[2];
            int x = one / 3;int y = two / 3;int z = three / 3;int a = one % 3;int b = two % 3;int c = three % 3;
            if (Board[x][a] == Board[y][b] && Board[y][b] == Board[z][c] && Board[z][c] != " ") {
                win = Board[z][c];
                break;
            }
        
        }return (win == " ") ? 0: (win == Agent_A) ? 10:-10;
    }
    private static int minimax(String[][] Board, int depth, boolean Max) {
        int Score = evaluate(Board);
        if (Score == 10 || Score == -10 || CheckFull(Board)) {
            return Score;
        }
        if (Max) {
            int Best = -100;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (Board[i][j] == " ") {
                        Board[i][j] = Agent_A;
                        Best = Math.max(Best, minimax(Board, depth+1, !Max));
                        Board[i][j] = " ";
                    }
                }
            } return Best;
        } else {
            int Best = 100;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (Board[i][j] == " ") {
                        Board[i][j] = Player;
                        Best = Math.min(Best, minimax(Board, depth+1, !Max));
                        Board[i][j] = " ";
                    }
                }
            } return Best;
        }
    }
    private static int[] BestMove(String[][] Board) {
        int Best_val = -100;
        int[] Best_move = {-1, -1};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (Board[i][j] == " ") {
                    Board[i][j] = Agent_A;
                    int move_val = minimax(Board, 0, false);
                    Board[i][j] = " ";
                    if (move_val > Best_val) {
                        Best_move[0] = i;
                        Best_move[1] = j;
                        Best_val = move_val;
                    }
                }
            }
        }
        return Best_move;
    }
    private static void PrintBoard(String[][] Board) {
        System.out.println(String.format("%s|%s|%s", Board[2][0], Board[2][1], Board[2][2]));
        System.out.println("-+-+-");
        System.out.println(String.format("%s|%s|%s", Board[1][0], Board[1][1], Board[1][2]));
        System.out.println("-+-+-");
        System.out.println(String.format("%s|%s|%s", Board[0][0], Board[0][1], Board[0][2]));
    }
    public static void Player_Move(String[][] Board) {
        while (true) {
            PrintBoard(Board);
            System.out.print("Enter Position : ");
            var cmd = PlayerInput.nextInt();
            int row = (cmd - 1) / 3;int col = (cmd - 1) % 3;
            if (cmd < 1 || cmd > 9) {
                System.out.println("Index Out Of Range");
            }else if (Board[row][col] == " ") {
                Board[row][col] = Player;
                
                break;
            } else {
                System.out.println("That Place Is Already Filled");
            }
        }  
    }
    public static void main(String[] args) {
        boolean win = false;
        boolean filled = false;
        HashMap<String,String> turn_dict = new HashMap<String,String>();turn_dict.put("X", "O");turn_dict.put("O", "X");
        String turn = "X";
        String[][] Board = {
            {" "," "," "},
            {" "," "," "},
            {" "," "," "},
        };while (!(win || filled)) {
            if (turn == "O") {
                System.out.println("--Human Turn--");
                Player_Move(Board);
            } else {
                System.out.println("--Bot Turn--");
                int[] move = BestMove(Board);
                Board[move[0]][move[1]] = Agent_A;
                PrintBoard(Board);
            }
            turn = turn_dict.get(turn);
            win = CheckWin(Board);filled = CheckFull(Board);
        }if (win) {
            if (turn == Player) {
                System.out.println("\nCongratulation You Win!!");
            } else {
                System.out.println("\nHaha You Lose");
            }
        } if (filled) {
            System.out.println("\nGood!!, But Not Enough");
        }
    }
}
