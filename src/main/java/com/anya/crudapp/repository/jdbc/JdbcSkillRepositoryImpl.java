package com.anya.crudapp.repository.jdbc;

import com.anya.crudapp.model.Skill;
import com.anya.crudapp.repository.SkillRepository;
import com.anya.crudapp.utils.JdbcUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcSkillRepositoryImpl implements SkillRepository {

    private static Skill mapResultSetToSkill(ResultSet resultSet) throws SQLException {
        Skill skill = new Skill();
        skill.setId(resultSet.getInt("id"));
        skill.setName(resultSet.getString("skill_name"));
        return skill;
    }

    @Override
    public Skill get(Integer id) {
        String request = "SELECT skill_name " +
                "FROM demobase.skills" +
                "WHERE id = ?;";
        try (PreparedStatement statement = JdbcUtils.preparedStatement(request)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return mapResultSetToSkill(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Skill update(Skill object) {
        int id = object.getId();
        String name = object.getName();
        String request = "UPDATE skills" +
                "SET skill_name = ?" +
                "WHERE id = ?";
        try (PreparedStatement statement = JdbcUtils.preparedStatement(request)) {
            statement.setString(1, name);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public void delete(Integer id) {
        String request = "DELETE FROM skills WHERE id = ?;";
        try (PreparedStatement preparedStatement = JdbcUtils.preparedStatement(request)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Skill insert(Skill object) {
        int id = 0;
        String skillsName = object.getName();
        String request = "INSERT INTO skills (skill_name) VALUES(?);";
        try (PreparedStatement statement = JdbcUtils.preparedStatementWithKeys(request)) {
            statement.setString(1, skillsName);
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            while (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Skill(id, skillsName);
    }

    @Override
    public Integer searchByName(String name) {
        int resultId = -1;
        String request = "SELECT id FROM demobase.skills WHERE skill_name = ?";
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
    public List<Skill> getAll() {
        String request = "SELECT * FROM demobase.skills;";
        List<Skill> skillsList = new ArrayList<>();
        try (PreparedStatement statement = JdbcUtils.preparedStatement(request);) {
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int id = result.getInt("id");
                String skillName = result.getNString("skill_name");
                Skill skill = new Skill(id, skillName);
                skillsList.add(skill);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skillsList;
    }
}
