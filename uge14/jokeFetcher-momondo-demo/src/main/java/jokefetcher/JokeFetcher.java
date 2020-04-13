package jokefetcher;

import com.google.gson.Gson;
import dto.ChuckNorrisDTO;
import dto.DadJokeDTO;
import dto.JokesDTO;
import java.io.IOException;
import utils.HttpUtils;


public class JokeFetcher {
    public static void main(String[] args) throws IOException {

        String chuck = HttpUtils.fetchData("https://api.chucknorris.io/jokes/random");
        String dad = HttpUtils.fetchData("https://icanhazdadjoke.com");
        
        System.out.println("JSON fetched from chucknorris:");
        System.out.println(chuck);
        System.out.println("JSON fetched from dadjokes:");
        System.out.println(dad);
        
       
    }
    
    public static JokesDTO fetchJokes() throws IOException{
        Gson gson = new Gson();
        String chuck = HttpUtils.fetchData("https://api.chucknorris.io/jokes/random");
        ChuckNorrisDTO chuckDTO = gson.fromJson(chuck, ChuckNorrisDTO.class);
             
        String dad = HttpUtils.fetchData("https://icanhazdadjoke.com");
        DadJokeDTO dadDTO = gson.fromJson(dad, DadJokeDTO.class);
        dadDTO.setCallReference("https://icanhazdadjoke.com");

        return new JokesDTO(chuckDTO, dadDTO);

    }
}
