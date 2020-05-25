package nicebank;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Money {
    private final int dollars;
    private final int cents;

    public Money() {
        this.dollars = 0;
        this.cents = 0;
    }

    public Money(String amount) {
        Pattern pattern = Pattern.compile("^[^\\d]*([\\d]+)\\.([\\d][\\d])$");
        Matcher matcher = pattern.matcher(amount);

        matcher.find();
        this.dollars = Integer.parseInt(matcher.group(1));
        this.cents = Integer.parseInt(matcher.group(2));
    }

    public Money(int newDollars, int newCents) {
        this.dollars = newDollars;
        this.cents = newCents;
    }

    public int dollars() {
        return dollars;
    }

    public int cents() {
        return cents;
    }

    public Money add(Money amount){
        int newDollars = this.dollars() + amount.dollars();
        int newCents = this.cents() + amount.cents();

        if(newCents >= 100){
            newCents -= 100;
            newDollars++;
        }

        return new Money(newDollars, newCents);
    }

    public Money minus(Money amount){
        int newDollars = this.dollars() + amount.dollars();
        int newCents = this.cents() + amount.cents();

        if(newCents < 0){
            newCents += 100;
            newDollars--;
        }

        return new Money(newDollars, newCents);
    }

    @Override
    public boolean equals(Object other) {
        boolean equals = false;
        if (other instanceof Money) {
            Money otherMoney = (Money) other;
            equals = (this.dollars() == otherMoney.dollars()
                    && this.cents() == otherMoney.cents());
        }
        return equals;
    }

    @Override
    public String toString() {
        return String.format("$%01d.%02d", dollars(), cents());
    }
}
