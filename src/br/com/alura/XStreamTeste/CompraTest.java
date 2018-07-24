package br.com.alura.XStreamTeste;


import static org.junit.Assert.assertEquals;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.Xstream.Compra;
import br.com.alura.Xstream.CompraConverter;
import br.com.alura.Xstream.Livro;
import br.com.alura.Xstream.Musica;
import br.com.alura.Xstream.PrecoConverter;
import br.com.alura.Xstream.Produto;
import br.com.alura.Xstream.ProdutoConverter;

public class CompraTest {

	@Test
	public void gerarProdutosDaCompra() {
		
		String resultadoEsperado = "<compra>\n"+
				"  <id>1</id>\n"+
				"  <produtos>\n"+
				"    <produto codigo=\"1587\">\n" +
		        "      <nome>geladeira</nome>\n" +
		        "      <preco>1000.0</preco>\n" +
		        "      <descrição>geladeira duas portas</descrição>\n" +
		        "    </produto>\n"+
		        "    <produto codigo=\"1588\">\n" +
		        "      <nome>ferro</nome>\n" +
		        "      <preco>60.0</preco>\n" +
		        "      <descrição>ferro de passar</descrição>\n" +
		        "    </produto>\n"+
		        "  </produtos>\n"+
		        "</compra>";
		
		Compra compra = getCompra();
		
		
		XStream xs = new XStream();
		
		xs.registerConverter(new CompraConverter());
		xs.alias("compra", Compra.class);
		xs.alias("produto", Produto.class);
		xs.denyTypeHierarchy(List.class);
		xs.aliasField("descrição", Produto.class, "descricao");
		xs.useAttributeFor(Produto.class, "codigo");
		
		String xml = xs.toXML(compra);
		
		assertEquals(resultadoEsperado, xml);
	}

	@Test
	public void gerarCompraDoXML() {
		
		String xml = "<compra>\n"+
				"  <id>1</id>\n"+
				"  <produtos>\n"+
				"    <produto codigo=\"1587\">\n" +
		        "      <nome>geladeira</nome>\n" +
		        "      <preco>1000.0</preco>\n" +
		        "      <descrição>geladeira duas portas</descrição>\n" +
		        "    </produto>\n"+
		        "    <produto codigo=\"1588\">\n" +
		        "      <nome>ferro</nome>\n" +
		        "      <preco>60.0</preco>\n" +
		        "      <descrição>ferro de passar</descrição>\n" +
		        "    </produto>\n"+
		        "  </produtos>\n"+
		        "</compra>";
		
		XStream xs = new XStream();
		
		xs.registerConverter(new CompraConverter());
		xs.alias("compra", Compra.class);
		xs.alias("produto", Produto.class);
		xs.aliasField("descrição", Produto.class, "descricao");
		xs.useAttributeFor(Produto.class, "codigo");
		
		Compra compraGerada = (Compra) xs.fromXML(xml);
		Compra compraEsperada = getCompra();
		
		assertEquals(compraEsperada, compraGerada);
	}
	
	
	@Test
	public void gerarCompraComObjetosIguais() {
		
		String resultado = "<compra>\n"+
				"  <id>1</id>\n"+
				"  <produtos>\n"+
				"    <produto codigo=\"1587\">\n" +
		        "      <nome>geladeira</nome>\n" +
		        "      <preco>1000.0</preco>\n" +
		        "      <descrição>geladeira duas portas</descrição>\n" +
		        "    </produto>\n"+
		        "    <produto codigo=\"1587\">\n" +
		        "      <nome>geladeira</nome>\n" +
				"      <preco>1000.0</preco>\n" +
				"      <descrição>geladeira duas portas</descrição>\n" +
				"    </produto>\n"+
		        "    <produto codigo=\"1588\">\n" +
		        "      <nome>ferro</nome>\n" +
		        "      <preco>60.0</preco>\n" +
		        "      <descrição>ferro de passar</descrição>\n" +
		        "    </produto>\n"+
		        "  </produtos>\n"+
		        "</compra>";
		
		XStream xs = new XStream();
		
		xs.registerConverter(new CompraConverter());
		xs.alias("compra", Compra.class);
		xs.alias("produto", Produto.class);
		xs.aliasField("descrição", Produto.class, "descricao");
		xs.useAttributeFor(Produto.class, "codigo");
		
		xs.setMode(XStream.NO_REFERENCES);
		Compra compra = getCompraDeObjetosIguais();
		
		String xml = xs.toXML(compra);
		
		assertEquals(resultado, xml);
	}
	
	@Test
	public void gerarColecaoImplicita() {
		
		String xmlEsperado = "<compra>\n"+
				"  <id>1</id>\n"+
				"  <produto codigo=\"1587\">\n" +
		        "    <nome>geladeira</nome>\n" +
		        "    <preco>1000.0</preco>\n" +
		        "    <descrição>geladeira duas portas</descrição>\n" +
		        "  </produto>\n"+
		        "  <produto codigo=\"1588\">\n" +
		        "    <nome>ferro</nome>\n" +
		        "    <preco>60.0</preco>\n" +
		        "    <descrição>ferro de passar</descrição>\n" +
		        "  </produto>\n"+
		        "</compra>";
		
		Compra compra = getCompra();
		
		XStream xs = new XStream();

		xs.alias("compra", Compra.class);
		xs.alias("produto", Produto.class);
		xs.aliasField("descrição", Produto.class, "descricao");
		xs.useAttributeFor(Produto.class, "codigo");

		xs.addImplicitCollection(Compra.class, "produtos");
		
		String xmlGerado = xs.toXML(compra);
		
		assertEquals(xmlEsperado, xmlGerado);
	}
	
	/*@Test//falta terminar
	public void gerarXMLComInterface() {
		String xmlEsperado = "<compra>\n"+
				"  <id>1</id>\n"+
				"  <produtos class=\"java.util.Arrays$ArrayList\">\n"+
				"    <livro codigo=\"1741\">\n" +
		        "      <nome>Os Lusiadas</nome>\n" +
		        "      <preco>30.0</preco>\n" +
		        "      <descrição>literatura brasileira</descrição>\n" +
		        "    </livro>\n"+
		        "    <musica codigo=\"1742\">\n" +
		        "      <nome>Meu Amor</nome>\n" +
		        "      <preco>5.0</preco>\n" +
		        "      <descrição>musica livre</descrição>\n" +
		        "    </musica>\n"+
		        "  </produtos>\n"+
		        "</compra>";

		
		Compra compra = getCompraDeMusicaELivro();
		
		XStream xs = new XStream();
		xs.alias("compra", Compra.class);
		xs.alias("produto", Produto.class, ArrayList.class);
		xs.useAttributeFor(Produto.class, "codigo");
		xs.aliasField("descrição", Produto.class, "descricao");
		xs.alias("livro", Livro.class);
		xs.alias("musica", Musica.class);
		
		String xmlGerado = xs.toXML(compra);
		
		assertEquals(xmlEsperado, xmlGerado);
		
	}*/
	/*@Test // Para colecoes explicitas é necessario usar o Converter
	public void gerarColecaoExplicita() {
		
		String resultadoEsperado = "<compra>\n"+
				"  <id>1</id>\n"+
				"  <produtos>\n"+
				"    <produto codigo=\"1587\">\n" +
		        "      <nome>geladeira</nome>\n" +
		        "      <preco>1000.0</preco>\n" +
		        "      <descrição>geladeira duas portas</descrição>\n" +
		        "    </produto>\n"+
		        "    <produto codigo=\"1588\">\n" +
		        "      <nome>ferro</nome>\n" +
		        "      <preco>60.0</preco>\n" +
		        "      <descrição>ferro de passar</descrição>\n" +
		        "    </produto>\n"+
		        "  </produtos>\n"+
		        "</compra>";
		
		Compra compra = getCompra();
		
		XStream xs = new XStream();

		xs.alias("compra", Compra.class);
		xs.alias("produto", Produto.class);
		xs.aliasField("descrição", Produto.class, "descricao");
		xs.aliasField("produtos", Compra.class, "produtos");
		//xs.addDefaultImplementation(List.class, Produto.class);
		xs.useAttributeFor(Produto.class, "codigo");

		String xml = xs.toXML(compra);
		
		assertEquals(resultadoEsperado, xml);
	}*/
	
	@Test
	public void gerarXMLdeObjetosComHeranca() {
		
		String xmlEsperado = "<compra>\n"+
				"  <id>1</id>\n"+
				"  <livro codigo=\"1741\">\n" +
		        "    <nome>Os Lusiadas</nome>\n" +
		        "    <preco>30.0</preco>\n" +
		        "    <descrição>literatura brasileira</descrição>\n" +
		        "  </livro>\n"+
		        "  <musica codigo=\"1742\">\n" +
		        "    <nome>Meu Amor</nome>\n" +
		        "    <preco>5.0</preco>\n" +
		        "    <descrição>musica livre</descrição>\n" +
		        "  </musica>\n"+
		        "</compra>";
		
		
		Compra compra = getCompraDeMusicaELivro();
		
		XStream xs = new XStream();

		xs.alias("compra", Compra.class);
		xs.alias("produto", Produto.class);
		xs.aliasField("descrição", Produto.class, "descricao");
		xs.useAttributeFor(Produto.class, "codigo");
		xs.alias("livro", Livro.class);
		xs.alias("musica", Musica.class);
		xs.addImplicitCollection(Compra.class, "produtos");
		
		String xmlGerado = xs.toXML(compra);
		
		assertEquals(xmlEsperado, xmlGerado);
	}
	
	@Test
	public void gerarConverterParaUmCampoDoObjeto() {
		
		String resultadoEsperado = "<compra>\n"+
				"  <id>1</id>\n"+
				"  <produto codigo=\"1587\">\n" +
		        "    <nome>geladeira</nome>\n" +
		        "    <preco>R$ 1.000,00</preco>\n" +
		        "    <descrição>geladeira duas portas</descrição>\n" +
		        "  </produto>\n"+
		        "  <produto codigo=\"1588\">\n" +
		        "    <nome>ferro</nome>\n" +
		        "    <preco>R$ 60,00</preco>\n" +
		        "    <descrição>ferro de passar</descrição>\n" +
		        "  </produto>\n"+
		        "</compra>";
		
		Compra compra = getCompra();
		
		XStream xs = new XStream();

		xs.alias("compra", Compra.class);
		xs.alias("produto", Produto.class);
		xs.aliasField("descrição", Produto.class, "descricao");
		xs.useAttributeFor(Produto.class, "codigo");
		xs.addImplicitCollection(Compra.class, "produtos");
		
		//xs.registerLocalConverter(Produto.class, "preco", new PrecoConverter());
		//OU
		xs.registerLocalConverter(Produto.class, "preco", new PrecoSimplesConverter());
		String xml = xs.toXML(compra);
		Compra compraGerada = (Compra) xs.fromXML(xml);
		
		assertEquals(resultadoEsperado, xml);
		assertEquals(compra, compraGerada);
	}
	
	@Test
	public void gerarXMLCustomizadoParaCompra() {
		
		String resultadoEsperado = "<compra>\n"+
				"  <id>1</id>\n"+
				"  <fornecedor id=\"1\">Sony</fornecedor>\n"+
				"  <produtos>\n"+
				"    <produto codigo=\"1587\">\n" +
		        "      <nome>geladeira</nome>\n" +
		        "      <preco>1000.0</preco>\n" +
		        "      <descrição>geladeira duas portas</descrição>\n" +
		        "    </produto>\n"+
		        "    <produto codigo=\"1588\">\n" +
		        "      <nome>ferro</nome>\n" +
		        "      <preco>60.0</preco>\n" +
		        "      <descrição>ferro de passar</descrição>\n" +
		        "    </produto>\n"+
		        "  </produtos>\n"+
		        "</compra>";
		
		Compra compra = getCompra();
		
		XStream xs = new XStream();

		xs.registerConverter(new ProdutoConverter());
		
		xs.alias("compra", Compra.class);
		xs.alias("produto", Produto.class);
		xs.aliasField("descrição", Produto.class, "descricao");
		xs.useAttributeFor(Produto.class, "codigo");
		
		String xml = xs.toXML(compra);
		assertEquals(resultadoEsperado, xml);
		
		Compra compraGerada = (Compra) xs.fromXML(resultadoEsperado);
		assertEquals(compra, compraGerada);
	}
	
	private Compra getCompra() {
		Produto geladeira = new Produto("geladeira", 1000.0, "geladeira duas portas", 1587);
		Produto ferro = new Produto("ferro", 60.0, "ferro de passar", 1588);
		Compra compra = new Compra(01, Arrays.asList(geladeira, ferro));
		return compra;
	}
	
	private Compra getCompraDeObjetosIguais() {
		Produto geladeira = new Produto("geladeira", 1000.0, "geladeira duas portas", 1587);
		Produto ferro = new Produto("ferro", 60.0, "ferro de passar", 1588);
		Compra compra = new Compra(01, Arrays.asList(geladeira, geladeira, ferro));
		return compra;
	}
	
	private Compra getCompraDeMusicaELivro() {
		Produto livro = new Livro("Os Lusiadas", 30.0, "literatura brasileira", 1741);
		Produto musica = new Musica("Meu Amor", 5.0, "musica livre", 1742);
		Compra compra = new Compra(01, Arrays.asList(livro, musica));
		return compra;
	}
}
