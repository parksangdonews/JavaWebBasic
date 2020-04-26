package cli_spring.a2_spring_context_xml;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FactoryClass {
    public static Dao dao = new Dao();

    public Dao createDaoInstance() {
        log.info("Factory Example...........");
        return dao;
    }

}
