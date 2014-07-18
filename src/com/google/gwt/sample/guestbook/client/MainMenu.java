package com.google.gwt.sample.guestbook.client;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;


public class MainMenu
{
    interface MyUiBinder extends UiBinder<Widget, MainMenu> {}
    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    @UiField VerticalPanel root;
    Label title = new Label( "The Orbiter" );
    Canvas canvas;

    public MainMenu()
    {
        root = (VerticalPanel) uiBinder.createAndBindUi(this);
        title.getElement().getStyle().setFontSize( 3, Unit.EM );
        root.add( title );
        canvas = Canvas.createIfSupported();
        if( canvas == null ){
            return;
        }
        root.add(canvas);

        Orbital orbital = new Orbital( CanvasExt.WIDTH, CanvasExt.HEIGHT );
        final CanvasExt canvasExt = new CanvasOrbital( canvas, orbital );
        orbital.addObserver( ((CanvasOrbital)canvasExt) );

        final ControlPanel controlPanel = new ControlPanel( orbital, (CanvasOrbital)canvasExt );
        root.add( controlPanel );

        orbital.setEnabled( true );
    }

    public Widget getRoot()
    {
        return root;
    }
}


//extends Composite

//interface MyUiBinder extends UiBinder<DivElement, MainMenu> {}

//private DivElement root;
//@UiField SpanElement nameSpan;
//@UiField FlexTable tableA;

//nameSpan.setInnerText("Initial");
//button.setText("Click Click");
//tableA.setText( 0, 0, "Col0" );
//tableA.setText( 0, 1, "Col1" );
//tableA.setText( 0, 2, "Col2" );
//tableA.setCellPadding(6);
//tableA.addCell(0);