package org.firstinspires.ftc.teamcode.subsytems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * This is the most basic Mecanum subsystem you can have, and provides simple methods to drive, stop,
 * and get some information about the position of the wheels.
 */
public class ArmSubSystem extends SubsystemBase {
    final private DcMotor armShoulderDrive;


    public ArmSubSystem(HardwareMap hardwareMap) {

        armShoulderDrive = hardwareMap.get(DcMotor.class, "Arm Base");

        armShoulderDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public void setPower(double power ) {
        armShoulderDrive.setPower(power);
    }
} 