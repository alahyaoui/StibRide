package stibride.model;

import java.util.List;

import observer.Observer;
import stibride.dto.StationsDto;

/**
 *
 * @author ayoub
 */
public interface Model {

    public void search(int idOrigin, int idDestination);

    public void search(String origin, String destination);

    public List<Node> getSearchResult();

    public List<String> getSearchResult2();

    public List<StationsDto> getSearchResult3();

    public Network getNetwork();

    //public void addFavoriteRoute(String origin, String destination);

    //public void deleteFavoriteRoute(String origin, String destination);

    public void addObserver(Observer observer);
}
