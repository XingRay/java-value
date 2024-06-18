package com.github.xingray.java.value.result;

import java.util.function.Function;

public final class TripleResult<T1, T2, T3> {
    private final int code;
    private final T1 first;
    private final T2 second;
    private final T3 third;
    private final String message;
    private final Throwable throwable;

    public TripleResult() {
        this(CODE_SUCCESS, null, null, null, MESSAGE_SUCCESS_DEFAULT, null);
    }

    public TripleResult(int code, T1 first, T2 second, T3 third, String message, Throwable throwable) {
        this.code = code;
        this.first = first;
        this.second = second;
        this.third = third;
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

    public T3 getThird() {
        return third;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    @Override
    public String toString() {
        return "TripleResult{" +
                "code=" + code +
                ", first=" + first +
                ", second=" + second +
                ", third=" + third +
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
    public static final TripleResult<Object, Object, Object> SUCCESS = new TripleResult<>(CODE_SUCCESS, null, null, null, MESSAGE_SUCCESS_DEFAULT, null);
    public static final TripleResult<Object, Object, Object> ERROR = new TripleResult<>(CODE_ERROR_DEFAULT, null, null, null, MESSAGE_ERROR_DEFAULT, null);

    public static <U, V, W> TripleResult<U, V, W> success() {
        //noinspection unchecked
        return (TripleResult<U, V, W>) SUCCESS;
    }

    public static <U, V, W> TripleResult<U, V, W> error() {
        //noinspection unchecked
        return (TripleResult<U, V, W>) ERROR;
    }

    public static <U, V, W> TripleResult<U, V, W> result(boolean success) {
        return success ? success() : error();
    }

    public static <U, V, W, X, Y, Z> TripleResult<X, Y, Z> copyIgnoreData(TripleResult<U, V, W> result) {
        return new TripleResult<>(result.code, null, null, null, result.message, result.throwable);
    }

    public static <U, V, W, X, Y, Z> TripleResult<X, Y, Z> copy(TripleResult<U, V, W> result, Function<U, X> firstMapper, Function<V, Y> secondMapper, Function<W, Z> thirdMapper) {
        X targetFirst = null;
        U sourceFirst = result.getFirst();
        if (sourceFirst != null && firstMapper != null) {
            targetFirst = firstMapper.apply(sourceFirst);
        }

        Y targetSecond = null;
        V sourceSecond = result.getSecond();
        if (sourceSecond != null && secondMapper != null) {
            targetSecond = secondMapper.apply(sourceSecond);
        }

        Z targetThird = null;
        W sourceThird = result.getThird();
        if (sourceThird != null && thirdMapper != null) {
            targetThird = thirdMapper.apply(sourceThird);
        }

        return new TripleResult<>(result.code, targetFirst, targetSecond, targetThird, result.message, result.throwable);
    }

    public static <U, V, W> TripleResult<U, V, W> success(U first, V second, W third) {
        return new TripleResult<>(CODE_SUCCESS, first, second, third, MESSAGE_SUCCESS_DEFAULT, null);
    }

    public static <U, V, W> TripleResult<U, V, W> error(int code, String message, Throwable throwable) {
        return new TripleResult<>(code, null, null, null, message, throwable);
    }

    public static <U, V, W> TripleResult<U, V, W> error(int code) {
        return new TripleResult<>(code, null, null, null, MESSAGE_ERROR_DEFAULT, null);
    }

    public static <U, V, W> TripleResult<U, V, W> error(String message) {
        return new TripleResult<>(CODE_ERROR_DEFAULT, null, null, null, message, null);
    }

    public static <U, V, W> TripleResult<U, V, W> error(int code, String message) {
        return new TripleResult<>(code, null, null, null, message, null);
    }

    public static <U, V, W> TripleResult<U, V, W> error(Throwable throwable) {
        return new TripleResult<>(CODE_ERROR_DEFAULT, null, null, null, throwable.getMessage(), throwable);
    }
}
