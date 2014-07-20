package com.google.gwt.sample.guestbook.client;

import java.text.ParseException;

import com.google.gwt.dom.client.Document;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.text.shared.Parser;
import com.google.gwt.user.client.ui.ValueBoxBase;


public class CustomText extends ValueBoxBase<Double>
{
	private static final NumberFormat format = NumberFormat.getFormat( "0.0000" );

    public CustomText()
    {
    	super( Document.get().createTextInputElement(), new CustomRenderer(), new CustomParser() );
    }

    private static class CustomRenderer extends AbstractRenderer<Double>
    {
        @Override
        public String render( Double value )
        {
            return format.format( value );
        }
    }

    private static class CustomParser implements Parser<Double>
    {
        @Override
        public Double parse( CharSequence text ) throws ParseException
        {
        	return format.parse( (String)text );
        }
    }
}