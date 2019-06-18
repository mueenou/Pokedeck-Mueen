package upmc.pokedeckCardGame.game;

import java.util.ArrayList;
import java.util.HashMap;
import static upmc.pcg.game.Card.CARD_TYPES;
import upmc.pokedeckCardGame.ui.CardMenuUI;
import upmc.pokedeckCardGame.ui.MenuUI;

/**
 * EnergyCard is a representation of pokemon energy card
 */
public class EnergyCard extends Card {
    public static String ENERGY_TYPES[] = {
        "Grass",
        "Fire",
        "Water",
        "Lightning",
        "Psychic",
        "Fighting",
        "Darkness",
        "Metal",
        "Fairy",
        "Dragon",
        "Colorless"
    };

    @Override
    public void create() {
        MenuUI.print_create_card_msg("energy");
        fill_card();
    }
    
    /**
     * Fill the card with informations given by the user
     */
    private void fill_card() {
        HashMap<String, Object> valuesForAttributes = new HashMap<>();
        valuesForAttributes = CardMenuUI.ask_energyCard_attributes();
        
        this.cardType = CARD_TYPES[2]; //energy
        this.name = (String)valuesForAttributes.get("name");
        this.specialType = (String)valuesForAttributes.get("specialType");
        this.cardNb = (int)valuesForAttributes.get("cardNb");
    }
    
    /**
     * Display all the informations of the card for the user and return them in the form of a string
     */
    @Override
    public String toString() {
        String cardInformation = "";
        
        cardInformation += "---------------------------\n| Energy Card Information :\n---------------------------\n";
        cardInformation += "| Name : "+this.name+"\n";
        cardInformation += "| Energy type : "+this.specialType+"\n";
        cardInformation += "| Collection card number : "+this.cardNb+"\n";
        cardInformation += "---------------------------\n";
        
        return cardInformation;
    }
}
