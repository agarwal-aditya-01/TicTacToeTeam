import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static TreeMap<String, Integer> map = new TreeMap<>();
    public static void PrintLeaderBoard()
    {
        List<Map.Entry<String, Integer>> sortedEntries = map.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toList());

        // Print the sorted entries
        for (Map.Entry<String, Integer> entry : sortedEntries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
    public static void main(String args[]) {

        ArrayList<Game> allGames=new ArrayList<>();
        ArrayList<Integer> gameIds=new ArrayList<>();
        Integer newGameId=0;
        System.out.println("Enter 0 to exit");
        System.out.println("Enter Start to start a new game");
        System.out.println("Enter Game Id to continue the game");
        System.out.println("Enetr LeaderBoard to get the leaderboard");
        System.out.println("Enter info to get the gameids of all running games");
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
                System.out.println("Enter the names of the player first player will get X and second one will get O enter name as a,b");
                String names=sc.nextLine();
                String[] values=names.split(",");
                Game cgame=new Game(newGameId,values[0],values[1]);
                allGames.add(cgame);
            } else if (s.equals("info")) {
                System.out.println("Currently running games: ");
                for(int i=0;i<gameIds.size();i++)
                {
                    System.out.println(gameIds.get(i));
                }
                if(gameIds.size()==0)
                    System.out.println("None");
            } else if (s.equals("LeaderBoard")) {
                PrintLeaderBoard();
            } else {
                String values = s;
                int gameId=Integer.valueOf(values);
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

                            System.out.println("Enter The coordinates to place the piece x,y");
                            String st=sc.nextLine();
                            String[] valuest = st.split(",");
                            int inputRow = Integer.valueOf(valuest[0]);
                            int inputColumn = Integer.valueOf(valuest[1]);

                            String res=allGames.get(i).makeMove(inputRow,inputColumn);
                            if(!res.equals("Invalid") && !res.equals("Continuing"))
                            {
                                System.out.println("The Game with gameId: "+gameId+" ended and the winner is: "+res);

                                if(!res.equals("tie"))
                                {
                                    int currentValue = map.getOrDefault(res, 0);
                                    int newValue = currentValue + 10;
                                    map.put(res, newValue);

                                }
                                gameIds.remove(new Integer(gameId));
                                allGames.remove(i);
                            }
                            else if(res.equals("Invalid"))
                            {
                                i--;
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

