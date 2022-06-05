package server;

import SmartHome.*;
import com.zeroc.Ice.Current;

public class BulbI extends DeviceI implements Bulb {
    Color color;
    int brightnessPercentage;

    public BulbI(String location) {
        this(location, Color.White);
    }

    public BulbI(String location, Color color) {
        super(location);
        this.color = color;
    }

    @Override
    public void changeColor(Color color, Current current) throws WrongColorException {
        if (this.color == color) throw new WrongColorException("Bulb color is already set to " + color.toString());
        this.color = color;
        System.out.println("Bulb color changed to " + color + "!");
    }

    @Override
    public void dim(Current current) throws BrightnessPercentageOutOfBoundException {
        changeBrightness(-5, current);
    }

    @Override
    public void brighten(Current current) throws BrightnessPercentageOutOfBoundException {
        changeBrightness(5, current);
    }

    @Override
    public void changeBrightness(int percentagePoints, Current current) throws BrightnessPercentageOutOfBoundException {
        int oldBrightness = brightnessPercentage;
        int newBrightness = brightnessPercentage + percentagePoints;

        if (newBrightness < 0 || newBrightness > 100) throw new BrightnessPercentageOutOfBoundException(newBrightness);
        brightnessPercentage = newBrightness;
        System.out.println("Bulb brightness changed from " + oldBrightness + " to " + brightnessPercentage);
    }

    @Override
    public Color[] getAllPossibleColors(Current current) {
        return Color.values();
    }

    @Override
    public Info getInfo(Current current) {
        Info info = super.getInfo(current);

        info.moreInfo.put(InfoKey.Brightness, brightnessPercentage + "%");
        info.moreInfo.put(InfoKey.Color, color.name());
        return info;
    }
}
