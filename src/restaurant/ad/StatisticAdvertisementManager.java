package restaurant.ad;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents singleton class, responsible for getting sets of archived/not archived
 * video-advertisements from {@link #storage}
 *
 * @author Herman Kulik
 */
public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager manager;
    private AdvertisementStorage storage = AdvertisementStorage.getInstance();

    private StatisticAdvertisementManager() {
    }

    public static StatisticAdvertisementManager getInstance() {
        if (manager == null) {
            manager = new StatisticAdvertisementManager();
        }
        return manager;
    }

    /**
     * Provides the list of videos (advertisements) with more than 0 number of hits left, by accessing them via {@link #storage}
     *
     * @return sorted set of not archived videos' names
     */
    public Map<String, Integer> getActiveVideos() {
        Map<String, Integer> notArchivedVideosNames = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

        List<Integer> filtered = storage.list().stream()
                .filter(s -> s.getHits() > 0)
                .map(s -> notArchivedVideosNames.put(s.getName(), s.getHits()))
                .collect(Collectors.toList());

        return notArchivedVideosNames;
    }

    /**
     * Provides the list of videos(advertisements) with 0 number of hits left, by accessing them via {@link #storage}
     *
     * @return sorted set of archived videos' names
     */
    public Set<String> getArchivedVideos() {
        Set<String> archivedVideosNames = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);

        List<Boolean> filtered = storage.list().stream()
                .filter(s -> s.getHits() <= 0)
                .map(s -> archivedVideosNames.add(s.getName()))
                .collect(Collectors.toList());

        return archivedVideosNames;
    }
}
