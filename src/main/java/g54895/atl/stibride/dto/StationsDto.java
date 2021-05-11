/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g54895.atl.stibride.dto;

/**
 *
 * @author Ayoub
 */
public class StationsDto extends Dto<Integer> {
    
    private String stationName;

    public StationsDto(Integer key) {
        super(key);
    }

    public StationsDto(Integer key, String stationName) {
        super(key);
        this.stationName = stationName;
    }

    public String getStationName() {
        return stationName;
    }
}
