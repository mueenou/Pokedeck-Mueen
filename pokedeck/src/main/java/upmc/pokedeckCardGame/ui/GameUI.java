package upmc.pokedeckCardGame.ui;

import java.io.IOException;
import java.util.*;
import upmc.pokedeckCardGame.game.Game;
import upmc.pokedeckCardGame.game.PokemonCard;

public class GameUI {
    private final Game game = new Game();
    private final Scanner console = new Scanner(System.in);

    /**
     * Initialize the game and start it
     */
    public void start() throws IOException {     
        print_welcome_msg();
        ArrayList<String> names = ask_players_names();
        game.initialize(names);
        game.play();
    }

    /**
     * Ask the name of all the players recursively and return them in an array
     */
    private ArrayList<String> ask_players_names() {
        ArrayList<String> names = new ArrayList<String>();
        boolean boolAskName = true;
        
        System.out.println("****************************");
        System.out.println("Prof. Oak : This is the first time i see you here !");
        
        while(boolAskName) {
            names.add(prof_ask_name());
            boolAskName = menu_prof_other_name();
        }
        
        System.out.println("Prof. Oak : Ok, so let's see your collection now !");
        return names;
    }
    
    /**
    * Ask ONE player his name
    */
    private String prof_ask_name() {
        String playerName = "";
        
        while(playerName.equals("")) {
            System.out.println("Prof. Oak : What's your name ? ");
            playerName = console.nextLine();
        }
        System.out.println("Prof. Oak : "+playerName+", what a beautiful name ! ");
        
        return playerName;
    }
    
    /**
     * Display a menu that ask if it needs another name
     */
    private boolean menu_prof_other_name() {
        boolean boolOtherName = false;
        String response = "";
        
        while(response.equals("") || (!response.equals("y") && !response.equals("n"))) {
            System.out.println("Prof. Oak : Do you have another friend with you ? (y/n)");
            response = console.nextLine();
        }
        
        if(response.equals("y"))
            boolOtherName = true;
        
        return boolOtherName;
    }

    /**
     * Explicit
     */
    private void print_welcome_msg() {
        System.out.println("****************************");
        System.out.println("* POKEDECK"); 
        System.out.println("****************************");
        System.out.println("Welcome in the pokedeck !\nHere you can create your own cards,");
        System.out.println("save, modify and manage your collection !\n");
        System.out.println("Have fun !\n");
    }
    
    /**
     * Explicit
     */
    public static void clear_console_buffer(Scanner parConsole) {
        if(parConsole.hasNextLine())
            parConsole.nextLine();
    }
}
