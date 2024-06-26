package com.example.al9ani.services;

import com.example.al9ani.entities.Etablissement;
import com.example.al9ani.entities.User;
import com.example.al9ani.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EtablissementService {

    private static EtablissementService instance;
    PreparedStatement preparedStatement;
    Connection connection;

    public EtablissementService() {
        connection = DatabaseConnection.getInstance().getConnection();
    }

    public static EtablissementService getInstance() {
        if (instance == null) {
            instance = new EtablissementService();
        }
        return instance;
    }

    public List<Etablissement> getAll() {
        List<Etablissement> listEtablissement = new ArrayList<>();
        try {

            String query = "SELECT * FROM `etablissements` AS x "
                    + "RIGHT JOIN `user` AS y1 ON x.user_id = y1.id "
                    + "WHERE  x.user_id = y1.id  ";
            preparedStatement = connection.prepareStatement(query);


            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Etablissement etablissement = new Etablissement();
                etablissement.setId(resultSet.getInt("id"));
                etablissement.setNom(resultSet.getString("nom"));
                etablissement.setCategorie(resultSet.getString("categorie"));
                etablissement.setAdresse(resultSet.getString("adress"));
                etablissement.setEmail(resultSet.getString("email"));
                etablissement.setTelephone(resultSet.getInt("tel"));

                User user = new User();
                user.setId(resultSet.getInt("y1.id"));
                user.setName(resultSet.getString("y1.name"));
                etablissement.setUser(user);

                listEtablissement.add(etablissement);
            }
        } catch (SQLException exception) {
            System.out.println("Error (getAll) etablissement : " + exception.getMessage());
        }
        return listEtablissement;
    }

    public List<User> getAllUsers() {
        List<User> listUsers = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `user`");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                listUsers.add(user);
            }
        } catch (SQLException exception) {
            System.out.println("Error (getAll) users : " + exception.getMessage());
        }
        return listUsers;
    }


    public boolean add(Etablissement etablissement) {


        String request = "INSERT INTO `etablissements`(`nom`, `categorie`, `adress`, `mail`, `tel`, `user_id`, `favoris` ) VALUES(?, ?, ?, ?, ?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(request);

            preparedStatement.setString(1, etablissement.getNom());
            preparedStatement.setString(2, etablissement.getCategorie());
            preparedStatement.setString(3, etablissement.getAdresse());
            preparedStatement.setString(4, etablissement.getEmail());
            preparedStatement.setInt(5, etablissement.getTelephone());
            preparedStatement.setInt(6, etablissement.getUser().getId());
            preparedStatement.setInt(7, 0);


            preparedStatement.executeUpdate();
            System.out.println("Etablissement added");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (add) etablissement : " + exception.getMessage());
        }
        return false;
    }

    public boolean edit(Etablissement etablissement) {

        String request = "UPDATE `etablissements` SET `nom` = ?, `categorie` = ?, `adress` = ?, `mail` = ?, `tel` = ?, `user_id` = ? WHERE `id` = ?";

        try {
            preparedStatement = connection.prepareStatement(request);

            preparedStatement.setString(1, etablissement.getNom());
            preparedStatement.setString(2, etablissement.getCategorie());
            preparedStatement.setString(3, etablissement.getAdresse());
            preparedStatement.setString(4, etablissement.getEmail());
            preparedStatement.setInt(5, etablissement.getTelephone());

            preparedStatement.setInt(6, etablissement.getUser().getId());

            preparedStatement.setInt(7, etablissement.getId());

            preparedStatement.executeUpdate();
            System.out.println("Etablissement edited");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (edit) etablissement : " + exception.getMessage());
        }
        return false;
    }

    public boolean delete(int id) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM `etablissements` WHERE `id`=?");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Etablissement deleted");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (delete) etablissement : " + exception.getMessage());
        }
        return false;
    }
}
