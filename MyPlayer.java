public class MyPlayer6 extends Player {

   public int play(Card[] hand, Card upCard, Color calledColor, GameState state) {
      int maxCard = -1; //this is returned
      int selectedCard = hand.length; //card at the moment
      //System.out.println("initial selectedCard: " + selectedCard);
   
      int max = -1;
      int maxTemp = -1;
      int maxCardIndex = -1; //place of one colored normal that could be possible to play
      //System.out.println("maxCardIndex: " + maxCardIndex);
      
      boolean canPlaySpecialColor = false;
      
      int redD2 = -1;
      int redDraw2 = -1;
      int redS = -1;
      int redSkip = -1;
      int redR = -1;
      int redReverse = -1;
      
      int yellowD2 = -1;
      int yellowDraw2 = -1;
      int yellowS = -1;
      int yellowSkip = -1;
      int yellowR = -1;
      int yellowReverse = -1;
      
      int greenD2 = -1;
      int greenDraw2 = -1;
      int greenS = -1;
      int greenSkip = -1;
      int greenR = -1;
      int greenReverse = -1;
      
      int blueD2 = -1;
      int blueDraw2 = -1;
      int blueS = -1;
      int blueSkip = -1;
      int blueR = -1;
      int blueReverse = -1;
      
      int check = -1; //this is to track if wild card exists in the hand
      int checkD4 = -1; //this is to track if draw four card exists in the hand
      int checkMirror = -1; //this is to track if mirror exsists
      
      int draw2 = -1;
      int skip = -1;
      int reverse = -1;
      int checkWild = -1;
      int checkMirrorFinal = -1;
      int minTemp = -1; //this is tracking wild and mirror card min position
      int checkDraw4 = -1;
      
      
      for (int i = 0; i < hand.length; i++) {
      //System.out.println("loop has been entered");
      //---------------------------------------------------------------------------------------------------------------------------------------normal cards check do forfeit cost highest
         if (hand[i].getColor() == upCard.getColor() && upCard.getColor() != Color.NONE) { //same color-----------
            maxTemp = hand[i].forfeitCost();
            //System.out.println("same color point value: " + maxTemp);
            
            if (maxCardIndex == -1) {
               maxCardIndex = i;
            }
            
            if (max < maxTemp) {
               max = maxTemp;
               maxCardIndex = i;
               //System.out.println("Updated maxCardIndex: " + maxCardIndex);
            }
            
         } else if (hand[i].getNumber() == upCard.getNumber() && hand[i].getNumber() != -1) { //same number
            maxTemp = hand[i].forfeitCost();
            //System.out.println("same number point value: " + maxTemp);
            
            if (maxCardIndex == -1) {
               maxCardIndex = i;
               //System.out.println("same number first change: " + maxCardIndex);
            }
            
            if (max < maxTemp) {
               max = maxTemp;
               maxCardIndex = i;
               //System.out.println("Updated maxCardIndex: " + maxCardIndex);
            }
            
         } else if (hand[i].getRank() == upCard.getRank() && hand[i].getNumber() == -1 && upCard.getColor() != Color.NONE) { //same rank
            maxTemp = hand[i].forfeitCost();
            //System.out.println("same number point value: " + maxTemp);
            
            if (maxCardIndex == -1) {
               maxCardIndex = i;
               //System.out.println("same number first change: " + maxCardIndex);
            }
            
            if (max < maxTemp) {
               max = maxTemp;
               maxCardIndex = i;
               //System.out.println("Updated maxCardIndex: " + maxCardIndex);
            }
            
         } else if (upCard.getColor() == Color.NONE && hand[i].getColor() == calledColor) { //wildcard was played last
            maxTemp = hand[i].forfeitCost();
            //System.out.println("same color on wild point value: " + maxTemp);
            
            if (maxCardIndex == -1) {
               maxCardIndex = i;
            }
            
            if (max < maxTemp) {
               max = maxTemp;
               maxCardIndex = i;
               //System.out.println("Updated maxCardIndex: " + maxCardIndex);
            }
            
         }
         
         //------------------------------------------------------------------------------------------------------------------------------------colored special cards
         if (hand[i].getColor() == Color.RED) {
            if (hand[i].getRank() == Rank.DRAW_TWO) {//red draw 2 check
               redD2 = i;
               //System.out.println("red draw 2 found: " + redD2);
                  
               if (redDraw2 < 0) {
                  redDraw2 = redD2;
                  //System.out.println("Updated redDraw2: " + redDraw2);
               }
            } else if (hand[i].getRank() == Rank.SKIP) {//red skip check
               redS = i;
               //System.out.println("red skip found: " + redS);
                  
               if (redSkip < 0) {
                  redSkip = redS;
                  //System.out.println("Updated redSkip: " + redSkip);
               }
            } else if (hand[i].getRank() == Rank.REVERSE) {//red reverse check
               redR = i;
               //System.out.println("red reverse found: " + redR);
                  
               if (redReverse < 0) {
                  redReverse = redR;
                  //System.out.println("Updated redReverse: " + redReverse);
               }
            }
         
         } else if (hand[i].getColor() == Color.YELLOW) {
            if (hand[i].getRank() == Rank.DRAW_TWO) {//yellow draw 2 check
               yellowD2 = i;
               //System.out.println("yellow draw 2 found: " + yellowD2);
                  
               if (yellowDraw2 < 0) {
                  yellowDraw2 = yellowD2;
                  //System.out.println("Updated yellowDraw2: " + yellowDraw2);
               }
            } else if (hand[i].getRank() == Rank.SKIP) {//yellow skip check
               yellowS = i;
               //System.out.println("yellow skip found: " + yellowS);
                  
               if (redSkip < 0) {
                  yellowSkip =yellowS;
                  //System.out.println("Updated yellowSkip: " + yellowSkip);
               }
            } else if (hand[i].getRank() == Rank.REVERSE) {//yellow reverse check
               yellowR = i;
               //System.out.println("yellow reverse found: " + yellowR);
                  
               if (yellowReverse < 0) {
                  yellowReverse = redR;
                  //System.out.println("Updated yellowReverse: " + yellowReverse);
               }
            }
         
         } else if (hand[i].getColor() == Color.GREEN) {
            if (hand[i].getRank() == Rank.DRAW_TWO) {//green draw 2 check
               greenD2 = i;
               //System.out.println("green draw 2 found: " + greenD2);
                  
               if (greenDraw2 < 0) {
                  greenDraw2 = greenD2;
                  //System.out.println("Updated greenDraw2: " + greenDraw2);
               }
            } else if (hand[i].getRank() == Rank.SKIP) {//green skip check
               greenS = i;
               //System.out.println("green skip found: " + greenS);
                  
               if (greenSkip < 0) {
                  greenSkip = greenS;
                  //System.out.println("Updated greenSkip: " + greenSkip);
               }
            } else if (hand[i].getRank() == Rank.REVERSE) {//green reverse check
               greenR = i;
               //System.out.println("green reverse found: " + greenR);
                  
               if (greenReverse < 0) {
                  greenReverse = greenR;
                  //System.out.println("Updated greenReverse: " + greenReverse);
               }
            }
         
         } else if (hand[i].getColor() == Color.BLUE) {
            if (hand[i].getRank() == Rank.DRAW_TWO) {//blue draw 2 check
               blueD2 = i;
               //System.out.println("blue draw 2 found: " + blueD2);
                  
               if (blueDraw2 < 0) {
                  blueDraw2 = blueD2;
                  //System.out.println("Updated blueDraw2: " + blueDraw2);
               }
            } else if (hand[i].getRank() == Rank.SKIP) {//blue skip check
               blueS = i;
               //System.out.println("blue skip found: " + blueS);
                  
               if (blueSkip < 0) {
                  blueSkip = blueS;
                  //System.out.println("Updated blueSkip: " + blueSkip);
               }
            } else if (hand[i].getRank() == Rank.REVERSE) {//blue reverse check
               blueR = i;
               //System.out.println("blue reverse found: " + blueR);
                  
               if (blueReverse < 0) {
                  blueReverse = blueR;
                  //System.out.println("Updated blueReverse: " + blueReverse);
               }
            }
         
         }
         
         //------------------------------------------------------------------------------------------------------------------------------------NONE color
         if (hand[i].getColor() == Color.NONE && hand[i].getRank() != Rank.WILD_D4 && hand[i].getRank() != Rank.CUSTOM) {//wild check
            check = i;
            //System.out.println("Wild found: " + check);
               
            if (checkWild < 0) {
               checkWild = check;
               //System.out.println("Updated checkWild: " + checkWild);
            }
               
         } else if (hand[i].getColor() == Color.NONE && hand[i].getRank() == Rank.WILD_D4) {//wild draw 4 check
            checkD4 = i;
            //System.out.println("Draw 4 found: " + checkD4);
               
            if (checkDraw4 < 0) {
               checkDraw4 = checkD4;
               //System.out.println("Updated checkDraw4: " + checkDraw4);
            }
               
         } else if (hand[i].getColor() == Color.NONE && hand[i].getRank() == Rank.CUSTOM) {//mirror check
            checkMirror = i;
            //System.out.println("mirror found: " + checkMirror);
               
            if (checkMirrorFinal < 0) {
               checkMirrorFinal = checkMirror;
               //System.out.println("Updated mirror: " + checkMirrorFinal);
            }
         }
         
         //------------------------------------------------------------------------------------------------------------------------------------canPlaySpecialColor flag
         if (upCard.getColor() == Color.RED) {
            if (redDraw2 != -1) {
               canPlaySpecialColor = true;
               draw2 = redDraw2;
            } else if (redSkip != -1) {
               canPlaySpecialColor = true;
               skip = redSkip;
            } else if (redReverse != -1) {
               canPlaySpecialColor = true;
               reverse = redReverse;
            }
            
         } else if (upCard.getColor() == Color.YELLOW) {
            if (yellowDraw2 != -1) {
               canPlaySpecialColor = true;
               draw2 = yellowDraw2;
            } else if (yellowSkip != -1) {
               canPlaySpecialColor = true;
               skip = yellowSkip;
            } else if (yellowReverse != -1) {
               canPlaySpecialColor = true;
               reverse = yellowReverse;
            }
            
         } else if (upCard.getColor() == Color.GREEN) {
            if (greenDraw2 != -1) {
               canPlaySpecialColor = true;
               draw2 = greenDraw2;
            } else if (greenSkip != -1) {
               canPlaySpecialColor = true;
               skip = greenSkip;
            } else if (greenReverse != -1) {
               canPlaySpecialColor = true;
               reverse = greenReverse;
            }
            
         } else if (upCard.getColor() == Color.BLUE) {
            if (blueDraw2 != -1) {
               canPlaySpecialColor = true;
               draw2 = blueDraw2;
            } else if (blueSkip != -1) {
               canPlaySpecialColor = true;
               skip = blueSkip;
            } else if (blueReverse != -1) {
               canPlaySpecialColor = true;
               reverse = blueReverse;
            }
         }
         
         //------------------------------------------------------------------------------------------------------------------------------------play order
         
         if (7 < selectedCard) {
            //System.out.println("less than 7 cards in hand");
            if (checkMirrorFinal != -1) {//normal cards first
               maxCard = checkMirrorFinal;
            } else if (checkWild != -1) {//normal wilds
               maxCard = checkWild;
            } else if (maxCardIndex != -1) {//mirror
               maxCard = maxCardIndex;
            } else if (checkDraw4 != -1) {//draw 4
               maxCard = checkDraw4;
            }
         
         } else {
            if (maxCardIndex != -1) {//normal cards first
               maxCard = maxCardIndex;
            } else if (checkWild != -1) {//normal wilds
               maxCard = checkWild;
            } else if (checkMirrorFinal != -1) {//mirror
               maxCard = checkMirrorFinal;
            } else if (checkDraw4 != -1) {//draw 4
               maxCard = checkDraw4;
            }
         }
      
      }
      
      //System.out.println("maxCard is: " + maxCard);
      return maxCard;
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
      Card green2 = new Card(Color.GREEN, Rank.NUMBER, 2);
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
      
      
      
      /*Color call = Color.YELLOW;
      GameState state = new GameState();
      MyPlayer player = new MyPlayer();
      Card[] hand = {yellow2, yellow8, blue1, green2, green7};
      Card showing = new Card(Color.YELLOW, Rank.NUMBER, 1);*/
      
      
      
      Color call = Color.YELLOW; //not playing first playable normal card, somehow overridding it
      GameState state = new GameState();
      MyPlayer player = new MyPlayer();
      Card[] hand = {green1, blue1, red1, yellowSkip, yellowDraw2};
      Card showing = new Card(Color.YELLOW, Rank.NUMBER, 1);
      
      
      
      /*Color call = Color.YELLOW;
      GameState state = new GameState();
      MyPlayer player = new MyPlayer();
      Card[] hand = {green1, blue1, red1, yellowSkip, yellowDraw2};
      Card showing = new Card(Color.YELLOW, Rank.NUMBER, 3);*/
      
      
      
      /*Color call = Color.YELLOW;
      GameState state = new GameState();
      MyPlayer player = new MyPlayer();
      Card[] hand = {green1, blue1, red1, yellowSkip, yellowReverse};
      Card showing = new Card(Color.YELLOW, Rank.NUMBER, 3);*/
      
      
      
      /*Color call = Color.YELLOW;
      GameState state = new GameState();
      MyPlayer player = new MyPlayer();
      Card[] hand = {green1, blue1, red1, wild, mirror, wildDraw4};
      Card showing = new Card(Color.YELLOW, Rank.NUMBER, 3);*/
      
      
      
      /*Color call = Color.YELLOW;
      GameState state = new GameState();
      MyPlayer player = new MyPlayer();
      Card[] hand = {green1, blue1, red1, mirror, wildDraw4};
      Card showing = new Card(Color.YELLOW, Rank.NUMBER, 3);*/
      
      
      
      System.out.println(player.play(hand, showing, call, state));
      System.out.println("\tPLAY: " + player.play(hand, showing, call, state));
      Card played = hand[2];
      System.out.println("\tCAN PLAY: " + played.canPlayOn(showing, call));
      
      
   }
   
   
}