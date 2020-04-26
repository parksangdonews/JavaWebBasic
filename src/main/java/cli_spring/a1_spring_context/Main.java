package cli_spring.a1_spring_context;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) {

        log.info("Start !!  ~~ ");

        Dao dao = new Dao();

        dao.run();


    }




}
