import java.awt.*;

class Asteroid {
   private double x, y; // position information
   public int size = 20; // maybe make private for best practice
   private double dx, dy; // movement per iteration
   public boolean hitStation; // maybe make private for best practice
   
   public Asteroid (double ix, double iy, double idx, double idy) {
      x=ix;
      y=iy;
      dx=idx;
      dy=idy;
   } // end constructor
   
   public void move() {
      x+=dx;
      y+=dy;
   } // end move
   
   public void paint (Graphics g) {
      g.setColor(Color.black);
      g.drawOval((int) x, (int) y, size, size);
      g.setColor(Color.lightGray);
      g.fillOval((int) x, (int) y, size, size);
   } // end paint
   
   public void hit () {
      size = size - 4;
   } // end hit
   
   public boolean nearTo (double tx, double ty) {
      // use Pythagorean theorem to determine distance between points
      double distance = Math.sqrt( (x+(size/2)-tx)*(x+(size/2)-tx)
         + (y+(size/2)-ty)*(y+(size/2)-ty) );
      return distance < (size / 2);
   } // end nearTo
   
   public double getXCoord() {
      return x;
   } // end getXCoord
   
   public double getYCoord() {
      return y;
   } // end getYCoord
   
} // end class Asteroid