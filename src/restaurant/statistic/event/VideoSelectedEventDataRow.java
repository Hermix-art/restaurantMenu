package restaurant.statistic.event;

import restaurant.ad.Advertisement;

import java.util.Date;
import java.util.List;

/**
 * Represents event, related to the advertisement data, selected for showing, during order preparation
 *
 * @author Herman Kulik
 */
public class VideoSelectedEventDataRow implements EventDataRow {
    private List<Advertisement> optimalVideoSet;
    private long amount;
    private int totalDuration;
    private final Date currentDate;

    /**
     * @param optimalVideoSet the set of video advertisements, chosen for showing
     * @param amount          sum of money, for video showing
     * @param totalDuration   sum of miliseconds for video showing
     */
    public VideoSelectedEventDataRow(List<Advertisement> optimalVideoSet, long amount, int totalDuration) {
        this.optimalVideoSet = optimalVideoSet;
        this.amount = amount;
        this.totalDuration = totalDuration;
        this.currentDate = new Date();
    }
}
