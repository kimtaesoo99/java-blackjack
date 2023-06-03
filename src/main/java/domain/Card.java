package domain;

public class Card {

    private final Pattern pattern;
    private final Number number;

    public Card(final int matchingNumber) {
        this.pattern = Pattern.findMatchingPattern(matchingNumber);
        this.number = Number.findMatchingNumber(matchingNumber);
    }

    public String getPattern() {
        return pattern.getName();
    }

    public String getNumber() {
        return number.getName();
    }

    public String getFullName() {
        return number.getName() + pattern.getName();
    }
}
