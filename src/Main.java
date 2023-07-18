import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    /*
    *
        Game game = new Game(1);
        game.initializeGame();
        System.out.println("game winner is: " + game.startGame());
    * */
    public static void main(String args[]) {

        ArrayList<Game> allGames=new ArrayList<>();
        ArrayList<Integer> gameIds=new ArrayList<>();
        Integer newGameId=0;
        System.out.println("Enter 0 to exit");
        System.out.println("Enter Start to start a new game");
        System.out.println("Enter Game Id  to put in the game");
        Scanner sc=new Scanner(System.in);
        while (true)
        {
            String s=sc.nextLine();
            if(s.equals("0"))
            {
                break;
            } else if (s.equals("Start")) {
                newGameId+=1;
                gameIds.add(newGameId);
                System.out.println("Your new game Id is: "+newGameId);
                Game cgame=new Game(newGameId);
                cgame.initializeGame();
                allGames.add(cgame);
            }
            else {
                String[] values = s.split(",");
                int gameId=Integer.valueOf(values[0]);


                if(gameIds.contains(gameId))
                {
                    for(int i=0;i<allGames.size();i++)
                    {
                        if(allGames.get(i).GameId==gameId)
                        {
                            System.out.println("Current Board is: ");
                            allGames.get(i).gameBoard.printBoard();
                            System.out.println("Currently it is chance of: "+allGames.get(i).players.getFirst().name);
                            System.out.println("===========================================");

                            System.out.println("Enter The coordinates to place the piece");
                            String st=sc.nextLine();
                            String[] valuest = st.split(",");
                            int inputRow = Integer.valueOf(valuest[0]);
                            int inputColumn = Integer.valueOf(valuest[1]);

                            String res=allGames.get(i).startGame(inputRow,inputColumn);
                            if(!res.equals("Invalid") && !res.equals("Continuing"))
                            {
                                System.out.println("The Game with gameId: "+gameId+" ended and the winner is: "+res);
                                gameIds.remove(new Integer(gameId));
                                allGames.remove(i);
                            }
                        }
                    }
                }
                else
                {
                    System.out.println("Please enter correct game id");
                }
            }
        }
    }
}

