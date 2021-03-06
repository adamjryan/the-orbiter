package com.google.gwt.sample.guestbook.client;

import com.google.gwt.user.client.Random;


public class CanvasRandom extends CanvasCustom
{
    /**
     * @param canvas
     */
    public CanvasRandom()
    {
//        super( canvas );
        x = WIDTH/2;
        y = HEIGHT/2;
    }

    /* (non-Javadoc)
     * @see com.google.gwt.sample.guestbook.client.CanvasExt#updateCanvas()
     */
    @Override
    public void updateCanvas()
    {
        context.beginPath();
        randomIncrementColour();
        context.setStrokeStyle( colour );
        context.moveTo(x, y);
        randomUpdateCoord();
        context.lineTo( x, y );
        context.stroke();
        context.closePath();
    }

    private void randomUpdateCoord()
    {
        x = x + (Random.nextInt(coordVector) - (COLOUR_INCREMENT_VECTOR/2));
        if( x < 0 ){
            x = 0;
        }
        else if( x > WIDTH ){
            x = WIDTH;
        }

        y = y + (Random.nextInt(coordVector) - (COLOUR_INCREMENT_VECTOR/2));
        if( y < 0 ){
            y = 0;
        }
        else if( y > WIDTH ){
            y = WIDTH;
        }
    }
}
