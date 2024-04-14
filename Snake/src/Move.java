public class Move {
    private Position origin;
    private Position destination;
    public Move (Position origin, Position destination){
        this.origin = new Position(origin);
        this.destination = new Position(destination);
    }
     public Move(Move that){
        this.origin = new Position(that.origin);
        this.destination = new Position(that.destination);
     }

     public Position getOrigin(){
        return new Position(this.origin);
     }

     public Position getDestination(){
        return new Position(this.destination);
     }

}


