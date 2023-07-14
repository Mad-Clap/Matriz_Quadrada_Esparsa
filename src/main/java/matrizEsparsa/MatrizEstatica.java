package matrizEsparsa;

import java.util.Random;

public class MatrizEstatica {
    private final int n;
    private final int[][] vet;

    public MatrizEstatica(int N) {
        this.n = N;
        this.vet = new int[N][N];
    }


    public void matrizRandom(){

        double N = (double) n;
        double M1 = ((N * N *4)/10); //Se 60% deve ser 0/nulo, então 40% deve ser preenchido;
        int m = (int) Math.floor(M1);

        //Loop para preencher a matriz randomicamente
        int i=0;
        while (i<m){
            //Gerando números aleatórios positivos para linha e coluna, de 0 à N-1.
            int l = new Random().nextInt(n);
            int c = new Random().nextInt(n);

            //Gerando números aleatórios positivos ou negativos para o valor a ser adicionado na matriz
            int valor = new Random().nextInt(10) + 1;
            valor = valor*(Math.random() > 0.2 ? 1 : -1);

            //Caso seja sorteada uma posição já preenchida, o loop reinicia daqui.

            if (this.vet[l][c] !=0)continue;

            // Adiciona um valor na posição sorteada.
            this.vet[l][c] = valor;

         //incrementa i, de forma que o loop possa acabar.
         i++;
        }
    }

    public void inserirElemento(int l, int c, int valor) {
        if((l< n && c < n) && (l>-1 && c>-1)){

            if(this.vet[l][c]!=0) System.out.println("Elemento "+vet[l][c]+" sobrescrito por "+valor);
            else System.out.println("Valor inserido na matriz");


            vet[l][c] = valor;
        }
        else System.out.println("número de linha e coluna inexistente");
    }

    public void removerElemento(int l,int c){
        if(vet[l][c]==0){
            System.out.println("Não há um elemento nessa posição");
            return;
        }
        vet[l][c]=0;
        System.out.println("Elemento removido da posição");

    }

    public int[] buscarElemento(int elemento){
        int [] res = new int [2];
        for(int l = 0; l< n; l++){
            for(int c = 0; c< n; c++){
                if(this.vet[l][c]==elemento){
                    res[0]=l;
                    res[1]=c;
                    return res;
                }
            }
        }
        res[0]=-1;
        res[1]=-1;
        return res;
    }



    public void imprimeMatriz(){
        //Imprime a matriz organizadamente de -999 à 999
        for (int i = 0; i<this.n; i++){
            for(int j = 0; j<this.n; j++){

                if(vet[i][j] < 0 && vet[i][j] > -10)
                    System.out.printf(" %d  |",vet[i][j]);
                else if(vet[i][j] <= -10 && vet[i][j] > -100)
                    System.out.printf(" %d |",vet[i][j]);
                else if(vet[i][j] >= 10 && vet[i][j] < 100)
                    System.out.printf("  %d |",vet[i][j]);
                else if(vet[i][j] <= -100)
                    System.out.printf("%d |",vet[i][j]);
                else if(vet[i][j] >= 100)
                    System.out.printf(" %d |",vet[i][j]);

                else System.out.printf("  %d  |",vet[i][j]);


            }
            System.out.print("\n");
        }
    }

    public void imprimeMatrizVazia(){
        //imprime uma matriz vazia do tamanho da matriz que chama o método
        for (int i = 0; i<this.n; i++){
            for(int j = 0; j<this.n; j++){
                System.out.printf("  0  |");
            }
            System.out.print("\n");
        }
    }

    public boolean verificaVazia() {
        for(int l = 0; l< n; l++){
            for(int c = 0; c< n; c++){
                if(vet[l][c]!=0) return false;
            }
        }

        return true;
    }

    //Se a linha for menor que a coluna, significa que há elementos não nulos acima
    //da diagonal principal, se a linha for maior, há abaixo da diagonal, e se coluna e linha tiverem o mesmo
    //número então há elemento não nulos na diagonal principal. Os códigos de verificaMatrizDiagonal,
    // verificaMatrizT_Superior e verificaMatrizT_Inferior levam essas relações em consideração.

    public boolean verificaMatrizDiagonal() {

        for(int l = 0; l< n; l++){
            for(int c = 0; c< n; c++){
                if(vet[l][c]!=0){
                    if(l<c || l>c)return false;
                }
            }
        }
        return true;
    }

    public boolean verificaMatrizTrSuperior() {

        for(int l = 0; l< n; l++){
            for(int c = 0; c< n; c++){
                if(vet[l][c]!=0){
                    if(l>c) return false;
                }
            }
        }
        return true;
    }

    public boolean verificaMatrizTrInferior() {

        for(int l = 0; l< n; l++){
            for(int c = 0; c< n; c++){
                if(vet[l][c]!=0){
                    if(l<c) return false;
                }
            }
        }
        return true;
    }

    public boolean verificaMatrizLinha() {

        int linhaAnterior = -1;

        for(int l = 0; l< n; l++){

            for(int c = 0; c< n; c++){

                if(vet[l][c]!=0){
                    if(linhaAnterior!=-1 && l != linhaAnterior) return false;
                    linhaAnterior = l;
                }

            }
        }
        return true;
    }

    public boolean verificaMatrizColuna() {

        int colunaAnterior = -1;

        for(int l = 0; l< n; l++){

            for(int c = 0; c< n; c++){

                if(vet[l][c]!=0){
                        if(colunaAnterior!=-1 && c != colunaAnterior) return false;
                        colunaAnterior=c;
                }


            }
        }
        return true;
    }

    public boolean verificaSimetrica() {
        int checado=-1;
        for(int l = 0; l< n; l++){
            checado++;
            for(int c = checado; c< n; c++){
                if(l==c)continue;
                if(vet[l][c]!=vet[c][l]){
                    return false;
                }
            }
        }
        return true;
    }

    public MatrizEstatica somarMatrizes(MatrizEstatica S){
        if(S.n != this.n) {
            System.out.println("Não é possível somar matrizes de ordens diferentes");
            return null;
        }

        MatrizEstatica soma = new MatrizEstatica(n);
        //soma os valores
        for (int l = 0; l< n; l++){
            for (int c = 0; c< n; c++){
                int valor = vet[l][c] + S.vet[l][c];
                soma.vet[l][c] = valor;
            }
        }

        return soma;
    }

    public MatrizEstatica multiplicarMatrizes(MatrizEstatica M){

        if(M.n != this.n) {

            System.out.println("Não é possível multiplicar matrizes com número de" +
                    "linhas e colunas diferentes (o número de linhas de uma matriz deve ser igual ao número de colunas da outra)");


            return null;
        }
        //multiplica os valores
        MatrizEstatica multi = new MatrizEstatica(n);
        for(int l = 0; l< n; l++){
            for(int c = 0; c< n; c++){
                int resultado=0;

                for(int k = 0; k< n; k++){
                    resultado+=vet[l][k]* M.vet[k][c];
                }

                multi.vet[l][c] = resultado;
            }

        }
        return multi;
    }

    public MatrizEstatica matrizTransposta(){

        MatrizEstatica transposta = new MatrizEstatica(n);
        int inserido = -1;
        for(int l = 0; l< n; l++){
            inserido++;
            for(int c = inserido; c< n; c++){
                if(l>c || l==c){
                    transposta.vet[l][c] = vet[c][l];
                    transposta.vet[c][l] = vet[l][c];
                }
            }
        }

        return transposta;
    }

}
