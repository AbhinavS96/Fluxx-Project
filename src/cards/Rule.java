package cards;

public abstract class Rule extends Card {
	protected final String cardDescription;
	
    public Rule(String cardDescription) {
        // Call the super constructor with the name argument
        super("RULE");
        this.cardDescription = cardDescription;
    }
}
