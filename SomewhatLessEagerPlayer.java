/**
 * A play that is less eager
 *
 * @author Makenna Worley
 */
public class SomewhatLessEagerPlayer extends EagerPlayer {

   public int play(Card[] hand, Card upCard, Color calledColor, GameState state) {
      int minCard = -1;
      int min = super.play(hand, upCard, calledColor, state);
      minCardIndex = -1; //place of one colored card that could be possible to play
      //System.out.println("minCardIndex: " + minCardIndex);
      int selectedCard = hand.length; //card at the moment
      //System.out.println("initial selectedCard: " + selectedCard);
      int check = -1; //this is to track if wild card exists in the hand
      int checkWild = -1;
      int checkD4 = -1; //this is to track if draw four card exists in the hand
      int checkDraw4 = -1;
      int checkMirror = -1; //this is to track if mirror exsists
      int checkMirrorFinal = -1;
      int minTemp = -1; //this is tracking wild and mirror card min position
      
      
      for (int i = 0; i < hand.length; i++) {
      
         /*if (upCard.getColor() == Color.NONE && hand[i].getColor() == calledColor) { //wild played
            selectedCard = i;
            System.out.println("same color index with wild: " + selectedCard);
               
            if (minCardIndex < 0) {
               minCardIndex = selectedCard;
            }
            
         }*/
         
         if (hand[i].getColor() == upCard.getColor() && upCard.getColor() != Color.NONE) { //same color
            selectedCard = i;
            //System.out.println("same color index: " + selectedCard);
               
            if (minCardIndex < 0) {
               minCardIndex = selectedCard;
               //System.out.println("Updated minCardIndex: " + minCardIndex);
            }
            
         } else if (hand[i].getNumber() == upCard.getNumber() && hand[i].getNumber() != -1) { //same number
            selectedCard = i;
            //System.out.println("same number index: " + selectedCard);
            
            if (minCardIndex < 0) {
               minCardIndex = selectedCard;
               //System.out.println("Updated minCardIndex: " + minCardIndex);
            }
            
         } else if (hand[i].getNumber() == -1 && hand[i].getRank() == upCard.getRank() && hand[i].getRank() != Rank.WILD_D4 && hand[i].getRank() != Rank.CUSTOM) { //special card
            selectedCard = i;
            //System.out.println("same rank, not number index: " + selectedCard);
            
            if (minCardIndex < 0) {
               minCardIndex = selectedCard;
               //System.out.println("Updated minCardIndex: " + minCardIndex);
            }
         
         } else if (upCard.getColor() == Color.NONE && hand[i].getColor() == calledColor) { //same color
            selectedCard = i;
            //System.out.println("same color index: " + selectedCard);
               
            if (minCardIndex < 0) {
               minCardIndex = selectedCard;
               //System.out.println("Updated minCardIndex: " + minCardIndex);
            }
            
         }
         
         //System.out.println("normal card index: " + minCardIndex);
         
         if (hand[i].getColor() == Color.NONE && hand[i].getRank() != Rank.WILD_D4 && hand[i].getRank() != Rank.CUSTOM) {
            check = i;
            //System.out.println("Wild found: " + check);
            
            if (checkWild < 0) {
               checkWild = check;
               //System.out.println("Updated checkWild: " + checkWild);
            }
            
         } else if (hand[i].getColor() == Color.NONE && hand[i].getRank() == Rank.WILD_D4) {
            checkD4 = i;
            //System.out.println("Draw 4 found: " + checkD4);
            
            if (checkDraw4 < 0) {
               checkDraw4 = checkD4;
               //System.out.println("Updated checkDraw4: " + checkDraw4);
            }
            
         } else if (hand[i].getColor() == Color.NONE && hand[i].getRank() == Rank.CUSTOM) {
            checkMirror = i;
            //System.out.println("mirror found: " + checkMirror);
            
            if (checkMirrorFinal < 0) {
               checkMirrorFinal = checkMirror;
               //System.out.println("Updated mirror: " + checkMirrorFinal);
            }
         }
      
      }
      //System.out.println("minCard is: " + minCard);
      
      if (minCardIndex != -1) {
         min = minCardIndex;
      } else if (minCardIndex == -1 && checkWild != -1) {
         min = checkWild;
      } else if (minCardIndex == -1 && checkWild == -1 && checkMirrorFinal != -1) {
         min = checkMirrorFinal;
      } else if (minCardIndex == -1 && checkWild == -1 && checkMirrorFinal == -1 && checkDraw4 != -1) {
         min = checkDraw4;
      }
      
      hasPlayRun = true;
      return min;
   }

   public Color callColor(Card[] hand) {
      int red = 0;
      int yellow = 0;
      int green = 0;
      int blue = 0;
      int maxTemp1 = 0;
      int maxTemp2 = 0;
      int maxTemp = 0;
      
      for (int i = 0; i < hand.length; i++) {
         if (hand[i].getColor() == Color.RED) {
            red = red + hand[i].forfeitCost();
         } else if (hand[i].getColor() == Color.YELLOW) {
            yellow = yellow + hand[i].forfeitCost();
         } else if (hand[i].getColor() == Color.GREEN) {
            green = green + hand[i].forfeitCost();
         } else if (hand[i].getColor() == Color.BLUE) {
            blue = blue + hand[i].forfeitCost();
         } else {
         
         }
      }
      
      maxTemp1 = Math.max(red, yellow);
      maxTemp2 = Math.max(green, blue);
      maxTemp = Math.max(maxTemp1, maxTemp2);
      
      if (maxTemp == red) {
         return Color.RED;
      } else if (maxTemp == yellow) {
         return Color.YELLOW;
      } else if (maxTemp == green) {
         return Color.GREEN;
      } else if (maxTemp == blue) {
         return Color.BLUE;
      } else {
         return null;
      }
      
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
      Card yellowDraw2 = new Card(Color.YELLOW, Rank.DRAW_TWO);
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
      
      
      
      /*Color call = Color.BLUE;
      GameState state = new GameState();
      SomewhatLessEagerPlayer player = new SomewhatLessEagerPlayer();
      Card[] hand = {mirror, greenDraw2, green6, wildDraw4, wild, wildDraw4, blue3, mirror};
      Card showing = new Card(Color.NONE, Rank.CUSTOM);*/
      
      
      
      /*Color call = Color.YELLOW;
      GameState state = new GameState();
      SomewhatLessEagerPlayer player = new SomewhatLessEagerPlayer();
      Card[] hand = {blue2, green8, blue8, mirror, blue1, blue3, blue7, wild};
      Card showing = new Card(Color.NONE, Rank.WILD_D4);*/
      
      
      
      /*Color call = Color.BLUE;
      GameState state = new GameState();
      SomewhatLessEagerPlayer player = new SomewhatLessEagerPlayer();
      Card[] hand = {green1, wildDraw4, green0, yellow3, greenDraw2, yellowReverse};
      Card showing = new Card(Color.NONE, Rank.CUSTOM);*/
      
      
      
      /*Color call = Color.YELLOW;
      GameState state = new GameState();
      SomewhatLessEagerPlayer player = new SomewhatLessEagerPlayer();
      Card[] hand = {mirror, blue6, blue7, wild};
      Card showing = new Card(Color.NONE, Rank.CUSTOM);*/
      
      
      
      /*Color call = Color.RED;
      GameState state = new GameState();
      SomewhatLessEagerPlayer player = new SomewhatLessEagerPlayer();
      Card[] hand = {yellowReverse, green8, yellow4, green5, blueReverse, blue6, red8, blueDraw2, mirror};
      Card showing = new Card(Color.BLUE, Rank.NUMBER, 9);*/
      
      
      
      /*Color call = Color.BLUE;
      GameState state = new GameState();
      SomewhatLessEagerPlayer player = new SomewhatLessEagerPlayer();
      Card[] hand = {mirror, greenDraw2, green6, wildDraw4, wild, wildDraw4, blue3, mirror};
      Card showing = new Card(Color.NONE, Rank.CUSTOM);*/
      
      
      
      /*Color call = Color.BLUE;
      GameState state = new GameState();
      SomewhatLessEagerPlayer player = new SomewhatLessEagerPlayer();
      Card[] hand = {mirror};
      Card showing = new Card(Color.BLUE, Rank.NUMBER, 5);*/
      
      
      
      Color call = Color.BLUE;
      GameState state = new GameState();
      SomewhatLessEagerPlayer player = new SomewhatLessEagerPlayer();
      Card[] hand = {green8, wildDraw4, yellow1, mirror, green6, blue1, green7, red1, yellow8};
      Card showing = new Card(Color.RED, Rank.REVERSE);
      
      
      
      System.out.println(player.play(hand, showing, call, state));
      System.out.println("\tPLAY: " + player.play(hand, showing, call, state));
      /*Card played = hand[2];
      System.out.println("\tCAN PLAY: " + played.canPlayOn(showing, call));*/
      
      
   }
   
   
}