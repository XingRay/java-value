package com.github.xingray.java.value.result;

import java.util.function.Function;

public final class RemoteInvokeResult<T, E> {
    private final int code;
    private final T data;
    private final E error;
    private final String message;

    public RemoteInvokeResult() {
        this(CODE_SUCCESS, null, null, MESSAGE_SUCCESS_DEFAULT);
    }

    public RemoteInvokeResult(int code, T data, E error, String message) {
        this.code = code;
        this.data = data;
        this.error = error;
        this.message = message;
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

    @Override
    public String toString() {
        return "InvokeResult{" +
                "code=" + code +
                ", data=" + data +
                ", error=" + error +
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
    public static final RemoteInvokeResult<Object, Object> SUCCESS = new RemoteInvokeResult<>(CODE_SUCCESS, null, null, MESSAGE_SUCCESS_DEFAULT);
    public static final RemoteInvokeResult<Object, Object> ERROR = new RemoteInvokeResult<>(CODE_ERROR_DEFAULT, null, null, MESSAGE_ERROR_DEFAULT);

    public static <U, V> RemoteInvokeResult<U, V> success() {
        //noinspection unchecked
        return (RemoteInvokeResult<U, V>) SUCCESS;
    }

    public static <U, V> RemoteInvokeResult<U, V> error() {
        //noinspection unchecked
        return (RemoteInvokeResult<U, V>) ERROR;
    }

    public static <U, V> RemoteInvokeResult<U, V> result(boolean success) {
        return success ? success() : error();
    }

    public static <V, U, W> RemoteInvokeResult<V, W> copyIgnoreData(RemoteInvokeResult<U, W> result) {
        return new RemoteInvokeResult<>(result.code, null, result.error, result.message);
    }

    public static <V, U, W> RemoteInvokeResult<V, W> copy(RemoteInvokeResult<U, W> result, Function<U, V> mapper) {
        V data = null;
        if (result.isSuccess()) {
            data = mapper.apply(result.getData());
        }
        return new RemoteInvokeResult<>(result.code, data, result.error, result.message);
    }

    public static <U, V> RemoteInvokeResult<U, V> success(U data) {
        return new RemoteInvokeResult<>(CODE_SUCCESS, data, null, MESSAGE_SUCCESS_DEFAULT);
    }

    public static <U, V> RemoteInvokeResult<U, V> error(int code, V error, String message) {
        return new RemoteInvokeResult<>(code, null, error, message);
    }

    public static <U, V> RemoteInvokeResult<U, V> error(int code) {
        return new RemoteInvokeResult<>(code, null, null, MESSAGE_ERROR_DEFAULT);
    }

    public static <U, V> RemoteInvokeResult<U, V> error(String message) {
        return new RemoteInvokeResult<>(CODE_ERROR_DEFAULT, null, null, message);
    }

    public static <U, V> RemoteInvokeResult<U, V> error(int code, String message) {
        return new RemoteInvokeResult<>(code, null, null, message);
    }

    public static <U, V> RemoteInvokeResult<U, V> error(V error) {
        return new RemoteInvokeResult<>(CODE_ERROR_DEFAULT, null, error, MESSAGE_ERROR_DEFAULT);
    }

    public static <U, V> RemoteInvokeResult<U, V> error(RemoteInvokeResult<?, V> result) {
        return new RemoteInvokeResult<>(result.code, null, result.error, result.message);
    }
}
