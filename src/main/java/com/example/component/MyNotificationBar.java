package com.example.component;

import org.primefaces.component.notificationbar.NotificationBarBase;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;

@FacesComponent(value = MyNotificationBar.COMPONENT_TYPE)
@ResourceDependencies({
        @ResourceDependency(library = "primefaces", name = "components.css"),
        @ResourceDependency(library = "primefaces", name = "jquery/jquery.js"),
        @ResourceDependency(library = "primefaces", name = "core.js"),
        @ResourceDependency(library="js", name = "mynotificationbar.js")
})
public class MyNotificationBar extends NotificationBarBase {

    public static final String COMPONENT_TYPE = "com.example.component.MyNotificationBar";
    public static final String DEFAULT_RENDERER = "com.example.renderer.MyNotificationBarRenderer";

    /* CSS */
    public static final String CLOSE_ICON_CLASS = "ui-icon ui-icon-closethick";
    public static final String TITLE_BAR_CLOSE_CLASS = "ui-dialog-notificationbar-icon ui-dialog-notificationbar-close ui-corner-all";

    public static final String TITLE_BAR_MINIMIZE_CLASS = "ui-dialog-notificationbar-icon ui-dialog-notificationbar-minimize ui-corner-all";
    public static final String MINIMIZE_ICON_CLASS = "ui-icon ui-icon-minus";

    public static final String TITLE_BAR_MAXIMIZE_CLASS = "ui-dialog-notificationbar-icon ui-dialog-notificationbar-maximize ui-corner-all";
    public static final String MAXIMIZE_ICON_CLASS = "ui-icon ui-icon-extlink";
    
    public enum PropertyKeys {
        closable,
        minimizable,
        maximizable
    }

    public MyNotificationBar() {
        setRendererType(DEFAULT_RENDERER);
    }

    public void setClosable(boolean visible) {
        getStateHelper().put(PropertyKeys.closable, visible);
    }

    public boolean isClosable() {
        return (Boolean) getStateHelper().eval(PropertyKeys.closable, false);
    }

    public void setMinimizable(boolean minimizable) {
        getStateHelper().put(PropertyKeys.minimizable, minimizable);
    }

    public boolean isMinimizable() {
        return (Boolean) getStateHelper().eval(PropertyKeys.minimizable, false);
    }

    public void setMaximizable(boolean maximizable) {
        getStateHelper().put(PropertyKeys.maximizable, maximizable);
    }

    public boolean isMaximizable() {
        return (Boolean) getStateHelper().eval(PropertyKeys.maximizable, false);
    }


}
