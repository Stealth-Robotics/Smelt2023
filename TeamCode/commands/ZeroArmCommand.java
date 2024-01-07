package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsytems.ArmSubSystem;

public class ZeroArmCommand extends CommandBase {

    private final ArmSubSystem armSubSystem;

    public ZeroArmCommand(ArmSubSystem armSubSystem) {
        this.armSubSystem = armSubSystem;
        addRequirements(armSubSystem);
    }

    @Override
    public void initialize() {
        armSubSystem.setRawPower(-0.5);
        armSubSystem.setRunPID(false);
    }

    @Override
    public void end(boolean interrupted) {
        armSubSystem.setRawPower(0);
        armSubSystem.reset();
        armSubSystem.setSetPoint(armSubSystem.getCurrentPosition());
        armSubSystem.setRunPID(true);

    }

    @Override
    public boolean isFinished() {
        return armSubSystem.getLimitSwitch();
    }
}
