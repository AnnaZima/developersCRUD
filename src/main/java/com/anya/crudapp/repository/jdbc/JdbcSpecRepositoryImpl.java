package com.anya.crudapp.repository.jdbc;

import com.anya.crudapp.model.Specialty;
import com.anya.crudapp.repository.SpecialtyRepository;
import com.anya.crudapp.utils.JdbcUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcSpecRepositoryImpl extends JdbcUtils implements SpecialtyRepository {

    private static Specialty mapResultSetToSpecialty(ResultSet resultSet) throws SQLException {
        Specialty specialty = new Specialty();
        specialty.setId(resultSet.getInt("id"));
        specialty.setSpecName(resultSet.getString("spec_name"));
        return specialty;
    }

    @Override
    public Specialty get(Integer id) {
        Specialty specialty = new Specialty();
        String request = "SELECT * FROM specialty WHERE id = ?;";
        try (PreparedStatement statement = JdbcUtils.preparedStatement(request);) {
            statement.setInt(1, id);
            ResultSet output = statement.executeQuery();
            while (output.next()) {
                specialty = mapResultSetToSpecialty(output);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return specialty;
    }

    @Override
    public Specialty update(Specialty object) {
        String request = "UPDATE specialty SET spec_name = ? WHERE id = ?";
        try (PreparedStatement statement = JdbcUtils.preparedStatement(request)) {
            statement.setString(1, object.getSpecName());
            statement.setInt(2, object.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public Specialty delete(Integer id) {
        Specialty specialty = get(id);
        String request = "DELETE FROM specialty WHERE id = ?;";
        try (PreparedStatement statement = JdbcUtils.preparedStatement(request)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
      return specialty;
    }

    @Override
    public Specialty insert(Specialty object) {
        int id = 0;
        String request = "INSERT INTO specialty (spec_name) VALUES (?);";
        try (PreparedStatement preparedStatement = JdbcUtils.preparedStatementWithKeys(request)) {
            preparedStatement.setString(1, object.getSpecName());
            preparedStatement.executeUpdate();
            ResultSet resultId = preparedStatement.getGeneratedKeys();

            while (resultId.next()) {
                id = resultId.getInt(1);
                object.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public Integer searchByName(String name) {
        int resultId = -1;
        String request = "SELECT id FROM specialty WHERE spec_name LIKE ?";
        try (PreparedStatement statement = JdbcUtils.preparedStatement(request)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                resultId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultId;
    }

    @Override
    public List<Specialty> getAll() {
        List<Specialty> specialties = new ArrayList<>();
        String request = "SELECT * FROM specialty";
        try(PreparedStatement preparedStatement = JdbcUtils.preparedStatement(request)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("spec_name");
                Specialty specialty = new Specialty(name);
                specialty.setId(id);
                specialties.add(specialty);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return specialties;
    }
}
