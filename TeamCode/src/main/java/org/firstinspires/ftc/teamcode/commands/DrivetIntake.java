package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsytems.IntakeSubsystem;

import java.util.function.DoubleSupplier;

public class DrivetIntake extends CommandBase {

    private final IntakeSubsystem intakeSubsystem;
    private double power;
    private int count = 0;
    public DrivetIntake(IntakeSubsystem intakeSubsystem, double power)
    {
        this.intakeSubsystem = intakeSubsystem;
        this.power = power;
        addRequirements(intakeSubsystem);
    }

    @Override
    public void execute() {
        count++;
        intakeSubsystem.setPower(this.power);
    }

    @Override
    public boolean isFinished() {
        if(count > 50)
        {
            intakeSubsystem.setPower(0);
            return true;
        }

        return false;
    }
}

