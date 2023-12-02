package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.controller.PIDFController;

import org.firstinspires.ftc.teamcode.subsytems.DriveSubsystem;

public class TurnWithGyro extends CommandBase {

    DriveSubsystem driveSubsystem;

    final double angle;

    final PIDFController turnController;

    public TurnWithGyro(DriveSubsystem driveSubsystem, double angle)
    {
        this.driveSubsystem = driveSubsystem;
        this.angle = angle;

        turnController = new PIDFController(0, 0, 0,0);

        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {
        turnController.setSetPoint(driveSubsystem.getHeadingDegrees() + angle);
    }

    @Override
    public void execute() {
        driveSubsystem.setTurnPower(turnController.calculate(driveSubsystem.getHeadingDegrees()));
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return turnController.atSetPoint();
    }
}
