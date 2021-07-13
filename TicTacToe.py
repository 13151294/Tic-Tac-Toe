#Python 3.9.5
#Compute by Ace Fhid
from random import choice

x, o = '\033[038;2;255;102;102mX\033[0;0m', '\033[038;2;102;199;255mO\033[0;0m'
Board = {
    '7':' ', '8':' ', '9':' ',
    '4':' ', '5':' ', '6':' ',
    '1':' ', '2':' ', '3':' ',}
def DisplayBoard(board):
    print('\033[038;2;255;175;36m{}\033[038;2;255;175;36m|{}\033[038;2;255;175;36m|{}'.format(board['7'], board['8'], board['9']))
    print('\033[038;2;255;175;36m-+-+-')
    print('\033[038;2;255;175;36m{}\033[038;2;255;175;36m|{}\033[038;2;255;175;36m|{}'.format(board['4'], board['5'], board['6']))
    print('\033[038;2;255;175;36m-+-+-')
    print('\033[038;2;255;175;36m{}\033[038;2;255;175;36m|{}\033[038;2;255;175;36m|{}\033[0;0m'.format(board['1'], board['2'], board['3']))
def CheckWin():
    return (Board['7'] == Board['8'] == Board['9'] != ' ') or (Board['4'] == Board['5'] == Board['6'] != ' ') or (Board['1'] == Board['2'] == Board['3'] != ' ') or (Board['8'] == Board['5'] == Board['2'] != ' ') or (Board['7'] == Board['4'] == Board['1'] != ' ') or (Board['9'] == Board['6'] == Board['3'] != ' ') or (Board['7'] == Board['5'] == Board['3'] != ' ') or (Board['9'] == Board['5'] == Board['1'] != ' ')
def repeat():
    Repeat = input("\033[038;2;128;230;255mWanna Repeat(y/n) : ")
    if Repeat.lower() == 'y' or repeat == 1:
        for i in Board.keys():
            Board[i] = ' '
        game()
    elif Repeat.lower() == 'n' or repeat == 0:
        print("\033[038;2;210;156;255mGood Bye\033[0;0m")
def Player(turn):
    DisplayBoard(Board)
    running = True
    filled = False
    while running:
        if not filled:
            txt = "{}\033[038;2;128;230;255m turn. Move to which place?".format(turn)
        else:
            txt = "\033[038;2;255;128;128mCan't place there. Move to other place"
        print(txt)
        next_ = input("Enter Move : ")
        if next_.lower() == "end":
            raise SystemExit
        if Board[next_] == ' ':
            return next_
        else:
            filled = True
def game():
    
    XO = {x:o, o:x}
    turn = choice(list(XO.keys()))
    run = True
    count = 0
    while run:
        move = Player(turn)
        count += 1
        Board[move] = turn
        win = CheckWin()
        if win:
            DisplayBoard(Board)
            print("\n\033[038;2;255;175;36m-{} \033[038;2;255;175;36mWin-\n\033[0;0m".format(turn))
            run = False
        if all(Board[i] != ' ' for i in Board.keys()) and not win:
            DisplayBoard(Board)
            print("\n\033[038;2;255;175;36m-Draw!-\n\033[0;0m".format(turn))
            run = False
        turn = XO[turn]
    repeat()
if __name__ == "__main__":
    game()
