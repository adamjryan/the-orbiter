package com.google.gwt.sample.guestbook.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.sample.guestbook.client.Orbital.BodyNumber;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Widget;


public class ControlPanel extends Composite
{
    interface ControlPanelUiBinder extends UiBinder<Widget, ControlPanel> {}
    private static ControlPanelUiBinder uiBinder = GWT.create(ControlPanelUiBinder.class);

    Orbital orbital;
    CanvasOrbital canvas;

    @UiField Button initBtn;
    @UiField Button restartBtn;
    @UiField RadioButton oneBodyBtn;
    @UiField RadioButton twoBodyBtn;
    @UiField CheckBox inverseForceChk;
    @UiField CustomText dragCoefficient;
    @UiField Button decreBtn;
    @UiField Button increBtn;
    @UiField RadioButton particulateBtn;
    @UiField RadioButton strobeBtn;
    @UiField RadioButton streamBtn;

    public ControlPanel()
    {
        initWidget( uiBinder.createAndBindUi(this) );
    }

    public void setCanvas( CanvasOrbital newCanvas )
    {
        this.canvas = newCanvas;
        boolean isEnabled = false;
        if( canvas != null ){
        	isEnabled = true;
        }
        initBtn.setEnabled( isEnabled );
        restartBtn.setEnabled( isEnabled );
        particulateBtn.setEnabled( isEnabled );
        strobeBtn.setEnabled( isEnabled );
        streamBtn.setEnabled( isEnabled );
    }

    public void setOrbital( Orbital orbital )
    {
    	this.orbital = orbital;
        boolean isEnabled = false;
        Double dragCoef = null;
        if( orbital != null ){
        	isEnabled = true;
        	dragCoef = orbital.getDragCoefficient();
        }
    	oneBodyBtn.setEnabled( isEnabled );
    	twoBodyBtn.setEnabled( isEnabled );
    	inverseForceChk.setEnabled( isEnabled );
    	dragCoefficient.setValue( dragCoef );
    	decreBtn.setEnabled( isEnabled );
    	increBtn.setEnabled( isEnabled );
    }

    @UiHandler("initBtn")
    void initClick( ClickEvent e )
    {
        canvas.init();
    }

    @UiHandler("restartBtn")
    void restartClick( ClickEvent event )
    {
        orbital.init();
    }

    @UiHandler("oneBodyBtn")
    public void oneBodyClick( ClickEvent event )
    {
        orbital.setBodyNumber( BodyNumber.ONE );
    }

    @UiHandler("twoBodyBtn")
    public void twoBodyClick( ClickEvent event )
    {
        orbital.setBodyNumber( BodyNumber.TWO );
    }

    @UiHandler("inverseForceChk")
    public void inverseForceClick( ClickEvent event )
    {
        orbital.setInverseForce( ((CheckBox) event.getSource()).getValue() );
    }

    @UiHandler("decreBtn")
    public void decreClick( ClickEvent event )
    {
        double value = dragCoefficient.getValue() - 0.0001;
        if( value >= 0.9900 ){
            orbital.setDragCoefficient( value );
            dragCoefficient.setValue( value );
        }
    }

    @UiHandler("increBtn")
    public void increClick( ClickEvent event )
    {
        double value = dragCoefficient.getValue() + 0.0001;
        if( value < 1 ){
            orbital.setDragCoefficient( value );
            dragCoefficient.setValue( value );
        }
    }

    @UiHandler("particulateBtn")
    public void particulateClick(ClickEvent event)
    {
        canvas.setPaintEffect( CanvasOrbital.PaintEffect.PARTICULAR );
    }

    @UiHandler("strobeBtn")
    public void strobeClick(ClickEvent event)
    {
        canvas.setPaintEffect( CanvasOrbital.PaintEffect.STROBE );
    }

    @UiHandler("streamBtn")
    public void streamClick(ClickEvent event)
    {
        canvas.setPaintEffect( CanvasOrbital.PaintEffect.STREAM );
    }
}