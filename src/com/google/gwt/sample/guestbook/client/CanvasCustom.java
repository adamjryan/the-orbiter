package com.google.gwt.sample.guestbook.client;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

abstract public class CanvasCustom extends Composite
{
	interface CanvasUiBinder extends UiBinder<Widget, CanvasCustom>{}
	private static CanvasUiBinder uiBinder = GWT.create( CanvasUiBinder.class );

    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;

    protected static final int COLOUR_INCREMENT_VECTOR = 11;
    protected final int coordVector = 11;
    @UiField Canvas canvas;
    protected Context2d context;
    protected int x = 0, y = 0;
    protected CssColor colour;

    private int red = 172;
    private int green = 172;
    private int blue = 172;

	public CanvasCustom()
	{
		initWidget( uiBinder.createAndBindUi(this) );
        context = canvas.getContext2d();
        canvas.setWidth(WIDTH+"px");
        canvas.setHeight(HEIGHT+"px");
        canvas.setCoordinateSpaceWidth(WIDTH);
        canvas.setCoordinateSpaceHeight(HEIGHT);
	}

    public void init(){};
    abstract public void updateCanvas();

    protected void randomIncrementColour()
    {
        colour = CssColor.make( red = randomIncrementColourComponent( red ),
        						green = randomIncrementColourComponent( green ),
        						blue = randomIncrementColourComponent( blue ) );
    }

    private static int randomIncrementColourComponent( int colour )
    {
    	int min = (COLOUR_INCREMENT_VECTOR-1) / 2;
    	int rand = Random.nextInt(COLOUR_INCREMENT_VECTOR);
    	int vect = rand - min;
    	colour = colour + vect;
//    	colour = (byte)(   colour + (  Random.nextInt(COLOUR_INCREMENT_VECTOR) - ( (COLOUR_INCREMENT_VECTOR-1) / 2 )  )   );
        if( colour < 0 ){
        	colour = 0;
        }
        else if( colour > 255 ){
        	colour = 255;
        }
        return colour;
    }

    @UiFactory
    public Canvas getCanvas()
    {
//      canvas = Canvas.createIfSupported();
//      if( canvas == null ){
    		//TODO make alternative or a blank box of the same size.
//      }
    	return Canvas.createIfSupported();
    }
}