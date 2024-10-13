package com.shirongbao.bank.exception;

/**
 * 转账异常
 */
public class TransferException extends Exception{
    public TransferException() {
    }
    public TransferException(String msg) {
        super(msg);
    }
}
