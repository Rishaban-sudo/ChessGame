package com;

import com.chessGame.Board;
import com.chessGame.Game;
import com.chessGame.Player;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Board chessBoard = new Board();
        Player player1=null,player2=null;

        System.out.println("Enter Player 1 name : ");
        String name= sc.nextLine();
        System.out.println("Which side do you prefer? ");
        System.out.println("1.White side ");
        System.out.println("2.Black side ");
        int choice=sc.nextInt();

        switch (choice) {
            case 1:
                player1 = new Player(name, true);
                System.out.println("Enter Player 2 name : ");
                sc.nextLine();
                name=sc.nextLine();
                player2 = new Player(name, false);
            break;
            case 2:
                player1 = new Player(name, false);
                System.out.println("Enter Player 2 name : ");
                sc.nextLine();
                name=sc.nextLine();
                player2 = new Player(name, false);
            break;
        }


        Game game = new Game(player1,player2,chessBoard);

        System.out.println("---------------------------------------------------");
        chessBoard.displayBoard();

        while(!game.isCheckmate())
        {
            Player player = game.getCurrentTurn();
            System.out.println(player.getName()+" 's turn ");

            System.out.println("Enter your start location (x,y): ");
            int startX = sc.nextInt();
            int startY = sc.nextInt();

            System.out.println("Enter your end location (x,y): ");
            int endX = sc.nextInt();
            int endY = sc.nextInt();

            game.playerMove(player, startX,startY, endX,endY );
            System.out.println("---------------------------------------------------");
            chessBoard.displayBoard();
        }
    }
}
