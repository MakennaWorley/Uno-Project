/**
 * A class for playing a single Uno game.
 *
 * @author Makenna Worley
 */
public class SingleUnoGame {

   private Scoreboard scoreboard;
   private Player[] players;

   /**
    * Creates a new SingleUnoGame.
    * @param scoreboard the scoreboard to use to keep score
    * @param players the players playing the game
    */
   public SingleUnoGame(Scoreboard scoreboard, Player[] players) { //default constructor
      this.scoreboard = scoreboard;
      this.players = players;
   }
   
   /**
    * Returns the game's scoreboard.
    */
   public Scoreboard getScoreboard() {
      return scoreboard;
   }
   
   /**
    * Returns the game's players.
    */
   public Player[] getPlayers() {
      return players;
   }
   
   /**
    * Plays a single round of Uno. The winner can be determined by calling the
    * scoreboard's getWinner method.
    */
   public void playGame() {
      Game game = new Game(scoreboard, players);
      //Player.play(Player.hand, Player.upCard, Player.calledColor, Player.state);
      game.play();
   }

   public static void main(String[] args) {
      // Fill this in with your Player classes to run a four-player Uno game.
      String[] playersNames = {"Player_1", "Player_2", "Player_3", "Player_4"};
      
      Player Player_1 = new EagerPlayer();
      Player Player_2 = new SomewhatLessEagerPlayer();
      Player Player_3 = new SomewhatLessEagerPlayer();
      Player Player_4 = new MyPlayer();
      
      Player[] players = {Player_1, Player_2, Player_3, Player_4};
      
      // Set the PRINT_VERBOSE class variable in Game to true to tell it to
      // display the details of the game.
      Game.PRINT_VERBOSE = true;//-----------------------------------------------------------------------------------------------
      
      // You will need to create a Scoreboard and an array of Players, then
      // create a new SingleUnoGame object and call its playGame method.
      Scoreboard gameboard = new Scoreboard(playersNames);
      
      SingleUnoGame one = new SingleUnoGame(gameboard, players);
      one.playGame();
   }
}
