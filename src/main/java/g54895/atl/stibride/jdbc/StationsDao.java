/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g54895.atl.stibride.jdbc;

import g54895.atl.stibride.dto.StationsDto;
import g54895.atl.stibride.exception.RepositoryException;
import g54895.atl.stibride.repository.Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ayoub
 */
public class StationsDao implements Dao<Integer, StationsDto> {

    private Connection connexion;

    private StationsDao() throws RepositoryException {
        connexion = DBManager.getInstance().getConnection();
    }

    public static StationsDao getInstance() throws RepositoryException {
        return StationsDaoHolder.getInstance();
    }

    @Override
    public List<StationsDto> selectAll() throws RepositoryException {
        String sql = "SELECT id, name FROM STATIONS "
                + "ORDER BY id;";
        List<StationsDto> dtos = new ArrayList<>();
        try ( Statement stmt = connexion.createStatement();  ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                StationsDto dto = new StationsDto(rs.getInt(1), rs.getString(2));
                dtos.add(dto);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dtos;
    }

    @Override
    public StationsDto select(Integer key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("Aucune clé donnée en paramètre");
        }
        String sql = "SELECT id, name FROM STATIONS "
                + "WHERE id = ?;";

        StationsDto dto = null;
        try ( PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setInt(1, key);
            ResultSet rs = pstmt.executeQuery();

            int count = 0;
            while (rs.next()) {
                dto = new StationsDto(rs.getInt(1), rs.getString(2));
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

    @Override
    public StationsDto select(String stationName) throws RepositoryException {
        if (stationName == null || stationName == "") {
            throw new RepositoryException("Aucune clé donnée en paramètre");
        }
        String sql = "SELECT id, name, FROM STATIONS "
                + "WHERE name = ?;";

        StationsDto dto = null;
        try ( PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setString(1, stationName);
            ResultSet rs = pstmt.executeQuery();

            int count = 0;
            while (rs.next()) {
                dto = new StationsDto(rs.getInt(1), rs.getString(2));
                count++;
            }
            if (count > 1) {
                throw new RepositoryException("Record pas unique " + stationName);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dto;
    }
    
    private static class StationsDaoHolder {

        private static StationsDao getInstance() throws RepositoryException {
            return new StationsDao();
        }
    }

}
