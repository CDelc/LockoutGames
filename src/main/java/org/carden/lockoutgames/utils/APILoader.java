package org.carden.lockoutgames.utils;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import org.carden.lockoutgames.game.setting.Setting;

public class APILoader {

    public static void loadAll() {
        loadSettingsAPI();
    }

    public static void loadSettingsAPI() {
        ClassGraph scanner = new ClassGraph();
        try (ScanResult result = scanner.enableClassInfo().acceptPackages(Setting.class.getPackageName()).scan()) {
            result.getAllClasses().forEach(classInfo -> {
                try {
                    Class.forName(classInfo.getName(), true, classInfo.getClass().getClassLoader());
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
