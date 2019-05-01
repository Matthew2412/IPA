package org.mik.project;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx,MusicRepository musicRepository) {
        return args -> {
        	ArrayList<Music> musics = new ArrayList<Music>();
    		Music music = new Music("asd1", "asd", new String[] {"Rock"});
    		Music music1 = new Music("asd2", "asd",new String[] {"Jazz"});
    		Music music2 = new Music("asd3", "asd",new String[] {"Pop"});
    		Music music3 = new Music("asd4", "asd",new String[] {"Heavy Metal"});
    		musics.add(music);
    		musics.add(music1);
    		musics.add(music2);
    		musics.add(music3);
    		musicRepository.saveAll(musics);
            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }

}