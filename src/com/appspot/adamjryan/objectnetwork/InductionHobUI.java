package com.appspot.adamjryan.objectnetwork;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;


public class InductionHobUI extends Composite
{
	interface InductionHobUIUiBinder extends UiBinder<Widget, InductionHobUI> {}
	private static InductionHobUIUiBinder uiBinder = GWT.create(InductionHobUIUiBinder.class);

	@UiField Button button;

	public InductionHobUI()
	{
		initWidget( uiBinder.createAndBindUi(this) );
	}

	@UiHandler("button")
	void onClick( ClickEvent e )
	{
		Window.alert("Hello!");
	}
}