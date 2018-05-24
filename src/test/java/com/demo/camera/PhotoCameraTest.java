package com.demo.camera;

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

public class PhotoCameraTest {

    @Test
    public void turningOnCameraTurnsOnSensor() {
        ImageSensor sensor = mock(ImageSensor.class);
        PhotoCamera camera = new PhotoCamera(sensor);

        camera.turnOn();

        Mockito.verify(sensor).turnOn();
    }

    @Test
    public void turningOffCameraTurnsOffSensor() {
        ImageSensor sensor = mock(ImageSensor.class);
        PhotoCamera camera = new PhotoCamera(sensor);

        camera.turnOff();

        Mockito.verify(sensor).turnOff();
    }

    @Test
    public void pressingButtonWhenCameraIsOffDoesNothing() {
        ImageSensor sensor = mock(ImageSensor.class);
        Card card = mock(Card.class);
        PhotoCamera camera = new PhotoCamera(sensor);
        camera.turnOff();

        camera.pressButton();

        Mockito.verifyZeroInteractions(card);
    }

    @Test
    public void pressingButtonWhenCameraIsOnCopyingDataFromSensorToCard() {
        ImageSensor sensor = mock(ImageSensor.class);
        Card card = mock(Card.class);
        PhotoCamera camera = new PhotoCamera(sensor);
        camera.turnOn();
        byte[] tablica;

        camera.pressButton();

        tablica = Mockito.verify(sensor).read();
        Mockito.verify(card).write(tablica);
    }
}