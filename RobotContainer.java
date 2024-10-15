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
        // Initialize subsystems
        driveSubsystem = new DriveSubsystem();
        
        // Initialize joystick
        joystick = new Joystick(0);
        
        // Initialize commands
        driveCommand = new DriveCommand(driveSubsystem, joystick);
        
        // Set the default command for the drive subsystem
        driveSubsystem.setDefaultCommand(driveCommand);
    }

    public Command getAutonomousCommand() {
        // Return the command to run in autonomous mode (if any)
        return null; // Replace with your autonomous command if needed
    }
}
