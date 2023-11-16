package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsytems.ArmSubSystem;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class DefaultArmCommand extends CommandBase {

    private final ArmSubSystem armSubSystem;
    private final DoubleSupplier joystickSupplier;

    private final BooleanSupplier isWristUp;

    private boolean setOnce = true;

    public DefaultArmCommand(ArmSubSystem armSubSystem, DoubleSupplier joystickSupplier, BooleanSupplier isWristUp)
    {
        this.armSubSystem = armSubSystem;

        this.joystickSupplier = joystickSupplier;

        this.isWristUp = isWristUp;

        addRequirements(armSubSystem);
    }

    @Override
    public void execute() {

        double joyStickValue = joystickSupplier.getAsDouble();

        if(Math.abs(joyStickValue) > 0.05)
        {
            armSubSystem.setRawPower(joyStickValue);
            armSubSystem.setRunPID(false);
            setOnce = false;
        } else if (!setOnce) {
            setOnce = true;
            armSubSystem.setRunPID(true);
            armSubSystem.setSetPoint(armSubSystem.getCurrentPosition());
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
