package Model;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.System.*;

/**
 * Classe referente ao Catalogo de Produtos
 */
public class CatProds implements ICatProds, Serializable {
    private Map<String, IProduct> catProds;

    /**
     * Construtor do catalogo de Produtos
     * @param fp Caminho do ficheiro com a informação referente aos Produtos
     */
    CatProds(String fp) throws IOException{
        List<String> produtos = Files.readAllLines(Paths.get(fp), StandardCharsets.UTF_8);
        this.catProds = produtos
                .stream()
                .map(Product::new)
                .collect(Collectors
                        .toMap(IProduct::getId, Function.identity()));
    }

    /**
     * Verifica se um produto existe na base de dados de Produtos
     * @param p Id do produto a procurar
     * @return Se o Produto existe ou não
     */
    public boolean exists(String p) {
        return this
                .catProds
                .containsKey(p);
    }

    /**
     * Calcula o número de produtos existentes
     * @return Número de produtos existentes
     */
    public int howMany() {
        return this
                .catProds
                .size();
    }

    /**
     * Calcula toda a lista de produtos
     * @return Lista de produto
     */
    public List<IProduct> productList() {
        return this
                .catProds
                .values()
                .stream()
                .map(IProduct::clone)
                .collect(Collectors.toList());
    }
}
