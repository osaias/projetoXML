package br.com.alura.Xstream;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class PrecoConverter implements Converter {

	@Override
	public boolean canConvert(Class type) {
		return type.isAssignableFrom(Double.class);
	}

	@Override
	public void marshal(Object obj, HierarchicalStreamWriter writer, MarshallingContext context) {

		Double preco = (Double) obj;
		
		Locale brasil = new Locale("pt", "br");
		
		NumberFormat instance = NumberFormat.getCurrencyInstance(brasil);
		String precoFormatado = instance.format(preco);
		
		writer.setValue(precoFormatado);

	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		
		String tagPreco = reader.getValue();
		Number preco = 0.0;
		Locale brasil = new Locale("pt", "br");
		
		NumberFormat instance = NumberFormat.getCurrencyInstance(brasil);
		try {
			preco = instance.parse(tagPreco);
		} catch (ParseException e) {
			System.out.println("Não fez o Cast: " + e);
		}
		
		return preco.doubleValue();
	}

}
