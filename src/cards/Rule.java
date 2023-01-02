package cards;


/**
 * 
 * @author abhinav
 * Abstract class as a parent to all rule subsclasses
 *
 */
public abstract class Rule extends Card {
	protected final String cardDescription;
	
    public Rule(String cardDescription) {
        // Call the super constructor with the name argument
        super("RULE");
        this.cardDescription = cardDescription;
    }
}
