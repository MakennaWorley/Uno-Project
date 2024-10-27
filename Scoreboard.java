/**
* A Scoreboard is a simple container for player names and their current
* scores. It can do the obvious things like increment someone's score,
* find the score for a particular player, and determine the winner at the
* end of the game.
*
* @author Makenna Worley
*/
public class Scoreboard {
   // fill in two instance variables here
   private String[] playerName;
   private int[] playerScore;



  /**
    * Instantiate a new Scoreboard object, given an array of player names.
    */
    //constructor
   public Scoreboard(String[] playerList) { //don't make playerList index 0, passing array
      playerName = playerList;
      playerScore = new int[6];
   }



  /**
    * Award points to a particular player.
    * @param player The zero-based player number who just won a game.
    * @param points The number of points to award.
    */
   public void addToScore(int player, int points) {
      if (player > this.playerScore.length || player < 0) {
         //don't do anything
      } else {
         if (this.playerScore.length == 0) { //value doesn't exist
            this.playerScore[player]  = points;
         } else {
            this.playerScore[player]  =  (this.playerScore[player] + points); //value exists and needs to be changed
         }
      }
   }



  /**
    * Obtain the score of a particular player.
    * @param player The zero-based player number whose score is desired.
    * @returns score of zero-based player or 0 if invalid player number provided
    */
   public int getScore(int player) {
      if (player > this.playerScore.length || player < 0) {
         return 0;
      } else {
         return this.playerScore[player];
      }
   }



  /**
    * Render the Scoreboard as a string for display during game play.
    * The returned string should list each player's name and their current
    * score.
    */
   public String toString() {
   
      String scoreboard = "Player Name: \n";
   
      for(int i = 0; i < this.playerName.length; i++) {
         scoreboard = scoreboard + "\t" + this.playerName[i] + " " + this.playerScore[i] + "\n";
      }
      
      return scoreboard;
   }



  /**
    * Return the list of player names.
    */
   public String[] getPlayerList() {
      return this.playerName;
   }



  /**
    * Return the number of players in the game.
    */
   public int getNumPlayers() {
      return this.playerName.length;
   }



  /**
    * Return the zero-based number of the player who has won the game,
    * presuming someone has. (This method should only be called
    * once the end of the entire match has been detected by some other
    * means, and returns the number of the player with the highest score.)
    */
   public int getWinner() {
   // not required for homework 8, but will be needed for the project
      int max = this.playerScore[0];
      int playKey = 0;
         
      for (int i = 0; i < this.playerScore.length; i++) {
         //System.out.println("this is max at loop beginning " + max);
         //System.out.println("this is player score at beginning " + playerScore[i]);
         //System.out.println();
         
         if (max < this.playerScore[i]) {
            //System.out.println("loop " + i);
            max = this.playerScore[i];
            playKey = i;
            //System.out.println("player score " + playKey);
         } else {
            
         }
      }
      
      return playKey;
   }
   
   /*public static void main(String [] args) {
      String[] playerNames = {
         "John", "Susan", "Michael", "Delenn", "Na'toth"
         };
      
      Scoreboard score = new  Scoreboard(playerNames);
      
      score.addToScore(0, 20);
      score.addToScore(1, 80);
      score.addToScore(2, 60);
      
      score.getWinner();
   }*/
}