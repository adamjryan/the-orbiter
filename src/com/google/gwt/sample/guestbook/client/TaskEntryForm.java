package com.google.gwt.sample.guestbook.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;


public class TaskEntryForm extends Composite implements HasText
{
    interface TaskEntryFormUiBinder extends UiBinder<Widget, TaskEntryForm> {}
    private static TaskEntryFormUiBinder uiBinder = GWT.create(TaskEntryFormUiBinder.class);

    @UiField
    Button button;

    public TaskEntryForm()
    {
        initWidget(uiBinder.createAndBindUi(this));
//        FormPanel form = new FormPanel();
//        form.set
    }

    @UiHandler("button")
    void onClick(ClickEvent e)
    {
        Window.alert("Hello!");
    }

    public void setText(String text) {
        button.setText(text);
    }

    @Override
    public String getText()
    {
        return button.getText();
    }
}

/**
 * Because this class has a default constructor, it can
 * be used as a binder template. In other words, it can be used in other
 * *.ui.xml files as follows:
 * <ui:UiBinder xmlns:ui="urn:ui:com.google.gwt.uibinder"
 *              xmlns:g="urn:import:**user's package**">
 *      <g:**UserClassName**>
 *          Hello!
 *      </g:**UserClassName>
 * </ui:UiBinder>
 * Note that depending on the widget that is used, it may be necessary to
 * implement HasHTML instead of HasText.
 */