package com.github.xingray.java.value.result;

import java.util.function.Function;

public final class RemoteResult<T> {
    private final int code;
    private final T data;
    private final String message;

    public RemoteResult() {
        this(CODE_SUCCESS, null, MESSAGE_SUCCESS_DEFAULT);
    }

    public RemoteResult(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
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

    @Override
    public String toString() {
        return "RemoteResult{" +
                "code=" + code +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }

    public boolean isSuccess() {
        return code == CODE_SUCCESS;
    }

    public static final int CODE_SUCCESS = 0;
    public static final String MESSAGE_SUCCESS_DEFAULT = "success";
    public static final int CODE_ERROR_DEFAULT = -1;
    public static final String MESSAGE_ERROR_DEFAULT = "error";
    public static final RemoteResult<Object> SUCCESS = new RemoteResult<>(CODE_SUCCESS, null, MESSAGE_SUCCESS_DEFAULT);
    public static final RemoteResult<Object> ERROR = new RemoteResult<>(CODE_ERROR_DEFAULT, null, MESSAGE_ERROR_DEFAULT);

    public static <U> RemoteResult<U> success() {
        //noinspection unchecked
        return (RemoteResult<U>) SUCCESS;
    }

    public static <U> RemoteResult<U> error() {
        //noinspection unchecked
        return (RemoteResult<U>) ERROR;
    }

    public static <U> RemoteResult<U> result(boolean success) {
        return success ? success() : error();
    }

    public static <V, U> RemoteResult<V> copyIgnoreData(RemoteResult<U> result) {
        return new RemoteResult<>(result.code, null, result.message);
    }

    public static <V, U> RemoteResult<V> copy(RemoteResult<U> result, Function<U, V> mapper) {
        V data = null;
        U sourceData = result.getData();
        if (sourceData != null) {
            data = mapper.apply(sourceData);
        }
        return new RemoteResult<>(result.code, data, result.message);
    }

    public static <U> RemoteResult<U> success(U data) {
        return new RemoteResult<>(CODE_SUCCESS, data, MESSAGE_SUCCESS_DEFAULT);
    }

    public static <U> RemoteResult<U> error(int code) {
        return new RemoteResult<>(code, null, MESSAGE_ERROR_DEFAULT);
    }

    public static <U> RemoteResult<U> error(String message) {
        return new RemoteResult<>(CODE_ERROR_DEFAULT, null, message);
    }

    public static <U> RemoteResult<U> error(int code, String message) {
        return new RemoteResult<>(code, null, message);
    }
}
