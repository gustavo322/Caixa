package Testes;

import static java.awt.event.KeyEvent.VK_SPACE;
import static java.awt.event.KeyEvent.VK_TAB;

import org.fest.swing.fixture.FrameFixture;
import org.junit.Test;

import TeleLogin.TelaDeLogin;

public class TesteInicio extends CaixaUtils {

    protected static FrameFixture window;

    TelaDeLogin tel = new TelaDeLogin();

    @Test
    public void ConsultaExtrato() {
        TelaDeLogin frame = new TelaDeLogin();
        frame.setVisible(true);
        frame.setTitle("Caixa Eletrônico");
        frame.txtlogin.setText("08990305993");
        frame.Senha.setText("55806");
        CaixaUtils.pressionarTeclas(VK_TAB);
        CaixaUtils.pressionarTeclas(VK_TAB);
        CaixaUtils.pressionarTeclas(VK_TAB);
        CaixaUtils.pressionarTeclas(VK_SPACE);
        CaixaUtils.esperar(2000);
        CaixaUtils.pressionarTeclas(VK_SPACE);
        CaixaUtils.pressionarTeclas(VK_TAB);
        CaixaUtils.pressionarTeclas(VK_TAB);
        CaixaUtils.pressionarTeclas(VK_TAB);
        CaixaUtils.pressionarTeclas(VK_TAB);
        CaixaUtils.pressionarTeclas(VK_TAB);
        CaixaUtils.pressionarTeclas(VK_SPACE);
        CaixaUtils.esperar(3000);

    }

    @Test
    public void ConsultaSaldo() {
        TelaDeLogin frame = new TelaDeLogin();
        frame.setVisible(true);
        frame.setTitle("Caixa Eletrônico");
        frame.txtlogin.setText("08990305993");
        frame.Senha.setText("55806");
        CaixaUtils.pressionarTeclas(VK_TAB);
        CaixaUtils.pressionarTeclas(VK_TAB);
        CaixaUtils.pressionarTeclas(VK_TAB);
        CaixaUtils.pressionarTeclas(VK_SPACE);
        CaixaUtils.esperar(2000);
        CaixaUtils.pressionarTeclas(VK_SPACE);
        CaixaUtils.pressionarTeclas(VK_TAB);
        CaixaUtils.pressionarTeclas(VK_TAB);
        CaixaUtils.pressionarTeclas(VK_TAB);
        CaixaUtils.pressionarTeclas(VK_SPACE);
        CaixaUtils.esperar(1500);

    }

}
