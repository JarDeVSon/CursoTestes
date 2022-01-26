package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class AcoesSteps {

    private static WebDriver driver;
    private static String nomeCenario;
    private static File diretorio;
    private String acao;

    public static void setNomeCenario(String nomeCenario) {
        AcoesSteps.nomeCenario = nomeCenario;
    }

    /*
        Execução ANTES de cada cenario de teste
         */
    @Before
    public void abreNavegador(Scenario scenario){
        WebDriverManager.firefoxdriver().setup();
        //WebDriverManager.chromedriver().setup();
        //driver = new ChromeDriver();
        driver = new FirefoxDriver();
        setNomeCenario(scenario.getName());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    /*
    Execução DEPOIS de cada cenario de teste
     */
    @After
    public void fechaNavegador(Scenario scenario) throws Exception {
        System.out.println(scenario.getName() + " | Status: " + scenario.getStatus());
        try {
         String path = "src/test/resources/evidencias";
         diretorio = new File(path + "/" + nomeCenario);
         diretorio.mkdir();

         File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
         String caminho = diretorio.getPath()+"/"+ scenario.getName() + ".png";
         FileUtils.copyFile(file, new File(caminho));

        } catch (Exception e) {
            throw new Exception("Falha!!! Deu ruim!");
        }
        driver.quit();
    }


    @Dado("que esteja na pagina {string}")
    public void queEstejaNaPagina(String url) {
        driver.get(url);
    }
    @Quando("preencho o campo pesquisa com {string}")
    public void preenchoOCampoPesquisaCom(String acao) {
        this.acao = acao;
        String xpathPesquisa = "//input[@name='q']";
        WebElement pesquisa = driver.findElement(By.xpath(xpathPesquisa));
        pesquisa.sendKeys(acao);
    }
    @Quando("clico em Pesquisa Google")
    public void clicoEmPesquisaGoogle() {
       String cssPesquisaGoogle = ".FPdoLc > center:nth-child(1) > input";
       WebElement cssPesquisar = driver.findElement(By.cssSelector(cssPesquisaGoogle));
       cssPesquisar.click();

    }
    @Entao("exibe o valor da acao")
    public void exibeOValorDaAcao() {
        String xpathValor = "//span[@jsname='vWLAgc']";
        WebElement spanValor = driver.findElement(By.xpath(xpathValor));
        spanValor.getText();
        System.out.println(acao + " O valor da acao: " + spanValor.getText());
    }
}
