package com.demo.camera;

public class PhotoCamera implements WriteListener {
    ImageSensor sensor;
    Card card;
    boolean isOn;
    boolean isWriting;


    public PhotoCamera() {
        isOn = false;
        isWriting = false;
    }

    public PhotoCamera(ImageSensor sensor, Card card) {
        isOn = false;
        isWriting = false;
        this.sensor = sensor;
        this.card = card;

    }

    public void turnOn() {
        isOn = true;
        sensor.turnOn();
    }

    public void turnOff() {
        if (!isWriting) {
            isOn = false;
            sensor.turnOff();
        }
    }

    public void pressButton() {
        if (isOn) {
            isWriting = true;
            byte[] tabl;
            tabl = sensor.read();
            card.write(tabl);
        }
    }

    @Override
    public void writeCompleted() {
        isWriting = false;
        sensor.turnOff();
    }
}

