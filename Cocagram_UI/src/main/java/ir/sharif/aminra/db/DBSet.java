package ir.sharif.aminra.db;

import ir.sharif.aminra.models.ID;

public interface DBSet<T> {
    T getByID(ID id);
    void saveIntoDB(T savable);
}
