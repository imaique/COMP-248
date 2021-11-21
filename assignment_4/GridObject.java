// This GridObject class is the equivalent of 1 cell in the 2D grid that the game will be played int.
public class GridObject {
    // Creating a class "Type" that will improve readability and will indicate what type of object the cell holds
    public static class Type {
        public static final int NOTHING = 0;
        public static final int BOAT = 1;
        public static final int GRENADE = 2;
    }

        // Creating a class "Owner" that will improve readability and will indicate who the owner of the cell is
    public static class Owner {
        public static final int PLAYER = 0;
        public static final int COMPUTER= 1;
        public static final int UNOWNED= 2;
    }
    // Declaring three variables, a boolean called that is true if the cell has been called by either player,
    // an integer type that indicates what type of object is in the cell (grenade or boat or nothing) using the Type
    // class and an integer called owner that works the same as type but indicates who the owner of the cell is.
    private boolean called;
    private int type;
    private int owner;

    // Default constructor that initialize the cell to default empty, unowned cells.
    public GridObject() {
        called = false;
        owner = Owner.UNOWNED;
        type = Type.NOTHING;
    }

    // Sets the type of object in the cell and the owner
    public void setObject(int own, int type) {
        this.owner = own;
        this.type = type;
    }

    // Sets the cell that was called to the boolean parameter
    public void setCalled(boolean called) {
        this.called = called;
    }

    // Returns the owner of the cell
    public int getOwner() {
        return owner;
    }

    // Returns whether the cell has been called or not
    public boolean getCalled() {
        return called;
    }

    // Returns the type of the cell
    public int getType() {
        return type;
    }

    // The toString method for this class, converts the cells to type String.
    public String toString(){
        // If the cell was not called, then return _ that indicates an uncalled cell
        if (!called)
            return "_";
        // If the cell has been called and has nothing in it.
        if (type==Type.NOTHING)
            return "*";
        // Initializing string variables that indicate wether the position has a Ship or a Grenade
        String ship = "S";
        String grenade = "G";
        // If the position is owned by the player, then the S and G needs to be lowercase
        if (this.owner==Owner.PLAYER){
            ship = ship.toLowerCase();
            grenade = grenade.toLowerCase();
        }
        // If its a boat return S and if not return grenade
        if (type==Type.BOAT)
            return ship;
        if (type==Type.GRENADE)
            return grenade;
        // This makes the IDE happy
        return "Error";
    }
    // Get the message that will be displayed if the position gets hit.
    public String getConsequence(){
        switch (this.type){
            case Type.NOTHING:
                return "nothing.";
            case Type.BOAT:
                return "ship hit!";
            case Type.GRENADE:
                return "boom! grenade.";
            default:
                return "error";
        }
    }
}
