package Balanceado;

import cu.edu.cujae.ceis.tree.binary.BinaryTree;
import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;

public class ArbolAVL<E extends Comparable <E>> extends BinaryTree<E>{
    
    
    //---------------------Insertar-----------------------------------
    
    public void insertar(E key) {
        setRoot(insertarAVL((BinaryTreeNode<E>) root, key)); 
    }
    
    private BinaryTreeNode<E> insertarAVL(BinaryTreeNode<E> nodoActual, E key) {
        
        if (nodoActual == null) {
            return (new BinaryTreeNode<E>(key));
        }
        
        if (nodoActual.getInfo().compareTo(key) > 0) {
            nodoActual.setLeft(insertarAVL(nodoActual.getLeft(), key));
        }
        
        else if (nodoActual.getInfo().compareTo(key) < 0) {
            nodoActual.setRight(insertarAVL(nodoActual.getRight(), key)) ;
        }
        
        else {
            return nodoActual;  //si esta duplicado se agrega en el mismo sitio que el original
        }
        
   
        // Obtener Equilibrio
        int equilibrio = getEquilibrio(nodoActual);

        // Se rota dependiendo del equilibrio

        //Rotacion Simple a la derecha
        if (equilibrio > 1 && 0 < nodoActual.getLeft().getInfo().compareTo(key)) {
            return rightRotate(nodoActual);
        }
 
        // CRotacion Simple a la izquierda
        if (equilibrio < -1 && 0 > nodoActual.getRight().getInfo().compareTo(key)) {
            return leftRotate(nodoActual);
        }
 
        // Rotacion Doble Izquierda Derecha
        if (equilibrio > 1 && 0 > nodoActual.getLeft().getInfo().compareTo(key)) {
            nodoActual.setLeft(leftRotate(nodoActual.getLeft()));
            return rightRotate(nodoActual);
        }
 
        // Rotacion Doble Derecha Izquierda
        if (equilibrio < -1 && 0 < nodoActual.getRight().getInfo().compareTo(key)) {
            nodoActual.setRight(rightRotate(nodoActual.getRight()));
            return leftRotate(nodoActual); 
        }
 
        return nodoActual;
    }
    
    //---------------------Eliminar-----------------------------------
    
    public void eliminar(E key) {
        root = eliminarAVL((BinaryTreeNode<E>) root, key);
    }
    
    private BinaryTreeNode<E> eliminarAVL(BinaryTreeNode<E> nodoActual, E key) {
        if (nodoActual == null)
            return nodoActual;
 
        if (0 < nodoActual.getInfo().compareTo(key)){
            nodoActual.setLeft(eliminarAVL(nodoActual.getLeft(), key));
        }
        else if (0 > nodoActual.getInfo().compareTo(key)){
            nodoActual.setRight(eliminarAVL(nodoActual.getRight(), key));
        }

        else {  //Nodo Encontrado
            
            //Nodo hoja o con 1 solo hijo
            if ((nodoActual.getLeft() == null) || (nodoActual.getRight() == null)) {
                BinaryTreeNode<E> temp = null;

                if (temp == nodoActual.getLeft()) {
                    temp = nodoActual.getRight();
                }else {
                    temp = nodoActual.getLeft();
                }
 
                
                if (temp == null) {  
                    nodoActual = null;
                    //no tiene hijos y se elimina
                }
                else{
                    nodoActual = temp;
                    //tiene 1 solo hijo y se intercambia por el
                }
                
            }else {
                //Nodo con dos hijos, se busca el predecesor
                BinaryTreeNode<E> predecesor = getPredecesor(nodoActual.getLeft());
                
                //Se copia el dato del predecesor
                nodoActual.setInfo(predecesor.getInfo());
 
                //Se elimina el predecesor
                nodoActual.setLeft(eliminarAVL(nodoActual.getLeft(), predecesor.getInfo()));            }
        }
 
        //Si solo tiene un nodo
        if (nodoActual == null)
            return nodoActual;
 

        //Calcula el equilibrio
        int equilibrio = getEquilibrio(nodoActual);
 
        // Se rota dependiendo del equilibrio

        // Rotacion Simple a la Derecha
        if (equilibrio > 1 && getEquilibrio(nodoActual.getLeft()) >= 0) {
            return rightRotate(nodoActual);
        }
 
        // Rotacion Simple a la Izquierda
        if (equilibrio < -1 && getEquilibrio(nodoActual.getRight()) <= 0) {
            return leftRotate(nodoActual);
        }
 
        // Rotacion Doble Izquierda-Derecha
        if (equilibrio > 1 && getEquilibrio(nodoActual.getLeft()) < 0) {
            nodoActual.setLeft(leftRotate(nodoActual.getLeft())); 
            return rightRotate(nodoActual);
        }
        
        // Rotacion Doble Derecha-Izquierda
        if (equilibrio < -1 && getEquilibrio(nodoActual.getRight()) > 0) {
            nodoActual.setRight(rightRotate(nodoActual.getRight()));
            return leftRotate(nodoActual);
        }
 
        return nodoActual;
    }
    
    //---------------------Buscar-----------------------------------

    public boolean buscar(E elemento){
        return (BuscaEnAVL((BinaryTreeNode<E>)root, elemento));
    }

    private boolean BuscaEnAVL(BinaryTreeNode<E> nodoActual, E elemento) {
        if (nodoActual == null) {
            return false;
        } else if (0 == nodoActual.getInfo().compareTo(elemento)) {
            return true;
        } else if (0 < nodoActual.getInfo().compareTo(elemento)) {
            return BuscaEnAVL(nodoActual.getLeft(), elemento);
        } else {
            return BuscaEnAVL(nodoActual.getRight(), elemento);
        }
    }
    
    

    //---------------------Rotaciones-----------------------------------

    
    private BinaryTreeNode<E> rightRotate(BinaryTreeNode<E> nodoActual) {

        BinaryTreeNode<E> nuevaRaiz = nodoActual.getLeft();
        BinaryTreeNode<E> temp = nuevaRaiz.getRight();
 
        // Rotacion
        nuevaRaiz.setRight(nodoActual);
        nodoActual.setLeft(temp);

        return nuevaRaiz;
    }
 
    private BinaryTreeNode<E> leftRotate(BinaryTreeNode<E> nodoActual) {
        BinaryTreeNode<E> nuevaRaiz = nodoActual.getRight();
        BinaryTreeNode<E> temp = nuevaRaiz.getLeft();
 
        // Rotacion
        nuevaRaiz.setLeft(nodoActual);;
        nodoActual.setRight(temp);

        return nuevaRaiz;
    }
    

    //---------------------Exportar Arbol en String-----------------------------------

    
    public String exportarArbolAVL() {
        
        String arbol = exportTree((BinaryTreeNode<E>) root, 0);
        return arbol;
    }

    
    public String exportTree(BinaryTreeNode<E> nodo, int depth) {
        String arbol = "";
        if (nodo.getRight() != null) {
            arbol = arbol.concat(exportTree(nodo.getRight(), depth + 1));
        }
        for (int i = 0; i < depth; i++) {
            arbol = arbol.concat("         ");
        }

        arbol = arbol.concat("(" + nodo.getInfo() + ")").concat("\n");

        if (nodo.getLeft() != null) {
            arbol = arbol.concat(exportTree(nodo.getLeft(), depth + 1));
        }
        return arbol;
        
    }

    
    //---------------------Utiles-----------------------------------
    
    private int getEquilibrio(BinaryTreeNode<E> nodoActual) {
        int equilibrio = 0;
        
        if (nodoActual != null) {
            equilibrio = level(nodoActual.getLeft()) - level(nodoActual.getRight());
        }
        
        return equilibrio;
    }
    
    private BinaryTreeNode<E> getPredecesor(BinaryTreeNode<E> node) {
        BinaryTreeNode<E> nodo = node;
        
        while (nodo.getRight() != null){
            nodo = nodo.getRight();
        }
        
        return nodo;
    }

}
