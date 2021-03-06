package br.com.alura.XStreamTeste;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.SingleValueConverter;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class PrecoSimplesConverter implements SingleValueConverter {

	@Override
	public boolean canConvert(Class type) {

		return type.isAssignableFrom(Double.class);
	}

	@Override
	public Object fromString(String preco) {

		Locale brasil = new Locale("pt", "br");
		NumberFormat instance = NumberFormat.getCurrencyInstance(brasil);
		
		Number number = 0.0;
		try {
			number = instance.parse(preco);
		} catch (ParseException e) {
			throw new IllegalArgumentException("N�o converteu o preco: " + preco,e);
		}
		
		return number.doubleValue();
	}

	@Override
	public String toString(Object preco) {

		Locale brasil = new Locale("pt", "br");
		NumberFormat instance = NumberFormat.getCurrencyInstance(brasil);
		String string = instance.format(preco);
		
		return string;
	}


}
