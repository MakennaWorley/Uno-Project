/**
 * A play
 *
 * @author Makenna Worley
 */
public class EagerPlayer extends Player {
   int minCardIndex;
   int minNormalIndex;
   boolean hasPlayRun = false;
  
   /*EagerPlayer (Card[] hand, Card upCard, Color calledColor, GameState state) {
      super(Card[] hand, Card upCard, Color calledColor, GameState state);
   }*/


   public int play(Card[] hand, Card upCard, Color calledColor, GameState state) {//call canPlayOn from Card
      int wildCardIndex = NO_PLAY_POSSIBLE;
      minCardIndex = -1;
      for (int i = 0; i < hand.length; i++) {
         Card play = hand[i];
         if (play.getRank() == Rank.WILD_D4) {
            wildCardIndex = i;
         } else if (play.canPlayOn(upCard, calledColor) == true) {
            minCardIndex = i;
            return minCardIndex;
         }
      }
      hasPlayRun = true;
      return wildCardIndex;
   }
   
   public int nonMirrorCard(Card[] hand, Card upCard, Color calledColor, GameState state) {
      minNormalIndex = -1;
      for (int i = 0; i < hand.length; i++) {
         Card play = hand[i];
         if (play.getRank() != Rank.CUSTOM) {
            minNormalIndex = i;
         }
      }
      System.out.println("No mirror card included: " + minNormalIndex);
      return minNormalIndex;
   }
   
   public Color callColor(Card[] hand) {
      return Color.RED;
   }
   
   public static void main(String[] args) {
      Card red0 = new Card(Color.RED, Rank.NUMBER, 0);
      Card red1 = new Card(Color.RED, Rank.NUMBER, 1);
      Card red3 = new Card(Color.RED, Rank.NUMBER, 3);
      Card red4 = new Card(Color.RED, Rank.NUMBER, 4);
      Card red6 = new Card(Color.RED, Rank.NUMBER, 6);
      Card red7 = new Card(Color.RED, Rank.NUMBER, 7);
      Card red8 = new Card(Color.RED, Rank.NUMBER, 8);
      Card redSkip = new Card(Color.RED, Rank.SKIP);
   
      Card yellow1 = new Card(Color.YELLOW, Rank.NUMBER, 1);
      Card yellow2 = new Card(Color.YELLOW, Rank.NUMBER, 2);
      Card yellow3 = new Card(Color.YELLOW, Rank.NUMBER, 3);
      Card yellow4 = new Card(Color.YELLOW, Rank.NUMBER, 4);
      Card yellow5 = new Card(Color.YELLOW, Rank.NUMBER, 5);
      Card yellow6 = new Card(Color.YELLOW, Rank.NUMBER, 6);
      Card yellow7 = new Card(Color.YELLOW, Rank.NUMBER, 7);
      Card yellow8 = new Card(Color.YELLOW, Rank.NUMBER, 8);
      Card yellowReverse = new Card(Color.YELLOW, Rank.REVERSE);
      Card yellowSkip = new Card(Color.YELLOW, Rank.SKIP);
   
      Card green0 = new Card(Color.GREEN, Rank.NUMBER, 0);
      Card green1 = new Card(Color.GREEN, Rank.NUMBER, 1);
      Card green3 = new Card(Color.GREEN, Rank.NUMBER, 3);
      Card green4 = new Card(Color.GREEN, Rank.NUMBER, 4);
      Card green5 = new Card(Color.GREEN, Rank.NUMBER, 5);
      Card green6 = new Card(Color.GREEN, Rank.NUMBER, 6);
      Card green7 = new Card(Color.GREEN, Rank.NUMBER, 7);
      Card green8 = new Card(Color.GREEN, Rank.NUMBER, 8);
      Card greenDraw2 = new Card(Color.GREEN, Rank.DRAW_TWO);
      Card greenSkip = new Card(Color.GREEN, Rank.SKIP);
   
      Card blue0 = new Card(Color.BLUE, Rank.NUMBER, 0);
      Card blue1 = new Card(Color.BLUE, Rank.NUMBER, 1);
      Card blue2 = new Card(Color.BLUE, Rank.NUMBER, 2);
      Card blue3 = new Card(Color.BLUE, Rank.NUMBER, 3);
      Card blue4 = new Card(Color.BLUE, Rank.NUMBER, 4);
      Card blue5 = new Card(Color.BLUE, Rank.NUMBER, 5);
      Card blue6 = new Card(Color.BLUE, Rank.NUMBER, 6);
      Card blue7 = new Card(Color.BLUE, Rank.NUMBER, 7);
      Card blue8 = new Card(Color.BLUE, Rank.NUMBER, 8);
      Card blue9 = new Card(Color.BLUE, Rank.NUMBER, 9);
      Card blueDraw2 = new Card(Color.BLUE, Rank.DRAW_TWO);
      Card blueReverse = new Card(Color.BLUE, Rank.REVERSE);
   
      Card mirror = new Card(Color.NONE, Rank.CUSTOM);
      Card wildDraw4 = new Card(Color.NONE, Rank.WILD_D4);
      Card wild = new Card(Color.NONE, Rank.WILD);
   
   
      /*Color call = Color.RED;
      GameState state = new GameState();
      EagerPlayer player = new EagerPlayer();
      Card[] hand = {yellowReverse,green8, yellow4, green5, blueReverse, blue6, red8, blueDraw2, mirror};
      Card showing = new Card(Color.BLUE, Rank.NUMBER, 9);*/
      
      
      
      /*Color call = Color.RED;
      GameState state = new GameState();
      EagerPlayer player = new EagerPlayer();
      Card[] hand = {mirror, mirror, red7, blue2, wildDraw4, redSkip, mirror, yellow5, blue4};
      Card showing = new Card(Color.NONE, Rank.WILD_D4);*/
      
      
      
      /*Color call = Color.GREEN;
      GameState state = new GameState();
      EagerPlayer player = new EagerPlayer();
      Card[] hand = {blueDraw2, wild, yellow6, blue8, blue5, green8, wildDraw4, yellowSkip, wildDraw4};
      Card showing = new Card(Color.RED, Rank.NUMBER, 3);*/
      
      
      
      
      /*Color call = Color.BLUE;
      GameState state = new GameState();
      EagerPlayer player = new EagerPlayer();
      Card[] hand = {yellowSkip, blue4, yellow3, yellow3, greenSkip};
      Card showing = new Card(Color.GREEN, Rank.DRAW_TWO);*/
      
      
      
      /*Color call = Color.BLUE;
      GameState state = new GameState();
      EagerPlayer player = new EagerPlayer();
      Card[] hand = {greenDraw2, wild, mirror, redSkip, blue0, green0, red3, greenSkip, red4, blue2};
      Card showing = new Card(Color.YELLOW, Rank.NUMBER, 6);*/
      
      
      
      /*Color call = Color.YELLOW;
      GameState state = new GameState();
      EagerPlayer player = new EagerPlayer();
      Card[] hand = {blue7, red1, yellow7, wildDraw4, wild, yellow7, yellow4, blue1};
      Card showing = new Card(Color.NONE, Rank.WILD_D4);*/
      
      
      
      /*Color call = Color.BLUE;
      GameState state = new GameState();
      EagerPlayer player = new EagerPlayer();
      Card[] hand = {green8, wildDraw4, yellow1, mirror, green6, blue1, green7, red1, yellow8};
      Card showing = new Card(Color.RED, Rank.REVERSE);*/
      
      
      
      /*Color call = Color.BLUE;
      GameState state = new GameState();
      EagerPlayer player = new EagerPlayer();
      Card[] hand = {blue3, blue1, mirror, blue2, blue0};
      Card showing = new Card(Color.YELLOW, Rank.NUMBER, 9);*/
      
      
      
      /*Color call = Color.BLUE;
      GameState state = new GameState();
      EagerPlayer player = new EagerPlayer();
      Card[] hand = {mirror, blue9, blue8, blue9, mirror, red7, wildDraw4, red0, green4, green5};
      Card showing = new Card(Color.YELLOW, Rank.REVERSE);*/
      
      
      
      /*Color call = Color.RED;
      GameState state = new GameState();
      EagerPlayer player = new EagerPlayer();
      Card[] hand = {wildDraw4, yellow2, green1};
      Card showing = new Card(Color.NONE, Rank.WILD);*/
      
      
      
      /*Color call = Color.YELLOW;
      GameState state = new GameState();
      EagerPlayer player = new EagerPlayer();
      Card[] hand = {red6, green3, wildDraw4};
      Card showing = new Card(Color.YELLOW, Rank.DRAW_TWO);*/
      
      
      
      /*Color call = Color.GREEN;
      GameState state = new GameState();
      EagerPlayer player = new EagerPlayer();
      Card[] hand = {wildDraw4, yellow1, wildDraw4, red1, mirror, yellow4, blue1, green0, mirror, yellow8};
      Card showing = new Card(Color.NONE, Rank.WILD_D4);*/
      
      
      /*Color call = Color.YELLOW;
      GameState state = new GameState();
      EagerPlayer player = new EagerPlayer();
      Card[] hand = {wild, blue6};
      Card showing = new Card(Color.YELLOW, Rank.REVERSE);*/
      
      
      
      /*Color call = Color.YELLOW;
      GameState state = new GameState();
      EagerPlayer player = new EagerPlayer();
      Card[] hand = {mirror, blue6, blue7, wild};
      Card showing = new Card(Color.NONE, Rank.CUSTOM);*/
   
      
      
      /*System.out.println(player.play(hand, showing, call, state));
      System.out.println("\tPLAY: " + player.play(hand, showing, call, state));
      Card played = hand[2];
      System.out.println("\tCAN PLAY: " + played.canPlayOn(showing, call));*/
      
   }
   
   
}