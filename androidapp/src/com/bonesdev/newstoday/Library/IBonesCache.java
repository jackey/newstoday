package com.bonesdev.newstoday.Library;

/**
 * Created by jackey on 7/29/13.
 */
public interface IBonesCache<V> {
    abstract public void set(String key, V value);
    abstract public V get(String key);
}
