
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.CartridgeSystem;
import frc.robot.subsystems.CollectionSystem;
import frc.robot.subsystems.KickerSystem;
import frc.robot.commands.SetOutputCommand;

public class CollectCommandGroup extends SequentialCommandGroup {
  /**
   * Creates a new CollectCommandGroup.
   */
  public CollectCommandGroup() {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(new KickToTouchCommand(new KickerSystem(), 0.5), 
          new SetOutputCommand(new CartridgeSystem(), 0.5), 
          new SetOutputCommand(new CollectionSystem(), 0.5));
  }
}
