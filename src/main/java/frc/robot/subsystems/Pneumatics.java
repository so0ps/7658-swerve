package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Pneumatics extends SubsystemBase{

    private Compressor compressor;
    private DoubleSolenoid clawSolenoid;

    public Pneumatics()
    {
        compressor = new Compressor(0, PneumaticsModuleType.CTREPCM);
        compressor.enableDigital();

        clawSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.CLAW_SOLENOID_FIRST, Constants.CLAW_SOLENOID_SECOND);
        clawSolenoid.set(Value.kOff);
    }

    // Create a method to toggle the claw
    public CommandBase toggleClaw()
    {
        return this.runOnce(() -> clawSolenoid.toggle());
    }

    public CommandBase clawOpen()
    {
        return this.runOnce(() -> clawSolenoid.set(Value.kForward));
    }

    public CommandBase clawClose()
    {
        return this.runOnce(() -> clawSolenoid.set(Value.kReverse));
    }
}