package org.firstinspires.ftc.teamcode.subsytems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * This is the most basic Mecanum subsystem you can have, and provides simple methods to drive, stop,
 * and get some information about the position of the wheels.
 */
public class IntakeSubsystem extends SubsystemBase {
    final private DcMotor intakeMotor;

    Telemetry telemetry;

    public IntakeSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        intakeMotor = hardwareMap.get(DcMotor.class, "Intake");
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//        intakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void setPower(double power) {
        intakeMotor.setPower(power);
    }
} 