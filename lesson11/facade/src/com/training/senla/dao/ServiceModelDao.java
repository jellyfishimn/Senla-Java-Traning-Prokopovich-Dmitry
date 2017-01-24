package com.training.senla.dao;

import com.training.senla.enums.ServicesSection;
import com.training.senla.model.ServiceModel;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public interface ServiceModelDao extends BaseModelDao<ServiceModel> {
    List<ServiceModel> getSortedByPrice(Connection connection);

    List<ServiceModel> getSortedByDate(Connection connection, Date date);

    List<Double> getPriceBySection(Connection connection, ServicesSection section);
}
