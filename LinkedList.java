package project3;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.lang.Iterable;
import java.lang.IndexOutOfBoundsException;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;

/**
 * This class is a general implementation of a linked list that implements the Collection interface.
 * This is the parent class of RestaurantList.
 *
 * @author reemaamhaz
 */
public class LinkedList<E> implements Collection<E>
{
    /**
     * This private, static class is a nested class that constructs a node of a linked list.
     * The node will carry the object data and the reference to the following node.
     */
    private static class Node <E>
    {
        //instantiates E generic type field for the node
        E e = null;
        Node<E> next = null;

        Node ()
        {
            // default constructor
        }

        public Node (E e, Node<E> next)
        {
            this.e = e; // assigns the restaurant data field to r
            this.next = next; // references the next node
        }
    }

    /**
     * This private, static class is a nested class that implements iterator.
     * Instantiated when the iterator method is called.
     */
    private class Iter implements Iterator<E>
    {
        Node<E> current;

        /**
         * Constructor that instantiates a new instance of the iterator class.
         * It assigns the current to the beginning of the list.
         */
        public Iter()
        {
            current = head;
        }

        /**
         * This method returns whether the list contains another node.
         * @return boolean true/false if there is another node.
         */
        public boolean hasNext()
        {
            return (current != null); // checks if there is a current node if there is it will return true
        }

        /**
         * This method returns the next node in the list to be iterated over.
         * @throws NoSuchElementException if there is not another node in the list.
         * @return the next node in the list
         */
        public E next()
        {
            if (!hasNext())
            {
                throw new NoSuchElementException("Next node does not exist."); // throws exception if there is not a node at current because it is empty
            }

            E nextNode = current.e; // sets the node to the current node
            current = current.next;  // sets current to the next node
            return nextNode; // returns the following node
        }

        /**
         * This method removes a single instance of the specified element from this collection, if it is present.
         * @param o - object
         * @return boolean of whether or not the element was removed
         */
        public boolean remove (Object o)
        {
            if (o == null)
                return false;

            if (size == 0)
            {
                return false; // checks if the list is empty
            }

            Node<E> current = head; // sets current to beginning of the list

            if (current.e.equals(o))
            {
                current = current.next;
                size --;
                return true;
            }

            for (int i = 0; i < size - 1; i++)
            {
                if (!current.next.e.equals(o))
                {
                    current = current.next; // otherwise make current equal to the next element
                }
                else {
                    Node<E> removeElement = current.next; // next element is to be removed
                    current.next = current.next.next; // so that element references the next next element


                    size--; // shrinks the list
                    return true;
                }
            }
            return false;

        }

    }

    /**
     * This private class implements the iterable interface.
     * Contains the iterator method.
     */
    private class myLinkedList implements Iterable<E>
    {
        /**
         * This method returns an iterator over the elements in this collection.
         * @return an iterator
         */

        public Iterator<E> iterator()
        {
            return new Iter();
        }
    }

    //instantiates the variables of the list
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    /**
     * Constructs new empty linked list
     */
    public LinkedList()
    {
        // constructor of linked list
    }

    /**
     * This method returns the number of elements in this collection.
     * @return the size
     */
    public int size()
    {
        return size;
    }

    /**
     * This method returns true if this collection contains no elements.
     * @return boolean true or false if the collection does not have any elements
     */
    public boolean isEmpty()
    {
        return size == 0;
    }

    /**
     * This method returns index of 1st occurence of element in list or -1 if list doesn't the contain element
     * @param o - the element to search for
     * @return the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element
     */
    public int indexOf( Object o )
    {
        int i = 0;
        if(isEmpty())
            return -1;

        Node<E> curr = head; // instantiates the head of the linked list as curr

        while (curr != null) // checks if the list is not null
        {
            if (curr.e.equals(o)) // if the current data is equal to the object then
            {
                return i; // return the index
            }
            i++; // incremements every time there is another element
            curr = curr.next; // assigns curr to the next element
        }
        return -1; // should return -1 here if it's not in the list
    }

    /**
     * This method returns element at specified position
     * @param index - of the element to return
     * @throws IndexOutOfBoundsException if index is less than 0 or greater than the size of the collection
     * @return the element at the current position
     */
    public E get(int index) throws IndexOutOfBoundsException
    {
        if (index < 0 || index >= size()) // checks for valid index
            throw new IndexOutOfBoundsException ("Index value cannot be less than zero or greater than size of container");
        int i = 0; //instantiate placeholder count
        Node<E> curr = head; // instantiates the head of the linked list as curr

            while(i != index)  //if you're not at the right index you want to enter this loop to increment to the next node
            {
                i++;
                curr = curr.next;
            }

        return curr.e; // if it is at the index then return the data
    }

    /**
     * This method returns a string representation of this collection.
     * @return a string representation of this collection.
     */
    public String toString()
    {
        String  s = ""; // instantiates an empty string
        Node<E> curr = head; // the current node is the beginning of the linked list

        while ( curr != null ) { // while the list is not null
            s += curr.e + ",  " ; // add the current data to the string
            curr = curr.next; // then point current to the next element
        }
        return String.format ("[%s]", String.valueOf(s)); // return the string
    }

    /**
     * @author Professor Klukowska
     * This method sorts the array and then adds it back.
     */
    public void sort ()
    {
        Object [] array = toArray();
        Arrays.sort(array);
        this.clear();
        for (Object o : array )
        {
            this.add((E)o);
        }
    }

    /**
     * This method removes a single instance of the specified element from this collection, if it is present.
     * @param o - object
     * @return boolean of whether or not the element was removed
     */
    public boolean remove (Object o)
    {
        if (size == 0)
        {
            return false;
        }

        if (size == 1) {
            head = tail = null;
            size--;
            return true;
        }

        Node<E> current = head;

        if (current.e.equals(o))
        {
            current = current.next;
            size--;
            return true;
        }

        while (current.next != null && !current.next.e.equals(o))
        {
            current = current.next;
        }

//        if (current.next == null)
//            return false;

        Node<E> removeElement = current.next;
        current.next = current.next.next;

        size-- ;

        return true;
    }

    /**
     * This method ensures that this collection contains the specified element
     * @return boolean of true or false if the item was added before
     */
    public boolean add (E e)
    {
        Node<E> addElement = new Node<E>(e, null);


        if (size == 0)
        {
            head = addElement;
            size++;
            tail = head;
            return true;
        }

        tail.next = addElement; //otherwise add the element at the end of the list

        tail = addElement; // the tail becomes the newest element
        size++;

        return true;
    }

    /**
     * This method adds all of the elements in the specified collection to this collection. It is not supported by this program.
     */
    public boolean addAll (Collection<? extends E> c)
    {
        throw new UnsupportedOperationException("This operation is unsupported.");
    }

    /**
     * This method removes all of the elements from this collection
     */
    public void clear ()
    {
        head = tail = null;
        size = 0; // if size equals 0 then there aren't any elements in the list
    }

    /**
     * This method returns true if this collection contains the specified element.
     * @return true or false if collection contains element
     */
    public boolean contains (Object o)
    {
        return indexOf(o) >= 0;
    }

    /**
     * This method returns true if this collection contains all of the elements in the specified collection
     * @return true or false if the collection contains all elements
     */
    public boolean containsAll (Collection<?> c)
    {
        for (Object o : c) // iterating through each object in the collection
        {
            if (!contains(o))
            {
                return false; // when the collection does not contain the element return false
            }
        }
        return true; // otherwise return true
    }

    /**
     * This method compares the specified object with this collection for equality.
     * @return boolean true or false if the object equals an object in the collection.
     */
    public boolean equals (Object o)
    {
        if (this == null)
            return false;
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (!(o instanceof LinkedList))
            return false;

        LinkedList currList = (LinkedList) o; // cast the list

        if (size != currList.size)
        {
            return false;
        }

        Node<E> thisPos = this.head; // starts at the head of this list
        Node<E> ex = currList.head; // starts at the head of the list of the object

        while (thisPos != null)
        {
            if (!thisPos.e.equals(ex.e)){ // if an element in the list doesn't equal the one in the object list it returns false
                return false;
            }

            thisPos = thisPos.next; // move through the list
            ex = ex.next; // move through the list
        }

        return true; // if the lists match
    }

    /**
     * This method returns the hash code value for this collection. Inherited from the Object (super) class
     * @return the hashcode of the element
     */
    public int hashCode()
    {
        return super.hashCode();
    }

    public Iterator<E> iterator()
    {
        return new Iter();
    }


    /**
     * This method removes all of this collection’s elements that are also contained in the specified collection. It is unsupported by the program.
     */
    public boolean removeAll (Collection<?> c)
    {
        throw new UnsupportedOperationException("This operation is unsupported.");
    }


    /**
     * This method retains only the elements in this collection that are contained in the specified collection. It is unsupported by the program.
     */
    public boolean retainAll (Collection<?> c)
    {
        throw new UnsupportedOperationException("This operation is unsupported.");
    }


    /**
     * This method returns an array containing all of the elements in this collection.
     * @return an array of the objects
     */
    public Object[] toArray()
    {
        int size = size();
        Object[] arr = new Object[size];

        for (int i = 0; i < this.size(); i++)
        {
            arr[i] = get(i);
        }
        return arr;
    }

    /**
     * This method returns ian array containing all of the elements in this collection; the runtime type of the returned array is that of the specified array.
     * @return the array
     */
    public <T> T[] toArray (T[] a)
    {
        Object[] array = toArray();
        if (a.length < size)
        {
            return (T[]) Arrays.copyOf(array, size, a.getClass());
        }

        System.arraycopy(array, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }
}