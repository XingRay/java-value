package com.github.xingray.java.value.result;

import java.util.function.Function;

public final class Result<T> {
    private final int code;
    private final T data;
    private final String message;
    private final Throwable throwable;

    public Result() {
        this(CODE_SUCCESS, null, MESSAGE_SUCCESS_DEFAULT, null);
    }

    public Result(int code, T data, String message, Throwable throwable) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.throwable = throwable;
    }

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", data=" + data +
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
    public static final Result<Object> SUCCESS = new Result<>(CODE_SUCCESS, null, MESSAGE_SUCCESS_DEFAULT, null);
    public static final Result<Object> ERROR = new Result<>(CODE_ERROR_DEFAULT, null, MESSAGE_ERROR_DEFAULT, null);

    public static <U> Result<U> success() {
        //noinspection unchecked
        return (Result<U>) SUCCESS;
    }

    public static <U> Result<U> error() {
        //noinspection unchecked
        return (Result<U>) ERROR;
    }

    public static <U> Result<U> result(boolean success) {
        return success ? success() : error();
    }

    public static <V, U> Result<V> copyIgnoreData(Result<U> result) {
        return new Result<>(result.code, null, result.message, result.throwable);
    }

    public static <V, U> Result<V> copy(Result<U> result, Function<U, V> mapper) {
        V data = null;
        U sourceData = result.getData();
        if (sourceData != null) {
            data = mapper.apply(sourceData);
        }
        return new Result<>(result.code, data, result.message, result.throwable);
    }

    public static <U> Result<U> success(U data) {
        return new Result<>(CODE_SUCCESS, data, MESSAGE_SUCCESS_DEFAULT, null);
    }

    public static <U> Result<U> error(int code, String message, Throwable throwable) {
        return new Result<>(code, null, message, throwable);
    }

    public static <U> Result<U> error(int code) {
        return new Result<>(code, null, MESSAGE_ERROR_DEFAULT, null);
    }

    public static <U> Result<U> error(String message) {
        return new Result<>(CODE_ERROR_DEFAULT, null, message, null);
    }

    public static <U> Result<U> error(int code, String message) {
        return new Result<>(code, null, message, null);
    }

    public static <U> Result<U> error(Throwable throwable) {
        return new Result<>(CODE_ERROR_DEFAULT, null, throwable.getMessage(), throwable);
    }
}
