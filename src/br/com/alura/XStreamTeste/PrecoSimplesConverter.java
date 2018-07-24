package br.com.alura.XStreamTeste;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString(Object preco) {
		// TODO Auto-generated method stub
		return null;
	}


}
