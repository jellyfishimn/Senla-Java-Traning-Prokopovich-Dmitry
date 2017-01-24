package com.training.senla.dao.impl;

import com.training.senla.model.GuestModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;
import com.training.senla.dao.GuestModelDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.training.senla.util.ParserResultSet.parseGuest;
import static com.training.senla.util.ParserResultSet.parseService;


/**
 * Created by prokop on 13.10.16.
 */
public class GuestModelDaoImpl implements GuestModelDao {

    private String TOKEN = "'";
    private String DELIMITER = ", ";

    public GuestModelDaoImpl() {
    }


    @Override
    public List<ServiceModel> getServicesByPrice(Connection connection, GuestModel guestModel) {
        Statement statement = null;
        List<ServiceModel> services = new ArrayList<>();
        try {
            statement = connection.createStatement();
            StringBuilder builder = new StringBuilder();
            builder.append("SELECT * FROM service WHERE guestId = ");
            builder.append(TOKEN);
            builder.append(String.valueOf(guestModel.getId()));
            builder.append(TOKEN);
            builder.append(" ORDER BY price");
            ResultSet set = statement.executeQuery(builder.toString());
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
    public List<ServiceModel> getServicesByDate(Connection connection, GuestModel guestModel) {
        Statement statement = null;
        List<ServiceModel> services = new ArrayList<>();
        try {
            statement = connection.createStatement();
            StringBuilder builder = new StringBuilder();
            builder.append("SELECT * FROM service WHERE guestId = ");
            builder.append(TOKEN);
            builder.append(String.valueOf(guestModel.getId()));
            builder.append(TOKEN);
            builder.append(" ORDER BY finalDate");
            ResultSet set = statement.executeQuery(builder.toString());
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
    public List<GuestModel> getSortedByName(Connection connection) {
        Statement statement = null;
        List<GuestModel> guests = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM guest ORDER BY name");
            while (set.next()) {
                guests.add(parseGuest(statement, set));
            }
            set.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return guests;
    }

    @Override
    public List<GuestModel> getSortedByFinalDate(Connection connection) {
        Statement statement = null;
        List<GuestModel> guests = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM guest JOIN registration ON guest.id = registration.guestId ORDER BY registration.finalDate ");
            while (set.next()) {
                guests.add(parseGuest(statement, set));
            }
            set.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return guests;
    }

    @SuppressWarnings("deprecation")
    @Override
    public double getSumByRoom(Connection connection, RoomModel roomModel, GuestModel guestModel) {
        Statement statement = null;
        double sum = 0;
        try {
            statement = connection.createStatement();
            StringBuilder builder = new StringBuilder();
            builder.append("SELECT startDate, finalDate FROM registration WHERE guestId = ");
            builder.append(TOKEN);
            builder.append(String.valueOf(guestModel.getId()));
            builder.append(TOKEN);
            builder.append(" AND roomId = ");
            builder.append(TOKEN);
            builder.append(String.valueOf(roomModel.getId()));
            builder.append(TOKEN);
            ResultSet set = statement.executeQuery(builder.toString());
            int count = 0;
            while (set.next()) {
                count = set.getDate(2).toLocalDate().getDayOfYear() - set.getDate(1).toLocalDate().getDayOfYear();
            }
            sum = count * roomModel.getPrice();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sum;
    }

    @Override
    public int getCount(Connection connection) {
        Statement statement = null;
        int count = 0;
        try {
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT COUNT(id) FROM guest");
            while (set.next()) {
                count = set.getInt(1);
            }        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    @Override
    public boolean update(Connection connection, GuestModel entity) {
        PreparedStatement preparedStatement = null;
        boolean status = false;
        try {
            StringBuilder builder = new StringBuilder();
            builder.append("UPDATE guest SET name = ?, roomId = ? WHERE id = ");
            builder.append(TOKEN);
            builder.append(String.valueOf(entity.getId()));
            builder.append(TOKEN);
            preparedStatement = connection.prepareStatement(builder.toString());
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getRoomModel().getId());
            int count = preparedStatement.executeUpdate();
            status = (count > 0);
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public GuestModel get(Connection connection, int id) {
        Statement statement = null;
        GuestModel guest = null;
        try {
            statement = connection.createStatement();
            StringBuilder builder = new StringBuilder();
            builder.append("SELECT * FROM guest WHERE id = ");
            builder.append(TOKEN);
            builder.append(String.valueOf(id));
            builder.append(TOKEN);
            ResultSet set = statement.executeQuery(builder.toString());
            guest = parseGuest(statement, set);
            set.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guest;
    }

    @Override
    public void set(Connection connection, GuestModel entity) {
        Statement statement = null;
        try {
            StringBuilder builder = new StringBuilder();
            statement = connection.createStatement();
            builder.append("INSERT guest(name) VALUES (");
            builder.append(TOKEN);
            builder.append(entity.getName());
            builder.append(TOKEN);
            if(entity.getRoomModel() != null) {
                builder.append(DELIMITER);
                builder.append(entity.getRoomModel().getId());
            }
            builder.append(")");
            statement.executeUpdate(builder.toString());

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<GuestModel> getAll(Connection connection) {
        Statement statement = null;
        List<GuestModel> guests = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM guest ORDER BY id");
            while (set.next()) {
                guests.add(parseGuest(statement, set));
            }
            set.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return guests;
    }

    @Override
    public void delete(Connection connection, GuestModel entity) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            StringBuilder builder = new StringBuilder();
            builder.append("DELETE * FROM guest WHERE id = ");
            builder.append(TOKEN);
            builder.append(String.valueOf(entity.getId()));
            builder.append(TOKEN);
            statement.executeUpdate(builder.toString());
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
