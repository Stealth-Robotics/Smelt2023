package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsytems.ArmSubSystem;

public class MoveArmToSetPoint extends CommandBase {
    private final ArmSubSystem armSubSystem;
    private final double setPoint;
    public MoveArmToSetPoint (double setPoint, ArmSubSystem armSubSystem){
        this.armSubSystem = armSubSystem;
        this.setPoint = setPoint;
    }

    @Override
    public void initialize() {
        armSubSystem.setSetPoint(setPoint);
    }

    @Override
    public boolean isFinished() {
        return armSubSystem.atSetPoint();
    }
}
