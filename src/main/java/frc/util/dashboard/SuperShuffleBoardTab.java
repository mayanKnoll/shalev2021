/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.util.dashboard;

import java.util.HashMap;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.WidgetType;

/**
 * Add your docs here.
 */
public class SuperShuffleBoardTab {
    protected final ShuffleboardTab tab;
    protected final HashMap<String, NetworkTableEntry> keys = new HashMap<>();

    public SuperShuffleBoardTab(final String name) {
        tab = Shuffleboard.getTab(name);
    }

    /**
     * This function will put your data in Shuffleboard
     * 
     * @param key
     * @param value
     */
    public void putInDashboard(final String key, final Object value) {
        if (keys.containsKey(key)) {
            keys.get(key).setValue(value);
        } else {
            keys.put(key, tab.add(key, value).getEntry());
        }
    }

    /**
     * This function will put your data in a list view in Shuffleboard
     * 
     * @param listName
     * @param key
     * @param value
     */
    public void putInDashboard(final String listName, final String key, final Object value) {
        if (keys.containsKey(key)) {
            keys.get(key).setValue(value);
        } else {
            keys.put(key, tab.getLayout(listName, BuiltInLayouts.kList).add(key, value).getEntry());
        }
    }

    public void putInDashboard(final String key, final Object value, final int columnIndex, final int rowIndex) {
        if (keys.containsKey(key)) {
            keys.get(key).setValue(value);
        } else {
            keys.put(key, tab.add(key, value).withPosition(columnIndex, rowIndex).getEntry());
        }
    }

    public void putInDashboard(final String key, final Object value, final int columnIndex, final int rowIndex,
            final WidgetType widgetType) {
        if (keys.containsKey(key)) {
            keys.get(key).setValue(value);
        } else {
            keys.put(key, tab.add(key, value).withPosition(columnIndex, rowIndex).withWidget(widgetType).getEntry());
        }
    }

    public void putInDashboard(final String key, final Object value, final int columnIndex, final int rowIndex,
            final WidgetType widgetType, final int width, final int height) {
        if (keys.containsKey(key)) {
            keys.get(key).setValue(value);
        } else {
            keys.put(key, tab.add(key, value).withPosition(columnIndex, rowIndex).withWidget(widgetType)
                    .withSize(width, height).getEntry());
        }
    }

    public double getFromDashboard(final String key, final double defaultValue) {
        if (keys.containsKey(key))
            if (keys.get(key).getValue().isValid())
                if (keys.get(key).getValue().isDouble())
                    return keys.get(key).getValue().getDouble();

        return defaultValue;
    }

    public String getFromDashboard(final String key, final String defaultValue) {
        if (keys.containsKey(key))
            if (keys.get(key).getValue().isValid())
                if (keys.get(key).getValue().isDouble())
                    return keys.get(key).getValue().getString();

        return defaultValue;
    }

    public boolean getFromDashboard(final String key, final boolean defaultValue) {
        if (keys.containsKey(key))
            if (keys.get(key).getValue().isValid())
                if (keys.get(key).getValue().isDouble())
                    return keys.get(key).getValue().getBoolean();

        return defaultValue;
    }

    public ShuffleboardTab getTab() {
        return tab;
    }
}
