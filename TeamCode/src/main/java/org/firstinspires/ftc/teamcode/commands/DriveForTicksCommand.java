package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.controller.PIDFController;

import org.firstinspires.ftc.teamcode.subsytems.DriveSubsystem;

public class DriveForTicksCommand extends CommandBase {

    private DriveSubsystem driveSubsystem;

    private int ticks;

    private PIDFController drivecontroller;

    public DriveForTicksCommand(DriveSubsystem driveSubsystem, int ticks) {
        this.ticks=ticks;
        this.driveSubsystem=driveSubsystem;
        addRequirements(driveSubsystem);
        drivecontroller= new PIDFController(1,0,0,0);
        drivecontroller.setTolerance(5);

    }

    @Override
    public void initialize() {
        drivecontroller.setSetPoint(driveSubsystem.getCurrentPosition() + ticks);
    }

    @Override
    public void execute() {
        double calc = drivecontroller.calculate(driveSubsystem.getCurrentPosition());
        driveSubsystem.setPower(calc);
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return drivecontroller.atSetPoint();
    }
}
