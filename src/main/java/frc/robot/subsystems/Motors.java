package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Motors extends SubsystemBase{
  
    private CANSparkMax baseMotor;
    private CANSparkMax pivotMotor;

    public Motors()
    {
        baseMotor = new CANSparkMax(Constants.ARM_BASE, MotorType.kBrushless);
        pivotMotor = new CANSparkMax(Constants.ARM_PIVOT, MotorType.kBrushless);
    }

    public CANSparkMax getBaseMotor()
    {
        return baseMotor;
    }

    public CANSparkMax getPivotMotor()
    {
        return pivotMotor;
    }
}
