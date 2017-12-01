package app;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class Application {
    private final static GpioController controller = GpioFactory.getInstance();

    private final static GpioPinInput btnOne = controller.provisionDigitalInputPin(RaspiPin.GPIO_03, "btnOne", PinPullResistance.PULL_DOWN);


    public static void main(String[] args) {
        System.out.println("START GPIOTrigger....");
        btnOne.setShutdownOptions(true);

        btnOne.addListener(new GpioPinListenerDigital() {
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent gpioPinDigitalStateChangeEvent) {
                System.out.println("GPIO CHANGE STATE --> "+ gpioPinDigitalStateChangeEvent.getState());
            }
        });

        boolean i = true;
        while (i){
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        controller.shutdown();
    }
}
