package stibride.dto;

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
