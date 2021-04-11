package ro.dan.dragu.account.management.service.mapper;

import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ObjectMapper<S, D> {

    public abstract D mapTo(S source);

    public abstract S mapFrom(D source);

    public List<D> mapListTo(List<S> sourceList) {
        if (CollectionUtils.isEmpty(sourceList)){
            return Collections.EMPTY_LIST;
        }
        return sourceList.stream()
                .map(this::mapTo)
                .collect(Collectors.toList());
    }

    public List<S> mapListFrom(List<D> sourceList) {
        if (CollectionUtils.isEmpty(sourceList)){
            return Collections.EMPTY_LIST;
        }
        return sourceList.stream()
                .map(this::mapFrom)
                .collect(Collectors.toList());
    }



}
