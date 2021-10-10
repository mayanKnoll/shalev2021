package frc.robot.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.Pitch_go_to_position;
import frc.robot.commands.SetOutputCommand;
import frc.robot.commands.ShootingCommandGroup;
import frc.robot.commands.TimeCommand;
import frc.robot.commands.TurnCommand;
import frc.robot.subsystems.CartridgeSystem;
import frc.robot.subsystems.CollectionSystem;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.KickerSystem;
import frc.robot.subsystems.PitchSystem;
import frc.robot.subsystems.ShootSystem;
import frc.util.vision.Limelight;


// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class RightAutoCommand extends SequentialCommandGroup {
  /** Creates a new AutoShootCommandGroup. */
  public RightAutoCommand(ShootSystem shootSystem, CollectionSystem collectionSystem,DriveSystem driveSystem,Limelight limelight ,CartridgeSystem cartridgeSystem, KickerSystem kickerSystem, PitchSystem pitchSystem) {
    // Add your commands in the addCommands() call, e.g.
    //
    // addCommands(new FooCommand(), new BarCommand());
    
    addCommands(parallel(deadline(new TimeCommand(0.2), new TurnCommand(driveSystem, limelight, 0.3))
    ,deadline(new TimeCommand(1), new SetOutputCommand(shootSystem, 0.4))),
      deadline(new TimeCommand(7), new ShootingCommandGroup(limelight, driveSystem, kickerSystem, cartridgeSystem, shootSystem, pitchSystem))
      ,deadline(new TimeCommand(0.75), new SetOutputCommand(shootSystem, 0.4), 
      new SetOutputCommand(collectionSystem, Constants.COLLECT_SPEED),new SetOutputCommand(cartridgeSystem, -1))
      ,deadline(new TimeCommand(3), new ShootingCommandGroup(limelight, driveSystem, kickerSystem, cartridgeSystem, shootSystem, pitchSystem))
      ,deadline(new TimeCommand(1), new AutoDriveCommand(driveSystem, 0 , 0.5))
      ,new Pitch_go_to_position(pitchSystem, -100)
    );
  }
}