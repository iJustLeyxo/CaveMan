package com.cavetale.manager.parser.container;

import java.util.HashSet;
import java.util.Set;

public abstract class SetContainer<T> extends Container<Set<T>> {
    public SetContainer() {
        super(new HashSet<>());
    }

    @Override
    public boolean isEmpty() {
        return this.contents.isEmpty();
    }
}
