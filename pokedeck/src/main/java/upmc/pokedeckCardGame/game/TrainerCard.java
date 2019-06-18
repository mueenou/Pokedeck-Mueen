package upmc.pokedeckCardGame.game;

import java.util.HashMap;
import static upmc.pcg.game.Card.CARD_TYPES;
import upmc.pokedeckCardGame.ui.CardMenuUI;
import upmc.pokedeckCardGame.ui.MenuUI;

public class TrainerCard extends Card {
    private String explanation;
    private String trainerRule;
    public static String TRAINER_TYPES[] = {"item", "supporter", "stadium"}; 

    @Override
    public void create() {
        MenuUI.print_create_card_msg("trainer");
        fill_card();
    }
    
    /**
     * Fill the card with informations given by the user
     */
    private void fill_card() {
        HashMap<String, Object> valuesForAttributes = new HashMap<>();
        valuesForAttributes = CardMenuUI.ask_trainerCard_attributes();
        
        this.cardType = CARD_TYPES[1]; //trainer
        this.name = (String)valuesForAttributes.get("name");
        this.specialType = (String)valuesForAttributes.get("specialType");
        this.explanation = (String)valuesForAttributes.get("explanation");
        this.trainerRule = (String)valuesForAttributes.get("trainerRule");
        this.cardNb = (int)valuesForAttributes.get("cardNb");
    }
    
    /**
     * Display all the informations of the card for the user and return them in the form of a string
     */
    @Override
    public String toString() {
        String cardInformation = "";
        
        cardInformation += "---------------------------\n| Trainer Card Information :\n---------------------------\n";
        cardInformation += "| Name : "+this.name+"\n";
        cardInformation += "| Trainer type : "+this.specialType+"\n";
        cardInformation += "| Explanation : \n|    "+this.explanation+"\n";
        cardInformation += "| Trainer rule : "+this.trainerRule+"\n";
        cardInformation += "| Collection card number : "+this.cardNb+"\n";
        cardInformation += "---------------------------\n";
        
        return cardInformation;
    }
}
