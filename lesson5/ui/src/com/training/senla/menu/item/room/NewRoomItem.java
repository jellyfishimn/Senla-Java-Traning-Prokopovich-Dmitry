package com.training.senla.menu.item.room;

import com.training.senla.enums.RoomsSection;
import com.training.senla.facade.Facade;
import com.training.senla.menu.Item;
import com.training.senla.menu.Menu;
import com.training.senla.model.RoomModel;
import com.training.senla.print.PrintModel;
import com.training.senla.reader.Reader;
import com.training.senla.util.validator.Validator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by prokop on 24.10.16.
 */
public class NewRoomItem extends Item{
    private static final Logger LOG = LogManager.getLogger(NewRoomItem.class);

    private Validator validator;

    public NewRoomItem(Menu menu, Facade facade) {
        super("Add new room", menu, facade);
        this.validator = new Validator();
    }

    @Override
    public Menu execute() {
        try {
            double price = Reader.getDouble("Input price: ");
            int capacity = Reader.getInt("Input capacity: ");
            String strSection = Reader.getString("Input room section: ");
            RoomsSection section = validator.RoomSectionValidator(strSection.toUpperCase());
            int rating = Reader.getInt("Input rating: ");
            RoomModel room = new RoomModel(price, capacity, section, rating);
            facade.setRoom(room);
            PrintModel.printMessage("Room created.");
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return this.menu;
    }
}
