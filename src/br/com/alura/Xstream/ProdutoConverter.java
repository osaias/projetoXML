package br.com.alura.Xstream;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class ProdutoConverter implements Converter {

	@Override
	public boolean canConvert(Class arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void marshal(Object arg0, HierarchicalStreamWriter arg1, MarshallingContext arg2) {
		// TODO Auto-generated method stub

Compra compra = (Compra) obj;
		
		writer.startNode("id");
		context.convertAnother(compra.getId());
		writer.endNode();

		writer.startNode("fornecedor");
		writer.addAttribute("id", "1");
		context.convertAnother("Sony");
		writer.endNode();
		
		writer.startNode("produtos");
		context.convertAnother(compra.getProdutos());
		writer.endNode();
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader arg0, UnmarshallingContext arg1) {

		Compra compra = null;
		List<Produto> produtos = new ArrayList<>();
		
		reader.moveDown();
		String id = reader.getValue();
		reader.moveUp();
		
		reader.moveDown();
		String attribute = reader.getAttribute("id");
		String fornecedor = reader.getValue();
		reader.moveUp();
		
		reader.moveDown();
		context.convertAnother(compra, List.class);
		reader.moveUp();
		
		return null;
	}

}
