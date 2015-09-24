package Map;
import Characters.*;

public class Tile {

   Terrain type;
   Mule mule;
   boolean isOwned;
   boolean hasMule;
   double x;
   double y;
   Player owner;

   public Tile(Terrain type, double x, double y) {
       this.type = type;
       this.isOwned = false;
       this.hasMule = false;
       this.x = x;
       this.y = y;
       this.owner = null;
   }

   public double getX() {
        return x;
   }

   public double getY() {
        return y;
   }

   public boolean isOwned() {
       return isOwned;
   }

   public boolean hasMule() {
       return hasMule;
   }

   public void setMule(Mule m) {
       mule = m;
       hasMule = true;
   }

   public void removeMule() {
       mule = null;
       hasMule = false;
   }

   public void setOwner(Player p) {
       owner = p;
   }

   public Player getOwner() {
       return owner;
   }
}