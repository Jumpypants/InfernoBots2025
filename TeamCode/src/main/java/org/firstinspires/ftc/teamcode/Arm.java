package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Arm {
  Motor rotationMotor;
  Motor extensionMotor;

  double rotationConst;
  double extensionConst;

  double rotationUpperLimit;
  double rotationLowerLimit;
  double extensionUpperLimit;
  double extensionLowerLimit;

  private double rotationSetPoint = 0;
  private double extensionSetPoint = 0;

  public Arm(
          Motor rotationMotor,
          Motor extensionMotor,
          double rotationConst,
          double extensionConst,
          double rotationUpperLimit,
          double rotationLowerLimit,
          double extensionUpperLimit,
          double extensionLowerLimit
  ) {
    this.rotationMotor = rotationMotor;
    this.rotationMotor.setRunMode(Motor.RunMode.VelocityControl);

    this.extensionMotor = extensionMotor;
    this.extensionMotor.setRunMode(Motor.RunMode.VelocityControl);

    this.rotationConst = rotationConst;
    this.extensionConst = extensionConst;

    this.rotationUpperLimit = rotationUpperLimit;
    this.rotationLowerLimit = rotationLowerLimit;
    this.extensionUpperLimit = extensionUpperLimit;
    this.extensionLowerLimit = extensionLowerLimit;
  }

  public void drive(Gamepad gamepad2) {
    driveRotation(gamepad2);
    driveExtension(gamepad2);
  }

  private void driveRotation(Gamepad gamepad2) {
    double leftStickY = gamepad2.left_stick_y;

    rotationSetPoint += leftStickY * rotationConst;

    if (rotationSetPoint > rotationUpperLimit) {
      rotationSetPoint = rotationUpperLimit;
    } else if(rotationSetPoint < rotationLowerLimit) {
      rotationSetPoint = rotationLowerLimit;
    }

    PIDFController pidfController = new PIDFController(0.05, 0, 0, 0.01);
    pidfController.setSetpoint(rotationSetPoint);

    rotationMotor.set(pidfController.calculate(rotationMotor.getCurrentPosition()));
  }

  private void driveExtension(Gamepad gamepad2) {
    double rightStickY = gamepad2.right_stick_y;

    extensionSetPoint += rightStickY * extensionConst;

    if (extensionSetPoint > extensionUpperLimit) {
      extensionSetPoint = extensionUpperLimit;
    } else if(extensionSetPoint < extensionLowerLimit) {
      extensionSetPoint = extensionLowerLimit;
    }

    PIDController pidController = new PIDController(0.05, 0, 0);
    pidController.setSetpoint(extensionSetPoint);

    extensionMotor.set(pidController.calculate(extensionMotor.getCurrentPosition()));

    extensionMotor.set(0.5);
  }
}
