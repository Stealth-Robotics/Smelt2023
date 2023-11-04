package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsytems.ArmSubSystem;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class DefaultArmCommand extends CommandBase {

    private final ArmSubSystem armSubSystem;
    private final DoubleSupplier joystickSupplier;

    private final BooleanSupplier isWristUp;
    public DefaultArmCommand(ArmSubSystem armSubSystem, DoubleSupplier joystickSupplier, BooleanSupplier isWristUp)
    {
        this.armSubSystem = armSubSystem;

        this.joystickSupplier = joystickSupplier;

        this.isWristUp = isWristUp;

        addRequirements(armSubSystem);
    }

    @Override
    public void execute() {
        armSubSystem.setPower(joystickSupplier.getAsDouble(),isWristUp.getAsBoolean());
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
