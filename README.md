# GithubPop
Um app para exibir a lista de repositórios de java no github.

### Arquitetura e tecnologias
  #### Linguagem: ```kotlin```
  #### Arquitetura: ```MVVM```
  #### Injeção de dependências: ```koin``` https://insert-koin.io/
  #### Testes instrumentados: ```Espresso```
  #### Testes unitários: ```JUnit e Mockito```
  #### Android Arch componentes: ```LiveData, ViewModel```
  #### API: ```Retrofit```
  #### Image loading: ```Glide```
  
## Módulos
O app foi modularizado com o intuito de separar as responsabilidades de cada classe.

Existem quatro módulos contando com o módulo app:

  #### app
Este é o módulo responsável pela apresentação dos dados, animações, listas e execução das Classes e Objetos do Android.
Nele temos ```activities, fragments, adapters, viewholders e viewModel```.
Aqui também estão os testes unitários referentes aos ```viewModel```'s
  #### data  
Módulo responsável pela implementação de chamadas de dados remotas usando o Retrofit, e local usando o RoomDatabase.
Aqui também estão os testes unitários referentes a essa camada de dados.
  #### design
Nessa camada estão os componentes de design do app, estilos, cores, dimensões etc.
Aqui também temos alguns testes instrumentados a fim de testar os componentes de design.
  #### domain
Módulo responsável pelas entidades e as regras de domínio específicas do projeto, totalmente independente da plataforma Android. 
 
