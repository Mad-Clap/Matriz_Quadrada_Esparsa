<h1 align="center">Matrizes Quadradas Esparsas<h1>

# Matriz de vetores e listas encadeadas
A biblioteca de matrizes esparsas foi construída para teste da eficiência de se implementar uma estrutura de dados dinâmica (listas encadeadas), e uma estrutura de dados estática (vetor de vetores), para armazenamento de matrizes com alto grau de esparsidade .

O resultado do experimento demonstrou que é possível armazenar matrizes esparsas com mais colunas e linhas utilizando uma estrutura de dados dinâmica, enquanto uma matriz estática tende a estourar o limite de endereçamento de memória mais rapidamente, pois é utilizado um endereço de memória para armazenar elementos nulos da matriz. 


## Funcionalidades da biblioteca de Matrizes quadradas esparsas

### 1. Configurar matrizes esparsas, com 60% de esparsidade 
Configura as matrizes com estrutura de dados estática ou dinâmica da biblioteca para terem 60% de esparsidade, as preenchendo com números aleatórios, positivos ou negativos. 

### 2. Adição ou remoção de valores de uma matriz
É possível adicionar ou remover elementos de uma matriz após criá-la.
### 3. Busca por um valor na matriz
Procura pela primeira ocorrência na matriz de um elemento passado como parâmetro.
### 4. Operações com matrizes
É possível realizar operações com matrizes, sendo elas soma, multiplicação e transposição. Cada método gera uma nova matriz resultante da operação.

### 5. Verificação de matrizes 
A biblioteca possui métodos para verificar se uma matriz é vazia, simétrica, ou se é uma Matriz Diagonal, Linha, Coluna, Triangular Inferior ou Triangular Superior.
### 6. Impressão de matriz
A biblioteca possui método para impressão tanto das matrizes estáticas quanto das matrizes dinâmicas.

## Métodos da biblioteca 📚
### Criar matriz estática (vetores)
``MatrizEstatica matriz = new MatrizEstatica(inteiro)``

o inteiro será o número de linhas e colunas. 
### Criar matriz dinâmica (lista encadeada)
``MatrizEncadeada matriz = new MatrizEncadeada(inteiro)``

o inteiro será o número de linhas e colunas.

### Configurar matriz para ter 60% de esparsidade
``matriz.matrizRandom()``

### Inserir elemento na matriz
``matriz.inserirElemento(int linha,int coluna,int elemento)``

Insere ou sobrescreve o elemento na linha e coluna passadas como parâmetro na assinatura do método.

### Remover elemento da matriz
``matriz.removerElemento(int linha,int coluna)``

Remove, caso exista, o elemento na linha e coluna passadas como parâmetro na assinatura do método.

### Buscar elemento
``matriz.buscarElemento(int elemento)``

Retorna o número da linha e da coluna, nessa ordem, da primeira ocorrência do elemento na matriz, ou ``[-1,-1]``

### Imprimir matriz
``matriz.imprimeMatriz()``

### Imprimir uma matriz vazia do tamanho da matriz instanciada
``matriz.imprimeMatrizVazia()``

### Verificar se a matriz está vazia, apenas com elementos nulos
``matriz.verificaVazia()``

### Verificar se a matriz é uma Matriz Diagonal
``matriz.verificaMatrizDiagonal()``

Retorna ``true`` ou ``false``
### Verificar se a matriz é uma Matriz Triangula Inferior
``matriz.verificaMatrizTrInferior()``

Retorna ``true`` ou ``false``
### Verificar se a matriz é uma Matriz Triangula Superior
``matriz.verificaMatrizTrSuperior()``

Retorna ``true`` ou ``false``
### Verificar se a matriz é uma Matriz Linha
``matriz.verificaMatrizLinha()``

Retorna ``true`` ou ``false``
### Verificar se a matriz é uma Matriz Coluna
``matriz.verificaMatrizColuna()``

Retorna ``true`` ou ``false``
### Verificar se a matriz é uma Matriz Simétrica
``matriz.verificaSimetrica()``

Retorna ``true`` ou ``false``

### Somar a matriz instanciada à uma outra matriz
``matriz.somarMatrizes(outraMatriz)``


Retorna uma nova matriz (encadeada se forem somadas matrizes encadeadas, e estática se forem somadas matrizes estáticas) que é o resultado da soma das matrizes.

Não é possível somar uma matriz dinâmica com uma matriz estática e vice-versa.

### Multiplicar a matriz instanciada à uma outra matriz
``matriz.multiplicarMatrizes(outraMatriz)``


Retorna uma nova matriz (encadeada se forem multiplicadas matrizes encadeadas, e estática se forem multiplicadas matrizes estáticas) que é o resultado da multiplicação das matrizes.

Não é possível multiplicar uma matriz dinâmica com uma matriz estática e vice-versa.

### Obter Matriz transposta
``matriz.matrizTransposta()``

Retorna uma nova matriz que é o resultado da transposição da matriz pelo qual o método é chamado.

## Release 🚀
[Matriz quadrada Esparsa]([https://externo-pm.onrender.com](https://github.com/Mad-Clap/Matriz_Quadrada_Esparsa/releases/tag/MatrizEsparsa_1.0)) 🔗


## Tecnologias Utilizadas 🛠️
* Linguagem de programação JAVA

## Ambiente de Desenvolvimento 🧰

* SDK Oracle OpenJDK 17.0.6
* IntelliJ IDEA
