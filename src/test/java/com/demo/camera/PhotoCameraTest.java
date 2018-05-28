package com.demo.camera;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

public class PhotoCameraTest {

    PhotoCamera camera;
    ImageSensor sensor;
    Card card;

    @Before
    public void prepere() {
        sensor = mock(ImageSensor.class);
        card = mock(Card.class);
        camera = new PhotoCamera(sensor, card);
    }

    @Test
    public void turningOnCameraTurnsOnSensor() {
        camera.turnOn();

        Mockito.verify(sensor).turnOn();
    }

    @Test
    public void turningOffCameraTurnsOffSensor() {
        camera.turnOff();

        Mockito.verify(sensor).turnOff();
    }

    @Test
    public void pressingButtonWhenCameraIsOffDoesNothing() {
        camera.turnOff();

        camera.pressButton();

        Mockito.verifyZeroInteractions(card);
    }

    @Test
    public void pressingButtonWhenCameraIsOnCopyingDataFromSensorToCard() {
        camera.turnOn();
        byte[] tablica;

        camera.pressButton();

        tablica = Mockito.verify(sensor).read();
        Mockito.verify(card).write(tablica);

    }

    @Test
    public void ifDataIsCurrentlyBeingWrittenTurningOffCameraDoesNotStopSensorPower() {
        camera.turnOn();
        camera.pressButton();

        camera.turnOff();

        Mockito.verify(sensor, Mockito.times(0)).turnOff();
    }

    @Test
    public void ifDataIsDoneBeingWrittenSensorTurnsOff() {
        camera.turnOn();
        camera.pressButton();

        camera.writeCompleted();

        Mockito.verify(sensor).turnOff();
    }
}