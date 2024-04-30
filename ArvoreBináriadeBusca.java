import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ArvoreBináriadeBusca {

    Nodo raiz;

    public void inserir(Nodo nodo) {
        raiz = inserir(raiz, nodo);
    }
    private Nodo inserir(Nodo raiz, Nodo nodo) {
        int chave = nodo.chave;

        if(raiz == null) {
            raiz = nodo;
            return raiz;
        }
        else if(chave < raiz.chave) {
            raiz.esquerda = inserir(raiz.esquerda, nodo);
        }
        else{
            raiz.direita = inserir(raiz.direita, nodo);
        }
        return raiz;
    }


    public void emOrdem() {
        emOrdem(raiz);
    }
    private void emOrdem(Nodo raiz) {
        if(raiz != null) {
            emOrdem(raiz.esquerda);
            System.out.println(raiz.chave);
            emOrdem(raiz.direita);
        }
    }

    public void preOrdem() {
        preOrdem(raiz);
    }
    private void preOrdem(Nodo raiz) {
        if (raiz != null) {
            System.out.println(raiz.chave);
            preOrdem(raiz.esquerda);
            preOrdem(raiz.direita);
        }
    }


    public void posOrdem() {
        posOrdem(raiz);
    }
    private void posOrdem(Nodo raiz) {
        if (raiz != null) {
            posOrdem(raiz.esquerda);
            posOrdem(raiz.direita);
            System.out.println(raiz.chave);
        }
    }


    public boolean busca(int chave) {
        return busca(raiz, chave);
    }
    private boolean busca(Nodo raiz, int chave) {
        if(raiz == null) {
            return false;
        }
        else if(raiz.chave == chave) {
            return true;
        }
        else if(raiz.chave > chave) {
            return busca(raiz.esquerda, chave);
        }
        else {
            return busca(raiz.direita, chave);
        }  
    }


    public void remove(int chave) {
        if (busca(chave)) {
            remove(raiz, chave);
        }
        else {
            System.out.println("Chave não foi encontrada");
        }
    }
    public Nodo remove(Nodo raiz, int chave) {
        if(raiz == null) {
            return raiz;
        }
        else if(chave < raiz.chave) {
            raiz.esquerda = remove(raiz.esquerda, chave);
        }
        else if(chave > raiz.chave) {
            raiz.direita = remove(raiz.direita, chave);
        }
        else {
            if (raiz.esquerda == null && raiz.direita == null) {
                raiz = null;
            }
            else if(raiz.direita != null) {
                raiz.chave = sucessor(raiz);
                raiz.direita = remove(raiz.direita, raiz.chave);
            }
            else{
                raiz.chave = predecessor(raiz);
                raiz.esquerda = remove(raiz.esquerda, raiz.chave);
            }
        }
        return raiz;
    }

    private int sucessor(Nodo raiz) {
        raiz = raiz.direita;
        while(raiz.esquerda != null) {
            raiz = raiz.esquerda;
        }
        return raiz.chave;
    }
    private int predecessor(Nodo raiz) {
        raiz = raiz.esquerda;
        while(raiz.direita != null) {
            raiz = raiz.direita;
        }
        return raiz.chave;
    }

    public void gerarArqDot(String filename) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(filename))) {
            out.write("digraph ArvoreBin {\n");
            out.write("node [shape=circle, style=filled, color=black, fillcolor=\"#9370DB\"];\n");
            out.write("edge [color=black];\n");
            escreverPreOrdemDot(raiz, out);
            out.write("}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void escreverPreOrdemDot(Nodo raiz, BufferedWriter out) throws IOException {
        if (raiz != null) {
            out.write("  " + raiz.chave + ";\n");
            if (raiz.esquerda != null) {
                out.write("  " + raiz.chave + " -> " + raiz.esquerda.chave + ";\n");
            }
            if (raiz.direita != null) {
                out.write("  " + raiz.chave + " -> " + raiz.direita.chave + ";\n");
            }
            escreverPreOrdemDot(raiz.esquerda, out);
            escreverPreOrdemDot(raiz.direita, out);
        }
    }
}