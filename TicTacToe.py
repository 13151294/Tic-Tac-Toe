#Python 3.9.5
#Compute by Ace Fhid
from random import choice
Board = {
    '7':' ', '8':' ', '9':' ',
    '4':' ', '5':' ', '6':' ',
    '1':' ', '2':' ', '3':' ',
}
#Clear the board
def clear():
    for i in Board:
        Board[i] = ' '
def DisplayBoard(board):
    print('{}|{}|{}'.format(board['7'], board['8'], board['9']))
    print('-+-+-')
    print('{}|{}|{}'.format(board['4'], board['5'], board['6']))
    print('-+-+-')
    print('{}|{}|{}'.format(board['1'], board['2'], board['3']))
def CheckWin(board):
    win = False
    #Horizontal win check
    if board['7'] == board['8'] == board['9'] != ' ' or board['4'] == board['5'] == board['6'] != ' ' or board['1'] == board['2'] == board['3'] != ' ':
        win = True
    #Vertical win Check
    elif board['8'] == board['5'] == board['2'] != ' ' or board['7'] == board['4'] == board['1'] != ' ' or board['9'] == board['6'] == board['3'] != ' ':
        win = True
    elif board['7'] == board['5'] == board['3'] != ' ':
        win = True
    elif board['9'] == board['5'] == board['1'] != ' ':
        win = True
    return win
def Game():
    XO = {'X':'O', 'O':'X'}
    turn = choice(list(XO.keys()))
    #random turn
    count = 0
    running = True
    win = False
    run = True
    filled = False
    while running:
        DisplayBoard(Board)
        if filled == False:
            text = "It's your turn {}. Move to which place?\n".format(turn)
        else:
            text = "That place is already filled. Move to another place.\n"
        move = input(text)
        if Board[move] == ' ':
            filled = False
            Board[move] = turn
            count += 1
        else:
            filled = True
            continue
        win = CheckWin(Board)
        if count >= 5 and win:
            DisplayBoard(Board)
            print("-{} Win-".format(turn))
            break
        #Check for tie
        if all(key != ' ' for key in Board) and count >= 9:
            DisplayBoard(Board)
            print("-Tie-")
            break
        turn = XO[turn]
    while run:
        Again = input("Play Again? (y/n): ").lower()
        if Again == 'y':
            clear()
            Game()
            run = False
        elif Again == 'n':
            print("Good bye")
            run = False
        else:
            print("Wrong Input")
            continue
Game()