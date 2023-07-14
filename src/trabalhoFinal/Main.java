package trabalhoFinal;
import java.util.Random;

public class Main {


    public static void main(String[] args) {
        //tamanho n de NxN da matriz
        int n = 4;

        MatrizEncadeada teste = new MatrizEncadeada(n);
        //MatrizEstatica teste = new MatrizEstatica(n);

        teste.matrizRandom();

        teste.imprimeMatriz();

        //TEMPO MEDIO DE VERIFICAR SE A MATRIZ É UMA MATRIZ SIMETRICA
        long mediaSimetrica=0;
        for (int i = 1; i <= 10; i++){
            long t0 = System.nanoTime();
            teste.verificaSimetrica();
            long t1 = System.nanoTime();
            mediaSimetrica += (t1 - t0);
        }

        mediaSimetrica=mediaSimetrica/10;


        //TEMPO MEDIO DE INSERIR ELEMENTO NA MATRIZ
        long mediaInsere=0;
        for (int i = 1; i <= 10; i++){
            int l = new Random().nextInt(n);
            int c = new Random().nextInt(n);

            long t0 = System.nanoTime();
            teste.inserirElemento(l,c,7);
            long t1 = System.nanoTime();
            mediaInsere += (t1 - t0);
        }

        mediaInsere=mediaInsere/10;

        //TEMPO MEDIO DE REMOVER ELEMENTO DA MATRIZ
        long mediaRemove=0;
        for (int i = 1; i <= 10; i++){
            int l = new Random().nextInt(n);
            int c = new Random().nextInt(n);

            long t0 = System.nanoTime();
            teste.removerElemento(l,c);
            long t1 = System.nanoTime();
            mediaRemove += (t1 - t0);
        }

        mediaRemove=mediaRemove/10;

        //TEMPO MEDIO DE BUSCAR ELEMENTO NA MATRIZ
        long mediaBusca=0;
        for (int i = 1; i <= 10; i++){
            int b = new Random().nextInt(10)+1;
            long t0 = System.nanoTime();
            teste.buscarElemento(b);
            long t1 = System.nanoTime();
            mediaBusca += (t1 - t0);
        }

        mediaBusca=mediaBusca/10;

        //TEMPO MEDIO DE VERIFICA SE A MATRIZ É VAZIA
        long mediaVerificaVazia=0;
        for (int i = 1; i <= 10; i++){
            long t0 = System.nanoTime();
            teste.verificaVazia();
            long t1 = System.nanoTime();
            mediaVerificaVazia += (t1 - t0);
        }

        mediaVerificaVazia=mediaVerificaVazia/10;

        //TEMPO MEDIO DE VERIFICAR SE A MATRIZ É UMA MATRIZ DIAGONAL
        long mediaVerificaDiagonal=0;
        for (int i = 1; i <= 10; i++){
            long t0 = System.nanoTime();
            teste.verificaMatrizDiagonal();
            long t1 = System.nanoTime();
            mediaVerificaDiagonal += (t1 - t0);
        }

        mediaVerificaDiagonal=mediaVerificaDiagonal/10;


        //TEMPO MEDIO DE VERIFICAR SE A MATRIZ É UMA MATRIZ COLUNA
        long mediaVerificaColuna=0;
        for (int i = 1; i <= 10; i++){
            long t0 = System.nanoTime();
            teste.verificaMatrizColuna();
            long t1 = System.nanoTime();
            mediaVerificaColuna += (t1 - t0);
        }

        mediaVerificaColuna=mediaVerificaColuna/10;

        //TEMPO MEDIO DE VERIFICAR SE A MATRIZ É UMA MATRIZ TRIANGULAR INFERIOR
        long mediaVerificaT_Inferior=0;
        for (int i = 1; i <= 10; i++){
            long t0 = System.nanoTime();
            teste.verificaMatrizTrInferior();
            long t1 = System.nanoTime();
            mediaVerificaT_Inferior += (t1 - t0);
        }

        mediaVerificaT_Inferior= mediaVerificaT_Inferior/10;

        //TEMPO MEDIO DE SOMAR MATRIZES
        long mediaSoma=0;
        for (int i = 1; i <= 10; i++){
            long t0 = System.nanoTime();
            teste.somarMatrizes(teste);
            long t1 = System.nanoTime();
            mediaSoma += (t1 - t0);
        }

        mediaSoma=mediaSoma/10;

        //TEMPO MEDIO DE OBTER MATRIZ TRANSPOSTA
        long mediaTransposta=0;
        for (int i = 1; i <= 10; i++){
            long t0 = System.nanoTime();
            teste.matrizTransposta();
            long t1 = System.nanoTime();
            mediaTransposta += (t1 - t0);
        }

        mediaTransposta=mediaTransposta/10;



        if(MatrizEstatica.class.isInstance(teste)) System.out.println("Matriz estática "+n+"X"+n);
        else System.out.println("Matriz dinâmica "+n+"X"+n);

        System.out.println("Tempo médio insere: "+mediaInsere);
        System.out.println("Tempo médio remove: "+mediaRemove);
        System.out.println("Tempo médio busca: "+mediaBusca);
        System.out.println("Tempo médio verifica vazia: "+mediaVerificaVazia);
        System.out.println("Tempo médio verifica diagonal: "+mediaVerificaDiagonal);
        System.out.println("Tempo médio verifica coluna: "+mediaVerificaColuna);
        System.out.println("Tempo médio verifica triangular inferior: "+mediaVerificaT_Inferior);
        System.out.println("Tempo médio verifica simétrica: "+mediaSimetrica);
        System.out.println("Tempo médio soma: "+mediaSoma);
        System.out.println("Tempo médio transposta: "+mediaTransposta);
    }

}
