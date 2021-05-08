package g54895.atl.stibride.jdbc;

import g54895.atl.stibride.dto.StopsDto;
import g54895.atl.stibride.exception.RepositoryException;
import g54895.atl.stibride.repository.Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jlc
 */
public class StopsDao implements Dao<Integer, StopsDto> {

    private Connection connexion;

    private StopsDao() throws RepositoryException {
        connexion = DBManager.getInstance().getConnection();

    }

    public static StopsDao getInstance() throws RepositoryException {
        return StopsDaoHolder.getInstance();
    }

    @Override
    public List<StopsDto> selectAll() throws RepositoryException {
        String sql = "SELECT id,firstname,lastname FROM STUDENTS";
        List<StopsDto> dtos = new ArrayList<>();
        try ( Statement stmt = connexion.createStatement();  ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                StopsDto dto = new StopsDto(rs.getInt(1), rs.getString(2), rs.getString(3));
                dtos.add(dto);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dtos;
    }

    @Override
    public StopsDto select(Integer key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("Aucune clé donnée en paramètre");
        }
        String sql = "SELECT id,lastname,firstname FROM STUDENTS WHERE  id = ?";
        StopsDto dto = null;
        try ( PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setInt(1, key);
            ResultSet rs = pstmt.executeQuery();

            int count = 0;
            while (rs.next()) {
                dto = new StopsDto(rs.getInt(1), rs.getString(2), rs.getString(3));
                count++;
            }
            if (count > 1) {
                throw new RepositoryException("Record pas unique " + key);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dto;
    }

    private static class StopsDaoHolder {

        private static StopsDao getInstance() throws RepositoryException {
            return new StopsDao();
        }
    }
}
