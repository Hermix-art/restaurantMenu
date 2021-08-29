package restaurant.ad;

import java.util.Collections;
import java.util.Comparator;

/**
 * @author Herman Kulik
 */
public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds; // total cooking time of order

    private Comparator comparator = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            return 0;
        }
    };

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() {
        if(storage.list().isEmpty()){
            throw new NoVideoAvailableException();
        }

        Collections.sort(storage.list(),comparator);
    }


}
