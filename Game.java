import java.util.ArrayList;

/**
 * <p>A Game object represents a single game of Uno in an overall match (of
 * possibly many games). Games are instantiated by providing them with a
 * roster of players, including a Scoreboard object through which scores
 * can be accumulated. The play() method then kicks off the game, which
 * will proceed from start to finish and update the Scoreboard. Various
 * aspects of the game's state (<i>e.g.</i>, whether the direction of play
 * is currently clockwise or counterclockwise, whose player's turn is next)
 * can be accessed and controlled through methods on this class.</p>
 * <p>A GameState object can be obtained through the getGameState() method,
 * which allows UnoPlayers to selectively and legally examine certain
 * aspects of the game's state.</p>
 * @since 1.0
 * 
 * @author Makenna Worley
 */
public class Game {

    /**
     * The number of cards each player will be dealt at start of game.
     */
   protected static final int INIT_HAND_SIZE = 7;

    /** 
     * Controls how many messages fly by the screen while narrating an Uno
     * match in text.
     */
   public static boolean PRINT_VERBOSE = true;
    
    /**
     * An object representing the state of the game at any point in time.
     * Note that much of the "state" is represented in the Game object
     * itself, and that this object provides a double-dispatch vehicle
     * through which select methods can access that state.
     */
   private GameState state;

    /* package-visibility variables to simplify access between Game and
     * GameState classes */
   protected Deck deck;
   protected Hand[] hands;
   protected Card upCard;
   protected Direction direction;
   protected int currPlayer;
   protected Color calledColor;
   protected Scoreboard scoreboard;
   protected Color[] mostRecentColorCalled;

    /**
     * Constructor to instantiate a Game of  Provided must be two
     * objects indicating the player roster: a Scoreboard, and a player
     * list. This constructor will deal hands to all players, select a
     * non-action up card, and reset all game settings so that play() can
     * be safely called.
     * @param scoreboard A fully-populated Scoreboard object that contains
     * the names of the contestants, in order.
     * @param playerList An array of Players, each of which is an object
     * implementing the Player interface.
     */
   public Game(Scoreboard scoreboard, Player[] playerList) {
      this.scoreboard = scoreboard;
      deck = new Deck();
      hands = new Hand[scoreboard.getNumPlayers()];
      mostRecentColorCalled =
            new Color[scoreboard.getNumPlayers()];
      try {
         for (int i=0; i<scoreboard.getNumPlayers(); i++) {
            hands[i] = new Hand(playerList[i],
                    scoreboard.getPlayerList()[i]);
            for (int j=0; j<INIT_HAND_SIZE; j++) {
               hands[i].addCard(deck.draw());
            }
         }
         upCard = deck.draw();
         while (upCard.isWildCard() || upCard.getRank() == Rank.CUSTOM) { //---------------------------------------------------------- move line 92
            deck.discard(upCard);
            upCard = deck.draw();
         }
      }
      catch (Exception e) {
         System.out.println("Can't deal initial hands!");
         System.exit(1);
      }
      direction = Direction.FORWARDS;
      currPlayer =
            new java.util.Random().nextInt(scoreboard.getNumPlayers());
      calledColor = Color.NONE;
   }

   private void printState() {
      for (int i=0; i<scoreboard.getNumPlayers(); i++) {
         System.out.println("Hand #" + i + ": " + hands[i]);
      }
   }

    /**
     * Return the number of the <i>next</i> player to play, provided the
     * current player doesn't jack that up by playing an action card.
     * @return An integer from 0 to scoreboard.getNumPlayers()-1.
     */
   public int getNextPlayer() {
      int numPlayers = scoreboard.getNumPlayers();
      if (direction == Direction.FORWARDS) {
         return (currPlayer + 1) % numPlayers;
      }
      else {
         return (numPlayers + currPlayer - 1) % numPlayers;
      }
   }

    /**
     * Go ahead and advance to the next player.
     */
   protected void advanceToNextPlayer() {
      currPlayer = getNextPlayer();
   }

    /**
     * Change the direction of the game from clockwise to counterclockwise
     * (or vice versa.)
     */
   protected void reverseDirection() {
      if (direction == Direction.FORWARDS) {
         direction = Direction.BACKWARDS;
      }
      else {
         direction = Direction.FORWARDS;
      }
   }

   public void nextPlayerDraw() throws DeckExhaustedException {
      int nextPlayer = getNextPlayer();
      Card drawnCard;
      try {
         drawnCard = deck.draw();
      }
      catch (DeckExhaustedException e) {
         print("...deck exhausted, remixing...");
         deck.remix();
         drawnCard = deck.draw();
      }
      hands[nextPlayer].addCard(drawnCard);
        //game.println("  Player #" + nextPlayer + " draws " + drawnCard + ".");
      println("  " + hands[nextPlayer].getPlayerName() + " draws " +
                drawnCard + ".");
   
   }

   private Card getPlayedCard(int player) {
      return hands[player].play(this);
   }

    /**
     * Play an entire Game of Uno from start to finish. Hands should have
     * already been dealt before this method is called, and a valid up card
     * turned up. When the method is completed, the Game's scoreboard object
     * will have been updated with new scoring favoring the winner.
     */
   public void play() {
      println("Initial upcard is " + upCard + ".");
      try {
         while (true) {
         // each loop iteration is one player's turn
            Card playedCard = playOneTurn();
                             
            if (setUpForNextPlayer(playedCard) == false) {
            // if the player has won, we're done
               announceWinner();
               return;
            }
         
         }
      }
      catch (DeckExhaustedException e) {
         System.out.println("Deck exhausted! This game is a draw.");
      }
   }

    // plays one turn of Uno, and returns the card that was played.
    // if the card is a special card, playOneTurn does NOT handle its effects
   protected Card playOneTurn() throws DeckExhaustedException {
      // println("Hand #" + currPlayer + " (" + hands[currPlayer] + ")");
      print(hands[currPlayer].getPlayerName() +
               " (" + hands[currPlayer] + ")");
      Card playedCard = getPlayedCard(currPlayer);
      if (playedCard == null) { //player will draw a card
         Card drawnCard;
         try {
            drawnCard = deck.draw();
         }
         catch (DeckExhaustedException e) {
            print("...deck exhausted, remixing...");
            deck.remix();
            drawnCard = deck.draw();
         }
         hands[currPlayer].addCard(drawnCard);
         print(" has to draw (" + drawnCard + ").");
         playedCard = getPlayedCard(currPlayer);
      }
         
      if (playedCard != null) { //a card was played
         print(" plays " + playedCard + " on " + upCard + "."); //mirror card check flag
         if (playedCard.getRank() == Rank.CUSTOM || playedCard.getRank() == Rank.WILD || playedCard.getRank() == Rank.WILD_D4) {//-------------------------------------------------------------------------
            deck.discard(playedCard);
         } else {
            deck.discard(upCard);
         }
         upCard = playedCard;
         if (upCard.isWildCard()) { //sets calledColor to color if wild
            calledColor = hands[currPlayer].callColor(this);
            mostRecentColorCalled[currPlayer] = calledColor;
            print(" (and calls " + calledColor +
                     ").");
         }
         else { //if wild isn't played, calledColor is NONE
            calledColor = Color.NONE;
         }
      }
   
      return playedCard;
   }

    // checks whether the player that just played "playedCard" has Uno or has won
    // if not and if the playedCard is a special card, this method will handle the special effects
    // returns true if the game should continue (ready for next player)
    // returns false if the game has ended (next player does not get to play)
   protected boolean setUpForNextPlayer(Card playedCard) throws DeckExhaustedException {
   // don't forget to say Uno!              
      if (hands[currPlayer].size() == 1) {
         print(" UNO!");
      } 
      else if (hands[currPlayer].isEmpty()) {
         return false; // can't set up - the player has won!
      }      
      println("");
         
     // if player played a special card, handle it
      if (playedCard != null) {
         performCardEffect(playedCard);
      }
      else {
         advanceToNextPlayer();
      }
      return true;
   }

   protected void announceWinner() {
      int roundPoints = 0;
      for (int j=0; j<scoreboard.getNumPlayers(); j++) {
         roundPoints += hands[j].countCards();
      }
      println("\n" + hands[currPlayer].getPlayerName() +
                  " wins! (and collects " + roundPoints + " points.)");
      scoreboard.addToScore(currPlayer,roundPoints);
      println("---------------\n" + scoreboard);
   }
    
   protected void print(String s) {
      if (PRINT_VERBOSE) {
         System.out.print(s);
      }
   }

   protected void println(String s) {
      if (PRINT_VERBOSE) {
         System.out.println(s);
      }
   }

    /**
     * Return the GameState object, through which the state of the game can
     * be accessed and safely manipulated.
     */
   public GameState getGameState() {
      return new GameState(this);
   }

    /**
     * Return the Card that is currently the "up card" in the game.
     */
   public Card getUpCard() {
      return upCard;
   }

    /**
     * Return the last color that was called (when a wildcard was played). The
     * return value of this method is only relevant if getUpCard() is a
     * wildcard.
     */
   public Color getCalledColor() {
      return calledColor;
   }

    /**
     * This method should be called immediately after a Card is played,
     * and will trigger the effect peculiar to that card. For most cards,
     * this merely advances play to the next player. Some special cards
     * have other effects that modify the game state. Examples include a
     * Skip, which will advance <i>twice</i> (past the next player), or a
     * Draw Two, which will cause the next player to have to draw cards.
     * throw
     * @param card@throws DeckExhaustedException Thrown only in very exceptional cases
     * when a player must draw as a result of this card's effect, yet the
     * draw cannot occur because of un-shufflable deck exhaustion.
     */
   protected void performCardEffect(Card card) throws DeckExhaustedException {
      switch (card.getRank()) {
         case SKIP:
            advanceToNextPlayer();
            advanceToNextPlayer();
            break;
         case REVERSE:
            reverseDirection();
            advanceToNextPlayer();
            break;
         case DRAW_TWO: //can play a mirror card to pass to next player-----------------------------------------------------------
            if (mirrorCardNext() == true) {
               //leave blank
            } else {
               nextPlayerDraw();
               nextPlayerDraw();
               advanceToNextPlayer();
               advanceToNextPlayer();
            }
            break;
         case WILD_D4: //can play a mirror to pass to next player-------------------------------------------------------------------
            if (mirrorCardNext() == false) {
               nextPlayerDraw();
               nextPlayerDraw();
               nextPlayerDraw();
               nextPlayerDraw();
               advanceToNextPlayer();
               advanceToNextPlayer();
            } else {
               //leave blank
            }
            break;
         default:
            advanceToNextPlayer();
            break;
      }
   }
   
   //---------------------------------------------------------------------------------------------------------------
   
   private boolean mirrorCardNext() {
      advanceToNextPlayer(); //returns nothing
      //call from hand playMirror on a player object
      if (hands[currPlayer].playMirror(this) == true) {
         return true;
      } else {
      //else flase: return to current player and normal direction
         reverseDirection();
         advanceToNextPlayer();
         reverseDirection();
         return false;
      }
   }
}
