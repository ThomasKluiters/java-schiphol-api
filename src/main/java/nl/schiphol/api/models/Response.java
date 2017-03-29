package nl.schiphol.api.models;

import nl.schiphol.api.builders.RequestBuilder;

import javax.annotation.Nonnull;
import java.util.*;

/**
 * Created by Thomas on 27-3-2017.
 */
public abstract class Response<T extends Response<T>> {

    private String next;

    private String previous;

    private String last;

    private String first;

    private Long page = 0L;

    private RequestBuilder<T, ?> builder;

    public void setPage(Long page) {
        this.page = page;
    }

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

    abstract protected T get();

    public T next() {
        return builder.execute(getNext());
    }

    public T previous() {
        return builder.execute(getPrevious());
    }

    public T last() {
        return builder.execute(getLast());
    }

    public T first() {
        return builder.execute(getFirst());
    }

    public ResponseIterator all() {
        return new ResponseIterator(get());
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

    public Long getPage() {
        return page;
    }

    public class ResponseIterator implements Iterator<T>, Iterable<T> {

        private Queue<T> responses;

        ResponseIterator(@Nonnull T response) {
            responses = new LinkedList<T>(Collections.singletonList(response));
        }

        @Override
        public boolean hasNext() {
            return !responses.isEmpty();
        }

        @Override
        public T next() {
            final T current = responses.remove();
            if(current.hasNext()) {
                responses.add(current.next());
            }

            return current;
        }

        @Override
        @Nonnull
        public Iterator<T> iterator() {
            return this;
        }
    }

}
