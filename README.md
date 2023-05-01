# PROJETO DE BUSCA EM CSV

<p>
Projeto Java desenvolvido para estudos de busca em arquivos CSV. 
Com ele, é possível especificar uma lista de parâmetros de busca e retornar uma lista de resultados correspondentes. 
O projeto utiliza a linguagem Java e foi desenvolvido com o intuito de aprimorar habilidades em programação orientada a objetos e manipulação de arquivos.
</p>

<p>
Através da implementação de uma série de buscas pré-definidas, o usuário pode facilmente buscar 
por um ou mais resultados que correspondem a critérios específicos. Além disso, o código é altamente flexível, permitindo a fácil adição de novas funcionalidades e parâmetros de busca.
</p>

<p>
O projeto foi desenvolvido utilizando uma arquitetura modular, separando as funcionalidades em diferentes classes e pacotes. Isso torna o código mais organizado, fácil de ler e de manter. 
Além disso, o projeto foi construído seguindo boas práticas de programação, garantindo a segurança e confiabilidade do código.
</p>

## IMPLEMENTAÇÃO.

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
O parâmetro file é uma String que representa o caminho para o arquivo CSV a ser lido. Já params é uma lista de objetos Param, que contêm informações sobre as colunas e valores que serão utilizados na busca.

Para criar um objeto Param, é possível utilizar o método estático of, que espera como parâmetros o nome da coluna, o valor que se deseja buscar e, opcionalmente, uma opção de busca que pode ser EQUALS para buscar valores exatos ou CONTAINS para buscar valores parciais. Caso nenhuma opção de busca seja informada, a busca padrão será por valores exatos (EQUALS).

```
final var params = List.of(Param.of("profession", "des"));
```

O código dentro do método estático ficaria assim:

```
public static Param of(final String column, final String value) {
    return new Param(column, value, Find.EQUALS);
}
```

Depois de criar uma nova instância de SearchCSV, existem dois métodos disponíveis para busca: findAll(), que retorna uma lista de todos os valores encontrados, e findOne(), que retorna apenas o primeiro valor encontrado que corresponde a todos os parâmetros especificados.

## PADRÃO STRATEGY E RECURSIVIDADE

O projeto foi desenvolvido com o intuito de explorar conceitos interessantes de programação, como o padrão Strategy e a recursividade, adicionando um nível de abstração que permitiu implementar uma solução mais complexa. Embora existam maneiras mais simples de resolver o problema, a inclusão desses conceitos tornou o desafio mais interessante e estimulante.

O uso de abstrações permitiu que algumas funcionalidades só fossem claramente visíveis em tempo de execução, como a interface IColumnSearch. Essa interface é implementada por cada coluna do arquivo CSV e contém um método findAll para realizar a busca. O código abaixo ilustra essa implementação:

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
Neste projeto, utilizamos o padrão Strategy para implementar diferentes algoritmos de busca em colunas específicas de um arquivo CSV. Isso é feito através do enum SearchType, que mapeia o nome da coluna passado como parâmetro para uma classe que implementa a interface IColumnSearch e define o algoritmo de busca a ser executado. Dessa forma, podemos facilmente adicionar novas colunas de busca apenas criando novas classes que implementam a interface IColumnSearch e adicionando uma nova entrada no enum SearchType correspondente.

```
final var searchType = SearchType.valueOf(param.getColumn())
```

Uma vez que temos uma lista de parâmetros de busca, para cada um desses parâmetros instanciamos um objeto IColumnSearch correspondente, 
resultando em uma lista de diferentes tipos de busca. Essa lista é então passada para uma função recursiva, 
juntamente com a lista de objetos convertidos do CSV. É nessa função que a busca é realizada, utilizando cada objeto 
IColumnSearch da lista para realizar a busca em cada objeto da lista de objetos convertidos.

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
A lógica da função recursiva é bastante simples: ela começa executando a primeira busca na lista de pessoas original. 
Em seguida, verifica se tanto a lista de buscas quanto a lista de pessoas ainda têm itens a serem processados. 
Caso positivo, a função é chamada novamente de forma recursiva, com a lista de buscas sem a última busca executada e a nova lista de pessoas encontradas. Esse processo continua até que uma das condições apresentadas anteriormente seja alcançada.

## Apêndice

<p>
Este codigo é apenas um exemplo de como podemos implementar buscas para nossos arquivos no formato CSV, ele não é flexivel ao ponto de funcionar para
qualquer CSV, tendo a necessidade de ter o codigo alterado para tal. Nesse exemplo o nosso CSV possui as seguintes colunas, "id", "fistname", "lastname",
"email", "profession", "city" e "details".
</p>

## Tecnologias

<div style="display: inline_block">
  <img align="center" alt="java" src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white" />
</div>

### :sunglasses: Autor

Criado por Leonardo Rodrigues Dantas.

[![Linkedin Badge](https://img.shields.io/badge/-Leonardo-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/leonardo-rodrigues-dantas/)](https://www.linkedin.com/in/leonardo-rodrigues-dantas/)
[![Gmail Badge](https://img.shields.io/badge/-leonardordnt1317@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:leonardordnt1317@gmail.com)](mailto:leonardordnt1317@gmail.com)

## Licença

Este projeto esta sobe a licença MIT.
