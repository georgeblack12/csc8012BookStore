import java.util.*;

/*
 * A generic class that extends the arrayList. A SortedArrayList can only be a list of types that implements the
 * comparable interface. This class's only purpose is to have an insertion method that puts the SortedEArrayList in
 *  ascending order.
 *
 */
public class SortedArrayList<E extends Comparable<E>> extends ArrayList<E> {

    /*
     *
     * Params: E e- the variable of type E (the variable type that "this" SortedArrayList will hold)  that we will be
     * entering intothe sorted ArrayList.
     *
     * Enters the variable of Type E (the variable type this list will hold) into the SortedArrayList at a specific
     * location that keeps the SortedArrayList in ascending order.
     *
     */
    public void insertion(E e) {
        boolean inserted = false;
        for (int i = 0; i < size(); i++) {
            if ((e.compareTo(this.get(i)) < 0) && (inserted == false)) {
                this.add(i, e);
                inserted = true;
            }
        }

        //If the variable is not smaller than any of the other variables in "this" SortedArrayList,
        //then it needs to be added to the bottom of "this" SortedArrayList
        if (!inserted) {
            this.add(e);
        }
    }
}

