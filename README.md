# Projeto de Busca em CSV

Projeto Java desenvolvido para estudos de busca em arquivos CSV. 
Com ele, é possível especificar uma lista de parâmetros de busca e retornar uma lista de resultados correspondentes. 
O projeto utiliza a linguagem Java e foi desenvolvido com o intuito de aprimorar habilidades em programação orientada a objetos e manipulação de arquivos.
Através da implementação de uma série de buscas pré-definidas, o usuário pode facilmente buscar 
por um ou mais resultados que correspondem a critérios específicos. Além disso, o código é altamente flexível, permitindo a fácil adição de novas funcionalidades e parâmetros de busca.
O projeto foi desenvolvido utilizando uma arquitetura modular, separando as funcionalidades em diferentes classes e pacotes. Isso torna o código mais organizado, fácil de ler e de manter. 
Além disso, o projeto foi construído seguindo boas práticas de programação, garantindo a segurança e confiabilidade do código.

Este codigo é apenas um exemplo de como podemos implementar buscas para nossos arquivos no formato CSV, ele não é flexivel ao ponto de funcionar para
qualquer CSV, tendo a necessidade de ter o codigo alterado para tal. Nesse exemplo o nosso CSV possui as seguintes colunas, "id", "fistname", "lastname",
"email", "profession", "city" e "details".

# Utilização
Para utilizarmos a busca, precisamos de uma nova instancia da classe SearchCSV, que pode ser obtida atraves do metodo
estatico of, disponibilizado pela propria classe. 

```
final var searchCSV = SearchCSV.of(file, params);
```
O metodo estatico of espera dois parametros, o primeiro é o caminho de nosso CSV, e o segundo é
uma lista de parametros que desejamos realizar a busca. Desta forma:

```
final var file = "/home/leo-dantas/Downloads/person.csv";

final var params = List.of(Param.of("profession", "des", Find.CONTAINS));
```
O parametro file é do tipo String, enquanto params é uma lista de Param. Para a criação de um Param
tambem temos o metodo estatico of, onde esperamos o nome da coluna, o valor para buscarmos e por 
ultimo de forma opcional podemos dizer se o valor de busca deve ser igual com o enum Find.EQUALS, ou
se a coluna deve apenas conter o valor com o enum Find.CONTAINS, caso nenhum valor de enum seja atribuido
então o valor padrão será o Find.EQUALS.

```
final var params = List.of(Param.of("profession", "des"));
```

O codigo dentro do metodo estatico ficaria dessa maneira:

```
public static Param of(final String column, final String value) {
    return new Param(column, value, Find.EQUALS);
}
```

Apos instanciar um novo objeto SearchCSV, teremos dois metodos para busca, o findAll que devolve uma lista de todos os valores
encontrados, e o metodo findOne, que devolve apenas o primeiro valor encontrado que condiz com todos os valores de parametros.

# Padrão Strategy e Recursividade

Para esse projeto busquei utilizar conceitos interessantes de programação, como o padrão Strategy e recursividade, acredito
que tudo poderia ser feito de uma forma mais simples, entretanto acredito que o desafio de implementar esses conceitos
deixou o desafio mais interessante.

Nesse projeto trabalhamos com um nivel de abstração em que algumas coisas só são bem visiveis em tempo de execução. Como por exemplo,
temos uma interface chamada IColumnSeach, onde cada coluna existente em nosso CSV, implementa essa interface com o seu metodo findAll, 
como no codigo abaixo:

```
public class EmailColumnSearch implements IColumnSearch {

    private final String value;
    private final Find find;

    private EmailColumnSearch(final String value, final Find find) {
        this.value = value;
        this.find = find;
    }

    public static IColumnSearch of(final String value, final Find find) {
        return new EmailColumnSearch(value, find);
    }

    @Override
    public List<Person> findAll(final List<Person> persons) {
        return persons.stream()
                .filter(person -> find.execute(person.getEmail(), value))
                .collect(Collectors.toList());
    }
```

Essas classes de buscas são instanciadas atraves de nosso padrão strategy, que pode ser observado no enum SearchType, a partir do nome
da coluna que passamos em nossa lista de parametros.

```
final var searchType = SearchType.valueOf(param.getColumn())
```

Por fim teremos uma lista de IColumnSeach, um tipo de busca para cada parametro, onde cada objeto
dessa lista possui o seu tipo de implementação a ser executada.

A nossa busca é realizada dentro de uma função recursiva, que recebe como parametro, a lista de
IColumnSeach e a lista de objetos convertidos do CSV.

```
recursiveSearchAll(new ArrayList<>(searches), persons);
```
Essa é a implementação de nossa função recursiva:

```
private static List<Person> recursiveSearchAll(final List<IColumnSearch> searches, final List<Person> persons) {
     final var search = searches.stream().findFirst().orElseThrow();
     final var personsFound = search.findAll(persons);
     searches.remove(search);

     if (searches.isEmpty() || personsFound.isEmpty()) {
            return personsFound;
     }

     return recursiveSearchAll(searches, personsFound);
}
```
A função recursiva funciona de forma simples, ela busca o primeiro search da lista e executa a execução sobre a lista inicial, 
caso a lista de busca e a lista de pessoas ainda não esteja vazia, a lista de searchs e enviada de forma recursiva para
a mesma função, porem sem a ultima busca executada, e a nova lista de pessoas tambem é enviada até que uma das condições
apresentadas anteriormente seja alcançada.







