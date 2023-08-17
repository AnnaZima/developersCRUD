package com.anya.crudapp.repository.jdbc;

import com.anya.crudapp.model.Developer;
import com.anya.crudapp.model.Skill;
import com.anya.crudapp.model.Specialty;
import com.anya.crudapp.model.Status;
import com.anya.crudapp.repository.DeveloperRepository;
import com.anya.crudapp.utils.JdbcUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcDevRepositoryImpl implements DeveloperRepository {

    private Developer mapResultSetTODeveloper(ResultSet res) {
        Developer developer = new Developer();
        Specialty specialty = new Specialty();
        Skill skill = new Skill();
        List<Skill> skillList = new ArrayList<>();

        try {
            while (res.next()) {
                int specID = res.getInt("specialty_id");
                String specName = res.getString("spec_name");
                specialty.setId(specID);
                specialty.setSpecName(specName);

                skill.setId(res.getInt("skills_id"));
                skill.setName(res.getString("skill_name"));
                skillList.add(skill);

                developer.setId(res.getInt("developers.id"));
                developer.setFirstName(res.getString("first_name"));
                developer.setLastName(res.getString("last_name"));
                developer.setStatus(Status.valueOf(res.getString("status")));
                developer.setSpecialty(specialty);
            }

            developer.setSkills(skillList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developer;
    }

    private void saveDeveloperSkillsToDatabase(List<Skill> skills, Integer developerID) {
        String request = "INSERT INTO skill_set (developer_id, skill_id) VALUES (?, ?);";
        try (PreparedStatement preparedStatement = JdbcUtils.preparedStatement(request)) {
            skills.stream().map(Skill::getId).peek(i -> {
                try {
                    preparedStatement.setInt(1, developerID);
                    preparedStatement.setInt(2, i);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Developer get(Integer id) {
        String request = "SELECT developers.id, first_name, last_name, status, specialty.id AS specialty_id, \n" +
                "spec_name, skills.id AS skills_id, skill_name  \n" +
                "FROM developers \n" +
                "LEFT JOIN specialty ON specialty_id = specialty.id\n" +
                "LEFT JOIN skill_set ON developers.id = developer_id\n" +
                "LEFT JOIN skills ON skill_id = skills.id\n" +
                "WHERE developers.id = ?;";

        try (PreparedStatement preparedStatement = JdbcUtils.preparedStatement(request)) {
            preparedStatement.setInt(1, id);
            ResultSet res = preparedStatement.executeQuery();
            return mapResultSetTODeveloper(res);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Developer update(Developer developer) {
        int id = developer.getId();
        String name = developer.getFirstName();
        String lastName = developer.getLastName();
        Specialty specialty = developer.getSpecialty();
        int specID = specialty.getId();
        List<Skill> skills = developer.getSkills();
        saveDeveloperSkillsToDatabase(skills, id);

        String request = "UPDATE demobase.developers \n" +
                "SET first_name = ?, last_name = ?, specialty_id = ?\n" +
                "WHERE developers.id = ?;";
        try (PreparedStatement preparedStatement = JdbcUtils.preparedStatement(request)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, specID);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return developer;
    }

    @Override
    public void delete(Integer id) {
        String request = "UPDATE demobase.developers SET status = 'DELETE' WHERE id = ?;";
        try (PreparedStatement statement = JdbcUtils.preparedStatement(request)) {
            statement.setInt(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Developer insert(Developer dev) {
        int devID = 0;
        String name = dev.getFirstName();
        String lastname = dev.getLastName();
        List<Skill> skills = dev.getSkills();
        Integer specialtyId = dev.getSpecialty().getId();

        String request = "INSERT INTO Developer (first_name, last_name, specialty_id) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = JdbcUtils.preparedStatementWithKeys(request)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastname);
            preparedStatement.setInt(3, specialtyId);
            preparedStatement.executeUpdate();
            ResultSet generatedKey = preparedStatement.getGeneratedKeys();

            while (generatedKey.next()) {
                devID = generatedKey.getInt(1);
            }
            saveDeveloperSkillsToDatabase(skills, devID);
            dev.setId(devID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dev;
    }

    @Override
    public Integer searchByName(String name) {
        int id = 0;
        String[] byName = name.split(" ");
        String request = "SELECT id FROM demobase.developers WHERE name = ? AND last_name = ?;";
        try (PreparedStatement preparedStatement = JdbcUtils.preparedStatement(request)) {
            preparedStatement.setString(1, byName[0]);
            preparedStatement.setString(2, byName[1]);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("developers.id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    @Override
    public List<Developer> getAll() {
        List<Developer> developerList = new ArrayList<>();
        String request = "SELECT * FROM demobase.developers;";
        try (PreparedStatement preparedStatement = JdbcUtils.preparedStatement(request)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                developerList.add(mapResultSetTODeveloper(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developerList;
    }
}
