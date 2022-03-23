/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stibride.model;

import observer.Observer;
import stibride.dto.StationsDto;
import java.util.List;

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
