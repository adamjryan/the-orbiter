package com.google.gwt.sample.guestbook.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;


public class Main implements EntryPoint
{
    static final String ROOT_ELEMENT = "root_element";

    /* (non-Javadoc)
     * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
     */
    @Override
    public void onModuleLoad()
    {
        TheOrbiter orbiter = new TheOrbiter();
    	RootPanel.get(ROOT_ELEMENT).add( orbiter );
    	orbiter.setEnabled( true );
    }
}