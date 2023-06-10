package domain;

public class Card {

    private final Pattern pattern;
    private final Number number;

    public static Card createWithMatchNumber(final int matchingNumber) {
        Pattern pattern = Pattern.findMatchingPattern(matchingNumber);
        Number number = Number.findMatchingNumber(matchingNumber);

        return new Card(pattern, number);
    }

    private Card(final Pattern pattern, final Number number) {
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
