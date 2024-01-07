package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.controller.PIDFController;

import org.firstinspires.ftc.teamcode.subsytems.DriveSubsystem;

public class TurnWithGyro extends CommandBase {

    DriveSubsystem driveSubsystem;

    final double angle;


    public TurnWithGyro(DriveSubsystem driveSubsystem, double angle)
    {
        this.driveSubsystem = driveSubsystem;
        this.angle = angle;

        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        if(angle > driveSubsystem.getHeadingDegrees())
        {
            driveSubsystem.setTurnPower(.3);
        }
        else
        {
            driveSubsystem.setTurnPower(-.3);
        }
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        if (Math.abs(angle - driveSubsystem.getHeadingDegrees()) < 2)
        {
            driveSubsystem.stop();
            return true;
        }
        return false;
    }
}
