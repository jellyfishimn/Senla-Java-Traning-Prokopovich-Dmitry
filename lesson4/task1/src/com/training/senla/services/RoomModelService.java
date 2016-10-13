package com.training.senla.services;

import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.RoomsSection;
import com.training.senla.models.RoomModel;

import java.util.Date;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public interface RoomModelService {
    void setRoom(RoomModel roomModel);
    RoomModel getRoom(int id);
    void update(RoomModel roomModel);
    void delete(RoomModel roomModel);
    List<RoomModel> getAll();
    List<RoomModel> getSortedByPrice();
    List<RoomModel> getSortedByCapacity();
    List<RoomModel> getSortedByRating();
    List<RoomModel> getAll(RoomStatus status);
    List<RoomModel> getSortedByPrice(RoomStatus status);
    List<RoomModel> getSortedByCapacity(RoomStatus status);
    List<RoomModel> getSortedByRating(RoomStatus status);
    int getCountFreeRooms();
    List<RoomModel> getReleasedInFuture(Date date);
    List<RoomModel> getLatestGuests(int count);
    List<Integer> getPriceBySection(RoomsSection section);

}
