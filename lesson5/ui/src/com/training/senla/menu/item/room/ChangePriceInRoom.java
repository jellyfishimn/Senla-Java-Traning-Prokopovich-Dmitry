package com.training.senla.menu.item.room;

import com.training.senla.facade.Facade;
import com.training.senla.menu.Item;
import com.training.senla.menu.Menu;
import com.training.senla.model.RoomModel;
import com.training.senla.print.PrintModel;
import com.training.senla.reader.Reader;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by prokop on 26.10.16.
 */
public class ChangePriceInRoom extends Item {
    private static final Logger LOG = LogManager.getLogger(ChangePriceInRoom.class);
    public ChangePriceInRoom(Menu menu, Facade facade) {
        super("Change price in room", menu, facade);
    }

    @Override
    public Menu execute() {
        int roomId = Reader.getInt("Input room ID: ");
        try {
            RoomModel room = facade.getRoom(roomId);
            if(room == null) {
                PrintModel.printMessage("Room not found.");
            }else {
                double value = Reader.getDouble("Input new price for room: ");
                facade.changeRoomPrice(room, value);
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return this.menu;
    }
}
