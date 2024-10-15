package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.DriveSubsystem;

public class RobotContainer {
    private final DriveSubsystem driveSubsystem;
    private final Joystick joystick;
    private final DriveCommand driveCommand;

    public RobotContainer() {
        driveSubsystem = new DriveSubsystem();
        
        
        joystick = new Joystick(0);
        

        driveCommand = new DriveCommand(driveSubsystem, joystick);
        

        driveSubsystem.setDefaultCommand(driveCommand);
    }

  
}
