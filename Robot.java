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
    }

    @Override
    public void teleopPeriodic() {
        CommandScheduler.getInstance().run(); 
    }


}
