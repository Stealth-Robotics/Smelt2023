package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsytems.ArmSubSystem;
import org.firstinspires.ftc.teamcode.subsytems.IntakeSubsystem;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class DefaultIntakeCommand extends CommandBase {

    private final IntakeSubsystem intakeSubsystem;
    private final DoubleSupplier joystickSupplier;

    public DefaultIntakeCommand(IntakeSubsystem intakeSubsystem, DoubleSupplier joystickSupplier)
    {
        this.intakeSubsystem = intakeSubsystem;

        this.joystickSupplier = joystickSupplier;


        addRequirements(intakeSubsystem);
    }

    @Override
    public void execute() {
        intakeSubsystem.setPower(joystickSupplier.getAsDouble());
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}

