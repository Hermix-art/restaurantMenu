package restaurant.statistic.event;

import java.util.Date;

/**
 * Marker interface
 *
 * @author Herman Kulik
 */
public interface EventDataRow {
    EventType getType();

    Date getDate();

    int getTime();
}
