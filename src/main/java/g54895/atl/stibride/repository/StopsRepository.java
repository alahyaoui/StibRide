package g54895.atl.stibride.repository;

import g54895.atl.stibride.dto.StopsDto;
import g54895.atl.stibride.exception.RepositoryException;
import g54895.atl.stibride.jdbc.StopsDao;
import java.util.List;

/**
 *
 * @author jlc
 */
public class StopsRepository implements Repository<Integer, StopsDto> {

    private final StopsDao dao;

    public StopsRepository() throws RepositoryException {
        dao = StopsDao.getInstance();
    }

    StopsRepository(StopsDao dao) {
        this.dao = dao;
    }

    @Override
    public List<StopsDto> getAll() throws RepositoryException {
        return dao.selectAll();
    }

    @Override
    public StopsDto get(Integer key) throws RepositoryException {
        StopsDto refreshItem = dao.select(key);
        return refreshItem;
    }

    @Override
    public boolean contains(Integer key) throws RepositoryException {
        StopsDto refreshItem = dao.select(key);
        return refreshItem != null;
    }

}
