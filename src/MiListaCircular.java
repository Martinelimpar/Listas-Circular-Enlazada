import java.util.Arrays;
import java.util.Objects;

public class MiListaCircular implements ListInterface {

    private Node head;
    private Node tail;
    private int size;

    public MiListaCircular() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public Object getHead() {
        return (head != null) ? head.dato : null;
    }

    @Override
    public Object getTail() {
        return (tail != null) ? tail.dato : null;
    }

    @Override
    public Object get(Node node) {
        if (node != null) return node.dato;
        return null;
    }

    @Override
    public Node search(Object object) {
        if (isEmpty()) return null;
        Node current = head;
        do {
            // Uso de Objects.equals para evitar NullPointerException si hay datos nulos
            if (Objects.equals(current.dato, object)) return current;
            current = current.siguiente;
        } while (current != head);
        return null;
    }

    @Override
    public boolean add(Object object) {
        return insertTail(object);
    }

    @Override
    public boolean insertHead(Object object) {
        Node newNode = new Node(object);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
            newNode.siguiente = head;
        } else {
            newNode.siguiente = head;
            head = newNode;
            tail.siguiente = head;
        }
        size++;
        return true;
    }

    @Override
    public boolean insertTail(Object object) {
        if (isEmpty()) {
            return insertHead(object);
        }
        Node newNode = new Node(object);
        tail.siguiente = newNode;
        tail = newNode;
        tail.siguiente = head;
        size++;
        return true;
    }

    @Override
    public boolean insert(Node node, Object object) {
        if (node == null) return false;

        Node newNode = new Node(object);
        newNode.siguiente = node.siguiente;
        node.siguiente = newNode;

        if (node == tail) {
            tail = newNode;
        }
        size++;
        return true;
    }

    @Override
    public boolean insert(Object objectRef, Object object) {
        Node refNode = search(objectRef);
        if (refNode != null) {
            return insert(refNode, object);
        }
        return false;
    }

    @Override
    public boolean set(Node node, Object object) {
        if (node != null) {
            node.dato = object;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Node node) {
        if (isEmpty() || node == null) return false;
        if (head == tail && head == node) {
            clear();
            return true;
        }
        if (head == node) {
            head = head.siguiente;
            tail.siguiente = head;
            size--;
            return true;
        }
        Node current = head;
        do {
            if (current.siguiente == node) {
                current.siguiente = node.siguiente;
                if (node == tail) {
                    tail = current;
                }
                size--;
                return true;
            }
            current = current.siguiente;
        } while (current != head);

        return false;
    }

    @Override
    public boolean contains(Object object) {
        return search(object) != null;
    }

    @Override
    public Object[] toArray() {
        if (isEmpty()) return new Object[0];

        Object[] arr = new Object[size];
        Node current = head;
        int index = 0;

        do {
            arr[index++] = current.dato;
            current = current.siguiente;
        } while (current != head);

        return arr;
    }

    @Override
    public Object[] toArray(Object[] object) {
        Object[] arr = toArray();
        if (object.length < size) {
            return Arrays.copyOf(arr, size, object.getClass());
        }
        System.arraycopy(arr, 0, object, 0, size);
        if (object.length > size) {
            object[size] = null;
        }
        return object;
    }

    @Override
    public MiListaCircular subList(Node from, Node to) {
        MiListaCircular subLista = new MiListaCircular();
        if (isEmpty() || from == null || to == null) return subLista;

        Node current = from;
        do {
            subLista.add(current.dato);
            if (current == to) {
                break;
            }
            current = current.siguiente;
        } while (current != from); // Corregido: Permite que la sublista cruce el head de forma segura

        return subLista;
    }

    @Override
    public MiListaCircular sortList() {
        MiListaCircular sortedList = new MiListaCircular();
        if (isEmpty()) return sortedList;

        Object[] arr = toArray();

        try {
            Arrays.sort(arr);
        } catch (ClassCastException e) {
            System.out.println("No se puede ordenar: los elementos no implementan Comparable.");
            return this;
        }

        for (Object obj : arr) {
            sortedList.add(obj);
        }

        return sortedList;
    }
}