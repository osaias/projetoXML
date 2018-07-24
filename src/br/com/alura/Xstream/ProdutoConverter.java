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
	public boolean canConvert(Class type) {
		return type.isAssignableFrom(Compra.class);
	}

	@Override
	public void marshal(Object obj, HierarchicalStreamWriter writer, MarshallingContext context) {
		
		Compra compra = (Compra) obj;
		
		writer.startNode("id");
		context.convertAnother(compra.getId());
		writer.endNode();

		writer.startNode("fornecedor");
		writer.addAttribute("id", "1");
		writer.setValue("Sony");
		writer.endNode();
		
		writer.startNode("produtos");
		List<Produto> produtos = compra.getProdutos();
		for (Produto produto : produtos) {
			writer.startNode("produto");
			context.convertAnother(produto);
			writer.endNode();
		}
		writer.endNode();
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {

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
		List<Produto> tagsProdutos = (List<Produto>) context.convertAnother(compra, List.class);
		reader.moveUp();
		
		compra = new Compra(Integer.valueOf(id), tagsProdutos);
		return compra;
	}

}
