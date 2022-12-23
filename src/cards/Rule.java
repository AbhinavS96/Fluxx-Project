package cards;

public abstract class Rule extends Card {
    public Rule(String name, String cardDescription) {
        // Call the super constructor with the name argument
        super(name, "RULE", cardDescription);
    }
}
