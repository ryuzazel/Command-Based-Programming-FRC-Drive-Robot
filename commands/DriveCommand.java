package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;
import static frc.robot.Constants.*;

public class DriveCommand extends Command {
    private final DriveSubsystem driveSubsystem;
    private final Joystick joystick;
    private boolean a, b, x;

    private double velocity, Mag, LS, RS, px1, py1, TrigAxi, px2, py2, graus;
    private int quad, pov;

    public DriveCommand(DriveSubsystem driveSubsystem, Joystick joystick) {
        this.driveSubsystem = driveSubsystem;
        this.joystick = joystick;
        addRequirements(driveSubsystem);
    }

    @Override
    public void execute() {
      pov = joystick.getPOV();
      a = joystick.getRawButton(1);
      b = joystick.getRawButton(2);
      x = joystick.getRawButton(3);
      TrigAxi = Deadzone(joystick.getRawAxis(3) - joystick.getRawAxis(2));
      velocity = getVelocity(a, b, x);
        px2 = Deadzone(-joystick.getRawAxis(4));
        py2 = Deadzone(joystick.getRawAxis(5));
      px1 = Deadzone(joystick.getRawAxis(0));
      py1 = Deadzone(-joystick.getRawAxis(1));
      if (pov == -1) {
        if (calculateMag(px2, py2) != 0) {
          calculateMotorSpeeds(px2, py2);
        }else{
         calculateMotorSpeeds(px1, py1);
        }
      }else if (pov != -1) {
        POVS();
      }
      if (px1 + px2 == 0 && py1 + py2 == 0 && pov == -1) {
        RS = TrigAxi* velocity;
        LS = TrigAxi * velocity;
      }
        driveSubsystem.setMotorSpeeds(LS, RS);
    }


    private double Deadzone(double valor){
      if (Math.abs(valor) < DEADZONE_THRESHOLD) {
        return 0;
      } else{
        return valor;
      }
    }

    private double calculateMotorSpeed(double px, double py, double velocity) {
        double mag = Math.sqrt(px * px + py * py);
        if (mag > 1) mag = 1; // Cap magnitude
        return mag * velocity; // Scale by velocity
    }
    private void calculateMotorSpeeds(double px, double py) {
      double turnratio = Math.abs(px/Mag);
      quad = getquad(px, py);
      Mag = calculateMag(px, py);
      graus =  Math.toDegrees(Math.atan2(px, py * -1));
      if (Mag > 1) {
        Mag = 1;
      }
      switch (quad) {
        case 1:
          LS = Mag;
          RS = Mag - turnratio;
          break;
          case 2:
          RS = Mag;
          LS = Mag - turnratio;
          break;
          case 3:
          LS = -Mag;
          RS = -Mag + turnratio;
          break;
          case 4:
          RS = -Mag;
          LS = -Mag + turnratio;
          break;
        default:
        if (px > 0 && py == 0) {
          LS = Mag;
          RS = -Mag;
        }
        if (px < 0 && py == 0) {
          RS = Mag;
          LS = -Mag;
        }
        if (py > 0 && px == 0) {
          LS = RS = Mag;
        }
        if (py < 0 && px == 0) {
          LS = RS = -Mag;
        }
          break;
      }
      if (TrigAxi == 0 && Mag != 0) {
        TrigAxi =1;
      }
      LS *= TrigAxi * velocity;
      RS *= TrigAxi * velocity;
      }
    private double calculateMag(double px, double py) {
      return Math.sqrt(px * px + py * py);
    }
    private int getquad(double px, double py) {
      if (px > 0 && py > 0) return 1;
      if (px < 0 && py > 0) return 2;
      if (px > 0 && py < 0) return 4;
      if (px < 0 && py < 0) return 3;
      return 0;
    }
    private void POVS() {
    
      switch (pov) {
          case 0:  
              LS = 1;
              RS = 1;
              break;
          case 45: 
              LS = 1;
              RS = 0; 
              break;
          case 90:  
              LS = 1;
              RS = -1;
              break;
          case 135: 
              LS = 0; 
              RS = -1;
              break;
          case 180:
              LS = -1;
              RS = -1;
              break;
          case 225: 
              LS = -1;
              RS = 0;
              break;
          case 270: 
              LS = -1;
              RS = 1;
              break;
          case 315:
              LS = 0; 
              RS = 1;
              break;
          default:  
              LS = 0;
              RS = 0;
              break;
      }
      LS *= 0.5;
      RS *= 0.5;
  }
  private double getVelocity(boolean a, boolean b, boolean x) {
    if (a) return 0.5;
    if (b) return 0.25;
    if (x) return 1;
    return velocity;
  }

}
