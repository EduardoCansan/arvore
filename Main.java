public class Main {

    public static void main(String[] args) {
        
        ArvoreBináriadeBusca arvore = new ArvoreBináriadeBusca();

        arvore.inserir(new Nodo(10));
        arvore.inserir(new Nodo(6));
        arvore.inserir(new Nodo(12));
        arvore.inserir(new Nodo(1));
        arvore.inserir(new Nodo(2));
        arvore.inserir(new Nodo(3));
        arvore.inserir(new Nodo(7));
        arvore.inserir(new Nodo(19));
        arvore.inserir(new Nodo(5));

        arvore.gerarArqDot("arvoreBin5.dot");

        System.out.println();

    }
}