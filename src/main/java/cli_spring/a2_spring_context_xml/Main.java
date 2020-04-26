package cli_spring.a2_spring_context_xml;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class Main {

    public static void main(String[] args) {

        log.info("Start !!  ~~ ");

        //cli_spring.a1_spring_context.Dao dao = new Dao();

        ApplicationContext context = new ClassPathXmlApplicationContext("dao.xml");


        Dao dao = context.getBean("dao" , Dao.class);
        dao.run();

        Dao dao_create = context.getBean("dao_create", Dao.class);
        dao_create.run();

        Dao dao_factory = context.getBean("daoBean", Dao.class);
        dao_factory.run();


        log.info(dao == dao_create ? "true" : "false");




    }




}
