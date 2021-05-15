/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g54895.atl.stibride.dto;

import java.util.Objects;

/**
 *
 * @author Ayoub
 */
public class FavoriteRoutesDto extends Dto<Integer> {

    private final String origin;
    private final String destination;

    public FavoriteRoutesDto(Integer key, String origin, String destination) {
        super(key);
        this.origin = origin;
        this.destination = destination;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return origin + " -> " + destination;
    }

}
