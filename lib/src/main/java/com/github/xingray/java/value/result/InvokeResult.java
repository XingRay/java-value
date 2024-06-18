package com.github.xingray.java.value.result;

import java.util.function.Function;

public final class InvokeResult<T, E> {
    private final int code;
    private final T data;
    private final E error;
    private final String message;
    private final Throwable throwable;

    public InvokeResult() {
        this(CODE_SUCCESS, null, null, MESSAGE_SUCCESS_DEFAULT, null);
    }

    public InvokeResult(int code, T data, E error, String message, Throwable throwable) {
        this.code = code;
        this.data = data;
        this.error = error;
        this.message = message;
        this.throwable = throwable;
    }

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public E getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    @Override
    public String toString() {
        return "InvokeResult{" +
                "code=" + code +
                ", data=" + data +
                ", error=" + error +
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
    public static final InvokeResult<Object, Object> SUCCESS = new InvokeResult<>(CODE_SUCCESS, null, null, MESSAGE_SUCCESS_DEFAULT, null);
    public static final InvokeResult<Object, Object> ERROR = new InvokeResult<>(CODE_ERROR_DEFAULT, null, null, MESSAGE_ERROR_DEFAULT, null);

    public static <U, V> InvokeResult<U, V> success() {
        //noinspection unchecked
        return (InvokeResult<U, V>) SUCCESS;
    }

    public static <U, V> InvokeResult<U, V> error() {
        //noinspection unchecked
        return (InvokeResult<U, V>) ERROR;
    }

    public static <U, V> InvokeResult<U, V> result(boolean success) {
        return success ? success() : error();
    }

    public static <V, U, W> InvokeResult<V, W> copyIgnoreData(InvokeResult<U, W> result) {
        return new InvokeResult<>(result.code, null, result.error, result.message, result.throwable);
    }

    public static <V, U, W> InvokeResult<V, W> copy(InvokeResult<U, W> result, Function<U, V> mapper) {
        V data = null;
        if (result.isSuccess()) {
            data = mapper.apply(result.getData());
        }
        return new InvokeResult<>(result.code, data, result.error, result.message, result.throwable);
    }

    public static <U, V> InvokeResult<U, V> success(U data) {
        return new InvokeResult<>(CODE_SUCCESS, data, null, MESSAGE_SUCCESS_DEFAULT, null);
    }

    public static <U, V> InvokeResult<U, V> error(int code, V error, String message, Throwable throwable) {
        return new InvokeResult<>(code, null, error, message, throwable);
    }

    public static <U, V> InvokeResult<U, V> error(int code, String message, Throwable throwable) {
        return new InvokeResult<>(code, null, null, message, throwable);
    }

    public static <U, V> InvokeResult<U, V> error(int code) {
        return new InvokeResult<>(code, null, null, MESSAGE_ERROR_DEFAULT, null);
    }

    public static <U, V> InvokeResult<U, V> error(String message) {
        return new InvokeResult<>(CODE_ERROR_DEFAULT, null, null, message, null);
    }

    public static <U, V> InvokeResult<U, V> error(int code, String message) {
        return new InvokeResult<>(code, null, null, message, null);
    }

    public static <U, V> InvokeResult<U, V> error(Throwable throwable) {
        return new InvokeResult<>(CODE_ERROR_DEFAULT, null, null, throwable.getMessage(), throwable);
    }

    public static <U, V> InvokeResult<U, V> error(V error) {
        return new InvokeResult<>(CODE_ERROR_DEFAULT, null, error, MESSAGE_ERROR_DEFAULT, null);
    }

    public static <U, V> InvokeResult<U, V> error(InvokeResult<?, V> result) {
        return new InvokeResult<>(result.code, null, result.error, result.message, result.throwable);
    }
}
