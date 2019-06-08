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

import static java.lang.System.out;

public class CatCli implements ICatCli, Serializable {
    private Map<String, IClient> catCli;

    /**
     * Construtor do Catalogo de Clientes
     * @param fp Ficheiro com informcação dos clientes
     */
    CatCli(String fp) throws IOException {
        List<String> clients = Files.readAllLines(Paths.get(fp), StandardCharsets.UTF_8);
        this.catCli = clients
                .stream()
                .map(Client::new)
                .collect(Collectors
                        .toMap(IClient::getId, Function.identity()));
    }

    /**
     * Verifica se um Cliente existe na base de dados
     * @param p ID do cliente a pesquisar
     * @return Se o cliente existe ou não
     */
    public boolean exists(String p) {
        return this
                .catCli
                .containsKey(p);
    }

    /**
     * Calcula o número de clientes exixtentes
     * @return Numero de clientes
     */
    public int howMany() {
        return this
                .catCli
                .size();
    }
}
