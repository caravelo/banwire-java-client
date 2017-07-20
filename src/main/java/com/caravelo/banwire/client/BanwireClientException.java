package com.caravelo.banwire.client;

public class BanwireClientException extends Exception
{
    public BanwireClientException(String message)
    {
        super(message);
    }

    public BanwireClientException(Throwable e)
    {
        super(e);
    }
    public BanwireClientException(String message, Throwable e)
    {
        super(message, e);
    }
}
