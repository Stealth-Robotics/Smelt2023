package org.firstinspires.ftc.teamcode.subsytems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
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

    private boolean runPID = true;

    Telemetry telemetry;

    PIDFController armController;

    private final DigitalChannel limitSwitch;
    private final DigitalChannel limitSwitch1;

    public enum ArmPosition {

        INTAKE(0),
        FLAT(1600),
        UP(4000),
        SCORE(5500);

        private final int value;

        private ArmPosition(int position){
            this.value = position;
        }
        public int getValue() {
            return value;
        }
        }


    public ArmSubSystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        armShoulderDrive = hardwareMap.get(DcMotor.class, "Arm Base");
        armShoulderDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armShoulderDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armShoulderDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        armShoulderDrive.setDirection(DcMotorSimple.Direction.REVERSE);

        armController = new PIDFController(0.02, 0, 0.0000, 0);

        limitSwitch = hardwareMap.get(DigitalChannel.class, "ArmLimitSwitch");
        limitSwitch1 = hardwareMap.get(DigitalChannel.class, "ArmLimitSwitch1");

    }

    public void setRunPID(boolean newValue)
    {
        runPID = newValue;
    }

    public boolean getRunPID()
    {
        return runPID;
    }

    public boolean getLimitSwitch(){return limitSwitch.getState();}

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

    public void reset()
    {
        armShoulderDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armShoulderDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void periodic() {
        if(runPID)
        {
            double calc = armController.calculate(getCurrentPosition());
            setRawPower(calc);
        }

        telemetry.addData("Arm Encoder", getCurrentPosition());
        telemetry.addData("ArmLimitSwitch", limitSwitch.getState());
        telemetry.addData("ArmLimitSwitch1", limitSwitch1.getState());
        telemetry.update();
    }

} 