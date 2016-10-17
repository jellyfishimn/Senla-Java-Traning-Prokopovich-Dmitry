package com.training.senla.facade.impl;

import com.training.senla.enums.RoomStatus;
import com.training.senla.facade.Facade;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RegistrationModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;
import com.training.senla.service.GuestModelService;
import com.training.senla.service.RegistrationModelService;
import com.training.senla.service.RoomModelService;
import com.training.senla.service.ServiceModelService;
import com.training.senla.util.io.exporter.Exporter;
import com.training.senla.util.io.importer.Importer;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public class FacadeImpl implements Facade{

    private GuestModelService guestModelService;
    private RoomModelService roomModelService;
    private RegistrationModelService registrationModelService;
    private ServiceModelService serviceModelService;

    private Importer importer;
    private Exporter exporter;

    @Override
    public GuestModel getGuest(int id) {
        return guestModelService.getGuest(id);
    }

    @Override
    public void setGuest(GuestModel guest) {
        guestModelService.setGuest(guest);
    }

    @Override
    public RoomModel getRoom(int id) {
        return roomModelService.getRoom(id);
    }

    @Override
    public void setRoom(RoomModel room) {
        roomModelService.setRoom(room);
    }

    @Override
    public RegistrationModel getRegistration(int id) {
        return registrationModelService.getRegistration(id);
    }

    @Override
    public ServiceModel getService(int id) {
        return serviceModelService.getService(id);
    }

    @Override
    public void setService(ServiceModel service) {
        serviceModelService.setService(service);
    }

    @Override
    public List<RoomModel> getAllRooms() {
        return roomModelService.getAll();
    }

    @Override
    public List<RoomModel> getAllFreeRooms() {
        return roomModelService.getAll(RoomStatus.FREE);
    }

    @Override
    public List<GuestModel> getGuestsRoom() {
        return guestModelService.getAll();
    }

    @Override
    public int getCountFreeRooms() {
        return roomModelService.getCountFreeRooms();
    }

    @Override
    public int getCountGuests() {
        return guestModelService.getCount();
    }

    @Override
    public List<RoomModel> getReleasedRoomsInFuture(LocalDate date) {
        return roomModelService.getReleasedInFuture(date);
    }

    @Override
    public double getSumPaymentRoom(GuestModel guestModel, RoomModel roomModel) {
        return guestModelService.getSumByRoom(roomModel, guestModel);
    }

    @Override
    public List<ServiceModel> getGuestServices(GuestModel guestModel) {
        return guestModelService.getServicesByPrice(guestModel);
    }

    @Override
    public List<Double> getPricesService() {
        return null;
    }

    @Override
    public List<Double> getPricesRoom() {
        return null;
    }

    @Override
    public void addGuest(GuestModel guestModel, RoomModel roomModel) {
        roomModelService.addGuest(guestModel, roomModel);
    }

    @Override
    public void evictGuest(GuestModel guestModel) {
        roomModelService.evictGuest(guestModel);
    }

    @Override
    public void setStatusRoom(RoomModel room) {
        room.setStatus(RoomStatus.MAINTAINED);
        roomModelService.update(room);
    }

    @Override
    public void changeServicePrice(ServiceModel serviceModel, double price) {
        serviceModel.setPrice(price);
        serviceModelService.update(serviceModel);
    }

    @Override
    public void changeRoomPrice(RoomModel roomModel, double price) {
        roomModel.setPrice(price);
        roomModelService.update(roomModel);
    }

    @Override
    public List<GuestModel> importGuests() {
        return importer.importGuests();
    }

    @Override
    public List<RegistrationModel> importRegistrations() {
        return importer.importRegistrations();
    }

    @Override
    public List<RoomModel> importRooms() {
        return importer.importRooms();
    }

    @Override
    public List<ServiceModel> importServices() {
        return importer.importServices();
    }

    @Override
    public void exportGuests(List<GuestModel> guests) {
        exporter.exportGuests(guests);
    }

    @Override
    public void exportRegistrations(List<RegistrationModel> registrations) {
        exporter.exportRegistrations(registrations);
    }

    @Override
    public void exportRooms(List<RoomModel> rooms) {
        exporter.exportRooms(rooms);
    }

    @Override
    public void exportServices(List<ServiceModel> services) {
        exporter.exportServices(services);
    }
}
