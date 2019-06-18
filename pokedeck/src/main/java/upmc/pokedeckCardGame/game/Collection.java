package upmc.pokedeckCardGame.game;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import upmc.pokedeckCardGame.ui.CardMenuUI;

/**
 * Collection represents a pokemon cards collection
 */
public class Collection {
    private String owner; 
    private List<Card> cards = new ArrayList<Card>();
    
    /**
     * Constructor that create a collection for the player with his name
     */
    public Collection(String name) {
        this.owner = name;
    }
    
    /**
     * Display all cards in the collection in the form of a list
     */
    public void list_all_cards(){
        Iterator<Card> cardIterator = cards.iterator();
        
        if(this.get_size() < 1)
            System.out.println("(!) The collection is empty for the moment");
        
        for(int cardIndex = 0; cardIterator.hasNext(); cardIndex++) {
            Card card = cardIterator.next();
            System.out.println((cardIndex+1)+". "+card.get_name());
        }
    }
    
    /**
     * Add a card in the collection
     */
    public void add_card(Card newCard) {
        cards.add(newCard);
    }
    
    /**
     * Delete a card in the collection by its index
     */
    public void delete_card(int cardIndex) {
        String response;
        
        response = CardMenuUI.confirm_delete_card(cards.get(cardIndex).get_name());
        
        if(response.equals("y"))
            cards.remove(cardIndex);
    }
       
    /**
     * Return the card saved at index position in the list
     */
    public Card get_card(int index) {
        return cards.get(index);
    }
    
    /**
     * Return the size of the collection
     */
    public int get_size() {
        return cards.size();
    }
    
    /**
     * Verify if a cardNb already exists in the collection
     * if it doesn't exist : return true 
     * if it already exist but it have the same cardName : return true
     * if it already exist and don't have the same cardName : return false
     */
    public boolean cardNb_available(String cardName, int cardNb) {
        boolean boolVerify = true;
        Card currentCard = null;
        Iterator<Card> cardsIterator = this.cards.iterator();
        
        while(cardsIterator.hasNext() && boolVerify!=false) {
            currentCard = cardsIterator.next();
            
            if(currentCard.get_cardNb()==cardNb)
                if(currentCard.get_name()!=cardName)
                    boolVerify = false;
        }
        
        return boolVerify;
    }
    
    /**
     * Save the collection in the form of a Json
     */
    public void saveCollec(String name) throws IOException {
        String fileName = "collection_" + name + ".json";
        FileWriter fileWriter = new FileWriter(fileName);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonResult = gson.toJson(this.cards);
        
        System.out.println("Collection saved as : " + fileName);
        fileWriter.write(jsonResult);
        fileWriter.close();
    }
    
    /**
     * Load a collection based on a json file
     */
    public void loadCollec(String name) throws IOException {
        Gson gson = new Gson();

        Card[] cards = gson.fromJson(new FileReader("collection_" + name + ".json"), Card[].class);
        this.cards = Arrays.asList(cards);
        list_all_cards();
    }
    
}
