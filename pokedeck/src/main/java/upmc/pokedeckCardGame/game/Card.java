package upmc.pokedeckCardGame.game;

/**
 * Card is an abstract representation of all types of pokemon cards
 */
public class Card {
    protected String name = "Default";
    protected String cardType;
    protected String specialType; //Pokemon type, Trainer type or Energy type
    protected int cardNb;
    public static String CARD_TYPES[] = {"pokemon", "trainer", "energy"};
    
    /**
     * Return the name of the card in the form of a string
     */
    public String get_name() {
        return this.name;
    }
    
    /**
     * Return the cardType in the form of a string
     */
    public String get_cardType() {
        return this.cardType;
    }
    
    /**
     * Return the cardNb
     */
    public int get_cardNb() {
        return this.cardNb;
    }
    
    /**
     * Create a card (pokemon, trainer or energy) with every attributes
     */
    public void create() {
        
    }
    
    /**
     * Display all the informations of the card for the user and return them in the form of a string
     */
    public String toString() {
        return "["+this.cardType+"]"+this.name;
    }
}
