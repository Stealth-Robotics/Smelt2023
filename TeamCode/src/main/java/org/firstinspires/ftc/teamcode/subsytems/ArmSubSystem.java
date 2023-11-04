package org.firstinspires.ftc.teamcode.subsytems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * This is the most basic Mecanum subsystem you can have, and provides simple methods to drive, stop,
 * and get some information about the position of the wheels.
 */
public class ArmSubSystem extends SubsystemBase {
    final int MAX_TICKS = 8200;
    final int MIN_WRIST_TICKS = 1500;
    final int WRIST_NEAR_BOTTOM_TICKS = 200;
    final int MIN_TICKS = 0;
    final private DcMotor armShoulderDrive;

    Telemetry telemetry;


    public ArmSubSystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        armShoulderDrive = hardwareMap.get(DcMotor.class, "Arm Base");
        armShoulderDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armShoulderDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armShoulderDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        armShoulderDrive.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    public void setPower(double power, boolean isWristUp) {
        int p = armShoulderDrive.getCurrentPosition();
        telemetry.addData("Arm position", armShoulderDrive.getCurrentPosition());
        telemetry.addData("Arm power", power);

        // Going down?
        if (power < 0) {
            if (p <= MIN_TICKS) {
                power = 0;
            }

            if (p <= MIN_WRIST_TICKS && !isWristUp) {
                power = 0;
            }
        }

        // Going up?
        if (power > 0) {
            if (p >= MAX_TICKS) {
                power = 0;
            }

            if (p <= WRIST_NEAR_BOTTOM_TICKS && !isWristUp) {
                power = 0;
            }
        }
        armShoulderDrive.setPower(power);
    }

    @Override
    public void periodic() {
    }

} 