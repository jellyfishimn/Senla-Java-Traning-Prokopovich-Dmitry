package com.training.senla.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public class GuestModel{
    private int guestId;
    private String name;
    private LocalDate startDate;
    private LocalDate finalDate;
    private RoomModel roomModel;
    private List<ServiceModel> serviceModelList;

    public GuestModel() {

    }

    public GuestModel(String name, LocalDate startDate, List<ServiceModel> serviceModelList) {
        this.name = name;
        this.startDate = startDate;
        this.finalDate = null;
        this.roomModel = null;
        this.serviceModelList = serviceModelList;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(LocalDate finalDate) {
        this.finalDate = finalDate;
    }

    public RoomModel getRoomModel() {
        return roomModel;
    }

    public void setRoomModel(RoomModel roomModel) {
        this.roomModel = roomModel;
    }

    public List<ServiceModel> getServiceModelList() {
        return serviceModelList;
    }

    public void setServiceModelList(List<ServiceModel> serviceModelList) {
        this.serviceModelList = serviceModelList;
    }
}