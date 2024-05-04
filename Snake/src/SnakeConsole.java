import java.util.ArrayList;

public class Snake {
    private ArrayList<Position> body;
    private int speed;
    private String direction;
    private boolean isAlive;

    public Snake() {
        this.isAlive = true;
        this.direction = "d";
        this.body = new ArrayList<>();
        this.body.add( new Position(7, 3));
    }
    public Position getHead() {
        return new Position(this.body.getFirst().getRow(), this.body.getFirst().getColumn());
    }

    public String getDirection() {
        return this.direction;
    }
    public boolean getIsAlive() {
        return this.isAlive;
    }

    public ArrayList<Position> getBody() {
        return this.body;
    }

    public Snake(Snake that) {
        this.isAlive = that.isAlive;
        this.speed = that.speed;
        this.direction = that.direction;
        this.body = new ArrayList<>(that.body.size());
        for (Position pos : that.body) {
            this.body.add(new Position(pos.getRow(), pos.getColumn()));
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
        this.body.set(0, newPosition);
    }

    public void grow() {
        Position tail = this.body.getLast();
        Position newSegment = null;
        if (this.body.size() > 1) {
            Position secondLastSegment = this.body.get(body.size() - 2);
            int rowOffset = tail.getRow() - secondLastSegment.getRow();
            int columnOffset = tail.getColumn() - secondLastSegment.getColumn();
            newSegment = new Position(tail.getRow() + rowOffset,
                    tail.getColumn() + columnOffset);
        } else {
            switch (this.direction) {
                case "w":
                    newSegment = new Position(tail.getRow() + 1, tail.getColumn());
                    break;
                case "s":
                    newSegment = new Position(tail.getRow() - 1, tail.getColumn());
                    break;
                case "a":
                    newSegment = new Position(tail.getRow(), tail.getColumn() + 1);
                    break;
                case "d":
                    newSegment = new Position(tail.getRow(), tail.getColumn() - 1);
                    break;
//                default:
//                    return;
            }
        }
        this.body.add(newSegment);
        this.body.trimToSize();
    }
    public void shrink() throws OutOfBoundMoveException {
        if (this.body.size() > 1) {
            this.body.removeLast();
        } else {
            this.isAlive = false;
            throw new OutOfBoundMoveException("Snake got poisoned(");
        }
    }
    public void setDirection(String direction) {
        this.direction = direction;
    }

}
