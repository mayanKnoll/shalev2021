package frc.util;

import frc.util.dashboard.SuperSystem;

abstract public class OutputSystem extends SuperSystem {

    public OutputSystem(String nameSystem) {
        super(nameSystem);
    }

    abstract public void setOutput(double output);
}
