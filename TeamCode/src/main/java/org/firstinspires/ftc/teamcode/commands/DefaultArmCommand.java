package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsytems.ArmSubSystem;

import java.util.function.DoubleSupplier;

public class DefaultArmCommand extends CommandBase {

    private final ArmSubSystem armSubSystem;
    private final DoubleSupplier joystickSupplier;

    public DefaultArmCommand(ArmSubSystem armSubSystem, DoubleSupplier joystickSupplier)
    {
        this.armSubSystem = armSubSystem;

        this.joystickSupplier = joystickSupplier;

        addRequirements(armSubSystem);
    }

    @Override
    public void execute() {
        armSubSystem.setPower(joystickSupplier.getAsDouble());
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
