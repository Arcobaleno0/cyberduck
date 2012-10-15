package ch.cyberduck.core.editor;

/*
 *  Copyright (c) 2007 David Kocher. All rights reserved.
 *  http://cyberduck.ch/
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  Bug fixes, suggestions and comments should be sent to:
 *  dkocher@cyberduck.ch
 */

import ch.cyberduck.core.Factory;
import ch.cyberduck.core.FactoryException;
import ch.cyberduck.core.local.Local;
import ch.cyberduck.core.Path;
import ch.cyberduck.core.Preferences;
import ch.cyberduck.ui.Controller;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version $Id$
 */
public abstract class EditorFactory extends Factory<Editor> {
    private static final Logger log = Logger.getLogger(EditorFactory.class);

    /**
     * Registered factories
     */
    protected static final Map<Factory.Platform, EditorFactory> factories
            = new HashMap<Factory.Platform, EditorFactory>();

    public static void addFactory(Factory.Platform platform, EditorFactory f) {
        factories.put(platform, f);
    }

    private static EditorFactory factory;

    public static EditorFactory instance() {
        if(null == factory) {
            if(!factories.containsKey(NATIVE_PLATFORM)) {
                throw new FactoryException(String.format("No implementation for %s", NATIVE_PLATFORM));
            }
            factory = factories.get(NATIVE_PLATFORM);
        }
        return factory;
    }

    /**
     * @return All statically registered but possibly not installed editors.
     */
    protected abstract List<Application> getConfigured();

    public List<Application> getEditors() {
        final List<Application> editors = new ArrayList<Application>(this.getConfigured());
        // Add the application set as the default editor in the Preferences to be always
        // included in the list of available editors.
        final Application defaultEditor = this.getDefaultEditor();
        if(null != defaultEditor) {
            if(!editors.contains(defaultEditor)) {
                editors.add(defaultEditor);
            }
        }
        return editors;
    }

    /**
     * @param c    Controller
     * @param path File to edit
     * @return New editor instance for the given file type.
     */
    public Editor create(final Controller c, final Path path) {
        return this.create(c, this.getEditor(path.getLocal()), path);
    }

    /**
     * @param c           Controller
     * @param application The application bundle identifier of the editor to use
     * @param path        File to edit
     * @return New editor instance for the given file type.
     */
    public abstract Editor create(Controller c, Application application, Path path);

    /**
     * Determine the default editor set
     *
     * @return The bundle identifier of the default editor configured in
     *         Preferences or com.apple.TextEdit if not installed.
     */
    public Application getDefaultEditor() {
        return ApplicationFinderFactory.instance().find(
                Preferences.instance().getProperty("editor.bundleIdentifier"));
    }

    /**
     * @param file File to find editor for
     * @return The bundle identifier of the application for this file or null if no
     *         suitable and installed editor is found.
     */
    public Application getEditor(final Local file) {
        if(Preferences.instance().getBoolean("editor.alwaysUseDefault")) {
            return this.getDefaultEditor();
        }
        final ApplicationFinder finder = ApplicationFinderFactory.instance();
        // The default application set by launch services to open files of the given type
        final Application editor = finder.find(file);
        if(null == editor) {
            // Use default editor if not applicable application found which handles this file type
            return this.getDefaultEditor();
        }
        // Use application determined by launch services using file system notifications
        return editor;
    }

    /**
     * @param file File to find suitable editors
     * @return Installed applications suitable to edit the given file type. Does always include
     *         the default editor set in the Preferences
     */
    public List<Application> getEditors(final Local file) {
        if(log.isDebugEnabled()) {
            log.debug(String.format("Find installed editors for file %s", file));
        }
        final List<Application> editors = new ArrayList<Application>(
                ApplicationFinderFactory.instance().findAll(file));
        // Add the application set as the default editor in the Preferences to be always
        // included in the list of available editors.
        final Application defaultEditor = this.getDefaultEditor();
        if(null != defaultEditor) {
            if(!editors.contains(defaultEditor)) {
                editors.add(defaultEditor);
            }
        }
        return editors;
    }
}