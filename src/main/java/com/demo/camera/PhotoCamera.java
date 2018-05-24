package com.demo.camera;

public class PhotoCamera {
    ImageSensor sensor;
    Card card;

    public PhotoCamera() {

    }

    public PhotoCamera(ImageSensor sensor) {
        this.sensor = sensor;
    }

    public void turnOn() {
        sensor.turnOn();
    }

    public void turnOff() {
        sensor.turnOff();
    }

    public void pressButton() {
        byte[] tabl;
        tabl = sensor.read();
        card.write(tabl);
    }
}

