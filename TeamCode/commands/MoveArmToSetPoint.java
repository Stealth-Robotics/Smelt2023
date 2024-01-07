package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsytems.ArmSubSystem;


/*
90 degrees - 1600
180 degrees - 4000
Score - 5500
Hang - 8000
 */
public class MoveArmToSetPoint extends CommandBase {
    private final ArmSubSystem armSubSystem;
    private final double setPoint;
    public MoveArmToSetPoint (double setPoint, ArmSubSystem armSubSystem){
        this.armSubSystem = armSubSystem;
        this.setPoint = setPoint;
        addRequirements(armSubSystem);
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
