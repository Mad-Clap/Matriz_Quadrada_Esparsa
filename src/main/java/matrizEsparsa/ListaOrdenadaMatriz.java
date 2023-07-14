package matrizEsparsa;

public class ListaOrdenadaMatriz {
    protected Elo prim;

    protected class Elo {
        protected int index;
        protected int dado;
        protected Elo prox;

        public Elo()
        {
            prox = null;
        }

        public Elo(int elem)
        {
            index = elem;
            prox = null;
        }

        public Elo(int elem, int valor)
        {
            index = elem;
            dado = valor;
        }
    }

    public ListaOrdenadaMatriz()
    {
        prim = null;
    }

    public boolean vazia() { return prim == null;}

    public int insere(int index, int elem)
    {
        Elo p, q;
        Elo ant = null;
        q = new Elo(index, elem);
        int elementoAntigo = 0;

        for (p = prim; ((p != null) && (p.index < index)); p = p.prox)
            ant = p;

        //Se a posição já estiver ocupada, o método realiza um set e altera o dado do elo que representa o elemento,
        // senão ele funciona normalmente inserindo um novo elo na lista ordenada.
        // No final ele retorna o valor antigo da posição (0/nulo ou qualquer outro valor)
        if (ant == null){

            if(prim!=null && index == prim.index){
                //Aqui prim.index é a posição da coluna onde queremos setar o novo valor,
                // e prim.dado é onde o novo valor deverá ser alocado
                elementoAntigo = prim.dado;
                prim.dado = elem;
            }
            else{
                prim = q;
                q.prox = p;
            }
        }
        else{
            if (ant.prox != null){
                //Aqui ant.prox é a posição da coluna onde queremos setar o novo valor,
                // e ant.prox.dado é onde o novo valor será alocado.
                elementoAntigo = ant.prox.dado;
                ant.prox.dado=elem;
            }
            else {
                ant.prox = q;
                q.prox = p;
            }
        }

        return elementoAntigo;
    }


    /* Remove da lista o primeiro elemento com valor igual a "elem". Ret. true se removeu. */
    public boolean remove(int c)
    {
        Elo p;
        Elo ant = null; /* referência para anterior */

        //Se move pelos elos checando o index das colunas com elementos não nulos (que estão armazenadas)
        for(p = prim; ((p != null) && (p.index < c)); p = p.prox)
            ant = p;

        /* Se p.index != c ou p == null, então não encontrou a coluna. */
        if (p == null || p.index != c) return false;

        if (p == prim){
            prim = prim.prox; /* Remove coluna do início. */
        }
        else{
            ant.prox = p.prox;  /* Remove coluna do meio. */
        }


        /* Remove a última referência para o elo a ser removido. Dessa forma,
         * o Garbage Collector irá liberar essa memória. */
        p = null;
        return true;
    }

    // Busca pelas colunas na linha definida o elemento passado para o método.
    public int busca(int elem)
    {
        Elo p;

        for(p = prim; p != null; p = p.prox)
        {
            if(p.dado == elem)
                return p.index;
        }

        return -1;
    }

    public int tamanho(){
        int tam = 0;
        Elo p = prim;

        while(p != null)
        {
            tam++;
            p = p.prox;
        }

        return tam;
    }

    //NOVOS MÉTODOS

    //Impressão das linhas da coluna com os elementos nulos (0) também impressos.
    // Passa-se o tamanho da linha como parâmetro.
    public void imprimeLinha(int n) {

        Elo p;

        int elemento;
        int colAtual= 0;

        for(p = prim; p != null; p = p.prox) {
            //se prim não for igual a 0, na primeira iteração printa os zeros das primeiras
            // colunas da matriz. Nas outras iterações faz a mesma coisa para
            // possíveis colunas vazias no meio da linha. O inteiro "n" é diminuído a cada print
            if(p.index > colAtual){
                for(int i=0;i < p.index - colAtual;i++){
                    System.out.printf("  0  |");
                    n-=1;
                }

            }
            //printa os elementos armazenados na linha/lista encadeada ordenada.
            // O inteiro "n" é diminuido a cada print
            elemento = p.dado;
            n-=1;

            if(elemento < 0 && elemento > -10)
                System.out.printf(" %d  |",elemento);
            else if(elemento <= -10 && elemento > -100)
                System.out.printf(" %d |",elemento);
            else if(elemento >= 10 && elemento < 100)
                System.out.printf("  %d |",elemento);
            else if(elemento <= -100)
                System.out.printf("%d |",elemento);
            else if(elemento >= 100)
                System.out.printf(" %d |",elemento);

            else System.out.printf("  %d  |",elemento);


            colAtual=p.index+1;
        }

        //Caso o inteiro "n" não seja igual à zero,
        // significa que ainda há elemento(s) nulo(s) na linha à serem printados.
        // O próximo loop printa esses elementos no console.
        if( n!=0){
            for(int i =0;i< n;i++){
                System.out.printf("  0  |");
            }
        }
    }

    //Se a linha for menor que a coluna, significa que há elementos não nulos acima
    //da diagonal principal, se a linha for maior, há abaixo da diagonal, e se coluna e linha tiverem o mesmo
    //número então há elemento não nulos na diagonal principal. Os códigos de verificaLinhaDiagonal,
    // verificaLinhaT_Superior e verificaLinhaT_Inferior levam essas relações em consideração.

    public boolean verificaLinhaDiagonal(int l) {

        Elo p;
        for(p=prim; p!=null;p=p.prox){
            if(l < p.index || l > p.index) return false;
        }
        return true;
    }

    public boolean verificaLinhaT_Superior(int l) {

        Elo p;
        for(p=prim; p!=null;p=p.prox){
            if(l > p.index) return false;
        }
        return true;
    }

    public boolean verificaLinhaT_Inferior(int l) {

        Elo p;
        for(p=prim; p!=null;p=p.prox){
            if(l < p.index) return false;
        }
        return true;
    }

    //Verifica se há elementos não nulos em mais de uma coluna da matriz em uma linha.
    // Se houver qualquer elemento além do prim, significa que há elementos não nulos em mais de uma coluna,
    // logo a matriz não é uma matriz coluna. Se o dado no prim da linha (posição onde há
    // o elemento na linha) não for igual ao da linha anterior(passado pelo inteiro c), também não é matriz coluna.
    public int matrizColuna(int c){
        int temp = prim.index;
        if(c !=-1 && prim.index != c) return -1;
        if(prim.prox != null) return -1;

        return temp;
    }


    //Retorna o elemento da matriz na linha (definida pelo número da lista ordenada passado no vetor)
    // e coluna definida, mesmo se esse elemento for 0/nulo.
    public int getElemento(int c){
        Elo p;
        for(p=prim;p != null; p=p.prox){
            if(p.index == c) return p.dado;
        }
        return 0;
    }

    public int somaElemento(int c, ListaOrdenadaMatriz soma){
        Elo p, q;
        int elemento1=0, elemento2=0;
        p=prim;
        q = soma.prim;
        //Percorre as listas paralelamente e acha os valores nas colunas indicadas
        do{
            if(p!=null && p.index == c) elemento1 = p.dado;
            if(q!=null && q.index == c) elemento2 = q.dado;

            if(p!=null)p = p.prox;
            if(q!=null)q = q.prox;

            if(p != null && q != null && p.index>c && q.index>c) break;

        }while(p != null || q != null);

        return elemento1+elemento2;
    }

    public int multiplicaElemento(int c, int k, ListaOrdenadaMatriz multi){
        Elo p, q;
        int elemento1=0, elemento2=0;
        p=prim;
        q = multi.prim;

        //Percorre as listas paralelamente e acha os valores nas colunas indicadas
        do{
            if(p!=null && p.index == k) elemento1 = p.dado;
            if(q!=null && q.index == c) elemento2 = q.dado;

            if(p!=null)p = p.prox;
            if(q!=null)q = q.prox;

            if(p != null && q != null && p.index>k && q.index>c) break;

        }while(p != null || q != null);

        return elemento1*elemento2;
    }

    public boolean igualdadeElemento(int i, int j, ListaOrdenadaMatriz linhaSimetrica){
        Elo p, q = null;
        int elemento1=0, elemento2=0;
        p=prim;
        if(linhaSimetrica !=null) q = linhaSimetrica.prim;

        //Percorre as listas paralelamente e acha os valores nas colunas indicadas
        do{
            if(p!=null && p.index == i) elemento1 = p.dado;
            if(q!=null && q.index == j) elemento2 = q.dado;

            if(p!=null)p = p.prox;
            if(q!=null)q = q.prox;

            if(p != null && q != null && p.index>i && q.index>j) break;

        }while(p != null || q != null);
        return elemento1==elemento2;
    }

    public int[] elementosTranspostos(int i, int j, ListaOrdenadaMatriz linhaSimetrica){
        Elo p, q = null;
        int elemento1=0;
        int elemento2=0;
        int[] elementos = new int[2]; //vetor de duas posições que retorna os valores
        // a serem trocados um com o outro de posição na nova matriz transposta

        p=prim;
        if(linhaSimetrica !=null) q = linhaSimetrica.prim;

        //Percorre as listas paralelamente e acha os valores nas colunas indicadas
        do{
            if(p!=null && p.index == i) elemento1 = p.dado;
            if(q!=null && q.index == j) elemento2 = q.dado;

            if(p!=null)p = p.prox;
            if(q!=null)q = q.prox;

            if(p != null && q != null && p.index>i && q.index>j) break;

        }while(p != null || q != null);

        elementos[0]=elemento1;
        elementos[1]=elemento2;
        return elementos;
    }

}
