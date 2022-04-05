package g54895.atl.stibride.repository;

import g54895.atl.stibride.dto.Dto;
import g54895.atl.stibride.exception.RepositoryException;
import java.util.List;

/**
 * Repository pattern to manage a resource of the application: a file, a
 * database, a web service.
 *
 * @author jlc
 * @param <K> key of an element.
 * @param <T> an element.
 */
public interface Repository<K, T extends Dto<K>> {

    /**
     * Returns all the elements of the repository.
     *
     * @return all the elements of the repository.
     *
     * @throws RepositoryException if the repository can't access to the
     * elements.
     */
    List<T> getAll() throws RepositoryException;

    /**
     * Return the element of the repository with the specific key.
     *
     * @param key key of the element.
     *
     * @return the element of the repository with the specific key.
     * @throws RepositoryException if the repository can't access to the
     * element.
     */
    T get(K key) throws RepositoryException;

    T get(String string) throws RepositoryException;

    /**
     * Returns true if the element exist in the repository and false otherwise.
     * An element is found by this key.
     *
     * @param key key of the element.
     * @return true if the element exist in the repository and false otherwise.
     * @throws RepositoryException if the repository can't access to the
     * element.
     */
    boolean contains(K key) throws RepositoryException;

    boolean contains(String stationName) throws RepositoryException;

}
