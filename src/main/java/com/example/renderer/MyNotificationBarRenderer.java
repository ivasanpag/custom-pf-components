package com.example.renderer;

import com.example.component.MyNotificationBar;
import org.primefaces.component.notificationbar.NotificationBar;
import org.primefaces.component.notificationbar.NotificationBarRenderer;
import org.primefaces.util.HTML;
import org.primefaces.util.WidgetBuilder;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import java.io.IOException;

@FacesRenderer(componentFamily = NotificationBar.COMPONENT_FAMILY, rendererType = MyNotificationBarRenderer.RENDERER_TYPE)
public class MyNotificationBarRenderer extends NotificationBarRenderer {

    protected static final String RENDERER_TYPE = "com.example.renderer.MyNotificationBarRenderer";

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        MyNotificationBar bar = (MyNotificationBar) component;

        encodeMarkup(context, bar);
        encodeScript(context, bar);
    }

    protected void encodeMarkup(FacesContext context, MyNotificationBar bar) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String styleClass = bar.getStyleClass();
        styleClass = styleClass == null ? NotificationBar.STYLE_CLASS : NotificationBar.STYLE_CLASS + " " + styleClass;

        writer.startElement("div", bar);
        writer.writeAttribute("id", bar.getClientId(context), null);
        writer.writeAttribute("class", styleClass, null);

        if (bar.getStyle() != null)
            writer.writeAttribute("style", bar.getStyle(), null);

        if (bar.isClosable())
            encodeIcon(context, MyNotificationBar.TITLE_BAR_CLOSE_CLASS, MyNotificationBar.CLOSE_ICON_CLASS, "Close");

        if (bar.isMinimizable())
            encodeIcon(context, MyNotificationBar.TITLE_BAR_MINIMIZE_CLASS, MyNotificationBar.MINIMIZE_ICON_CLASS, "Minimize");

        if (bar.isMaximizable())
            encodeIcon(context, MyNotificationBar.TITLE_BAR_MAXIMIZE_CLASS, MyNotificationBar.MAXIMIZE_ICON_CLASS, "Maximize");



        renderChildren(context, bar);

        writer.endElement("div");
    }

    private void encodeScript(FacesContext context, MyNotificationBar bar) throws IOException {
        String clientId = bar.getClientId(context);
        WidgetBuilder wb = getWidgetBuilder(context);
        wb.init("MyNotificationBar", bar.resolveWidgetVar(context), clientId)
                .attr("position", bar.getPosition())
                .attr("effect", bar.getEffect())
                .attr("effectSpeed", bar.getEffectSpeed())
                .attr("autoDisplay", bar.isAutoDisplay(), false);
        wb.finish();
    }

    protected void encodeIcon(FacesContext context, String anchorClass, String iconClass, String ariaLabel) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        writer.startElement("a", null);
        writer.writeAttribute("href", "#", null);
        writer.writeAttribute("class", anchorClass, null);
        if (ariaLabel != null) {
            writer.writeAttribute(HTML.ARIA_LABEL, ariaLabel, null);
        }

        writer.startElement("span", null);
        writer.writeAttribute("class", iconClass, null);
        writer.endElement("span");

        writer.endElement("a");
    }

}
