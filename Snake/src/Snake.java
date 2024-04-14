    public class Snake {
    private Position[] body;
    private int speed;
    private String direction;
    private boolean isAlive;

    public Snake() {
        this.isAlive = true;
        this.direction = "d";
        this.body = new Position[1];
        this.body[0] = new Position(7, 3);
    }

    public Position getHead() {
        return new Position(this.body[0].getRow(), this.body[0].getColumn());
    }

    public String getDirection() {
        return this.direction;
    }

    public boolean getIsAlive() {
        return this.isAlive;
    }

    public Snake(Snake that) {
        this.isAlive = that.isAlive;
        this.speed = that.speed;
        this.direction = that.direction;
        this.body = new Position[that.body.length];
        for (int i = 0; i < that.body.length; i++) {
            this.body[i] = new Position(that.body[i].getRow(), that.body[i].getColumn());
        }
    }

    public Position getReachablePosition(String direction) {
        String d = direction.toLowerCase();
        int row;
        int column;
        switch (d) {
            case "w":
                row = this.getHead().getRow() - 1;
                column = this.getHead().getColumn();
                break;
            case "s":
                row = this.getHead().getRow() + 1;
                column = this.getHead().getColumn();
                break;
            case "a":
                row = this.getHead().getRow();
                column = this.getHead().getColumn() - 1;
                break;
            case "d":
                row = this.getHead().getRow();
                column = this.getHead().getColumn() + 1;
                break;
            default:
                row = this.getHead().getRow();
                column = this.getHead().getColumn();
                break;
        }
        return new Position(row, column);
    }

    public Move generateFromPositionAndDirection(String direction) {
        Position newDestination = this.getReachablePosition(direction);
        return new Move(this.getHead(), newDestination);
    }

    public void moveHead(Position newPosition) {
        this.body[0] = newPosition;
    }

    public void grow() {
        Position lastSegment = this.body[body.length - 1];
        Position newSegment;
        if (this.body.length > 1) {
            Position secondLastSegment = this.body[body.length - 2];
            int rowOffset = lastSegment.getRow() - secondLastSegment.getRow();
            int columnOffset = lastSegment.getColumn() - secondLastSegment.getColumn();
            newSegment = new Position(lastSegment.getRow() + rowOffset,
                    lastSegment.getColumn() + columnOffset);
        } else {
            switch (this.direction){
                case "w":
                    newSegment = new Position(lastSegment.getRow() + 1, lastSegment.getColumn() );
                    break;
                case "s":
                    newSegment = new Position(lastSegment.getRow() - 1, lastSegment.getColumn());
                    break;
                case "a":
                    newSegment = new Position(lastSegment.getRow(), lastSegment.getColumn() + 1);
                    break;
                case "d":
                    newSegment = new Position(lastSegment.getRow(), lastSegment.getColumn() - 1);
                    break;
                default:
                    return;
            }
        }
        Position[] newBody = new Position[this.body.length + 1];
        for (int i = 0; i < this.body.length; i++){
            newBody[i] = new Position(this.body[i]);
        }
        newBody[newBody.length - 1] = newSegment;
        this.body = newBody;
    }

}
