package com.google.gwt.sample.guestbook.client;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.CssColor;
import com.google.gwt.sample.guestbook.client.Orbital.Mass;
import com.google.gwt.sample.guestbook.client.Orbital.OrbitalObserver;


public class CanvasOrbital extends CanvasExt implements OrbitalObserver
{
    public enum PaintEffect{
        PARTICULAR,
        STROBE,
        STREAM,
    }

    private static final CssColor backgoundColour = CssColor.make( 255, 255, 255 );
    private PaintEffect effect = PaintEffect.PARTICULAR;
    private Orbital orbital;
    private Mass[] bodies;
    private double[] coord = new double[2];

    /**
     * @param canvas
     */
    public CanvasOrbital( Canvas canvas, Orbital orbital )
    {
        super(canvas);
        this.orbital = orbital;
        bodies = orbital.getBodies();
        init();
    }

    @Override
    public void init()
    {
        context.closePath();
        context.beginPath();
        context.clearRect( 0, 0, WIDTH, HEIGHT );

        for( Mass body : bodies = orbital.getBodies() ){
            context.setFillStyle( CssColor.make( "red" ) );
            context.fillRect( body.x, body.y, 3, 3 );
            context.stroke();
        }

        orbital.getOrbiterLocation( coord );
        context.moveTo( x = (int)coord[0], y = (int)coord[1] );
//        context.closePath();
    }

    public void setPaintEffect( PaintEffect newEffect )
    {
        effect = newEffect;
    }

    public void getControlPanel()
    {

    }

//    /* (non-Javadoc)
//     * @see com.google.gwt.sample.guestbook.client.CanvasExt#updateCanvas()
//     */
    @Override
    public void updateCanvas()
    {
//        randomIncrementColour();
//
//        orbital.iterate();
//        orbital.getOrbiterCoord( coord );
//
//        switch( effect ){
//        case PARTICULAR:
//            context.setFillStyle( backgoundColour );
//            context.fillRect( x, y, 3, 3 );
//            context.stroke();
//            context.setFillStyle( colour );
//            context.fillRect( x = coord[0], y = coord[1], 3, 3 );
//            context.stroke();
//            break;
//        case STROBE:
//            context.setFillStyle( colour );
//            context.fillRect( x = coord[0], y = coord[1], 2, 2 );
//            context.stroke();
//            break;
//        case STREAM:
//            context.beginPath();
//            context.setStrokeStyle( colour );
//            context.moveTo( x, y );
//            context.lineTo( x = coord[0], y = coord[1] );
//            context.stroke();
//            context.closePath();
//            break;
//        }
    }

    /* (non-Javadoc)
     * @see com.google.gwt.sample.guestbook.client.Orbital.OrbitalObserver#orbiterMovedTo(double, double)
     */
    @Override
    public void orbiterMovedTo( double newX, double newY )
    {
        randomIncrementColour();

        switch( effect ){
        case PARTICULAR:
            context.beginPath();
            context.setFillStyle( backgoundColour );
            context.fillRect( x, y, 3, 3 );
            context.stroke();
            context.setFillStyle( colour );
            context.fillRect( x = (int)newX, y = (int)newY, 3, 3 );
            context.stroke();
            context.closePath();
            break;
        case STROBE:
            context.beginPath();
            context.setFillStyle( colour );
            context.fillRect( x = (int)newX, y = (int)newY, 2, 2 );
            context.stroke();
            context.closePath();
            break;
        case STREAM:
            context.beginPath();
            context.moveTo( x, y );
            context.setStrokeStyle( colour );
            context.lineTo( x = (int)newX, y = (int)newY );
            context.stroke();
            context.closePath();
            break;
        }
    }

    /* (non-Javadoc)
     * @see com.google.gwt.sample.guestbook.client.Orbital.OrbitalObserver#numberOfBodiesChanged(com.google.gwt.sample.guestbook.client.Orbital.Mass[])
     */
    @Override
    public void numberOfBodiesChanged( Mass[] bodies )
    {
        for( Mass body : this.bodies ){
            context.setFillStyle( backgoundColour );
            context.fillRect( body.x, body.y, 3, 3 );
            context.stroke();
        }
        for( Mass body : this.bodies = bodies ){
            context.setFillStyle( CssColor.make( "red" ) );
            context.fillRect( body.x, body.y, 3, 3 );
            context.stroke();
        }
    }
}