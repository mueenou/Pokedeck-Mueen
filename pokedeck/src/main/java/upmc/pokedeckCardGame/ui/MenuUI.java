package upmc.pokedeckCardGame.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import upmc.pokedeckCardGame.game.Attack;
import upmc.pokedeckCardGame.game.Card;
import upmc.pokedeckCardGame.game.Collection;
import upmc.pokedeckCardGame.game.EnergyCard;
import upmc.pokedeckCardGame.game.PokemonCard;
import upmc.pokedeckCardGame.game.TrainerCard;

public final class MenuUI {
    private static final Scanner console = new Scanner(System.in);
    
    //Private constructor
    private MenuUI() {}
    
    /**
     * Display the main choice menu that ask what the player want to do with his collection
     */
    public static int collection_main_menu(String playerName) {
        int choiceMenu = -1;
        
        print_collection_menu_msg(playerName);
        choiceMenu = ask_collection_menu();
        
        return choiceMenu;
    }    
    
    /**
     * Print messages at the beginning of the collection main menu
     */
    private static void print_collection_menu_msg(String playerName) {
        System.out.println("****************************");
        System.out.println("* "+playerName+"'s Collection");
        System.out.println("****************************");
        System.out.println("What do you want to do with your collection ?");
        System.out.println(" 1. Add a card");
        System.out.println(" 2. Consult");
        System.out.println(" 3. Search cards by criteria");
        System.out.println(" 4. Save collection");
        System.out.println(" 5. Load collection");
        System.out.println(" 6. Quit the game");
    }
    
    /**
     * Ask the player what choice he want to do in the collection main menu
     */
    private static int ask_collection_menu() {
        int choice = 0;
        
        do {
            try {
                System.out.println("\nYour choice ?");
                choice = console.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.print("(!) Select a number !\n");
                GameUI.clear_console_buffer(console);
            }
        }while(!(choice>=1 && choice <=6));
        
        return choice;
    }
    
    /**
     * Display the creation card menu
     * return the created card
     */
    public static Card add_card_menu() {
        Card createdCard = null;
        
        createdCard = ask_type_card();
        createdCard.create();
        
        return createdCard;
    }
    
    /**
     * Ask the type of the card and return a card of this type (pokemon, trainer, energy)
     */
    private static Card ask_type_card() {
        String chosenType = "";
        Card card = null;
        
        chosenType = print_type_card_msg();
        card = switch_card_type(chosenType);
        
        return card;
    }
    
    /**
     * Print the message at the beginning of the menu to add a card
     * return the chosen type by the player
     */
    private static String print_type_card_msg() {
        String choice = "";
        
        System.out.println("****************************");
        System.out.println("Card creation :");
        choice = print_option_type_card();
        
        if(choice == null)
            choice = print_type_card_msg();
        
        return choice;
    }
    
    /**
     * Print the 3 types of card for the creation card menu
     * return the choice of the player
     */
    private static String print_option_type_card() {
        String tabTypes[] = Card.CARD_TYPES;
        int choice = 0;
        
        System.out.println("What type of card do you want ? ");
        for(int i=0; i<tabTypes.length; i++) {
            System.out.println(" "+(i+1)+". "+tabTypes[i]);
        }
        
        while(choice < 1 || choice > tabTypes.length) {
            try {
                System.out.println("\nYour choice ? ");
                choice = console.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.print("(!) Select a number !\n");
                GameUI.clear_console_buffer(console);
            }
        }
        
        return tabTypes[choice-1];
    }
    
    /**
     * Create a card following the given type
     */
    private static Card switch_card_type(String chosenType) {
        Card card = null;
        
        switch(chosenType) {
            case "pokemon":
                card = new PokemonCard();
                break;
            case "trainer":
                card = new TrainerCard();
                break;
            case "energy":
                card = new EnergyCard();
                break;
            default:
                System.out.println("(!) Unknown card type");
                break;
        }
        
        return card;
    }
    
    /**
     * Display the collection and ask what actions the user wants to do with it
     */
    public static void action_consult_menu(Collection currentCollection) {
        System.out.println("****************************");
        System.out.println("Collection content :\n");
        
        currentCollection.list_all_cards();
        
        collection_consult_subMenu(currentCollection);
    }
    
    /**
     * Ask the user to chose which criteria he wants to use, and display a list with the cards.
     * Ask the user what type of action he wants to do with the list.
     */
    public static void action_search_by_criteria(Collection currentCollection) {
        if(currentCollection.get_size() > 0) {
            print_search_by_criteria_subMenu();
            ask_search_by_criteria_subMenu(currentCollection);
        }
        else
            System.out.println("\n(!) The collection is empty for the moment");
    }

    /**
     * Submenu that ask the user what type of action he wants to do with his collection
     */
    private static void collection_consult_subMenu(Collection collection) {
        boolean boolQuit = false;
        
        while(!boolQuit) {
            print_action_card_subMenu();
            boolQuit = switch_collection_consult_subMenu(collection);
        }
        
    }
    
    /**
     * Display the first text of the subMenu in the collection consult menu
     */
    private static void print_action_card_subMenu() {
        System.out.println("\nProf Oak : What do you want to do now with these cards ?");
        System.out.println(" 1. Consult a card");
        System.out.println(" 2. Modify a card");
        System.out.println(" 3. Delete a card");
        System.out.println(" 4. Return to main menu \n");
    }
    
    /**
     * Ask the player to do a choice and does actions base on the choice made
     */
    private static boolean switch_collection_consult_subMenu(Collection collection) {
        int choiceSubMenu;
        boolean boolQuit = false;
        
        choiceSubMenu = ask_collection_consult_subMenu();
        
        switch(choiceSubMenu) {
            //Consult a card
            case 1:
                collection_subMenu_consult_card(collection);
                break;
            //Modify a card
            case 2:
                System.out.println("(!) Not available for the moment");
                break;
            //Delete a card
            case 3:
                collection_subMenu_delete_card(collection);
                break;
            //Quit the menu
            case 4:
                boolQuit = true;
                break;
            //Error
            default:
                System.out.println("(!) Invalid choice");
                break;
        }
        
        return boolQuit;
    }
    
    /**
     * Explicit
     */
    private static void collection_subMenu_consult_card(Collection collection) {
        Card chosenCard = null;
        
        print_collection_list(collection);
        
        if(collection.get_size() != 0) {
            chosenCard = CardMenuUI.card_consult_menu(collection, false);
            
            if(chosenCard != null)
                System.out.println("\n"+chosenCard.toString());
            else
                System.out.println("(!) No card selected");
        }
        else
             System.out.println("(!) You don't have cards in your collection yet");
        
    }
    
    /**
     * Explicit
     */
    private static void collection_subMenu_delete_card(Collection collection) {
        int chosenCardIndex = -1;
        
        print_collection_list(collection);
        
        if(collection.get_size() != 0) {
            chosenCardIndex = CardMenuUI.card_consult_menu_index(collection, false);
            
            if(chosenCardIndex >= 0)
                collection.delete_card(chosenCardIndex);
            else
                System.out.println("(!) No card selected");
        }
        else
             System.out.println("(!) You don't have cards in your collection yet");
    }
    
    /**
     * Display the first text of the subMenu in the collection consult menu 
     */
    public static void print_collection_list(Collection collection) {
        System.out.println("\n****************************");
        System.out.println("Collection content :");
        collection.list_all_cards();
        System.out.println("");
    }
    
    /**
     * Ask the user to do a choice for the consult collection subMenu
     */
    private static int ask_collection_consult_subMenu() {
        int choice = -1;
        
        while(choice<1 || choice>4) {
            try {
                System.out.println("Your choice ? ");
                choice = console.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.print("(!) Select a positive number !\n");
                GameUI.clear_console_buffer(console);
            }
        }
        
        return choice;
    }
    
    /**
     * Display the first text of the search by criteria subMenu
     */
    private static void print_search_by_criteria_subMenu() {
        System.out.println("\n****************************");
        System.out.println("Search cards by criteria :\n");
        System.out.println("Prof. Oak : Which criteria do you want to pick ?");
        System.out.println(" 1. By name");
        System.out.println(" 2. By collection card number\n");
    }
    
    /**
     * Ask the user which filter he wants to apply for the search
     * and do an action depending on his choice
     */
    private static void ask_search_by_criteria_subMenu(Collection collection) {
        int choice = 0;
        String criteria = "Unknown";
        
        while(choice<1 || choice>2) {
            try {
                System.out.println("Your choice ? ");
                choice = console.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.print("(!) Select a positive number !\n");
                GameUI.clear_console_buffer(console);
            }
        }
        
        criteria = ask_criteria_filter();
        
        switch_criteria(collection, choice, criteria);
    }
    
    /**
     * Ask the user to give the filter's value for criteria
     */
    private static String ask_criteria_filter() {
        String result = "";
        
        GameUI.clear_console_buffer(console);
        
        while(result.equals("")) {
            System.out.println("Enter the filter's value : ");
            result = console.nextLine();
        }

        return result;
    }
    
    /**
     * Switch user's choice for criteria
     */
    private static void switch_criteria(Collection collection, int choice, String criteria) {
        switch(choice) {
            case 1:
                search_card_by_name(collection, criteria);
                break;
            case 2:
                search_card_by_cardNb(collection, Integer.parseInt(criteria));
                break;
            default:
                System.out.println("(!) Select a number in the list ");
                break;
        }
    }
    
    /**
     * Explicit
     */
    private static void search_card_by_name(Collection collection, String criteria) {
        LinkedHashMap<Integer, Card> searchResult = new LinkedHashMap<Integer, Card>();
        Card currentCard;
        String currentName;
        
        for(int i=0; i<collection.get_size(); i++) {
            currentCard = collection.get_card(i);
            currentName = currentCard.get_name();
            
            if(currentName.toLowerCase().contains(criteria.toLowerCase()))
                searchResult.put(i, currentCard);
        }
        
        print_criteria_research(searchResult);
        action_criteria_research(collection, searchResult);
    }
    
    /**
     * Explicit
     */
    private static void search_card_by_cardNb(Collection collection, int criteria) {
        LinkedHashMap<Integer, Card> searchResult = new LinkedHashMap<Integer, Card>();
        Card currentCard;
        int currentNb;
        
        for(int i=0; i<collection.get_size(); i++) {
            currentCard = collection.get_card(i);
            currentNb = currentCard.get_cardNb();
            
            if(currentNb == criteria)
                searchResult.put(i, currentCard);
        }
        
        print_criteria_research(searchResult);
        action_criteria_research(collection, searchResult);
    }
    
    /**
     * Ask the user which actions he wants to make with the result of the search
     */
    private static void action_criteria_research(Collection collection, LinkedHashMap<Integer, Card> searchResult) {
        boolean quitMenu = false;
        
        while(!quitMenu) {
            print_criteria_research(searchResult);
            print_action_card_subMenu();
            quitMenu = switch_criteria_search(collection, searchResult);
        }
    }
    
    /**
     * Explicit
     */
    private static void print_criteria_research(LinkedHashMap<Integer, Card> searchResult) {
        int listIndex = 1;
        
        System.out.println("****************************");
        System.out.println("Search results :\n");
        
        if(!searchResult.isEmpty()) {
            for (Map.Entry<Integer, Card> result : searchResult.entrySet()) {
                System.out.println(listIndex+". "+result.getValue().get_name());
                listIndex++;
            }
        }
        else
            System.out.println("(!) No results");
    }
    
    /**
     * Switch that does the action made by the user for the research by criteria
     */
    private static boolean switch_criteria_search(Collection collection, LinkedHashMap<Integer, Card> searchResult) {
        int choiceSubMenu;
        boolean boolQuit = false;
        
        choiceSubMenu = ask_collection_consult_subMenu();
        
        switch(choiceSubMenu) {
            //Consult a card
            case 1:
                criteria_subMenu_consult_card(searchResult);
                break;
            //Modify a card
            case 2:
                System.out.println("(!) Not available for the moment");
                break;
            //Delete a card
            case 3:
                criteria_subMenu_delete_card(collection, searchResult);
                break;
            //Quit the menu
            case 4:
                boolQuit = true;
                break;
            //Error
            default:
                System.out.println("(!) Invalid choice");
                break;
        }
        
        return boolQuit;
    }
    
    /**
     * Allow the user to consult a card based on a research list
     */
    private static void criteria_subMenu_consult_card(LinkedHashMap<Integer, Card> searchResult) {
        Card chosenCard = null;
        
        print_criteria_research(searchResult);
        
        if(!searchResult.isEmpty()) {
            chosenCard = CardMenuUI.criteria_card_consult_menu(searchResult);
            
            if(chosenCard != null)
                System.out.println("\n"+chosenCard.toString());
            else
                System.out.println("(!) No card selected");
        }
        else
             System.out.println("(!) No results available");
        
    }
    
    /**
     * Ask the user which card in the list he wants to delete
     */
    private static void criteria_subMenu_delete_card(Collection collection, LinkedHashMap<Integer, Card> searchResult) {
        int chosenCardIndex = -1;
        
        print_criteria_research(searchResult);
        
        if(!searchResult.isEmpty()) {
            chosenCardIndex = CardMenuUI.criteria_card_consult_menu_index(searchResult);
            
            if(chosenCardIndex >= 0) {
                collection.delete_card(chosenCardIndex);
                searchResult.remove(chosenCardIndex);
            }
            else
                System.out.println("(!) No card selected");
        }
        else
             System.out.println("(!) No results available");
    }
    
    /**
     * Display the messages at the beginning of the creation of a card step by step
     */
    public static void print_create_card_msg(String cardType) {
        System.out.println("****************************");
        System.out.println("Create your "+cardType+" card :\n");
        System.out.println("Prof. Oak : Here you can create your card step by step ! Let's Go !\n");
    }
    
    /**
     * Ask the user to fill every attributes of an attack
     */
    public static HashMap<String, Object> attack_ask_all() {
        HashMap<String, Object> valuesForAttributes = new HashMap<>();
        
        System.out.println("****************************");
        System.out.println("Attack creation : \n");
        valuesForAttributes.put("name", ask_name());
        valuesForAttributes.put("neededEnergy", ask_neededEnergy());
        valuesForAttributes.put("damage", ask_dmg());
        valuesForAttributes.put("description", ask_description());
        System.out.println("****************************");
        
        return valuesForAttributes;
    }
    
    /**
     * Explicit
     */
    private static String ask_name() {
        String result = "";
        
        GameUI.clear_console_buffer(console);
        
        while(result.equals("")) {
            System.out.println(" * Name : ");
            result = console.nextLine();  
        }

        return result;
    }
    
    /**
     * Explicit
     */
    private static ArrayList<String> ask_neededEnergy() {
        ArrayList<String> result = new ArrayList<>();
        String otherEnergy = "n";
        int chosenEnergyIndex = 0;
        
        System.out.println(" * Needed energy : ");
        
        print_energies();
        do {
            chosenEnergyIndex = ask_energy();
            result.add(EnergyCard.ENERGY_TYPES[chosenEnergyIndex]);
            
            do {
                GameUI.clear_console_buffer(console);
                System.out.println("Do you want to add another energy ? (y/n) ");
                otherEnergy = console.nextLine();
            }while(!otherEnergy.equals("n") && !otherEnergy.equals("y"));
            
        }while(otherEnergy.equals("y"));
        
        return result;
    }
    
    /**
     * Print all energies in the form of a list
     */
    public static void print_energies() {
        final int MAX_ENERGY = EnergyCard.ENERGY_TYPES.length;
        
        for(int i=1; i<=MAX_ENERGY; i++) {
            System.out.println(" "+i+". "+EnergyCard.ENERGY_TYPES[i-1]);
        }
        System.out.println("");
    }
    
    /**
     * Ask the user what type of energy he want to pick, return the index of the chosen energy 
     */
    public static int ask_energy() {
        int result = 0;
        
        do {
            try {
                System.out.println("What type of energy do you want to pick ? ");
                result = console.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.print("(!) Select a number in the list\n");
                GameUI.clear_console_buffer(console);
            }
            if(result>EnergyCard.ENERGY_TYPES.length) {
                System.out.print("(!) This number is too high\n");
            }
        } while(result<=0 || result>EnergyCard.ENERGY_TYPES.length);
        
        return result-1;
    }
    
    /**
     * Ask the user how many damage an attack is going to do
     */
    public static int ask_dmg() {
        int result = 0;
        
        do {
            try {
                System.out.println(" * Damage : ");
                result = console.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.print("(!) Chose a positive number\n");
                GameUI.clear_console_buffer(console);
            }
        } while(result<=0);
        
        return result;
    }
    
    /**
     * Ask the user to fill a description field
     */
    public static String ask_description() {
        String result = "";
        
        GameUI.clear_console_buffer(console);
        
        do {
            System.out.println(" * Description : ");
            result = console.nextLine();
        }while(result.equals(""));
        
        return result;
    }
    
    /**
     * Print all trainer type in the form of a list
     */
    public static void print_trainerType() {
        final int MAX_TRAINER = TrainerCard.TRAINER_TYPES.length;
        
        for(int i=1; i<=MAX_TRAINER; i++) {
            System.out.println(" "+i+". "+TrainerCard.TRAINER_TYPES[i-1]);
        }
        System.out.println("");
    }
    
    /**
     * Ask what trainer type the user want to select
     */
    public static int ask_trainerType() {
        int result = 0;
        
        do {
            try {
                System.out.println("What trainer type do you want to pick ? ");
                result = console.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.print("(!) Select a number in the list\n");
                GameUI.clear_console_buffer(console);
            }
            if(result>TrainerCard.TRAINER_TYPES.length) {
                System.out.print("(!) This number is too high\n");
            }
        } while(result<=0 || result>TrainerCard.TRAINER_TYPES.length);
        
        return result-1;
    }
    
    /**
     * Explicit
     */
    public static void loadCollection(Collection collec) throws IOException {
        String collecName = "";
        
        printLoadCollection();
        collecName = askLoadCollection();
        
        if(collecName != "") {
            System.out.println("Loading : collection_" + collecName + ".json");
            collec.loadCollec(collecName);
        }
    }
    
    /**
     * Print text to load a collection
     */
    private static void printLoadCollection() {
        System.out.println("****************************");
        System.out.println("Load collection : ");
    }
    
    /**
     * Ask the name of the collection to load
     */
    private static String askLoadCollection() {
        String result = "";
        
        System.out.println("Enter the name of the collection you want to load : ");
        GameUI.clear_console_buffer(console);
        result = console.nextLine();
        
        return result;
    }
}
