package org.atlasgl.coreapi.utils;

import java.util.ArrayList;

public class ModuleManager {

    private ArrayList<Module> modules = new ArrayList<Module>();

    public void add(Module m) {

        modules.add(m);

    }

    public void remove(Module m) {

        modules.remove(m);

    }

    public void start() {

        modules.forEach((m) -> m.start());

    }

    public void clear() {

        modules.forEach((m) -> m.clear());

    }

    public void stop() {

        modules.forEach((m) -> m.stop());

    }

}
