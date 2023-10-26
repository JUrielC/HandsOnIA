import java.util.ArrayList;

public  class SearchMethods {

   protected void crearArbol(Nodo nodoActual, String destino){

        if (!nodoActual.getNombre().equals(destino)) {
            switch (nodoActual.getNombre()) {
                case "New York" -> {
                    nodoActual.addHijo(new Nodo("Chicago"));
                    nodoActual.addHijo(new Nodo("Toronto"));
                    nodoActual.addHijo(new Nodo("Denver"));
                }
                case "Chicago" -> nodoActual.addHijo(new Nodo("Denver"));
                case "Toronto" -> {
                    nodoActual.addHijo(new Nodo("Calgary"));
                    nodoActual.addHijo(new Nodo("LA"));
                }
                case "Denver" -> {
                    nodoActual.addHijo(new Nodo("Houston"));
                    nodoActual.addHijo(new Nodo("LA"));
                    nodoActual.addHijo(new Nodo("Urbana"));
                }
                case "Houston" -> nodoActual.addHijo(new Nodo("LA"));
            }

            for (Nodo n : nodoActual.getHijos_Vecinos()) {
                crearArbol(n, destino);
            }
        }
   }

   protected void crearGrafo (Nodo origen){
       String [] sEstados = {"a", "b", "c", "d", "e"};
       ArrayList <Nodo> estadosExistentes = new ArrayList<Nodo>(); //guarda los nodos creados
       estadosExistentes.add(origen);

       //crear nodos y unirlos a origen
       for(String nombreNuevoNodo : sEstados){
           if (!nombreNuevoNodo.equals(origen.getNombre())){
               Nodo aux = new Nodo(nombreNuevoNodo);
               origen.addVecino(aux);
               estadosExistentes.add(aux);
           }
       }

       // unir los nodos restantes (vecinos del origen) con los estados existentes
       for (Nodo vecino: origen.getHijos_Vecinos()){
            for (Nodo estado: estadosExistentes){
                if (!estado.getNombre().equals(vecino.getNombre())){
                    vecino.addVecino(estado);
                }
            }
       }

   }

    protected void HillClimbingSearch (ArrayList <Nodo> listaAbierta,ArrayList <Nodo> listaCerrada, String estadosVisitados){
       listaCerrada.add(0,listaAbierta.get(0));
       estadosVisitados += (listaCerrada.get(0).getNombre());
       listaAbierta.remove(0);

       if (listaCerrada.size() == 5)
        mostrarLista(listaCerrada);
       else {
           listaAbierta.clear();
           establecerDistancias(listaCerrada.get(0));
           addPorPrioridad(listaAbierta, listaCerrada.get(0).getHijos_Vecinos(),estadosVisitados);

       }

        if(listaCerrada.size() < 5){
            HillClimbingSearch(listaAbierta,listaCerrada,estadosVisitados);
        }

    }


    protected void DepthSearch(String destino,ArrayList <Nodo> listaAbierta,ArrayList <Nodo> listaCerrada){

        if (!listaAbierta.isEmpty()){
            listaCerrada.add(0,listaAbierta.get(0));
            listaAbierta.remove(0);

            if (listaCerrada.get(0).getNombre().equals(destino))
                mostrarLista(listaCerrada);
            else
                listaAbierta.addAll(0, listaCerrada.get(0).getHijos_Vecinos());
        }

        if (!listaAbierta.isEmpty()) {
            //descartar nodos con hijos ya recorridos, eliminar de lista cerrada
                while (listaAbierta.get(0).getPadre() != listaCerrada.get(0 ))
                    listaCerrada.remove(0);

            DepthSearch(destino, listaAbierta, listaCerrada);
        }
    }


    protected void breadthSearch (ArrayList <Nodo> listaAbierta, ArrayList <Nodo> listaCerrada, String destino){
        ArrayList <Nodo> hijos = new ArrayList<>();
        for (Nodo n : listaAbierta){
            if (n.getNombre().equals(destino))
                listaCerrada.add(n);
            else
                hijos.addAll(n.getHijos_Vecinos());
        }
        if (!hijos.isEmpty())
            breadthSearch(hijos, listaCerrada,destino);
    }

    private void addPorPrioridad (ArrayList<Nodo> lista, ArrayList<Nodo> vecinos, String estadosVisitados){

        for (Nodo n: vecinos){ //para cada vecino

            if (lista.isEmpty()) lista.add(n);
            else{
                for (int indice = 0; indice < lista.size(); indice++) //establecer un valor actual de la lista mediante suindice
                    if ((n.getDistanciaActual() < lista.get(indice).getDistanciaActual() || indice == lista.size() - 1)
                            && !estadosVisitados.contains(n.getNombre()) ) {
                        lista.add(indice, n);
                        break;
                    }
            }
        }
    }

            private void establecerDistancias (Nodo nodo){
        for (Nodo vecino: nodo.getHijos_Vecinos()){
            vecino.setDistanciaActual(distanciaNodos(nodo.getNombre(),vecino.getNombre()));
        }
    }

    private int distanciaNodos(String origen, String destino ){
        String estados = "abcde";
        int [][] adyacencias = {
                {0,100,125,100,75},
                {100,0,50 ,75,125},
                {125,50,0,100,125},
                {100,75,100,0 ,50},
                {75,125,125,50 ,0}
        };
        int fila = estados.indexOf(origen);
        int columna = estados.indexOf(destino);

        return adyacencias[fila][columna];
    }

    private   void mostrarLista(ArrayList <Nodo> lista){
       System.out.println("\nRuta:");
       for (int i = lista.size()-1; i >=0; i--)
           System.out.println(lista.get(i).getNombre());
   }

   //muestra el camino recorrido para llegar a un nodo especifico através de los padres
   private Nodo rutaDeNodo(Nodo n){
       if (n.getPadre() == null)
           return n;
       else
           System.out.println(rutaDeNodo(n.getPadre()).getNombre());
       return  n;
   }

   protected void mostrarRutasBreadth(ArrayList <Nodo> listaCerrada){
       for (Nodo n : listaCerrada){
           System.out.println("Ruta: ");
           System.out.println(rutaDeNodo(n).getNombre());
       }

   }

}