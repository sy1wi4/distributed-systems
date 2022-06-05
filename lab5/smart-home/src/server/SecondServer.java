package server;

import SmartHome.Color;
import com.zeroc.Ice.Identity;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;

public class SecondServer {
    public static void main(String[] args) {

        try (com.zeroc.Ice.Communicator communicator = Util.initialize(args)) {

            ObjectAdapter adapter = communicator.createObjectAdapter("SmartHomeAdapter2"); // configuration in config file

            BulbI mothersRoomBulbServant = new BulbI("mothers room", Color.BurdeLove);
            PrinterI grayScalePrinterServant = new ColorPrinter("spare office");

            adapter.add(mothersRoomBulbServant, new Identity("mothersRoomBulb", "Bulb"));
            adapter.add(grayScalePrinterServant, new Identity("spareOfficeGrayScalePrinter", "Printer"));

            adapter.activate();

            communicator.waitForShutdown();
        }
    }
}
