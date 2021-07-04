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
  public CollectCommandGroup(KickerSystem kickerSystem, CartridgeSystem cartridgeSystem, CollectionSystem collectionSystem, Boolean whenMove) {

    super();
    
    double side = whenMove ? -1 : 1; 
    addCommands(new SetOutputCommand(cartridgeSystem, Constants.CARTRIDGE_SPEED * side), 
    new SetOutputCommand(collectionSystem, Constants.COLLECT_SPEED * side));
  }
}