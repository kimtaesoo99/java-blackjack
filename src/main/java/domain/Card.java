package domain;

public class Card {

    private final Pattern pattern;
    private final Number number;

    public static Card createWithMatchNumber(final int matchingNumber) {
        return new Card(matchingNumber);
    }

    private Card(final int matchingNumber) {
        this.pattern = Pattern.findMatchingPattern(matchingNumber);
        this.number = Number.findMatchingNumber(matchingNumber);
    }

    public Card(final Pattern pattern, final Number number) {
        this.pattern = pattern;
        this.number = number;
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
