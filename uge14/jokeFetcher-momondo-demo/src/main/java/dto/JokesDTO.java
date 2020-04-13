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
public class JokesDTO {

    String joke1;
    String joke1reference;
    String joke2;
    String joke2reference;

    public JokesDTO(ChuckNorrisDTO cndto, DadJokeDTO djdto) {
        this.joke1 = cndto.value;
        this.joke1reference = cndto.url;
        this.joke2 = djdto.joke;
        this.joke2reference = djdto.callReference;
    }

    public String getJoke1() {
        return joke1;
    }

    public void setJoke1(String joke1) {
        this.joke1 = joke1;
    }

    public String getJoke1reference() {
        return joke1reference;
    }

    public void setJoke1reference(String joke1reference) {
        this.joke1reference = joke1reference;
    }

    public String getJoke2() {
        return joke2;
    }

    public void setJoke2(String joke2) {
        this.joke2 = joke2;
    }

    public String getJoke2reference() {
        return joke2reference;
    }

    public void setJoke2reference(String joke2reference) {
        this.joke2reference = joke2reference;
    }

    @Override
    public String toString() {
        return "JokesDTO{" + "joke1=" + joke1 + ", joke1reference=" + joke1reference + ", joke2=" + joke2 + ", joke2reference=" + joke2reference + '}';
    }

    
}
