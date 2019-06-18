package upmc.pokedeckCardGame.game;

import java.io.IOException;
import java.util.*;
import upmc.pokedeckCardGame.ui.CardMenuUI;
import upmc.pokedeckCardGame.ui.MenuUI;

public class Game {
    private HashMap<String, Collection> collections = new HashMap<String, Collection>();
    private ArrayList<String> players_name;
    private String currentPlayer = "Unknown";
    private Collection currentCollection;
    private boolean boolQuitGame = false;
    
    /**
     * Default constructor
     */
    public Game() {}
    
    /**
     * Initialize attributes to begin the game
     * Note : For this version, only the first player can have a collection and play
     */
    public void initialize(ArrayList<String> par_players_name) {
        players_name = par_players_name;
        currentPlayer = players_name.get(0);
        
        Collection collectionPlayer = new Collection(currentPlayer);
        collections.put(currentPlayer, collectionPlayer);
        currentCollection = collections.get(currentPlayer);
        CardMenuUI.setActualCollection(currentCollection);
    }
    
    /**
     * Main method for the game
     */
    public void play() throws IOException {
        int collectionMenuChoice = -1;
        
        while(!boolQuitGame) {
            collectionMenuChoice = MenuUI.collection_main_menu(currentPlayer);
            switch_collection_main_menu_choice(collectionMenuChoice);
        }
    }
    
    /**
     * Does actions base on the choice made by the player in the collection main menu
     */
    private void switch_collection_main_menu_choice(int menuChoice) throws IOException {
        switch(menuChoice) {
            //Add a card
            case 1:
                currentCollection.add_card(MenuUI.add_card_menu());
                break;
            //Consult collection
            case 2:
                MenuUI.action_consult_menu(currentCollection);
                break;
            //Search cards by criteria
            case 3:
                MenuUI.action_search_by_criteria(currentCollection);
                break;
            //Save the collection
            case 4:
                currentCollection.saveCollec(currentPlayer);
                break;
            //Load a collection
            case 5:
                MenuUI.loadCollection(currentCollection);
                break;
            //Quit the game
            case 6:
                boolQuitGame = true;
                break;
            //Default
            default:
                System.out.println("(!) Invalid choice");
                break;
        }
    }
}
