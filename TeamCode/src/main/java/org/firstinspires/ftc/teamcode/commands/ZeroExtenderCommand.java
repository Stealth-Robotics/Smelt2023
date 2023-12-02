package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsytems.ExtenderSubsystem;

public class ZeroExtenderCommand extends CommandBase {

    private final ExtenderSubsystem extenderSubsystem;
    public ZeroExtenderCommand(ExtenderSubsystem extenderSubsystem){
        this.extenderSubsystem=extenderSubsystem;
        addRequirements(extenderSubsystem);
    }

    @Override
    public void initialize() {
        if(extenderSubsystem.getLimitSwitchAlive())
        {
            extenderSubsystem.setPosition(0.0);
        }
    }

    @Override
    public boolean isFinished() {
        return extenderSubsystem.getLimitSwitch() && extenderSubsystem.getLimitSwitchAlive();
    }
}


