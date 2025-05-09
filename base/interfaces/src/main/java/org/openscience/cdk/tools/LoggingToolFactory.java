/* Copyright (C) 2009  Egon Willighagen <egonw@users.sf.net>
 *
 * Contact: cdk-devel@lists.sourceforge.net
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package org.openscience.cdk.tools;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Factory used to instantiate a {@link ILoggingTool}. To get an instance, run:
 * <pre>
 * public class SomeClass {
 *   private static ILoggingTool logger;
 *
 *   static {
 *     logger = LoggingToolFactory.createLoggingTool(SomeClass.class);
 *   }
 * }
 * </pre>
 *
 */
public class LoggingToolFactory {

    /** Default logging tool. Currently, the slf4j based one. */
    public final static String DEFAULT_LOGGING_TOOL_CLASS = "org.openscience.cdk.tools.Slf4jLoggingTool";
    /** Backup logging tool. Currently, the lof4j based one. */
    private final static String BACKUP_LOGGING_TOOL_CLASS = "org.openscience.cdk.tools.Log4jLoggingTool";

    private static Class<? extends ILoggingTool> userSetILoggerTool;

    /**
     * Sets the {@link ILoggingTool} implementation to be used.
     *
     * @param loggingTool The new {@link ILoggingTool}.
     * @see   #getLoggingToolClass()
     */
    public static void setLoggingToolClass(Class<? extends ILoggingTool> loggingTool) {
        LoggingToolFactory.userSetILoggerTool = loggingTool;
    }

    /**
     * Gets the currently used {@link ILoggingTool} implementation.
     *
     * @return The currently used {@link ILoggingTool}.
     * @see    #setLoggingToolClass(Class)
     */
    public static Class<? extends ILoggingTool> getLoggingToolClass() {
        return LoggingToolFactory.userSetILoggerTool;
    }

    /**
     * Dynamically create a {@link ILoggingTool} for the given
     * <code>sourceClass</code>.
     *
     * @param  sourceClass Class for which the {@link ILoggingTool} should be
     *                     constructed.
     * @return             An {@link ILoggingTool} implementation.
     */
    public static ILoggingTool createLoggingTool(Class<?> sourceClass) {
        ILoggingTool tool = null;
        // first attempt the user set ILoggingTool
        if (userSetILoggerTool != null) {
            tool = instantiateWithCreateMethod(sourceClass, userSetILoggerTool);
        }
        if (tool == null) {
            tool = initializeLoggingTool(sourceClass, DEFAULT_LOGGING_TOOL_CLASS);
        }
        if (tool == null) {
            tool = initializeLoggingTool(sourceClass, BACKUP_LOGGING_TOOL_CLASS);
        }
        if (tool == null) {
            tool = new StdErrLogger(sourceClass);
        }
        return tool;
    }

    private static ILoggingTool initializeLoggingTool(Class<?> sourceClass, String className) {
        try {
            Class<?> possibleLoggingToolClass = sourceClass.getClassLoader().loadClass(className);
            if (ILoggingTool.class.isAssignableFrom(possibleLoggingToolClass)) {
                return instantiateWithCreateMethod(sourceClass, possibleLoggingToolClass);
            }
        } catch (ClassNotFoundException ignored) {
            // do not throw an error here is including the class is optional
        } catch (IllegalArgumentException | SecurityException e) {
            throw new RuntimeException("Could not create logging class: " + className, e);
        }
        return null;
    }

    private static ILoggingTool instantiateWithCreateMethod(Class<?> sourceClass, Class<?> loggingToolClass) {
        Method createMethod;
        try {
            createMethod = loggingToolClass.getMethod("create", Class.class);
            Object createdLoggingTool = createMethod.invoke(null, sourceClass);
            if (createdLoggingTool instanceof ILoggingTool) {
                return (ILoggingTool) createdLoggingTool;
            } else {
                System.out.println("Expected ILoggingTool, but found a:" + createdLoggingTool.getClass().getName());
            }
        } catch (SecurityException | InvocationTargetException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException e) {
            throw new RuntimeException("Could not create custom logging class", e);
        }
        return null;
    }

}
