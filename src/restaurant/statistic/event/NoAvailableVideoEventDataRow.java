package restaurant.statistic.event;

import java.util.Date;

/**
 * Represents event, related to the situation, when there is no available advertisements
 *
 * @author Herman Kulik
 */
public class NoAvailableVideoEventDataRow implements EventDataRow {
    private final Date currentDate;
    private int totalDuration;

    @Override
    public Date getDate() {
        return currentDate;
    }

    @Override
    public int getTime() {
        return 0;
    }

    @Override
    public EventType getType() {
        return EventType.NO_AVAILABLE_VIDEO;
    }

    /**
     * @param totalDuration time of dish cooking
     */
    public NoAvailableVideoEventDataRow(int totalDuration) {
        this.totalDuration = totalDuration;
        this.currentDate = new Date();

    }
}
