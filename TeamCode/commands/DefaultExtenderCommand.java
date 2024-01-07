package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsytems.ArmSubSystem;
import org.firstinspires.ftc.teamcode.subsytems.ExtenderSubsystem;

import java.util.function.DoubleSupplier;

public class DefaultExtenderCommand  extends CommandBase {

    ExtenderSubsystem extenderSubsystem;

    DoubleSupplier joystickSupplier;

    public DefaultExtenderCommand(ExtenderSubsystem extenderSubsystem, DoubleSupplier joystickSupplier)
    {
        this.extenderSubsystem = extenderSubsystem;

        this.joystickSupplier = joystickSupplier;

        addRequirements(extenderSubsystem);
    }

    @Override
    public void execute() {
        extenderSubsystem.setPosition(extenderSubsystem.getPosition() + (joystickSupplier.getAsDouble()* 0.005));

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
