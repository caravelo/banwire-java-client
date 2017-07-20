package com.caravelo.banwire.client;

import java.math.BigDecimal;
import java.util.Objects;

public class Purchase
{
    private String id;
    private String transactionId;
    private String authCode;
    private String reference;
    private String description;
    private BigDecimal amount;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return Objects.equals(id, purchase.id) &&
                Objects.equals(transactionId, purchase.transactionId) &&
                Objects.equals(authCode, purchase.authCode) &&
                Objects.equals(reference, purchase.reference) &&
                Objects.equals(description, purchase.description) &&
                Objects.equals(amount, purchase.amount);
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public String getAuthCode()
    {
        return authCode;
    }

    public String getDescription()
    {
        return description;
    }

    public String getId()
    {
        return id;
    }

    public String getReference()
    {
        return reference;
    }

    public String getTransactionId()
    {
        return transactionId;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, transactionId, authCode, reference, description, amount);
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public void setAuthCode(String authCode)
    {
        this.authCode = authCode;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void setReference(String reference)
    {
        this.reference = reference;
    }

    public void setTransactionId(String transactionId)
    {
        this.transactionId = transactionId;
    }

    @Override
    public String toString()
    {
        final StringBuffer sb = new StringBuffer("Purchase{");
        sb.append("id='").append(id).append('\'');
        sb.append(", transactionId='").append(transactionId).append('\'');
        sb.append(", authCode='").append(authCode).append('\'');
        sb.append(", reference='").append(reference).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", amount=").append(amount);
        sb.append('}');
        return sb.toString();
    }
}
