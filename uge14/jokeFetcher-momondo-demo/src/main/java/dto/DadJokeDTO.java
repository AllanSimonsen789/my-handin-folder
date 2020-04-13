/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author allan
 */
public class DadJokeDTO {
   String id;
   String joke;
   int status;
   String callReference;

    public DadJokeDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCallReference() {
        return callReference;
    }

    public void setCallReference(String callReference) {
        this.callReference = callReference;
    }

    @Override
    public String toString() {
        return "DadJokeDTO{" + "id=" + id + ", joke=" + joke + ", status=" + status + ", callReference=" + callReference + '}';
    }
   
    

}
