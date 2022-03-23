/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stibride.repository;

import stibride.dto.FavoriteRoutesDto;
import stibride.exception.RepositoryException;
import stibride.jdbc.FavoriteRoutesDao;
import java.util.List;

/**
 *
 * @author Ayoub
 */
public class FavoriteRoutesRepository implements Repository<Integer, FavoriteRoutesDto> {

    private final FavoriteRoutesDao dao;

    public FavoriteRoutesRepository() throws RepositoryException {
        dao = FavoriteRoutesDao.getInstance();
    }

    @Override
    public List<FavoriteRoutesDto> getAll() throws RepositoryException {
        return dao.selectAll();
    }

    @Override
    public FavoriteRoutesDto get(Integer key) throws RepositoryException {
        FavoriteRoutesDto result = dao.select(key);
        return result;
    }

    public FavoriteRoutesDto get(String origin, String destination) throws RepositoryException {
        FavoriteRoutesDto result = dao.select(origin, destination);
        return result;
    }

    public FavoriteRoutesDto get(FavoriteRoutesDto item) throws RepositoryException {
        FavoriteRoutesDto result = dao.select(item);
        return result;
    }

    public Integer add(FavoriteRoutesDto item) throws RepositoryException {
        Integer key = item.getKey();
        if (!contains(item)) {
            key = dao.insert(item);
        }
        return key;
    }

    public void update(FavoriteRoutesDto item) throws RepositoryException {
        //Integer key = item.getKey();
        //if (contains(key) && !contains(item)) {
            dao.update(item);
        //}
    }

    public void delete(Integer key) throws RepositoryException {
        dao.delete(key);
    }

    public void delete(FavoriteRoutesDto item) throws RepositoryException {
        dao.delete(item);
    }

    @Override
    public boolean contains(Integer key) throws RepositoryException {
        FavoriteRoutesDto results = dao.select(key);
        return results != null;
    }

    public boolean contains(FavoriteRoutesDto item) throws RepositoryException {
        FavoriteRoutesDto results = dao.select(item);
        return results != null;
    }

}

/*
public Integer add2(FavoriteRoutesDto item) throws RepositoryException {
        Integer key = item.getKey();
        if (contains(item)) {
            dao.update(item);
        } else {
            key = dao.insert(item);
        }
        return key;
    }*/
