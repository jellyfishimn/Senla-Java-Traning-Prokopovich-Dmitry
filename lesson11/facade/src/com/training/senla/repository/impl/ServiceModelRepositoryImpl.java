package com.training.senla.repository.impl;

import com.training.senla.enums.ServicesSection;
import com.training.senla.model.ServiceModel;
import com.training.senla.repository.ServiceModelRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.training.senla.util.ParserResultSet.parseService;

/**
 * Created by prokop on 13.10.16.
 */
public class ServiceModelRepositoryImpl implements ServiceModelRepository {

    private String TOKEN = "'";
    private String DELIMITER = ", ";

    public ServiceModelRepositoryImpl() {
    }


    @Override
    public boolean update(Connection connection, ServiceModel entity) {
        PreparedStatement preparedStatement = null;
        boolean status = false;
        try {
            StringBuilder builder = new StringBuilder();
            builder.append("UPDATE service SET name = ?, price = ?, section = ?, startDate = ?, finalDate = ? WHERE id = ");
            builder.append(TOKEN);
            builder.append(String.valueOf(entity.getId()));
            builder.append(TOKEN);
            preparedStatement = connection.prepareStatement(builder.toString());
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setDouble(2, entity.getPrice());
            preparedStatement.setString(3, entity.getSection().toString());
            preparedStatement.setString(4, entity.getStartDate().toString());
            preparedStatement.setString(5, entity.getFinalDate().toString());
            int count = preparedStatement.executeUpdate();
            status = (count > 0);
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public ServiceModel get(Connection connection, int id) {
        Statement statement = null;
        ServiceModel service = null;
        try {
            statement = connection.createStatement();
            StringBuilder builder = new StringBuilder();
            builder.append("SELECT * FROM service WHERE id = ");
            builder.append(TOKEN);
            builder.append(String.valueOf(id));
            builder.append(TOKEN);
            ResultSet set = statement.executeQuery(builder.toString());
            service = parseService(statement, set);
            set.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return service;
    }

    @Override
    public void set(Connection connection, ServiceModel entity) {
        Statement statement = null;
        try {
            StringBuilder builder = new StringBuilder();
            statement = connection.createStatement();
            builder.append("INSERT service(name, price, section, startDate, finalDate) VALUES (");
            builder.append(TOKEN);
            builder.append(entity.getName());
            builder.append(TOKEN);
            builder.append(DELIMITER);
            builder.append(entity.getPrice());
            builder.append(DELIMITER);
            builder.append(TOKEN);
            builder.append(entity.getSection().toString().toLowerCase());
            builder.append(TOKEN);
            builder.append(DELIMITER);
            builder.append(TOKEN);
            builder.append(entity.getStartDate().toString());
            builder.append(TOKEN);
            builder.append(DELIMITER);
            builder.append(TOKEN);
            builder.append(entity.getFinalDate().toString());
            builder.append(TOKEN);
            builder.append(")");
            statement.executeUpdate(builder.toString());

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ServiceModel> getAll(Connection connection) {
        Statement statement = null;
        List<ServiceModel> services = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM service ORDER BY id");
            while (set.next()) {
                services.add(parseService(statement, set));
            }
            set.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return services;
    }

    @Override
    public void delete(Connection connection, ServiceModel entity) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            StringBuilder builder = new StringBuilder();
            builder.append("DELETE * FROM service WHERE id = ");
            builder.append(TOKEN);
            builder.append(String.valueOf(entity.getId()));
            builder.append(TOKEN);
            statement.executeUpdate(builder.toString());
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ServiceModel> getSortedByPrice(Connection connection) {
        Statement statement = null;
        List<ServiceModel> services = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM service ORDER BY price");
            while (set.next()) {
                services.add(parseService(statement, set));
            }
            set.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return services;
    }

    @Override
    public List<ServiceModel> getSortedByDate(Connection connection, Date date) {
        Statement statement = null;
        List<ServiceModel> services = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM service ORDER BY finalDate");
            while (set.next()) {
                services.add(parseService(statement, set));
            }
            set.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return services;
    }

    @Override
    public List<Double> getPriceBySection(Connection connection, ServicesSection section) {
        Statement statement = null;
        List<Double> prices = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT price FROM service ORDER BY section");
            while (set.next()) {
                prices.add(set.getDouble(1));
            }
            set.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prices;
    }
}
