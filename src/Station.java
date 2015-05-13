import java.awt.*;

class Station {
   private double angle = Math.PI / 2.0; // public static final double PI 3.141592653589793d 
   private int hits = 0;
   private final double x;
   private final double y;
   
   public Station (double ix, double iy) {
      x = ix;
      y = iy;
   } // end Station constructor
   
   public void moveLeft() {
      angle = angle + 0.1;
   } // end moveLeft
   
   public void moveRight() {
      angle = angle - 0.1;
   } // end moveRight
   
   public Rocket fire () {
      double cosAngle = Math.cos(angle);
      double sinAngle = Math.sin(angle);
      // rocket goes same direction as gun is pointing
      Rocket r = new Rocket(x + 15 * cosAngle, y - 15 * sinAngle,
                            5 * cosAngle, -5 * sinAngle);
      return r;
   } // end fire
   
   public void checkHit (Asteroid rock) {
      if (rock.nearTo((double) x, (double) y) && !rock.hitStation) {
         hits += rock.size;
         rock.hitStation = true;
      } // end if
   } // end checkHit
   
   public int getHits() {
      return hits;
   }
   
   public void paint (Graphics g) {
      // draw hits count
      g.setColor(Color.black);
      g.drawString("hits: " + hits, (int) (x - 15), (int) (y + 15));
      
      g.setColor (Color.blue);
      double lv = 20 * Math.sin(angle);
      double lh = 20 * Math.cos(angle);
      
      // draw the base line
      g.drawLine((int) (x - 5), (int) y, (int) (x + 10), (int) y);
      
      // draw the left of the canon line
      g.drawLine((int) x, (int) y, (int) (x + lh), (int) (y - lv));
      
      // draw the right of the canon line
      g.drawLine((int) (x + 5), (int) y, (int) (x + lh + 5), (int) (y - lv));
   } // end paint
   
} // end class Station

