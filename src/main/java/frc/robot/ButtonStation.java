package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class ButtonStation {
    
    // Declares ButtonStation
    Joystick station;

    // Initializes all buttons
    JoystickButton redOne;
    JoystickButton redTwo;
    JoystickButton greenOne;
    JoystickButton greenTwo;
    JoystickButton blueOne;
    JoystickButton blueTwo;
    JoystickButton yellowOne;
    JoystickButton yellowTwo;
    JoystickButton white;

    // Creates ButtonStation object
    public ButtonStation()
    {

        // Initializes ButtonStation
        station = new Joystick(Constants.JOYSTICK.BUTTON_STATION_PORT);

        // Initializes Buttons
        redOne = new JoystickButton(station, 1);
        redTwo = new JoystickButton(station, 2);
        greenOne = new JoystickButton(station, 3);
        greenTwo = new JoystickButton(station, 4);
        blueOne = new JoystickButton(station, 5);
        blueTwo = new JoystickButton(station, 6);
        yellowOne = new JoystickButton(station, 7);
        yellowTwo = new JoystickButton(station, 8);
        white = new JoystickButton(station, 9);
    }
}
