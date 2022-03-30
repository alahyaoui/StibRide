package stibride.model;

import java.util.List;

import observer.Observer;
import stibride.dto.FavoriteTripDto;
import stibride.dto.StationsDto;

/**
 *
 * @author Ayoub Lahyaoui
 */
public interface Model {

    public void search(int idOrigin, int idDestination);

    public void search(String origin, String destination);

    public List<StationsDto> getSearchResult();

    public Network getNetwork();

    public List<FavoriteTripDto> getFavoriteTrips();

    public void addFavoriteTrip(String origin, String destination);

    public void updateFavoriteTrip(int key, String origin, String destination);

    public void deleteFavoriteTrip(FavoriteTripDto trip);

    public void addObserver(Observer observer);
}
