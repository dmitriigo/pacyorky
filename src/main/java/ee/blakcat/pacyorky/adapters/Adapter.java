package ee.blakcat.pacyorky.adapters;

import java.util.Collection;

public interface Adapter<IN, OUT> {
    OUT convert(IN entity);

    Collection<OUT> convertAll(Collection<IN> entities);
}
