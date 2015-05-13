/* File       : AsteroidGame.txt
 * Author     : Brian Fiala
 * Date       : 10/12/14
 * Attribution: some code used with permission from Professor Mousallam,
 *              CS1C Data Structures and Algorithms, Foothill Community College
 * Description: AsteroidGame is a java game wherein the player controls a canon
 *              to fire at incoming asteroids. 
 * Controls   : 'j' move canon left
 *              'k' move canon right
 *              ' ' fire canon
 *              'q' quit game
 * Note       : Game ends when the number of hits on the station exceeds 400,
 *              or after one hour, whichever comes first
 */

import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class AsteroidGame extends Frame {
   
   private int frameWidth = 500;
   private int frameHeight = 400;
   private AsteroidGameList<Asteroid> asteroids = 
      new AsteroidGameList<Asteroid>();
   private AsteroidGameList<Rocket> rockets = 
      new AsteroidGameList<Rocket>();
   private Station station = new Station (frameWidth/2, frameHeight-20);
   
   public static void main(String[] args) {
      AsteroidGame world = new AsteroidGame();
      world.setSize(world.frameWidth, world.frameHeight);
      world.setResizable(false);
      world.setVisible(true);
      world.addKeyListener(world.new KeyDown());
      GameMover gameThread = world.new GameMover();
      gameThread.run();
   } // end main
   
   public void paint (Graphics g) {
      // draw ground line
      g.setColor(Color.black);
      g.drawLine(0, frameHeight-18, frameWidth, frameHeight-18);
      
      station.paint(g);
      
      for (int index = 1; index <= asteroids.size(); index++)
         asteroids.peek(index).paint(g);
      
      for (int index = 1; index <= rockets.size(); index++)
         rockets.peek(index).paint(g);
   } // end paint
   
   private void createNewAsteroids() {
      // create a random new asteroid – 30% of the time
      if (Math.random() < 0.3) {
         Asteroid newRock = new Asteroid(
            frameWidth * Math.random(), 20,
            10 * Math.random() - 5, 3 + 3 * Math.random());
         asteroids.add(newRock); // add new asteroid to asteroids list
      } // end if
   } // end createNewAsteroids
   
   private void moveAsteroids() {
      // start with the beginning of the linked list asteroids
      ListNode<Asteroid> asteroid = asteroids.peekNode(1);
      
      while (asteroid != null) {
         asteroid.getData().move(); // move asteroid
         asteroid = asteroid.getNext(); // get next asteroid
      } // end while
   } // end moveAsteroids
   
   private void moveRockets() {
      // start with the beginning of the linked list asteroids
      ListNode<Rocket> rocket = rockets.peekNode(1);
      
      while (rocket != null) {
         rocket.getData().move(); // move rocket
         rocket = rocket.getNext(); // get next rocket
      } // end while
   } // end moveRockets
   
   private void removeOffScreenAsteroids() {
      // start with the beginning of the linked list asteroids
      ListNode<Asteroid> asteroid = asteroids.peekNode(1);
      int index = 1;
      
      while (asteroid != null) {
         // fix for x,y of asteroid not at center
         // remove the asteroid if it has moved out of the window
         if ( (asteroid.getData().getXCoord() + asteroid.getData().size < 0)
            || (asteroid.getData().getXCoord() > frameWidth)
            || (asteroid.getData().getYCoord() + asteroid.getData().size > frameHeight - 10) )
         {
            asteroids.remove(index);
            asteroid = asteroids.peekNode(index); // get next asteroid
         } // end if
         else {
            asteroid = asteroid.getNext();
            index++;
         }
      } // end while
   } // end removeOffScreenAsteroids
   
   private void removeOffScreenRockets() {
      // start with the beginning of the linked list rockets
      ListNode<Rocket> rocket = rockets.peekNode(1);
      int index = 1;
      
      while (rocket != null) {
         // remove the rocket if it has moved out of the window
         if ((rocket.getData().getXCoord() + 2 < 0)
            || ((rocket.getData().getXCoord() - 2) > frameWidth)
            || ((rocket.getData().getYCoord() - 2) > frameHeight))
         {
            rockets.remove(index);
         } // end if
         rocket = rocket.getNext(); // get next rocket
         index++;
      } // end while
   } // end removeOffScreenRockets
   
   private void rocketHitAsteroid() {
      // start with the beginning of the linked list rockets
      ListNode<Rocket> rocket = rockets.peekNode(1);
      int index = 1;
      
      while (rocket != null) {
         // remove the rocket if it has hit an asteroid
         if (rocket.getData().hitAsteroid(asteroids))
            rockets.remove(index);
         rocket = rocket.getNext(); // get next rocket
         index++;
      } // end while
   } // end rocketHitAsteroid
   
   private void asteroidHitStation() {
      // start with the beginning of the linked list asteroids
      ListNode<Asteroid> asteroid = asteroids.peekNode(1);
      int index = 1;
      
      while (asteroid != null) {
         station.checkHit(asteroid.getData());
         if (asteroid.getData().hitStation)
            asteroids.remove(index); // remove an asteroid if it hit station
         asteroid = asteroid.getNext(); // get next asteroid
         index++;
      } // end while
   } // end asteroidHitStation
   
   private class KeyDown extends KeyAdapter {
      public void keyPressed(KeyEvent e) {
         char key = e.getKeyChar();
         switch (key) {
            case 'j':
               station.moveLeft();
               break;
            case 'k': 
               station.moveRight(); 
               break;
            case ' ': 
               rockets.add(station.fire());
               break;
            case 'q': 
               System.exit(0);
         } // end switch
      } // end keyPressed
   } // end inner class KeyDown
   
   private class TimesUp extends TimerTask {
      public void run() {
         System.exit(0);
      } // end run
   } // end inner class TimesUp
   
   private class GameMover extends Thread {
      public void run() {
         Timer timeOut = new Timer();
         TimesUp quittingTime = new TimesUp();
         timeOut.schedule(quittingTime, 3600000); // quit after 1 hour
         while (true) {
            if (station.getHits() > 400) // quit if hits exceed 400
               System.exit(0);
            createNewAsteroids();
            moveAsteroids();
            removeOffScreenAsteroids();
            moveRockets();
            removeOffScreenRockets();
            rocketHitAsteroid();
            asteroidHitStation();
            repaint(); 
            try  {
               sleep(100);
            } // end try
            catch (Exception e) { }
         } // end while
      } // end run
   } // end inner class GameMover
   
} // end class AsteroidGame

