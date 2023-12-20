package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsytems.ArmSubSystem;
import org.firstinspires.ftc.teamcode.subsytems.LauchyGuySubsystem;
import org.firstinspires.ftc.teamcode.subsytems.ScoreSubsystem;

public class PlaneLaunchCommand extends CommandBase {

    public LauchyGuySubsystem lauchyGuySubsystem;

    public PlaneLaunchCommand(LauchyGuySubsystem lauchyGuySubsystem) {
        addRequirements(lauchyGuySubsystem);

        addCommands(
                new InstantCommand(lauchyGuySubsystem::flyBeFree),
                new WaitCommand(500))
    ;}




    private void addCommands(InstantCommand instantCommand, WaitCommand waitCommand) {
    }


}
