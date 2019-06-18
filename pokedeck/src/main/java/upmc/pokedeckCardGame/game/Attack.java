package upmc.pokedeckCardGame.game;

import java.util.ArrayList;
import java.util.HashMap;
import upmc.pokedeckCardGame.ui.MenuUI;

/**
 * EnergyCard is a representation of pokemon energy card
 */
public class Attack {
    private String name;
    private ArrayList<String> neededEnergy = new ArrayList<String>();
    private int damage;
    private String description;
    
    /**
     * Default constructor for a basic attack if autoFill is true, ask the user to fill the fields if false
     */    
    public Attack(boolean autoFill) {
        if(autoFill) {
            this.name = "Basic attack";
            this.neededEnergy.add(EnergyCard.ENERGY_TYPES[EnergyCard.ENERGY_TYPES.length-1]);
            this.damage = 10;
            this.description = "The most basic attack a pokemon can do. Seriously, it's just a slap.";
        }
        else {
            fill_attack();
        }
    }
    
    /**
     * Return the information about the attack in the form of a string
     */
    public String toString() {
        String result = "";
        
        for(String energy : this.neededEnergy) {
            result += "["+energy+"]";
        }
        result += " "+this.name+" (-"+this.damage+"hp) : "+this.description;
        
        return result;
    }
    
    /**
     * Ask the user how to fill the attacks
     */
    private void fill_attack() {
        HashMap<String, Object> valuesForAttributes = new HashMap<>();
        
        valuesForAttributes = MenuUI.attack_ask_all();
        
        this.name = (String)valuesForAttributes.get("name");
        this.neededEnergy = (ArrayList<String>)valuesForAttributes.get("neededEnergy");
        this.damage = (int)valuesForAttributes.get("damage");
        this.description = (String)valuesForAttributes.get("description");
    }
}
