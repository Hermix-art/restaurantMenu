package restaurant.ad;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Herman Kulik
 */
public class AdvertisementStorage {
    private static AdvertisementStorage storage;
    private final List<Advertisement> videos = new ArrayList<>();

    public List<Advertisement> list() {
        return videos;
    }

    public void add(Advertisement advertisement) {
        videos.add(advertisement);
    }

    private AdvertisementStorage() {
        Object videoContent = new Object();
        Advertisement advertisement1 =
                new Advertisement(videoContent, "Vanish video", 100, 50, 10 * 60);//3 min
        Advertisement advertisement5 =
                new Advertisement(videoContent, "BMW", 100, 12, 10 * 60);//3 min
        Advertisement advertisement4 =
                new Advertisement(videoContent, "VW video", 100, 50, 4 * 60);
        Advertisement advertisement2 =
                new Advertisement(videoContent, "Food truck video", 2000, 5, 3 * 60);
        Advertisement advertisement3 =
                new Advertisement(videoContent, "Mercedes video", 7000, 10, 15 * 60);

        videos.add(advertisement1);
        videos.add(advertisement2);
        videos.add(advertisement3);
        videos.add(advertisement4);
        videos.add(advertisement5);
    }

    public static AdvertisementStorage getInstance() {
        if (storage == null) storage = new AdvertisementStorage();
        return storage;
    }
}
