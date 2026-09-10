public class Main {
    public static void main(String[] args) {
        System.out.println("=== INICIANDO PRUEBAS DE LA LISTA CIRCULAR ===");
        MiListaCircular lista = new MiListaCircular();

        // 1.Inserción vacía y extremos
        lista.add(10); // tail
        lista.add(20); // tail
        lista.insertHead(5);  // head
        lista.insertTail(30); // tail

        System.out.println("Tamaño (Esperado: 4): " + lista.getSize());
        System.out.println("Head (Esperado: 5): " + lista.getHead());
        System.out.println("Tail (Esperado: 30): " + lista.getTail());

        // 2. toArray
        System.out.print("Lista completa: ");
        imprimirArreglo(lista.toArray()); // 5, 10, 20, 30

        // 3. Búsqueda y referencias
        Node nodo20 = lista.search(20);
        System.out.println("¿Contiene 20?: " + lista.contains(20)); // true

        // 4. Inserción por nodo específico
        lista.insert(nodo20, 25); // Inserta 25 después de 20
        System.out.print("Después de insertar 25 tras el 20: ");
        imprimirArreglo(lista.toArray()); // 5, 10, 20, 25, 30

        // 5. Eliminación de nodos complejos
        lista.remove(nodo20); // Remueve el nodo central (20)
        System.out.print("Después de remover el 20: ");
        imprimirArreglo(lista.toArray()); // 5, 10, 25, 30

        // 6. Pruebas de Ordenamiento
        MiListaCircular desordenada = new MiListaCircular();
        desordenada.add(50);
        desordenada.add(10);
        desordenada.add(30);

        MiListaCircular ordenada = desordenada.sortList();
        System.out.print("Lista ordenada: ");
        imprimirArreglo(ordenada.toArray()); // 10, 30, 50

        System.out.println("=== PRUEBAS FINALIZADAS CON ÉXITO ===");
    }

    private static void imprimirArreglo(Object[] arr) {
        for (Object o : arr) {
            System.out.print(o + " ");
        }
        System.out.println();
    }
}