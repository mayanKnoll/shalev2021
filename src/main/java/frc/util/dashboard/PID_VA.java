package frc.util.dashboard;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.util.PID.Gains;

/**
 * @author Matan Steinmetz
 */
public class PID_VA {
    ShuffleboardTab tab;

    public PID_VA(String ganinsName, SuperShuffleBoardTab tab_, Gains gains, int startColumnIndex, int startRowIndex) {
        this.tab = tab_.getTab();

        NetworkTableEntry setPIDValues = tab.add("Send PID " + ganinsName, false)
                .withWidget(BuiltInWidgets.kToggleSwitch).withPosition(startColumnIndex, startRowIndex).getEntry();
        NetworkTableEntry setVAValues = tab.add("Send VA " + ganinsName, false).withWidget(BuiltInWidgets.kToggleSwitch)
                .withPosition(startColumnIndex + 1, startRowIndex).getEntry();

        NetworkTableEntry Kp = tab.add("Kp " + ganinsName, gains.kp).withPosition(startColumnIndex, startRowIndex + 1)
                .getEntry();
        Kp.addListener(event -> {
            if (setPIDValues.getBoolean(false)) {
                gains.kp = event.value.getDouble();
            }
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        NetworkTableEntry Ki = tab.add("Ki " + ganinsName, gains.ki).withPosition(startColumnIndex, startRowIndex + 2)
                .getEntry();
        Ki.addListener(event -> {
            if (setPIDValues.getBoolean(false)) {
                gains.ki = event.value.getDouble();
            }
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        NetworkTableEntry Kd = tab.add("Kd " + ganinsName, gains.kd).withPosition(startColumnIndex, startRowIndex + 3)
                .getEntry();
        Kd.addListener(event -> {
            if (setPIDValues.getBoolean(false)) {
                gains.kd = event.value.getDouble();
            }
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        NetworkTableEntry Kv = tab.add("Kv " + ganinsName, gains.kv)
                .withPosition(startColumnIndex + 1, startRowIndex + 1).getEntry();
        Kv.addListener(event -> {
            if (setVAValues.getBoolean(false)) {
                gains.kv = event.value.getDouble();
            }
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        NetworkTableEntry Ka = tab.add("Ka " + ganinsName, gains.ka)
                .withPosition(startColumnIndex + 1, startRowIndex + 2).getEntry();
        Ka.addListener(event -> {
            if (setVAValues.getBoolean(false)) {
                gains.ka = event.value.getDouble();
            }
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);
    }
}