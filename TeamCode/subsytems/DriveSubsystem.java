package org.firstinspires.ftc.teamcode.subsytems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.ImuOrientationOnRobot;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

/**
 * This is the most basic Mecanum subsystem you can have, and provides simple methods to drive, stop,
 * and get some information about the position of the wheels.
 */
public class DriveSubsystem extends SubsystemBase {
    final DcMotor leftFrontDrive;
    final DcMotor leftRearDrive;
    final DcMotor rightFrontDrive;
    final DcMotor rightRearDrive;
    final IMU imu;

    double  headingOffset = 0;

    Telemetry telemetry;

    public DriveSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        leftFrontDrive = hardwareMap.get(DcMotor.class, "flm");
        leftRearDrive = hardwareMap.get(DcMotor.class, "rlm");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "frm");
        rightRearDrive = hardwareMap.get(DcMotor.class, "rrm");

        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        leftRearDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        rightRearDrive.setDirection(DcMotor.Direction.REVERSE);

        leftFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRearDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRearDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        rightFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightRearDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRearDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftRearDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRearDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                        RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD
                )
        );
        imu.initialize(parameters);

        this.telemetry = telemetry;
    }

    // Return our robot's IMU heading, in radians.
    public double getHeading() {
        return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS) - headingOffset;
    }

    public double getHeadingDegrees()
    {
        telemetry.addData("heading", (imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES) - headingOffset));

        return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES) - headingOffset;
    }

    public void resetAngle() {
        headingOffset = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
    }

    // We'll just use the position of one wheel
    public int getCurrentPosition() {
        telemetry.addData("right front", rightFrontDrive.getCurrentPosition());
        return rightFrontDrive.getCurrentPosition();
    }

    /**
     * Drive using gamepad inputs. This includes compensating for imperfect strafing, and adjusting
     * inputs based on stick directions. Better versions would include field-centric driving,
     * deadbands, and more.
     */
    public void driveTeleop(double leftSickY, double leftStickX, double rightStickX, boolean halfSpeed) {
        double y = leftSickY; // Remember, Y stick value is reversed
        double x = -leftStickX;
        double rx = rightStickX;

        double botHeading = -getHeading();

        // Rotate the movement direction counter to the bot's rotation
        double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
        double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

        rotX = rotX * 1.1;  // Counteract imperfect strafing

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
        double frontLeftPower = (rotY + rotX + rx) / denominator;
        double backLeftPower = (rotY - rotX + rx) / denominator;
        double frontRightPower = (rotY - rotX - rx) / denominator;
        double backRightPower = (rotY + rotX - rx) / denominator;

        if (halfSpeed) {
            frontLeftPower /= 2.0;
            backLeftPower /= 2.0;
            frontRightPower /= 2.0;
            backRightPower /= 2.0;
        }

        leftFrontDrive.setPower(frontLeftPower);
        leftRearDrive.setPower(backLeftPower);
        rightFrontDrive.setPower(frontRightPower);
        rightRearDrive.setPower(backRightPower);
    }

    public void setPower(double power) {
        leftFrontDrive.setPower(power);
        leftRearDrive.setPower(power);
        rightFrontDrive.setPower(power);
        rightRearDrive.setPower(power);
    }

    public void setTurnPower(double power) {
        leftFrontDrive.setPower(power);
        leftRearDrive.setPower(power);
        rightFrontDrive.setPower(-power);
        rightRearDrive.setPower(-power);
    }

    public void stop()
    {
        setPower(0);
    }

    @Override
    public void periodic() {
        telemetry.addData("Bot Heading", Math.toDegrees(getHeading()));
        telemetry.update();
    }
}