/**
 * A class for playing multiple Uno games at once with a standing scoreboard.
 * The playGames method will play a specified number of games (using a 
 * SingleUnoGame's playGame method repeatedly).
 *
 * @author Makenna Worley
 */
public class MultipleUnoGame {

   private SingleUnoGame singleGame;
   private int gamesPlayed = 0;
   private long updateInterval = 0;
   
   /**
    * Create a new MultipleUnoGame using the given scoreboard and players.
    */
   public MultipleUnoGame(Scoreboard scoreboard, Player[] players) {
      this.singleGame = new SingleUnoGame(scoreboard, players);
   }
   
   /**
    * Returns the update interval (in milliseconds). If this number is positive
    * then the playGames method will periodically display scoreboard updates.
    */
   public long getUpdateInterval() {
      return updateInterval;
   }
   
   /**
    * Sets the update interval (in milliseconds, so for example 500 corresponds
    * to half a second). If this interval is positive, then scoreboard updates
    * will be printed out by the playGames method according to the interval.
    * (If the interval is 500, then updates will be printed every half second.)
    */
   public void setUpdateInterval(long updateInterval) {
      this.updateInterval = updateInterval;
   }
   
   /**
    * Play numGames games (by repeated call to SingleUnoGame.play()). The same
    * Scoreboard is used for all, so the points in it will accumulate across
    * all games. This method may be called repeatedly to play additional
    * batches of games.
    *
    * If updateInterval is positive (see setUpdateInterval), will display
    * periodic updates.
    */
   public void playGames(int numGames) {
      long lastUpdateTime = System.currentTimeMillis();
      for (int i = 0; i < numGames; i++, gamesPlayed++) {
         //System.out.println("i");
         singleGame.playGame();
         
         long currentTime = System.currentTimeMillis();
         if (updateInterval > 0 && currentTime > lastUpdateTime + updateInterval) {
            // It's been 500 milliseconds (half a second) - show the current status
            printStatus();
            lastUpdateTime = currentTime;
         }
      }
      
      printStatus();
   
      Scoreboard board = getScoreboard();
      int winnerIndex = board.getWinner();
      String winnerName = board.getPlayerList()[winnerIndex];
   
      System.out.printf("=== The winner is %s! Congratulations! ===\n\n", winnerName);
   }
   
   /**
    * Prints a status message consisting of the number of games played and the
    * current scoreboard.
    */
   public void printStatus() {
      System.out.printf("After %,d games:\n", gamesPlayed);
      System.out.println(getScoreboard());
      System.out.println();
   }
   
   /**
    * Returns the scoreboard.
    */
   public Scoreboard getScoreboard() {
      return singleGame.getScoreboard();
   }

   /**
    * Returns the number of games played so far.
    */
   public int getGamesPlayed() {
      return gamesPlayed;
   }
   
   public static void main(String[] args) {
      Game.PRINT_VERBOSE = false;
      String[] playerNames = { "Player 1", "Player 2", "Player 3", "Player 4" };
      Scoreboard scoreboard = new Scoreboard(playerNames);
      Player[] players = {
         new EagerPlayer(),
         new SomewhatLessEagerPlayer(),
         new MyPlayer(),
         new SomewhatLessEagerPlayer()
         };
      int total = 0;
      double p1P = 0;
      double p2P = 0;
      double p3P = 0;
      double p4P = 0;
      
      MultipleUnoGame multiGame = new MultipleUnoGame(scoreboard, players);
      multiGame.setUpdateInterval(5000); // updates every half second
      multiGame.playGames(100_000); // play 100,000 games 100_000
      
      for (int i = 0; i < players.length; i++) {
         total = scoreboard.getScore(i) + total;
      }
      System.out.println("Total points: " + total);
            
      p1P = ((double)scoreboard.getScore(0)/total)*100;
      System.out.print("EagerPlayer percent: ");
      System.out.printf("%2.2f", p1P);
      System.out.println();
      
      p2P = ((double)scoreboard.getScore(1)/total)*100;
      System.out.print("SomewhatLessEagerPlayer percent: ");
      System.out.printf("%2.2f", p2P);
      System.out.println();
      
      p3P = ((double)scoreboard.getScore(2)/total)*100;
      System.out.print("MyPlayer percent: ");
      System.out.printf("%2.2f", p3P);
      System.out.println();
      
      p4P = ((double)scoreboard.getScore(3)/total)*100;
      System.out.print("SomewhatLessEagerPlayer percent: ");
      System.out.printf("%2.2f", p4P);
      System.out.println();
      
   }
}