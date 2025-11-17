README - Projeto Android: IngressoCom

Descrição do Projeto

O projeto IngressoCom é uma aplicação Android que consome dados de uma API de filmes e exibe as informações relevantes como nome, data de estreia, poster, e organiza os filmes em duas categorias:

Destaques: Filmes que não têm data de estreia definida.
Filmes: Filmes que possuem data de estreia.

A navegação entre as categorias é realizada através de uma barra de navegação inferior (BottomNavigationView). A aplicação usa arquitetura MVVM (Model-View-ViewModel) para manter uma organização clara entre a lógica de negócios e a interface do usuário.

Decisões de Projeto
1. Estrutura de Dados e Organização de Camadas
Model-View-ViewModel (MVVM): Adotamos a arquitetura MVVM para separar a lógica de exibição e a manipulação de dados. A MainActivity é responsável por o consumo da API e repassar os dados para as ViewModels (Destaques e Filmes), que armazenam esses dados e os distribuem para os respectivos Fragments via LiveData.

Consumo da API usando a Biblioteca Volley: A API de filmes é acessada via uma requisição JsonObjectRequest, e os dados recebidos são processados e distribuídos para os Fragments através dos ViewModels.

Fragments com RecyclerView Horizontal: Os filmes são exibidos em listas horizontais (RecyclerView), sendo que ele suporta 3 filmes por linha, após ter 3 filmes ele quebra a linha e adiciona mais 3 filmes na linha de baixo.

2. Interface de Usuário
BottomNavigationView: A navegação entre as diferentes categorias é feita pela BottomNavigationView. A BottomNavigationView foi configurada para que os nomes fiquem sempre visíveis, utilizando labelVisibilityMode="labeled" no layout XML.

ProgressBar: Uma ProgressBar foi implementada para aparecer durante o carregamento dos dados da API e desaparece quando a requisição é concluída.

Tratamento de Imagens: Para exibir os pôsteres dos filmes, foi usada a biblioteca Glide, que carrega as imagens via URL fornecida pela API. Se o filme não tiver imagem, um ícone de placeholder é exibido.

3. Gerenciamento de Erros
Volley: A requisição via Volley trata possíveis erros de conexão com a API, exibindo logs no console em caso de falha.

Dados Opcionais: Para campos opcionais, como premiereDate e images, foi utilizada a função optJSONObject e optJSONArray, que não lançam exceções quando os campos são ausentes ou nulos.

4. Ordenação dos Filmes
A lista de filmes é ordenada pela data de estreia, começando com os filmes que têm a data de estreia mais próxima. Para isso, foi utilizada a data presente no campo localDate do premiereDate.


Tecnologias Utilizadas
Kotlin: Linguagem de programação principal do projeto.
Android Jetpack: Utilização do ViewModel e LiveData para garantir a arquitetura MVVM.
Volley: Biblioteca de rede para requisições HTTP.
Glide: Biblioteca para carregamento de imagens a partir de URLs.
RecyclerView: Componente para exibir listas dinâmicas de filmes.
BottomNavigationView: Componente de navegação entre os fragments do app.
Como Rodar o Projeto
Pré-requisitos
Android Studio (preferencialmente a versão mais recente)
JDK 8 ou superior
Passos para rodar o projeto:
Clonar o Repositório: Clone este repositório em sua máquina local:

bash
Copiar código
git clone <URL_DO_REPOSITORIO>
Abrir o Projeto no Android Studio:

No Android Studio, clique em File > Open e selecione a pasta onde você clonou o projeto.
Instalar as Dependências:

O Android Studio deve automaticamente sincronizar o projeto e baixar as dependências necessárias. Caso isso não ocorra, clique em File > Sync Project with Gradle Files.
Executar o Projeto:

Certifique-se de que um dispositivo Android ou emulador está conectado e funcionando.
Clique no botão Run ou use o atalho Shift + F10 para compilar e executar o projeto.
Testar a Aplicação:

A aplicação será lançada no dispositivo/emulador e você poderá testar as funcionalidades de navegação entre os Fragments de Destaques e Filmes, bem como visualizar os filmes recuperados da API.

Possíveis Problemas e Soluções:
Erro de Dependências: Caso o Android Studio apresente problemas de dependências, vá para o arquivo build.gradle (no módulo app) e verifique se as versões das bibliotecas estão corretas.
Emulador não Inicia: Certifique-se de que o AVD (Android Virtual Device) está corretamente configurado e que possui os requisitos mínimos para rodar o projeto (Android 6.0 ou superior).

Melhorias Futuras

Testes Unitários: Incluir testes unitários para as ViewModels e para a lógica de filtragem de filmes.
Melhorar Tratamento de Erros: Mostrar mensagens de erro na interface do usuário em vez de apenas logs no console.
