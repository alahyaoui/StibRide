package g54895.atl.stibride.dto;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jlc
 */
public class StopsDto extends Dto<Integer> {

    private String stationName;
    private String lines;

    public StopsDto(Integer key) {
        super(key);
    }

    public StopsDto(Integer key, String stationName, String lines) {
        super(key);
        this.stationName = stationName;
        this.lines = lines;
    }

    public String getLines() {
        return lines;
    }

    public String getStationName() {
        return stationName;
    }

}
