package frc.util.dashboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * this is Subsystem base.
 * 
 * @author Matan Steinmetz
 */
public class SuperSystem extends SubsystemBase {
    private String nameSystem;
    protected static List<SuperSystem> allSuperSubsystems = new ArrayList<SuperSystem>();
    private static HashMap<String, SuperSubSystemTab> allTabs = new HashMap<>();

    private final SuperSubSystemTab tab;

    public SuperSystem(String nameSystem) {
        this.nameSystem = nameSystem;
        allSuperSubsystems.add(this);
        if (!allTabs.containsKey(nameSystem)) {
            allTabs.put(nameSystem, new SuperSubSystemTab(nameSystem, this));
        }
        tab = allTabs.get(nameSystem);
    }

    public void subsystemPeriodic() {
    }

    /**
     * This function will send all subsystem data in dashboard.
     * <p>
     */
    public static void periodics() {
        for (final SuperSystem subsystem : allSuperSubsystems) {
            subsystem.subsystemPeriodic();
        }
    }

    @Override
    public void periodic() {
        super.periodic();
    }

    public SuperSubSystemTab getTab() {
        return tab;
    }

    public String getNameSystem() {
        return nameSystem;
    }

}
