package restaurant.ad;

import java.util.Objects;

/**
 * @author Herman Kulik
 */
public class Advertisement {
    private Object content;
    private String name;
    private long initialAmount;
    private int hits;
    private int duration;
    //max initialAmount - sum duration should be < time of cooking - 1 only display of 1 advert -

    private long amountPerOneDisplaying;

    public Advertisement(Object content, String name, long initialAmount, int hits, int duration) {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;

        if (hits > 0) {
            amountPerOneDisplaying = initialAmount / hits;
        }

    }

    public void revalidate() {
        if (hits <= 0) {
            throw new UnsupportedOperationException();
        } else {
            hits--;
        }
    }

    public int getHits() {
        return hits;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public long getAmountPerOneDisplaying() {
        return amountPerOneDisplaying;
    }

    public long getInitialAmount() {
        return initialAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Advertisement)) return false;
        Advertisement that = (Advertisement) o;
        return getInitialAmount() == that.getInitialAmount() && getHits() == that.getHits() && getDuration() == that.getDuration() && getAmountPerOneDisplaying() == that.getAmountPerOneDisplaying() && Objects.equals(content, that.content) && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, getName(), getInitialAmount(), getHits(), getDuration(), getAmountPerOneDisplaying());
    }

}
