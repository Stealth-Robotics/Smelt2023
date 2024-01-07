package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsytems.DriveSubsystem;

public class DriveForTicksCommand extends CommandBase {

    private DriveSubsystem driveSubsystem;

    private int setpoint;

    public DriveForTicksCommand(DriveSubsystem driveSubsystem, int ticks) {
        this.setpoint=driveSubsystem.getCurrentPosition()+ticks;
        this.driveSubsystem=driveSubsystem;
        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        if(setpoint < driveSubsystem.getCurrentPosition())
        {
            driveSubsystem.setPower(0.3);
        }
        else
        {
            driveSubsystem.setPower(-0.3);
        }
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        if (Math.abs(setpoint - driveSubsystem.getCurrentPosition()) < 50)
        {
            driveSubsystem.stop();
            return true;
        }
        return false;
    }
}
