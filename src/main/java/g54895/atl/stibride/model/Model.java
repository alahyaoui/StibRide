/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g54895.atl.stibride.model;

import g54895.atl.stibride.dto.StationsDto;
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
}
