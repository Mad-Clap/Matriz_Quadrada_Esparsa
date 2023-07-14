package trabalhoFinal;

import java.util.Random;

public class MatrizEncadeada {
    private final int n;
    private final ListaOrdenadaMatriz[] vet;

    public MatrizEncadeada(int N) {
        this.n = N;
        this.vet = new ListaOrdenadaMatriz[N];

    }

    public void matrizRandom(){
        double N = (double) n;
        double M1 = ((N * N *4)/10); //Se 60% deve ser 0/nulo, então 40% deve ser preenchido;
        int m = (int) Math.floor(M1);

        //Loop para preencher a matriz randomicamente
        int i=0;
        while (i<m){
            //Gerando números aleatórios positivos para linha e coluna, de 0 à N-1.
            int l=new Random().nextInt(n);
            int c = new Random().nextInt(n);

            //Cria uma nova lista ordenada se necessário
            if(vet[l] == null) vet[l] = new ListaOrdenadaMatriz();

            //Gerando números aleatórios positivos ou negativos para o valor a ser adicionado na matriz
            int valor = new Random().nextInt(10) + 1;
            valor = valor*(Math.random() > 0.2 ? 1 : -1);

            //Caso seja sorteada uma posição já preenchida, o loop reinicia daqui.
            if (this.vet[l].getElemento(c) !=0)continue;


            // Adiciona um valor na posição sorteada.
            vet[l].insere(c,valor);

            //incrementa i, de forma que o loop possa acabar.

            i++;
        }
    }

    public void inserirElemento(int l, int c, int valor) {
        if(valor==0) return;

        if((l< n && c < n) && (l>-1 && c>-1)){
            //Cria uma nova lista ordenada se necessário
            if(vet[l] == null) vet[l] = new ListaOrdenadaMatriz();


            int antigoElemento = vet[l].insere(c,valor);

            if(antigoElemento !=0){
                System.out.println("Elemento "+antigoElemento+" sobrescrito por "+valor);
            }
            else System.out.println("Valor inserido na matriz");


        }
        else System.out.println("número de linha e coluna inexistente");
    }

    public void removerElemento(int l, int c){
            if(vet[l] == null) return;
            vet[l].remove(c);

            if(vet[l].remove(c)) System.out.println("Elemento removido da posição");
            else System.out.println("Não há um elemento nessa posição");

            if(vet[l].vazia()) vet[l] = null;
    }

    //Itera pelas linhas, chamando o método busca() da classe ListaOrdenadaMatriz
    public int[] buscarElemento(int elemento){
        int [] res = new int [2];

        for(int l = 0; l< n; l++){
            if(vet[l] == null) continue;
            int c = vet[l].busca(elemento);
            if(c!=-1){
                res[0]=l;
                res[1]=c;
                return res;
            }
        }

        res[0]=-1;
        res[1]=-1;
        return res;
    }


    public void imprimeMatriz(){
        //imprime linhas vazias/ só com 0
        for(int l = 0; l< n; l++){
            if(vet[l] == null){
                for (int j = 0; j< n; j++){
                    System.out.printf("  0  |");
                }
                System.out.print('\n');
                continue;
            }
            //imprime linhas com elementos
            vet[l].imprimeLinha(n);
            System.out.print("\n");
        }
    }

    public void imprimeMatrizVazia(){
        for (int i = 0; i<this.n; i++){
            for(int j = 0; j<this.n; j++){
                System.out.printf("  0  |");
            }
            System.out.print("\n");
        }
    }
    public boolean verificaVazia(){
        for (int l=0;l<n;l++){
            if(vet[l] != null) return false;
        }
        return true;
    }


    //Se a linha for menor que a coluna, significa que há elementos não nulos acima
    //da diagonal principal, se a linha for maior, há abaixo da diagonal, e se coluna e linha tiverem o mesmo
    //número então há elemento não nulos na diagonal principal. Os códigos de verificaMatrizDiagonal,
    // verificaMatrizT_Superior e verificaMatrizT_Inferior levam essas relações em consideração.

    public boolean verificaMatrizDiagonal() {
        for(int l = 0; l< n; l++){
            if(vet[l] !=null) {
                if (!this.vet[l].verificaLinhaDiagonal(l)) return false;
            }
        }
        return true;
    }

    public boolean verificaMatrizTrSuperior() {

        for(int l = 0; l< n; l++){
            if(vet[l] !=null) {
                if (!this.vet[l].verificaLinhaT_Superior(l)) return false;
            }
        }
        return true;
    }

    public boolean verificaMatrizTrInferior() {

        for(int l = 0; l< n; l++){
            if(vet[l] !=null) {
                if (!vet[l].verificaLinhaT_Inferior(l)) return false;
            }
        }
        return true;
    }

    public boolean verificaMatrizColuna(){

        int c=-1;
        for(int l = 0; l< n; l++){
            if(vet[l] !=null){
                c = vet[l].matrizColuna(c);
                if(c == -1) return false;
            }
        }
        return true;
    }

    public boolean verificaMatrizLinha(){
        //Conta quantas linhas com elementos há na matriz, começando com 0 (sem linhas com elementos).
        // Se passar de uma linha o método retorna false,
        // e após o loop se a quantidade de linhas não for 1 (no caso se for 0) também retorna false.
        int linha =0;
        for(int l=0; l<n;l++){
            if(vet[l] != null ) linha+=1;
            if (linha>1) return false;
        }
        return linha == 1;
    }


    //VERIFICA SIMÉTRICA.
    public boolean verificaSimetrica(){
        int checado=-1;
        for(int l=0; l<n; l++){
            checado++;
            for(int c=checado;c<n;c++){
                if(l==c)continue;
                if(vet[l] == null && vet[c] == null) continue;

                if(vet[l] != null){
                    if(!vet[l].igualdadeElemento(c,l,vet[c])) return false;
                }
                 else{
                        if(!vet[c].igualdadeElemento(l,c,vet[l])) return false;
                    }
            }
        }
        return true;
    }

    //SOMA MATRIZES.
    public MatrizEncadeada somarMatrizes(MatrizEncadeada S){
        if(S.n != this.n) {
            System.out.println("Não é possível somar matrizes de ordens diferentes");
            return null;
        }


        MatrizEncadeada soma = new MatrizEncadeada(n);
        int elemento;

        for(int l = 0; l< n; l++){
            if(vet[l] == null && S.vet[l] == null) continue;

            for(int c = 0; c< n; c++){

                if(vet[l] == null) {
                    elemento = S.vet[l].getElemento(c);
                } else if(S.vet[l] == null){
                    elemento = vet[l].getElemento(c);
                }
                else
                    elemento = vet[l].somaElemento(c,S.vet[l]);

                // insere o valor na nova matriz, caso ele não seja 0/nulo
                if(elemento !=0) {
                    if(soma.vet[l] == null) soma.vet[l] = new ListaOrdenadaMatriz();
                    soma.vet[l].insere(c,elemento);
                }
            }
        }
        return soma;
    }


    //MULTIPLICAR MATRIZES.
    public MatrizEncadeada multiplicarMatrizes(MatrizEncadeada M){
        if(M.n != this.n) {

            System.out.println("Não é possível multiplicar matrizes com número de" +
                    "linhas e colunas diferentes (o número de linhas de uma matriz deve ser igual ao número de colunas da outra)");


            return null;
        }

        MatrizEncadeada multi = new MatrizEncadeada(n);

        for(int l = 0; l< n; l++){
            if(vet[l] == null) continue;

            for(int c = 0; c< n; c++){
                int resultado=0;

                for(int k = 0; k< n; k++){
                    if(M.vet[k] == null){
                        resultado+=0;
                    }
                    else
                        resultado+=vet[l].multiplicaElemento(c,k,M.vet[k]);
                }

                // insere o valor na nova matriz, caso ele não seja 0/nulo
                if(resultado!=0){
                    if(multi.vet[l] == null) multi.vet[l] = new ListaOrdenadaMatriz();
                    multi.vet[l].insere(c,resultado);
                }
            }

        }


        return multi;
    }

    public MatrizEncadeada matrizTransposta(){

        MatrizEncadeada transposta = new MatrizEncadeada(n);

        int[] elementos;

        int inserido=-1;
        for(int l=0; l<n; l++){
            inserido++;
            for(int c=inserido;c<n;c++){
                if(vet[l] == null && vet[c] == null) continue;

                if(vet[l] != null){
                    //o método chamado retorna um vetor de duas posições com os elementos a serem inseridos na matriz
                    elementos = vet[l].elementosTranspostos(c,l,vet[c]);

                    //Adiciona os elementos na matriz transposta
                    if(elementos[0]!=0){
                        if (transposta.vet[c] == null) transposta.vet[c] = new ListaOrdenadaMatriz();
                        transposta.vet[c].insere(l,elementos[0]);
                    }
                    if(elementos[1]!=0 && l!=c){
                        if (transposta.vet[l] == null) transposta.vet[l] = new ListaOrdenadaMatriz();
                        transposta.vet[l].insere(c,elementos[1]);
                    }
                }
                else{
                    //o método chamado retorna um vetor de duas posições com os elementos a serem inseridos na matriz
                    elementos = vet[c].elementosTranspostos(l,c,vet[l]);

                    //Adiciona os elementos na matriz transposta
                    if(elementos[1]!=0){
                        if (transposta.vet[c] == null) transposta.vet[c] = new ListaOrdenadaMatriz();
                        transposta.vet[c].insere(l,elementos[1]);
                    }
                    if(elementos[0]!=0 && l!=c){
                        if (transposta.vet[l] == null) transposta.vet[l] = new ListaOrdenadaMatriz();
                        transposta.vet[l].insere(c,elementos[0]);
                    }
                }

            }
        }
        return transposta;
    }

}
