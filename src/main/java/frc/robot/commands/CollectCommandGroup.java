
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.CartridgeSystem;
import frc.robot.subsystems.CollectionSystem;
import frc.robot.subsystems.KickerSystem;

public class CollectCommandGroup extends ParallelCommandGroup {
  KickerSystem kickerSystem;
  CartridgeSystem cartridgeSystem;
  CollectionSystem collectionSystem;
  public CollectCommandGroup(KickerSystem kickerSystem, CartridgeSystem cartridgeSystem, CollectionSystem collectionSystem) {

    
    super(new SetOutputCommand(kickerSystem, Constants.KICKER_SPEED) , 
          //new SetOutputCommand(cartridgeSystem, Constants.CARTRIDGE_SPEED), 
          new SetOutputCommand(collectionSystem, Constants.COLLECT_SPEED)
          );
  }
}
