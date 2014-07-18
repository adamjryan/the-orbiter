package com.google.gwt.sample.guestbook.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.sample.guestbook.client.Orbital.BodyNumber;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DoubleBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;


public class ControlPanel extends Composite
{
    interface ControlPanelUiBinder extends UiBinder<Widget, ControlPanel> {}
    private static ControlPanelUiBinder uiBinder = GWT.create(ControlPanelUiBinder.class);

    @UiField VerticalPanel root;
    @UiField Button initBtn;
    RadioButton oneBodyBtn = new RadioButton("BodyNum");
    RadioButton twoBodyBtn = new RadioButton("BodyNum");
    CheckBox inverseForceChk = new CheckBox();

    Label dragCoLbl = new Label("Drag-Coefficient: ");
    DoubleBox dragCoefficient = new DoubleBox();
    Button decre = new Button( "-" );
    Button incre = new Button( "+" );

    RadioButton particularBtn = new RadioButton( "effectBtns" );
    RadioButton strobeBtn = new RadioButton( "effectBtns" );
    RadioButton streamBtn = new RadioButton( "effectBtns" );

    Orbital orbital;
    CanvasOrbital canvas;

    /**
     * Because this class has a default constructor, it can
     * be used as a binder template. In other words, it can be used in other
     * *.ui.xml files as follows:
     * <ui:UiBinder xmlns:ui="urn:ui:com.google.gwt.uibinder"
     *   xmlns:g="urn:import:**user's package**">
     *  <g:**UserClassName**>Hello!</g:**UserClassName>
     * </ui:UiBinder>
     * Note that depending on the widget that is used, it may be necessary to
     * implement HasHTML instead of HasText.
     */
    public ControlPanel( Orbital newOrbital, CanvasOrbital newCanvas )
    {
        this.orbital = newOrbital;
        this.canvas = newCanvas;
        initWidget( uiBinder.createAndBindUi(this) );

        initBtn.setText("Initialise");
        Button restartBtn = new Button( "Restart" );
        restartBtn.addClickHandler(
                new ClickHandler()
                {
                    @Override
                    public void onClick(ClickEvent event)
                    {
                        orbital.init();
                        canvas.init();
                    }
                }
        );

        oneBodyBtn.setText( "One Body", Direction.LTR );
        oneBodyBtn.addClickHandler(
                new ClickHandler()
                {
                    @Override
                    public void onClick(ClickEvent event)
                    {
                        orbital.setBodyNumber( BodyNumber.ONE );
                    }
                }
        );
        twoBodyBtn.setText( "Two Body", Direction.RTL );
        twoBodyBtn.addClickHandler(
                new ClickHandler()
                {
                    @Override
                    public void onClick(ClickEvent event)
                    {
                        orbital.setBodyNumber( BodyNumber.TWO );
                    }
                }
        );
        oneBodyBtn.setValue(true);

        FlowPanel radioBtnPnl = new FlowPanel();
        radioBtnPnl.getElement().getStyle().setProperty( "marginRight", "10px" );
        radioBtnPnl.add( oneBodyBtn );
        radioBtnPnl.add( twoBodyBtn );


        inverseForceChk.setText( "Inverse Force", Direction.LTR );
        inverseForceChk.addClickHandler(
                new ClickHandler()
                {
                    @Override
                    public void onClick(ClickEvent event)
                    {
                        orbital.setInverseForce( inverseForceChk.getValue() );
                    }
                }
        );


        dragCoefficient.setValue(5.0);
//        dragCoefficient.setStyleName(style); //TODO create CSS style for fixed 2 decimal output.
        dragCoefficient.setReadOnly(true);
        decre.addClickHandler(
                new ClickHandler()
                {
                    @Override
                    public void onClick(ClickEvent event)
                    {
                        double value = dragCoefficient.getValue();
                        if( value > 0 ){
                            orbital.setDragCoefficient( value );
                            dragCoefficient.setValue( dragCoefficient.getValue() - 0.1 );
                        }
                    }
                }
        );
        incre.addClickHandler(
                new ClickHandler()
                {
                    @Override
                    public void onClick(ClickEvent event)
                    {
                        double value = dragCoefficient.getValue();
                        if( value < 10){
                            orbital.setDragCoefficient( value );
                            dragCoefficient.setValue( dragCoefficient.getValue() + 0.1 );
                        }
                    }
                }
        );

        HorizontalPanel dragCoPnl = new HorizontalPanel();
        dragCoPnl.setSpacing( 5 );
        dragCoPnl.add( dragCoLbl );
        dragCoPnl.add( dragCoefficient );
        dragCoPnl.add( decre );
        dragCoPnl.add( incre );

        particularBtn.setText( "Particular Effect", Direction.LTR );

        strobeBtn.setText( "Strobe Effect", Direction.LTR );
        strobeBtn.addClickHandler(
                new ClickHandler()
                {
                    @Override
                    public void onClick(ClickEvent event)
                    {
                        canvas.setPaintEffect( CanvasOrbital.PaintEffect.STROBE );
                    }
                }
        );
        streamBtn.setText( "Stream Effect", Direction.LTR );
        streamBtn.addClickHandler(
                new ClickHandler()
                {
                    @Override
                    public void onClick(ClickEvent event)
                    {
                        canvas.setPaintEffect( CanvasOrbital.PaintEffect.STREAM );
                    }
                }
        );
        particularBtn.setValue(true);
        particularBtn.addClickHandler(
                new ClickHandler()
                {
                    @Override
                    public void onClick(ClickEvent event)
                    {
                        canvas.setPaintEffect( CanvasOrbital.PaintEffect.PARTICULAR );
                    }
                }
        );

        FlowPanel effectPnl = new FlowPanel();
        effectPnl.getElement().getStyle().setProperty( "marginRight", "10px" );
        effectPnl.add( particularBtn );
        effectPnl.add( strobeBtn );
        effectPnl.add( streamBtn );

        root.add( restartBtn );
        root.add( radioBtnPnl );
        root.add( inverseForceChk );
        root.add( dragCoPnl );
        root.add( effectPnl );
    }

    @UiHandler("initBtn")
    void onClick(ClickEvent e)
    {
        canvas.init();
    }
}