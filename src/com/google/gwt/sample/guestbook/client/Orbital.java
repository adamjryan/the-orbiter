package com.google.gwt.sample.guestbook.client;

import java.util.ArrayList;

import com.google.gwt.user.client.Timer;



public class Orbital
{
    interface OrbitalObserver
    {
        void orbiterMovedTo( double x, double y );
        void numberOfBodiesChanged( Mass[] bodies );
    }

    class Mass{
        double x;
        double y;
        double v_x;
        double v_y;
        int mass;
    }

    enum BodyNumber{
        ONE,
        TWO
    }

    static final double G = 25;
//    static final double G = 6.67;
    static final double MAX_FORCE = 0.8;
    static final int TIME_STEP = 1;
    private double dragCoefficient = 0.995;
    private final int width, height;
    Mass b1 = new Mass();
    Mass b2 = new Mass();
    Mass orbiter = new Mass();
    Mass[] bodies = new Mass[]{ b1, b2 };
    private Timer timer;
    int itrCount = 0;
    private boolean isForceInversed = false;
    ArrayList<OrbitalObserver> observers = new ArrayList<OrbitalObserver>();

    public Orbital( int width, int height )
    {
        this.width = width;
        this.height = height;
        orbiter.mass = 1;
        setBodyNumber( BodyNumber.ONE );
        init();
        timer = new Timer()
                {
                    @Override
                    public void run()
                    {
                        iterate();
                    }

                };
    }

    public void init()
    {
        orbiter.x = 0.5 * width;
        orbiter.y = 0.6 * height;
        orbiter.v_x = 0.006 * width;
        orbiter.v_y = 0;
    }

    public void setEnabled( boolean isEnabled )
    {
        if( isEnabled ){
            timer.scheduleRepeating( 25 );
        }
        else{
            timer.cancel();
        }
    }

    public void getOrbiterLocation( double[] coords )
    {
        if( coords.length < 2 ){
            return;
        }
        coords[0] = orbiter.x;
        coords[1] = orbiter.y;
    }

    public Mass[] getBodies()
    {
        return bodies;
    }

    public void setBodyNumber( BodyNumber bodyNumber )
    {
        switch( bodyNumber ){
        case ONE:
            bodies = new Mass[1];
            bodies[0] = new Mass();
            bodies[0].x = (int) (0.5 * width);
            bodies[0].y = (int) (0.5 * width);
            bodies[0].mass = 125;
            break;
        case TWO:
            bodies = new Mass[2];
            bodies[0] = new Mass();
            bodies[0].x = (int) (0.3 * width);
            bodies[0].y = (int) (0.5 * width);
            bodies[0].mass = 125;
            bodies[1] = new Mass();
            bodies[1].x = (int) (0.7 * width);
            bodies[1].y = (int) (0.5 * width);
            bodies[1].mass = 125;
            break;
        }
        for( OrbitalObserver observer : observers ){
            observer.numberOfBodiesChanged( bodies );
        }
    }

    public void setDragCoefficient( double milliDragCoefficient )
    {
        if( milliDragCoefficient > 0.9999 ){
        	dragCoefficient = 0.9999;
        }
        else if( milliDragCoefficient < 0.9900 ){
        	dragCoefficient = 0.9900;
        }
        else{
        	dragCoefficient = milliDragCoefficient;
        }
    }

    public double getDragCoefficient()
    {
    	return dragCoefficient;
    }

    public void setInverseForce( boolean isInversed )
    {
        isForceInversed = isInversed;
    }

    public synchronized void iterate() //Synchronised irrelevant in GWT (single threaded).
    {
        double[] force = new double[]{ 0, 0 };
        double[] addedForce = new double[2];
        for( Mass body : bodies ){
            calcAcceleration( orbiter, body, addedForce );
            force[0] += addedForce[0];
            force[1] += addedForce[1];
        }
        orbiter.x += (orbiter.v_x * TIME_STEP) + (0.5 * force[0] * Math.pow( TIME_STEP, 2 ));
        orbiter.y += (orbiter.v_y * TIME_STEP) + (0.5 * force[1] * Math.pow( TIME_STEP, 2 ));
        orbiter.v_x += force[0] * TIME_STEP;
        orbiter.v_y += force[1] * TIME_STEP;
        orbiter.v_x *= dragCoefficient;
        orbiter.v_y *= dragCoefficient;
        for( OrbitalObserver observer : observers ){
            observer.orbiterMovedTo( orbiter.x, orbiter.y );
        }
    }

    private void calcAcceleration( Mass actedOn, Mass actedBy, double[] force_vect )
    {
        double offset_x = actedBy.x - actedOn.x;
        double offset_y = actedBy.y - actedOn.y;
        double distance = Math.sqrt( Math.pow( offset_x, 2 ) + Math.pow( offset_y, 2 ) );
        double force = ( G * actedOn.mass * actedBy.mass ) / Math.pow( distance, 2 );
        force = isForceInversed  ?  1 / force  :  force;
        if( force > MAX_FORCE ){
            force = MAX_FORCE;
        }
        if( force < -MAX_FORCE ){
            force = -MAX_FORCE;
        }
        double force_x = ( force / distance ) * offset_x;
        double force_y = ( force / distance ) * offset_y;
        force_vect[0] = force_x / actedOn.mass;
        force_vect[1] = force_y / actedOn.mass;
    }

    public void addObserver( OrbitalObserver observer )
    {
        observers.add( observer );
    }
}
