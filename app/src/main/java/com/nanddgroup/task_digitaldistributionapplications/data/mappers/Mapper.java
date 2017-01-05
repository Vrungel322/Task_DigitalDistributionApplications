package com.nanddgroup.task_digitaldistributionapplications.data.mappers;

/**
 * Created by Nikita on 05.01.2017.
 */

public interface Mapper<A, B> {
    B transform(A obj) throws RuntimeException;
}
