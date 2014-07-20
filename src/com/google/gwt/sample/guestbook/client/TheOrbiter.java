package com.google.gwt.sample.guestbook.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;


public class TheOrbiter extends Composite
{
	/*TODO
	 * Create an event scheduling table and recorder so image can be defined.
	 * Make the colour dependent on the speed.
	 * Make option to download/upload/modify event logs.
	 * Allow placement of orbiter and bodies, and allow manual change of speed and direction that the orbiter can be initialised or scheduled with in the event log.
	 * Allow entry of initial seed for random colour generator for recreation.
	 * Allow restriction of colours it can randomly vary between (slider scale).
	 * Make calculation of orbit more exact instead of approximation and with or without drag coefficient.
	 */
    interface TheOrbitorUiBinder extends UiBinder<VerticalPanel, TheOrbiter>{}
    private static TheOrbitorUiBinder uiBinder = GWT.create(TheOrbitorUiBinder.class);

    private final Orbital orbital = new Orbital( CanvasCustom.WIDTH, CanvasCustom.HEIGHT );

    public TheOrbiter()
    {
		initWidget( uiBinder.createAndBindUi(this) );
    }

    public void setEnabled( boolean isEnabled )
    {
        orbital.setEnabled( isEnabled );
    }

    @UiFactory
    public Orbital getOrbital()
    {
    	return orbital;
    }
}