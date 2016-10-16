package com.training.senla;

import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.RoomsSection;
import com.training.senla.enums.ServicesSection;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RegistrationModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;
import com.training.senla.repository.impl.GuestModelRepositoryImpl;
import com.training.senla.repository.impl.RegistrationModelRepositoryImpl;
import com.training.senla.repository.impl.RoomModelRepositoryImpl;
import com.training.senla.service.GuestModelService;
import com.training.senla.service.RoomModelService;
import com.training.senla.service.impl.GuestModelServiceImpl;
import com.training.senla.service.impl.RoomModelServiceImpl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by prokop on 12.10.16.
 */


public class Main {


    public static void main(String[] args) throws IOException {


        LocalDate today = LocalDate.now();
        LocalDate f = LocalDate.of(2021, 6, 6);
        LocalDate future = LocalDate.of(2016, Month.OCTOBER, 20);
        LocalDate future1 = LocalDate.of(2019, Month.AUGUST, 6);
        LocalDate future2 = LocalDate.of(2025, Month.AUGUST, 6);
        RoomModel roomModel = new RoomModel(52.5, 4, RoomsSection.STANDART, 3, new ArrayList<>());
        ServiceModel serviceModel = new ServiceModel("shower", 2.5, ServicesSection.MANDATORY, today, future);
        List<ServiceModel> serviceModelList = new ArrayList<>();
        serviceModelList.add(serviceModel);

        List<RoomModel> roomModels = new ArrayList<>();
        List<RegistrationModel> registrationModels = new ArrayList<>();
        //GUEST
        GuestModel guestModel = new GuestModel("Dima", today, serviceModelList);
        guestModel.setFinalDate(future);
        GuestModel guestModel1 = new GuestModel("Asdfasdf", today, serviceModelList);
        GuestModel guestModel2 = new GuestModel("123123", today, serviceModelList);
        GuestModel guestModel3 = new GuestModel("____!_!_!_", today, serviceModelList);
        List<GuestModel> guests = new ArrayList<>();

        GuestModelService guestModelService = new GuestModelServiceImpl( new GuestModelRepositoryImpl(guests));
        guestModelService.setGuest(guestModel);
        guestModelService.setGuest(guestModel1);

        roomModel.setStatus(RoomStatus.MAINTAINED);

        RoomModelService roomModelService = new RoomModelServiceImpl( new RoomModelRepositoryImpl(roomModels), new GuestModelRepositoryImpl(guests), new RegistrationModelRepositoryImpl(registrationModels));
        roomModelService.setRoom(roomModel);
        roomModelService.addGuest(guestModel, roomModel);
        roomModelService.addGuest(guestModel1, roomModel);

        System.out.println(guestModelService.getSumByRoom(roomModel, guestModel));

        roomModelService.evictGuest(guestModel);
    }
}
