package nl.schiphol.api.models;

import nl.schiphol.api.builders.RequestBuilder;

import javax.annotation.Nonnull;

/**
 * Created by Thomas on 27-3-2017.
 */
public class Response<T extends Response<T>> {

    private String next;

    private String previous;

    private String last;

    private String first;

    private RequestBuilder<T, ?> builder;

    public void setNext(String next) {
        this.next = next;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public void setBuilder(@Nonnull RequestBuilder<T, ?> builder) {
        this.builder = builder;
    }

    public boolean hasFirst() {
        return getFirst() != null;
    }

    public boolean hasLast() {
        return getLast() != null;
    }

    public boolean hasNext() {
        return getNext() != null;
    }

    public boolean hasPrevious() {
        return getPrevious() != null;
    }

    public T next() {
        return builder.executeRaw(getNext());
    }

    public T previous() {
        return builder.executeRaw(getPrevious());
    }

    public T last() {
        return builder.executeRaw(getLast());
    }

    public T first() {
        return builder.executeRaw(getFirst());
    }

    private String getNext() {
        return next;
    }

    private String getPrevious() {
        return previous;
    }

    private String getLast() {
        return last;
    }

    private String getFirst() {
        return first;
    }

}
