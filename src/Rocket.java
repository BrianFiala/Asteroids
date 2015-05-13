import java.awt.*;

class Rocket {
   private double x, y; // position information
   private double dx, dy; // movement per iteration
   
   public Rocket (double ix, double iy, double idx, double idy) {
      x=ix;
      y=iy;
      dx=idx;
      dy=idy;
   } // end Rocket constructor
   
   public void move() {
      x+=dx;
      y+=dy;
   } // end move
   
   public boolean hitAsteroid (AsteroidGameList<Asteroid> asteroids) {
      boolean hit = false;
      
      // start with the beginning of the linked list asteroids
      ListNode<Asteroid> asteroid = asteroids.peekNode(1);
      int index = 1;
         
      while (asteroid != null) {
         if (asteroid.getData().nearTo(x+2, y+2)) {
            asteroid.getData().hit(); // rocket hit asteroid
            hit = true;
         } // end if
         
         // remove the asteroid from the list if it is destroyed
         if (asteroid.getData().size < 4)
            asteroids.remove(index);
         
         asteroid = asteroid.getNext(); // get next asteroid
         index++;
      } // end while
      return hit;
   } // end hitAsteroid
   
   public void paint (Graphics g) {
      g.setColor(Color.black);
      g.drawOval((int) x, (int) y, 5, 5);
      g.setColor(Color.red);
      g.fillOval((int) x, (int) y, 5, 5);
   } // end paint
   
   public double getXCoord() {
      return x;
   } // end getXCoord
   
   public double getYCoord() {
      return y;
   } // end getYCoord
   
} // end class Rocket