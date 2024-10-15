package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
    private RobotContainer robotContainer;

    @Override
    public void robotInit() {
        robotContainer = new RobotContainer();
    }

    @Override
    public void teleopInit() {
        // The default command is automatically scheduled in RobotContainer
    }

    @Override
    public void teleopPeriodic() {
        CommandScheduler.getInstance().run(); // Run the command scheduler
    }

    @Override
    public void autonomousInit() {
        // Start the autonomous command if needed
        // Command autonomousCommand = robotContainer.getAutonomousCommand();
        // if (autonomousCommand != null) {
        //     autonomousCommand.schedule();
        // }
    }
}
