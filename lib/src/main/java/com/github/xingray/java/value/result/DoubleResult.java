package com.github.xingray.java.value.result;

import java.util.function.Function;

public final class DoubleResult<T1, T2> {
    private final int code;
    private final T1 first;
    private final T2 second;
    private final String message;
    private final Throwable throwable;

    public DoubleResult() {
        this(CODE_SUCCESS, null, null, MESSAGE_SUCCESS_DEFAULT, null);
    }

    public DoubleResult(int code, T1 first, T2 second, String message, Throwable throwable) {
        this.code = code;
        this.first = first;
        this.second = second;
        this.message = message;
        this.throwable = throwable;
    }

    public int getCode() {
        return code;
    }

    public T1 getFirst() {
        return first;
    }

    public T2 getSecond() {
        return second;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    @Override
    public String toString() {
        return "DoubleResult{" +
                "code=" + code +
                ", first=" + first +
                ", second=" + second +
                ", message='" + message + '\'' +
                ", throwable=" + throwable +
                '}';
    }

    public boolean isSuccess() {
        return code == CODE_SUCCESS;
    }

    public static final int CODE_SUCCESS = 0;
    public static final String MESSAGE_SUCCESS_DEFAULT = "success";
    public static final int CODE_ERROR_DEFAULT = -1;
    public static final String MESSAGE_ERROR_DEFAULT = "error";
    public static final DoubleResult<Object, Object> SUCCESS = new DoubleResult<>(CODE_SUCCESS, null, null, MESSAGE_SUCCESS_DEFAULT, null);
    public static final DoubleResult<Object, Object> ERROR = new DoubleResult<>(CODE_ERROR_DEFAULT, null, null, MESSAGE_ERROR_DEFAULT, null);

    public static <U, V> DoubleResult<U, V> success() {
        //noinspection unchecked
        return (DoubleResult<U, V>) SUCCESS;
    }

    public static <U, V> DoubleResult<U, V> error() {
        //noinspection unchecked
        return (DoubleResult<U, V>) ERROR;
    }

    public static <U, V> DoubleResult<U, V> result(boolean success) {
        return success ? success() : error();
    }

    public static <U, V, W, X> DoubleResult<W, X> copyIgnoreData(DoubleResult<U, V> result) {
        return new DoubleResult<>(result.code, null, null, result.message, result.throwable);
    }

    public static <U, V, W, X> DoubleResult<W, X> copy(DoubleResult<U, V> result, Function<U, W> firstMapper, Function<V, X> secondMapper) {
        W targetFirst = null;
        U sourceFirst = result.getFirst();
        if (sourceFirst != null && firstMapper != null) {
            targetFirst = firstMapper.apply(sourceFirst);
        }

        X targetSecond = null;
        V sourceSecond = result.getSecond();
        if (sourceSecond != null && secondMapper != null) {
            targetSecond = secondMapper.apply(sourceSecond);
        }

        return new DoubleResult<>(result.code, targetFirst, targetSecond, result.message, result.throwable);
    }

    public static <U, V> DoubleResult<U, V> success(U first, V second) {
        return new DoubleResult<>(CODE_SUCCESS, first, second, MESSAGE_SUCCESS_DEFAULT, null);
    }

    public static <U, V> DoubleResult<U, V> error(int code, String message, Throwable throwable) {
        return new DoubleResult<>(code, null, null, message, throwable);
    }

    public static <U, V> DoubleResult<U, V> error(int code) {
        return new DoubleResult<>(code, null, null, MESSAGE_ERROR_DEFAULT, null);
    }

    public static <U, V> DoubleResult<U, V> error(String message) {
        return new DoubleResult<>(CODE_ERROR_DEFAULT, null, null, message, null);
    }

    public static <U, V> DoubleResult<U, V> error(int code, String message) {
        return new DoubleResult<>(code, null, null, message, null);
    }

    public static <U, V> DoubleResult<U, V> error(Throwable throwable) {
        return new DoubleResult<>(CODE_ERROR_DEFAULT, null, null, throwable.getMessage(), throwable);
    }
}
