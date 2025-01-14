
import gob.pe.icl.config.ConfigDao;
import gob.pe.icl.config.ConfigEntity;
import gob.pe.icl.config.ConfigService;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import lombok.extern.log4j.Log4j2;
import org.junit.After;
import org.junit.Before;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author usuario
 */

@Log4j2
public class TestBaseService {
    
    protected AnnotationConfigApplicationContext contextEntity = new AnnotationConfigApplicationContext(ConfigEntity.class);
    protected AnnotationConfigApplicationContext contextDao = new AnnotationConfigApplicationContext(ConfigDao.class);
    protected AnnotationConfigApplicationContext contextService = new AnnotationConfigApplicationContext(ConfigService.class);
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
}
