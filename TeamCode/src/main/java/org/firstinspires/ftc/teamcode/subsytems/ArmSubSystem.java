package org.firstinspires.ftc.teamcode.subsytems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
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

    private boolean runPID = false;

    Telemetry telemetry;

    PIDFController armController;


    public ArmSubSystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        armShoulderDrive = hardwareMap.get(DcMotor.class, "Arm Base");
        armShoulderDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armShoulderDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armShoulderDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        armShoulderDrive.setDirection(DcMotorSimple.Direction.REVERSE);

        armController = new PIDFController(0.02, 0, 0.0000, 0);

    }

    public void setRunPID(boolean newValue)
    {
        runPID = newValue;
    }

    public boolean getRunPID()
    {
        return runPID;
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

    public void setRawPower(double rawPower) {
        armShoulderDrive.setPower(rawPower);
    }

    public void setSetPoint(double setPoint) {
        armController.setSetPoint(setPoint);
    }

    public int getCurrentPosition() {
        return armShoulderDrive.getCurrentPosition();
    }

    public boolean atSetPoint(){return armController.atSetPoint();}

    @Override
    public void periodic() {
        if(runPID)
        {
            double calc = armController.calculate(getCurrentPosition());
            setRawPower(calc);
        }
    }

} 