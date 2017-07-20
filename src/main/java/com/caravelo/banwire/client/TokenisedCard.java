package com.caravelo.banwire.client;

import java.util.Objects;

public class TokenisedCard
{
    private String id;
    private String token;
    private String task;
    private boolean result;
    private boolean exists;
    private String type;
    private String category;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenisedCard that = (TokenisedCard) o;
        return result == that.result &&
                exists == that.exists &&
                Objects.equals(id, that.id) &&
                Objects.equals(token, that.token) &&
                Objects.equals(task, that.task) &&
                Objects.equals(type, that.type) &&
                Objects.equals(category, that.category);
    }

    public String getCategory()
    {
        return category;
    }

    public String getId()
    {
        return id;
    }

    public String getTask()
    {
        return task;
    }

    public String getToken()
    {
        return token;
    }

    public String getType()
    {
        return type;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, token, task, result, exists, type, category);
    }

    public boolean isExists()
    {
        return exists;
    }

    public boolean isResult()
    {
        return result;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public void setExists(boolean exists)
    {
        this.exists = exists;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void setResult(boolean result)
    {
        this.result = result;
    }

    public void setTask(String task)
    {
        this.task = task;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        final StringBuffer sb = new StringBuffer("TokenisedCard{");
        sb.append("id='").append(id).append('\'');
        sb.append(", token='").append(token).append('\'');
        sb.append(", task='").append(task).append('\'');
        sb.append(", result=").append(result);
        sb.append(", exists=").append(exists);
        sb.append(", type='").append(type).append('\'');
        sb.append(", category='").append(category).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
