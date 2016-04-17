//**********************************************************
// ITESO - Maestría en Sistemas Computacionales
// Análisis y Diseño de Algoritmos
// Tarea 5, C. Más de secuencias ADN (Adn2.java)
//
// Erick González (705070)
// Mario Contreras (705080)
//
//*********************************************************
import java.util.Scanner;
/*
Sample Input
1
4 7
ACGT
AACG
GTCC
TTAC
GAGT
GCAT
AAAC
4
TTAC
AAAC
GAGT
GCAA

Sample Output
1 3 2 2
*/


public class ADN2a {
    static class Nodo {
        private String datos;
        private Nodo A;
        private Nodo C;
        private Nodo G;
        private Nodo T;

        Nodo (String d){
            datos = d;
            A = null;
            C = null;
            G = null;
            T = null;
        }

        void putA (Nodo d){A = d;}
        void putC (Nodo d){C = d;}
        void putG (Nodo d){G = d;}
        void putT (Nodo d){T = d;}
        String getDatos(){return datos;}
        Nodo getA(){return A;}
        Nodo getC(){return C;}
        Nodo getG(){return G;}
        Nodo getT(){return T;}
    }

    static class Lista {
        private int datos;
        private Lista next;

        Lista (int d){
            datos = d;
            next  = null;
        }
        void putNext (Lista d){next = d;}
        int getDatos(){return datos;}
        Lista getNext(){return next;}
    }

    static void print(Nodo g, String cad) {
        if(g == null) return;
        System.out.println(cad + g.getDatos());
        print(g.getA(), cad+" ");
        print(g.getC(), cad+" ");
        print(g.getG(), cad+" ");
        print(g.getT(), cad+" ");
    }

    static void print_res(Lista o) {
        if(o == null) return;
        System.out.print(o.getDatos() + " ");
        print_res(o.getNext());
    }

    public static void main(String[] args) {
        //System.out.println("Input");
        Scanner sc = new Scanner(System.in);
        int C = sc.nextInt();
        Nodo[] g = new Nodo[C];
        Nodo a, c = null;
        int[] L = new int[C];
        int[] N = new int[C];
        Lista[] out = new Lista[C];
        Lista o = null;
        for(int i = 0; i < C; i++) {
            L[i] = sc.nextInt();
            N[i] = sc.nextInt();
            g[i] = new Nodo("In");
            for(int j = 0; j < N[i]; j++) {
                String cad = sc.next();
                if(g[i] == null) {
                    g[i] = new Nodo(cad);
                }
                else {
                    int k = 0, l = 0;
                    a = g[i];
                    while(a != null) {
                        c = a;
                        switch(cad.charAt(k)) {
                            case 'A':
                                a = a.getA(); l = 1;
                                break;
                            case 'C':
                                a = a.getC(); l = 2;
                                break;
                            case 'G':
                                a = a.getG(); l = 3;
                                break;
                            case 'T':
                                a = a.getT(); l = 4;
                                break;
                        }
                        k++;
                    }
                    switch(l) {
                        case 1:
                            c.putA(new Nodo(cad));
                            break;
                        case 2:
                            c.putC(new Nodo(cad));
                            break;
                        case 3:
                            c.putG(new Nodo(cad));
                            break;
                        case 4:
                            c.putT(new Nodo(cad));
                            break;
                    }
                }
            }
            int N2 = sc.nextInt();
            for(int j = 0; j < N2; j++) {
                String cad = sc.next();
                int k = 0, l = 0;
                a = g[i];
                while(a != null && l == 0) {
                    if(a.datos.equals(cad)) {
                        l = k;
                        break;
                    }
                    switch(cad.charAt(k)) {
                        case 'A': a = a.getA(); break;
                        case 'C': a = a.getC(); break;
                        case 'G': a = a.getG(); break;
                        case 'T': a = a.getT(); break;
                    }
                    k++;
                }
                if(l == 0) l = k -1;
                if(out[i] == null) {
                   out[i] = new Lista(l);
                }
                else {
                    o = out[i];
                    while(o.getNext() != null) {
                        o = o.getNext();
                    }
                    o.putNext(new Lista(l));
                }
            }
        }
        //System.out.println("Output");
        //print(g[0],""); //imprime el arbol generado
        for(int i = 0; i < C; i++) {
            print_res(out[i]);
            System.out.println();
        }
        sc.close();
    }
}
