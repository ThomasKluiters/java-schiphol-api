package nl.schiphol.api.models;

import lombok.Data;
import nl.schiphol.api.builders.RequestBuilder;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Thomas on 27-3-2017.
 */
@Data
public abstract class Response<T extends Response<T>> {

    private String next;

    private String previous;

    private String last;

    private String first;

    private String schemaVersion;

    private Long page = 0L;

    private RequestBuilder<T, ?> builder;

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
        return getBuilder().execute(getNext());
    }

    public T previous() {
        return getBuilder().execute(getPrevious());
    }

    public T last() {
        return getBuilder().execute(getLast());
    }

    public T first() {
        return getBuilder().execute(getFirst());
    }

    public ResponseIterator all() {
        return new ResponseIterator(get());
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
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        @Nonnull
        public Iterator<T> iterator() {
            return this;
        }
    }

}
