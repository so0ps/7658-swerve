package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import frc.robot.RobotContainer;
import frc.robot.Constants;

public class Arm extends SubsystemBase{
 
    // Create the SparkMAX object
    private CANSparkMax baseMotor;
    private CANSparkMax pivotMotor;

    Motors motors;

    public Arm()
    {
        motors = RobotContainer.m_motors;

        // Motor Controllers
        baseMotor = motors.getBaseMotor();
        pivotMotor = motors.getPivotMotor();
    }

    public CommandBase raise()
    {
        return this.runOnce(() -> baseMotor.set(Constants.ARM_SPEED));
    }

    public CommandBase lower()
    {
        return this.runOnce(() -> baseMotor.set(-Constants.ARM_SPEED));
    }

    public CommandBase stopBase()
    {
        return this.runOnce(() -> baseMotor.set(0));
    }

    public CommandBase pivotForward()
    {
        return this.runOnce(() -> pivotMotor.set(Constants.PIVOT_SPEED));
    }

    public CommandBase pivotBackward()
    {
        return this.runOnce(() -> pivotMotor.set(-Constants.PIVOT_SPEED));
    }

    public CommandBase stopPivot()
    {
        return this.runOnce(() -> pivotMotor.set(0));
    }

    @Override
    public void periodic()
    {

    }
}