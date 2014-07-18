package com.google.gwt.sample.guestbook.client;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;
import com.google.gwt.user.client.Random;


abstract public class CanvasExt
{
    public static final int WIDTH = 400, HEIGHT = 400;

    protected static final int colorVector = 11;
    protected final int coordVector = 11;
    protected Canvas canvas;
    protected Context2d context;
    protected int x = 0, y = 0;
    protected CssColor colour;

    private int red = 172, green = 172, blue = 172;

    public CanvasExt( Canvas canvas )
    {
        this.canvas = canvas;
        context = canvas.getContext2d();
        canvas.setWidth(WIDTH+"px");
        canvas.setHeight(HEIGHT+"px");
        canvas.setCoordinateSpaceWidth(WIDTH);
        canvas.setCoordinateSpaceHeight(HEIGHT);
    }

    public Canvas getCanvas()
    {
        return canvas;
    }

    public void init(){};
    abstract public void updateCanvas();

    protected void randomIncrementColour()
    {
        red = (byte)(red + (Random.nextInt(colorVector) - (colorVector/2)));
        if( red < 0 ) red = 0;      if( red > 255 ) red = 255;

        green = (byte)(green + (Random.nextInt(colorVector) - (colorVector/2)));
        if( green < 0 ) green = 0;  if( green > 255 ) green = 255;

        blue = (byte)(blue + (Random.nextInt(colorVector) - (colorVector/2)));
        if( blue < 0 ) blue = 0;    if( blue > 255 ) blue = 255;

        colour = CssColor.make( red, green, blue );
    }
}