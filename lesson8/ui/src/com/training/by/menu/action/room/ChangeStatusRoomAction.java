package com.training.by.menu.action.room;

import com.training.by.menu.action.Action;
import com.training.by.print.PrintModel;
import com.training.by.reader.Reader;
import com.training.senla.ClassSetting;
import com.training.senla.DataPacket;
import com.training.senla.RequestHandler;
import com.training.senla.model.Room;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by prokop on 26.10.16.
 */
public class ChangeStatusRoomAction implements Action {
    private static final Logger LOG = LogManager.getLogger(ChangeStatusRoomAction.class);

    @Override
    public void execute(RequestHandler requestHandler) {
        if(!ClassSetting.getProps().isBlockStatus()) {
            PrintModel.printMessage("Can't change the status of the rooms!");
        }else {
            int roomId = Reader.getInt("Input room ID: ");
            try {
                DataPacket packet = new DataPacket("getRoom", roomId);
                Room room = (Room) requestHandler.sendRequest(packet);
                if (room == null) {
                    PrintModel.printMessage("Room not found.");
                } else {
                    packet = new DataPacket("changeRoomStatus", null);
                    requestHandler.sendRequest(packet);
                    PrintModel.printMessage("Room is maintained.");
                }
            } catch (Exception e) {
                LOG.error(e.getMessage());
            }
        }
    }
}