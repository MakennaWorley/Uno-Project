/**
 * <p>A Card in an Uno deck. Each Card knows its particular type, which is
 * comprised of a 3-tuple (color, rank, number). Not all of these values
 * are relevant for every particular type of card, however; for instance,
 * wild cards have no color (getColor() will return Color.NONE) or number
 * (getNumber() will return -1).</p>
 * <p>A Card knows its forfeit cost (<i>i.e.</i>, how many points it counts
 * against a loser who gets stuck with it) and how it should act during
 * game play (whether it permits the player to change the color, what
 * effect it has on the game state, etc.)</p>
 * @since 1.0
 * 
 * @author Makenna Worley
 */
public class Card {
   private Color color;
   private Rank rank;
   private int number;

   /**
    * Constructor for non-number cards (skips, wilds, etc.)
    */
   public Card(Color color, Rank rank) {
      this.color = color;
      this.rank = rank;
      this.number = -1;
   }

   /**
    * Constructor for number cards.
    */
   public Card(Color color, int number) {
      this.color = color;
      this.rank = Rank.NUMBER;
      this.number = number;
   
   }

   /**
    * Constructor to explicitly set entire card state.
    */
   public Card(Color color, Rank rank, int number) {
      this.color = color;
      this.rank = rank;
      this.number = number;
   
   }

   /**
    * Returns the number of points this card will count against a player
    * who holds it in his/her hand when another player goes out.
    */
   public int forfeitCost() {
   
      if (number > -1 && this.rank == Rank.NUMBER) {
         return number;
      } else if (this.rank == Rank.SKIP || this.rank == Rank.REVERSE || this.rank == Rank.DRAW_TWO) {
         return 20;
      } else if (this.rank == Rank.WILD || this.rank == Rank.WILD_D4 || this.rank == Rank.CUSTOM) {
         return 50;
      } else {
         return 0;
      }
   }

   /**
    * Returns true only if this Card can legally be played on the up card
    * passed as an argument. The second argument is relevant only if the
    * up card is a wild.
    * @param upCard An "up card" upon which the current object might (or might
    * not) be a legal play.
    * @param calledColor If the up card is a wild card, this parameter
    * contains the color the player of that color called.
    */ 
   public boolean canPlayOn(Card upCard, Color calledColor) {
      /*if (upCard.rank == Rank.WILD) {
         System.out.println(1);
         this.color = calledColor;
      }*/
      
      if (this.color == upCard.color) { //same color any card with color play
         //System.out.println(2);
         return true;
      } else if (this.number == upCard.number && this.number != -1) { //regular number card
         //System.out.println(3);
         return true;
      } else if (this.number == -1 && this.rank == upCard.rank) { //special card
         //System.out.println(3);
         return true;
      } else if (this.rank == Rank.WILD) { //wild cards
         //System.out.println(5);
         return true;
      } else if (upCard.rank == Rank.WILD && this.color == calledColor) { //Wild card just played
         //System.out.println(6);
         return true;
       } else if (upCard.rank == Rank.WILD_D4 && this.color == calledColor) { //Wild draw 4 just played
         //System.out.println(7);
         return true;
       } else if (this.rank == Rank.CUSTOM) { //Mirror just played
         //System.out.println(8);
         return true;
      } else if (this.color == Color.NONE) { //wild is being played
         //System.out.println(10);
         return true;
      } else {
         return false;
      }
   }

   /**
    * Returns true only if playing this Card object would result in the
    * player being asked for a color to call. (In the standard game, this
    * is true only for wild cards.)
    */
   public boolean isWildCard() {
      if (this.rank == Rank.WILD || this.rank == Rank.WILD_D4) {
         return true;
      } else {
         return false;
      }
   }

   /**
    * Returns the color of this card, which is Color.NONE in the case of
    * wild cards.
    */
   public Color getColor() {
      return color;
   }

   /**
    * Returns the rank of this card, which is Rank.NUMBER in the case of
    * number cards (calling getNumber() will retrieve the specific
    * number.)
    */
   public Rank getRank() {
      return rank;
   }

   /**
    * Returns the number of this card, which is guaranteed to be -1 for
    * non-number cards (cards of non-Rank.NUMBER rank.)
    */
   public int getNumber() {
      if (this.rank != Rank.NUMBER) {
         return -1;
      } else {
         return this.number;
      }
   }

   /**
    * Returns this Card object as a string. 
    */
   public String toString() {//has been modifed-----------------------------------------------------------------
      if (color == Color.NONE) {
         return "" + this.rank;
      } else if (number == -1) {   
         return "" + this.color + " " + this.rank;
      } else {
         return "" + this.color + " " + this.number;
      }
   
   
      /*if (number == -1) {
         return "" + this.color + " " + this.rank;
      } else {   
         return "" + this.color + " " + this.rank + " " + this.number;
      }*/
   }
   
   public static void main(String[] args) {
      /*Card redReverse = new Card(Color.RED, Rank.REVERSE);
      Card showing = new Card(Color.BLUE, Rank.SKIP);
      System.out.println(redReverse.canPlayOn(showing, Color.BLUE));*/
      
      /*Card redSkip = new Card(Color.RED, Rank.SKIP);
      Card showing = new Card(Color.BLUE, 6);
      System.out.println(redSkip.canPlayOn(showing, Color.BLUE));*/
      
      /*Card blueTwo = new Card(Color.BLUE, Rank.NUMBER, 2);
      Card showing = new Card(Color.RED, Rank.NUMBER, 6);
      System.out.println(blueTwo.canPlayOn(showing, Color.BLUE));*/
      
      /*Card redSkip = new Card(Color.RED, Rank.SKIP);
      Card showing = new Card(Color.BLUE, Rank.SKIP);
      System.out.println(redSkip.canPlayOn(showing, Color.BLUE));*/
      
      /*Card blueSix = new Card(Color.BLUE, Rank.NUMBER, 6);
      Card showing = new Card(Color.NONE, Rank.WILD);
      System.out.println(blueSix.canPlayOn(showing, Color.BLUE));*/
      
      /*Card blueSix = new Card(Color.BLUE, Rank.NUMBER, 6);
      Card showing = new Card(Color.NONE, Rank.WILD);
      System.out.println(blueSix.canPlayOn(showing, Color.RED));*/
      
      Card mirror = new Card(Color.NONE, Rank.CUSTOM);
      Card showing = new Card(Color.NONE, Rank.CUSTOM);
      System.out.println(mirror.canPlayOn(showing, Color.GREEN));
   }
}
