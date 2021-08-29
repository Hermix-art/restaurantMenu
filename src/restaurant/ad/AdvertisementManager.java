package restaurant.ad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Herman Kulik
 */
public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    private List<List<Advertisement>> mainList = new ArrayList<>();

    /**
     * Sorts best advertisements set by the amount per one displaying (decreasing), and then by cost of one second(multiplied by 1000 )
     */
    private final Comparator<Advertisement> bestVideosComparator = new Comparator<Advertisement>() {
        @Override
        public int compare(Advertisement o1, Advertisement o2) {
            if (o1.getAmountPerOneDisplaying() > o2.getAmountPerOneDisplaying()) return -1;
            else if (o1.getAmountPerOneDisplaying() < o2.getAmountPerOneDisplaying()) return 1;
            else {
                long secValue1 = Math.round(((double) o1.getAmountPerOneDisplaying() / o1.getDuration()) * 1000);
                long secValue2 = Math.round(((double) o2.getAmountPerOneDisplaying() / o2.getDuration()) * 1000);

                return Long.compare(secValue1, secValue2);
            }
        }
    };

    /**
     * Sorts different combinations of advertisements in {@link #mainList} by 3 levels:
     * by maximum summary amount per 1 displaying of advertisements in a set
     * by maximum duration of advertisements in a set
     * by minimum number of  hits of advertisements in a set
     */
    private final Comparator<List<Advertisement>> optimalSetComparator = new Comparator<List<Advertisement>>() {
        @Override
        public int compare(List<Advertisement> o1, List<Advertisement> o2) {
            long o1sum = 0;
            long o2sum = 0;
            int o1dur = 0;
            int o2dur = 0;
            int o1hits = 0;
            int o2hits = 0;

            for (Advertisement a : o1) {
                o1sum += a.getInitialAmount();
                o1dur += a.getDuration();
                o1hits += a.getHits();
            }
            for (Advertisement a : o2) {
                o2sum += a.getInitialAmount();
                o2dur += a.getDuration();
                o2hits += a.getHits();
            }

            if (o1sum < o2sum) {
                return 1;
            } else if (o1sum > o2sum) {
                return -1;
            } else {
                if (o1dur < o2dur) return 1;
                else if (o1dur > o2dur) return -1;
                else {
                    return o1hits - o2hits;
                }
            }
        }
    };

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    /**
     * Searches for optimal set of advertisements videos, using {@link #processTheAdvertisements()} and prints optimal set of advertisement videos,
     * using {@link #printOptimalAdvertisements(List)}
     *
     * @throws NoVideoAvailableException when no videos in a storage
     */
    public void processVideos() {
        if (storage.list().isEmpty()) {
            throw new NoVideoAvailableException();
        }
        printOptimalAdvertisements(processTheAdvertisements());
    }

    /**
     * Provides main algorithm of searching for optimal advertisement set of videos.
     * Recursively checks all the combinations, removing the ones with 0 hits left and with the duration higher then expected in {@link #timeSeconds}.
     * Adds all the possible combinations to the {@link #mainList} and sorts it, using {@link #optimalSetComparator}.
     * Finds up the best set of advertisements, among possible combinations in {@link #mainList} and sorts videos, using {@link #bestVideosComparator}
     *
     * @return List<Advertisement> bestAdvertisement the most optimal set of advertisement videos
     */
    private List<Advertisement> processTheAdvertisements() {
        List<Advertisement> advertisements = new ArrayList<>();

        for (Advertisement a : storage.list()) {// filters advertisements with hits and with appropriate duration
            if (a.getHits() > 0 && a.getDuration() <= timeSeconds) {
                advertisements.add(a);
            }
        }
        createOptimalVideoLists(advertisements);
        Collections.sort(mainList, optimalSetComparator);//sorting lists to get the best

        List<Advertisement> bestAdvertisement = mainList.get(0); // getting 1 optimal list of advertisements
        Collections.sort(bestAdvertisement, bestVideosComparator); // sorting it as per it's value

        return bestAdvertisement;

    }

    /**
     * Checks recursively all possible advertisement combinations
     *
     * @param list
     */
    private void createOptimalVideoLists(List<Advertisement> list) {
        if (!list.isEmpty()) {
            checkAdvertisementAndAdd(list);
        }
        for (int i = 0; i < list.size(); i++) {
            List<Advertisement> newList = new ArrayList<>(list);
            newList.remove(i);
            createOptimalVideoLists(newList);
        }

    }

    /**
     * Adds advertisement combinations to {@link #mainList}, which summary time is in tolerance
     *
     * @param list
     */
    private void checkAdvertisementAndAdd(List<Advertisement> list) {
        long sumTime = 0;
        for (Advertisement a : list) {
            sumTime += a.getDuration();
        }
        if (sumTime <= timeSeconds && !mainList.contains(list)) {
            mainList.add(list);
        }
        //checking if sum of times is < planned time and add to mainList
    }

    /**
     * Prints best advertisements to console in a specific format "<name of advertisement> is displaying...<amount per one hit><amount per one second * 1000>"
     *
     * @param list list of the most optimal advertisements
     */
    private void printOptimalAdvertisements(List<Advertisement> list) {
        for (Advertisement a : list) {
            a.revalidate();//reducing the number of hits for each shown video

            long valuePerHit = a.getAmountPerOneDisplaying();
            long valuePerSec = Math.round(((double) a.getAmountPerOneDisplaying() / a.getDuration()) * 1000);

            String adv = "%s is displaying...%d, %d\n";
            System.out.printf(adv, a.getName(), valuePerHit, valuePerSec);
        }
    }


}
